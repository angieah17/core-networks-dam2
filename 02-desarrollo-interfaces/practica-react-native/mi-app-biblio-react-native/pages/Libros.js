import { useEffect, useState } from 'react';
import {
  Text,
  ScrollView,
  TouchableOpacity,
  ActivityIndicator,
  StyleSheet,
  Platform, //componente que sirve para detectar la plataforma en la que se está ejecutando la aplicación (iOS, Android, web, etc.)
} from 'react-native';

const API_BASE = 'http://192.168.0.180:6060/api.php';

export default function Libros() {
  const styles = getStyles();

  const [lista, setLista] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  // Cargar listado
  useEffect(() => {
    async function cargarLista() {
      try {
        const res = await fetch(API_BASE);
        const json = await res.json();
        setLista(json);
      } catch (e) {
        setError('Error cargando la lista de librosaurios');
      } finally {
        setCargando(false);
      }
    }

    cargarLista();
  }, []);

  // Estados globales
  if (cargando) {
    return <ActivityIndicator size="large" style={{ marginTop: 50 }} />;
  }

  if (error) {
    return <Text style={{ padding: 20 }}>{error}</Text>;
  }

  function manejarDescarga(libro) {
  // WEB
  if (Platform.OS === 'web') {
    const enlace = document.createElement('a');
    enlace.href = libro.archivo;           // URL del archivo
    enlace.download = `${libro.titulo}.pdf`;
    document.body.appendChild(enlace);
    enlace.click();
    document.body.removeChild(enlace);
  }
  // MÓVIL (por ahora informativo)
  else {
    alert('Descarga disponible solo en versión web (de momento)');
  }
}

  // Listado
  return (
    <ScrollView style={styles.screen}>
      <Text style={styles.title}>Librosaurios</Text>
      <Text style={styles.counter}>{lista.length}</Text>

      {lista.map((libro) => (
        <TouchableOpacity
          key={libro.id_libro}
          onPress={() => manejarDescarga(libro)}
          style={styles.item}
        >
          <Text style={styles.itemTitle}>{libro.titulo}</Text>
          <Text>{libro.autor}</Text>
        </TouchableOpacity>
      ))}
    </ScrollView>
  );
}

const getStyles = () =>
  StyleSheet.create({
    screen: {
      padding: 20,
    },

    title: {
      fontSize: 22,
      fontWeight: 'bold',
      marginBottom: 20,
    },

    counter: {
      fontSize: 16,
      fontWeight: 'bold',
      marginBottom: 10,
    },

    item: {
      padding: 15,
      marginBottom: 10,
      backgroundColor: '#eee',
      borderRadius: 6,
    },

    itemTitle: {
      fontSize: 16,
      fontWeight: 'bold',
    },
  });
