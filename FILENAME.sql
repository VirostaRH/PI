-- MySQL dump 10.13  Distrib 5.7.18, for Linux (i686)
--
-- Host: localhost    Database: PROYECTO
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `OT`
--

DROP TABLE IF EXISTS `OT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OT` (
  `id_OT` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_OT` varchar(100) NOT NULL,
  `descripcion_OT` varchar(100) NOT NULL,
  `id_centro` int(11) NOT NULL,
  PRIMARY KEY (`id_OT`),
  KEY `fk_OT_centro` (`id_centro`),
  CONSTRAINT `fk_OT_centro` FOREIGN KEY (`id_centro`) REFERENCES `centro` (`id_centro`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OT`
--

LOCK TABLES `OT` WRITE;
/*!40000 ALTER TABLE `OT` DISABLE KEYS */;
INSERT INTO `OT` VALUES (10,'Apaga fuegos','Solucionador de marrones en barra',5);
/*!40000 ALTER TABLE `OT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RRSS`
--

DROP TABLE IF EXISTS `RRSS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RRSS` (
  `id_rrss` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(200) NOT NULL,
  `id_alumno` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id_rrss`),
  KEY `fk_redes_sociales` (`id_alumno`),
  CONSTRAINT `fk_redes_sociales` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RRSS`
--

LOCK TABLES `RRSS` WRITE;
/*!40000 ALTER TABLE `RRSS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RRSS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adquiere_aptitud`
--

DROP TABLE IF EXISTS `adquiere_aptitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adquiere_aptitud` (
  `id_alumno` varchar(9) NOT NULL,
  `id_aptitud` int(11) NOT NULL,
  `nivel` int(1) DEFAULT NULL,
  PRIMARY KEY (`id_alumno`,`id_aptitud`),
  KEY `fk_adquiere_aptitud` (`id_aptitud`),
  CONSTRAINT `fk_adquiere_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_adquiere_aptitud` FOREIGN KEY (`id_aptitud`) REFERENCES `aptitud` (`id_aptitud`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adquiere_aptitud`
--

LOCK TABLES `adquiere_aptitud` WRITE;
/*!40000 ALTER TABLE `adquiere_aptitud` DISABLE KEYS */;
/*!40000 ALTER TABLE `adquiere_aptitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `id_alumno` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido1` varchar(20) NOT NULL,
  `apellido2` varchar(20) NOT NULL,
  `f_nac` date DEFAULT NULL,
  `direccion` varchar(200) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  `cp` int(5) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `telefono` int(9) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `email2` varchar(100) DEFAULT NULL,
  `disponibilidad` tinyint(1) NOT NULL,
  `observaciones` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id_alumno`),
  CONSTRAINT `clave_de_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES ('00000001A','Fran','Rojas','Cabrillán','1988-02-22','Calle Lejano Oriente 11, 1ª','Sevilla',41023,'Sevilla',954332214,'Frak_el_rojo@gmail.com','',1,''),('00000002A','Pedro','Pedrola','Segurola','1922-12-12','Calle Negra 12, 2ª','Sevilla',41009,'Sevilla',954332214,'Pedrola_rules@popopo.com','',1,'Nah');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aptitud`
--

DROP TABLE IF EXISTS `aptitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aptitud` (
  `id_aptitud` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_aptitud` varchar(45) NOT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_aptitud`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aptitud`
--

LOCK TABLES `aptitud` WRITE;
/*!40000 ALTER TABLE `aptitud` DISABLE KEYS */;
INSERT INTO `aptitud` VALUES (17,'Evaluación trabajadores','Dotes de liderazgo'),(18,'Gestión de grupos en discapacidad','Guiado, cuidado y atención discapacitados'),(19,' Evaluación trabajadores','Dotes de liderazgo'),(20,'Crear llaveros con cadenas','Pos eso');
/*!40000 ALTER TABLE `aptitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centro`
--

DROP TABLE IF EXISTS `centro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centro` (
  `id_centro` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_centro` varchar(100) NOT NULL,
  PRIMARY KEY (`id_centro`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centro`
--

LOCK TABLES `centro` WRITE;
/*!40000 ALTER TABLE `centro` DISABLE KEYS */;
INSERT INTO `centro` VALUES (1,'Martinez montañes'),(2,'IES PUNTA DEL VERDE'),(3,'IES POLIGONO SUR'),(4,'a'),(5,'IES POLIG SUR'),(6,'IES CALLE');
/*!40000 ALTER TABLE `centro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciclo`
--

DROP TABLE IF EXISTS `ciclo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciclo` (
  `id_ciclo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_ciclo` varchar(49) NOT NULL,
  `siglas` varchar(4) NOT NULL,
  PRIMARY KEY (`id_ciclo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciclo`
--

LOCK TABLES `ciclo` WRITE;
/*!40000 ALTER TABLE `ciclo` DISABLE KEYS */;
INSERT INTO `ciclo` VALUES (1,'Asistencia a Personas en Situación de Dependencia','APSD'),(2,'Animación Sociocultural y Turismo','ASCT'),(3,'Administración de Sistemas Informáticos en Red','ASIR'),(4,'Desarrollo de aplicaciones web','DAW'),(5,'Electromecánica de Vehículos Automóviles','EVA'),(6,'Gestión Administrativa','GA'),(7,'Instalaciones Eléctricas y Automáticas','IEA'),(8,'Mediación Comunicativa','MCO'),(9,'Sistemas Microinformáticos y redes','SMR'),(10,'Sistemas de Telecomunicación Informáticos','STI');
/*!40000 ALTER TABLE `ciclo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursa_OT`
--

DROP TABLE IF EXISTS `cursa_OT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursa_OT` (
  `id_OT` int(11) NOT NULL,
  `id_alumno` varchar(9) NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_OT`,`id_alumno`),
  KEY `fk_cursa_OT_alumno` (`id_alumno`),
  CONSTRAINT `fk_cursa_OT_OT` FOREIGN KEY (`id_OT`) REFERENCES `OT` (`id_OT`),
  CONSTRAINT `fk_cursa_OT_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursa_OT`
--

LOCK TABLES `cursa_OT` WRITE;
/*!40000 ALTER TABLE `cursa_OT` DISABLE KEYS */;
/*!40000 ALTER TABLE `cursa_OT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursa_ciclo`
--

DROP TABLE IF EXISTS `cursa_ciclo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursa_ciclo` (
  `id_alumno` varchar(9) NOT NULL,
  `id_ciclo` int(11) NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `centro` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id_alumno`,`id_ciclo`),
  KEY `fk_cursa_ciclo_ciclo` (`id_ciclo`),
  CONSTRAINT `fk_cursa_ciclo_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumno` (`id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cursa_ciclo_ciclo` FOREIGN KEY (`id_ciclo`) REFERENCES `ciclo` (`id_ciclo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursa_ciclo`
--

LOCK TABLES `cursa_ciclo` WRITE;
/*!40000 ALTER TABLE `cursa_ciclo` DISABLE KEYS */;
INSERT INTO `cursa_ciclo` VALUES ('00000001A',4,'2002-07-22',NULL);
/*!40000 ALTER TABLE `cursa_ciclo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` varchar(9) NOT NULL,
  `password` varchar(25) NOT NULL,
  `rol_alumno` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('00000000A','root',1),('00000001A','ROOT',0),('00000002A','11111',0),('00000003A','11111',0),('00000004A','22222',0),('00000005A','22222',0),('00000006A','22222',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-19 21:45:28
