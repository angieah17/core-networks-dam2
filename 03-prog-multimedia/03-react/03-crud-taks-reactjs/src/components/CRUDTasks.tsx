import { useState } from "react";

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

    //1. Mostrar lista de tareas en tabla

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
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <a>Ver</a>
                        <a>Editar</a>
                        <a>Borrar</a>
                    </td>
                </tr>
            </tbody>
        </table>    
    </>
  )
}
