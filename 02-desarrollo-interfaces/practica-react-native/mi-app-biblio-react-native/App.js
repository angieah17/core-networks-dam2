 import { View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';//Es el componente que inicializa, controla y mantiene el estado de toda la navegación.
import { createNativeStackNavigator } from '@react-navigation/native-stack' ; //se deben descargar npm install '@react-navigation/native-stack' y la anterior
/*createNativeStackNavigator es una función que crea un navegador de tipo “stack” usando transiciones nativas de iOS y Android.

Es el sistema que permite movernos entre pantallas una encima de otra, como si fueran cartas apiladas.*/

import MenuAbajo from './componentes/MenuAbajo';
import Libros from './pages/Libros';
import Ayuda from './pages/Ayuda';
import Descargas from './pages/Descargas';


export default function App() {
  return (
    <NavigationContainer>
      <Principal />
    </NavigationContainer>
  );
}

function Principal() {
  const Estac = createNativeStackNavigator();

  return (
    <View style={{ flex: 1 }}>

      {/* Contenido que cambia */}
      <View style={{ flex: 1 }}>
        <Estac.Navigator screenOptions={{ headerShown: false }}>
          <Estac.Screen name="Libros" component={Libros} />
          <Estac.Screen name="Ayuda" component={Ayuda} />
          <Estac.Screen name="Descargas" component={Descargas} />
  
        </Estac.Navigator>
      </View>

      {/* Menú fijo abajo */}
      <MenuAbajo />

    </View>
  );
}
