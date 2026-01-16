import { useState } from "react"
import { apiCliente, apliClienteSpring } from "../services/api";



interface Cliente {
    id: number,
    nombre: string,
    apellido: string,
    email: string,
}

export default function CrudClientes() {

    const [cliente, setCliente] = useState<Cliente | null>(null);
    const [clientes, setClientes] = useState<Cliente []>([]);
    const [loading, setLoading] = useState<boolean>(false);

    //Listado de Clientes (GET)
    const dameListaDeClientes = async () => {

        setLoading(true);

        try {
            const response = await apliClienteSpring.get<Cliente []>('/todos');
            setClientes(response.data);
            console.log(response.data)
        } catch (error) {
            console.error('Error al obtener clientes: ', error);
        } finally{
            setLoading(false)
        }

    }

    //Ver 1 Cliente (GET)
    const verCliente = async (id: number) => {
                setLoading(true)
    
                try {
                    const response = await apiCliente.get<Cliente>('/clientes/' + id);
                    setCliente(response.data)
                } catch (error) {
                    console.error('Error al obtener cliente: ', error);
                } finally{
                    setLoading(false)
                }
            }
    //Borrar 1 Cliente (DELETE)
    const borrarCliente = async (id: number) => {
                setLoading(true)
                
                try {
                    //En delete no se recomienda tipar, porque a veces devuelve 204 No Content o message Deleted
                    await apiCliente.delete('/clientes/' + id); //Con esta línea el servidor borra el usuario en la API
                    setClientes(clientes.filter(c => c.id !== id)) //Con esta línea se actualiza en la aplicación (en memoria del navegador)   
                } catch (error) {
                    console.error('Error al eliminar Cliente: ', error);
                } finally{
                    setLoading(false)
                }
            }
    
    //Actualizar Cliente (PUT)
    const [nombreNuevo, setNombreNuevo] = useState<string>("");
    const [apellidoNuevo, setApellidoNuevo] = useState<string>("");
    const [telefonoNuevo, setTelefonoNuevo] = useState<string>("");
    const [idEnEdicion, setIdEnEdicion] = useState<number | null>(null);

     //Se crea esta función para que permita tomar el usuario a editar
        const seleccionarParaEditar = (cliente: Cliente) => {
            setIdEnEdicion(cliente.id);
            setNombreNuevo(cliente.nombre);
            setApellidoNuevo(cliente.apellido);
            setTelefonoNuevo(cliente.email);
        }
    
        const actualizarCliente = async (id: number) => {

            //Validación
            if (!nombreNuevo.trim() || !apellidoNuevo.trim() || !telefonoNuevo.trim()) {
                alert('Nombre, email o teléfono son requeridos');
                return;
            }
            
            setLoading(true)

            const clienteActualizado: Cliente = {
                id: id,
                nombre: nombreNuevo,
                apellido: apellidoNuevo,
                email: telefonoNuevo,
            }
            
            try {
                
                await apiCliente.put('/clientes/' + id, clienteActualizado); //siguiente de la , la información que envío al servidor
                setClientes(clientes.map(
                    (c: Cliente) => c.id === clienteActualizado.id ? clienteActualizado : c
                )) 
                setNombreNuevo("");
                setApellidoNuevo("");
                setTelefonoNuevo("");
                setIdEnEdicion(null);
            } catch (error) {
                console.error('Error al actualizar el Cliente: ', error);
            } finally{
                setLoading(false)
            }

        }
    
        //Nuevo Cliente (POST)
        
        const agregarNuevoCliente = async () => {
            
            if (!confirm(`¿Seguro que quieres crear el cliente?`)) return;
            
            // Validación
            if (!nombreNuevo.trim() || !apellidoNuevo.trim() || !telefonoNuevo.trim()) {
                alert('Nombre, apellido y teléfono son requeridos');
                return;
            }


            const nuevoCliente = {
                nombre: nombreNuevo,
                apellido: apellidoNuevo,
                telefono: telefonoNuevo,
            }

            try {
                const response = await apiCliente.post("/clientes", nuevoCliente);
                setClientes([...clientes, response.data]);

                //Se limpian solo si han funcionado
                setNombreNuevo("");
                setApellidoNuevo("");
                setTelefonoNuevo("");
                
            } catch (error) {
                console.error('Error agregando Cliente ', error);
            } finally{
                setLoading(false)
            }
        
        
                    
        }

  return (
    <>
        <h2>Listado de Clientes</h2>
        <button onClick={dameListaDeClientes} disabled={loading}>{loading ? 'Cargando ...' : 'Dame Lista de Clientes'}</button>

        <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Teléfono</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    {
                        clientes.map(
                            (c: Cliente) => 
                            <tr key={c.id}>
                                <td>{c.id}</td>
                                <td>{c.nombre}</td>
                                <td>{c.apellido}</td>
                                <td>{c.email}</td>
                                <td>
                                    <button onClick={() => verCliente(c.id)}>Ver</button>
                                    <button onClick={() => borrarCliente(c.id)}>Borrar</button>
                                    <button onClick={() => seleccionarParaEditar(c)}>Editar</button>
                                </td>
                            </tr>
                        )
                    }
                    
                </tbody>
            </table>
        
        {cliente &&
            <ul>
                <li>Id: {cliente.id}</li>
                <li>Nombre: {cliente.nombre}</li>
                <li>Apellido: {cliente.apellido}</li>
                <li>Teléfono: {cliente.email}</li>
            </ul>
        }

        <h2>Formulario de Clientes</h2>
        {idEnEdicion && <h2>Actualizar el cliente {idEnEdicion}</h2>}
        <input type="text" placeholder="Nombre ..." value={nombreNuevo}  onChange={(e) => setNombreNuevo(e.target.value)}/>  <br />
        <input type="text" placeholder="Apellido ..." value={apellidoNuevo}  onChange={(e) => setApellidoNuevo(e.target.value)}/> <br />
        <input type="text" placeholder="Teléfono ..." value={telefonoNuevo}  onChange={(e) => setTelefonoNuevo(e.target.value)}/> <br />
        {idEnEdicion && <button onClick={() => actualizarCliente(idEnEdicion)}>Actualizar Cliente</button> }
         <button onClick={agregarNuevoCliente} disabled={idEnEdicion !== null || loading}>Agregar Cliente </button>


    
    </>
  )
}
