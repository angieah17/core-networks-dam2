# Informe para entrega manuscrita

Fecha: (escribir la fecha)

## Propósito
Breve resumen técnico y justificativo sobre consumo de APIs, manejo de estado en React, gestión de errores, navegación, uso de React Native Paper, decisiones de diseño y estructura del proyecto.

## Consumo de API REST con `fetch`
- Flujo básico: `fetch(url, { method, headers, body })` → comprobar `response.ok` → `await response.json()`.
- Ejemplo mínimo:

```js
async function obtenerPeliculas(url) {
  const controller = new AbortController();
  const timeout = setTimeout(() => controller.abort(), 10000);
  try {
    const res = await fetch(url, { signal: controller.signal });
    if (!res.ok) throw new Error(res.status + ' ' + res.statusText);
    const data = await res.json();
    return data;
  } finally { clearTimeout(timeout); }
}
```

- Consideraciones: headers (Authorization, Content-Type), timeouts (AbortController), reintentos exponenciales en fallos transitorios, paginación y validación de esquema.
- Organización recomendada: separar capa `api/ghibli.js` que normalice respuestas.

## Gestión del estado en React (`useState`, `useEffect`)
- `useState` para estado local: formularios, toggles, listas.
- `useEffect` para efectos y cargas iniciales:

```js
useEffect(() => {
  let mounted = true;
  obtenerPeliculas('/api/peliculas').then(data => {
    if (mounted) setPeliculas(data);
  }).catch(setError);
  return () => { mounted = false; };
}, []);
```

- Patrones: hooks personalizados (`usePeliculas`), elevar estado a padres o usar `Context`/`useReducer` si es global.

## Estrategias de gestión de errores implementadas
- Clasificación: red (network), HTTP (4xx/5xx), y lógica/parseo.
- UX: estados `loading`, `error` y componentes de reintento (botón "Reintentar").
- Recuperación: reintentos para errores temporales y fallback con caché (`AsyncStorage`).
- Registro: enviar errores críticos a Sentry u otro servicio.

## Funcionamiento de la navegación (React Navigation)
- Estructura típica: `Stack.Navigator` principal; opcional `Tab` o `Drawer`.
- Navegar con: `navigation.navigate('PeliculaDetalle', { id })`.
- Lectura de params con `useRoute()` y acciones con `useNavigation()`.
- Buenas prácticas: centralizar rutas, tipar params y pasar sólo ids en params.

## Uso y personalización de React Native Paper
- Componentes comunes: `Appbar`, `Card`, `Button`, `TextInput`, `Snackbar`.
- Theming global: definir `theme.js` y envolver la app con `Provider` de Paper.
- Ejemplo de Card:

```jsx
<Card style={{ margin: 8 }}>
  <Card.Title title={pelicula.title} />
  <Card.Content>
    <Paragraph>{pelicula.description}</Paragraph>
  </Card.Content>
</Card>
```

- Accesibilidad: contraste y `accessibilityLabel` donde aplique.

## Justificación de decisiones de diseño
- Colores: color primario para CTA y secundario para acentos; asegurar contraste.
- Espaciado: sistema modular (múltiplos de 4 u 8) para consistencia.
- Tipografía: jerarquía clara (títulos > subtítulos > cuerpo) para escaneo rápido.
- Estructura: lista principal de películas, pantalla de detalle con imagen, sinopsis y acciones claras; prioriza contenido relevante.

## Estructura del proyecto y organización del código
- Archivos principales:
  - `App.js` — entrypoint y providers (Paper, Navigation, Theme).
  - `pages/` — pantallas: `Peliculas.js`, `PeliculaDetalle.js`, `Ayuda.js`, `Creditos.js`.
  - `componentes/` — componentes reutilizables como `MenuAbajo.js`.
  - `theme.js` — tema compartido de Paper.
  - `api/` (recomendado) — funciones para llamadas a la API.
  - `utils/` (recomendado) — utilidades y formateadores.

- Patrones: separar presentación y lógica; hooks personalizados; constantes centralizadas para rutas.

## Instrucciones para la entrega manuscrita
- Escribir cada sección con título y 2–4 puntos clave.
- Añadir el ejemplo de `fetch` y el diagrama mental de rutas (Stack → Detalle).
- Si se prefiere imprimir, convertir este archivo Markdown a PDF.

---

Si desea, convierto este Markdown a PDF y lo dejo en la raíz del proyecto.
