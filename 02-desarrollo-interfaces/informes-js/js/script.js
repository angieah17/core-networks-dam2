const URL_DATOS = 'datos/calificaciones.json';

//1. Selección de elementos del DOM
const btnGenerar = document.getElementById('generar-informe');
const btnGuardar = document.getElementById('guardar-pdf');
const tabla = document.getElementById('tabla');
const promediosAsignatura = document.getElementById('promediosAsignatura');
const fechaEl = document.getElementById('fecha');

const ASIGNATURAS = ['matematicas', 'lengua', 'historia', 'ciencias'];
const NOMBRE_ASIG = {
  matematicas: 'Matemáticas',
  lengua: 'Lengua',
  historia: 'Historia',
  ciencias: 'Ciencias'
};

//2. Funciones
// Formatear número a formato español con 2 decimales
function formatNumber(n) { //n es un número que entra como parámetro
  return n.toLocaleString('es-ES', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}
