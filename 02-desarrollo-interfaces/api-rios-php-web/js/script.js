const raiz = document.getElementById('root');

async function cargarRios() {
    try {
        const respuesta = await fetch('http://192.168.0.237:8087/api/index.php') //se agrega la ip, con ip config en la carpeta y se agrega el http y el puerto
        const datos = await respuesta.json(); //con esto convertimos la respuesta en json, para acceder a ellos

        //Mostramos datos en root
        raiz.innerText = '';
        const lista = document.createElement('ul');
        datos.forEach(rio => {
            const item = document.createElement('li');
            item.setAttribute('data-id', rio.id_rio);
            item.innerText = rio.nombre_rio;
            lista.append(item);
        });

        raiz.append(lista);

        //Al pulsar sobre cada li escriba en una caja los datos del río

        

        /* Mi versión: 
        
        raiz.innerText = '';
        datos.forEach(rio => {
            const tarjeta = document.createElement('div');
            tarjeta.classList.add('rio');

            const nombre = document.createElement('h2');
            nombre.textContent = rio.nombre_rio;

            const longitud = document.createElement('p');
            longitud.textContent = `Longitud: ${rio.longitud_rio} km`;

            const continente = document.createElement('p');
            continente.textContent = `Continente: ${rio.continente}`;

            const descripcion = document.createElement('p');
            descripcion.textContent = rio.descripcion;

            const listaPaises = document.createElement('ul');

            rio.paises_rio.forEach(pais => {
                const li = document.createElement('li');
                li.textContent = pais;
                listaPaises.appendChild(li);
            });


            tarjeta.append(nombre, longitud, continente, descripcion, listaPaises);
            raiz.appendChild(tarjeta);

        });
        
        */

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
