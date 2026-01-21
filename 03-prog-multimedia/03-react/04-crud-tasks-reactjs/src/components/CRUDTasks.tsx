import axios from "axios";
import { useState, type FormEvent } from "react"
import './CRUDTasks.css'

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
    //I. ESTADOS
    //1. task
    const [task, setTask] = useState<Task | null>(null);
    //2.tasks
    const [tasks, setTasks] = useState<Task[]>([]);
    //3. error
    const [error, setError] = useState<string | null>(null);
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
    
    //7. searchTerm //para el filtrado
    const [searchTerm, setSearchTerm] = useState<string>('');
    //8. validationErrors
    const [validationErrors, setValidationErrors] = useState<string []>([]);

    //II. MÉTODOS
    const BASE_URL = 'https://6970f81b78fec16a63ffa6af.mockapi.io/api/v1/tasks'
    //1. listar
    const listarTareas = async () =>{
        setError(null);
        setLoading(true);
        try {
            const response = await axios.get<Task[]>(BASE_URL);
            setTasks(response.data);
        } catch (error) {
            console.error(error);
            setError('Error listando tareas.')
        } finally {
            setLoading(false);
        }
    }
    //2. ver 1
        const verDetalleDeUnaTarea = async (id: number) =>{
        setError(null);
        setLoading(true);
        try {
            const response = await axios.get<Task>(`${BASE_URL}/${id}`);
            setTask(response.data);
        } catch (error) {
            console.error(error);
            setError('Error mostrando detalle de una tarea.')
        } finally {
            setLoading(false);
        }
    }
        //2.1 ocultar
        function ocultar() {
            setTask(null);
        }
    //3. eliminar

    const eliminarTarea = async (id: number) =>{

        const tarea = tasks.find(t => t.id === id);

        if(!confirm(`¿Eliminar la tarea '${tarea?.title}'?` )) return;

        setError(null);
        setLoading(true);
        try {
            await axios.delete(`${BASE_URL}/${id}`);
            
            setTasks(tasks.filter( t => t.id !== id))

            //si esta mostrando 1
            if(task?.id === id) setTask(null);
        } catch (error) {
            console.error(error);
            setError(`Error eliminado la tarea '${tarea?.title}'` );
        } finally {
            setLoading(false);
        }
    }

    //4. crear

    const crearTarea = async (taskData : TaskForm) =>{
        setError(null);
        setLoading(true);
        try {
            const response = await axios.post<Task>(BASE_URL, taskData);
            setTasks([...tasks, response.data]);
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error creando una tarea.')
        } finally {
            setLoading(false);
        }
    }
    //5. editar
        const editarTarea = (task : Task) => {
            setEditingId(task.id);
            setFormData(
                {
                    title: task.title, 
                    assignedTo: task.assignedTo,
                    priority: task.priority,
                    completed: task.completed,
                }
            )
            setValidationErrors([]);
        }


        //5.1 guardar cambios

        const guardarCambios = async (id: number, taskData : TaskForm) =>{
        setError(null);
        setLoading(true);
        try {
            const response = await axios.put<Task>(`${BASE_URL}/${id}`, taskData);
            setTasks(tasks.map(
                t => t.id == id ? response.data : t
            ));

            //si es la que muestra
            if(task?.id === id) setTask(response.data);
            
        } catch (error) {
            console.error(error);
            setError('Error editando una tarea.')
        } finally {
            setLoading(false);
        }
    }

    //III. MÉTODOS AUXILIARES

    //1. resetForm
    function resetForm() {
        setEditingId(null);
        setFormData(formDataEnBlanco);
    }
    //2.handleSubmit

    const handleSubmit = (e : FormEvent) => {
        e.preventDefault();

        if(!validacionesBasicas()) return;
        if(!validacionesAvanzadas()) return; 

        if(editingId !== null){
            guardarCambios(editingId, formData);
        }else {
            crearTarea(formData);
        }
    }

    //IV. VALIDACIONES
    //1. Val basicas
    function validacionesBasicas() : boolean {
        
        if(!formData.title.trim() || !formData.assignedTo.trim()){
            setError('El título y el asignado a no pueden estar vacíos');
            return false;
        }
        return true;
    }
    //2. Val avanzadas

    function validacionesAvanzadas() : boolean {
        const errors : string [] = [];

        if(formData.title.length < 5){
            errors.push('El título debe contener por lo menos 5 caracteres');
        }

        if(formData.title.length > 100){
            errors.push('El título debe contener máximo 100 caracteres');
        }

        if(formData.assignedTo.length < 3){
            errors.push('El asignado debe contener por lo menos 3 caracteres');
        }

        if(!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(formData.assignedTo)){
            errors.push('El asignado debe ser solo letras y espacios');
        }

        if(!['Baja', 'Media', 'Alta', 'Urgnete'].includes(formData.priority)){
            errors.push('La prioridad debe estar dentro de los valores permitidos');
        }
        setValidationErrors(errors);
        return errors.length === 0;
    }

    //V. MÉTODOS AVANZADOS
    //1. Filtrado

    const filteredTasks = tasks.filter(
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
                (err, idx) => <div key={idx} className="error">{err}</div>
            )
        }

        {error && <div className="error">{error}</div> }

        <section>
            <h2>FORMULARIO</h2>
            <form onSubmit={handleSubmit}>
                <label>Título</label>
                <input type="text" placeholder="Título de la tarea ..." value={formData.title} onChange={(e) => setFormData({...formData, title: e.target.value})} />

                <label>Asignado a</label>
                <input type="text" placeholder="Asignado a ..." value={formData.assignedTo} onChange={(e) => setFormData({...formData, assignedTo: e.target.value})} />

                <label>Prioridad</label>
                <select value={formData.priority} onChange={(e) => setFormData({...formData, priority: e.target.value})}>
                    <option value="">Selecciona prioridad</option>
                    <option value="Baja">Baja</option>
                    <option value="Media">Media</option>
                    <option value="Alta">Alta</option>
                    <option value="Urgente">Urgente</option>
                </select>
                
                <label>Completada</label>
                <input type="checkbox" checked={formData.completed} onChange={(e) => setFormData({...formData, completed: e.target.checked})} />

                <button type="submit" disabled={loading}>{editingId ? 'Guardar cambios' : 'Crear Tarea'}</button>
                {editingId && <button type="button" disabled={loading}>Cancelar</button> }
            </form>
        </section>
        
        {task && <section>
            <h2>DETALLE</h2>
            <ul>
                <li> <span>ID: </span> {task.id}</li>
                <li> <span>Título: </span> {task.title}</li>
                <li> <span>Asignado a: </span> {task.assignedTo}</li>
                <li> <span>Prioridad: </span> {task.priority}</li>
                <li> <span>Completada: </span> {task.completed ? 'Sí' : 'No'}</li>
            </ul>
            <button onClick={ocultar} disabled={loading}>Ocultar</button>
        </section>}

        <section>
            <button onClick={listarTareas} disabled={loading}>Cargar Tareas</button>
            
            <label>Buscar</label>
            <input type="text" placeholder="Buscar por título, asignado o prioridad" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />

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
                    
                    filteredTasks.map( t => 
                        <tr>
                            <td>{t.id}</td>
                            <td>{t.title}</td>
                            <td>{t.assignedTo}</td>
                            <td>{t.priority}</td>
                            <td>{t.completed}</td>
                            <td>
                                <button onClick={() => verDetalleDeUnaTarea(t.id)}>Ver</button>
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
