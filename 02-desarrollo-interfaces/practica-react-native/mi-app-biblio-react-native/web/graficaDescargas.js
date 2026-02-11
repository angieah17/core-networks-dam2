import Chart from "chart.js/auto";

//esta función permite pasarle los libros que ya se han cargado en la página Descargas
export function pintarGraficaDescargas(idContenedor, libros) {
  const contenedor = document.getElementById(idContenedor);
  if (!contenedor) return;

  // ESCRIBIR AQUÍ. Crea tu javascript Nativo/vainilla como mejor sabes


  //1. Prepara los datos para la gráfica

  
    const etiquetas = libros.map(libro => libro.titulo); // Extrae los títulos de los libros para usarlos como etiquetas en la gráfica
    const datos = libros.map(libro => libro.total_descargas);   // Extrae el total de descargas para cada libro para usarlos como datos en la gráfica
    const etiquetasLimitadas = etiquetas.slice(0, 10); // Limitar a los 10 primeros libros
    const datosLimitados = datos.slice(0, 10); // Limitar a los 10 primeros libros


  //2. Crea la gráfica usando Chart.js

    const lienzo = document.createElement("canvas"); //Se crea un elemento canvas que es donde se dibujará la gráfica
    lienzo.setAttribute("id","lienzo"); //se le asigna un id al canvas para poder referenciarlo posteriormente
    contenedor.appendChild(lienzo); //se agrega el canvas al contenedor que se ha pasado como parámetro a la función


    /*El canvas creado se pasa como parámetro del new Chart que se está creando para que allí dibuje la gráfica.
    Por eso es necesario que este id se corresponda con el nombre de la constante asignada al canvas creado.*/
  new Chart(lienzo, {
  type: "bar",
  data: {
    labels: etiquetasLimitadas, //las etiquetas de la gráfica serán los títulos de los libros
    datasets: [{
      label: "Total de descargas", 
      data: datosLimitados, //los datos de la gráfica serán el total de descargas de cada libro
      backgroundColor: [ //se agregan colores de fondo para cada barra de la gráfica, mejorando la visualización para el usuario
      'rgba(255, 99, 132, 0.2)',
      'rgba(255, 159, 64, 0.2)',
      'rgba(255, 205, 86, 0.2)',
      'rgba(75, 192, 192, 0.2)',
      'rgba(54, 162, 235, 0.2)',
      'rgba(153, 102, 255, 0.2)',
      'rgba(114, 43, 171, 0.2)',
      'rgba(43, 94, 197, 0.2)',
      'rgba(99, 120, 14, 0.2)',
      'rgba(115, 146, 114, 0.2)',
    ],
    }]
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      y: {
        min: 0,
        max: Math.max(...datosLimitados) + 5, // Establece el máximo del eje Y un poco por encima del valor máximo de descargas para mejor visualización
        ticks: {
          stepSize: 1
        }
      }
    }
  }
});
  }
