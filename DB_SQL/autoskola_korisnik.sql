-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: autoskola
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `KORISNIK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ULOGA_ID` int(11) NOT NULL,
  `IME` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `PREZIME` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `KORISNICKO_IME` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `EMAIL` varchar(45) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `LOZINKA` varchar(128) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `OIB` varchar(20) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `ORDINAL` int(11) DEFAULT '1',
  `DELETED` int(11) DEFAULT '0',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `MODIFICATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`KORISNIK_ID`),
  UNIQUE KEY `KORISNICKO_IME_UNIQUE` (`KORISNICKO_IME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `FK_ULOGA` (`ULOGA_ID`),
  CONSTRAINT `FK_ULOGA` FOREIGN KEY (`ULOGA_ID`) REFERENCES `uloge` (`ULOGA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,1,'Marino','Marić','mmaric','mmaric@gmail.com','$2a$12$lxzvaH9hVT0iipHJNGzHXOill41IC7U0Yd1UaiJ8hhK72M2Mznavi','1234567890',1,0,'2022-08-31 16:27:46','2022-09-05 14:03:19'),(2,2,'Ana','Anić','aanic','aanic@gmail.com','$2a$10$iFPbEkX5LRjsco/dOssEyOhgtt3F91kxQlFWo3cYp27joQZ/HzlpG','1234567890',1,0,'2022-08-31 16:27:46','2022-09-05 14:56:29'),(3,3,'Željko','Horvat','zhorvat','zhorvat@gmail.com','$2a$10$QvjjsNJ5yBZNdrQJSM5JOuKCmmxIx14oOqLUThjuLFVW5RTiWL3cm','213421432',1,0,'2022-08-31 16:27:46','2022-09-08 13:48:19'),(7,2,'Dario','Daric','ddaric','ddaric@gmail.com','$2a$10$aTl1aLRmaPyKuTR4iyYyGeQwilB/l7Vd/FelmgdWJIyr.stKsHV1e','1243124324',1,0,'2022-09-01 11:51:56','2022-09-08 12:12:30'),(8,2,'dsa','dsa','dsa','dsa','$2a$10$MlQd7jH2swT0e9gxZnkn0OWHjHnThFzKMhDChMu/00u4deJCbAGgG','dsa',1,1,'2022-09-02 17:30:43','2022-09-02 17:31:22'),(9,3,'Ivona','Lazar','ilazar','ivona1227@gmail.com','$2a$10$JitcfZMOh5bEPJEfUJ.pKep7PZXZH7T8Yw08qQobNfH55Q7HIEf6a',NULL,1,0,'2022-09-08 15:04:50','2022-09-08 15:39:29'),(10,2,'Doris','Lazar','dorisl','doris.lazar@gmail.com','$2a$10$dUuX.A3zFD/G7j5DuZeHpeVmG8BoFEQkzlPEyH469uDg.j81F3oWu','5375398923736',1,0,'2022-09-08 15:48:55','2022-09-08 15:48:55');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-08 18:10:27
