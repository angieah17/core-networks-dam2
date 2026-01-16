
import axios from 'axios';
import { useState } from 'react';

interface User {
  id: number,
  name: string,
  email: string,
  phone: string,
}


export default function ComparacionFetchVsAxios() {
   const [userFetch, setUserFetch] = useState<User | null>(null);
    const obtenerConFetch = async () => {
      //Vamos a hacer una petición asíncrona que va a contener:
      try {
        const response = await fetch ('https://jsonplaceholder.typicode.com/users/1')
  
        //Fetch no lanza error 404 y 500, se deben generar manualmente:
  
        if(!response.ok) {
          throw new Error(`Http error: ${response.status}`)
        }
  
        const data:User = await response.json()
        setUserFetch(data)
  
      } catch (error) {
        console.error('Error con fetch: ', error)
      }
  
    }

    const [userAxios, setUserAxios] = useState<User | null>(null);
    const obtenerConAxios = async () => {

        try {
            const response = await axios.get<User>('https://jsonplaceholder.typicode.com/users/2')
            setUserAxios(response.data) //no es necesario parsear directamente da los datos en el formato User
        } catch (error) {
            console.error('Error con axios: ', error)
        }
    }


    /*El ? que ves en expresiones como: userFetch?.id es optional chaining en JavaScript/TypeScript.
    */
  
    return (
      <>
        <h2>Comparación Fetch vs Axios</h2>
        <button onClick={obtenerConFetch}>Llamada a Fetch</button>
        <ul>
          <li> Id: {userFetch?.id}</li>
          <li> Name: {userFetch?.name}</li>
          <li> Email: {userFetch?.email}</li>
          <li> Phone: {userFetch?.phone}</li>
        </ul>

        <button onClick={obtenerConAxios}>Llamada a Axios</button>
        <ul>
          <li> Id: {userAxios?.id}</li>
          <li> Name: {userAxios?.name}</li>
          <li> Email: {userAxios?.email}</li>
          <li> Phone: {userAxios?.phone}</li>
        </ul>
  
      </>
    )
}
