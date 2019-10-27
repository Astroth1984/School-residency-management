# School-residency-management

## UML : class diagram
![database](https://user-images.githubusercontent.com/47123273/67628600-9913a700-f868-11e9-86e6-66f796aad871.png)

##Query :
-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 26 mars 2019 à 17:54
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestioninternat`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE IF NOT EXISTS `administrator` (
  `IdAdmin` int(11) NOT NULL AUTO_INCREMENT,
  `ImmatriculeAdmin` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdAdmin`),
  KEY `IdUser` (`IdUser`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `administrator`
--

INSERT INTO `administrator` (`IdAdmin`, `ImmatriculeAdmin`, `IdUser`) VALUES
(1, 102546, 9);

-- --------------------------------------------------------

--
-- Structure de la table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
CREATE TABLE IF NOT EXISTS `appuser` (
  `IdUser` int(11) NOT NULL AUTO_INCREMENT,
  `NameUser` varchar(150) NOT NULL,
  `passwordUser` varchar(255) NOT NULL,
  `EmailUser` varchar(200) NOT NULL,
  `PhoneUser` varchar(20) NOT NULL,
  `CINUser` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdUser`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appuser`
--

INSERT INTO `appuser` (`IdUser`, `NameUser`, `passwordUser`, `EmailUser`, `PhoneUser`, `CINUser`) VALUES
(1, 'EL MOUNIR Othmane', 'greenman', 'amine.elalaoui.med@gmail.com', '0663205709', 'W393394'),
(2, 'othmane', '23bc6df7647b818d79ce7fc43fa0f460c188205a', 'haajar.eq@gmail.com', '0700112233', 'BK123456'),
(9, 'el mounir', '41237a505d939ae273056ba68a8b2c8bfcdcf46b', 'taha.fettah@gmail.com', '06666666', 'A123465'),
(8, 'Astroth1984', '23bc6df7647b818d79ce7fc43fa0f460c188205a', 'amine-elalaoui@um5s.net.ma', '0632200867', 'W456213');

-- --------------------------------------------------------

--
-- Structure de la table `batiment`
--

DROP TABLE IF EXISTS `batiment`;
CREATE TABLE IF NOT EXISTS `batiment` (
  `IdBatiment` int(11) NOT NULL AUTO_INCREMENT,
  `CodeBatiment` varchar(250) NOT NULL,
  `gender` char(1) NOT NULL,
  `NBEtage` int(11) NOT NULL,
  PRIMARY KEY (`IdBatiment`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batiment`
--

INSERT INTO `batiment` (`IdBatiment`, `CodeBatiment`, `gender`, `NBEtage`) VALUES
(1, 'A', 'F', 3),
(2, 'B', 'F', 3),
(3, 'E', 'F', 3),
(4, 'A', 'M', 3),
(5, 'C', 'M', 3);

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

DROP TABLE IF EXISTS `chambre`;
CREATE TABLE IF NOT EXISTS `chambre` (
  `IdChambre` int(11) NOT NULL AUTO_INCREMENT,
  `CodeChambre` varchar(20) DEFAULT NULL,
  `IdEtage` int(11) NOT NULL,
  PRIMARY KEY (`IdChambre`),
  KEY `IdEtage` (`IdEtage`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chambre`
--

INSERT INTO `chambre` (`IdChambre`, `CodeChambre`, `IdEtage`) VALUES
(1, 'A1', 1),
(2, 'A2', 1),
(3, 'A3', 1),
(4, 'A4', 1),
(5, 'A5', 1),
(6, 'A6', 1),
(7, 'A7', 1),
(8, 'A8', 1),
(9, 'A9', 1),
(10, 'A10', 1),
(11, 'A11', 2),
(12, 'A12', 2),
(13, 'A13', 2),
(14, 'A14', 2),
(15, 'A15', 2),
(16, 'A16', 2),
(17, 'A17', 2),
(18, 'A18', 2),
(19, 'A19', 2),
(20, 'A20', 2),
(21, 'A1', 4),
(23, 'C1', 5),
(24, 'C2', 5),
(25, 'C3', 5);

-- --------------------------------------------------------

--
-- Structure de la table `chambrea2`
--

DROP TABLE IF EXISTS `chambrea2`;
CREATE TABLE IF NOT EXISTS `chambrea2` (
  `IdChambreA2` int(11) NOT NULL AUTO_INCREMENT,
  `EtatPlace1` tinyint(1) NOT NULL,
  `EtatPlace2` tinyint(1) NOT NULL,
  `IdChambre` int(11) NOT NULL,
  PRIMARY KEY (`IdChambreA2`),
  KEY `IdChambre` (`IdChambre`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chambrea2`
--

INSERT INTO `chambrea2` (`IdChambreA2`, `EtatPlace1`, `EtatPlace2`, `IdChambre`) VALUES
(1, 0, 1, 1),
(2, 1, 1, 2),
(3, 1, 1, 3),
(4, 1, 1, 4),
(5, 1, 1, 5),
(6, 1, 1, 6),
(7, 1, 1, 7),
(8, 1, 1, 8),
(9, 1, 1, 9),
(10, 1, 1, 10),
(11, 1, 1, 11),
(12, 1, 1, 12),
(13, 1, 1, 13),
(14, 1, 1, 14),
(15, 1, 1, 15),
(16, 1, 1, 16),
(17, 1, 1, 17),
(18, 1, 1, 18),
(19, 1, 1, 19),
(20, 1, 1, 20),
(21, 1, 1, 21),
(22, 1, 1, 22),
(23, 1, 1, 23),
(24, 1, 1, 24),
(25, 1, 1, 25);

-- --------------------------------------------------------

--
-- Structure de la table `chambrea3`
--

DROP TABLE IF EXISTS `chambrea3`;
CREATE TABLE IF NOT EXISTS `chambrea3` (
  `IdChambreA3` int(11) NOT NULL AUTO_INCREMENT,
  `EtatPlace1` tinyint(1) NOT NULL,
  `EtatPlace2` tinyint(1) NOT NULL,
  `EtatPlace3` tinyint(1) NOT NULL,
  `IdChambre` int(11) NOT NULL,
  PRIMARY KEY (`IdChambreA3`),
  KEY `IdChambre` (`IdChambre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `chambrea4`
--

DROP TABLE IF EXISTS `chambrea4`;
CREATE TABLE IF NOT EXISTS `chambrea4` (
  `IdChambreA4` int(11) NOT NULL AUTO_INCREMENT,
  `EtatPlace1` tinyint(1) NOT NULL,
  `EtatPlace2` tinyint(1) NOT NULL,
  `EtatPlace3` tinyint(1) NOT NULL,
  `EtatPlace4` tinyint(1) NOT NULL,
  `IdChambre` int(11) NOT NULL,
  PRIMARY KEY (`IdChambreA4`),
  KEY `IdChambre` (`IdChambre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `chambreindiv`
--

DROP TABLE IF EXISTS `chambreindiv`;
CREATE TABLE IF NOT EXISTS `chambreindiv` (
  `IdChambreIndiv` int(11) NOT NULL AUTO_INCREMENT,
  `EtatChambre` tinyint(1) NOT NULL,
  `IdChambre` int(11) NOT NULL,
  PRIMARY KEY (`IdChambreIndiv`),
  KEY `IdChambre` (`IdChambre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `claims`
--

DROP TABLE IF EXISTS `claims`;
CREATE TABLE IF NOT EXISTS `claims` (
  `IdClaim` int(11) NOT NULL AUTO_INCREMENT,
  `Categorie` varchar(255) NOT NULL,
  `Contenu` text NOT NULL,
  `dateClaim` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valide` tinyint(1) NOT NULL DEFAULT '0',
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdClaim`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `claims`
--

INSERT INTO `claims` (`IdClaim`, `Categorie`, `Contenu`, `dateClaim`, `valide`, `IdUser`) VALUES
(3, 'Cuisine', 'Salless!', '2019-01-30 13:53:25', 1, 8),
(6, 'Internat,Chambre', 'probleme d\'eau', '2019-01-30 16:26:33', 1, 8),
(5, 'Terrain', 'Terrain', '2019-01-30 14:02:03', 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `etage`
--

DROP TABLE IF EXISTS `etage`;
CREATE TABLE IF NOT EXISTS `etage` (
  `IdEtage` int(11) NOT NULL AUTO_INCREMENT,
  `CodeEtage` varchar(100) NOT NULL,
  `NbChambres` int(11) NOT NULL,
  `IdBatiment` int(11) NOT NULL,
  PRIMARY KEY (`IdEtage`),
  KEY `IdBatiment` (`IdBatiment`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etage`
--

INSERT INTO `etage` (`IdEtage`, `CodeEtage`, `NbChambres`, `IdBatiment`) VALUES
(1, 'AF1', 10, 1),
(2, 'AF2', 10, 1),
(3, 'AF3', 10, 1),
(5, 'CM1', 10, 5),
(4, 'AM1', 10, 4);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `IdInscription` int(11) NOT NULL AUTO_INCREMENT,
  `DateInscription` date NOT NULL,
  `DateDebut` date NOT NULL,
  `Duree` int(11) NOT NULL,
  `current` tinyint(1) NOT NULL,
  `IdChambre` int(11) NOT NULL,
  `IdStudent` int(11) NOT NULL,
  PRIMARY KEY (`IdInscription`),
  KEY `IdChambre` (`IdChambre`),
  KEY `IdStudent` (`IdStudent`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`IdInscription`, `DateInscription`, `DateDebut`, `Duree`, `current`, `IdChambre`, `IdStudent`) VALUES
(10, '2019-01-30', '2019-01-30', 12, 0, 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `IdStudent` int(11) NOT NULL AUTO_INCREMENT,
  `Level` varchar(100) NOT NULL,
  `CNE` varchar(100) NOT NULL,
  `IdUser` int(11) NOT NULL,
  PRIMARY KEY (`IdStudent`),
  KEY `IdUser` (`IdUser`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `student`
--

INSERT INTO `student` (`IdStudent`, `Level`, `CNE`, `IdUser`) VALUES
(1, 'Second year', '1311280464', 1),
(2, 'Second year', '1311280465', 2),
(6, '2A', '14229681', 8);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


