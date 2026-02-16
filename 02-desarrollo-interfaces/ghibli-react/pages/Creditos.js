import React, { useState } from 'react';
import { ScrollView, StyleSheet, View } from 'react-native';
import {
  Appbar,
  Card,
  Text,
  Button,
  Portal,
  Dialog,
  List,
  Divider,
} from 'react-native-paper';
import theme from '../theme';

const Creditos = ({ navigation }) => {
  const [visible, setVisible] = useState(false);

  return (
    <View style={styles.full}>
      <Appbar.Header>
        <Appbar.Content title="Créditos" titleStyle={styles.title} />
      </Appbar.Header>

      <ScrollView contentContainerStyle={styles.container}>
        <Card style={styles.card}>
          <Card.Content>
            <Text style={styles.title}>Uso de la API pública</Text>
            <Text style={styles.paragraph}>
              Esta aplicación obtiene información sobre las películas del estudio Studio Ghibli
              mediante una API pública. La app realiza peticiones HTTP (GET) para recuperar la
              lista de películas y muestra los datos al usuario en pantallas de listado y detalle.
            </Text>
          </Card.Content>
        </Card>

        <Card style={styles.card}>
          <Card.Content>
            <Text style={styles.title}>URL utilizada</Text>
            <Text style={styles.paragraph}>
              La API consumida es pública y está disponible en:
            </Text>
            <List.Item
              title="https://ghibliapi.vercel.app/films/"
              description="Endpoint que devuelve la lista completa de películas"
              left={() => <List.Icon icon="web" />}
            />
          </Card.Content>
        </Card>

        <Card style={styles.card}>
          <Card.Content>
            <Text style={styles.title}>Tipo de datos obtenidos</Text>
            <Text style={styles.paragraph}>
              La API devuelve datos en formato JSON. Concretamente, el endpoint devuelve un
              array de objetos, donde cada objeto representa una película con campos como:
            </Text>

            <List.Section>
              <List.Item title="id" description="Identificador único (string)" />
              <Divider />
              <List.Item title="title" description="Título de la película (string)" />
              <Divider />
              <List.Item title="original_title" description="Título original (string)" />
              <Divider />
              <List.Item title="director" description="Director (string)" />
              <Divider />
              <List.Item title="producer" description="Productor(es) (string)" />
              <Divider />
              <List.Item title="release_date" description="Año de estreno (string o number)" />
              <Divider />
              <List.Item title="running_time" description="Duración en minutos (string)" />
              <Divider />
              <List.Item title="rt_score" description="Puntuación (Rotten Tomatoes) (string)" />
            </List.Section>
          </Card.Content>
        </Card>

        <Card style={styles.card}>
          <Card.Content>
            <Text style={styles.title}>Justificación educativa</Text>
            <Text style={styles.paragraph}>
              Esta API pública es adecuada para uso educativo porque:
            </Text>
            <List.Section>
              <List.Item
                title="Acceso abierto"
                description="No requiere autenticación, facilita experimentación rápida."
              />
              <Divider />
              <List.Item
                title="Estructura clara"
                description="JSON bien formado y ejemplos de objetos que permiten practicar parsing."
              />
              <Divider />
              <List.Item
                title="Casos reales"
                description="Permite aprender sobre fetch, estados de carga, manejo de errores y navegación."
              />
            </List.Section>
            <Text style={styles.paragraph}>
              En resumen, usar esta API permite a estudiantes practicar llamadas HTTP, manejo de
              respuestas JSON, modelado de datos en la UI y experiencia de usuario frente a
              fallos de red o datos inesperados.
            </Text>
          </Card.Content>
        </Card>

        <Button mode="contained" onPress={() => setVisible(true)} style={styles.button}>
          He leído y entiendo
        </Button>

        <Portal>
          <Dialog visible={visible} onDismiss={() => setVisible(false)}>
            <Dialog.Title>Confirmación</Dialog.Title>
            <Dialog.Content>
              <Text>Gracias. Has leído la información sobre el uso de la API pública.</Text>
            </Dialog.Content>
            <Dialog.Actions>
              <Button onPress={() => setVisible(false)}>Cerrar</Button>
            </Dialog.Actions>
          </Dialog>
        </Portal>
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  full: { flex: 1, backgroundColor: theme.colors.background },
  container: { padding: 16, paddingBottom: 40 },
  card: { marginBottom: 12, borderRadius: 8, overflow: 'hidden' },
  title: { fontSize: 20, fontWeight: '700', marginBottom: 8, color: theme.colors.text, fontFamily: 'JosefinSans_200ExtraLight' },
  paragraph: { marginTop: 8, marginBottom: 8, lineHeight: 20, color: theme.colors.text },
  button: { marginTop: 12 },
});

export default Creditos;
