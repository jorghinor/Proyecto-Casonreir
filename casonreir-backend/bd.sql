-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generaci�n: 29-05-2014 a las 18:02:47
-- Versi�n del servidor: 5.0.51
-- Versi�n de PHP: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `casonreir1`
--
-- CREATE DATABASE `casonreir` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
-- USE `casonreir`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE IF NOT EXISTS `actividad` (
  `id_actividad` int(11) NOT NULL auto_increment,
  `id_tipo_act` int(11) default NULL,
  `id_cea` int(11) default NULL,
  `nombre_actividad` varchar(250) default NULL,
  `detalle_actividad` text,
  `ini_actividad` date default NULL,
  `fin_actividad` date default NULL,
  `estado_actividad` varchar(8) default NULL,
  `name1_actividad` varchar(255) default NULL,
  `name2_actividad` varchar(255) default NULL,
  PRIMARY KEY  (`id_actividad`),
  KEY `id_cea_idx` (`id_cea`),
  KEY `id_tipo_act_idx` (`id_tipo_act`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=8192 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`id_actividad`, `id_tipo_act`, `id_cea`, `nombre_actividad`, `detalle_actividad`, `ini_actividad`, `fin_actividad`, `estado_actividad`, `name1_actividad`, `name2_actividad`) VALUES
(1, NULL, NULL, 'paseo', '<p>\r\n	los siguientes dias seran de agasajo alas mamdrres</p>\r\n', '2013-05-01', '2013-05-10', 'ACTIVO', '', ''),
(2, NULL, NULL, 'excursion', '<p>\r\n	sera un dia de fortalecimiento espirutual</p>\r\n', '2013-01-26', '2013-01-27', 'ACTIVO', '', ''),
(3, 6, 1, 'taller', '<p>\r\n	los talleres de para los estudiantes seran de 3&ordm; a&nbsp; 6&ordm; de primaria</p>\r\n', '2013-07-02', '2013-07-04', 'ACTIVO', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_becado`
--

CREATE TABLE IF NOT EXISTS `actividad_becado` (
  `id_ab` int(11) NOT NULL auto_increment,
  `id_becado` int(11) default NULL,
  `id_actividad` int(11) default NULL,
  `fecha_ab` date default NULL,
  `tipo_ab` varchar(15) default NULL,
  `name1_ab` varchar(255) default NULL,
  `name2_ab` varchar(255) default NULL,
  `name3_ab` varchar(255) default NULL,
  PRIMARY KEY  (`id_ab`),
  KEY `id_becado_idx` (`id_becado`),
  KEY `id_actividad_idx` (`id_actividad`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `actividad_becado`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `becado`
--

CREATE TABLE IF NOT EXISTS `becado` (
  `id_becado` int(11) NOT NULL auto_increment,
  `id_inf_pers` int(11) default NULL,
  `num_hermanos` int(11) default NULL,
  `ingreso_becado` date default NULL,
  `salida_becado` date default NULL,
  `estado_becado` varchar(8) default NULL,
  `name1_becado` varchar(255) default NULL,
  `name2_becado` varchar(255) default NULL,
  `name3_becado` varchar(255) default NULL,
  `name4_becado` varchar(255) default NULL,
  PRIMARY KEY  (`id_becado`),
  KEY `id_inf_pers_idx` (`id_inf_pers`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=2048 AUTO_INCREMENT=16 ;

--
-- Volcar la base de datos para la tabla `becado`
--

INSERT INTO `becado` (`id_becado`, `id_inf_pers`, `num_hermanos`, `ingreso_becado`, `salida_becado`, `estado_becado`, `name1_becado`, `name2_becado`, `name3_becado`, `name4_becado`) VALUES
(1, 1, 9, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(2, 2, 3, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(3, 3, 4, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(4, 8, 12, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(5, 10, 5, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(6, 11, 3, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(7, 12, 4, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(8, 13, 8, '0000-00-00', NULL, 'INACTIVO', NULL, NULL, NULL, NULL),
(9, 22, 8, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(10, 23, 3, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(11, 26, 2, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(12, 27, 2, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(13, 28, 3, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(14, 29, 2, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL),
(15, 30, 3, '0000-00-00', NULL, 'ACTIVO', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro_apoyo`
--

CREATE TABLE IF NOT EXISTS `centro_apoyo` (
  `id_cea` int(11) NOT NULL auto_increment,
  `nombre_cea` varchar(250) default NULL,
  `direccion_cea` varchar(250) default NULL,
  `telefono_cea` int(11) default NULL,
  `mision` text,
  `vision` text,
  `objetivo` text,
  `historia` text,
  `name1_cea` varchar(255) default NULL,
  `name2_cea` varchar(255) default NULL,
  `name3_cea` varchar(255) default NULL,
  `name4_cea` varchar(255) default NULL,
  PRIMARY KEY  (`id_cea`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=81920 AUTO_INCREMENT=2 ;

--
-- Volcar la base de datos para la tabla `centro_apoyo`
--

INSERT INTO `centro_apoyo` (`id_cea`, `nombre_cea`, `direccion_cea`, `telefono_cea`, `mision`, `vision`, `objetivo`, `historia`, `name1_cea`, `name2_cea`, `name3_cea`, `name4_cea`) VALUES
(1, 'sonreir', 'Av, blanco galindo Km 8', 45973641, '<h1>\r\n	<strong>Nuestra Misi&oacute;n</strong></h1>\r\n<h1>\r\n	Nuestra misi&oacute;n es seguir a Jesucristo, nuestro Se&ntilde;or y Salvador, trabajando con los pobres y oprimidos para promover la transformaci&oacute;n humana, buscar la justicia y testificar de las buenas nuevas del Reino de Dios.</h1>\r\n', '<div style="clear:both;">\r\n	<h1>\r\n		<strong>Visi&oacute;n </strong>es una organizaci&oacute;n cristiana de ayuda humanitaria, desarrollo e incidencia p&uacute;blica, experta en protecci&oacute;n, dedicada a trabajar con ni&ntilde;os, ni&ntilde;as, sus familias y comunidades para sobreponerse de la pobreza e injusticia sin importar su religi&oacute;n, raza, grupo &eacute;tnico o g&eacute;nero.</h1>\r\n</div>\r\n<p>\r\n	&nbsp;</p>\r\n', '<p>\r\n	&nbsp;</p>\r\n<p>\r\n	El proyecto de Apadrinamiento de ancianos pretende acompa&ntilde;ar a la poblaci&oacute;n envejecida de aquellas comunidades pertenecientes a pa&iacute;ses en los que no existen o son m&iacute;nimas las ayudas sociales a la tercera edad. Muchos de ellos no tienen qui&eacute;n les atienda y se encuentran en situaciones de pobreza, en algunos casos, extrema. El objetivo de esta ayuda es dignificar a la persona de forma que se sienta a&uacute;n parte &uacute;til de su comunidad.</p>\r\n<ul>\r\n	<li>\r\n		<strong>Una ni&ntilde;ez promotora (protagonista) </strong>hacemos &eacute;nfasis en el derecho de la ni&ntilde;ez de participar&nbsp;activamente&nbsp;en la toma de decisiones sobre temas que afecten su vida. Al mencionar</li>\r\n	<li>\r\n		<strong>Una ni&ntilde;ez en una sociedad m&aacute;s justa, </strong>nos inspiramos en el respeto y cumplimiento de los Derechos de la Ni&ntilde;ez.</li>\r\n	<li>\r\n		<strong>Una ni&ntilde;ez en una sociedad m&aacute;s segura, </strong>entendemos que todos y todas debemos trabajar para que la ni&ntilde;ez acceda de forma segura a buena salud, alimentaci&oacute;n, educaci&oacute;n, participaci&oacute;n y protecci&oacute;n.</li>\r\n	<li>\r\n		<strong>Una ni&ntilde;ez protegida, </strong>aspiramos y anhelamos una ni&ntilde;ez libre de toda forma de violencia.</li>\r\n</ul>\r\n', '<p>\r\n	<strong>Nuestros Valores</strong></p>\r\n<p>\r\n	La Confraternidad de la fundacion&nbsp; comparte una idea com&uacute;n que se basa en seis valores centrales. Estos valores son los principios fundamentales que orientan y determinan lo que la organizaci&oacute;n hace.</p>\r\n<ul>\r\n	<li>\r\n		<strong>Somos cristianos:</strong></li>\r\n</ul>\r\n<p style="margin-left:36.0pt;">\r\n	Nos esforzamos por imitar a Jesucristo en su identificaci&oacute;n con los pobres y los oprimidos y en su inter&eacute;s especial por la ni&ntilde;ez.</p>\r\n<ul>\r\n	<li>\r\n		<strong>Estamos comprometidos con los pobres:</strong></li>\r\n</ul>\r\n<p style="margin-left:36.0pt;">\r\n	Servimos a los pueblos m&aacute;s necesitados y promovemos la transformaci&oacute;n de sus condiciones de vida.</p>\r\n<ul>\r\n	<li>\r\n		<strong>Valoramos a las personas:</strong></li>\r\n</ul>\r\n<p style="margin-left:36.0pt;">\r\n	&nbsp;</p>\r\n<p style="margin-left:36.0pt;">\r\n	Consideramos que todas las personas son creadas y amadas por Dios.</p>\r\n<p style="margin-left:36.0pt;">\r\n	&nbsp;</p>\r\n<ul>\r\n	<li>\r\n		<strong>Somos mayordomos:</strong></li>\r\n</ul>\r\n<p style="margin-left:36.0pt;">\r\n	Somos transparentes en nuestro trato con donantes, comunidades, gobiernos y p&uacute;blico en general.</p>\r\n<ul>\r\n	<li>\r\n		<strong>Somos socios:</strong></li>\r\n</ul>\r\n<p style="margin-left:36.0pt;">\r\n	Mantenemos una posici&oacute;n colaboradora y una actitud abierta hacia otras organizaciones humanitarias.</p>\r\n<ul>\r\n	<li>\r\n		<strong>Somos sensibles:</strong></li>\r\n</ul>\r\n<p style="margin-left:36.0pt;">\r\n	Ante carencias sociales y econ&oacute;micas complejas, con ra&iacute;ces profundas que exigen un desarrollo sostenible y a largo plazo.</p>\r\n', '', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro_salud`
--

CREATE TABLE IF NOT EXISTS `centro_salud` (
  `id_centro_salud` int(11) NOT NULL auto_increment,
  `id_tipo_cs` int(11) default NULL,
  `nombre_cs` varchar(200) default NULL,
  `direccion_cs` varchar(255) default NULL,
  `telefono_cs` varchar(70) default NULL,
  `observacion_cs` text,
  `estado_cs` varchar(8) default NULL,
  `name1_cs` varchar(200) default NULL,
  `name2_cs` varchar(200) default NULL,
  `name3_cs` varchar(200) default NULL,
  PRIMARY KEY  (`id_centro_salud`),
  KEY `id_tipo_cs_idx` (`id_tipo_cs`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `centro_salud`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `donacion`
--

CREATE TABLE IF NOT EXISTS `donacion` (
  `id_donacion` int(11) NOT NULL auto_increment,
  `id_inst` int(11) default NULL,
  `id_cea` int(11) NOT NULL,
  `id_padrino` int(11) default NULL,
  `id_tipod` int(11) default NULL,
  `nombre_d` varchar(150) default NULL,
  `fecha_d` date default NULL,
  `cantidad_d` float(18,2) default NULL,
  `name1_d` varchar(255) default NULL,
  `name2_d` varchar(255) default NULL,
  `name3_d` varchar(255) default NULL,
  PRIMARY KEY  (`id_donacion`),
  KEY `id_padrino_idx` (`id_padrino`),
  KEY `id_cea_idx` (`id_cea`),
  KEY `id_tipod_idx` (`id_tipod`),
  KEY `id_inst_idx` (`id_inst`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=2048 AUTO_INCREMENT=19 ;

--
-- Volcar la base de datos para la tabla `donacion`
--

INSERT INTO `donacion` (`id_donacion`, `id_inst`, `id_cea`, `id_padrino`, `id_tipod`, `nombre_d`, `fecha_d`, `cantidad_d`, `name1_d`, `name2_d`, `name3_d`) VALUES
(1, 4, 1, NULL, 3, 'poleras y cortos', '2013-06-11', 100.00, '', '', ''),
(2, 3, 1, NULL, 5, 'zapatos', '2013-06-02', 1000.00, '', '', ''),
(3, 5, 1, NULL, 3, 'pantalones de vestir', '2013-05-09', 5000.00, '', '', ''),
(4, 2, 1, NULL, NULL, 'mandiles para mamas', '2013-06-27', 7000.00, '', '', ''),
(5, 5, 1, NULL, NULL, 'frazadas', '2013-06-20', 2000.00, '', '', ''),
(6, 1, 1, NULL, NULL, 'viveres (arroz, azucar, aceite, lenteja', '2013-07-18', 3000.00, '', '', ''),
(7, 4, 1, NULL, NULL, 'material de limpieza', '2013-06-20', 50000.00, '', '', ''),
(8, 4, 1, NULL, 2, 'leche en lata', '2013-06-19', 1000.00, '', '', ''),
(9, NULL, 1, 3, 13, 'regalo especial por su cumplea?os', '2013-07-02', 1.00, '', '', ''),
(10, NULL, 1, 1, 13, 'regalo  de fin de a?o', '2013-07-09', 2.00, '', '', ''),
(11, NULL, 1, 2, 13, 'juguetes por su cumplea?os', '2013-07-01', 5.00, '', '', ''),
(12, NULL, 1, 2, 13, 'por agasajo a la dia de la madre', '2013-07-26', 3.00, '', '', ''),
(13, NULL, 1, 4, 13, 'regalo especial por su cumplea?os', '2013-07-18', 2.00, '', '', ''),
(14, NULL, 1, 3, 13, 'regalo especial por su cumplea?os', '2013-07-09', 4.00, '', '', ''),
(15, NULL, 1, 2, 13, 'regalo', '2013-07-10', 1.00, '', '', ''),
(16, NULL, 1, 1, 13, 'regalo', '2013-07-17', 2.00, '', '', ''),
(17, NULL, 1, 2, 13, 'regalo', '2013-07-22', 5.00, '', '', ''),
(18, NULL, 1, 1, 13, 'poleras', '2013-07-24', 2.00, '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `donacion_becado`
--

CREATE TABLE IF NOT EXISTS `donacion_becado` (
  `id_donacion` int(11) NOT NULL default '0',
  `id_becado` int(11) NOT NULL default '0',
  `fecha_db` date default NULL,
  `cantidad_db` float(18,2) default NULL,
  `observacion_db` text,
  `name1_db` varchar(255) default NULL,
  `name2_db` varchar(255) default NULL,
  `name3_db` varchar(255) default NULL,
  PRIMARY KEY  (`id_donacion`,`id_becado`),
  KEY `donacion_becado_id_becado_becado_id_becado` (`id_becado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=630;

--
-- Volcar la base de datos para la tabla `donacion_becado`
--

INSERT INTO `donacion_becado` (`id_donacion`, `id_becado`, `fecha_db`, `cantidad_db`, `observacion_db`, `name1_db`, `name2_db`, `name3_db`) VALUES
(1, 1, '2013-06-30', 14.29, '', NULL, NULL, NULL),
(1, 2, '2013-06-30', 14.29, '', NULL, NULL, NULL),
(1, 3, '2013-06-30', 14.29, '', NULL, NULL, NULL),
(1, 4, '2013-06-30', 14.29, '', NULL, NULL, NULL),
(1, 6, '2013-06-30', 14.29, '', NULL, NULL, NULL),
(1, 7, '2013-06-30', 14.29, '', NULL, NULL, NULL),
(2, 1, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(2, 2, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(2, 3, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(2, 4, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(2, 5, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(2, 6, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(2, 7, '2013-06-30', 142.86, '', NULL, NULL, NULL),
(4, 1, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(4, 2, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(4, 3, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(4, 4, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(4, 5, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(4, 6, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(4, 7, '2013-07-04', 1000.00, '', NULL, NULL, NULL),
(5, 1, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(5, 2, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(5, 3, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(5, 4, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(5, 5, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(5, 6, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(5, 7, '2013-06-30', 285.71, '', NULL, NULL, NULL),
(7, 1, '2013-06-30', 7142.86, '', NULL, NULL, NULL),
(7, 2, '2013-06-30', 7142.86, '', NULL, NULL, NULL),
(7, 3, '2013-06-30', 7142.86, '', NULL, NULL, NULL),
(7, 5, '2013-06-30', 7142.86, 'no se le ortgo porque falta carta de agraecimiento ayuda', NULL, NULL, NULL),
(7, 6, '2013-06-30', 7142.86, '', NULL, NULL, NULL),
(7, 7, '2013-06-30', 7142.86, '', NULL, NULL, NULL),
(9, 5, '2013-07-03', 0.14, '', NULL, NULL, NULL),
(12, 1, '2013-07-03', 0.43, '', NULL, NULL, NULL),
(12, 3, '2013-07-03', 0.43, '', NULL, NULL, NULL),
(12, 5, '2013-07-03', 0.43, '', NULL, NULL, NULL),
(13, 3, '2013-07-03', 0.29, '', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `donante`
--

CREATE TABLE IF NOT EXISTS `donante` (
  `id_don` int(11) NOT NULL auto_increment,
  `id_inf_pers` int(11) default NULL,
  `tipo_don` varchar(100) default NULL,
  `estado_don` varchar(8) default NULL,
  PRIMARY KEY  (`id_don`),
  KEY `id_inf_pers_idx` (`id_inf_pers`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `donante`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargado`
--

CREATE TABLE IF NOT EXISTS `encargado` (
  `id_encargado` int(11) NOT NULL auto_increment,
  `id_cea` int(11) default NULL,
  `id_inf_pers` int(11) default NULL,
  `cargo_enc` varchar(80) NOT NULL,
  `ini_enc` date NOT NULL,
  `fin_enc` date default NULL,
  `estado_enc` varchar(8) default NULL,
  `observacion_enc` text,
  `name1_enc` varchar(255) default NULL,
  `name2_enc` varchar(255) default NULL,
  `name3_enc` varchar(255) default NULL,
  PRIMARY KEY  (`id_encargado`),
  KEY `id_inf_pers_idx` (`id_inf_pers`),
  KEY `id_cea_idx` (`id_cea`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 AUTO_INCREMENT=11 ;

--
-- Volcar la base de datos para la tabla `encargado`
--

INSERT INTO `encargado` (`id_encargado`, `id_cea`, `id_inf_pers`, `cargo_enc`, `ini_enc`, `fin_enc`, `estado_enc`, `observacion_enc`, `name1_enc`, `name2_enc`, `name3_enc`) VALUES
(2, 1, 6, 'educador', '2013-05-20', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(3, 1, 14, 'administrador', '2013-07-03', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(4, 1, 16, 'representante', '2013-07-03', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(5, 1, 17, 'educadora', '2013-07-03', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(6, 1, 33, 'encargado', '2014-05-24', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(7, 1, 42, 'encargado', '2014-05-24', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(8, 1, 43, '', '2014-05-24', NULL, 'ACTIVO', '', NULL, NULL, NULL),
(9, 1, 44, 'Encargado', '2014-05-26', NULL, 'Encargad', '', NULL, NULL, NULL),
(10, 1, 45, 'Encargado', '2014-05-26', NULL, 'Encargad', '', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargado_centro`
--

CREATE TABLE IF NOT EXISTS `encargado_centro` (
  `id_encargado` int(11) NOT NULL default '0',
  `id_cea` int(11) NOT NULL default '0',
  `ini_enc_cen` date default NULL,
  `fin_enc_cen` date default NULL,
  `estato_enc_cen` varchar(8) default NULL,
  PRIMARY KEY  (`id_encargado`,`id_cea`),
  KEY `encargado_centro_id_cea_centro_apoyo_id_cea` (`id_cea`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `encargado_centro`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `familia`
--

CREATE TABLE IF NOT EXISTS `familia` (
  `id_fam` int(11) NOT NULL auto_increment,
  `id_becado` int(11) default NULL,
  `nombre_f` varchar(100) default NULL,
  `apellido_patf` varchar(100) default NULL,
  `apellido_matf` varchar(100) default NULL,
  `direccion_f` varchar(255) default NULL,
  `telefono_f` varchar(50) default NULL,
  `parentesco_f` varchar(80) default NULL,
  `observacion_f` text,
  `name1_f` varchar(255) default NULL,
  `name2_f` varchar(255) default NULL,
  `name3_f` varchar(255) default NULL,
  `name4_f` varchar(255) default NULL,
  PRIMARY KEY  (`id_fam`),
  KEY `id_becado_idx` (`id_becado`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=1638 AUTO_INCREMENT=11 ;

--
-- Volcar la base de datos para la tabla `familia`
--

INSERT INTO `familia` (`id_fam`, `id_becado`, `nombre_f`, `apellido_patf`, `apellido_matf`, `direccion_f`, `telefono_f`, `parentesco_f`, `observacion_f`, `name1_f`, `name2_f`, `name3_f`, `name4_f`) VALUES
(1, 8, 'Roberto', 'Alanes', 'Montero', 'calle 21 de diciembre s/n lado tiendita', '4365874', 'PAP?', 'es el responsable de la familia ', '', '', '', ''),
(2, 8, 'Maria Ricarda', 'Flores', 'Martillo', 'pasaje piedras casi al fondo', '4365874', 'MAM?', 'es la responsable del hogar debido aque su esposo trabaja en otro departamento', '', '', '', ''),
(3, 8, 'david', 'valllejos ', 'choque', 'quillacollo', '4371571', 'PAP?', '', '', '', '', ''),
(4, 8, 'yordan ', 'max ', 'calizaya', 'colcapirhua', '4259858', 'PAP?', '', '', '', '', ''),
(5, 8, 'kely ', 'balderama', 'choque', 'cochabanba', '43651912', 'PAP?', '', '', '', '', ''),
(6, 8, 'gonsalo', 'mamani ', 'colque', 'sacaba', '4268852', 'HERMANO', 'gonsalo es responsable de cuidar a sus hermanos por que los padres fallecieron. ', '', '', '', ''),
(7, 8, 'luis alerto ', 'rodrigues ', 'canaviri', 'cochabanba', '4268258', 'PAP?', '', '', '', '', ''),
(8, 8, 'paola ', 'vallejos ', 'choque', 'colcapirhua', '4585225', 'MAM?', '', '', '', '', ''),
(9, 8, 'maria ', 'belen', 'parraga', 'quillacollo', '', 'MAM?', '', '', '', '', ''),
(10, 8, 'albaro ', 'bustamante', 'colque', 'quillacollo', '4256725', 'HERMANO', 'albaro es el encargado de los hermanos', '', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_academico`
--

CREATE TABLE IF NOT EXISTS `historial_academico` (
  `id_ha` int(11) NOT NULL auto_increment,
  `id_becado` int(11) default NULL,
  `fecha_ha` date default NULL,
  `libreta_ha` varchar(150) default NULL,
  `observacion_ha` text,
  `rendimiento_ha` varchar(30) default NULL,
  `tipo_ha` varchar(30) default NULL,
  `name1_ha` varchar(255) default NULL,
  `name2_ha` varchar(255) default NULL,
  `name3_ha` varchar(255) default NULL,
  `name4_ha` varchar(255) default NULL,
  PRIMARY KEY  (`id_ha`),
  KEY `id_becado_idx` (`id_becado`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=4096 AUTO_INCREMENT=5 ;

--
-- Volcar la base de datos para la tabla `historial_academico`
--

INSERT INTO `historial_academico` (`id_ha`, `id_becado`, `fecha_ha`, `libreta_ha`, `observacion_ha`, `rendimiento_ha`, `tipo_ha`, `name1_ha`, `name2_ha`, `name3_ha`, `name4_ha`) VALUES
(1, 1, '2013-05-15', 'e8a21c49cd860fa5ab192d73f0275e770a9d8d62.pdf', 'la ni?a maria tiene problemAS\r\nDE APREMDIZAJE MATEMATICO Y LENGUAJE', 'REGULAR', 'NOTAS 1? TRIMESTRE', '', '', '', ''),
(2, 1, '2013-09-10', '5d4b533eb6dd9b2def747fb32cd5c56936e23fb5.pdf', 'A MEJORADO EN MATEMATICAS PEROSIGUE COM PROBLEMAS DE LENGUAJE', 'BUENO-REGULAR', 'NOTAS 2? TRIMESTRE', '', '', '', ''),
(3, 8, '2013-06-30', '9bcb0f907eb551f1b68a5fbe3b3ed07b3da03f42.mp3', 'tiene un rendimiento poco favorable', '42', 'NOTAS 2? TRIMESTRE', '', '', '', ''),
(4, 8, '2013-06-30', 'e0401c4f34019de96e637e2a5ec1aefc451b2f74.pdf', 'esta bien', '50', 'NOTAS 2? TRIMESTRE', '', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_medico`
--

CREATE TABLE IF NOT EXISTS `historial_medico` (
  `id_hm` int(11) NOT NULL auto_increment,
  `id_becado` int(11) default NULL,
  `id_med` int(11) NOT NULL,
  `fecha_hm` date default NULL,
  `historial_hm` text,
  `costo_hm` float(18,2) default NULL,
  `name1_hm` varchar(255) default NULL,
  `name2_hm` varchar(255) default NULL,
  `name3_hm` varchar(255) default NULL,
  `name4_hm` varchar(255) default NULL,
  PRIMARY KEY  (`id_hm`),
  KEY `id_med_idx` (`id_med`),
  KEY `id_becado_idx` (`id_becado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `historial_medico`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informacion_personal`
--

CREATE TABLE IF NOT EXISTS `informacion_personal` (
  `id_inf_pers` int(11) NOT NULL auto_increment,
  `nombre` varchar(100) NOT NULL,
  `apellido_pat` varchar(100) NOT NULL,
  `apellido_mat` varchar(100) default NULL,
  `fecha_nac` date default NULL,
  `foto` varchar(150) default NULL,
  `certificado` varchar(150) default NULL,
  `ci` varchar(20) default NULL,
  `lugar_ci` varchar(20) default NULL,
  `est_civil` varchar(15) default NULL,
  `nacionalidad` varchar(50) default NULL,
  `direccion` text,
  `lugar_nac` varchar(50) default NULL,
  `telefono_fijo` int(11) default NULL,
  `telefono_cel` int(11) default NULL,
  `correo_e` varchar(100) default NULL,
  `name1_inf` varchar(255) default NULL,
  `name2_inf` varchar(255) default NULL,
  `name3_inf` varchar(255) default NULL,
  PRIMARY KEY  (`id_inf_pers`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=1671 AUTO_INCREMENT=46 ;

--
-- Volcar la base de datos para la tabla `informacion_personal`
--

INSERT INTO `informacion_personal` (`id_inf_pers`, `nombre`, `apellido_pat`, `apellido_mat`, `fecha_nac`, `foto`, `certificado`, `ci`, `lugar_ci`, `est_civil`, `nacionalidad`, `direccion`, `lugar_nac`, `telefono_fijo`, `telefono_cel`, `correo_e`, `name1_inf`, `name2_inf`, `name3_inf`) VALUES
(1, 'Maria', 'Encinas', 'Carrillo', '1999-05-27', '8262b86418fe6dc6b32dc5776717e54870d3f15e.gif', 'aed42a3c7a52fb529e745433594cc87f395977b9.pdf', '6406471', 'Cbba', '', '', '<p>\r\n	El ni&ntilde;o Vive e n la calle kallpchulpa s/n cerca parque</p>\r\n', 'cochabamba', 4371571, 75901360, 'carrillo.maria@hotmail.com', '', '', ''),
(2, 'Alex', 'Paredes', 'Cano', '2004-02-02', '80d23916cb22580e17df35cbf12327d280c78ea3.gif', '2587debe774d9d246dc61be65082551d068703a7.jpg', '', '', '', '', '<p>\r\n	su vivienda ubicada a 2 cuadras de la avenida empedrada</p>\r\n<p>\r\n	no cuenta con CI</p>\r\n', 'lapaz', NULL, 78935684, 'cano_alvarito@gmail.com', '', '', ''),
(3, 'yery', 'cruz', 'condri', '2008-01-17', '02775d994362744388790cdeaa28cca5d22ab723.gif', '96414720e3ed96ef0bfaf471a0bb4a474f4e5bca.pdf', 's/n', 'Cbba', '', '', '<p>\r\n	el ni&ntilde;o vive en aproimidades de l pozo de la zona kanarapa</p>\r\n', 'cochabamba', NULL, 68925861, 'cruz_yeri@hotmail.com', '', '', ''),
(4, 'silvia', 'lopez', 'fernandez', '2013-05-01', '0535be62eece5d6bd4f82630c59b60fbf5d95216.png', NULL, '6425897', 'Cbba', 'soltera', 'Bolivia', '<p>\r\n	Av. blanco Galindo km 10 s/n</p>\r\n', 'lapz', 4371171, 75925861, 'silvia_afer@hotmail.com', '', '', ''),
(5, 'hansen', 'valverde', 'ryon', '1994-12-04', '4e3ae8aa6728763708a6e5fd950d7d1d0ddc1f6f.gif', NULL, '555525154', '', '', 'USA', '<p>\r\n	Kansass City S/N<zxcvbnm,.-< p=""> </zxcvbnm,.-<></p>\r\n', '', NULL, 6892564, 'hasen@hotmail.com', '', '', '');
INSERT INTO `informacion_personal` (`id_inf_pers`, `nombre`, `apellido_pat`, `apellido_mat`, `fecha_nac`, `foto`, `certificado`, `ci`, `lugar_ci`, `est_civil`, `nacionalidad`, `direccion`, `lugar_nac`, `telefono_fijo`, `telefono_cel`, `correo_e`, `name1_inf`, `name2_inf`, `name3_inf`) VALUES
(6, 'tydhfrth', 'thrtwsr', 'esgrgreg', '2013-05-24', NULL, NULL, 'ertretgret', 'Cbba', 'rtrtserfawr', 'Bolivia', '<p>\r\n	<img alt="" src="data:image/png;base64,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', 'grgreg', 415615132, 75986350, 'wfefe@bgbfgb.com', '', '', '');
INSERT INTO `informacion_personal` (`id_inf_pers`, `nombre`, `apellido_pat`, `apellido_mat`, `fecha_nac`, `foto`, `certificado`, `ci`, `lugar_ci`, `est_civil`, `nacionalidad`, `direccion`, `lugar_nac`, `telefono_fijo`, `telefono_cel`, `correo_e`, `name1_inf`, `name2_inf`, `name3_inf`) VALUES
(7, 'Rosaly', 'Korum', 'Arsom', '1973-05-15', '8bff5db56f01d9e50bf889eb0fbd53d76203cb97.tif', NULL, '56877963', '', '', 'USA', '<p>\r\n	Los Angeles California</p>\r\n', '', NULL, 55558987, 'rosaly_a@yahoo.com', '', '', ''),
(8, 'Juan', 'Catacora', 'Sanches', '2005-10-29', 'ed1f6192e5891bf708efe08a8d602814031d6e1f.gif', '71c2fd67be89038fb945567254067615bdb4d9b8.jpg', '895713', 'Cbba', '', '', '<p>\r\n	Calle Callakchullpa S/N</p>\r\n', 'cochabamba', NULL, 7698417, 'juancatacora@hotmail.com', '', '', ''),
(9, 'Katerhine', 'Crosam', 'Dafft', '1969-12-31', '9db4af4d024e38a2488f65534e2ad7517bca3daf.pdf', NULL, '958201476', '', '', 'USA', '<p>\r\n	Av. california # 12676</p>\r\n', '', NULL, 6897543, 'katherine_krosam@gmail.com', '', '', ''),
(10, 'JOEL', 'DIAZ', 'ROJAS', '1999-10-29', '877ba431ae096f81300afc49194bbfc6c1ef2ae4.tif', '592f0d2ccf0310b873386f41c4ac5b0ba21baa5a.jpg', '136057', 'Cbba', '', '', '<p>\r\n	BARRIO FABRIL EN UNA&nbsp; ESQUINITA. DEL</p>\r\n', 'cochabamba', 42587957, 7991121, 'joel@hotmail.es', '', '', ''),
(11, 'calos ', 'veisaga', 'bargas', '2001-04-23', '3feccc92f15c47cb25616c0a13d947e77210132b.pdf', 'ed11c2c7b82d84eafd26d4ea88e987db79deebf1.jpg', '6406471', 'Cbba', '', '', '<p>\r\n	barrio fabril en una esquinita de la tienda</p>\r\n', 'quillacollo', 42852545, 7229856, 'carlos@hotmali.com', '', '', ''),
(12, 'alejandra rina', 'garcia ', 'alegre', '2002-10-28', '4389b75bf990080dbca533996e6d1702ca6ad3c2.gif', '64098177701cf60b06fdef3744246419183fcdb2.pdf', '136057', 'Cbba', '', '', '<p>\r\n	callle capacachi al frente de la canchita.</p>\r\n', 'cochabamba', 42556852, 7258356, 'alejandrita@hotmail.com', '', '', ''),
(13, 'diego', 'zenteno', 'pe?alosa', '2003-09-08', 'b378c3783184552408948efac1aea726f248416a.jpg', 'edb371cced816a50a73666156aa8412ef0db9b3e.pdf', '123456877896321', 'Cbba', '', '', '<p>\r\n	callle imnominada s/n pasando el canchon del cementerio de tiquipaya</p>\r\n', 'cochabamba', 4351874, 769824681, 'dieguito_zenteno@gmail.com', '', '', ''),
(14, 'luciano', 'canales', 'chaco', '1992-07-21', '5b42c7ceb545323ebad83cb23fffb47c41d02dad.jpg', NULL, '8765321', 'Cbba', 'soltero', 'Bolivia', '<p>\r\n	Av. los andes # 764</p>\r\n', 'Lapaz_bolivia', 4678954, 789654321, 'janco_gonsalo@hotmail.com', '', '', ''),
(15, 'Linda ', 'Brajaham', 'Landon', '1983-08-24', NULL, NULL, '216543838876', '', '', 'Bolivia', '<p>\r\n	Av 21 de septimbre # 16749 USA</p>\r\n', '', NULL, 65432246, 'penny_jim@hotmail.com', '', '', ''),
(16, 'marcela', 'ramos', 'peres', '1992-07-21', '49ddd8fb877a203a15fb1b7a4eeada1ac3b87c03.jpg', NULL, '654321', 'LPz', 'casado', 'Bolivia', '<p>\r\n	calle sion y pati&ntilde;o s/n</p>\r\n', 'Lapaz_bolivia', 5432176, 678965432, 'gonsales_peres@hotmail.com', '', '', ''),
(17, 'marcela', 'peres', 'ramos', '2005-07-21', NULL, NULL, '45674321', 'Pot', 'casado', 'Bolivia', '<p>\r\n	cale avedules esq. rosedales</p>\r\n', 'oruro', 4653217, 75947632, 'marcelita_pres@hotmail.com', '', '', ''),
(18, 'roberth', 'Blum', 'Pomilla', '1984-07-10', NULL, NULL, '6543887181', '', '', 'Bolivia', '<p>\r\n	Av. 12 de noviembre</p>\r\n', '', NULL, 5465767, 'robert_blum@hotmail.com', '', '', ''),
(19, 'Mark', 'linda', 'Ziemba', '2004-04-15', NULL, NULL, '45432147', '', '', 'USA', '<p>\r\n	calle 234 edif.mirador</p>\r\n', '', NULL, 32157876, 'markylinda@gmail.com', '', '', ''),
(20, 'Theresa', 'Taylor', 'James', '2004-07-13', NULL, NULL, '648339393', '', '', 'Canada', '<p>\r\n	los angeles california</p>\r\n', '', NULL, 555556565, 'theresa.zacher@gmail.com', '', '', ''),
(21, 'Michelle', 'Halley', 'ham', '2006-07-17', 'e5d32797d3fb554c5626ecaf35cae776fe720b01.jpg', NULL, '45674321', '', '', 'Austria', '<p>\r\n	Av. syney zona puerto madero</p>\r\n', '', NULL, 787655678, 'mechelle_handey@gmail.com', '', '', ''),
(22, 'Cristian', 'santos', 'Condori', '2004-07-20', '87f7e9c8cb806e22647e4c6c19866ae11394dd9f.jpg', '4b3ec505bc0a1ddef0f384d77232b9fd69f0a91c.jpg', '5238762', 'Or', '', '', '<p>\r\n	calle imnominada porton rojo casi esquina</p>\r\n', 'cochabamaba', 4653872, 7654321, 'cris_santoscc@yahoo.com', '', '', ''),
(23, 'jimena ', 'valderama ', 'colque', '1996-05-20', 'ebe32d18873cab139ea955e0e1c56b43f4296d0f.jpg', '9253552198beb493d90f86a61bdb1a7f3d09c7e7.jpg', '6030136', 'Cbba', '', '', '<p>\r\n	vive en moyapampa&nbsp; frente al colegio de daniel salamanca</p>\r\n', 'colcapirhua', 4371571, 75947632, 'jimena_@hotmail.com', '', '', ''),
(24, 'felipe', 'garcia', '', '1993-03-15', NULL, NULL, '326244', '', '', 'USA', '<p>\r\n	21 de diciembre</p>\r\n', '', NULL, 3457545, 'felipe_@hotmail.es', '', '', ''),
(25, 'veronica', 'contreras', '', '1994-02-10', NULL, NULL, '326444', '', '', 'USA', '<p>\r\n	21 de diciembre</p>\r\n', '', NULL, 3457542, 'veronica_@hotmail.es', '', '', ''),
(26, 'luciano', 'condori', 'colque', '2003-03-20', '3042ae6d18eb9d06841fceb603c31c28c9d08103.jpg', '0c38b85385d1461a8d4fc1e8a416839090f01906.jpg', '256347', 'Cbba', '', '', '<p>\r\n	vive en el barrio fabril frente ala tienda do&ntilde;a rosa</p>\r\n', 'cochabamba', 4678954, 75755434, 'luciano@hotmail.es', '', '', ''),
(27, 'lucia ', 'balderama ', 'peres', '2003-07-21', '74cdfa6e5fd6b6b806d7221c982c4670144c77ea.jpg', 'f6e24c82fc30c1040156226b9a4de96ebd40ad8a.jpg', '3354534', 'Cbba', '', '', '<p>\r\n	vive en el barrio entel frente ala parada de taxis concordia</p>\r\n', 'cochabamba', 4575757, 74574589, 'lucia@hotmail.es', '', '', ''),
(28, 'jorge', 'pe?alosa', 'colque', '2003-02-11', 'd49a7c21cf3559b7675d9a7a8a5d04cbc3c5ee8f.jpg', '91be20b5e76cbef052df07d962c4a3ade6ddcc07.jpg', '4454555', 'Cbba', '', '', '<p>\r\n	vive en tiquipaya frente ala alcaldia</p>\r\n', 'quillacollo', 4556566, 68565676, 'jorge@hotmail.es', '', '', ''),
(29, 'valentin', 'flores', 'coque', '2003-02-02', '97bc4d10c273e755386cd2163cf2c9fa05dea4e4.jpg', '046875a8ad43f8f31867ab2bcc021af4d78930ef.jpg', '4545456', 'Cbba', '', '', '<p>\r\n	vive al frente del cementerio concordia</p>\r\n', 'quillacollo', 4646464, 67454553, 'valentin@hotmail.com', '', '', ''),
(30, 'victor ', 'rodolfo', 'mamani', '2002-07-17', 'b2711643d025fe9ef439afab6f52a5a57ec723cc.jpg', '1781bdd35c37704bbff74f06ee9cf4f07b9ff978.jpg', '455667', 'Cbba', '', '', '<p>\r\n	vive en tiquipaya frente ala canchita</p>\r\n', 'colcapirhua', 4564667, 64566567, 'victor@hotmail.com', '', '', ''),
(31, 'thomas', 'perry', 'tyler', '1963-12-03', 'd8985911d99f5765446544dec98c3476ed17d2a1.jpg', NULL, '198217', '', '', 'USA', '<p>\r\n	21 de diciembre</p>\r\n', '', NULL, 3453545, 'thomas@hotmail.es', '', '', ''),
(32, 'gary', 'vicki', 'lamper', '1984-02-15', NULL, NULL, '345343', '', '', 'USA', '<p>\r\n	vive en la calle 30 de julio</p>\r\n', '', NULL, 4654656, 'gary_@hotmail.com', '', '', ''),
(33, 'rene', 'calli', 'roque', '2014-05-01', NULL, NULL, '5280599', 'Cbba', 'soltero', 'Bolivia', '', 'cercado', 4748076, 77927270, 'rene@hotmail.com', '', '', ''),
(34, 'rrr', 'rrr', 'rrr', '2014-05-14', NULL, NULL, '333', 'Cbba', 'rrr', 'Bolivia', '', 'rr', 333, 333, 'rr@hotmail.com', '', '', ''),
(35, 'ff', 'ff', 'ff', '2014-05-07', NULL, NULL, '', '', '', 'Bolivia', '', '', NULL, NULL, 'ff@hotmail.com', '', '', ''),
(36, 'ff', 'ff', 'ff', '2014-05-07', NULL, NULL, '', '', '', 'Bolivia', '', '', NULL, NULL, 'ff@hotmail.com', '', '', ''),
(37, 'ff', 'ff', 'ff', '2014-05-07', NULL, NULL, '', '', '', 'Bolivia', '', '', NULL, NULL, 'ff@hotmail.com', '', '', ''),
(38, 'ff', 'ff', 'ff', '2014-05-07', NULL, NULL, '', '', '', 'Bolivia', '', '', NULL, NULL, 'ff@hotmail.com', '', '', ''),
(39, 'tt', 'tt', 'tt', '2014-05-06', NULL, NULL, '55', '', 'tt', 'Bolivia', '', 'tt', 55, 55, 'ff@hotmail.com', '', '', ''),
(40, 'tt', 'tt', 'tt', '2014-05-06', NULL, NULL, '55', '', 'tt', 'Bolivia', '', 'tt', 55, 55, 'ff@hotmail.com', '', '', ''),
(41, 'carlos', 'merida', 'cardoso', '2014-05-21', NULL, NULL, '3333', '', 'casasdo', 'Bolivia', '', '', NULL, NULL, 'carlos@hotmail.com', '', '', ''),
(42, 'roberto', 'calli', 'roque', '2014-05-28', NULL, NULL, '222', '', 'casasdo', 'Bolivia', '', 'cercado', NULL, NULL, 'rr@hotmail.com', '', '', ''),
(43, 'maria', 'ester', 'jnknk', '2014-05-07', NULL, NULL, '', '', 'soltero', 'Bolivia', '', 'cercado', NULL, NULL, 'maria@hotmail.com', '', '', ''),
(44, 'rrr', 'rrr', 'rrr', '2014-05-13', NULL, NULL, '333.000000', 'Cbba', 'rrr', 'Bolivia', '', 'cercado', 4546464, 456464, 'sss@hotmail.com', '', '', ''),
(45, 'ttt', 'ttt', 'ttt', '2014-05-20', NULL, NULL, '444.000000', 'Cbba', 'ttt', 'Bolivia', '', 'ttt', 444, 44, 'tt@hotmail.com', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `institucion`
--

CREATE TABLE IF NOT EXISTS `institucion` (
  `id_inst` int(11) NOT NULL auto_increment,
  `nombre_inst` varchar(150) default NULL,
  `direccion_inst` varchar(250) default NULL,
  `telefono_inst` varchar(50) default NULL,
  `correo_inst` varchar(70) default NULL,
  `representante_inst` varchar(80) default NULL,
  `name1_inst` varchar(255) default NULL,
  `name2_inst` varchar(255) default NULL,
  `name3_inst` varchar(255) default NULL,
  PRIMARY KEY  (`id_inst`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=3276 AUTO_INCREMENT=6 ;

--
-- Volcar la base de datos para la tabla `institucion`
--

INSERT INTO `institucion` (`id_inst`, `nombre_inst`, `direccion_inst`, `telefono_inst`, `correo_inst`, `representante_inst`, `name1_inst`, `name2_inst`, `name3_inst`) VALUES
(1, 'santiba?ez vision mundial centro sonreir', 'tiquipaya', '46892271', 'centro_de_apoyo@sonreir.com', 'roberto', '', '', ''),
(2, 'vision mundial ', 'lapaz', '45586632', 'centro_de_apoyo@sonreir.com', 'doris', '', '', ''),
(3, 'fundacion padre bertha', 'final atahuallpa s/n', '4682574', 'fundacion.padre.bertha@hotmaill.com', 'padre eloy', '', '', ''),
(4, 'Fundaci?n PROCLADE ', 'calle palmeras # 1258', '32358674', 'proclave@hotmail.com', 'danitza parana', '', '', ''),
(5, 'fundacion save de children', 'Av el alto y autopista', '22458794', 'savedechildren@hotmail.com', 'martha guzman', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE IF NOT EXISTS `medico` (
  `id_med` int(11) NOT NULL auto_increment,
  `id_esp` int(11) default NULL,
  `id_inf_pers` int(11) default NULL,
  `id_centro_salud` int(11) default NULL,
  `observacion_med` text,
  `estado_med` varchar(8) default NULL,
  PRIMARY KEY  (`id_med`),
  KEY `id_esp_idx` (`id_esp`),
  KEY `id_centro_salud_idx` (`id_centro_salud`),
  KEY `id_inf_pers_idx` (`id_inf_pers`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `medico`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticia`
--

CREATE TABLE IF NOT EXISTS `noticia` (
  `id_noticia` int(11) NOT NULL auto_increment,
  `id_cea` int(11) default NULL,
  `nombre_noticia` varchar(250) default NULL,
  `detalle_noticia` text,
  `ini_noticia` date default NULL,
  `fin_noticia` date default NULL,
  `estado_noticia` varchar(8) default NULL,
  `name1_noticia` varchar(255) default NULL,
  `name2_noticia` varchar(255) default NULL,
  PRIMARY KEY  (`id_noticia`),
  KEY `id_cea_idx` (`id_cea`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=4096 AUTO_INCREMENT=5 ;

--
-- Volcar la base de datos para la tabla `noticia`
--

INSERT INTO `noticia` (`id_noticia`, `id_cea`, `nombre_noticia`, `detalle_noticia`, `ini_noticia`, `fin_noticia`, `estado_noticia`, `name1_noticia`, `name2_noticia`) VALUES
(1, NULL, 'aviso de bien social', '<p style="margin-left:18.0pt;">\r\n	Los widgets se generaran dentro de lib/form/doctrine</p>\r\n<p style="margin-left:18.0pt;">\r\n	Los validadores ah&iacute; mismo</p>\r\n', '2013-05-20', '2013-05-27', 'ACTIVO', '', ''),
(2, 1, 'Visita Miembros Fundacion', '<p>\r\n	&nbsp;</p>\r\n<table border="1" cellpadding="0" cellspacing="0" width="705">\r\n	<tbody>\r\n		<tr>\r\n			<td style="width:242px;">\r\n				<p>\r\n					Nombres y Apellidos de los Participantes</p>\r\n			</td>\r\n			<td style="width:121px;">\r\n				<p>\r\n					N&uacute;meros telef&oacute;nicos del contacto</p>\r\n			</td>\r\n			<td style="width:172px;">\r\n				<p>\r\n					Correo electr&oacute;nico</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P1. Eleuterio Galindo M.</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					72235837</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:eleuterigakindo@hotmail.com">eleuterigakindo@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P2. Sabastapugal Cabrera</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					67551561</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:sabas-ta@hotmail.com">sabas-ta@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P3. Luis Fernando Galindo</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					67531777</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:puyol_gali@yahoo.com">puyol_gali@yahoo.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P4. JesusaPuyal Rojas</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					71764192</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:jhesu2013sas@yahoo.com">jhesu2013sas@yahoo.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P5.Victor Alanes Gutierrez</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					72708597</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:carlosm_garcia_alcocer@hotmail.com">carlosm_garcia_alcocer@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P6. Jelder J. Mayta Mamani</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					68306876</p>\r\n			</td>\r\n			<td>\r\n				<p>\r\n					&nbsp;</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P7. Oscar Puyal Cabrera</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					71718477</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:Oscarpuyal2013@yahoo.com">Oscarpuyal2013@yahoo.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P8. Nelia Mamani Flores</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					72241055</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:daney_d17@yahoo.com">daney_d17@yahoo.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P9. Amalia JachacolloColque</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					67462107</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:ammy_brisa@hotmail.com">ammy_brisa@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P10. Antonia Mamani Apaza</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					68306876</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:antoniamamani@hotmail.com">antoniamamani@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					P11. Maria D. Cerezo Silvestre</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					68474666</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:cmaria@hotmail.com">cmaria@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					12. Beatriz Peralta Vargas</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					s/n</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:betyperalta@hotmail.com">betyperalta@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					13. Lilia Mendez Vela</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					s/n</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:liliamendesvela@hotmail.com">liliamendesvela@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					14. HilarionPuyal Rojas</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					747720854</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:hilarionpuyalr@hotmail.com">hilarionpuyalr@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					15. Ana JachacolloColque</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					67462107</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:anitajachacollo@hotmail.com">anitajachacollo@hotmail.com</a></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:242px;height:14px;">\r\n				<p>\r\n					16. Sulma Munoz Vargas</p>\r\n			</td>\r\n			<td style="width:121px;height:14px;">\r\n				<p>\r\n					68474666</p>\r\n			</td>\r\n			<td style="width:172px;height:14px;">\r\n				<p>\r\n					<a href="mailto:sulmamunosv@hotmail.com">sulmamunosv@hotmail.com</a></p>\r\n			</td>\r\n			<td>\r\n				&nbsp;</td>\r\n		</tr>\r\n		<tr>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="5" style="width:705px;height:14px;">\r\n				&nbsp;</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n<p>\r\n	&nbsp;</p>\r\n<p>\r\n	&nbsp;</p>\r\n', '2013-05-21', '2013-05-22', 'ACTIVO', '', ''),
(3, 1, 'Realizacion de Cartas de Agradecimiento', '', '2013-05-22', '2013-05-23', 'ACTIVO', '', ''),
(4, 1, 'fallecio un becado', '<p>\r\n	En pasados dias se tuvo la mala noticia de el fallecimiento de un familiar de un becado</p>\r\n<p>\r\n	por ese motivo se les invita a la misa el la casa del centro</p>\r\n', '2013-07-01', '2013-07-05', 'ACTIVO', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `padrino`
--

CREATE TABLE IF NOT EXISTS `padrino` (
  `id_padrino` int(11) NOT NULL auto_increment,
  `id_inf_pers` int(11) default NULL,
  `estado_padrino` varchar(8) default NULL,
  `name1_padrino` varchar(255) default NULL,
  `name2_padrino` varchar(255) default NULL,
  PRIMARY KEY  (`id_padrino`),
  KEY `id_inf_pers_idx` (`id_inf_pers`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=5461 AUTO_INCREMENT=13 ;

--
-- Volcar la base de datos para la tabla `padrino`
--

INSERT INTO `padrino` (`id_padrino`, `id_inf_pers`, `estado_padrino`, `name1_padrino`, `name2_padrino`) VALUES
(1, 5, 'ACTIVO', NULL, NULL),
(2, 7, 'ACTIVO', NULL, NULL),
(3, 9, 'ACTIVO', NULL, NULL),
(4, 15, 'ACTIVO', NULL, NULL),
(5, 18, 'ACTIVO', NULL, NULL),
(6, 19, 'ACTIVO', NULL, NULL),
(7, 20, 'ACTIVO', NULL, NULL),
(8, 21, 'ACTIVO', NULL, NULL),
(9, 24, 'ACTIVO', NULL, NULL),
(10, 25, 'ACTIVO', NULL, NULL),
(11, 31, 'ACTIVO', NULL, NULL),
(12, 32, 'ACTIVO', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `padrino_becado`
--

CREATE TABLE IF NOT EXISTS `padrino_becado` (
  `id_becado` int(11) NOT NULL default '0',
  `id_padrino` int(11) NOT NULL default '0',
  `ini_bp` date default NULL,
  `fin_bp` date default NULL,
  `estado_bp` varchar(8) default NULL,
  `name1_bp` varchar(255) default NULL,
  `name2_bp` varchar(255) default NULL,
  `name3_bp` varchar(255) default NULL,
  PRIMARY KEY  (`id_becado`,`id_padrino`),
  KEY `padrino_becado_id_padrino_padrino_id_padrino` (`id_padrino`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384;

--
-- Volcar la base de datos para la tabla `padrino_becado`
--

INSERT INTO `padrino_becado` (`id_becado`, `id_padrino`, `ini_bp`, `fin_bp`, `estado_bp`, `name1_bp`, `name2_bp`, `name3_bp`) VALUES
(1, 1, '2013-05-17', NULL, 'ACTIVO', '', '', ''),
(2, 5, '2013-07-02', NULL, 'ACTIVO', '', '', ''),
(3, 7, '2013-07-01', NULL, 'ACTIVO', '', '', ''),
(4, 6, '2013-07-25', NULL, 'ACTIVO', '', '', ''),
(5, 3, '2013-03-01', NULL, 'ACTIVO', '', '', ''),
(6, 4, '2013-04-17', NULL, 'ACTIVO', '', '', ''),
(8, 3, '2013-03-01', NULL, 'ACTIVO', '', '', ''),
(8, 4, '2013-07-26', NULL, 'ACTIVO', '', '', ''),
(9, 8, '2013-07-16', NULL, 'ACTIVO', '', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reglamento`
--

CREATE TABLE IF NOT EXISTS `reglamento` (
  `id_regla` int(11) NOT NULL auto_increment,
  `id_cea` int(11) default NULL,
  `reglamento_r` text,
  `name1_r` varchar(255) default NULL,
  `name2_r` varchar(255) default NULL,
  PRIMARY KEY  (`id_regla`),
  KEY `id_cea_idx` (`id_cea`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=8192 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `reglamento`
--

INSERT INTO `reglamento` (`id_regla`, `id_cea`, `reglamento_r`, `name1_r`, `name2_r`) VALUES
(1, NULL, '<ol style="list-style-type:lower-alpha;">\r\n	<li>\r\n		b&aacute;sicos en contabilidad para la administraci&oacute;n del Telecentro.</li>\r\n</ol>\r\n<p style="margin-left:39.3pt;">\r\n	&nbsp;</p>\r\n<ol style="list-style-type:lower-alpha;">\r\n	<li value="2">\r\n		Los Operadores y/o Concesionarios del Telecentro tienen un adecuado conocimiento en TICs.</li>\r\n	<li value="3">\r\n		&nbsp;</li>\r\n</ol>\r\n<table border="1" cellpadding="0" cellspacing="0">\r\n	<tbody>\r\n		<tr>\r\n			<td style="width:163px;">\r\n				<p>\r\n					<strong>M&Oacute;DULO 1</strong></p>\r\n			</td>\r\n			<td style="width:473px;">\r\n				<p>\r\n					<strong>SISTEMA DEL TELECENTRO</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:163px;">\r\n				<p>\r\n					<strong>UNIDAD 1</strong></p>\r\n			</td>\r\n			<td style="width:473px;">\r\n				<p>\r\n					<strong>MANEJAR</strong><strong> TECNOLOG&Iacute;AS DE LA INFORMACI&Oacute;N Y SOFTWARE DE OFIM&Aacute;TICA.</strong></p>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n<p style="margin-left:18.0pt;">\r\n	&nbsp;</p>\r\n<table border="1" cellpadding="0" cellspacing="0">\r\n	<tbody>\r\n		<tr>\r\n			<td style="width:68px;">\r\n				<p>\r\n					<strong>SESION</strong></p>\r\n			</td>\r\n			<td colspan="2" style="width:129px;">\r\n				<p>\r\n					<strong>OBJETIVO</strong></p>\r\n			</td>\r\n			<td style="width:98px;">\r\n				<p>\r\n					<strong>TECNICA</strong></p>\r\n			</td>\r\n			<td style="width:119px;">\r\n				<p>\r\n					<strong>PROCEDIMIENTO</strong></p>\r\n			</td>\r\n			<td style="width:102px;">\r\n				<p>\r\n					<strong>RECURSOS</strong></p>\r\n			</td>\r\n			<td style="width:95px;">\r\n				<p>\r\n					<strong>DESARROLLO</strong></p>\r\n			</td>\r\n			<td style="width:25px;">\r\n				<p>\r\n					<strong>Hrs</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:68px;">\r\n				<p align="center">\r\n					<strong>1</strong></p>\r\n			</td>\r\n			<td colspan="2" style="width:129px;">\r\n				<p>\r\n					Conocer y poner en funcionamiento los equipos.</p>\r\n			</td>\r\n			<td style="width:98px;">\r\n				<p>\r\n					Explicaci&oacute;n, demostraci&oacute;n</p>\r\n			</td>\r\n			<td style="width:119px;">\r\n				<p>\r\n					se explic&oacute;&nbsp; partes de la computadora y su funcionamiento.</p>\r\n			</td>\r\n			<td style="width:102px;">\r\n				<p>\r\n					Computadora, diapositivas, DVDs y videos.</p>\r\n			</td>\r\n			<td style="width:95px;">\r\n				<p>\r\n					<strong>A</strong></p>\r\n			</td>\r\n			<td style="width:25px;">\r\n				<p>\r\n					<strong>8</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:68px;">\r\n				<p align="center">\r\n					<strong>4</strong></p>\r\n			</td>\r\n			<td colspan="2" style="width:129px;">\r\n				<p>\r\n					Escribir la propuesta t&eacute;cnica y administrativa.</p>\r\n			</td>\r\n			<td style="width:98px;">\r\n				<p>\r\n					Explicaci&oacute;n,&nbsp; dialogo y preguntas.</p>\r\n			</td>\r\n			<td style="width:119px;">\r\n				<p>\r\n					Conocimiento de Microsoft office, TICs.</p>\r\n			</td>\r\n			<td style="width:102px;">\r\n				<p>\r\n					Computadora, diapositivas, DVDs y videos.</p>\r\n			</td>\r\n			<td style="width:95px;">\r\n				<p>\r\n					<strong>A</strong></p>\r\n			</td>\r\n			<td style="width:25px;">\r\n				<p>\r\n					<strong>8</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:68px;">\r\n				<p align="center">\r\n					<strong>7</strong></p>\r\n			</td>\r\n			<td colspan="2" style="width:129px;">\r\n				<p>\r\n					Enviar la propuesta y presentaci&oacute;n del servicio a un correo del CEASAD.</p>\r\n			</td>\r\n			<td style="width:98px;">\r\n				<p>\r\n					Explicaci&oacute;n y preguntas.</p>\r\n			</td>\r\n			<td style="width:119px;">\r\n				<p>\r\n					Se explic&oacute; sobre el uso de correo, y la identificaci&oacute;n de las necesidades.</p>\r\n			</td>\r\n			<td style="width:102px;">\r\n				<p>\r\n					Computadora, diapositivas, DVDs y videos. Internet.</p>\r\n			</td>\r\n			<td style="width:95px;">\r\n				<p>\r\n					<strong>A</strong></p>\r\n			</td>\r\n			<td style="width:25px;">\r\n				<p>\r\n					<strong>8</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="4" style="width:295px;">\r\n				<p>\r\n					&nbsp;</p>\r\n			</td>\r\n			<td colspan="4" style="width:341px;">\r\n				<p>\r\n					&nbsp;</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="2" style="width:158px;">\r\n				<p>\r\n					<strong>M&Oacute;DULO 1</strong></p>\r\n			</td>\r\n			<td colspan="6" style="width:479px;">\r\n				<p>\r\n					<strong>SISTEMA DEL TELECENTRO</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="2" style="width:158px;height:35px;">\r\n				<p>\r\n					<strong>UNIDAD 2</strong></p>\r\n			</td>\r\n			<td colspan="6" style="width:479px;height:35px;">\r\n				<p>\r\n					<strong>REALIZAR EL MANTENIMIENTO PREVENTIVO Y CORRECTIVO </strong><strong>DE LOS COMPONENTES PRINCIPALES DEL TELECENTRO.</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr height="0">\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n			<td>\r\n				&nbsp;</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n<p style="margin-left:18.0pt;">\r\n	&nbsp;</p>\r\n<table border="1" cellpadding="0" cellspacing="0">\r\n	<tbody>\r\n		<tr>\r\n			<td style="width:67px;">\r\n				<p>\r\n					<strong>SESION</strong></p>\r\n			</td>\r\n			<td colspan="2" style="width:127px;">\r\n				<p>\r\n					<strong>OBJETIVO</strong></p>\r\n			</td>\r\n			<td style="width:94px;">\r\n				<p>\r\n					<strong>TECNICA</strong></p>\r\n			</td>\r\n			<td style="width:119px;">\r\n				<p>\r\n					<strong>PROCEDIMIENTO</strong></p>\r\n			</td>\r\n			<td style="width:99px;">\r\n				<p>\r\n					<strong>RECURSOS</strong></p>\r\n			</td>\r\n			<td style="width:95px;">\r\n				<p>\r\n					<strong>DESARROLLO</strong></p>\r\n			</td>\r\n			<td style="width:35px;">\r\n				<p>\r\n					<strong>Hrs</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td style="width:67px;">\r\n				<p align="center">\r\n					<strong>2</strong></p>\r\n			</td>\r\n			<td colspan="2" style="width:127px;">\r\n				<p>\r\n					Revisar y mantener los equipos (diariamente).</p>\r\n			</td>\r\n			<td style="width:94px;">\r\n				<p>\r\n					Explicaci&oacute;n.</p>\r\n			</td>\r\n			<td style="width:119px;">\r\n				<p>\r\n					Se explic&oacute;, se demostr&oacute; el mantenimiento preventivo y previsiones.</p>\r\n			</td>\r\n			<td style="width:99px;">\r\n				<p>\r\n					Computadora, diapositivas, DVDs y videos.</p>\r\n			</td>\r\n			<td style="width:95px;">\r\n				<p>\r\n					<strong>B</strong></p>\r\n			</td>\r\n			<td style="width:35px;">\r\n				<p>\r\n					<strong>8</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="4" style="width:288px;">\r\n				<p>\r\n					&nbsp;</p>\r\n			</td>\r\n			<td colspan="4" style="width:348px;">\r\n				<p>\r\n					&nbsp;</p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="2" style="width:156px;">\r\n				<p>\r\n					<strong>M&Oacute;DULO 1</strong></p>\r\n			</td>\r\n			<td colspan="6" style="width:481px;">\r\n				<p>\r\n					<strong>SISTEMA DEL TELECENTRO</strong></p>\r\n			</td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan="2" style="width:156px;">\r\n				<p>\r\n					<strong>UNIDAD 3</strong></p>\r\n			</td>\r\n			<td colspan="6" style="width:481px;">\r\n				<p>\r\n					<strong>R</strong><strong>ESOLVER FALLAS RECURRENTES DEL SISTEMA DE ELECTRIFICACI&Oacute;N, DE LA RED LAN O DE LOS MATERIALES Y EQUIPOS DEL SISTEMA.</strong></p>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n<p>\r\n	&nbsp;</p>\r\n', '', ''),
(2, 1, '<ol>\r\n	<li>\r\n		Se trata del&nbsp; sacerdote austriaco Nicol&aacute;s Laireiter, quien lleg&oacute; a Bolivia hace 15 a&ntilde;os.</li>\r\n	<li>\r\n		El sacerdote austriaco, Nicol&aacute;s Laireiter, desde hace 13 a&ntilde;os ayuda a pagar tratamientos y curaciones de cientos de ni&ntilde;os pobres, al menos 8.000 al a&ntilde;o, en hospitales y centros m&eacute;dicos del pa&iacute;s.</li>\r\n	<li>\r\n		Su labor de cooperaci&oacute;n, que se inici&oacute; a partir de un caso muy particular, se tradujo en la creaci&oacute;n de la Fundaci&oacute;n Para ni&ntilde;os - Padre Klaus, que en la actualidad trabaja con cinco hospitales y centros de atenci&oacute;n m&eacute;dica, adem&aacute;s de otras instituciones del interior del pa&iacute;s, de donde llegan solicitudes de ayuda espec&iacute;ficas.</li>\r\n	<li>\r\n		Cuando vio la necesidad. El Padre Laireiter cuenta que el 19 de abril de 1999, mientras recorr&iacute;a las calles de la ciudad de El Alto, vio a una muchacha tirada en una calle, inconsciente, mientras la sangre flu&iacute;a de su boca y nariz. &ldquo;Tuve la impresi&oacute;n de que se estaba muriendo. Un m&eacute;dico, a qui&eacute;n me dirig&iacute;, contest&oacute; que la chica sufr&iacute;a de tuberculosis activa en fase casi terminal, que no hab&iacute;a salvaci&oacute;n, excepto tal vez en el hospital de ni&ntilde;os en La Paz. De inmediato la llev&eacute; a este hospital, donde fue atendida y salvada&rdquo;, record&oacute;.</li>\r\n	<li>\r\n		Como su familia era pobre, Laireiter pag&oacute; todos los gastos y la visit&oacute; con frecuencia. En ese recorrido conoci&oacute; la sala de quemados de dicho nosocomio y vio a muchos ni&ntilde;os con necesidades, incluso de anestesia para sus curaciones. &ldquo;Decid&iacute; pagar los medicamentos para estos pacientes. As&iacute; muchos padres de familia se enteraron, que hay un padre que sabe ayudar y acudieron con sus ni&ntilde;os enfermos&rdquo;.</li>\r\n	<li>\r\n		Caros y baratos. La trabajadora social de la Fundaci&oacute;n Jaquelin Bricher, explic&oacute; que mensualmente su instituci&oacute;n ayuda hasta a 1.200 menores, cada mes. &ldquo;Los ni&ntilde;os que no tiene recursos, puede ser por operaci&oacute;n, por medicamentos o curaci&oacute;n, piden ayuda&rdquo;. Las oficinas de trabajo social de cada hospital o centro m&eacute;dico, hacen la evaluaci&oacute;n y env&iacute;an el pedido de ayuda a la Fundaci&oacute;n.</li>\r\n	<li>\r\n		La directora ejecutiva, Drina Laruta, se&ntilde;al&oacute; que el &uacute;nico benefactor de esta instituci&oacute;n es el Padre Klaus, quien actualmente trabaja por consolidar este ente. &ldquo;Mientras disponga de recursos, quiero trabajar para mis queridos ni&ntilde;os en Bolivia&rdquo;, dijo Laireiter.</li>\r\n	<li>\r\n		3 profesionales apoyan la labor de la instituci&oacute;n conocida como Fundaci&oacute;n Klaus.</li>\r\n	<li>\r\n		&ldquo;Conoc&iacute; la unidad de cirug&iacute;a pl&aacute;stica y quemados. Me di cuenta que sufr&iacute;an much&iacute;simo estas pobres criaturas con quemaduras. Debido a su pobreza no dispon&iacute;an de calmantes ni para el cambio de vendajes&rdquo;.</li>\r\n	<li>\r\n		Padre Nicol&aacute;s Laireiter / BENEFACTOR DE LA FUNDACI&Oacute;N PADRE KLAUS</li>\r\n	<li>\r\n		La fundaci&oacute;n se estableci&oacute; en 2011. La Fundaci&oacute;n Para Ni&ntilde;os &ndash; Padre Klaus en Bolivia, fue establecida legalmente en el pa&iacute;s, desde 2011, a partir de un grupo de profesionales que trabaja en coordinaci&oacute;n directa con el padre Nicol&aacute;s Laireiter, a quien se apoda Klaus, como se nombra a todos los &ldquo;Nicol&aacute;s&rdquo; en su pa&iacute;s de origen , y por quien la organizaci&oacute;n lleva el nombre.</li>\r\n	<li>\r\n		Sin embargo, su organizaci&oacute;n funciona desde hace muchos a&ntilde;os en Austria, donde retorn&oacute; despu&eacute;s de conocer Bolivia.</li>\r\n	<li>\r\n		Trabaj&oacute; en la ciudad de El Alto. El Padre Laireitier, de la comunidad Verbo Divino, naci&oacute; en Austria y proviene de una familia de ocho hermanos.</li>\r\n	<li>\r\n		Vino a Bolivia hace 15 a&ntilde;os a trabajar como sacerdote en la parroquia Santa Mar&iacute;a de los &Aacute;ngeles, en El Alto, donde empez&oacute; su contacto con los ni&ntilde;os.</li>\r\n	<li>\r\n		Algunos beneficiarios</li>\r\n	<li>\r\n		<h3>\r\n			HOSPITALES</h3>\r\n	</li>\r\n	<li>\r\n		<h3>\r\n			Tiene convenios con los hospitales pace&ntilde;os del Ni&ntilde;o, San Gabriel, y San Francisco de As&iacute;s.</h3>\r\n	</li>\r\n	<li>\r\n		<h3>\r\n			CENTROS</h3>\r\n	</li>\r\n	<li>\r\n		<h3>\r\n			Apoya al Centro Santa Mar&iacute;a de los &Aacute;ngeles de El Alto y al Centro 20 de Octubre de Cochabamba.</h3>\r\n	</li>\r\n</ol>\r\n<p>\r\n	&nbsp;</p>\r\n<p>\r\n	&nbsp;</p>\r\n<p>\r\n	&nbsp;</p>\r\n<ol>\r\n	<li>\r\n		<h1>\r\n			Apadrinamiento de ancianos</h1>\r\n	</li>\r\n	<li>\r\n		<a href="http://www.fundacionproclade.org/print/7469" title="Mostrar una versi?n para imprimir de esta p?gina."><img alt="Versi?n para impresi?n" border="0" height="41" src="file:///C:\\Users\\SILVIA~1\\AppData\\Local\\Temp\\msohtmlclip1\\01\\clip_image001.gif" width="41" /></a><a href="http://www.fundacionproclade.org/printmail/7469" title="Send this page by email."><img alt="Enviar por email" border="0" height="41" src="file:///C:\\Users\\SILVIA~1\\AppData\\Local\\Temp\\msohtmlclip1\\01\\clip_image001.gif" width="41" /></a><a href="http://www.fundacionproclade.org/printpdf/7469" title="Mostrar una versi?n PDF de esta p?gina."><img alt="Versi?n PDF" border="0" height="41" src="file:///C:\\Users\\SILVIA~1\\AppData\\Local\\Temp\\msohtmlclip1\\01\\clip_image001.gif" width="41" /></a></li>\r\n	<li>\r\n		<h4>\r\n			&iquest;Qu&eacute;?</h4>\r\n	</li>\r\n	<li>\r\n		El proyecto de Apadrinamiento de ancianos pretende acompa&ntilde;ar a la poblaci&oacute;n envejecida de aquellas comunidades pertenecientes a pa&iacute;ses en los que no existen o son m&iacute;nimas las ayudas sociales a la tercera edad. Muchos de ellos no tienen qui&eacute;n les atienda y se encuentran en situaciones de pobreza, en algunos casos, extrema. El objetivo de esta ayuda es dignificar a la persona de forma que se sienta a&uacute;n parte &uacute;til de su comunidad.</li>\r\n	<li>\r\n		<h4>\r\n			&iquest;D&oacute;nde?</h4>\r\n	</li>\r\n	<li>\r\n		Las poblaciones en las que Fundaci&oacute;n PROCLADE est&aacute; desarrollando sus programas y que por tanto cuentan con el proyecto de Apadrinamiento a ni&ntilde;os son: Guayamer&iacute;n en Bolivia, Tela en Honduras y Lambar&eacute; y Yh&uacute;-Vaquer&iacute;a en Paraguay.</li>\r\n	<li>\r\n		<h4>\r\n			&iquest;C&oacute;mo?</h4>\r\n	</li>\r\n	<li>\r\n		Para hacer efectivo el apadrinamiento, los padrinos y madrinas deben rellenar una <a href="http://www.fundacionproclade.org/formulario-apadrinamientos"><strong>ficha de inscripci&oacute;n</strong></a> y abonar una cantidad m&iacute;nima de 12 euros mensuales que se cobran mediante domiciliaci&oacute;n bancaria. Fundaci&oacute;n PROCLADE se compromete a informar a los padrinos y madrinas sobre el programa de apadrinamiento al que apoya -por carta o correo electr&oacute;nico-&nbsp;al menos dos veces al a&ntilde;o.</li>\r\n</ol>\r\n', '', ''),
(3, NULL, '<ul>\r\n	<li>\r\n		El centro de apoyo SONREIR es una organizaci&oacute;n de Fe sin fines de lucro y tiene la misi&oacute;n de llevar vida y esperanza, en forma de solidaridad&nbsp; a ni&ntilde;os de escasos recursos econ&oacute;micos.</li>\r\n	<li>\r\n		El centro de apoyo SONREIR se encuentra, ubicada en el departamento de Cochabamba, en el&nbsp; municipio de Tiquipaya (zona Siripita). Es una&nbsp; organizaci&oacute;n que brinda apoyo&nbsp; a ni&ntilde;os, en forma&nbsp; de apadrinamiento.&nbsp;</li>\r\n	<li>\r\n		&nbsp;Gracias a la&nbsp; colaboraci&oacute;n de personas de origen extranjero, a los cuales se les denomina como padrinos. y a la fundaci&oacute;n quienes son los que se&nbsp; encargan de encontrar familias&nbsp; de buena voluntad, las que brindansu apoyo estos ni&ntilde;os y sus familias.</li>\r\n	<li>\r\n		&nbsp;</li>\r\n	<li>\r\n		La fundaci&oacute;n emplea los recursos en diferentes formas ya sea en apoyo de &uacute;tiles escolares, ropa, calzados, alimentaci&oacute;n, atenci&oacute;n m&eacute;dica y dental, cursos de apoyo para la educaci&oacute;n de todos los ni&ntilde;os y brinda informaci&oacute;n psicol&oacute;gica con temas que ayudan al fortalecimiento de&nbsp; la familia.</li>\r\n	<li>\r\n		&nbsp;</li>\r\n</ul>\r\n', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reporte`
--

CREATE TABLE IF NOT EXISTS `reporte` (
  `id_reporte` int(11) NOT NULL auto_increment,
  `id_persona` int(11) NOT NULL,
  `nivel_reporte` int(11) NOT NULL,
  `titulo_reporte` varchar(100) NOT NULL,
  `texto_reporte` varchar(1000) NOT NULL,
  PRIMARY KEY  (`id_reporte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `reporte`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_forgot_password`
--

CREATE TABLE IF NOT EXISTS `sf_guard_forgot_password` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) NOT NULL,
  `unique_key` varchar(255) default NULL,
  `expires_at` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id_idx` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `sf_guard_forgot_password`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_group`
--

CREATE TABLE IF NOT EXISTS `sf_guard_group` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` text,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `sf_guard_group`
--

INSERT INTO `sf_guard_group` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(3, 'admin', 'Administrator group', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_group_permission`
--

CREATE TABLE IF NOT EXISTS `sf_guard_group_permission` (
  `group_id` bigint(20) NOT NULL default '0',
  `permission_id` bigint(20) NOT NULL default '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`group_id`,`permission_id`),
  KEY `sf_guard_group_permission_permission_id_sf_guard_permission_id` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `sf_guard_group_permission`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_permission`
--

CREATE TABLE IF NOT EXISTS `sf_guard_permission` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `description` text,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 AUTO_INCREMENT=4 ;

--
-- Volcar la base de datos para la tabla `sf_guard_permission`
--

INSERT INTO `sf_guard_permission` (`id`, `name`, `description`, `created_at`, `updated_at`) VALUES
(3, 'admin', 'Administrator permission', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_remember_key`
--

CREATE TABLE IF NOT EXISTS `sf_guard_remember_key` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `remember_key` varchar(32) default NULL,
  `ip_address` varchar(50) default NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id_idx` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `sf_guard_remember_key`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_user`
--

CREATE TABLE IF NOT EXISTS `sf_guard_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `es_persona` int(11) default NULL,
  `nivel_user` varchar(250) default NULL,
  `first_name` varchar(255) default NULL,
  `last_name` varchar(255) default NULL,
  `email_address` varchar(255) NOT NULL,
  `username` varchar(128) NOT NULL,
  `algorithm` varchar(128) NOT NULL default 'sha1',
  `salt` varchar(128) default NULL,
  `password` varchar(128) default NULL,
  `is_active` tinyint(1) default '1',
  `is_super_admin` tinyint(1) default '0',
  `last_login` datetime default NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email_address` (`email_address`),
  UNIQUE KEY `username` (`username`),
  KEY `is_active_idx_idx` (`is_active`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 AUTO_INCREMENT=10 ;

--
-- Volcar la base de datos para la tabla `sf_guard_user`
--

INSERT INTO `sf_guard_user` (`id`, `es_persona`, `nivel_user`, `first_name`, `last_name`, `email_address`, `username`, `algorithm`, `salt`, `password`, `is_active`, `is_super_admin`, `last_login`, `created_at`, `updated_at`) VALUES
(3, 1, '1', 'John', 'Doe', 'john.doe@gmail.com', 'admin', 'sha1', '4f05d61c5e4ac41c8c088359d6bdbb07', '495c617cd40b830b4b5d5ccbe44aa0007122a5ad', 1, 1, '2014-05-29 17:11:35', '0000-00-00 00:00:00', '2014-05-29 17:11:35'),
(6, 42, '2', 'roberto', 'calli', 'rr@hotmail.com', 'admin3', 'sha1', '1331820be454b3af03a0fa47acbe7307', 'd3c0055fe4c5e83528eefe19d2cbbf2b8b21ed67', 1, 1, '2014-05-29 17:47:11', '2014-05-24 12:31:47', '2014-05-29 17:47:11'),
(7, 43, '3', 'maria', 'ester', 'maria@hotmail.com', 'admin4', 'sha1', '13137c8686a3298813d905212f0b3ead', 'de1e2da39ed6309f63dad816c4236802722654bb', 1, 1, '2014-05-29 17:50:20', '2014-05-24 13:11:09', '2014-05-29 17:50:20'),
(8, 44, '2', 'rrr', 'rrr', 'sss@hotmail.com', 'rene', 'sha1', '1c30fc2b6e6b6c884b4d1068d3937c79', '6ba21fc5e71517a623ec1e2200d3d9968199749f', 1, 1, '2014-05-26 21:25:02', '2014-05-26 20:43:55', '2014-05-26 21:25:02'),
(9, 45, '2', 'ttt', 'ttt', 'tt@hotmail.com', ' rene', 'sha1', '79214732c71193f666cb67eebcac6dbc', '8c2638cfe90a254674264eab40970f0f646ba7ea', 1, 1, NULL, '2014-05-26 20:46:16', '2014-05-26 20:46:16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_user_group`
--

CREATE TABLE IF NOT EXISTS `sf_guard_user_group` (
  `user_id` bigint(20) NOT NULL default '0',
  `group_id` bigint(20) NOT NULL default '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`user_id`,`group_id`),
  KEY `sf_guard_user_group_group_id_sf_guard_group_id` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `sf_guard_user_group`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sf_guard_user_permission`
--

CREATE TABLE IF NOT EXISTS `sf_guard_user_permission` (
  `user_id` bigint(20) NOT NULL default '0',
  `permission_id` bigint(20) NOT NULL default '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY  (`user_id`,`permission_id`),
  KEY `sf_guard_user_permission_permission_id_sf_guard_permission_id` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `sf_guard_user_permission`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_actividad`
--

CREATE TABLE IF NOT EXISTS `tipo_actividad` (
  `id_tipo_act` int(11) NOT NULL auto_increment,
  `nombre_tipo_act` varchar(150) default NULL,
  `estado_tipo_act` varchar(8) default NULL,
  `name1_tipo_act` varchar(255) default NULL,
  `name2_tipo_act` varchar(255) default NULL,
  PRIMARY KEY  (`id_tipo_act`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 AUTO_INCREMENT=7 ;

--
-- Volcar la base de datos para la tabla `tipo_actividad`
--

INSERT INTO `tipo_actividad` (`id_tipo_act`, `nombre_tipo_act`, `estado_tipo_act`, `name1_tipo_act`, `name2_tipo_act`) VALUES
(1, 'Rendicion de cuentas', 'ACTIVO', '', ''),
(2, 'paseo en aniversario', 'ACTIVO', '', ''),
(3, 'plantacion de arbolitos', 'ACTIVO', '', ''),
(4, 'reunion mensual', 'ACTIVO', '', ''),
(5, 'reunion mesa directiva', 'ACTIVO', '', ''),
(6, 'talleres para estudiantes', 'ACTIVO', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_centro_salud`
--

CREATE TABLE IF NOT EXISTS `tipo_centro_salud` (
  `id_tipo_cs` int(11) NOT NULL auto_increment,
  `nombre_tcs` varchar(100) NOT NULL,
  `estado_tcs` varchar(8) NOT NULL,
  `name1_tcs` varchar(255) default NULL,
  `name2_tcs` varchar(255) default NULL,
  PRIMARY KEY  (`id_tipo_cs`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `tipo_centro_salud`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_donacion`
--

CREATE TABLE IF NOT EXISTS `tipo_donacion` (
  `id_tipod` int(11) NOT NULL auto_increment,
  `nombre_tipod` varchar(80) NOT NULL,
  `estado_tipod` varchar(8) default NULL,
  `name1_tipod` varchar(255) default NULL,
  `name2_tipod` varchar(255) default NULL,
  PRIMARY KEY  (`id_tipod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Volcar la base de datos para la tabla `tipo_donacion`
--

INSERT INTO `tipo_donacion` (`id_tipod`, `nombre_tipod`, `estado_tipod`, `name1_tipod`, `name2_tipod`) VALUES
(1, 'Fresadas de invierno', 'ACTIVO', '', ''),
(2, 'Material escolar inicio de clases', 'ACTIVO', '', ''),
(3, 'Viveres 1ra entrega', 'ACTIVO', '', ''),
(4, 'Viveres 2ra entrega', 'ACTIVO', '', ''),
(5, 'consulta Odontologica 1ra', 'ACTIVO', '', ''),
(6, 'Material de limpieza 1ra entrega', 'ACTIVO', '', ''),
(7, 'material de servicio', 'ACTIVO', '', ''),
(8, 'calzados y zapatillas deportivas ', 'ACTIVO', '', ''),
(9, 'Material de limpieza dra entrega', 'ACTIVO', '', ''),
(10, 'consulta medica general', 'ACTIVO', '', ''),
(11, 'Viveres 3ra entrega', 'ACTIVO', '', ''),
(12, 'entrega de fin de a?o canaston', 'ACTIVO', '', ''),
(13, ' donacion especial', 'ACTIVO', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_especialidad`
--

CREATE TABLE IF NOT EXISTS `tipo_especialidad` (
  `id_esp` int(11) NOT NULL auto_increment,
  `nombre_esp` varchar(70) default NULL,
  `estado_esp` varchar(8) default NULL,
  `name1_esp` varchar(200) default NULL,
  `name2_esp` varchar(200) default NULL,
  PRIMARY KEY  (`id_esp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `tipo_especialidad`
--

