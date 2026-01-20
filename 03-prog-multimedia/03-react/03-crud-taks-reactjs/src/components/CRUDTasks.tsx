import axios from "axios";
import { useState, type FormEvent } from "react";
import './CRUDTasks2.css'

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
        function ocultarDetalle() {
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

        //antes de subir valido si pasan los datos ingresados
        if(!validacionesBasicas()) return;
        if(!validacionesAvanzadas()) return;

        if(editingId !== null){
            guardarCambios(editingId, formData);
        }else {
            crearNuevaTarea(formData);
        }
    }

    //IV. VALIDACIONES 
    //1. Validacion basica

    function validacionesBasicas() : boolean {
        if(!formData.title.trim() || !formData.assignedTo.trim()){
            setError('El título y el asignado son obligatorios.')
            return false;
        }
            return true;
    }

    //2. Validaciones avanzadas
    function validacionesAvanzadas(): boolean {

        /* errors.push('')
        REGEX.test(formData.assignedTo)
        ['Baja', 'Media'].includes(formData.priority) */

        //2.1 Crear el array de errores
        const errors : string [] = [];

        //2.2 Validacion del titulo que ingresa en el formulario

        if(formData.title.length < 5) {
            errors.push('El título deben tener mínimo 5 caracteres.'); //se agrega a la lista de errores
        }

        //2.3 Validación titulo

        if(formData.title.length > 100){
            errors.push('El título no puede exceder de 100 caracteres');
        }

        //2.4 Validacion de asignado a:
        if(formData.assignedTo.length < 3){
            errors.push('El asignado a debe tener por lo menos 3 caracteres');
        }

        //2.5 Expresion regular
        //Si este patrón no existe testealo porque da error
        if(!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(formData.assignedTo)){
            errors.push('El asignado a solo puede contener letras y espacios.')
        }

        //2.6 Prioridad 

        if(!['Baja', 'Media', 'Alta', 'Urgente'].includes(formData.priority)){
            errors.push('La prioridad debe ser uno de los valores permitidos.')
        }

        //3. Se incluye el array de errores en el estado de validacion de errores

        setValidationErrors(errors);

        return errors.length === 0; //Devuelve true si no hay errores
    }

    //V. METODOS AVANZADOS
    //1. Filtrar tiempo real

    //Sacar una lista filtrada de las tasks

    const filteredTasks : Task [] = tasks.filter(
        t => {
            const search = searchTerm.toLocaleLowerCase(); //guardo la variable en loweCase
            return (
                t.title.toLocaleLowerCase().includes(search) ||
                t.assignedTo.toLowerCase().includes(search) ||
                t.priority.toLowerCase().includes(search)
            );
        }
    ) 

    //2. Ordenar tareas


  return (
    <>
        <h1>Gestión de Tareas del Equipo</h1>
        
        <section className="formulario">
            
            <h2>FORMULARIO</h2>

            {validationErrors.length > 0 && 
                <ul>
                    {validationErrors.map(
                        (err, idx) => <li key={idx} className="validation-error">{err}</li>
                    )}
                </ul>
            }
            {error && <div className="validation-error">{error}</div>}

            <form onSubmit={handleSubmit}>
                <label>Título: </label>
                <input type="text" placeholder="Título" value={formData.title} onChange={(e) => setFormData({...formData, title: e.target.value})}/>

                <label>Asignado a :</label>
                <input type="text" placeholder="Aginado a" value={formData.assignedTo} onChange={(e) => setFormData({...formData, assignedTo: e.target.value})} />

                <label >Prioridad: </label>
                <select value={formData.priority} onChange={(e) => setFormData({...formData, priority: e.target.value})}>
                    <option value="">Selecciona prioridad</option>
                    <option value="Baja">Baja</option>
                    <option value="Media">Media</option>
                    <option value="Alta">Alta</option>
                    <option value="Urgente">Urgente</option>
                </select>

                <label>Completada: </label>
                <input type="checkbox" checked={formData.completed} onChange={(e) => setFormData({...formData, completed: e.target.checked})} />

                <button type="submit" disabled={loading}>{editingId ? 'Guardar cambios' : 'Crear Tarea'}</button>
                
                {
                    editingId && <button type="button" onClick={resetForm}>Cancelar</button>
                }
                
            </form>
        </section>

        
        {task && 
            <section>
            <h2>DETALLE</h2>

            <ul>
                <li><span>ID: </span>{task.id}</li>
                <li><span>Título: </span>{task.title}</li>
                <li><span>Asignado a: </span>{task.assignedTo}</li>
                <li><span>Prioridad: </span>{task.priority}</li>
                <li><span>Completada: </span>{task.completed ? 'Sí' : 'No'}</li>
            </ul>

            <button onClick={ocultarDetalle}>Ocultar</button>
            </section>
        }

        <section className="controles">

            <label>Buscar: </label>
            <input type="text" placeholder="Buscar por título o asignado o prioridad" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)}/>

            <div>REVISAR SI SE PUEDE AGREGAR LO DE ORDENAR</div>

            <button onClick={listarTareas} disabled={loading}>{loading ? 'Cargando' : 'Cargar Tareas'}</button>
        </section>
    
        <section className="tabla">
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
                    {
                        tasks.map(
                            t => <tr key={t.id}>
                                <td>{t.id}</td>
                                <td>{t.title}</td>
                                <td>{t.assignedTo}</td>
                                <td>{t.priority}</td>
                                <td>{t.completed ? 'Sí' : 'No'}</td>
                                <td>
                                    <button onClick={() => verDetalle(t.id)}>Ver</button>
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
