-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 29-11-2025 a las 18:56:58
-- Versión del servidor: 8.0.43
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `id_libro` int NOT NULL,
  `titulo` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `autor` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nombre_archivo` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`id_libro`, `titulo`, `autor`, `nombre_archivo`) VALUES
(1, 'El Capitán Tormenta II', 'Emilio Salgari', 'capitan-tormenta-el-capitan-tormenta-iiv.2-emilo-salgari.epub'),
(2, 'Cartago en llamas', 'Emilio Salgari', 'cartago-en-llamas-emilio-salgari.epub'),
(3, 'La Galatea', 'Miguel de Cervantes', 'cervantes-la-galatea.epub'),
(4, 'Cinco semanas en globo', 'Julio Verne', 'cinco-semanas-en-globo-verne-julio.epub'),
(5, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 'don-quijote-de-la-mancha-con-notas-miguel-de-cervantes-saavedra.epub'),
(6, 'El corazón delator', 'Edgar Allan Poe', 'edgar-allan-poe-el-corazon-delator.epub'),
(7, 'El escarabajo de oro', 'Edgar Allan Poe', 'edgar-allan-poe-el-escarabajo-de-oro.epub'),
(8, 'El gato negro', 'Edgar Allan Poe', 'edgar-allan-poe-el-gato-negro.epub'),
(9, 'El pozo y el péndulo', 'Edgar Allan Poe', 'edgar-allan-poe-el-pozo-y-el-pendulo.epub'),
(10, 'El Capitán del Djuma', 'Emilio Salgari', 'el-capitan-del-djuma-emilio-salgari.epub'),
(11, 'El Chancellor', 'Julio Verne', 'el-chancellor-julio-verne.epub'),
(12, 'El corazón de las tinieblas', 'Joseph Conrad', 'el-corazon-de-las-tinieblas-joseph-conrad.epub'),
(13, 'El corsario negro', 'Emilio Salgari', 'el-corsario-negro-emilio-salgari.epub'),
(14, 'El Eterno Adán', 'Julio Verne', 'el-eterno-adan-julio-verne.epub'),
(15, 'El faro del fin del mundo', 'Julio Verne', 'el-faro-del-fin-del-mundo-julio-verne.epub'),
(16, 'El león de Damasco', 'Emilio Salgari', 'el-leon-de-damasco-emilio-salgari.epub'),
(17, 'Isla Misteriosa', 'Julio Verne', 'isla-misteriosa-verne-julio.epub'),
(18, 'Ivanhoe', 'Walter Scott', 'ivanhoe-walter-scott.epub'),
(19, 'Lord Jim', 'Joseph Conrad', 'lord-jim-joseph-conrad.epub');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`id_libro`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `id_libro` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
