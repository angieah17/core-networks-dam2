import { useState, type JSX } from "react";
import { apiProducts, apiUsers } from "../services/api";

interface User{
    id: number,
    name: string,
    email: string,
    phone: string,
}

interface Producto{
    id:number, 
    title: string,
    price: number,
    category: string,
}

export default function MultipleApis():JSX.Element {

    const [users, setUsers] = useState<User[]>([]);
    const [products, setProducts] = useState<Producto[]>([]);
    const [loading, setLoading] = useState<boolean>(false);

    const obtenerUsers = async () => {
        setLoading(true)
        try {
            const response = await apiUsers.get<User[]>('/users');
            setUsers(response.data);
        } catch (error) {
            console.error('Error de tipo: ', error);
        } finally{
            setLoading(false)
        }
        
        
    }

    const obtenerProductos = async () =>{
        setLoading(true)

        try {
            const response = await apiProducts.get<Producto[]>('/products');
            setProducts(response.data);
        } catch (error) {
             console.error('Error de tipo: ', error);
        } finally{
            setLoading(false)
        }
        
    }

  return (
    <>
        <h2>Probando múltiples Apis</h2>
        <button onClick={obtenerUsers} disabled={loading}>{loading ? 'Cargando ...' : 'Obtener Usuarios'} </button>
        
        <ul>
            { users.map(
            (u: User) => 
            
                <li key={u.id}>Nombre {u.name}, teléfono: {u.phone}, email: {u.email}</li>
     
            )
        }
        </ul>

        <button onClick={obtenerProductos} disabled={loading}>{loading ? 'Cargando ...' : 'Obtener Productos'} </button>
        
        <ul>
            { products.map(
            (p: Producto) => 
            
                <li key={p.id}>Title: {p.title}, Price: {p.price}, Category: {p.category}</li>
     
            )
        }
        </ul>

        
        

    </>
  )
}
