import { useState, type JSX } from "react";

//1. Tipos

type Idioma = 'es' | 'en' | 'fr' | 'de'

type Traducciones = {
    [key in Idioma]:{
        titulo: string, 
        selecciona: string, 
        bienvenida: string, 
        descripcion: string,
    }
}

//2. Instancia de traducciones

const traducciones: Traducciones = {
    es:{
        titulo: 'Título en español',
        selecciona: 'Selecciona en español', 
        bienvenida: 'Bienvenida en español',
        descripcion: 'Descripción en español',
    },
    en:{
        titulo: 'Título en inglés',
        selecciona: 'Selecciona en inglés', 
        bienvenida: 'Bienvenida en inglés',
        descripcion: 'Descripción en inglés',
    },
    fr:{
        titulo: 'Título en francés',
        selecciona: 'Selecciona en francés', 
        bienvenida: 'Bienvenida en francés',
        descripcion: 'Descripción en francés',
    },
    de:{
        titulo: 'Título en aleman',
        selecciona: 'Selecciona en aleman', 
        bienvenida: 'Bienvenida en aleman',
        descripcion: 'Descripción en aleman',
    },
}


export default function Selector():JSX.Element {

    //useState

    const [idioma, setIdioma] = useState<Idioma>('es');


  return (
    <div className="container">
        <h1>{traducciones[idioma].titulo}</h1>
        <h2>{traducciones[idioma].selecciona}</h2>

        <div className="botones">
            <button onClick={() => setIdioma('es')}>Español</button>
            <button onClick={() => setIdioma('en')}>Inglés</button>
            <button onClick={() => setIdioma('fr')}>Francés</button>
            <button onClick={() => setIdioma('de')}>Aleman</button>
        </div>

        <h2>{traducciones[idioma].bienvenida}</h2>
        <p>{traducciones[idioma].descripcion}</p>
    
    </div>
  )
}
