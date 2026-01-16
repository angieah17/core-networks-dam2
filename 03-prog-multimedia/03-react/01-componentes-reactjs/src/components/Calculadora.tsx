import { useState, type JSX } from 'react'


export default function Calculadora():JSX.Element {

    //useState

    const [importe, setImporte] = useState<string>('');
    const [propina, setPropina] = useState<number>(10);
    
    //cálculos
    const importeNum: number = Number(importe);
    const valorPropina: number = importeNum * propina / 100;
    const total: number = importeNum + valorPropina;


  return (
    <div className='container'>
        <h1>Calculadora de propinas</h1>
        <h2>Importe de la cuenta</h2>
        <input type="text" placeholder='0.00' min={0} step={.01} value={importe} onChange={(e) => setImporte(e.target.value)} />
        
        <h2>Porcentaje de la propina</h2>
        <div className='botones'>
            <button onClick={() => setPropina(10)}>10%</button>
            <button onClick={() => setPropina(15)}>15%</button>
            <button onClick={() => setPropina(20)}>20%</button>
        </div>

        <h2>Desglose</h2>
        <div className='caja'>
            <p>Propina({propina}%):€{valorPropina}</p>
            <p>Total a pagar: €{total}</p>
        </div>
    </div>
  )
}
