const URL_DATOS = "datos/calificaciones.json";

//1. Selección de elementos del DOM
const btnGenerar = document.getElementById("generar-informe");
const btnGuardar = document.getElementById("guardar-pdf");
const tabla = document.getElementById("tabla");
const promediosAsignatura = document.getElementById("promediosAsignatura");
const fechaEl = document.getElementById("fecha");

const ASIGNATURAS = ["matematicas", "lengua", "historia", "ciencias"];
const NOMBRE_ASIG = {
  matematicas: "Matemáticas",
  lengua: "Lengua",
  historia: "Historia",
  ciencias: "Ciencias",
};

//2. Funciones
//2.1 Formatear número a formato español con 2 decimales
function formatNumber(n) {
  return n.toLocaleString("es-ES", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}

//2.2 Formatear fecha a formato español largo
function formatDate(date) {
  const opts = { year: "numeric", month: "long", day: "numeric" };
  const txt = date.toLocaleDateString("es-ES", opts);
  return `Fecha de generación: ${txt}`;
}

//2.3 Cargar datos desde el archivo JSON
async function cargarDatos() {
  const res = await fetch(URL_DATOS);
  if (!res.ok) throw new Error("No se pudo cargar datos: " + res.status); //se usa para comprobar si la respuesta es correcta
  const json = await res.json();
  return json.alumnado || []; //en caso de que no exista alumnado, devolver un array vacío
}

//2.4 Limpiar informe
function limpiarInforme() {
  tabla.innerHTML = '';
  promediosAsignatura.innerHTML = '';
}

//2.5 Generar tabla de calificaciones

function generarTabla(alumnado) {
  limpiarInforme(); // limpiar informe antes de generar uno nuevo

  
  const sumaAsignatura = { matematicas: 0, lengua: 0, historia: 0, ciencias: 0 }; // objeto para acumular sumas por asignatura
  const nAlumnos = alumnado.length; // número de alumnos


  alumnado.forEach(al => {
    const notas = al.notas; // objeto con las notas del alumno
    const valores = ASIGNATURAS.map(a => Number(notas[a] ?? 0)); // array con las notas en el orden de ASIGNATURAS, si no existe la nota se pone 0
    const suma = valores.reduce((s, v) => s + v, 0); // suma de las notas del alumno, el método reduce acumula los valores del array, siendo s el acumulador y v el valor actual, empezando en 0
    const promedioAlumno = suma / ASIGNATURAS.length;

    
    const tr = document.createElement('tr'); // crear fila para el alumno
    const tdNombre = document.createElement('td'); // crear celda para el nombre
    tdNombre.textContent = al.nombre; // poner el nombre del alumno en la celda
    tr.appendChild(tdNombre); // añadir la celda del nombre a la fila

    valores.forEach(v => {
      const td = document.createElement('td'); // crear celda para la nota
      td.textContent = v; // poner la nota en la celda
      tr.appendChild(td); // añadir la celda a la fila
    });

    const tdProm = document.createElement('td'); // crear celda para el promedio
    tdProm.textContent = formatNumber(promedioAlumno); // poner el promedio formateado en la celda
    tr.appendChild(tdProm); // añadir la celda del promedio a la fila

    tabla.appendChild(tr); // añadir la fila a la tabla

    // acumular por asignatura
    ASIGNATURAS.forEach((a, i) => { sumaAsignatura[a] += valores[i]; }); // sumar la nota del alumno a la suma de la asignatura, con esto se lleva la cuenta de la suma total por asignatura
  });

  // promedios por asignatura
  ASIGNATURAS.forEach(a => {
    const li = document.createElement('li'); // crear elemento lista para el promedio de la asignatura
    const prom = nAlumnos ? sumaAsignatura[a] / nAlumnos : 0; // calcular el promedio de la asignatura, si no hay alumnos el promedio es 0
    li.textContent = `${NOMBRE_ASIG[a]}: ${formatNumber(prom)}`; // poner el texto con el nombre de la asignatura y el promedio formateado
    promediosAsignatura.appendChild(li); // añadir el elemento lista a la lista de promedios
  });
}

//2.6 Generar informe completo
async function generarInforme() {
  try {
    btnGenerar.disabled = true; 
    btnGenerar.textContent = 'Generando...';
    const alumnado = await cargarDatos(); //se espera a que se carguen los datos
    generarTabla(alumnado); //generar la tabla con los datos cargados
    fechaEl.textContent = formatDate(new Date());
  } catch (err) {
    alert('Error: ' + err.message);
    console.error(err);
  } finally {
    btnGenerar.disabled = false; 
    btnGenerar.textContent = 'Generar informe';
  }
}

//3. Eventos
btnGenerar.addEventListener('click', generarInforme); //cuando se hace click en el botón, se genera el informe