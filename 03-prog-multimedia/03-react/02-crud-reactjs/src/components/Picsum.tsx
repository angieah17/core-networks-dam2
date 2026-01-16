import { useState } from "react"
import { apiPicsum } from "../services/api";

interface Foto{
    id: number,
    author: string,
    width: number, 
    height: number,
    download_url: string, 
}

export default function Picsum() {
    const [fotos, setFoto] = useState<Foto[]>([]);
    const [loading, setLoading] = useState<boolean>(false);

    const obtenerFotos = async () => {
        setLoading(true)
        try {
            const response = await apiPicsum.get('/v2/list');
            setFoto(response.data)
        } catch (error) {
            console.error("Error de tipo: ", error)
        } finally{
            setLoading(false)
        }
    }


  return (
    <>
        <h2>Mostrando LoremPicsum</h2>
        <button onClick={obtenerFotos} disabled={loading}>{loading ? 'Cargando ...' : 'Obtener Fotos'}</button>
        {
            fotos.map(
                (f:Foto) => <ul key={f.id}>
                    <li>Author: {f.author}</li>
                    <img src={f.download_url} width={f.width / 10}  height={f.height / 10}/>
                </ul>
            )
        }

    </>
  )
}
