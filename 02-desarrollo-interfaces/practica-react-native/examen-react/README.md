# Aplicación de personajes de Star Wars

## 1. Introducción

Esta aplicación permite consultar información sobre distintos personajes del universo Star Wars de forma sencilla e intuitiva.

La aplicación obtiene los datos desde la **API pública de SWAPI (Star Wars API)**, por lo que es necesario disponer de conexión a la red para su correcto funcionamiento.

🔗 **Enlaces a la API:** 
- Ver todos: [https://akabab.github.io/starwars-api/api/all.json](https://akabab.github.io/starwars-api/api/all.json)
- Ver uno: [https://akabab.github.io/starwars-api/api/id/1.json](https://akabab.github.io/starwars-api/api/id/1.json)

El objetivo principal de la aplicación es mostrar información de los personajes y guiar al usuario en todo momento mediante mensajes claros sobre el estado de la aplicación.

## 2. Qué puede hacer esta aplicación

Con esta aplicación podemos:

- Ver un listado de personajes de Star Wars
- Seleccionar un personaje para ver su información detallada
- Visualizar una imagen del personaje
- Consultar datos básicos como nombre, género o altura.
- Recibir mensajes claros cuando la aplicación está cargando datos o cuando ocurre un error

## 3. Funcionamiento general de la aplicación

### 3.1 Inicio de la aplicación

Al iniciar la aplicación:

1. La aplicación se conecta a Internet
2. Solicita los datos de los personajes a una API externa
3. Muestra un mensaje indicando que los datos se están cargando

**Ejemplo de mensaje:**  
*"Cargando, espere un momento…"*

Durante este proceso, el usuario no debe realizar ninguna acción.

### 3.2 Listado de personajes

Una vez los datos se han cargado correctamente:

- Se muestra un listado con los personajes disponibles
- Cada personaje aparece identificado por su nombre 
- La aplicación indica al usuario qué debe hacer a continuación

**Ejemplo de mensaje:**  
*"Puedes hacer click en cada uno para acceder a más detalles."*

El usuario puede desplazarse por la lista y pulsar sobre cualquier personaje.

### 3.3 Vista de detalle de un personaje

Al seleccionar un personaje:

- Se muestra una nueva vista con la información detallada
- Aparece la imagen del personaje
- Se muestran sus datos principales (nombre, género, altura)
- La interfaz incluye un enlace para volver al listado principal

**Ejemplo:**  
*"← Volver"*

## 4. Mensajes de estado de la aplicación

La aplicación informa al usuario en todo momento sobre lo que está ocurriendo.

### 4.1 Estado de carga

Cuando la aplicación está esperando datos de la API:

- Se muestra un indicador de carga
- Se informa al usuario de que la aplicación está trabajando

**Ejemplo:**  
*"Cargando, espere un momento…"*

### 4.2 Estado correcto

Cuando los datos se han cargado correctamente:

- Se muestra el contenido solicitado
- Desaparecen los mensajes de carga
- El usuario puede interactuar con normalidad

### 4.3 Estado de error

Si ocurre algún problema (por ejemplo, falta de conexión a Internet o error en la API):

- La aplicación muestra un mensaje claro y personalizado de acuerdo al tipo de error
- El usuario entiende que el problema no es suyo

**Ejemplo:**  
*"Error cargando la lista de personajes. Inténtalo de nuevo."*

En este estado, la aplicación no muestra datos incompletos ni confusos.

## 5. Requisitos para el correcto uso

Para usar la aplicación correctamente es necesario:

- Disponer de conexión a Internet
- Usar un dispositivo compatible (navegador web o dispositivo móvil)
- Esperar a que los datos se carguen antes de interactuar

## 6. Limitaciones conocidas

Esta aplicación depende de una API externa, por lo que:

- Si la API no está disponible, los datos no se mostrarán
- La velocidad de carga puede variar según la conexión
- La información mostrada es la que proporciona la API y puede cambiar

Estas limitaciones se comunican al usuario mediante mensajes en la interfaz.