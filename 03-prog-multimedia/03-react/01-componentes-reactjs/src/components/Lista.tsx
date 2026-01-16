import { useState, type JSX } from "react";

type Tarea = {
    id: number, 
    texto: string, 
    completada: boolean,
}

export default function Lista():JSX.Element {

    const [nombreTarea, setNombreTarea] = useState<string>('');
    const [tareas, setTareas] = useState<Tarea[]>([{id: 1, texto: 'Comprar pan', completada:false},
        {id: 2, texto: 'Estudiar React', completada:false},
        {id: 3, texto: 'Hacer ejercicio', completada:false},
     ]);
 
     //3. Funciones de agregar tarea, borrar tarea, editar tarea, completar tarea

     //3.1 COMPLETAR TAREA

     function completarTarea(id:number) {
        setTareas(
            tareas.map(
                (t: Tarea) => t.id === id ? {...t, completada: !t.completada} : t
            )
        )
     }

     //3.2 EDITAR TAREA

     function editarTarea(id: number, nuevoNombreTarea:string) {
        setTareas(
            tareas.map(
                (t: Tarea) => t.id === id ? {...t, texto: nuevoNombreTarea} : t
            )
        )
     }

     //3.3 BORRAR TAREA

     function borrarTarea(id:number) {
        setTareas(
            tareas.filter(
                (t: Tarea) => t.id != id
            )
        )
     }

     //3.4 AGREGAR TAREA

     function agregarTarea(nuevoNombreTarea:string) {
        let maximoId = 0;

        tareas.forEach(
            (t:Tarea) => maximoId = t.id > maximoId ? t.id : maximoId
        )

        const nuevaTarea: Tarea = {
            id: maximoId + 1,
            texto: nuevoNombreTarea, 
            completada: false,
        }

        setTareas([...tareas, nuevaTarea]);

     }

     
  return (
    <div className="container">
        <h1>Listas</h1>
        <h2>Primer ejemplo: checkar tareas</h2>
        <div className="caja">
            <ul>
                {
                    tareas.map(
                        (t:Tarea) => <li key={t.id}>
                            <input type="checkbox" checked={t.completada} onChange={() => completarTarea(t.id)} />{t.texto + ': ' + t.id}
                            <button onClick={() => borrarTarea(t.id)}>Borrar</button>
                            <button onClick={() => editarTarea(t.id, nombreTarea)}>Cambiar Tarea</button>
                        </li>
                    )
                }

            </ul>
        </div>
        <h2>Segundo ejemplo: agregar tareas</h2>
        <input type="text" placeholder="Nombre de la tarea ..." value={nombreTarea} onChange={(e) => setNombreTarea(e.target.value)} />
        <button onClick={() => agregarTarea(nombreTarea)}>Agregar tarea: {nombreTarea}</button>
    </div>
  )
}
