import { useEffect, useState } from "react";
import {
  View,
  Text,
  ScrollView,
  ActivityIndicator,
  StyleSheet,
  Platform,
} from "react-native";


const API_URL = "https://espacio199.com/biblioteca/api.php"; //Se guarda en una constante la URL de la API para poder usarla posteriormente en el fetch 


export default function Descargas() {
  const [libros, setLibros] = useState([]); //se guardará el listado de los libros
  const [cargando, setCargando] = useState(true); //se utiliza para mostrar un indicador de carga mientras se obtienen los datos de la API




  useEffect(() => { //El useEffect se utiliza para cargar los datos de la API cuando el componente se monta por primera vez.
    async function cargarDatos() { 
      try {
        //  /ESCRIBE AQUÍ:aquí va el fetch
        const res = await fetch(API_URL); //se hace una petición a la API utilizando fetch y se espera la respuesta
        const json = await res.json(); //se convierte la respuesta a formato JSON para poder trabajar con ella en JavaScript
        setLibros(json); //Se actualiza el estado de libros con los datos obtenidos de la API
      } catch (error) {
        console.error(error); //en caso de error muestra el error en la consola
      } finally {
        setCargando(false); //cuando se terminan de cargar los datos se actualiza el estado de cargando a false para dejar de mostrar el indicador de carga
      }
    }


    cargarDatos(); 
  }, []);


  useEffect(() => {
    if (Platform.OS === "web" && libros.length > 0) {
      import("../web/graficaDescargas").then(modulo => {
        modulo.pintarGraficaDescargas("grafica-web", libros);
      });
    }
  }, [libros]);


  if (cargando) {
    return <ActivityIndicator size="large" />;
  }

  
  return (
    <ScrollView style={styles.container}>
      <Text style={styles.titulo}>📚 Descargas por libro</Text>

    {
    //  Se hace un map sobre el array de libros para mostrar el título y el total de descargas de cada libro. 
    //Todo se mete dentro de un View y se mapea a través del key: libro.id:libro
    //Metí las descargas dentro de un View para que se mueste "Descargas:" y el número abajo
    libros.map((libro) => (
        <View key={libro.id_libro} style={styles.card}>
          <Text style={styles.libro}>{libro.titulo}</Text>
          <View>
            <Text style={styles.libro}>Descargas:</Text>
            <Text style={styles.descargas}>{libro.total_descargas}</Text>
          </View>
          
        </View>
      ))}   


      {/* GRÁFICA (solo web) */}
      {Platform.OS === "web" && (
        <View nativeID="grafica-web" style={{ marginTop: 40 }} />
      )}
    </ScrollView>
  );
}


const styles = StyleSheet.create({
    //  ESCRIBE AQUÍ:Tienes unos estilos base, pero estás obligado a mejorarlos personalizándolos
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: "#f2f2f2",
  },
  titulo: {
    fontSize: 22,
    fontWeight: "bold",
    marginBottom: 16,
  },
  card: {
    padding: 8,
    marginBottom: 6,
    backgroundColor: "#f2f2f2",
    border: "1px solid #a75858",
    borderRadius: 8,
    flexDirection: "row",
  },
  libro: {
    flex: 1,
    fontSize: 16,
    fontWeight: "600",
  },
  descargas: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#fd450d",
    alignSelf: "center",
  },
  itemTitle: {
      padding: 15,
      marginBottom: 10,
      backgroundColor: '#eee',
      borderRadius: 6,
      flex: 1,
},
});
