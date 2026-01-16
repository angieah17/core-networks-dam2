import { useState, type JSX } from 'react'
import './ContadorEstilos.css'


type HistorialItems = {
    id: number, 
    valor: number, 
    fecha: Date,
}


export default function Contador():JSX.Element {

    const [valor, setValor] = useState<number>(0);
    const [historial, setHistorial] = useState<HistorialItems[]>([{
        id: 1, valor: 0, fecha: new Date()
    }])

    //3. funciones modificar, agregar al historial, resetear

    function modificar(cantidad:number) {
        const nuevoValor = cantidad;
        setValor(nuevoValor);
        agregarAlHistorial(nuevoValor);
    }

    function agregarAlHistorial(nuevoValor:number) {
        let maximoId = 0;

        historial.forEach(
            (h: HistorialItems) => maximoId = h.id > maximoId ? h.id : maximoId
        )

        const nuevoItem: HistorialItems = {
            id: maximoId + 1,
            valor: nuevoValor, 
            fecha: new Date(),
        }

        setHistorial([...historial, nuevoItem]);

    }

    function resetear() {
        setValor(0);
        setHistorial([{
        id: 1, valor: 0, fecha: new Date()
    }]);
    }

  return (
    <div className='container'>
        <h1>Contador con historia</h1>
        <h2>Contador: {valor}</h2>
        <div className='botones'>
            <button onClick={() => modificar(valor - 1 > 0 ? valor - 1 : 0)}>-</button>
            <button onClick={resetear}>0</button>
            <button onClick={() => modificar(valor + 1)}>+</button>
        </div>

        <h2>Histórico del contador</h2>
        <ul>
            {historial.map(
                (h: HistorialItems) => <li key={h.id}>
                    {h.id} - {h.valor} - {h.fecha.toISOString()}
                </li>
            )

            }
        </ul>
        
        <h3>Cantidad de elementos del histórico: {historial.length}</h3>
    </div>
  )
}
