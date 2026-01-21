import axios from "axios";
import { useState, type FormEvent } from "react";

type Task = {
    id:number, 
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

    //I. ESTADOS
    //1. task
    const [task, setTask] = useState<Task | null>(null);
    //2. tasks
    const [tasks, setTasks] = useState<Task []>([]);
    //3. loading
    const [loading, setLoading] = useState<boolean>(false);
    //4. error
    const [error, setError] = useState<string | null>(null);
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
    const [searchTerm, setSearchTerm] = useState<string>('');
    //8. validationErrors
    const [validationErrors, setValidationErrors] = useState<string []>([]);

    //II. METODOS
    const BASE_URL = "https://6968b37169178471522b4549.mockapi.io/api/v1/tasks"

    //1. listar tareas
    
    const listarTareas = async () => {
        setError(null);
        setLoading(true);

        try {
           const response = await axios.get<Task []>(BASE_URL);
           setTasks(response.data);
        } catch (error) {
            console.error(error);
            setError('Error listando tareas.')
        }finally{
            setLoading(false);

        }
    }

    //2. Ver detalle

    const verDetalleUnaTarea = async (id: number) => {
        setError(null);
        setLoading(true);

        try {
           const response = await axios.get<Task>(`${BASE_URL}/${id}`);
           setTask(response.data);
        } catch (error) {
            console.error(error);
            setError('Error mostrando el detalle de una tarea.')
        }finally{
            setLoading(false);

        }
    }

        //2.1 Ocultar detalle

        function ocultarDetalle() {
            setTask(null);
        }

    //3. Eliminar tarea
    const eliminarTarea = async (id: number) => {

        const tarea = tasks.find(t => t.id === id);
        if(!confirm(`Seguro de eliminar tarea: '${tarea?.id}'`))

        setError(null);
        setLoading(true);

        try {
            
           await axios.delete(`${BASE_URL}/${id}`);
            setTasks(tasks.filter(t => t.id !== id));

           //si está mostrando esa tarea en el momento
           if(task?.id === id) setTask(null);
        } catch (error) {
            console.error(error);
            setError(`Error eliminando tarea: '${tarea?.id}'`);
        }finally{
            setLoading(false);

        }
    }

    //4. Crear nueva tarea

    const crearNuevaTarea = async (taskData : TaskForm) => {

        setError(null);
        setLoading(true);

        try {
           const response = await axios.post<Task>(BASE_URL, taskData);
           setTasks([...tasks, response.data]);
           resetForm();
        } catch (error) {
            console.error(error);
            setError('Error crear nueva tarea.')
        }finally{
            setLoading(false);
        }
    }
    
    //5. Editar tarea

    const editarTarea = (tarea:Task) => {
        setEditingId(tarea.id);
        setFormData(
            {
            title: tarea.title, 
            assignedTo: tarea.assignedTo,
            priority: tarea.priority, 
            completed: tarea.completed,
            }
        )
        setValidationErrors([]);
    }

        //5.1 Guardar cambios
    const guardarCambios = async (id: number, taskData: TaskForm) => {

        setError(null);
        setLoading(true);

        try {
           const response = await axios.put<Task>(`${BASE_URL}/${id}`, taskData);
           setTasks(tasks.map(t => t.id === id ? response.data : t));
           
           //si es la que estaba mostrando 

           if(task?.id === id) setTask (response.data);
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error editando tarea.')
        }finally{
            setLoading(false);
        }
    } 

    //III. METODOS AUXILIAREAS
    //1. resetForm
    function resetForm() {
        setEditingId(null);
        setFormData(formDataEnBlanco);
    }
    //2. handleSubmit

    function handleSubmit(e:FormEvent) {
        e.preventDefault();

        if(!validacionBasica()) return;
        if(!validacionAvanzada()) return;
        
        if(editingId != null){
            guardarCambios(editingId, formData);
        } else {
            crearNuevaTarea(formData);
        }
    }

    //IV. VALIDACIONES 
    //1. Validacion basica
    const validacionBasica = () : boolean =>  {
        if(!formData.title.trim() || !formData.assignedTo.trim()){
            setError('El título y el asignado a deben tener datos');
            return false;
        } return true;
    }
    //2. Validacion Avanzada

    const validacionAvanzada = () : boolean => {
        const errors : string [] = [];

        if(formData.title.length < 5){
            errors.push('El título debe tener como mínimo 5 caracteres');
        }
        //incluir las otras validaciones

        setValidationErrors(errors);
        return errors.length === 0;
    }


    //V. METODOS AVANZADOS
    //1.Filtrar tarea

    const filteredTask = tasks.filter(
       t => {
        const search = searchTerm.toLowerCase();

        return (
            t.title.toLowerCase().includes(search) ||
            t.assignedTo.toLowerCase().includes(search) ||
            t.priority.toLowerCase().includes(search)
        )
       }

    )
    
    

    


  return (
    <>
        <h1>Gestión de Tareas del Equipo</h1>
        {
            validationErrors && validationErrors.map(
                (err, idx) => (
                    <div key={idx}>{err}</div>
                )
            )
        }

        {error && 
            <div>{error}</div>
        }

        <section className="formulario">
            <h2>FORMULARIO</h2>
            <form onSubmit={handleSubmit}>
                <label>Título</label>
                <input type="text" placeholder="Título de la tarea ..." value={formData.title} onChange={(e) => setFormData({...formData, title: e.target.value})}/>

                <label>Asignado a</label>
                <input type="text" placeholder="Asignado a ..." value={formData.assignedTo} onChange={(e) => setFormData({...formData, assignedTo: e.target.value})}/>

                <label>Prioridad</label>
                <select value={formData.priority} onChange={(e) => setFormData({...formData, priority: e.target.value})}>
                    <option value="">Selecciona prioridad</option>
                    <option value="Baja">Baja</option>
                    <option value="Media">Media</option>
                    <option value="Alta">Alta</option>
                    <option value="Urgente">Urgente</option>
                </select>

                <label>Completada</label>
                <input type="checkbox" checked={formData.completed} onChange={(e) => setFormData({...formData, completed: e.target.checked})}/>
                <button type="submit" disabled={loading}>{editingId ? 'Guardar cambios' : 'Crear nueva tarea'}</button>
            </form>

            {editingId &&
                <button type="button" onClick={resetForm}>Cancelar</button>
            }

        </section>

        { task &&
        <section>
            <h2>DETALLE</h2>
            <ul>
                <li> <span>ID: </span> {task.id}</li>
                <li> <span>Título: </span> {task.title}</li>
                <li> <span>Asignado a: </span> {task.assignedTo}</li>
                <li> <span>Prioridad: </span> {task.priority}</li>
                <li><span>Completada: </span>{task.completed ? 'Sí' : 'No'}</li>
            </ul>
            <button onClick={ocultarDetalle}>Ocultar</button>
        </section>
        }    

        <section>
            <button onClick={listarTareas} disabled={loading}>Cargar Tareas</button>

            <input type="text" placeholder="Buscar Tarea" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)}/>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Asignado</th>
                        <th>Prioridad</th>
                        <th>Completa</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        filteredTask.map(t => 
                            <tr key={t.id}>
                                <td>{t.id}</td>
                                <td>{t.title}</td>
                                <td>{t.assignedTo}</td>
                                <td>{t.priority}</td>
                                <td>{t.completed}</td>
                                <td>
                                    <button onClick={() => verDetalleUnaTarea(t.id)}>Ver</button>
                                    <button onClick={() => editarTarea(t)}>Editar</button>
                                    <button onClick={() => eliminarTarea(t.id)}>Eliminar</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </section>

    </>
  )
}
