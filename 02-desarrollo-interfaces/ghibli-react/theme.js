import { MD3LightTheme as PaperDefault } from 'react-native-paper';

const theme = {
  ...PaperDefault,
  roundness: 8,
  colors: {
    ...PaperDefault.colors,
    primary: '#2B7A78',
    secondary: '#FFB703',
    background: '#F5F7FB',
    surface: '#FFFFFF',
    onSurface: '#263238',
    error: '#B00020',
  },
  fonts: {
    ...PaperDefault.fonts,
    regular: { fontFamily: 'JosefinSans_200ExtraLight', fontWeight: '400' },
    medium: { fontFamily: 'JosefinSans_200ExtraLight', fontWeight: '500' },
    light: { fontFamily: 'JosefinSans_100Thin', fontWeight: '300' },
    thin: { fontFamily: 'JosefinSans_100Thin', fontWeight: '100' },
  },
  typography: {
    title: 26,
    titleLarge: 28,
    subtitle: 18,
    body: 14,
    caption: 12,
    lineHeight: 20,
    weightBold: '700',
    weightMedium: '500',
  },
};

export default theme;
