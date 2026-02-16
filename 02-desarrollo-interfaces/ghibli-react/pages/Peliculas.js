import React, { useEffect, useState, useRef } from 'react';
import { View, Text, FlatList, StyleSheet, Platform } from 'react-native';
import theme from '../theme';
import { Card, ActivityIndicator, Button } from 'react-native-paper';

const API_BASE = 'https://ghibliapi.vercel.app/films/';

export default function Peliculas({ navigation }) {
  const [lista, setLista] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);
  const isMounted = useRef(true);

  async function cargarPeliculas() {
    if (isMounted.current) {
      setCargando(true);
      setError(null);
    }

    try {
      const res = await fetch(API_BASE);
      if (!res.ok) {
        // Lanzar con mensaje distinguible para diferenciar en el catch
        throw new Error(`Error del servidor: ${res.status} ${res.statusText || ''}`.trim());
      }
      const json = await res.json();
      if (isMounted.current) setLista(json || []);
    } catch (e) {
      if (isMounted.current) {
        const msg = (e && e.message && e.message.startsWith('Error del servidor'))
          ? e.message
          : 'Error de red. Comprueba tu conexión.';
        setError(msg);
      }
    } finally {
      if (isMounted.current) setCargando(false);
    }
  }

  useEffect(() => {
    isMounted.current = true;
    cargarPeliculas();
    return () => {
      isMounted.current = false;
    };
  }, []);

  
  if (cargando) {
    return (
      <View style={styles.center}>
        <ActivityIndicator animating={true} size={48} />
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.screen}>
        <Text style={styles.error}>{error}</Text>
        <Button mode="contained" onPress={cargarPeliculas} style={styles.retryButton}>Reintentar</Button>
      </View>
    );
  }

  if (!lista || lista.length === 0) {
    return (
      <View style={styles.screen}>
        <Text style={styles.title}>Películas</Text>
        <Text>No se han encontrado películas.</Text>
      </View>
    );
  }

  function renderItem({ item }) {
    return (
      <Card style={styles.card} onPress={() => navigation.navigate('PeliculaDetalle', { pelicula: item })}>
        {item.image ? <Card.Cover source={{ uri: item.image }} /> : null}
        <Card.Title title={item.title} subtitle={`${item.release_date} — ${item.director}`} />
        <Card.Content>
          <Text numberOfLines={3} style={styles.description}>{item.description}</Text>
        </Card.Content>
        <Card.Actions>
          <Button onPress={() => navigation.navigate('PeliculaDetalle', { pelicula: item })}>Ver detalle</Button>
        </Card.Actions>
      </Card>
    );
  }

  return (
    <FlatList
      style={styles.screen}
      contentContainerStyle={styles.list}
      ListHeaderComponent={() => <Text style={styles.title}>Studio Ghibli Peliculas</Text>}
      data={lista}
      keyExtractor={(i) => i.id}
      renderItem={renderItem}
    />
  );
}

const styles = StyleSheet.create({
  screen: { padding: 16, backgroundColor: theme.colors.background },
  center: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: theme.typography.title, fontWeight: theme.typography.weightBold, marginBottom: 12, color: theme.colors.onSurface, fontFamily: theme.fonts.medium.fontFamily },
  error: { color: theme.colors.error, padding: 8 },
  list: { padding: 12 },
  card: { marginBottom: 14, borderRadius: 8, overflow: 'hidden' },
  description: { marginTop: 8, color: theme.colors.text, lineHeight: 20 },
  retryButton: { marginTop: 12, alignSelf: 'flex-start' },
});
