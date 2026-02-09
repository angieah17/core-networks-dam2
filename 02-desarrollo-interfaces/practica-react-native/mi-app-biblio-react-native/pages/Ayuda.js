import { View, Text, StyleSheet } from 'react-native';

export default function Ayuda() {
  return (
    <View style={styles.contenedor}>
      <Text style={styles.texto}>Ayuda</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  contenedor: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  texto: { fontSize: 22 }
});
