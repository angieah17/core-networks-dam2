import axios from "axios";
import { useState } from "react";

/*Recordar que es necesario instalar: npm install axios*/

type Task = {
    id: number,
    title: string,
    assignedTo: string,
    priority: string,
    completed: boolean,
}

type TaskForm = {
    title: string,
    assignedTo: string,
    priority: string,
    completed: boolean,
}


export default function CRUDTasks() {

    //I.Definicion de los estados

    //1. task
    const [task, setTask] = useState<Task | null>(null);

    //2. tasks
    const [tasks, setTasks] = useState<Task[]>([]);

    //3. error (permite personalizar los errores)
    const [error, setError] = useState<string | null>(null);

    //4. loading 
    const [loading, setLoading] = useState<boolean>(false);

    //5. editingId (si es null CREA y si es number ACTUALIZA)
    const [editingId, setEditingId] = useState<number | null>(null);
    //6. formData

    const formDataEnBlanco : TaskForm = {
        title: '',
        assignedTo: '',
        priority: '',
        completed: false,
    }

    const [formData, setFormData] = useState<TaskForm>(formDataEnBlanco);

    //II. METODOS

    //1. Mostrar lista de tareas en tabla (GET)

    const BASE_URL = "https://6968b37169178471522b4549.mockapi.io/api/v1/tasks/tasks"

    const mostrarListaDeTareas = async () => {
        setError(null);
        setLoading(true);
        try {
            const response = await axios.get<Task[]>(BASE_URL);
            setTasks(response.data);
        } catch (error) {
            console.error(error);
            setError('Error mostrando lista de tareas.');
        } finally{
            setLoading(false);
        }
    }

    //2. Ver detalle de una tarea (GET)
    const verDetalle = async (id: number) => {
        setError(null);
        setLoading(true);
        try {
            const response = await axios.get<Task>(`${BASE_URL}/${id}`);
            setTask(response.data);
        } catch (error) {
            console.error(error);
            setError('Error mostrando detalle de la tarea: ' + id);
        }finally{
            setLoading(false); 
        }
    }

    //e. Elimiar tarea (DELETE)
    const eliminarTarea = async (id:number) => {
        
        const tarea = tasks.find(t => t.id === id);

        if(!confirm(`¿Eliminar la tarea [${tarea?.title}]?`)) return;

        setError(null);
        setLoading(true);
        try {
            //En el delete no se recomienda tipar
            await axios.delete(`${BASE_URL}/${id}`);
            setTasks(tasks.filter(t => t.id !== id));
            
            //Si se están mostrando los detalles de una tarea para que también se actualice ese estado
            if(task?.id === id) setTask(null);
        } catch (error) {
            console.error(error);
            setError(`Error eliminando la tarea: [${tarea?.title}]`);
        }finally{
            setLoading(false); 
        }
    }

  return (
    <>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Asignado a</th>
                    <th>Prioridad</th>
                    <th>Completada</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                {tasks.map(
                    t => 
                    <tr key={t.id}>
                        <td>{t.id}</td>
                        <td>{t.title}</td>
                        <td>{t.assignedTo}</td>
                        <td>{t.priority}</td>
                        <td>{t.completed ? 'Sí' : 'No'}</td>
                        <td>
                            <button onClick={() => verDetalle(t.id)}>Ver</button>
                            <button>Editar</button>
                            <button onClick={() => eliminarTarea(t.id)}>Borrar</button>
                        </td>
                    </tr>
                )
                }
            </tbody>
        </table>   
        <button onClick={mostrarListaDeTareas}>Cargar Tareas</button> 

        {
            tasks.map(
                t => 
                <ul key={t.id}>
                    <li>{t.id}</li>
                    <li>{t.title}</li>
                    <li>{t.assignedTo}</li>
                    <li>{t.priority}</li>
                    <li>{t.completed ? 'Sí' : 'No'}</li>
                </ul>
            )
        }
        <button>Ocultar</button>
    </>
  )
}
