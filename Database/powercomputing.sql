CREATE DATABASE  IF NOT EXISTS `powercomputing` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `powercomputing`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 130.185.82.39    Database: powercomputing
-- ------------------------------------------------------
-- Server version	5.0.95

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed` (
  `idFeed` int(11) NOT NULL auto_increment,
  `data` date default NULL,
  `hora` time default NULL,
  `news` varchar(300) default NULL,
  `nome` varchar(45) default NULL,
  PRIMARY KEY  (`idFeed`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tblResults`
--

DROP TABLE IF EXISTS `tblResults`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblResults` (
  `itera` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idProblem` int(11) NOT NULL,
  `globalAvarage` double NOT NULL,
  `globalDeviation` double NOT NULL,
  `globalBest` double NOT NULL,
  `globalNumBest` text NOT NULL,
  `variance` double NOT NULL,
  `finalfim` longtext
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tblClientes`
--

DROP TABLE IF EXISTS `tblClientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblClientes` (
  `idClient` int(11) NOT NULL auto_increment,
  `nome` varchar(150) default NULL,
  `email` varchar(150) default NULL,
  `password` varchar(150) default NULL,
  `id_confirmation` varchar(250) default NULL,
  `status` int(11) default NULL,
  `address` varchar(255) default NULL,
  `phone1` varchar(255) default NULL,
  `phone2` varchar(255) default NULL,
  `website` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `zipcode` varchar(255) default NULL,
  PRIMARY KEY  (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL auto_increment,
  `facebook_id` int(20) default NULL,
  `name` varchar(200) default NULL,
  `email` varchar(200) default NULL,
  `gender` varchar(10) default NULL,
  `birthday` date default NULL,
  `location` varchar(200) default NULL,
  `hometown` varchar(200) default NULL,
  `bio` text,
  `relationship` varchar(30) default NULL,
  `timezone` varchar(10) default NULL,
  `access_token` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logins`
--

