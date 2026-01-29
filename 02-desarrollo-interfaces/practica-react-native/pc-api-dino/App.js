import { useEffect, useState } from 'react';
import {
  Text,
  ScrollView,
  TouchableOpacity,
  View,
  ActivityIndicator,
} from 'react-native';

const API_BASE = 'https://dinoapi.brunosouzadev.com/api/dinosaurs';

export default function App() {
  const [lista, setLista] = useState([]);
  const [dinoSeleccionado, setDinoSeleccionado] = useState(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  // 1️nCargar lista de ríos
  useEffect(() => {
    async function cargarLista() {
      try {
        const res = await fetch(API_BASE);
        const json = await res.json();
        setLista(json);
      } catch (err) {
        setError('Error cargando la lista de dinosaurios');
      } finally {
        setCargando(false);
      }
    }

    cargarLista();
  }, []);

  // Aquí estamos cagando un dino por su NAME
  async function cargarDino(name) {
    try {
      setCargando(true);
      const res = await fetch(`${API_BASE}/${name}`);
      const json = await res.json();
      setDinoSeleccionado(json[0]); // la API devuelve array
    } catch (err) {
      setError('Error cargando el Dinosaurio');
    } finally {
      setCargando(false);
    }
  }

  // Estados generales
  if (cargando) {
    return <ActivityIndicator size="large" style={{ marginTop: 50 }} />;
  }

  if (error) {
    return <Text style={{ padding: 20 }}>{error}</Text>;
  }

  // Mostramos el listado. Los estilos están en línea. Os pido, querido  alumnado que lo apliquéis
  if (!dinoSeleccionado) {
    return (
      <ScrollView style={{ padding: 20 }}>
        <Text style={{ fontSize: 22, fontWeight: 'bold', marginBottom: 20 }}>
          Dinosaurios
        </Text>

        {lista.map((dino) => (
          <TouchableOpacity
            key={dino.name}
            onPress={() => cargarDino(dino.name)}
            style={{
              padding: 15,
              marginBottom: 10,
              backgroundColor: '#eee',
              borderRadius: 6,
            }}
          >
            <Text style={{ fontSize: 16, fontWeight: 'bold' }}>
              {dino.name}
            </Text>

            <Text>{dino.period}</Text>
          </TouchableOpacity>
        ))}
      </ScrollView>
    );
  }

  // Aquí mostramos el detalle de cada Dinosaurio
  return (
    <ScrollView style={{ padding: 20 }}>
      <TouchableOpacity
        onPress={() => setDinoSeleccionado(null)}
        style={{ marginBottom: 20 }}
      >
        <Text style={{ color: 'blue' }}>← Volver</Text>
      </TouchableOpacity>

      <Text style={{ fontSize: 24, fontWeight: 'bold', marginBottom: 10 }}>
        {dinoSeleccionado.name}
      </Text>

      <Text style={{ marginBottom: 10 }}>
        {dinoSeleccionado.region}
      </Text>

      <Text style={{ marginTop: 20 }}>
        {dinoSeleccionado.description}
      </Text>
    </ScrollView>
  );
}