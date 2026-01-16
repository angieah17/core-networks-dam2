import { useState, type FormEvent } from "react"
import { apiUsuario } from "../../services/api"

/*
Customer: Representa un cliente completo que viene del servidor (con id)
CustomerForm: Representa los datos del formulario (sin id, porque el servidor lo genera)
*/

// Datos completos que vienen de la API (incluye id)
interface Customer{
    id: number,
    name: string,
    country: string,
}

// Datos del formulario (sin id ni campos autogenerados)
interface CustomerForm {
    name: string,
    country: string,
}


export default function CRUDUsuarios() {

    //1. CONFIGURAR ESTADOS DEL COMPONENTE

    // 1.1 Usuario individual para mostrar detalle
    const [customer, setCustomer] = useState<Customer | null>(null)

    // 1.2 Lista completa de usuarios
    const [customers, setCustomers] = useState<Customer [] >([])

    // 1.3 Indicador de carga (deshabilita botones)
    const [loading, setLoading] = useState<boolean>(false)
    // 1.4 Mensajes de error para el usuario
    const [error, setError] = useState<string | null>(null)
    
    
    //1.5 Datos del formulario
    // Nuevos estados para el formulario
    const formDataEnBlanco = {
        name: '',
        country: ''
    }

    const [formData, setFormData] = useState<CustomerForm>(formDataEnBlanco)
    
    //1.6 ID del cliente en edición (null = modo CREAR, si tiene un número modo EDITAR)
    const [editingId, setEditingId] = useState<number | null>(null)

    //2. MÉTODOS PETICIONES HTTP

    //2.1 OBTENER TODOS LOS USUARIOS (GET)

    const findAllCustomers = async () => {
        setError(null); //Limpiar errores previos
        setLoading(true); //Activar indicador de carga

        try {
          const response = await apiUsuario.get<Customer[]>('/customers/');
          setCustomers(response.data)
        } catch (error) {
          console.error(error);
          setError('Error al cargar los clientes');
        }finally{
            setLoading(false) //Siempre desactivar loading
        }

    }

    //2.2. OBTENER UN CUSTOMER POR ID (GET)

    const findCustomerById = async (id: number) => {
        setError(null); //Limpia errores anteriores
        setLoading(true); //Muestra "cargando"
        try {
            const response = await apiUsuario.get<Customer>('/customers/' + id);
            setCustomer(response.data); // Guarda el customer en el estado
        } catch (error) {
            console.error(error);
            setError('Error al encontrar el cliente');
        } finally {
            setLoading(false); // Siempre quita el "cargando"
        }
    }

    //2.3 CREAR UN CUSTOMER (POST)

    const createCustomer = async (customerData: CustomerForm) => {
        
        if (!confirm(`¿Seguro que quieres crear el cliente?`)) return;
        setError(null)
        setLoading(true)
        
        try {
            const response = await apiUsuario.post<Customer>('/customers/', customerData)
            setCustomers([...customers, response.data])
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error al crear el cliente')
        } finally {
            setLoading(false);
        }
    }
    
    //2.4 ACTUALIZAR CUSTOMER (PUT)

     const updateCustomer = async (id: number, customerData: CustomerForm) => {
        setError(null)
        setLoading(true)
        try {
            const response = await apiUsuario.put<Customer>('/customers/' + id, customerData)
            setCustomers(customers.map(c => c.id === id ? response.data : c));
            if (customer?.id === id) setCustomer(response.data);
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error al actualizar el cliente');
        } finally {
            setLoading(false);
        }
    }   

    //2.5. ELIMINAR UN CUSTOMER (DELETE)
    
    const deleteCustomerById = async (id: number) => {
        if (!confirm(`¿Seguro que quieres borrar el cliente ${id}?`)) return;
        setError(null);
        setLoading(true);
        try {
            await apiUsuario.delete<Customer>('/customers/' + id);
            // Actualiza la lista en memoria (sin recargar) Y FILTER crea un nuevo array
            setCustomers(customers.filter(c => c.id !== id));
            if (customer?.id === id) setCustomer(null);
            // ? operador de encadenamiento opcional, permite hacer la verificación solo si customer existe,
            //así se evita el error al intentar acceder a un customer null
        } catch (error) {
            console.error(error);
            setError('Error al borrar el cliente');
        } finally {
            setLoading(false);
        }
    }


    //6. MÉTODOS AUXILIARES
    // 6.1 RESETEAR DATOS DEL FORM

    const resetForm = () => {
        setEditingId(null);  // Vuelve a modo CREAR
        setFormData(formDataEnBlanco); //Limpiar los campos
    } 

    //6.2 PREPARA EL FORMULARIO PARA EDICIÓN
    const handleEdit = (c: Customer) => {
        setFormData({ //Guarda los datos a subir
            name: c.name,
            country: c.country
        })
        setEditingId(c.id) // Cambia a modo EDIT
    }

    //6.2 MANEJADOR DEL FORMULARIO
    const handleSubmit = (e: FormEvent) => {
        e.preventDefault() //evita que la página se recargue, comportamiento por defecto del form en html
        
        // Validación: verifica que los campos no estén vacíos
        if (!formData.name.trim() || !formData.country.trim()) {
            setError('Por favor, completa todos los campos')
            return
        }
         // Decide qué hacer según el modo
        if (editingId) {
            updateCustomer(editingId, formData)
        } else {
            createCustomer(formData)
        }
    }




  return (
    <>
        <h2>CRUD de customers{loading && <span> cargando...</span>}</h2>

            {error && <strong>{error}</strong>}

        <h3>Creación de customers</h3>
        <form onSubmit={handleSubmit}>
                    <div>
                        <label>
                            Nombre: 
                            <input 
                                type="text" 
                                value={formData.name}
                                onChange={e => setFormData({...formData, name: e.target.value})}
                                disabled={loading}
                                placeholder="Nombre del cliente"
                            />
                        </label>
                    </div>
                    <div>
                        <label>
                            País: 
                            <input 
                                type="text" 
                                value={formData.country}
                                onChange={(e) => setFormData({...formData, country: e.target.value})}
                                disabled={loading}
                                placeholder="País del cliente"
                            />
                        </label>
                    </div>
                    <button type="submit" disabled={loading}>
                        {editingId ? 'Actualizar' : 'Crear'}
                    </button>
                    {editingId && (
                        <button type="button" onClick={resetForm} disabled={loading}>
                            Cancelar edición
                        </button>
                    )}
                </form>
        
        <h3>Información de customer</h3>
        <button disabled={loading} onClick={() => setCustomer(null)}>Ocultar customer</button>
        {
            customer 
            && 
            <ul>
                <li>Id: {customer.id}</li>
                <li>Name: {customer.name}</li>
                <li>Country: {customer.country}</li>
            </ul>
        }
        <h3>Lista de customers</h3>
        <button disabled={loading} onClick={findAllCustomers}>Actualizar lista</button>
        {
            customers.length !==0 
            &&
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Country</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        customers.map(c => 
                            <tr key={c.id}>
                                <td>{c.id}</td>
                                <td>{c.name}</td>
                                <td>{c.country}</td>
                                <td>
                                    <button className="actions" 
                                        disabled={loading}
                                        onClick={() => findCustomerById(c.id)}>Ver</button>
                                    <button className="actions" 
                                        disabled={loading}
                                        onClick={() => handleEdit(c)}>Editar</button>
                                    <button className="actions" 
                                        disabled={loading}
                                        onClick={() => deleteCustomerById(c.id)}>Borrar</button>                           
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        }
    </>
  )
}
