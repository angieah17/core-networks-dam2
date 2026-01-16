<section class="desarrollo">
<h2>Conceptos básicos para comenzar a trabajar con React Native</h2>

<p>
React Native es un framework basado en React que permite desarrollar aplicaciones móviles nativas utilizando JavaScript y React.
A continuación, te dejo un resumen de los conceptos esenciales para iniciarte en este entorno:
</p>

<h3>1. ¿Qué es React Native?</h3>
<p>
React Native permite construir aplicaciones móviles para iOS y Android utilizando una base de código compartida escrita en JavaScript o TypeScript.
En lugar de renderizar en el navegador como React, se traduce a componentes nativos en los dispositivos.
</p>

<p><strong>Ventajas:</strong></p>
<ul>
  <li><strong>Reutilización de código:</strong> Gran parte del código funciona en ambas plataformas.</li>
  <li><strong>Desempeño nativo:</strong> Utiliza componentes nativos de cada plataforma.</li>
  <li><strong>Gran comunidad:</strong> Muchas librerías y soporte en línea.</li>
</ul>

<h3>2. Componentes básicos de React Native</h3>
<p>Estos son los equivalentes de elementos HTML en React Native:</p>
<ul>
  <li><strong>View:</strong> Similar a un &lt;div&gt; o &lt;span&gt; en HTML; se usa para contenedores.</li>
  <li><strong>Text:</strong> Para mostrar texto.</li>
  <li><strong>Image:</strong> Para mostrar imágenes.</li>
  <li><strong>ScrollView:</strong> Contenedor que permite desplazamiento.</li>
  <li><strong>Touchable:</strong> Interactivos como botones (ej.: TouchableOpacity, TouchableHighlight).</li>
  <li><strong>FlatList y SectionList:</strong> Para mostrar listas de datos.</li>
</ul>

<h3>3. Estilos en React Native</h3>
<p>
React Native no usa CSS directamente, sino objetos de estilo con propiedades similares a CSS:
</p>

<pre><code class="language-javascript">
import { StyleSheet, Text, View } from 'react-native';

const App = () => (
  &lt;View style={styles.container}&gt;
    &lt;Text style={styles.text}&gt;¡Hola, React Native!&lt;/Text&gt;
  &lt;/View&gt;
);

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f0f0f0',
  },
  text: {
    fontSize: 20,
    color: '#333',
  },
});

export default App;
</code></pre>

<h3>4. Navegación</h3>
<p>La navegación entre pantallas es un aspecto importante. React Native usa bibliotecas como:</p>
<ul>
  <li><strong>React Navigation:</strong> Navegación basada en pilas, pestañas y drawers.</li>
  <li><strong>React Native Navigation:</strong> Más enfocada en rendimiento nativo.</li>
</ul>

<p><strong>Ejemplo básico con React Navigation:</strong></p>

<pre><code class="language-javascript">
import React from 'react';
import { View, Text, Button } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

const Stack = createNativeStackNavigator();

function HomeScreen({ navigation }) {
  return (
    &lt;View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}&gt;
      &lt;Text&gt;Pantalla Home&lt;/Text&gt;
      &lt;Button
        title="Ir a Details"
        onPress={() => navigation.navigate('Details')}
      /&gt;
    &lt;/View&gt;
  );
}

function DetailsScreen({ navigation }) {
  return (
    &lt;View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}&gt;
      &lt;Text&gt;Pantalla Details&lt;/Text&gt;
      &lt;Button
        title="Volver a Home"
        onPress={() => navigation.navigate('Home')}
      /&gt;
      &lt;Button
        title="Volver atrás"
        onPress={() => navigation.goBack()}
      /&gt;
    &lt;/View&gt;
  );
}

export default function App() {
  return (
    &lt;NavigationContainer&gt;
      &lt;Stack.Navigator initialRouteName="Home"&gt;
        &lt;Stack.Screen name="Home" component={HomeScreen} /&gt;
        &lt;Stack.Screen name="Details" component={DetailsScreen} /&gt;
      &lt;/Stack.Navigator&gt;
    &lt;/NavigationContainer&gt;
  );
}
</code></pre>

<h3>5. Manejo de estados</h3>
<ul>
  <li><strong>State:</strong> Para manejar estados internos de componentes.</li>
  <li><strong>Props:</strong> Para pasar datos a componentes hijos.</li>
  <li><strong>Context API o Redux:</strong> Para manejar estados globales en aplicaciones grandes.</li>
</ul>

<h3>6. Herramientas esenciales</h3>
<ul>
  <li><strong>Expo:</strong> Plataforma para prototipos rápidos.</li>
  <li><strong>CLI de React Native:</strong> Permite personalización total.</li>
  <li><strong>Debugging:</strong> React Developer Tools, console.log, Debugger de Chrome.</li>
  <li><strong>Hot Reloading:</strong> Actualiza la app en tiempo real.</li>
</ul>

<h3>7. Instalación inicial</h3>

<p><strong>Expo CLI (ideal para principiantes):</strong></p>

<pre><code class="language-javascript">
npx expo-cli init MiApp
cd MiApp
npx expo start
</code></pre>

<p><strong>React Native CLI (más control):</strong></p>

<pre><code class="language-javascript">
npx react-native init MiApp
cd MiApp
npx react-native run-android
npx react-native run-ios
</code></pre>

<h3>8. Diferencias con React</h3>
<ul>
  <li>React trabaja con el DOM en navegadores; React Native usa APIs nativas.</li>
  <li>React utiliza CSS; React Native usa StyleSheet.</li>
  <li>Componentes nativos como <strong>View</strong> o <strong>Text</strong> reemplazan elementos HTML.</li>
</ul>

</section>