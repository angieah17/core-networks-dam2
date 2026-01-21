const raiz = document.getElementById('root');

async function cargarRios() {
    try {//Si sale error puede que se necesite revisar ipconfig y actualizar la ip
        const respuesta = await fetch('http://192.168.0.203:8087/api/index.php') //se agrega la ip, con ip config en la carpeta y se agrega el http y el puerto
        const datos = await respuesta.json(); //con esto convertimos la respuesta en json, para acceder a ellos


        //Mostramos datos en root
        raiz.innerText = '';
        const lista = document.createElement('ul');
        lista.setAttribute('id', 'rios'); //primer parámetro el tipo, después el dato como tal
        
        //Se crea la sección
        const seccion = document.createElement('section');
        seccion.setAttribute('id', 'salida');
        seccion.innerHTML = 'Pulsa sobre un río para ampliar información'
        
        
        datos.forEach(rio => {
            const item = document.createElement('li');
            item.setAttribute('data-id', rio.id_rio);
            item.innerText = rio.nombre_rio;

            item.addEventListener('click', function () {
                const parrafo = document.createElement('p');
                parrafo.innerText = `El río ${rio.nombre_rio} transcurre por ${rio.paises_rio.length > 1 ? 'los países' : 'el país'} ${rio.paises_rio.join(' , ')}`;
                seccion.innerText = '';
                seccion.append(parrafo);
            })

            lista.append(item);
        });


        raiz.append(lista, seccion);

    } catch (error) {
        raiz.innerText = '';
        const parrafo1 = document.createElement('p');
        parrafo1.innerText = error;
        parrafo1.classList.add('error');
        const parrafo2 = document.createElement('p');
        parrafo2.classList.add('error');
        parrafo2.innerText ='Algo ha fallado. Prueba a recargar.';
        raiz.append(parrafo1, parrafo2);
    }

    
}



cargarRios();
