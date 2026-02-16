import { View, StatusBar } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';//Es el componente que inicializa, controla y mantiene el estado de toda la navegación.
import { Provider as PaperProvider } from 'react-native-paper';
import theme from './theme';
import { createNativeStackNavigator } from '@react-navigation/native-stack' ; //se deben descargar npm install '@react-navigation/native-stack' y la anterior
/*createNativeStackNavigator es una función que crea un navegador de tipo “stack” usando transiciones nativas de iOS y Android.

Es el sistema que permite movernos entre pantallas una encima de otra, como si fueran cartas apiladas.*/
import { useFonts, JosefinSans_100Thin, JosefinSans_200ExtraLight, } from '@expo-google-fonts/josefin-sans';

import MenuAbajo from './componentes/MenuAbajo';
import Peliculas from './pages/Peliculas';
import Ayuda from './pages/Ayuda';
import PeliculaDetalle from './pages/PeliculaDetalle';
import Creditos from './pages/Creditos';


export default function App() {
  const [fontsLoaded] = useFonts({
    JosefinSans_100Thin,
    JosefinSans_200ExtraLight,
  });

  if (!fontsLoaded) {
    return null; // o un componente de carga
  }

  return (
    <PaperProvider theme={theme}>
      <StatusBar barStyle="dark-content" backgroundColor={theme.colors.background} />
      <NavigationContainer>
        <Principal />
      </NavigationContainer>
    </PaperProvider>
  );
}

function Principal() {
  const Estac = createNativeStackNavigator();

  return (
    <View style={{ flex: 1 }}>

      {/* Contenido que cambia */}
      <View style={{ flex: 1 }}>
        <Estac.Navigator screenOptions={{ headerShown: false }}>
          <Estac.Screen name="Peliculas" component={Peliculas} />
          <Estac.Screen name="PeliculaDetalle" component={PeliculaDetalle} />
          <Estac.Screen name="Creditos" component={Creditos} />
          <Estac.Screen name="Ayuda" component={Ayuda} />
  
        </Estac.Navigator>
      </View>

      {/* Menú fijo abajo */}
      <MenuAbajo />

    </View>
  );
}
