import axios from "axios";
import { useState, type FormEvent } from "react";
import './CRUDTasks.css'

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

type SortBy = 'title' | 'assignedTo' | 'priority' | null;
type SortOrder = 'asc' | 'desc';


export default function CRUDTasks() {

    //I.DEFINICION DE LOS ESTADOS

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

    //6. searchTeam (para hacer la busqueda)
     const [searchTerm, setSearchTerm] = useState<string>('');
    //7. sortBy (para ordenar por)
    const [sortBy, setSortBy] = useState<SortBy>(null);
    //8. sortOrder (orden asc o desc)
    const [sortOrder, setSortOrder] = useState<SortOrder>('asc');
    //9.validationErrors
    const [validationErrors, setValidationErrors] = useState<string[]>([]);
    //10. formData

    const formDataEnBlanco : TaskForm = {
        title: '',
        assignedTo: '',
        priority: '',
        completed: false,
    }
    
    const [formData, setFormData] = useState<TaskForm>(formDataEnBlanco);

    //II. VALIDACIONES

    //1. Validación básica
    const validateBasic = (): boolean => {
        if (!formData.title.trim() || !formData.assignedTo.trim()) {
            setError('El título y asignado son obligatorios');
            return false;
        }
        return true;
    };

    //2. Validaciones avanzadas
    const validateForm = (): boolean => {
    const errors: string[] = [];

    if (formData.title.length < 5) {
      errors.push('El título debe tener mínimo 5 caracteres');
    }
    if (formData.title.length > 100) {
      errors.push('El título debe tener máximo 100 caracteres');
    }
    if (formData.assignedTo.length < 3) {
      errors.push('El asignado debe tener mínimo 3 caracteres');
    }
    if (!/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/.test(formData.assignedTo)) { //Expresión REGEX
      errors.push('El asignado solo puede contener letras y espacios');
    }
    if (!['Baja', 'Media', 'Alta', 'Urgente'].includes(formData.priority)) {
      errors.push('Debe seleccionar una prioridad válida');
    }

    setValidationErrors(errors);
    return errors.length === 0; //Devuele true si no hay elementos en el array
  };


    //III. METODOS

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
    //2.1 Ocultar detalle de una tarea
    const ocultarDetalle = () => {
        setTask(null);
    }

    //3. Elimiar tarea (DELETE)
    const eliminarTarea = async (id:number) => {
        
        const tarea = tasks.find(t => t.id === id);

        if(!confirm(`¿Eliminar la tarea '${tarea?.title}'?`)) return;

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

    //4. Crear nueva tarea (POST)
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

    //5. Editar tarea (PUT)
    const editarTarea = (tarea: Task) => {
        setEditingId(tarea.id);
        setFormData(
            {
                title: tarea.title,
                assignedTo: tarea.assignedTo,
                priority: tarea.priority,
                completed: tarea.completed,
            }
        );
        setValidationErrors([]);
    }

    const guardarCambios =  async (id:number, taskData:TaskForm) => {
        setError(null);
        setLoading(true);
        try {
            const response = await axios.put<Task>(`${BASE_URL}/${id}`, taskData);
            setTasks(tasks.map(t => t.id === id ? response.data : t));
            if(task?.id === id) setTask(response.data);
            resetForm();
        } catch (error) {
            console.error(error);
            setError('Error editando tarea');
        }finally{
            setLoading(false); 
        }
    }

    //IV. METODOS AUXILIARES

    //1. resetForm
    const resetForm = () => {
        setEditingId(null);
        setFormData(formDataEnBlanco);
    }

    //2. Guardar los cambios Submit
    const handleSubmit = (e:FormEvent) => {
        e.preventDefault();
        
        if (!validateBasic()) return;
        if (!validateForm()) return;

        if (editingId !== null) {
            guardarCambios(editingId, formData);
        } else {
            crearNuevaTarea(formData);
        }
    }
    
    //V. METODOS AVANZADOS
    //1. Búsqueda/Filtrado en tiempo real
    const filteredTasks = tasks.filter(t => {
    const search = searchTerm.toLowerCase();
    return (
        t.title.toLowerCase().includes(search) ||
        t.assignedTo.toLowerCase().includes(search) ||
        t.priority.toLowerCase().includes(search)
        );
    });    

    //2. Ordenar tareas (POR REVISAR ) 
    const priorityOrder = { 'Baja': 1, 'Media': 2, 'Alta': 3, 'Urgente': 4 };
  
    const sortedTasks = [...filteredTasks].sort((a, b) => {
        if (!sortBy) return 0;

        if (sortBy === 'priority') {
        const aVal = priorityOrder[a.priority as keyof typeof priorityOrder] || 0;
        const bVal = priorityOrder[b.priority as keyof typeof priorityOrder] || 0;
        return sortOrder === 'asc' ? aVal - bVal : bVal - aVal;
    }

    const aVal = a[sortBy].toLowerCase();
    const bVal = b[sortBy].toLowerCase();
    
    if (sortOrder === 'asc') {
        return aVal < bVal ? -1 : aVal > bVal ? 1 : 0;
        } else {
        return aVal > bVal ? -1 : aVal < bVal ? 1 : 0;
        }
     });

    const handleSort = (field: SortBy) => {
        if (sortBy === field) {
        setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
        } else {
        setSortBy(field);
        setSortOrder('asc');
        }
    };

    const getSortIndicator = (field: SortBy) => {
        if (sortBy !== field) return '';
        return sortOrder === 'asc' ? ' ↑' : ' ↓';
    };


  return (
    <div className="crud-container">  

        <h1>Gestión de Tareas del Equipo</h1>
        
        <form onSubmit={handleSubmit}>
            <h2>{editingId ? 'Editar Tarea' : 'Crear Nueva Tarea'}</h2>

            {validationErrors.length > 0 && (
              <div className="validation-errors">
                {validationErrors.map((err, idx) => (
                  <div key={idx} className="validation-error">{err}</div>
                ))}
              </div>
            )}

            <label>Título: </label>
            <input type="text" placeholder="Título de la tarea" value={formData.title} onChange={(e) => setFormData({...formData, title: e.target.value})}/>

            <label>Asignado a: </label> 
            <input type="text" placeholder="Nombre de la persona a asignarsela" value={formData.assignedTo} onChange={(e) => setFormData({...formData, assignedTo: e.target.value})}/>

            <label>Prioridad: </label> 
            <select value={formData.priority} onChange={(e) => setFormData({...formData, priority: e.target.value})}>
                <option value="">Selecciona prioridad</option>
                <option value="Baja">Baja</option>
                <option value="Media">Media</option>
                <option value="Alta">Alta</option>
                <option value="Urgente">Urgente</option>
            </select>

            
            <label >Completada</label>
            <input type="checkbox" checked={formData.completed} onChange={(e) => setFormData({...formData, completed: e.target.checked})}/>
            
            <button type="submit" disabled={loading} className="btn-primary">{editingId ? 'Guardar cambios': 'Crear Tarea'}</button>
            {editingId && (
                <button type="button" onClick={resetForm} className="btn-secondary">
                    Cancelar
                </button>
            )} 
        </form>
        
        {task &&
                (
                <div className="detail-container">
                <h2>Detalle de la Tarea</h2>
                <ul>
                    <li><strong>ID:</strong> {task.id}</li>
                    <li><strong>Título:</strong> {task.title}</li>
                    <li><strong>Asignado a:</strong> {task.assignedTo}</li>
                    <li><strong>Prioridad:</strong> {task.priority}</li>
                    <li><strong>Completada:</strong> {task.completed ? 'Sí' : 'No'}</li>
                </ul>
                <button onClick={ocultarDetalle} className="btn-secondary">Ocultar</button>
                </div>
          )}

        <div className="controls">
            
            <input type="text" placeholder="Buscar por título, asignado o prioridad..." value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)}/>
            
            <div className="sort-buttons">
                <button onClick={() => handleSort('title')} className="btn-sort">
                    Ordenar por Título{getSortIndicator('title')}
                </button>
                <button onClick={() => handleSort('assignedTo')} className="btn-sort">
                    Ordenar por Asignado{getSortIndicator('assignedTo')}
                </button>
                <button onClick={() => handleSort('priority')} className="btn-sort">
                    Ordenar por Prioridad{getSortIndicator('priority')}
                </button>
            </div>
            {error && <div className="error-message">{error}</div>}
            
            <button onClick={mostrarListaDeTareas} disabled={loading} className="btn-primary">{loading ? 'Cargando' : 'Cargar Tareas'}</button> 

        </div>

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
                    {sortedTasks.map(
                        t => 
                        <tr key={t.id}>
                            <td>{t.id}</td>
                            <td>{t.title}</td>
                            <td>{t.assignedTo}</td>
                            <td>{t.priority}</td>
                            <td>{t.completed ? 'Sí' : 'No'}</td>
                            <td>
                                <button onClick={() => verDetalle(t.id)}>Ver</button>
                                <button onClick={() => editarTarea(t)} >Editar</button>
                                <button onClick={() => eliminarTarea(t.id)}>Eliminar</button>
                            </td>
                        </tr>
                    )
                    }
                </tbody>
            </table> 
            
            {sortedTasks.length === 0 && tasks.length > 0 && (
            <p className="no-results">No se encontraron tareas con ese criterio de búsqueda</p>
          )}  
            

            



    </div>
  )
}
