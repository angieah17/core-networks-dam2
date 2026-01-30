import { useEffect, useState } from 'react';
import {
  Text,
  ScrollView,
  TouchableOpacity,
  ActivityIndicator,
  StyleSheet,
  Image,
  Dimensions,
} from 'react-native';

const API_BASE = 'https://dinoapi.brunosouzadev.com/api/dinosaurs';
const { width } = Dimensions.get('window');

export default function App() {
  const styles = getStyles();

  const [imgSize, setImgSize] = useState(null);
  const [lista, setLista] = useState([]);
  const [dinoSeleccionado, setDinoSeleccionado] = useState(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  // Cargar listado
  useEffect(() => {
    async function cargarLista() {
      try {
        const res = await fetch(API_BASE);
        const json = await res.json();
        setLista(json);
      } catch {
        setError('Error cargando la lista de dinosaurios');
      } finally {
        setCargando(false);
      }
    }

    cargarLista();
  }, []);

  // Calcular tamaño real de la imagen
  useEffect(() => {
    if (!dinoSeleccionado?.image) return;

    Image.getSize(
      dinoSeleccionado.image,
      (w, h) => {
        const ratio = width / w;
        setImgSize({
          width: width * 0.9,
          height: h * ratio * 0.9,
          alignSelf: 'center',
          marginVertical: 15,
        });
      },
      () => {
        setImgSize({
          width: width * 0.9,
          height: width * 0.6,
          alignSelf: 'center',
          marginVertical: 15,
        });
      }
    );
  }, [dinoSeleccionado]);

  // Cargar detalle
  async function cargarDino(name) {
    try {
      setCargando(true);
      const res = await fetch(`${API_BASE}/${name}`);
      const json = await res.json();
      setDinoSeleccionado(json[0]);
    } catch {
      setError('Error cargando el dinosaurio');
    } finally {
      setCargando(false);
    }
  }

  // Estados globales
  if (cargando) {
    return <ActivityIndicator size="large" style={{ marginTop: 50 }} />;
  }

  if (error) {
    return <Text style={{ padding: 20 }}>{error}</Text>;
  }

  // Listado
  if (!dinoSeleccionado) {
    return (
      <ScrollView style={styles.screen}>
        <Text style={styles.title}>Dinosaurios</Text>
        <Text style={styles.counter}>{lista.length}</Text>

        {lista.map((dino) => (
          <TouchableOpacity
            key={dino.name}
            onPress={() => cargarDino(dino.name)}
            style={styles.item}
          >
            <Text style={styles.itemTitle}>{dino.name}</Text>
            <Text>{dino.period}</Text>
          </TouchableOpacity>
        ))}
      </ScrollView>
    );
  }

  // Detalle
  return (
    <ScrollView style={styles.screen}>
      <TouchableOpacity
        onPress={() => {
          setDinoSeleccionado(null);
          setImgSize(null);
        }}
        style={styles.back}
      >
        <Text style={styles.backText}>← Volver</Text>
      </TouchableOpacity>

      <Text style={styles.detailTitle}>
        {dinoSeleccionado.name}
      </Text>

      {imgSize && (
        <Image
          source={{ uri: dinoSeleccionado.image }}
          style={imgSize}
          resizeMode="contain"
        />
      )}

      <Text style={styles.description}>
        {dinoSeleccionado.description}
      </Text>

      <Text style={styles.region}>
        Región: {dinoSeleccionado.region}
      </Text>
    </ScrollView>
  );
}

// 🎨 Función de estilos
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

    back: {
      marginBottom: 20,
    },

    backText: {
      color: 'blue',
    },

    detailTitle: {
      fontSize: 24,
      fontWeight: 'bold',
      marginBottom: 10,
    },

    description: {
      marginTop: 15,
    },

    region: {
      marginTop: 10,
    },
   
  });