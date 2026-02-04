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
