# Ghibli Explorer

## Descripción de la app
Ghibli Explorer es una aplicación móvil creada con React Native y Expo que permite explorar películas del Studio Ghibli. Muestra una lista de películas, detalles individuales (sinopsis, director, año, personajes) y permite navegar entre pantallas con una experiencia sencilla y accesible.

## Tecnologías usadas
- **Framework:** React Native
- **Plataforma:** Expo
- **UI:** React Native Paper
- **Navegación:** React Navigation
- **Gestión de estado:** Hooks de React (`useState`, `useEffect`)
- **Otras:** fetch para llamadas a la API, assets locales para imágenes y estilos en `theme.js`.

## Instalación
1. Clona el repositorio:

```bash
git clone <repo-url>
cd ghibli-react
```

2. Instala dependencias (elige `npm` o `yarn`):

```bash
npm install
# o
yarn install
```

3. Ejecuta la aplicación con Expo:

```bash
npx expo start
```

Sigue las instrucciones de Expo para abrir en simulador/emulador o en un dispositivo físico.

## Estructura del proyecto
Raíz del proyecto (resumen):

- `App.js`: Punto de entrada de la app.
- `index.js`: Entrada para Expo/bundling.
- `theme.js`: Variables de tema (paleta de colores, tipografías y configuración de `react-native-paper`).
- `componentes/`: Componentes reutilizables de UI (ej. barra inferior, tarjetas).
  - `MenuAbajo.js`: Componente de navegación inferior.
- `pages/`: Pantallas principales de la aplicación.
  - `Peliculas.js`: Pantalla que lista las películas (fetch de la API).
  - `PeliculaDetalle.js`: Pantalla con detalles ampliados de una película.
  - `Ayuda.js`, `Creditos.js`: Pantallas informativas.

Cada pantalla gestiona su propia lógica de presentación y, cuando corresponde, las llamadas a la API para obtener los datos que muestra.

## Gestión de estado
- Estado local: La app utiliza `useState` para estados locales (por ejemplo: lista de películas, estados de carga, estado del formulario, modales abiertos).
- Efectos y fetch: `useEffect` se usa para lanzar las llamadas a la API al montar las pantallas (por ejemplo, en `Peliculas.js` se realiza la petición para obtener la lista de películas).
- Datos de la API: Las peticiones a la API (por ejemplo, Studio Ghibli API) se realizan directamente en las pantallas responsables; para proyectos más grandes se puede extraer a `services/api.js` o a un contexto global.

Ejemplo típico:

```js
useEffect(() => {
  setLoading(true);
  fetch(apiUrl)
    .then(res => res.json())
    .then(data => setPeliculas(data))
    .catch(err => setError(err.message))
    .finally(() => setLoading(false));
}, []);
```

## Gestión de errores
- Tipos de errores controlados:
  - Errores de red / timeouts al realizar fetch.
  - Respuestas vacías o con formato inesperado.
  - Errores de renderizado por datos nulos.
- Cómo se muestran al usuario:
  - Estados de carga (`ActivityIndicator`) mientras se espera la respuesta.
  - Mensajes de error legibles (por ejemplo, un `Card` o un `Dialog` con mensaje) cuando ocurre un error de red o la respuesta es inválida.
  - Mensajes de estado cuando no hay resultados (pantalla vacía con texto explicativo).

Ejemplo de manejo simple:

```js
if (loading) return <ActivityIndicator />;
if (error) return <Text>Error: {error}</Text>;
if (peliculas.length === 0) return <Text>No hay películas disponibles.</Text>;
```

## Tipografías y carga de fuentes

En el proyecto se está usando la familia *Josefin Sans* desde `theme.js`. Es importante tener en cuenta que los nombres como `JosefinSans_200ExtraLight` o `JosefinSans_100Thin` no son fuentes por defecto del sistema: deben cargarse explícitamente (por ejemplo, usando `@expo-google-fonts/josefin-sans` o `expo-font`).

Instalación (Expo):

```bash
npm install @expo-google-fonts/josefin-sans expo-font
# o
yarn add @expo-google-fonts/josefin-sans expo-font
```

Carga de ejemplo en `App.js` usando `useFonts` de `@expo-google-fonts`:

```js
import React from 'react';
import { Provider as PaperProvider } from 'react-native-paper';
import { useFonts, JosefinSans_200ExtraLight, JosefinSans_100Thin } from '@expo-google-fonts/josefin-sans';
import theme from './theme';

export default function App() {
  const [fontsLoaded] = useFonts({ JosefinSans_200ExtraLight, JosefinSans_100Thin });

  if (!fontsLoaded) return null; // o mostrar un loader

  return (
    <PaperProvider theme={theme}>
      {/* Resto de la app */}
    </PaperProvider>
  );
}
```

Verificación: asegúrate de que las keys usadas al cargar las fuentes coinciden con los `fontFamily` en `theme.js`. Por ejemplo, si en `theme.js` usas `fontFamily: 'JosefinSans_200ExtraLight'`, la key en `useFonts` debe ser `JosefinSans_200ExtraLight`.

Si prefieres fuentes locales, puedes colocarlas en `assets/fonts` y cargarlas con `expo-font` usando `Font.loadAsync`.

## Decisiones de diseño
- Paleta de colores: La paleta se centraliza en `theme.js` para garantizar consistencia. Se eligieron tonos suaves inspirados en la estética de Studio Ghibli (fondos neutros y acentos cálidos) para priorizar la legibilidad y la sensación cinematográfica.
- Tipografía: Se utiliza `Josefin Sans` cargada explícitamente (ver sección "Tipografías y carga de fuentes"). En `theme.js` se referencian familias como `JosefinSans_200ExtraLight` y `JosefinSans_100Thin` para controlar pesos y tamaños. Si la carga de las fuentes falla, la app debe contemplar un fallback a la tipografía del sistema para mantener legibilidad.
- Distribución de la información: En la lista de películas se muestra una tarjeta por película con miniatura, título y año; la pantalla de detalle muestra imagen grande, descripción y metadatos (director, productor, fecha) en un scroll vertical para facilitar lectura.
- Componentes `react-native-paper` seleccionados:
  - `Appbar`: para la barra superior y navegación simple.
  - `Card`: para mostrar cada película en la lista con imagen y acción.
  - `ActivityIndicator`: para indicar carga.
  - `Dialog`: para confirmaciones y mensajes modales (ej. pantalla de Créditos).
  - `Button`: acciones principales (ej. reintentar carga, confirmar diálogo).
  - `List` / `List.Icon`: para desgloses informativos y listas de metadatos.

Justificación: `react-native-paper` ofrece componentes accesibles y estilísticamente coherentes con Material Design, lo que agiliza el desarrollo y mejora la accesibilidad sin sacrificar personalización (a través de `theme.js`).

