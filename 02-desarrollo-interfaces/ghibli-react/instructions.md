# Instrucciones del Proyecto: Ghibli Explorer App

## 1. Objetivo General
Desarrollar una aplicación en **React Native (Expo)** que consuma la API REST pública de Studio Ghibli (`https://ghibliapi.vercel.app/`) para mostrar información sobre sus películas. La aplicación constará de tres secciones principales y deberá cumplir con requisitos específicos de diseño, usabilidad, gestión de estado y documentación.

## 2. Funcionalidades y Secciones

### 2.1. Películas
- **Listado:** Obtener la lista de películas mediante `fetch` desde `https://ghibliapi.vercel.app/films/`.
- **Vista Resumida:** Mostrar una vista previa por cada película en el listado (ej. título, imagen, año).
- **Navegación a Detalle:** Al pulsar sobre un elemento del listado, navegar a una pantalla de detalle que muestre la ficha completa con los siguientes campos:
  - Título
  - Título original
  - Director
  - Productor(es)
  - Año de estreno
  - Duración
  - Puntuación (Rotten Tomatoes, ej. `rt_score`)
  - Descripción completa

### 2.2. Créditos
Pantalla informativa que debe incluir:
- Explicación del uso de la API pública.
- La URL utilizada.
- El tipo de datos obtenidos (JSON, estructura de objetos, etc.).
- Una justificación clara del uso de esta API con fines educativos.

### 2.3. Ayuda
Pantalla elaborada por el alumnado que sirva como guía de usuario. Debe contener:
- **Explicación de la navegación:** Cómo moverse entre las diferentes pantallas (Películas, Créditos, Ayuda).
- **Explicación de cada sección:** Descripción breve de qué información o funcionalidad encuentra el usuario en Películas, Créditos y Ayuda.
- **Posibles errores:** Qué puede ocurrir (ej. fallo de red, datos no encontrados) y cómo la aplicación informa de ello.
- **Cómo interactuar con la aplicación:** Instrucciones básicas (ej. "pulsa sobre una película para ver más detalles").
- **Formato libre:** Puedes usar `ScrollView`, secciones plegables (acordeones), tarjetas, etc., para presentar esta información de forma clara y atractiva.

## 3. Requisitos Técnicos Obligatorios

### 3.1. React Native Paper
- **Integración obligatoria:** La aplicación debe utilizar, como mínimo, **dos componentes diferentes** de la librería `react-native-paper`.
- **Uso significativo:** Los componentes no deben ser un "botón suelto sin sentido", sino que deben estar integrados de forma coherente en el diseño y la funcionalidad de la app.
- **Ejemplos de componentes válidos:** `Card`, `Button`, `TextInput`, `Snackbar`, `Appbar`, `Dialog`, `FAB`, `ActivityIndicator`.

### 3.2. Gestión de Estado y Errores
La aplicación debe manejar correctamente los siguientes estados:
- **Indicador de carga (Loading):** Mientras se obtienen los datos de la API.
- **Estado vacío:** Si la API devuelve un array vacío (aunque no sea lo esperado, debe contemplarse).
- **Estado de error:** Capturar y mostrar un mensaje claro si falla la petición a la API.
- **Manejo de errores de red / API:** Diferenciar o informar de manera genérica pero comprensible si no hay conexión o el servidor no responde.
- **Confirmaciones:** Cuando proceda (por ejemplo, en la pantalla de Ayuda se podría usar un `Dialog` para confirmar que se ha entendido un mensaje, aunque no es un requisito mayor, es una buena práctica).

### 3.3. Navegación
La aplicación debe tener una navegación clara y coherente entre las tres pantallas principales (Películas, Créditos, Ayuda) y la pantalla de detalle de la película. Se recomienda el uso de **React Navigation** (Stack y/o Bottom Tabs).

## 4. Diseño y Usabilidad
Se valorará la aplicación de los siguientes principios:
- **Jerarquía visual correcta:** Títulos, subtítulos y cuerpos de texto deben tener un tamaño y peso adecuados.
- **Distribución equilibrada:** Uso correcto de márgenes y espacios (padding/margin) para que la interfaz no se vea apelmazada.
- **Paleta de colores coherente:** Define una paleta de 2-3 colores principales y úsalos de forma consistente en toda la app (botones, cabeceras, acentos).
- **Contraste y legibilidad:** El texto debe ser fácil de leer sobre su fondo. Cumplir con las pautas de accesibilidad básicas.
- **Leyes de la Gestalt:** Aplicar principios como la proximidad (agrupar elementos relacionados), la similitud (mismo estilo para misma función) o el contraste (para destacar elementos importantes).
- **Textos claros y comprensibles:** La redacción debe ser sencilla y directa para el usuario.

## 5. Documentación (README.md)
El repositorio del proyecto debe incluir un archivo `README.md` con la siguiente estructura:
- **Descripción de la app:** ¿Qué es y para qué sirve Ghibli Explorer?
- **Tecnologías usadas:** React Native, Expo, React Native Paper, React Navigation, etc.
- **Instalación:** Pasos para clonar, instalar dependencias (`npm install` o `yarn`) y ejecutar la aplicación (`npx expo start`).
- **Estructura del proyecto:** Árbol de directorios y explicación breve de la función de cada carpeta/archivo principal (ej. `components/`, `screens/`, `utils/`).
- **Gestión de estado:** Explicar cómo se gestiona el estado (ej. `useState`, `useEffect`) y dónde residen los datos de la API.
- **Gestión de errores:** Describir los tipos de errores controlados y cómo se muestran al usuario.
- **Decisiones de diseño:** Justificar la paleta de colores, la tipografía, la distribución de la información y los componentes de `react-native-paper` elegidos.

## 6. Informe Escrito (A Bolígrafo - Entregable Físico)
Durante la última hora de la mañana, se entregará un informe escrito a mano que demuestre la comprensión del proyecto. El informe debe responder a preguntas sobre los siguientes puntos:
- Funcionamiento del consumo de una API REST con `fetch`.
- Gestión del estado en React (`useState`, `useEffect`).
- Estrategias de gestión de errores implementadas.
- Funcionamiento de la navegación utilizada (React Navigation).
- Uso y personalización de los componentes de React Native Paper.
- Justificación de las decisiones de diseño (colores, espaciado, estructura).
- Explicación de la estructura del proyecto y la organización del código.

## 7. Rúbrica de Evaluación (Ponderación sobre el 40% de la nota total)
- **Consumo de API (1,5 puntos):** Uso correcto de `fetch`, comprensión de la estructura de datos recibida.
- **Gestión de estado y errores (2 puntos):** Implementación correcta de `useState`, `useEffect`, y manejo de estados de carga, error y vacío.
- **Diseño y usabilidad (2,5 puntos):** Aplicación correcta de jerarquía visual, equilibrio, contraste y justificación de las decisiones de diseño.
- **Uso de React Native Paper (1 punto):** Integración coherente y personalización de al menos dos componentes de la librería.
- **Código y estructura (2 puntos):** Organización limpia del proyecto en componentes, reutilización y legibilidad del código.
- **Documentación (1 punto):** README completo y bien estructurado que refleje las decisiones del proyecto.
