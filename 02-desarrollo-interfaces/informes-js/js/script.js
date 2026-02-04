const URL_DATOS = 'datos/calificaciones.json';

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