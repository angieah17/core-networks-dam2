import { useState } from "react";
import { apiUsers } from "../services/api";

   interface User{
        id: number,
        name: string,
        email: string,
        phone: string,
    }
 

   
    
    export default function CrudUsers() {

        const [loading, setLoading] = useState<boolean>(false)
        const [users, setUsers] = useState<User[]>([])
        const [user, setUser] = useState<User | null>(null)

        const dameListaDeUsuarios = async () => {
            setLoading(true)
            try {
                const response = await apiUsers.get<User[]>('/users'); //Con esta línea se pide a la API
                setUsers(response.data) //Con esta línea se actualiza en React, es decir, en nuestra app
            } catch (error) {
                console.error('Error al obtener usuarios: ', error);
            } finally{
                setLoading(false)
            }
        }

        const verUsuario = async (id: number) => {
            setLoading(true)

            try {
                const response = await apiUsers.get<User>('/users/' + id);
                setUser(response.data)
            } catch (error) {
                console.error('Error al obtener usuario: ', error);
            } finally{
                setLoading(false)
            }
        }

            
    /*el put, post, borrar
    */ 
        const borrarUsuario = async (id: number) => {
            setLoading(true)
            
            try {
                //En delete no se recomienda tipar, porque a veces devuelve 204 No Content o message Deleted
                await apiUsers.delete('/users/' + id); //Con esta línea el servidor borra el usuario en la API
                setUsers(users.filter(u => u.id !== id)) //Con esta línea se actualiza en la aplicación (en memoria del navegador)          
            } catch (error) {
                console.error('Error al eliminar de tipo: ', error);
            } finally{
                setLoading(false)
            }
        }

        const [nameNuevo, setNameNuevo] = useState<string>("");
        const [emailNuevo, setEmailNuevo] = useState<string>("");
        const [phoneNuevo, setPhoneNuevo] = useState<string>("");
        const [idEnEdicion, setIdEnEdicion] = useState<number | null>(null);
        
        //Se crea esta función para que permita tomar el usuario a editar
        const seleccionarParaEditar = (usuario: User) => {
            setIdEnEdicion(usuario.id);
            setNameNuevo(usuario.name);
            setEmailNuevo(usuario.email);
            setPhoneNuevo(usuario.phone);
        }

        const actualizarUsuario = async (id: number) => {
            
            //Validación
            if (!nameNuevo.trim() || !emailNuevo.trim() || !phoneNuevo.trim()) {
                alert('Nombre, email o teléfono son requeridos');
                return;
            }
            
            setLoading(true)
            
            const usuarioActualizado: User = {
                id: id,
                name: nameNuevo,
                email: emailNuevo,
                phone: phoneNuevo,
            }

            try {
                await apiUsers.put('/users/' + id, usuarioActualizado);
                setUsers(users.map(
                    (u: User) => u.id === usuarioActualizado.id ? usuarioActualizado : u
                ));

                setNameNuevo("");
                setEmailNuevo("");
                setPhoneNuevo("");
                setIdEnEdicion(null);
            } catch (error) {
                console.error('Error en PUT (actualizar usuario) ', error);
            }finally{
                setLoading(false)
            }

            
        }

        const agregarNuevoUsuario = async () => {
            
            /*SALE ERROR 404 porque esta API no deja agregar usuarios a la API por eso el ver no funciona
            */ 


            // Validación
            if (!nameNuevo.trim() || !emailNuevo.trim() || !phoneNuevo.trim()) {
                alert('Nombre, email y teléfono son requeridos');
                return;
            }


            const nuevoUsuario = {
                name: nameNuevo,
                email: emailNuevo,
                phone: phoneNuevo,
            }

            try {
                const response = await apiUsers.post("/users", nuevoUsuario);
                setUsers([...users, response.data]);

                //Se limpian solo si han funcionado
                setNameNuevo("");
                setEmailNuevo("");
                setPhoneNuevo("");
                
            } catch (error) {
                console.error('Error en POST (agregar usuario) ', error);
            } finally{
                setLoading(false)
            }


            
        }

        
      return (
        <>
            <h2>Listado de Usuarios</h2>
            <button onClick={dameListaDeUsuarios} disabled={loading}>{loading ? 'Cargando ...' : 'Dame Lista de Usuarios'}</button>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    {
                        users.map(
                            (u: User) => 
                            <tr key={u.id}>
                                <td>{u.id}</td>
                                <td>{u.name}</td>
                                <td>{u.email}</td>
                                <td>{u.phone}</td>
                                <td>
                                    <button onClick={() => verUsuario(u.id)}>Ver</button>
                                    <button onClick={() => borrarUsuario(u.id)}>Borrar</button>
                                    <button onClick={() => seleccionarParaEditar(u)}>Editar</button>
                                </td>
                            </tr>
                        )
                    }
                    
                </tbody>
            </table>
            
            {user &&
            <ul>
                <li>Id: {user?.id}</li>
                <li>Name: {user?.name}</li>
                <li>Email: {user?.email}</li>
                <li>Phone: {user?.phone}</li>
            </ul>
            }

            
            <h2>Formulario de Usuarios</h2>
            {idEnEdicion && <h2>Actualizar el usuario {idEnEdicion}</h2>}
            <input type="text" placeholder="Name ..." value={nameNuevo}  onChange={(e) => setNameNuevo(e.target.value)}/>  <br />
            <input type="text" placeholder="Email ..." value={emailNuevo}  onChange={(e) => setEmailNuevo(e.target.value)}/> <br />
            <input type="text" placeholder="Phone ..." value={phoneNuevo}  onChange={(e) => setPhoneNuevo(e.target.value)}/> <br />
            {idEnEdicion && <button onClick={() => actualizarUsuario(idEnEdicion)}>Actualizar Usuario</button> }
            <button onClick={agregarNuevoUsuario} disabled={idEnEdicion !== null || loading}>Agregar Usuario </button>
               
            
            
            
        </>
      )
    }
    
  