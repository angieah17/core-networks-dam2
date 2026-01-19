import axios from "axios";
import { useState, type FormEvent } from "react";

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

type SortBy = 'tittle' | 'assignedTo' | 'priority' | null;

type SortOrder = 'asc' | 'desc';


export default function CRUDTasks() {

    //I. ESTADOS
    //1. task
    const [task, setTask] = useState<Task | null>(null);
    //2. tasks
    const [tasks, setTasks] = useState<Task[]>([]);
    //3. error
    const [error, setError] = useState<string | null>('');
    //4. loading
    const [loading, setLoading] = useState<boolean>(false);
    //5. editingId
    const [editingId, setEditingId] = useState<number | null>(null);
    //6. formData
    const formDataEnBlanco : TaskForm = {
        title: '',
        assignedTo: '', 
        priority: '',
        completed: false,
    }
    const [formData, setFormData] = useState<TaskForm>(formDataEnBlanco);
    //7. searchTerm
    const [searchTerm, setSearchTerm] = useState<string>('')
    //8. sortBy
    const [sortBy, setSortBy] = useState<SortBy>(null);
    //9. sortOrder
    const [sortOrder, setSortOrder] = useState<SortOrder>('asc');
    //10. validationErrors
    const [validationErrors, setValidationErrors] = useState<string[]>([]);

    //II. METODOS
    const BASE_URL = "https://6968b37169178471522b4549.mockapi.io/api/v1/tasks"
    //1. Listar tareas
    const listarTareas = async () => {
        setError(null);
        setLoading(true);

        try {
            const response = await axios.get<Task[]>(BASE_URL);
            setTasks(response.data);
        } catch (error) {
            console.error(error);
            setError('Error listando todas las tareas')
        }finally{
            setLoading(false);
        }

    }

    //2. Ver detalle de una tarea
    const verDetalle = async (id:number) => {
        setError(null);
        setLoading(true);

        try {
            const response = await axios.get<Task>(`${BASE_URL}/${id}`);
            setTask(response.data);
        } catch (error) {
            console.error(error);
            setError('Error mostrando detalle de una tarea.')
        }finally{
            setLoading(false);
        }

    }

        //2.1 Ocultar detalle de una tarea
        function ocultarDetalle(id:number) {
            setTask(null);
        }

    //3. Eliminar una tarea
        const eliminarTarea = async (id:number) => {
            const tarea = tasks.find(t => t.id === id);
            if(!confirm(`¿Eliminar la tarea '${tarea?.title}'?`)) return;
            setError(null);
            setLoading(true);

            try {
                await axios.delete<Task>(`${BASE_URL}/${id}`);
                setTasks(tasks.filter(t => t.id !== id));
                //si estaba mostrando esa tarea
                
                if(task?.id === id) setTask(null);

            } catch (error) {
                console.error(error);
                setError(`Error eliminando la tarea: '${tarea?.title}'`);
            }finally{
                setLoading(false);
            }
        }

    //4. Crear nueva tarea

    const crearNuevaTarea = async (taskData: TaskForm) => {
        setError(null);
        setLoading(true);

        try {
            const response = await axios.post<Task>(BASE_URL, taskData);
            setTasks([...tasks, response.data]);
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error creando nueva tarea');
        }finally{
            setLoading(false);
        }
    }

    //5. Editar tarea
    const editarTarea = async (tarea: Task) => {

        setEditingId(tarea.id); //para que se ponga el form en modo editar

        setFormData( //info que se va a guardar
            {title: tarea.title,
            assignedTo: tarea.assignedTo, 
            priority: tarea.priority,
            completed: tarea.completed,}
        )
        setValidationErrors([]); //si había antes algún error lo dejamos vacío
    }
        //5.1 Guardar cambios

    const guardarCambios = async (id: number, taskData: TaskForm) => {
        setError(null);
        setLoading(true);

        try {
            const response = await axios.put<Task>(`${BASE_URL}/${id}`, taskData);
            setTasks(tasks.map(t => t.id === id ? response.data : t));
            
            //Si se estaba mostrando esa tarea
            if(task?.id === id) setTask(response.data);
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error editando tarea');
        }finally{
            setLoading(false);
        }
    }

    //III. METODOS AUXILIARES
    //1. resetForm
    function resetForm() {
        setEditingId(null); 
        setFormData(formDataEnBlanco); 
    }
    //2. handleSubmit

    function handleSubmit(e: FormEvent) {
        e.preventDefault(); //evitar la recaga automática

        //AGREGAR AQUI LAS VALIDACIONES

        if(editingId !== null){
            guardarCambios(editingId, formData);
        }else {
            crearNuevaTarea(formData);
        }
    }

    //IV. VALIDACIONES 
    //1. Validacion basica
    //2. Validaciones avanzadas

    //V. METODOS AVANZADOS
    //1. Filtrar tiempo real
    //2. Ordenar tareas



  return (
    <div>

    </div>
  )
}
