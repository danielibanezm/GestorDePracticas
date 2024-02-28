-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-02-2024 a las 20:40:29
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestor_practicas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `id_alumno` int(11) NOT NULL,
  `id_centro` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `valido` tinyint(1) NOT NULL,
  `cv` longblob NOT NULL,
  `ss` varchar(12) NOT NULL,
  `ciclo` varchar(20) NOT NULL,
  `año` varchar(10) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anexos`
--

CREATE TABLE `anexos` (
  `id_anexo` int(11) NOT NULL,
  `anexo_8` longblob NOT NULL,
  `anexo_2_1` longblob NOT NULL,
  `anexo_2_2` longblob NOT NULL,
  `anexo_3` longblob NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anexos_semanales`
--

CREATE TABLE `anexos_semanales` (
  `id_anexo_semanal` int(11) NOT NULL,
  `seguiminento` longblob NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bolsa_becas`
--

CREATE TABLE `bolsa_becas` (
  `id_bolsa_becas` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bolsa_trabajo`
--

CREATE TABLE `bolsa_trabajo` (
  `id_bolsa_trabajo` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro`
--

CREATE TABLE `centro` (
  `id_centro` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `codigo` varchar(9) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `centro`
--

INSERT INTO `centro` (`id_centro`, `nombre`, `codigo`, `eliminado`) VALUES
(1, 'Alberto32', '14568', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `convenio`
--

CREATE TABLE `convenio` (
  `id_convenio` int(9) NOT NULL,
  `id_empresa` int(9) NOT NULL,
  `id_centro` int(9) NOT NULL,
  `anexo_1` longblob NOT NULL,
  `numero_serie` int(11) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id_empresa` int(11) NOT NULL,
  `CIF` varchar(9) NOT NULL,
  `dueño` varchar(20) NOT NULL,
  `nombre_empresa` varchar(20) NOT NULL,
  `telefono_empresa` varchar(9) NOT NULL,
  `direccion_empresa` varchar(50) NOT NULL,
  `email_empresa` varchar(20) NOT NULL,
  `tutor_empresa` varchar(20) NOT NULL,
  `correo_empresa` varchar(20) NOT NULL,
  `solicita` varchar(20) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intermedia_anexos`
--

CREATE TABLE `intermedia_anexos` (
  `id_anexo_semanal` int(11) NOT NULL,
  `id_anexo` int(11) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `necesidad`
--

CREATE TABLE `necesidad` (
  `id_necesidad` int(9) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `convocatoria` varchar(10) NOT NULL,
  `cantidad_dam` int(9) NOT NULL,
  `cantidad_daw` int(9) NOT NULL,
  `cantidad_asir` int(9) NOT NULL,
  `cantidad_marketin` int(9) NOT NULL,
  `cantidad_finanzas` int(9) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `otros`
--

CREATE TABLE `otros` (
  `id_otros` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `material` varchar(20) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `otros`
--

INSERT INTO `otros` (`id_otros`, `id_usuario`, `material`, `eliminado`) VALUES
(1, 1, '12345', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `practica`
--

CREATE TABLE `practica` (
  `id_practica` int(11) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_anexo` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `inicio` date NOT NULL,
  `final` date NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutor`
--

CREATE TABLE `tutor` (
  `id_tutor` int(11) NOT NULL,
  `id_centro` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tutor`
--

INSERT INTO `tutor` (`id_tutor`, `id_centro`, `nombre`, `eliminado`) VALUES
(1, 1, 'Paco', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `id_centro` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `primer` tinyint(1) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  `eliminado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `id_centro`, `email`, `primer`, `perfil`, `eliminado`) VALUES
(1, 1, 'alberto@correoelectronico.com', 0, 'Admin', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id_alumno`),
  ADD UNIQUE KEY `id_centro` (`id_centro`);

--
-- Indices de la tabla `anexos`
--
ALTER TABLE `anexos`
  ADD PRIMARY KEY (`id_anexo`);

--
-- Indices de la tabla `anexos_semanales`
--
ALTER TABLE `anexos_semanales`
  ADD PRIMARY KEY (`id_anexo_semanal`);

--
-- Indices de la tabla `bolsa_becas`
--
ALTER TABLE `bolsa_becas`
  ADD PRIMARY KEY (`id_bolsa_becas`),
  ADD KEY `id_empresa_becas_fk` (`id_empresa`),
  ADD KEY `id_alumno` (`id_alumno`,`id_empresa`) USING BTREE;

--
-- Indices de la tabla `bolsa_trabajo`
--
ALTER TABLE `bolsa_trabajo`
  ADD PRIMARY KEY (`id_bolsa_trabajo`),
  ADD KEY `id_empresa_trabajo_fk` (`id_empresa`),
  ADD KEY `id_alumno` (`id_alumno`,`id_empresa`) USING BTREE;

--
-- Indices de la tabla `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`id_centro`);

--
-- Indices de la tabla `convenio`
--
ALTER TABLE `convenio`
  ADD PRIMARY KEY (`id_convenio`),
  ADD KEY `id_empresa` (`id_empresa`) USING BTREE,
  ADD KEY `id_centro` (`id_centro`) USING BTREE;

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id_empresa`);

--
-- Indices de la tabla `intermedia_anexos`
--
ALTER TABLE `intermedia_anexos`
  ADD KEY `id_anexo_fk` (`id_anexo`),
  ADD KEY `id_anexo_semanal_fk` (`id_anexo_semanal`);

--
-- Indices de la tabla `necesidad`
--
ALTER TABLE `necesidad`
  ADD PRIMARY KEY (`id_necesidad`),
  ADD KEY `id_empresa` (`id_empresa`) USING BTREE;

--
-- Indices de la tabla `otros`
--
ALTER TABLE `otros`
  ADD PRIMARY KEY (`id_otros`),
  ADD KEY `id_usuario` (`id_usuario`) USING BTREE;

--
-- Indices de la tabla `practica`
--
ALTER TABLE `practica`
  ADD PRIMARY KEY (`id_practica`) USING BTREE,
  ADD KEY `id_anexo_practica_fk` (`id_anexo`),
  ADD KEY `id_empresa_practica_fk` (`id_empresa`),
  ADD KEY `id_alumno` (`id_alumno`,`id_anexo`,`id_empresa`) USING BTREE;

--
-- Indices de la tabla `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`id_tutor`),
  ADD KEY `id_centro_tutores_fk` (`id_centro`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_centro` (`id_centro`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `id_alumno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `anexos`
--
ALTER TABLE `anexos`
  MODIFY `id_anexo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `anexos_semanales`
--
ALTER TABLE `anexos_semanales`
  MODIFY `id_anexo_semanal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bolsa_becas`
--
ALTER TABLE `bolsa_becas`
  MODIFY `id_bolsa_becas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bolsa_trabajo`
--
ALTER TABLE `bolsa_trabajo`
  MODIFY `id_bolsa_trabajo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `centro`
--
ALTER TABLE `centro`
  MODIFY `id_centro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `convenio`
--
ALTER TABLE `convenio`
  MODIFY `id_convenio` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id_empresa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `necesidad`
--
ALTER TABLE `necesidad`
  MODIFY `id_necesidad` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `otros`
--
ALTER TABLE `otros`
  MODIFY `id_otros` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `practica`
--
ALTER TABLE `practica`
  MODIFY `id_practica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tutor`
--
ALTER TABLE `tutor`
  MODIFY `id_tutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `id_centro_fk` FOREIGN KEY (`id_centro`) REFERENCES `centro` (`id_centro`) ON DELETE CASCADE;

--
-- Filtros para la tabla `bolsa_becas`
--
ALTER TABLE `bolsa_becas`
  ADD CONSTRAINT `id_alumno_becas_fk` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_empresa_becas_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `bolsa_trabajo`
--
ALTER TABLE `bolsa_trabajo`
  ADD CONSTRAINT `id_alumno_trabajo_fk` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_empresa_trabajo_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `convenio`
--
ALTER TABLE `convenio`
  ADD CONSTRAINT `id_convenio_centro_fk` FOREIGN KEY (`id_centro`) REFERENCES `centro` (`id_centro`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_convenio_empresa_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `intermedia_anexos`
--
ALTER TABLE `intermedia_anexos`
  ADD CONSTRAINT `id_anexo_fk` FOREIGN KEY (`id_anexo`) REFERENCES `anexos` (`id_anexo`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_anexo_semanal_fk` FOREIGN KEY (`id_anexo_semanal`) REFERENCES `anexos_semanales` (`id_anexo_semanal`) ON DELETE CASCADE;

--
-- Filtros para la tabla `necesidad`
--
ALTER TABLE `necesidad`
  ADD CONSTRAINT `id_empresa_necesidad_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `otros`
--
ALTER TABLE `otros`
  ADD CONSTRAINT `id_usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `practica`
--
ALTER TABLE `practica`
  ADD CONSTRAINT `id_alumno_practica_fk` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_anexo_practica_fk` FOREIGN KEY (`id_anexo`) REFERENCES `anexos` (`id_anexo`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_empresa_practica_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `tutor`
--
ALTER TABLE `tutor`
  ADD CONSTRAINT `id_centro_tutores_fk` FOREIGN KEY (`id_centro`) REFERENCES `centro` (`id_centro`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `id_centro_usuarios_fk` FOREIGN KEY (`id_centro`) REFERENCES `centro` (`id_centro`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
