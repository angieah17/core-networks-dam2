import { useEffect, useState } from "react";
import {
  Text,
  ScrollView,
  TouchableOpacity,
  ActivityIndicator,
  StyleSheet,
  Image,
  Dimensions,
} from "react-native";

const API_BASE = "https://akabab.github.io/starwars-api/api";
const { width } = Dimensions.get("window");

/* En general todos los mensajes (errores, cargando, instrucciones) permiten que el usuario pueda tener conocimiento de lo que 
está sucediendo en la aplicación y de esta manera mejorar la experiencia de usuario */

export default function App() {
  const styles = getStyles();
  //La aplicación maneja los siguientes estados:
  const [imgSize, setImgSize] = useState(null); //permite ajustar el tamaño de la imagen si es que existe utiliza en Dimensions
  const [lista, setLista] = useState([]); //permite almacenar y recorrer el listado de personajes
  const [personajeSeleccionado, setPersonajeSeleccionado] = useState(null); //si hay un personaje seleccionado se muestran los detalles de ese personaje
  const [cargando, setCargando] = useState(true); //permite avisar al usuario que se encuentra cagando los datos
  const [error, setError] = useState(null); //si hay un error muestra en pantalla al usuario el error correspondiente

  // Cargar listado
  useEffect(() => {
    async function cargarLista() {
      //con cada una de estas funciones se realiza la petición asíncrona, permite que la app siga funcionando mientras llegan los datos
      try {
        setCargando(true); //mientras se cargan los datos se muestra la información de cargando
        const res = await fetch(`${API_BASE}/all.json`); //fetch hace la petición y await evita que se bloquee la pantalla
        const json = await res.json();
        setLista(json);
      } catch {
        setError("Error cargando la lista de personajes. Inténtalo de nuevo."); //se muestra este mensaje cuando haya un error cargando la lista de personajes.
      } finally {
        setCargando(false);
      }
    }

    cargarLista();
  }, []);

  // Calcular tamaño real de la imagen
  useEffect(() => {
    if (!personajeSeleccionado?.image) return;

    Image.getSize(
      personajeSeleccionado.image,
      (w, h) => {
        const ratio = width / w;
        setImgSize({
          width: width * 0.5,
          height: h * ratio * 0.5,
          alignSelf: "center",
          marginVertical: 15,
        });
      },
      () => {
        setImgSize({
          width: width * 0.9,
          height: width * 0.6,
          alignSelf: "center",
          marginVertical: 15,
        });
      },
    );
  }, [personajeSeleccionado]);

  // Cargar detalle
  async function cargarPersonaje(id) {
    //con cada una de estas funciones se realiza la petición asíncrona
    try {
      setCargando(true);
      const res = await fetch(`${API_BASE}/id/${id}.json`);
      const json = await res.json();
      setPersonajeSeleccionado(json);
    } catch {
      setError("Error cargando el personaje. Inténtalo de nuevo."); //se muestra este mensaje cuando hay un error cargando los detalles del personaje
    } finally {
      setCargando(false);
    }
  }

  if (cargando) {
    //se muestra este mensaje mientras cargan los datos, avisando al usuario
    return (
      <ScrollView contentContainerStyle={styles.loading}>
        <ActivityIndicator size="large" />
        <Text style={styles.loadingText}>Cargando, espera un momento...</Text>
      </ScrollView>
    );
  }

  if (error) {
    //se muestra un mensaje personalizado de acuerdo al tipo de error generado
    return (
      <ScrollView contentContainerStyle={styles.error}>
        <Text style={styles.errorTitle}>¡Ups!</Text>
        <Text style={styles.errorText}>{error}</Text>
      </ScrollView>
    );
  }

  // Listado
  if (!personajeSeleccionado) {
    return (
      <ScrollView style={styles.screen}>
        <Text style={styles.title}>Personajes de Star Wars</Text>
        <Text style={styles.instruction}>
          Puedes hacer click en cada uno para acceder a más detalles.
        </Text>

        <Text style={styles.counter}>
          Hay un total de: {lista.length} personajes disponibles.
        </Text>

        {lista.map((personaje) => (
          <TouchableOpacity
            key={personaje.id}
            onPress={() => cargarPersonaje(personaje.id)}
            style={styles.item}
          >
            <Text style={styles.itemTitle}>{personaje.name}</Text>
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
          setPersonajeSeleccionado(null);
          setImgSize(null);
        }}
        style={styles.back}
      >
        <Text style={styles.backText}>← Volver</Text>{" "}
        {/* Facilitar la navegación al usuario */}
      </TouchableOpacity>

      <Text style={styles.detailTitle}>{personajeSeleccionado.name}</Text>

      {imgSize && (
        <Image
          source={{ uri: personajeSeleccionado.image }}
          style={imgSize}
          resizeMode="contain"
        />
      )}

      <Text style={styles.description}>
        Altura: {personajeSeleccionado.height} cm
      </Text>

      <Text style={styles.description}>
        Género: {personajeSeleccionado.gender}
      </Text>
    </ScrollView>
  );
}

// Función de estilos
const getStyles = () =>
  /* Se parte de la paleta de colores de Star Wars:
  -Contraste de colores que faciliten la lectura. 
  -Los nombres de cada personaje están dentro de contenedores con estilos similares para que el usuario identifique que se tratan del mismo tipo.
  -El tamaño de letra maneje un rango 16 a 25 aproximadamente para facilitar la legibilidad. 
  -El error decidí un color rojo para el primer aviso que a primera vista permita identificar que se trata de un fallo, pero la descripción sí la realizo con color negro que facilite la lectura.
  -El tamaño de la imagen lo reconfiguré de tal manera que no ocupe el total de la pantalla, sino que aparezca centrada en un tamaño agradable en el móvil.
  -He utilizado el color amarillo y el tamaño del nombre cuando se abre el detalle de cada personaje para que llame la atención del usuario, en cambio los detalles, en un color más neutro.

   */
  StyleSheet.create({
    screen: {
      padding: 20,
      backgroundColor: "#191315",
    },

    title: {
      fontSize: 25,
      fontWeight: "bold",
      marginBottom: 20,
      textAlign: "center",
      color: "#F2D230",
    },

    counter: {
      fontSize: 16,
      textAlign: "center",
      marginBottom: 20,
      color: "white",
    },

    item: {
      padding: 15,
      marginBottom: 10,
      backgroundColor: "#eee",
      borderRadius: 6,
    },

    itemTitle: {
      fontSize: 16,
      fontWeight: "bold",
      color: "#261E21",
    },

    back: {
      marginBottom: 20,
    },

    backText: {
      color: "#A6943C",
      fontSize: 18,
    },

    detailTitle: {
      fontSize: 24,
      fontWeight: "bold",
      marginBottom: 10,
      color: "#F2D230",
      textAlign: "center",
    },

    description: {
      marginTop: 10,
      textAlign: "center",
      color: "#eeede7",
      fontSize: 20,
    },

    region: {
      marginTop: 10,
    },
    loading: {
      flex: 1,
      justifyContent: "center",
      alignItems: "center",
      paddingTop: 50,
    },

    loadingText: {
      marginTop: 10,
      fontSize: 16,
    },
    error: {
      flex: 1,
      justifyContent: "center",
      alignItems: "center",
      padding: 20,
    },

    errorTitle: {
      fontSize: 22,
      fontWeight: "bold",
      marginBottom: 10,
      color: "red",
    },

    errorText: {
      fontSize: 16,
      textAlign: "center",
    },
    instruction: {
      fontSize: 16,
      textAlign: "center",
      color: "#eeede7",
    },
  });
