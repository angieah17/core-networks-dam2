import { useEffect, useState } from 'react';
import {
  Text,
  ScrollView,
  TouchableOpacity,
  View,
  ActivityIndicator,
} from 'react-native';

const API_BASE = 'http://192.168.1.136:8087/api/index.php';

export default function App() {
  const [lista, setLista] = useState([]);
  const [rioSeleccionado, setRioSeleccionado] = useState(null);
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
        setError('Error cargando la lista de ríos');
      } finally {
        setCargando(false);
      }
    }

    cargarLista();
  }, []);

  // Aquí estamos cagando un río por su ID
  async function cargarRio(id) {
    try {
      setCargando(true);
      const res = await fetch(`${API_BASE}?id=${id}`);
      const json = await res.json();
      setRioSeleccionado(json[0]); // la API devuelve array
    } catch (err) {
      setError('Error cargando el río');
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
  if (!rioSeleccionado) {
    return (
      <ScrollView style={{ padding: 20 }}>
        <Text style={{ fontSize: 22, fontWeight: 'bold', marginBottom: 20 }}>
          Ríos del mundo
        </Text>

        {lista.map((rio) => (
          <TouchableOpacity
            key={rio.id_rio}
            onPress={() => cargarRio(rio.id_rio)}
            style={{
              padding: 15,
              marginBottom: 10,
              backgroundColor: '#eee',
              borderRadius: 6,
            }}
          >
            <Text style={{ fontSize: 16, fontWeight: 'bold' }}>
              {rio.nombre_rio}
            </Text>

            <Text>{rio.continente}</Text>
          </TouchableOpacity>
        ))}
      </ScrollView>
    );
  }

  // Aquí mostramos el detalle de cada río
  return (
    <ScrollView style={{ padding: 20 }}>
      <TouchableOpacity
        onPress={() => setRioSeleccionado(null)}
        style={{ marginBottom: 20 }}
      >
        <Text style={{ color: 'blue' }}>← Volver</Text>
      </TouchableOpacity>

      <Text style={{ fontSize: 24, fontWeight: 'bold', marginBottom: 10 }}>
        {rioSeleccionado.nombre_rio}
      </Text>

      <Text style={{ marginBottom: 10 }}>
        Continente: {rioSeleccionado.continente}
      </Text>

      <Text style={{ marginBottom: 10 }}>
        Longitud: {rioSeleccionado.longitud_rio} km
      </Text>

      <Text style={{ marginBottom: 10, fontWeight: 'bold' }}>
        Países:
      </Text>

      {rioSeleccionado.paises_rio.map((pais, index) => (
        <Text key={index}>• {pais}</Text>
      ))}

      <Text style={{ marginTop: 20 }}>
        {rioSeleccionado.descripcion}
      </Text>
    </ScrollView>
  );
}