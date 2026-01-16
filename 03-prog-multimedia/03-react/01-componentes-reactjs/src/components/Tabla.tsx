import { useState, type JSX } from "react";

type Nota = {
    id: number, 
    asignatura: string,
    nota: number,
}

export default function Tabla():JSX.Element {

    //use state
    const [nota, setNota] = useState<string>('');
    const [asignatura, setAsignatura] = useState<string>('');
    const [notas, setNotas] = useState<Nota[]>([]);

    //funciones, nota media agregarnota, eliminar nota

    //3.1 NOTA MEDIA

    let sumaNotas = 0;

    notas.forEach(
        (n:Nota) => sumaNotas = sumaNotas + n.nota
    )

    const media: number = notas.length > 0 ? sumaNotas / notas.length : 0


    //3.2 AGREGAR NOTA

    const notaNum: number = Number(nota)
    function agregarNota() {
        //Validaciones
        if(asignatura.trim() === '' ||  nota === '') return;

        if(isNaN(notaNum) || notaNum < 0 || notaNum > 10) return;

        //Lógica

        let maximoId = 0;

        notas.forEach(
            (n:Nota) => maximoId = n.id > maximoId ? n.id : maximoId
        )

        const nuevaNota: Nota = {
            id: maximoId + 1,
            asignatura: asignatura,
            nota: notaNum,
        }

        setNotas([...notas, nuevaNota]);

        //Limpiar los input
        setNota('');
        setAsignatura('');
    }

    // 3.3 ELIMINAR NOTA

    function eliminarNota(id:number) {
        setNotas(
            notas.filter(
                (n: Nota) => n.id != id
            )
        )
    }


  return (
    <div className="container">
        <h1>Tabla de notas con media</h1>
        <input type="text" placeholder="Nombre asignatura" value={asignatura} onChange={(e) => setAsignatura(e.target.value)}/>
        <input type="number" placeholder="Nota (0 - 10)" min={0} max={10} step={.1} value={nota} onChange={(e) => setNota(e.target.value)}/>
        <button onClick={agregarNota}>Agregar</button>

        <table>
            <thead>
                <tr>
                    <th>Asignatura</th>
                    <th>Nota</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>
                {notas.map(
                    (n: Nota) => <tr key={n.id}>
                        <td>{n.asignatura}</td>
                        <td>{n.nota}</td>
                        <td>
                            <button onClick={() => eliminarNota(n.id)}>Eliminar</button>
                        </td>
                    </tr>
                )

                }
            </tbody>

        </table>
        <h2>Nota media: {media}</h2>
        { notas.length === 0 && <p>No hay notas por mostrar</p>}
    </div>
  )
}
