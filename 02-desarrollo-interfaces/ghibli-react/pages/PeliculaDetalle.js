import React from 'react';
import { ScrollView, StyleSheet, Text } from 'react-native';
import theme from '../theme';
import { Card, Button } from 'react-native-paper';

export default function PeliculaDetalle({ route, navigation }) {
  const { pelicula } = route.params || {};

  if (!pelicula) {
    return (
      <ScrollView style={styles.full} contentContainerStyle={styles.screen}>
        <Text>No hay datos de la película.</Text>
      </ScrollView>
    );
  }

  return (
    <ScrollView style={styles.full} contentContainerStyle={styles.screen}>
      <Card>
        {pelicula.movie_banner ? <Card.Cover source={{ uri: pelicula.movie_banner }} /> : null}
        <Card.Content>
          <Text style={styles.title}>{pelicula.title}</Text>
          <Text style={styles.subtitle}>{pelicula.original_title}</Text>

          <Text>Director: {pelicula.director}</Text>
          <Text>Productor: {pelicula.producer}</Text>
          <Text>Año: {pelicula.release_date}</Text>
          <Text>Duración: {pelicula.running_time} min</Text>
          <Text>Puntuación: {pelicula.rt_score}</Text>

          <Text style={styles.description}>{pelicula.description}</Text>
        </Card.Content>

        <Card.Actions>
          <Button onPress={() => navigation.goBack()}>Volver</Button>
        </Card.Actions>
      </Card>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  full: { flex: 1, backgroundColor: theme.colors.background },
  screen: { padding: 14 },
  title: { fontSize: 24, fontWeight: '700', marginBottom: 6, color: theme.colors.text, fontFamily: 'JosefinSans_200ExtraLight' },
  subtitle: { fontStyle: 'italic', marginBottom: 8, color: theme.colors.placeholder },
  description: { marginTop: 10, color: theme.colors.text, lineHeight: 20 },
});
