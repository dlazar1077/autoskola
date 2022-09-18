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
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `FAQ_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PITANJE` varchar(500) NOT NULL,
  `ODGOVOR` varchar(1200) NOT NULL,
  `DELETED` int(11) DEFAULT '0',
  `ORDINAL` int(11) NOT NULL DEFAULT '1',
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `MODIFICATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FAQ_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'Kada i gdje se mogu upisati u autoškolu?','Upisivanje u autoškolu moguće je bilo kada, a upisivanje se može obaviti preko kontakt podatka sa dna stranice, ili registracijom na straniciu te na stranici \"Moj profil\" pod tipokom \"Upiši\" odabrati željenu kategoriju',0,1,'2022-09-13 21:02:31','2022-09-13 21:02:31'),(2,'Mogu li se upisati u autoškolu za B kategoriju ako nemam 18 godina?','Prema zakonu se možete upisati u autoškolu sa 17,5 godina, položiti testove iz Prometnih propisa i Prve pomoći, ali ne možete pristupiti ispitu iz Upravljanja vozilom na motorni pogon prije navršenih 18 godina.',0,1,'2022-09-13 21:02:43','2022-09-13 21:02:43'),(3,'Koliko dugo vrijedi liječnička potvrda za upis u autoškolu?','Nakon što ste upisali autoškolu, liječnička potvrda potrebna za upis u autoškolu vrijedi neograničeno (ali kod podizanje vozačke dozvole liječničko uvjerenje vrijedi 6 mjeseci).',0,1,'2022-09-13 21:02:59','2022-09-13 21:02:59'),(4,'Koliko puta imam pravo pristupiti ispitu iz Prometnih propisa i sigurnosnih pravila (PPSP)?','Zakonski imate pravo 5 puta (u roku od 12 mjeseci od odslušanih predavanja iz PPSP-a) pristupati polaganju ispita iz PPSP-a (prometni propisi i sigurnosna pravila), a nakon petog pada morate ponovo odslušati predavanja.',0,1,'2022-09-13 21:03:20','2022-09-13 21:03:20'),(5,'Kada mogu započeti s vožnjom?','S vožnjom možete započeti odmah nakon položenog ispita iz PPSP-a (prometnih propisa i sigurnosnih pravila).',0,1,'2022-09-13 21:03:31','2022-09-13 21:03:31'),(6,'Kako se provodi nastava iz upravaljanja vozilom B kategorije?','Pohađanje ove nastave dogovarate sa vašim instruktorom vožnje. Nastava iz ovog predmeta traje najmanje 35 sati. Jedan sat traje 45 minuta, a blok sat 90 minuta.',0,1,'2022-09-13 21:03:42','2022-09-13 21:03:42'),(7,'Mogu li voziti više od jednog sata kod B kategorije?','Prvih 5 sati nastava je individualna, po jedan školski sat, a kasnije se nastava može provoditi i po dva sata. Instruktor vožnje pokazivat će vam kako se izvode pojedine vještine, a vi ćete ih uvježbavati ponavljanjem.',0,1,'2022-09-13 21:03:54','2022-09-13 21:03:54'),(8,'Tko obavlja izdavanje vozačke dozvole?','Izdavanje se obavlja u Policijskoj upravi. Uz uvjerenje o položenom vozačkom ispitu i uz važeće liječničko Uvjerenje (ne smije biti starije od 6 mjeseci) možete podići svoju vozačku dozvolu.',0,1,'2022-09-13 21:04:06','2022-09-13 21:04:06');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `autoskola`.`faq_BEFORE_UPDATE` BEFORE UPDATE ON `faq` FOR EACH ROW
BEGIN
	SET NEW.MODIFICATION_DATE = now(); 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-18 23:50:36
