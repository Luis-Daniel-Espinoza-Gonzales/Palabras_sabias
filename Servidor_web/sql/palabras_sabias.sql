-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-11-2025 a las 15:29:40
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `palabras_sabias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_obra` int(11) NOT NULL,
  `texto` text DEFAULT NULL,
  `fecha` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`id`, `id_usuario`, `id_obra`, `texto`, `fecha`) VALUES
(1, 1, 1, 'muy buena 10 de 10 ', '2025-10-31 20:41:11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `id` int(11) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `fecha_evento` datetime NOT NULL,
  `ubicacion` varchar(250) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `organizador` int(11) NOT NULL,
  `fecha_creacion` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`id`, `titulo`, `fecha_evento`, `ubicacion`, `descripcion`, `organizador`, `fecha_creacion`) VALUES
(1, 'Feria de libro', '2025-11-11 20:41:18', 'Mar de plata', 'Comparte experiencia unica junto con otros amantes de la literatura', 2, '2025-10-31 20:41:18'),
(2, 'Comic-Con Argentina', '2025-12-05 11:06:25', 'Av. Costanera Rafael Obligado 1221, C1425 Cdad. Autónoma de Buenos Aires', '¡Atención, amantes de la cultura pop! En el mes de diciembre llega Argentina Comic-Con 2025. Preparate para sumergirte en un mundo lleno de fantasía, cine, series, cosplay, cómics, música, gaming, celebridades internacionales y mucho más.\r\n\r\n¡Reservá la fecha! La convención tendrá lugar los días 5, 6, y 7 de DICIEMBRE en el Centro Costa Salguero. Será un fin de semana lleno de emociones, encuentros con tus ídolos, paneles fascinantes y la oportunidad de explorar los universos de tus franquicias favoritas.', 2, '2013-12-05 11:06:06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `formatos`
--

CREATE TABLE `formatos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `formatos`
--

INSERT INTO `formatos` (`id`, `nombre`) VALUES
(1, 'Poema'),
(2, 'Novela'),
(3, 'Novela Visual'),
(4, 'Comic/Historieta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generos`
--

CREATE TABLE `generos` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `generos`
--

INSERT INTO `generos` (`id`, `name`) VALUES
(1, 'Aventura'),
(2, 'Fantasia'),
(3, 'Policial'),
(4, 'Romance'),
(5, 'Distopía'),
(6, 'Terror'),
(7, 'Accion'),
(8, 'Ciencia ficción'),
(9, 'Bélico'),
(10, 'Ciencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obras`
--

CREATE TABLE `obras` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `synopsis` text DEFAULT NULL,
  `id_autor` int(11) NOT NULL,
  `id_genero` int(11) NOT NULL,
  `id_formato` int(11) NOT NULL,
  `fecha_Publicacion` datetime DEFAULT NULL,
  `promedio_puntaje` decimal(3,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `obras`
--

INSERT INTO `obras` (`id`, `title`, `synopsis`, `id_autor`, `id_genero`, `id_formato`, `fecha_Publicacion`, `promedio_puntaje`) VALUES
(1, 'Mr Mercedes', 'Mr. Mercedes es la historia de una guerra entre el Bien y el Mal. Un retrato inolvidable de la mente de un asesino obsesionado y demente.De Stephen King, maestro del terror y el suspenso.', 3, 3, 2, '2015-10-15 20:33:53', 0.00),
(2, 'La suiza', 'Historias de la suiza', 2, 3, 2, '2025-10-15 20:33:53', 0.00),
(3, 'It', 'Una entidad maligna que adopta la forma de un payaso aterroriza a un pueblo.', 3, 6, 2, '1986-09-15 00:00:00', 0.00),
(4, 'El Resplandor', 'Un hotel aislado y fuerzas malignas que enloquecen a un hombre.', 3, 6, 2, '1977-01-28 00:00:00', 0.00),
(5, 'Carrie', 'Una joven con poderes psíquicos toma venganza tras años de abuso.', 3, 6, 2, '1974-04-05 00:00:00', 0.00),
(6, 'Misery', 'Un escritor secuestrado por su fan número uno.', 3, 6, 2, '1987-06-08 00:00:00', 0.00),
(7, 'Cementerio de Mascotas', 'Un cementerio capaz de devolver la vida, con consecuencias horribles.', 3, 6, 2, '1983-11-14 00:00:00', 0.00),
(8, 'Frankenstein', 'Un científico crea un ser vivo que terminará atormentándolo.', 4, 6, 2, '1818-01-01 00:00:00', 0.00),
(9, 'Valperga', 'Novela histórica sobre el ascenso y caída de Castruccio Castracani.', 4, 7, 2, '1823-01-01 00:00:00', 0.00),
(10, 'El último hombre', 'Una plaga destruye a la humanidad, narrada por el último sobreviviente.', 4, 5, 2, '1826-01-01 00:00:00', 0.00),
(11, 'Mathilda', 'Historia trágica de amor obsesivo entre padre e hija.', 4, 3, 2, '1959-01-01 00:00:00', 0.00),
(12, 'Lodore', 'Drama social sobre la vida familiar y las convenciones sociales.', 4, 3, 2, '1835-01-01 00:00:00', 0.00),
(13, 'El Eternauta', 'Una invasión alienígena bajo una nevada mortal en Buenos Aires.', 5, 8, 1, '1957-09-04 00:00:00', 0.00),
(14, 'Mort Cinder', 'Un inmortal que revive en distintos periodos históricos.', 5, 8, 1, '1962-01-20 00:00:00', 0.00),
(15, 'Sargento Kirk', 'Un soldado que defiende a los pueblos originarios del abuso militar.', 5, 2, 1, '1953-01-01 00:00:00', 0.00),
(16, 'Ernie Pike', 'Relatos humanos situados en la Segunda Guerra Mundial.', 5, 2, 1, '1957-01-01 00:00:00', 0.00),
(17, 'El Indio Suárez', 'Aventuras en la frontera argentina con enfoque social.', 5, 2, 1, '1955-01-01 00:00:00', 0.00),
(18, 'El Hobbit', 'La aventura de Bilbo Bolsón para recuperar un tesoro custodiado por un dragón.', 6, 1, 2, '1937-09-21 00:00:00', 0.00),
(19, 'El Señor de los Anillos: La Comunidad del Anillo', 'Comienza la travesía para destruir el anillo único.', 6, 1, 2, '1954-07-29 00:00:00', 0.00),
(20, 'El Señor de los Anillos: Las Dos Torres', 'La comunidad se divide y la guerra se intensifica.', 6, 1, 2, '1954-11-11 00:00:00', 0.00),
(21, 'El Señor de los Anillos: El Retorno del Rey', 'La batalla final por la Tierra Media.', 6, 1, 2, '1955-10-20 00:00:00', 0.00),
(22, 'El Silmarillion', 'El origen del universo de la Tierra Media y sus primeras edades.', 6, 1, 2, '1977-09-15 00:00:00', 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntuaciones`
--

CREATE TABLE `puntuaciones` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_obra` int(11) NOT NULL,
  `puntuacion` int(11) DEFAULT NULL CHECK (`puntuacion` between 1 and 10),
  `fecha` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `puntuaciones`
--

INSERT INTO `puntuaciones` (`id`, `id_usuario`, `id_obra`, `puntuacion`, `fecha`) VALUES
(1, 1, 2, 1, '2025-10-31 20:39:20'),
(2, 3, 1, 5, '2025-10-31 20:40:07'),
(4, 3, 2, 1, '2025-10-31 20:40:22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registration_day` datetime DEFAULT current_timestamp(),
  `biography` text DEFAULT NULL,
  `role` enum('user','admin') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `username`, `email`, `password`, `registration_day`, `biography`, `role`) VALUES
(1, 'cris', 'crisabuhadba@gmail.com', 'cris123', '2025-10-31 20:29:10', 'Me gusta la sopa', 'admin'),
(2, 'fulano', 'fulano@gmail.com', 'fulano', '2025-10-31 20:32:11', 'ola', 'user'),
(3, 'stephen king', 'stephen@gmail.com', 'stephen23', '2025-10-31 20:32:11', 'prolífico autor estadounidense de terror y otros géneros, nacido en Portland, Maine, el 21 de septiembre de 1947. Tras una infancia con dificultades y diversos trabajos, logró el éxito con su primera novela, Carrie, en 1974.', 'user'),
(4, 'Mary Shelley', 'frankie@gmail.com', 'mary', '1797-06-30 00:00:00', 'Mary Wollstonecraft Shelley (de soltera, Godwin; Londres, 30 de agosto de 1797-Londres, 1 de febrero de 1851) fue una escritora,[2]​ dramaturga, ensayista y biógrafa británica[3]​ conocida principalmente por ser la autora de la novela gótica Frankenstein o el moderno Prometeo (1818),[4]​ considerada la primera novela de ciencia ficción moderna y que logra inaugurar el género. También editó y promocionó las obras de su esposo, el poeta y filósofo romántico Percy Bysshe Shelley.[5]​ Su padre fue el filósofo político William Godwin y su madre la filósofa Mary Wollstonecraft,[6]​ escritora del libro fundacional del feminismo La Vindicación de los Derechos de la Mujer.', 'user'),
(5, 'Héctor Germán Oesterheld', 'hecotr@gmail.com', 'hector', '1977-07-24 00:00:00', 'Héctor Germán Oesterheld ​ fue un guionista de historietas y escritor argentino, a menudo citado como HGO. Escribió numerosos relatos breves de ciencia ficción y novelas, y publicó en revistas como Misterix, Hora Cero y Frontera.', 'user'),
(6, 'J. R. R. Tolkien', 'tolken@gmail.com', 'tolken', '1892-01-03 11:14:23', 'J. R. R. Tolkien, una biografía es una obra escrita por Humphrey Carpenter sobre la vida del escritor británico, poeta, filólogo y profesor universitario J. R. R. Tolkien, conocido principalmente por ser el autor de las obras clásicas de la alta fantasía El hobbit y El Señor de los Anillos.', 'user');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_obra` (`id_obra`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `organizador` (`organizador`);

--
-- Indices de la tabla `formatos`
--
ALTER TABLE `formatos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `generos`
--
ALTER TABLE `generos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `obras`
--
ALTER TABLE `obras`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_autor` (`id_autor`),
  ADD KEY `id_genero` (`id_genero`),
  ADD KEY `id_formato` (`id_formato`);

--
-- Indices de la tabla `puntuaciones`
--
ALTER TABLE `puntuaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_obra` (`id_obra`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`id_obra`) REFERENCES `obras` (`id`);

--
-- Filtros para la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD CONSTRAINT `eventos_ibfk_1` FOREIGN KEY (`organizador`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `obras`
--
ALTER TABLE `obras`
  ADD CONSTRAINT `obras_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `obras_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `generos` (`id`),
  ADD CONSTRAINT `obras_ibfk_3` FOREIGN KEY (`id_formato`) REFERENCES `formatos` (`id`);

--
-- Filtros para la tabla `puntuaciones`
--
ALTER TABLE `puntuaciones`
  ADD CONSTRAINT `puntuaciones_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`),
  ADD CONSTRAINT `puntuaciones_ibfk_2` FOREIGN KEY (`id_obra`) REFERENCES `obras` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
