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
-- Table structure for table `membros`
--

DROP TABLE IF EXISTS `membros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membros` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `estadoCivil` varchar(50) DEFAULT NULL,
  `dataBatismo` date DEFAULT NULL,
  `ministerio` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CPF` (`CPF`)
) ENGINE=InnoDB AUTO_INCREMENT=437 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membros`
--

LOCK TABLES `membros` WRITE;
/*!40000 ALTER TABLE `membros` DISABLE KEYS */;
INSERT INTO `membros` VALUES (1,'João Silva','12345678901','1980-01-01','masculino','Casado','2000-01-01','Ministério A','Ativo'),(2,'Maria Souza','23456789012','1985-02-01','feminino','Solteiro','2005-02-01','Ministério B','Ativo'),(3,'Carlos Pereira','34567890123','1990-03-01','masculino','Divorciado','2010-03-01','Ministério C','Inativo'),(4,'Ana Oliveira','45678901234','1995-04-01','feminino','Viúvo','2015-04-01','Ministério D','Ativo'),(39,'Ana ','56789012345','1996-06-14','feminino','Solteira','2018-11-30','Louvor','Ativo'),(40,'Bruna Alves','67890123456','2000-01-25','feminino','Solteira','2023-01-05','Mídia','Inativo'),(41,'Paulo Ferreira','78901234567','1978-12-05','masculino','Casado','2017-08-22','Recepção','Afastado'),(42,'Fernanda Lima','89012345678','1993-03-29','feminino','Divorciada','2022-04-11','Intercessão','Ativo'),(43,'Leonardo Ramos','90123456789','1985-07-13','masculino','Casado','2021-06-18','Ensino','Ativo'),(44,'Juliana Azevedo','01234567890','1999-10-22','feminino','Solteira','2019-02-27','Evangelismo','Ativo'),(45,'Marcos Souza','11223344556','1990-07-20','masculino','Casado','2020-03-12','Louvor','Ativo'),(46,'Pedro Martins','22334455667','1988-11-05','masculino','Solteiro','2019-04-18','Evangelismo','Ativo'),(47,'Sofia Pereira','33445566778','1992-02-14','feminino','Casada','2021-09-10','Ensino','Ativo'),(48,'Ricardo Silva','44556677889','1985-08-20','masculino','Divorciado','2018-07-30','Recepção','Inativo'),(49,'Isabela Rocha','55667788990','1998-04-25','feminino','Solteira','2022-02-15','Intercessão','Ativo'),(50,'Fernando Costa','66778899001','1982-10-05','masculino','Casado','2020-12-01','Mídia','Ativo'),(51,'Carolina Dias','77889900112','2001-06-30','feminino','Solteira','2023-04-08','Louvor','Afastado'),(52,'Camila Rodrigues','88990011223','1994-09-12','feminino','Casada','2019-11-05','Ensino','Ativo'),(53,'Eduardo Lima','99001122334','1987-03-17','masculino','Casado','2021-08-20','Evangelismo','Ativo'),(54,'Larissa Ferreira','10111213141','1999-12-01','feminino','Solteira','2018-05-25','Intercessão','Ativo'),(55,'Lucas Almeida','12121212121','1991-08-20','masculino','Casado','2023-05-15','Louvor','Ativo'),(56,'Tiago Mendes','13131313131','1989-07-12','masculino','Divorciado','2022-11-30','Ensino','Inativo'),(57,'Mariana Ferreira','14141414141','1993-12-05','feminino','Casada','2021-06-20','Intercessão','Ativo'),(58,'Gabriel Souza','15151515151','1984-04-10','masculino','Casado','2020-03-25','Evangelismo','Ativo'),(59,'Beatriz Ramos','16161616161','1996-09-30','feminino','Solteira','2019-09-18','Mídia','Afastado'),(60,'Vinicius Carvalho','17171717171','2002-01-25','masculino','Solteiro','2023-01-10','Recepção','Ativo'),(61,'Camila Ferreira','18181818181','1990-10-15','feminino','Casada','2020-12-05','Louvor','Inativo'),(62,'Renato Castro','19191919191','1995-05-05','masculino','Casado','2022-07-22','Ensino','Ativo'),(63,'Fábio Lima','20202020202','1987-03-03','masculino','Divorciado','2021-04-12','Intercessão','Ativo'),(64,'Lara Pires','21212121212','2001-11-11','feminino','Solteira','2023-08-01','Mídia','Ativo'),(65,'Alexandre ','22233344455','1982-06-10','masculino','Casado','2019-02-14','Louvor','Ativo'),(66,'Natália Souza','33344455566','1994-01-22','feminino','Solteira','2020-07-20','Intercessão','Ativo'),(67,'Francisco Oliveira','44455566677','1980-09-18','masculino','Viúvo','2018-11-11','Ensino','Inativo'),(68,'Henrique Silva','55566677788','1990-05-30','masculino','Casado','2021-06-05','Evangelismo','Ativo'),(69,'Juliana Mendes','66677788899','1998-12-15','feminino','Solteira','2022-03-25','Louvor','Ativo'),(70,'Eduardo Carvalho','77788899900','1975-03-28','masculino','Divorciado','2017-09-10','Recepção','Inativo'),(71,'Bruno Rocha','88899900011','1992-07-05','masculino','Casado','2019-12-18','Mídia','Ativo'),(72,'Renata Lima','99900011122','2000-02-21','feminino','Solteira','2023-05-08','Evangelismo','Afastado'),(73,'Patrícia Ferreira','00011122233','1983-11-09','feminino','Casada','2016-08-14','Intercessão','Ativo'),(74,'Rodrigo Almeida','11122233344','1987-04-12','masculino','Casado','2020-10-30','Ensino','Ativo'),(135,'Lucas Almeida','11293349556','2010-03-21','masculino','Solteiro','2021-08-15','Infantil','Ativo'),(136,'Sofia Martins','22394455667','2015-07-30','feminino','Solteiro','2023-01-10','Infantil','Ativo'),(137,'Guilherme Costa','33495566978','2008-12-12','masculino','Solteiro','2019-04-05','Adolescente','Ativo'),(138,'Camila Rodrigues','44556977889','1996-06-18','feminino','Solteiro','2014-10-22','Jovens','Ativo'),(139,'Bruno Ferreira','55697789990','1992-11-05','masculino','Casado','2010-06-30','Louvor','Ativo'),(140,'Vanessa Lima','66779899001','2001-04-28','feminino','Solteiro','2019-09-15','Missões','Ativo'),(141,'Rafael Souza','77889900912','1985-09-03','masculino','Casado','2003-02-19','Ensino','Ativo'),(142,'Tatiane Melo','88999011243','1981-02-14','feminino','Casado','1998-11-25','Pastoral','Ativo'),(143,'Henrique Duarte','99701124334','1976-08-20','masculino','Divorciado','1995-06-10','Louvor','Ativo'),(144,'Eduardo Batista','10111423344','1961-05-06','masculino','Casado','1980-03-18','Conselho','Ativo'),(145,'Marlene Castro','11121331485','1954-09-25','feminino','Viúvo','1974-07-12','Assistência','Ativo'),(146,'Sebastião Moreira','12141441556','1949-12-11','masculino','Casado','1969-05-08','Missões','Ativo'),(147,'Adriano Costa','33445566789','1987-08-15','masculino','Casado','2015-09-10','Louvor','Ativo'),(148,'Bianca Almeida','55667788912','1995-03-22','feminino','Solteira','2021-07-05','Mídia','Ativo'),(149,'Cláudio Ramos','66778899023','1972-11-30','masculino','Viúvo','1998-06-25','Conselho','Ativo'),(150,'Daniela Rocha','77889900134','2000-05-18','feminino','Solteira','2019-10-15','Jovens','Ativo'),(151,'Eduardo Mendes','88990011245','1989-02-14','masculino','Casado','2012-03-28','Evangelismo','Ativo'),(152,'Fernanda Souza','99001122356','1993-07-09','feminino','Casada','2020-09-20','Intercessão','Ativo'),(153,'Gabriel Santos','10111213148','1985-09-23','masculino','Divorciado','2018-04-11','Recepção','Inativo'),(154,'Helena Martins','11223344557','1991-12-02','feminino','Solteira','2022-08-07','Ensino','Ativo'),(155,'Igor Batista','22334455668','1997-06-11','masculino','Casado','2021-05-14','Infantil','Ativo'),(156,'Juliana Costa','33445566779','1994-10-21','feminino','Solteira','2019-03-30','Pastoral','Ativo'),(167,'Erick Ferreira','69487619274','1985-04-29','masculino','Solteiro','2010-08-19','Ministério D','Inativo'),(168,'Emanuella Aragão','68673229646','1953-07-15','masculino','Casado','2001-12-07','Mídia','Ativo'),(169,'Gabriel Pires','11372418433','1971-11-16','feminino','Solteiro','2018-08-13','Recepção','Inativo'),(170,'Nicole da Cunha','72413144043','2012-04-22','masculino','Solteiro','2022-04-28','Jovens','Afastado'),(171,'Lavínia Correia','30265472613','1944-09-04','feminino','Divorciado','2001-10-14','Ministério C','Inativo'),(172,'Carolina Pires','24688444944','1993-05-06','feminino','Casado','2010-12-15','Evangelismo','Afastado'),(173,'Sr. Luiz Henrique Ramos','61239017156','1948-07-19','feminino','Solteiro','2020-05-05','Intercessão','Ativo'),(174,'Marcos Vinicius Rocha','10613349650','1978-07-23','feminino','Viúvo','2009-03-08','Mídia','Ativo'),(175,'Olivia Santos','52233944954','1964-07-28','feminino','Divorciado','2016-08-09','Louvor','Ativo'),(176,'Ana Beatriz Barros','46048705531','2011-01-29','masculino','Divorciado','2024-10-05','Ministério C','Ativo'),(177,'Sr. Thomas Martins','29927059889','2003-05-27','feminino','Solteiro','2011-06-18','Ministério C','Ativo'),(178,'Paulo Oliveira','98059475296','1986-01-20','masculino','Solteiro','2022-05-22','Ministério A','Inativo'),(179,'Felipe Silveira','60835524020','2009-06-29','feminino','Viúvo','2022-03-11','Jovens','Afastado'),(180,'Beatriz Nunes','79957380553','1967-08-08','feminino','Casado','2004-12-27','Mídia','Ativo'),(181,'Ana Júlia Porto','84362186834','1986-02-08','masculino','Casado','2012-01-23','Intercessão','Afastado'),(182,'João Vitor Pereira','47367663378','1992-05-19','feminino','Viúvo','2006-01-25','Missões','Inativo'),(183,'Dra. Stephany Mendes','61527340823','1989-10-25','masculino','Viúvo','2012-11-28','Recepção','Afastado'),(184,'Luigi da Mota','67017255569','1994-03-30','masculino','Casado','2024-03-20','Ministério D','Ativo'),(185,'Bruna Pinto','44994020257','1957-07-26','feminino','Viúvo','2010-08-06','Ministério A','Inativo'),(186,'Srta. Isis Souza','42892990966','1986-10-14','masculino','Viúvo','2005-03-08','Ministério A','Ativo'),(187,'Olivia Barros','74438638508','2000-12-10','feminino','Casado','2013-09-18','Pastoral','Afastado'),(188,'Dra. Maitê Martins','57718738105','1972-09-18','masculino','Viúvo','2021-01-15','Ensino','Inativo'),(189,'Maria Sophia Costela','40801794201','1992-01-23','masculino','Solteiro','1998-05-29','Evangelismo','Inativo'),(190,'Joaquim Nogueira','24299022043','1993-12-15','feminino','Divorciado','2001-08-04','Intercessão','Inativo'),(191,'Enzo Gabriel Cardoso','71459081337','1960-06-09','masculino','Divorciado','2018-09-15','Intercessão','Afastado'),(192,'Levi da Mota','48889582285','1991-05-26','masculino','Casado','2005-11-18','Infantil','Ativo'),(193,'Raul Pires','87285384883','1954-07-27','masculino','Casado','2024-10-09','Ministério B','Ativo'),(194,'Ana Júlia Caldeira','15312404145','1969-03-08','feminino','Viúvo','2012-02-12','Jovens','Ativo'),(195,'Dra. Pietra Lopes','18045430830','1990-08-31','feminino','Casado','2007-08-15','Louvor','Ativo'),(196,'João Guilherme Duarte','27190373427','1978-01-21','feminino','Divorciado','2018-08-18','Recepção','Ativo'),(197,'Benício Moura','53658004025','1986-01-23','masculino','Solteiro','2018-06-08','Infantil','Inativo'),(198,'Fernando Gonçalves','42598652484','1997-12-14','feminino','Casado','1997-02-18','Jovens','Ativo'),(199,'Luiz Henrique Monteiro','68397110404','1990-11-12','masculino','Viúvo','2005-12-06','Louvor','Inativo'),(200,'Ana Clara Cardoso','56559303254','1949-10-23','feminino','Viúvo','2014-11-29','Ensino','Afastado'),(201,'Ryan Silva','17472756483','1981-04-24','feminino','Divorciado','2008-01-27','Ministério C','Ativo'),(202,'Agatha Caldeira','11685539113','1996-12-07','feminino','Divorciado','1995-08-20','Infantil','Inativo'),(203,'Gabriel Almeida','19630371852','1980-07-17','feminino','Viúvo','2016-08-22','Jovens','Inativo'),(204,'Sophia Alves','22883798762','1953-10-18','masculino','Divorciado','1999-08-24','Evangelismo','Ativo'),(205,'Laura Souza','15864844302','1997-07-25','masculino','Viúvo','1995-06-18','Mídia','Inativo'),(206,'Ian Rocha','38921102544','1974-05-11','feminino','Divorciado','2020-03-01','Missões','Afastado'),(207,'Luiz Miguel Caldeira','43101627479','2001-12-22','feminino','Solteiro','2023-11-14','Ministério D','Afastado'),(208,'Gabriel Silveira','49404933430','1998-08-08','masculino','Viúvo','2023-01-24','Pastoral','Ativo'),(209,'Srta. Camila Barbosa','30976870085','2011-02-27','feminino','Casado','1997-07-25','Mídia','Inativo'),(210,'Juan Fogaça','49423378674','1953-02-22','feminino','Viúvo','2020-09-18','Ministério C','Ativo'),(211,'Júlia Ribeiro','40630675619','1973-02-09','feminino','Solteiro','2025-01-15','Mídia','Ativo'),(212,'Pedro Gonçalves','77742849584','2011-12-14','masculino','Solteiro','2011-06-24','Missões','Inativo'),(213,'Dra. Lavínia Azevedo','50509116889','1986-07-21','feminino','Viúvo','1997-04-27','Ministério A','Afastado'),(214,'Ana Lívia das Neves','30452555016','2003-02-06','feminino','Solteiro','1998-03-18','Missões','Inativo'),(215,'Luigi Santos','88427366665','1954-10-02','masculino','Solteiro','2017-02-17','Missões','Afastado'),(216,'Felipe Viana','73815059049','1962-02-01','masculino','Divorciado','2015-01-16','Ministério A','Afastado'),(227,'Dra. Maria Julia Rocha','82272932551','1993-01-14','masculino','Solteiro','1996-03-14','Pastoral','Afastado'),(228,'Vitor Carvalho','52989204306','2001-05-26','feminino','Solteiro','2019-11-14','Ministério D','Ativo'),(229,'Ian Sales','77393624646','1962-06-05','masculino','Solteiro','2016-11-19','Assistência','Ativo'),(230,'Davi Luiz Cardoso','98417724474','1947-09-01','masculino','Viúvo','2023-07-10','Ministério C','Inativo'),(231,'André das Neves','35490923742','1960-11-01','masculino','Solteiro','2005-01-29','Louvor','Afastado'),(232,'Noah Jesus','39149215670','2006-03-04','masculino','Solteiro','2003-04-09','Ministério A','Inativo'),(233,'Miguel Monteiro','96339448453','2014-09-17','masculino','Viúvo','2019-10-20','Infantil','Afastado'),(234,'Marcelo Vieira','51918476716','1965-04-22','masculino','Solteiro','2008-04-27','Jovens','Afastado'),(235,'Bryan Porto','99387562185','1953-06-16','masculino','Solteiro','2020-03-15','Jovens','Afastado'),(236,'Manuela Souza','93143438728','1973-02-17','masculino','Casado','2020-11-27','Intercessão','Afastado'),(237,'Sra. Ana Lívia Peixoto','68639051017','1954-09-10','masculino','Divorciado','2008-01-08','Recepção','Inativo'),(238,'Ana Duarte','77622900318','1958-05-14','feminino','Viúvo','2020-03-13','Missões','Afastado'),(239,'Benício da Rosa','97682813709','1994-10-27','masculino','Casado','2012-10-18','Mídia','Ativo'),(240,'Bruna Barros','23279325595','1999-03-07','feminino','Casado','2003-02-12','Ensino','Afastado'),(241,'Luiz Otávio Rocha','20323461537','1988-05-24','masculino','Solteiro','2024-06-02','Evangelismo','Inativo'),(242,'Emilly Silveira','48653284676','2007-02-12','feminino','Viúvo','2003-09-23','Ministério A','Inativo'),(243,'João Pedro Lima','76066213714','2002-12-02','masculino','Divorciado','2002-12-01','Evangelismo','Ativo'),(244,'Dra. Maria Fernanda Castro','90589574093','2013-02-03','feminino','Divorciado','2003-06-07','Louvor','Afastado'),(245,'Lucas Gabriel da Paz','71421581231','1969-05-26','masculino','Divorciado','1999-02-08','Mídia','Ativo'),(246,'Beatriz Cardoso','60706749104','1946-10-28','feminino','Solteiro','2021-10-11','Intercessão','Inativo'),(247,'Ana Laura Novaes','30040523214','1951-11-08','feminino','Solteiro','1997-08-15','Jovens','Afastado'),(248,'Camila Cavalcanti','39216434232','1976-05-27','feminino','Divorciado','2015-10-08','Ministério D','Afastado'),(249,'Bruno Viana','56092008622','1958-02-16','feminino','Casado','2022-10-21','Assistência','Ativo'),(250,'Raquel Sales','34867314933','1950-06-12','feminino','Divorciado','2017-01-21','Assistência','Afastado'),(251,'Vinicius Gomes','82413004558','1951-07-09','masculino','Divorciado','2017-12-28','Ministério A','Afastado'),(252,'Letícia da Rocha','39278307977','2008-09-19','masculino','Divorciado','2006-12-01','Recepção','Inativo'),(253,'Daniela da Costa','67476879136','2004-02-01','feminino','Viúvo','2005-07-05','Assistência','Inativo'),(254,'Dr. João Felipe Mendes','64250317966','1956-03-09','masculino','Casado','1997-02-09','Ministério C','Ativo'),(255,'Yago Castro','55232421658','1968-01-03','masculino','Divorciado','2012-12-07','Ministério D','Inativo'),(256,'Sr. Vitor Nascimento','43885466265','1992-03-20','masculino','Casado','1996-01-26','Louvor','Afastado'),(257,'Ana Luiza da Cunha','23860605685','1989-08-18','feminino','Divorciado','2000-03-24','Assistência','Inativo'),(258,'Alexia Dias','39776973142','1959-06-01','masculino','Viúvo','2005-02-26','Recepção','Ativo'),(259,'Ana Vitória Monteiro','44650449987','2010-06-08','masculino','Viúvo','2015-08-05','Ministério D','Inativo'),(260,'Joana Nunes','56380827416','1961-10-13','masculino','Viúvo','2018-10-17','Ministério B','Inativo'),(261,'Pietra Melo','78318496753','1968-05-23','masculino','Casado','2022-10-17','Recepção','Ativo'),(262,'Sr. Diego Ribeiro','81914893678','1999-03-25','feminino','Divorciado','2011-02-26','Ministério B','Inativo'),(263,'Luiz Otávio Cunha','68843775090','1963-03-27','masculino','Solteiro','2021-02-26','Ensino','Afastado'),(264,'Daniela Oliveira','94428870556','1960-07-23','masculino','Casado','2008-12-01','Ensino','Inativo'),(265,'Caroline das Neves','59965619585','1973-09-18','masculino','Solteiro','2003-04-18','Ensino','Ativo'),(266,'Antônio Ramos','33952444196','1944-02-25','masculino','Solteiro','2023-12-22','Ministério B','Inativo'),(267,'Danilo das Neves','32646870804','1975-10-07','masculino','Divorciado','2007-08-29','Pastoral','Afastado'),(268,'Natália Costa','57915356569','1968-08-20','feminino','Casado','2017-01-03','Ministério A','Inativo'),(269,'Isabel da Conceição','56199521220','1990-03-15','feminino','Solteiro','2016-06-10','Recepção','Inativo'),(270,'Enzo Gabriel Porto','41456099341','1985-04-15','feminino','Solteiro','2022-09-05','Ministério D','Ativo'),(271,'Francisco da Mota','96039790027','1972-03-12','feminino','Divorciado','2013-12-25','Missões','Ativo'),(272,'Bárbara Rezende','97730663752','1994-03-12','feminino','Solteiro','1996-04-21','Recepção','Afastado'),(273,'Dr. Theo Cavalcanti','90571949926','1974-07-26','masculino','Solteiro','2023-08-14','Ministério B','Inativo'),(274,'Alexandre Rodrigues','86313299647','1964-10-10','feminino','Casado','2015-06-18','Evangelismo','Afastado'),(275,'Sr. Marcos Vinicius Azevedo','42722629896','1955-06-03','feminino','Viúvo','2009-02-07','Jovens','Ativo'),(276,'Yago Moraes','10538351866','1969-09-16','feminino','Viúvo','2004-11-22','Ensino','Ativo'),(277,'Lucas Gabriel Monteiro','48712046047','1968-07-04','feminino','Solteiro','2017-09-16','Ministério D','Inativo'),(278,'Lara Lima','71253002489','2007-04-01','feminino','Casado','2020-02-21','Ministério C','Afastado'),(279,'Kamilly Sales','31957004617','2002-12-29','masculino','Casado','2008-06-14','Ensino','Ativo'),(280,'Felipe Cardoso','64302126188','2006-07-26','feminino','Casado','2017-08-16','Evangelismo','Inativo'),(281,'Juliana Caldeira','48749320997','1953-11-26','feminino','Solteiro','2020-08-08','Pastoral','Afastado'),(282,'Bernardo da Mota','42826342565','1988-07-13','masculino','Casado','2020-01-03','Recepção','Inativo'),(283,'Ana Vitória Ferreira','15250180293','2005-06-11','feminino','Viúvo','2011-10-17','Ensino','Afastado'),(284,'Gabriela Cunha','61345591904','1949-10-25','feminino','Solteiro','2005-10-28','Louvor','Ativo'),(285,'Luiz Gustavo Martins','37602737837','1998-06-28','masculino','Casado','2005-07-29','Infantil','Inativo'),(286,'Sr. Marcelo Oliveira','73304651688','2004-02-15','feminino','Viúvo','2010-01-05','Ministério C','Ativo'),(287,'Dr. Vitor Hugo Farias','34065150085','2014-05-14','feminino','Divorciado','1996-07-10','Jovens','Afastado'),(288,'Dr. Noah Fernandes','78959615416','1955-07-25','masculino','Viúvo','1998-09-06','Mídia','Ativo'),(289,'Sr. João Gabriel Rocha','33644577556','1992-04-17','feminino','Viúvo','2011-11-15','Louvor','Afastado'),(290,'Pedro Henrique Barbosa','55259040619','2009-10-19','masculino','Solteiro','2021-05-07','Assistência','Inativo'),(291,'Srta. Ana Luiza Rocha','73352457132','1961-11-14','masculino','Divorciado','2011-01-24','Ministério D','Afastado'),(292,'Yasmin Aragão','44117697727','1980-10-09','feminino','Divorciado','2013-11-28','Recepção','Ativo'),(293,'Milena Oliveira','63885469020','1991-06-07','masculino','Viúvo','2019-03-21','Infantil','Inativo'),(294,'Agatha Gonçalves','90964500391','1948-01-14','feminino','Solteiro','1995-05-21','Louvor','Afastado'),(295,'Rafael Gonçalves','34139884338','1986-07-11','feminino','Divorciado','2007-05-13','Pastoral','Ativo'),(296,'Luana Campos','39209205051','1945-05-20','masculino','Divorciado','2003-12-13','Jovens','Inativo'),(297,'Sabrina Aragão','94453565857','2013-09-19','masculino','Divorciado','2020-04-16','Pastoral','Inativo'),(298,'Emanuella Cardoso','28767367853','1960-10-11','masculino','Viúvo','2009-02-07','Assistência','Ativo'),(299,'Sr. João Miguel Rezende','92815767318','1998-09-24','feminino','Divorciado','2002-05-07','Ensino','Afastado'),(300,'Maitê da Rocha','87568515561','1983-08-10','feminino','Casado','2017-08-12','Ministério B','Ativo'),(301,'Giovanna Rezende','57919421424','1977-06-28','feminino','Casado','2002-11-27','Ensino','Inativo'),(302,'Nina Ramos','91810458151','1993-06-19','masculino','Solteiro','2008-12-06','Mídia','Inativo'),(303,'Gabrielly Barros','31924667802','1969-07-20','masculino','Casado','2010-07-29','Ministério C','Ativo'),(304,'Enzo Gabriel da Mota','64433858846','1968-11-06','masculino','Solteiro','2020-02-15','Infantil','Inativo'),(305,'Luana Farias','88477527900','2004-11-06','masculino','Viúvo','2009-08-10','Evangelismo','Afastado'),(306,'Maria Julia Moraes','70751135067','1979-09-19','masculino','Casado','2021-01-05','Intercessão','Ativo'),(307,'Laura Almeida','26660838189','1982-03-26','feminino','Divorciado','1999-03-15','Ensino','Inativo'),(308,'Srta. Letícia Cardoso','92255662715','2004-09-22','masculino','Casado','2023-06-09','Assistência','Inativo'),(309,'Noah Rocha','61641521220','1973-03-12','masculino','Solteiro','2025-01-29','Ensino','Ativo'),(310,'Noah Silveira','46042339221','1980-06-19','feminino','Solteiro','2018-09-17','Ministério D','Inativo'),(311,'Srta. Stephany Porto','40506151397','1973-09-12','masculino','Casado','1998-11-11','Mídia','Ativo'),(312,'Bruna da Costa','64708356652','1999-10-27','masculino','Solteiro','1997-02-06','Missões','Inativo'),(313,'Daniel Vieira','19430376790','1955-02-19','feminino','Casado','2016-10-28','Ensino','Ativo'),(314,'João Guilherme Campos','76210389488','1986-05-07','masculino','Casado','2015-08-28','Ministério C','Afastado'),(315,'Caio da Rocha','75451044881','1983-03-23','feminino','Casado','2001-07-28','Ensino','Ativo'),(316,'Ian da Mata','85205483358','1989-05-23','feminino','Divorciado','1996-01-28','Missões','Ativo'),(317,'Sr. Henrique Cunha','88209744943','1984-06-25','feminino','Casado','2001-11-06','Ministério D','Inativo'),(318,'Sra. Ana Luiza Aragão','58764404822','1963-05-01','masculino','Divorciado','2008-03-12','Ministério C','Ativo'),(319,'Lara Teixeira','46533061339','1972-10-30','masculino','Divorciado','2024-01-28','Evangelismo','Ativo'),(320,'Ana Beatriz Dias','17245174567','2010-06-08','masculino','Casado','2007-07-23','Ministério A','Ativo'),(321,'João Felipe Jesus','31032981308','1952-06-27','feminino','Solteiro','2003-09-02','Ministério C','Ativo'),(322,'Sr. Juan Novaes','85827798092','1960-06-14','feminino','Divorciado','2004-03-14','Intercessão','Inativo'),(323,'Isis Moraes','30343899190','1961-05-02','feminino','Casado','2018-07-11','Ministério D','Afastado'),(324,'Pietro Duarte','17420231529','2002-11-11','masculino','Viúvo','2008-12-31','Ensino','Inativo'),(325,'Lara Porto','89221123259','2001-04-01','masculino','Viúvo','2008-06-06','Mídia','Afastado'),(326,'Emilly Moraes','19077234280','1966-11-24','masculino','Viúvo','2020-01-22','Louvor','Afastado');
/*!40000 ALTER TABLE `membros` ENABLE KEYS */;
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
