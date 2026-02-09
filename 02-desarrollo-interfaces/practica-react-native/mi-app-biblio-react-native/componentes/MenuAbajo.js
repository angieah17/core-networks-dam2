import { View, Button, StyleSheet, Pressable, Text } from 'react-native';
import { useNavigation } from '@react-navigation/native';
 import { Home, HelpCircle} from "lucide-react-native"; 



export default function MenuAbajo() {
  const navigation = useNavigation();//Da acceso a la navegación desde cualquier componente


  return (
    <View style={styles.menu}>
      <Pressable onPress={() => navigation.navigate('Libros')}><Home /> </Pressable>
      <Pressable onPress={() => navigation.navigate('Ayuda')}> <HelpCircle/> </Pressable>
    
     


    </View>
  );
}

const styles = StyleSheet.create({
  menu: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    paddingVertical: 10,
    backgroundColor: 'transparent',

  }
});
