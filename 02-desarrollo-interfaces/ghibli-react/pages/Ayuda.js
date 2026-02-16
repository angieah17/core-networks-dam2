import React, { useState } from 'react';
import { ScrollView, View, Text, StyleSheet } from 'react-native';
import { List, Divider } from 'react-native-paper';
import theme from '../theme';

export default function Ayuda() {

  const [expanded, setExpanded] = useState({
    nav: false,
    secciones: false,
    errores: false,
    interaccion: false,
    formato: false,
  });

  const toggle = (key) => setExpanded(prev => ({ ...prev, [key]: !prev[key] }));

  return (
    <ScrollView style={styles.full} contentContainerStyle={styles.container}>
      <Text style={styles.title}>Ayuda</Text>

      <List.Section>
        <List.Accordion
          title="1. Explicación de la navegación"
          left={props => <List.Icon {...props} icon="compass" />}
          expanded={expanded.nav}
          onPress={() => toggle('nav')}
        >
          <List.Item
            title="Menú inferior"
            description="Usa el menú inferior para cambiar entre Películas, Créditos y Ayuda. Pulsa la pestaña correspondiente." />
          <List.Item
            title="Navegación interna"
            description="Desde una lista, pulsa un elemento para ver su detalle; usa atrás para volver a la lista." />
        </List.Accordion>

        <Divider />

        <List.Accordion
          title="2. Explicación de cada sección"
          left={props => <List.Icon {...props} icon="file-document" />}
          expanded={expanded.secciones}
          onPress={() => toggle('secciones')}
        >
          <List.Item
            title="Películas"
            description="Listado con las películas. Pulsa sobre una para ver `PeliculaDetalle` con sinopsis, año y personajes." />
          <List.Item
            title="Créditos"
            description="Página con información del alumnado, herramientas usadas y referencias del proyecto." />
          <List.Item
            title="Ayuda"
            description="Esta pantalla con guía de uso, errores comunes e instrucciones de interacción." />
        </List.Accordion>

        <Divider />

        <List.Accordion
          title="3. Posibles errores"
          left={props => <List.Icon {...props} icon="alert-circle" />}
          expanded={expanded.errores}
          onPress={() => toggle('errores')}
        >
          <List.Item
            title="Fallo de red"
            description="La app mostrará 'Error de red. Comprueba tu conexión.' y podrás pulsar 'Reintentar' para intentar la carga de nuevo." />
          <List.Item
            title="Datos no encontrados"
            description="Si la API devuelve un array vacío verás 'No se han encontrado películas'." />
          <List.Item
            title="Error del servidor"
            description="Si el servidor responde con error, la app mostrará un mensaje con el código (p. ej. 'Error del servidor: 500')." />
        </List.Accordion>

        <Divider />

        <List.Accordion
          title="4. Cómo interactuar con la aplicación"
          left={props => <List.Icon {...props} icon="gesture-tap" />}
          expanded={expanded.interaccion}
          onPress={() => toggle('interaccion')}
        >
          <List.Item
            title="Ver más detalles"
            description="Pulsa sobre una película para abrir su pantalla de detalle con información ampliada." />
          <List.Item
            title="Volver"
            description="Usa el botón de retroceso del dispositivo o el menú inferior para regresar a la pantalla anterior." />
          <List.Item
            title="Actualizar"
            description="Para actualizar, vuelve a abrir la pantalla de Películas o usa 'Reintentar' si aparece un error." />
        </List.Accordion>
        

      </List.Section>

      <View style={styles.footer}>
        <Text style={styles.footerText}>¿Necesitas más ayuda? Pide soporte al docente o revisa la documentación del proyecto.</Text>
      </View>

    </ScrollView>
  );
}

const styles = StyleSheet.create({
  full: { flex: 1, backgroundColor: theme.colors.background },
  container: { padding: 16, paddingBottom: 40 },
  title: { fontSize: theme.typography.title, fontWeight: theme.typography.weightBold, marginBottom: 8, fontFamily: theme.fonts.medium.fontFamily, color: theme.colors.onSurface },
  footer: { marginTop: 16 },
  footerText: { color: theme.colors.text, fontSize: 14 }
});
