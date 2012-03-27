-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 23, 2012 at 11:24 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `localidades`
--
CREATE DATABASE IF NOT EXISTS localidades;
USE localidades;
-- --------------------------------------------------------

--
-- Table structure for table `distritos`
--

CREATE TABLE IF NOT EXISTS `distritos` (
  `idDistrito` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_distrito` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`idDistrito`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `distritos`
--

INSERT INTO `distritos` (`idDistrito`, `codigo_distrito`, `nome`) VALUES
(1, 1, 'Aveiro'),
(2, 2, 'Beja'),
(3, 3, 'Braga'),
(4, 4, 'Bragança'),
(5, 5, 'Castelo Branco'),
(6, 6, 'Coimbra'),
(7, 7, 'Évora'),
(8, 8, 'Faro'),
(9, 9, 'Guarda'),
(10, 10, 'Leiria'),
(11, 11, 'Lisboa'),
(12, 12, 'Portalegre'),
(13, 13, 'Porto'),
(14, 14, 'Santarém'),
(15, 15, 'Setúbal'),
(16, 16, 'Viana do Castelo'),
(17, 17, 'Vila Real'),
(18, 18, 'Viseu'),
(19, 31, 'Ilha da Madeira'),
(20, 32, 'Ilha de Porto Santo'),
(21, 41, 'Ilha de Santa Maria'),
(22, 42, 'Ilha de São Miguel'),
(23, 43, 'Ilha Terceira'),
(24, 44, 'Ilha da Graciosa'),
(25, 45, 'Ilha de São Jorge'),
(26, 46, 'Ilha do Pico'),
(27, 47, 'Ilha do Faial'),
(28, 48, 'Ilha das Flores'),
(29, 49, 'Ilha do Corvo');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
