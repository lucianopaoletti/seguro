-- MariaDB dump 10.19-11.2.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: seguros
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `beneficios`
--

DROP TABLE IF EXISTS `beneficios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tasa` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficios`
--

LOCK TABLES `beneficios` WRITE;
/*!40000 ALTER TABLE `beneficios` DISABLE KEYS */;
INSERT INTO `beneficios` VALUES
(1,'Responsabilidad Civil',0.40),
(2,'Robo Total',0.03),
(3,'Robo Parcial',0.03),
(4,'Incendio Total',0.03),
(5,'Incendio Parcial',0.03),
(6,'Daño Total',0.03),
(7,'Daño Parciales',0.03),
(8,'Cristales',0.03),
(9,'Cerradura',0.03),
(10,'Granizo',0.03),
(11,'Reposición 0KM',0.03);
/*!40000 ALTER TABLE `beneficios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coberturas`
--

DROP TABLE IF EXISTS `coberturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coberturas` (
  `numero` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coberturas`
--

LOCK TABLES `coberturas` WRITE;
/*!40000 ALTER TABLE `coberturas` DISABLE KEYS */;
INSERT INTO `coberturas` VALUES
(1,'Responsabilidad Civil'),
(3,'Terceros Básicos'),
(4,'Terceros Completo'),
(6,'Todo Riesgo');
/*!40000 ALTER TABLE `coberturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coberturas_beneficios`
--

DROP TABLE IF EXISTS `coberturas_beneficios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coberturas_beneficios` (
  `cobertura_numero` int NOT NULL,
  `beneficio_id` int NOT NULL,
  PRIMARY KEY (`cobertura_numero`,`beneficio_id`),
  KEY `coberturas_beneficios_beneficio_FK` (`beneficio_id`),
  CONSTRAINT `coberturas_beneficios_beneficio_FK` FOREIGN KEY (`beneficio_id`) REFERENCES `beneficios` (`id`),
  CONSTRAINT `coberturas_beneficios_cobertura_FK` FOREIGN KEY (`cobertura_numero`) REFERENCES `coberturas` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coberturas_beneficios`
--

LOCK TABLES `coberturas_beneficios` WRITE;
/*!40000 ALTER TABLE `coberturas_beneficios` DISABLE KEYS */;
INSERT INTO `coberturas_beneficios` VALUES
(1,1),
(3,1),
(4,1),
(6,1),
(3,2),
(4,2),
(6,2),
(4,3),
(6,3),
(3,4),
(4,4),
(6,4),
(4,5),
(6,5),
(3,6),
(4,6),
(6,6),
(4,7),
(6,7),
(4,8),
(6,8),
(4,9),
(6,9),
(4,10),
(6,10),
(6,11);
/*!40000 ALTER TABLE `coberturas_beneficios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES
(1,'Toyota'),
(2,'Renault'),
(3,'Chevrolet');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelos`
--

DROP TABLE IF EXISTS `modelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modelos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `marca_id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `modelos_marcas_fk` (`marca_id`),
  CONSTRAINT `modelos_marcas_fk` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelos`
--

LOCK TABLES `modelos` WRITE;
/*!40000 ALTER TABLE `modelos` DISABLE KEYS */;
INSERT INTO `modelos` VALUES
(1,1,'Etios'),
(2,1,'Corolla'),
(3,1,'Yaris'),
(4,2,'Logan'),
(5,2,'Kangoo'),
(6,2,'Duster'),
(7,3,'Cruze'),
(8,3,'S10'),
(9,3,'Tracker');
/*!40000 ALTER TABLE `modelos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versiones`
--

DROP TABLE IF EXISTS `versiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versiones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `modelo_id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `versiones_modelos_fk` (`modelo_id`),
  CONSTRAINT `versiones_modelos_fk` FOREIGN KEY (`modelo_id`) REFERENCES `modelos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versiones`
--

LOCK TABLES `versiones` WRITE;
/*!40000 ALTER TABLE `versiones` DISABLE KEYS */;
INSERT INTO `versiones` VALUES
(1,1,'Sedán X 6 M/T'),
(2,1,'Sedán XLS Pack 6 M/T'),
(3,1,'Hatchback X 6 M/T'),
(4,2,'2.0 XLI MT'),
(5,2,'2.0 XLI CVT'),
(6,2,'2.0 SEG CVT'),
(7,3,'Sedán XLS 1.5 6 M/T 4P'),
(8,3,'Sedán XLS 1.5 CVT 4P'),
(9,3,'Hatchback S 1.5 CVT 5P'),
(10,4,'Life 1.6'),
(11,4,'Zen 1.6'),
(12,4,'Intens 1.6'),
(13,5,'Life SCe 1.6'),
(14,5,'Zen SCe 1.6'),
(15,5,'Stepway dCi 1.5'),
(16,5,'Stepway SCe 1.6'),
(17,6,'Intens 1.6'),
(18,6,'Intens 1.6 CVT'),
(19,6,'Iconic 1.3T CVT 4x2'),
(20,6,'Iconic 1.3T MT 4x4'),
(21,7,'Premier'),
(22,7,'LT AT'),
(23,7,'LTZ'),
(24,8,'CD 2.8 TD 4x4 Z71 AT'),
(25,8,'CS 2.8 TD 4x4 LS MT'),
(26,9,'Premier 2023');
/*!40000 ALTER TABLE `versiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versiones_anios_fabricacion`
--

DROP TABLE IF EXISTS `versiones_anios_fabricacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versiones_anios_fabricacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `version_id` int NOT NULL,
  `anio` int NOT NULL,
  `suma_asegurada` int NOT NULL,
  `suma_asegurada_0km` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `versiones_anios_fabricacion_versiones_FK` (`version_id`),
  CONSTRAINT `versiones_anios_fabricacion_versiones_FK` FOREIGN KEY (`version_id`) REFERENCES `versiones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versiones_anios_fabricacion`
--

LOCK TABLES `versiones_anios_fabricacion` WRITE;
/*!40000 ALTER TABLE `versiones_anios_fabricacion` DISABLE KEYS */;
INSERT INTO `versiones_anios_fabricacion` VALUES
(1,1,2023,10000000,11000000),
(2,1,2022,9000000,10000000),
(3,2,2023,11000000,12000000),
(4,2,2022,10000000,11000000),
(5,3,2023,10500000,11500000),
(6,3,2022,9500000,10500000),
(7,4,2023,10000000,11000000),
(8,4,2022,9000000,10000000),
(9,5,2023,10000000,11000000),
(10,5,2022,9000000,10000000),
(11,6,2023,10000000,11000000),
(12,6,2022,9000000,10000000),
(13,7,2023,10000000,11000000),
(14,8,2023,10000000,11000000),
(15,9,2023,10000000,11000000),
(16,10,2023,10000000,11000000),
(17,11,2023,10000000,11000000),
(18,12,2023,10000000,11000000),
(19,13,2023,10000000,11000000),
(20,14,2023,10000000,11000000),
(21,15,2023,10000000,11000000),
(22,16,2023,10000000,11000000),
(23,17,2023,10000000,11000000),
(24,18,2023,10000000,11000000),
(25,19,2023,10000000,11000000),
(26,20,2023,10000000,11000000),
(27,21,2023,10000000,11000000),
(28,22,2023,10000000,11000000),
(29,23,2023,10000000,11000000),
(30,24,2023,10000000,11000000),
(31,25,2023,10000000,11000000),
(32,26,2023,10000000,11000000);
/*!40000 ALTER TABLE `versiones_anios_fabricacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'seguros'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-12 19:05:37
