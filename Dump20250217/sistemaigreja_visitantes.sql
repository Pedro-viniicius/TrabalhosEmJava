-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: sistemaigreja
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `visitantes`
--

DROP TABLE IF EXISTS `visitantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visitantes` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `data_visita` date DEFAULT NULL,
  `origem` varchar(255) DEFAULT NULL,
  `telefone1` varchar(15) DEFAULT NULL,
  `telefone2` varchar(15) DEFAULT NULL,
  `interesse` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitantes`
--

LOCK TABLES `visitantes` WRITE;
/*!40000 ALTER TABLE `visitantes` DISABLE KEYS */;
INSERT INTO `visitantes` VALUES (1,'Visitante A','2023-01-01','Origem A','11987654321','11987654322','Interesse A'),(2,'Visitante B','2023-02-01','Origem B','11987654323','11987654324','Interesse B'),(3,'Visitante C','2023-03-01','Origem C','11987654325','11987654326','Interesse C'),(4,'Visitante D','2023-04-01','Origem D','11987654327','11987654328','Interesse D'),(5,'João Silva','2025-02-05','São Paulo','11987654321','11987654322','Turismo'),(6,'Maria Souza','2025-02-03','Rio de Janeiro','21987654323','21987654324','Negócios'),(7,'Carlos Pereira','2025-02-25','Belo Horizonte','31987654325','31987654326','Cultura'),(8,'Ana Costa','2025-02-25','Curitiba','41987654327','41987654328','Educação'),(9,'Pedro Alves','2025-02-22','Porto Alegre','51987654329','51987654330','Saúde'),(10,'Isabela Oliveira','2025-02-20','Fortaleza','85987654331','85987654332','Tecnologia'),(11,'Rafael Santos','2025-02-18','Recife','81987654333','81987654334','Esportes'),(12,'Lúcia Ferreira','2023-09-15','Salvador','71987654335','71987654336','Arte'),(13,'Bruno Lima','2023-09-12','Goiânia','62987654337','62987654338','Ciência'),(14,'Fernanda Gomes','2023-09-10','Manaus','92987654339','92987654340','Meio Ambiente');
/*!40000 ALTER TABLE `visitantes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-17 14:02:53