DROP TABLE IF EXISTS `logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logins` (
  `idLogin` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(40) NOT NULL,
  `permission` int(11) NOT NULL COMMENT 'Nivel de permissões do utilizador',
  `active` int(11) NOT NULL COMMENT '0 -> Desactivada\n1 -> Activada',
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`idLogin`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tblProblemas`
--

DROP TABLE IF EXISTS `tblProblemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblProblemas` (
  `idProblem` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `algorithm` text,
  `selection` varchar(250) default NULL,
  `mutation` varchar(250) default NULL,
  `recombination` varchar(250) default NULL,
  `replacement` varchar(250) default NULL,
  `time` varchar(200) default NULL,
  `name` varchar(255) default NULL,
  `description` text,
  `filename` varchar(255) default NULL,
  PRIMARY KEY  (`idProblem`,`idClient`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tblIterations`
--

DROP TABLE IF EXISTS `tblIterations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblIterations` (
  `threadId` int(11) NOT NULL,
  `itera` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idProblem` int(11) NOT NULL,
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `best` double NOT NULL,
  `average` double NOT NULL,
  `attribute` longtext NOT NULL,
  `deviation` double NOT NULL,
  `tipo` tinyint(4) NOT NULL,
  `variance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'powercomputing'
--
/*!50003 DROP PROCEDURE IF EXISTS `ExecuteCountQuery` */;
--
-- WARNING: old server version. The following dump may be incomplete.
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`adminpower`@`%`*/ /*!50003 PROCEDURE `ExecuteCountQuery`(period INT, id_Cliente INT, id_Problem INT)
BEGIN
    SELECT COUNT(*) as num FROM tblIterations WHERE itera=period AND idClient=id_Cliente AND idProblem=id_Problem;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ExecuteMedia` */;
--
-- WARNING: old server version. The following dump may be incomplete.
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`adminpower`@`%`*/ /*!50003 PROCEDURE `ExecuteMedia`(period INT, id_Cliente INT, id_Problem INT, globalNumBests text,final text, control int)
BEGIN

    DECLARE media1 double; /*Soma dos resultados na itera=period com type=1 */
    DECLARE media2 double; /*Soma dos resultados com type=2 */
    
    /*Numero de registos para fazer a media  */
    DECLARE num1 int; 
    DECLARE num2 int;
    
    DECLARE deviationAverage double;
    DECLARE varianceAverage double;
    
    DECLARE deviationAverage2 double;
    DECLARE varianceAverage2 double; 
    
    DECLARE BestFit DOUBLE;
       DECLARE BestFit2 DOUBLE;
    DECLARE MAXItera INT;

    IF period = 0 THEN
      SELECT SUM(average), SUM(deviation), SUM(variance), count(average) INTO media1, deviationAverage, varianceAverage, num1
      FROM tblIterations 
      WHERE itera=period AND idClient=id_Cliente AND idProblem=id_Problem;

      SET num2 = 0;
    ELSE
      /* Todas as que têm itera que entra e type = 1*/
      SELECT SUM(average), SUM(deviation), SUM(variance), count(average) INTO media1, deviationAverage, varianceAverage, num1
      FROM tblIterations 
      WHERE itera=period AND idClient=id_Cliente AND idProblem=id_Problem and tipo=1;
      
      /* Todas as que têm type=2 */
      SELECT SUM(average), SUM(deviation), SUM(variance), count(average) INTO media2, deviationAverage2, varianceAverage2, num2
      FROM tblIterations 
      WHERE idClient=id_Cliente AND idProblem=id_Problem and tipo=2;
    END IF;
    
    
    /*0 > MAX */
    /*1 > MIN */
    IF control = 0 THEN
      /* verifica se existe algum registo naquela itera*/
      SELECT MAX(best) INTO BestFit 
      FROM tblIterations
      WHERE itera=period AND idClient=id_Cliente AND idProblem=id_Problem; 
      
      SELECT MAX(best) INTO BestFit2 
      FROM tblIterations
      WHERE tipo=2 AND idClient=id_Cliente AND idProblem=id_Problem;

        IF BestFit2 > BestFit THEN
            Set BestFit = BestFit2;
        END IF;    

    ELSEIF control = 1 THEN
      /* verifica se existe algum registo naquela itera*/
      SELECT MIN(best) INTO BestFit
      FROM tblIterations 
      WHERE itera=period AND idClient=id_Cliente AND idProblem=id_Problem; 
      
      SELECT MIN(best) INTO BestFit2
      FROM tblIterations 
      WHERE itera=period AND idClient=id_Cliente AND idProblem=id_Problem; 
      
      IF BestFit2 < BestFit THEN
            Set BestFit = BestFit2;
        END IF; 
      
    END IF;
      

   
    IF num2 = 0 THEN 
        INSERT INTO tblResults VALUES (period, id_Cliente, id_Problem, (media1 / num1), (deviationAverage / num1), BestFit, globalNumBests, (varianceAverage / num1), final);
    Elseif num1 = 0 THEN
       IF BestFit IS null THEN
            SELECT MAX(itera) INTO MAXItera
            FROM tblIterations 
            WHERE idClient=id_Cliente AND idProblem=id_Problem;
             
            SELECT MAX(best) INTO BestFit
            FROM tblIterations 
            WHERE itera=MAXItera AND idClient=id_Cliente AND idProblem=id_Problem;
        
            INSERT INTO tblResults VALUES (MAXItera, id_Cliente, id_Problem, (media2 / num2), (deviationAverage2 / num2), BestFit, globalNumBests, (varianceAverage2 / num2), final);
        ELSE
            INSERT INTO tblResults VALUES (period, id_Cliente, id_Problem, (media2 / num2), (deviationAverage2 / num2), BestFit, globalNumBests, (varianceAverage2 / num2), final);
        END IF;
    ELSE 
        INSERT INTO tblResults VALUES (period, id_Cliente, id_Problem, ((media1 + media2) / (num1 + num2)), ((deviationAverage + deviationAverage2) / (num1 + num2)), BestFit, globalNumBests, (varianceAverage + varianceAverage2) / (num1 + num2), final);
    END IF;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InserirIteracoes` */;
--
-- WARNING: old server version. The following dump may be incomplete.
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`adminpower`@`%`*/ /*!50003 PROCEDURE `InserirIteracoes`(threadId int, itera int, idClient int, idProblem int, best double, average double, attributes longtext, deviation double, tipo int, variance double)
BEGIN
    INSERT INTO tblIterations VALUES (threadId, itera, idClient, idProblem, NOW(), best, average, attributes, deviation, tipo, variance);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-07 22:25:36
