import { useState, useEffect } from 'expo-status-bar';
import { StyleSheet, Text, View, ActivityIndicator } from 'react-native';

const API_BASE = 'http://192.168.0.203:8087/api/index.php'

export default function App() {

  const [cargando, setCargando] = useState(true);
  const [lista, setLista] = useState([]);
  const [rioDetalle, setRioDetalle] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => 
async function cargarRios() {
    try {
        const respuesta = await fetch(API_BASE) 
        const datos = await respuesta.json(); 
        setLista(datos);

    } catch (err) {
      console.log(err)
      setError('Error al cargar la información sobre los ríos del mundo.');
    } finally{
      setCargando(false);
    }

    cargarRios();
}, [])
  
if(cargando){
  return (
    <View style={styles.container}>
      <Text>Cargando ... </Text>
      <ActivityIndicator size='large'/>
    </View>
  );
}

if(error){
  return (
    <View style={styles.container}>
      <Text>{error} </Text>
      <ActivityIndicator size='large'/>
    </View>
  );
}

  
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
