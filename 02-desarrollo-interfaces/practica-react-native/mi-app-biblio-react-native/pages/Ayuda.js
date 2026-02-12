import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { List } from 'react-native-paper';

export default function Ayuda() {

  const [expanded, setExpanded] = React.useState(true);

  const handlePress = () => setExpanded(!expanded);


  return (
    <List.Section title="Ayuda">
      <List.Accordion
        title="¿Qué es esta aplicación?"
        left={props => <List.Icon {...props} icon="folder" />}>
        <List.Item title="Esta aplicación es una biblioteca digital que permite consultar un catálogo de libros y visualizar estadísticas sobre su uso.
" /> 
        <List.Item title="Los datos se obtienen desde una API externa actualizada en tiempo real, lo que permite conocer:" />
        <List.Item title="- El listado completo de libros disponibles" />
        <List.Item title="- El número total de descargas de cada libro" />
        <List.Item title="- Un informe visual con los libros más descargados" />
        <List.Item title="La aplicación está diseñada para funcionar tanto en dispositivos móviles como en versión web." />
      </List.Accordion>

      <List.Accordion
        title="Cómo usar el menú inferior"
        left={props => <List.Icon {...props} icon="folder" />}
        expanded={expanded}
        onPress={handlePress}>
        <List.Item title="En la parte inferior de la pantalla encontrarás el menú principal de navegación." />
        <List.Item title="Cada icono o pestaña permite acceder a una sección diferente de la aplicación." />
        <List.Item title="Las secciones disponibles son:" />
        <List.Item title="- Libros" />
        <List.Item title="- Informe de descargas" />
        <List.Item title="- Ayuda" />
        <List.Item title="Para cambiar de sección, simplemente pulsa sobre la opción correspondiente en el menú." />
      </List.Accordion>

      <List.Accordion
        title="Sección “Libros”"
        left={props => <List.Icon {...props} icon="folder" />}
        expanded={expanded}
        onPress={handlePress}>
        <List.Item title="En esta sección puedes:" />
        <List.Item title="- Consultar el catálogo completo." />
        <List.Item title="- Ver el título y autor de cada libro." />
        <List.Item title="- Acceder a la descarga (en versión web)." />
        <List.Item title="Acceder a la descarga (en versión web)." />
      </List.Accordion>

      <List.Accordion
        title="Sección “Descargas”"
        left={props => <List.Icon {...props} icon="folder" />}
        expanded={expanded}
        onPress={handlePress}>
        <List.Item title="En esta sección se muestra:" />
        <List.Item title="- El título de cada libro." />
        <List.Item title="- El número total de descargas realizadas." />
        <List.Item title="- La información está organizada para que puedas identificar rápidamente qué libros son más populares." />
        <List.Item title="La información está organizada para que puedas identificar rápidamente qué libros son más populares." />
      </List.Accordion>

    </List.Section>
  );
}

const styles = StyleSheet.create({
  contenedor: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  texto: { fontSize: 22 }
});
