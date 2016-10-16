-- phpMyAdmin SQL Dump
-- version 3.1.5
-- http://www.phpmyadmin.net
--
-- Serveur: iutg1.sql.free.fr
-- Généré le : Dim 31 Janvier 2016 à 00:47
-- Version du serveur: 5.0.83
-- Version de PHP: 5.3.9

SET FOREIGN_KEY_CHECKS=0;

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

SET AUTOCOMMIT=0;
START TRANSACTION;

--
-- Base de données: `iutg1`
--

-- --------------------------------------------------------

--
-- Structure de la table `ARBITRES`
--

CREATE TABLE IF NOT EXISTS `ARBITRES` (
  `ID` int(3) NOT NULL,
  `PRENOM` varchar(40) collate latin1_general_ci NOT NULL,
  `NOM` varchar(40) collate latin1_general_ci NOT NULL,
  `PAYS` int(3) NOT NULL,
  `CATEGORIE` int(2) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `ARBITRES`
--

INSERT INTO `ARBITRES` (`ID`, `PRENOM`, `NOM`, `PAYS`, `CATEGORIE`) VALUES
(11, 'francis', 'deleck', 1, 0),
(12, 'heen', 'asd', 0, 0),
(35, 'Romain', 'Tubero', 4, 1),
(36, 'Ednor', 'Minor', 10, 1),
(37, 'Egisippe', 'Rutilus', 17, 1),
(38, 'Léontide', 'Porcius', 18, 1),
(39, 'Luciel', 'Niger', 8, 1),
(40, 'Eléodore', 'Proculus', 6, 1),
(41, 'Euzaric', 'Helvius', 19, 1),
(42, 'Basile', 'Hostilius', 13, 1),
(43, 'Elzéal', 'Blaesus', 18, 1),
(44, 'Méry', 'Varro', 16, 1),
(45, 'Elide', 'Blaesus', 16, 1),
(1, 'jean', 'matrol', 3, 0),
(3, 'giles', 'dukra', 5, 0),
(4, 'dan', 'parker', 6, 0),
(5, 'marc', 'hoster', 7, 0),
(6, 'xavier', 'krus', 8, 0),
(7, 'françois', 'moulon', 9, 0),
(8, 'murielle', 'platini', 10, 0),
(9, 'christophe', 'toutilini', 11, 1),
(10, 'rudolph', 'erwat', 12, 1),
(0, 'Serge', 'Terkel', 0, 0),
(13, 'Alexis', 'Aslak', 9, 0),
(14, 'Gilles', 'Runi', 19, 0),
(15, 'Yves', 'Hagbard', 0, 0),
(16, 'Olivier', 'Bernulf', 2, 0),
(17, 'Jérémy', 'Thorleif', 1, 0),
(18, 'Lucien', 'Svenn', 14, 0),
(19, 'Kevin', 'Ivar', 9, 0),
(20, 'Jean-Luc', 'Sigfred', 18, 0),
(21, 'Marcel', 'Sigvard', 1, 0),
(22, 'Gérard', 'Alwin', 9, 0),
(23, 'Yves', 'Ulv', 7, 0),
(24, 'Robert', 'Nordahl', 0, 0),
(25, 'René', 'Svein', 12, 1),
(26, 'Michel', 'Stig', 16, 1),
(27, 'Anthony', 'Asbjörn', 17, 1),
(28, 'Henri', 'Frithjof', 6, 1),
(29, 'Charles', 'Ranulf', 8, 1),
(30, 'Philippe', 'Sven', 17, 1),
(31, 'Emmanuel', 'Olaf', 8, 1),
(32, 'Fabien', 'Osbern', 19, 1),
(33, 'René', 'Asulf', 1, 1),
(34, 'Michel', 'Frode', 8, 1);

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORIEARBITRE`
--

CREATE TABLE IF NOT EXISTS `CATEGORIEARBITRE` (
  `ID` int(1) NOT NULL,
  `LABEL` varchar(20) collate latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `CATEGORIEARBITRE`
--

INSERT INTO `CATEGORIEARBITRE` (`ID`, `LABEL`) VALUES
(0, 'jat2'),
(1, 'itt1');

-- --------------------------------------------------------

--
-- Structure de la table `COURT`
--

CREATE TABLE IF NOT EXISTS `COURT` (
  `ID` int(2) NOT NULL,
  `LABEL` varchar(40) collate latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `COURT`
--

INSERT INTO `COURT` (`ID`, `LABEL`) VALUES
(0, 'principal'),
(1, 'annexe');

-- --------------------------------------------------------

--
-- Structure de la table `DMATCHS`
--

CREATE TABLE IF NOT EXISTS `DMATCHS` (
  `ID` int(11) NOT NULL auto_increment,
  `JOUEUR1` int(11) NOT NULL,
  `JOUEUR2` int(11) NOT NULL,
  `JOUEUR3` int(11) NOT NULL,
  `JOUEUR4` int(11) NOT NULL,
  `CRENEAU` varchar(20) NOT NULL,
  `COURT` int(11) NOT NULL,
  `FINALE` int(11) NOT NULL,
  `ARBITRECHAISE` int(11) NOT NULL,
  `JOUR` int(11) NOT NULL,
  `RAMASSEURS1` int(11) NOT NULL,
  `RAMASSEURS2` int(11) NOT NULL,
  `ARBITRES` varchar(50) NOT NULL,
  `SCORES` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=85 ;

--
-- Contenu de la table `DMATCHS`
--

INSERT INTO `DMATCHS` (`ID`, `JOUEUR1`, `JOUEUR2`, `JOUEUR3`, `JOUEUR4`, `CRENEAU`, `COURT`, `FINALE`, `ARBITRECHAISE`, `JOUR`, `RAMASSEURS1`, `RAMASSEURS2`, `ARBITRES`, `SCORES`) VALUES
(7, 5, 8, 6, 7, '11', 1, 0, 0, 1, 1, 0, '1:3:4:5:6:7:8:11:12:', '5:6:2:4:6:6:3:5:7:6:3:'),
(84, 5, 8, 7, 6, '8', 0, 1, 3, 3, 4, 2, '3:2:1:5:6:7:8:6:6:', '0');

-- --------------------------------------------------------

--
-- Structure de la table `ENTRAINEMENTS`
--

CREATE TABLE IF NOT EXISTS `ENTRAINEMENTS` (
  `ID` int(4) NOT NULL,
  `JOUEUR1` int(4) NOT NULL,
  `CRENEAU` varchar(20) collate latin1_general_ci NOT NULL,
  `JOUR` int(3) default NULL,
  `COURT` int(3) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `ENTRAINEMENTS`
--

INSERT INTO `ENTRAINEMENTS` (`ID`, `JOUEUR1`, `CRENEAU`, `JOUR`, `COURT`) VALUES
(12, 4, '11', 0, 3),
(13, 9, '15', 0, 3),
(2, 2, '11', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `EQUIPERAMASSEUR`
--

CREATE TABLE IF NOT EXISTS `EQUIPERAMASSEUR` (
  `ID` int(2) NOT NULL,
  `LABEL` varchar(40) collate latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `EQUIPERAMASSEUR`
--

INSERT INTO `EQUIPERAMASSEUR` (`ID`, `LABEL`) VALUES
(0, 'alpha'),
(1, 'beta'),
(2, 'gamma'),
(3, 'delta');

-- --------------------------------------------------------

--
-- Structure de la table `JOUEURS`
--

CREATE TABLE IF NOT EXISTS `JOUEURS` (
  `ID` int(11) default NULL,
  `PRENOM` varchar(50) default NULL,
  `NOM` varchar(50) default NULL,
  `PAYS` int(3) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `JOUEURS`
--

INSERT INTO `JOUEURS` (`ID`, `PRENOM`, `NOM`, `PAYS`) VALUES
(1, 'georges', 'matron', 2),
(2, 'juan', 'lino', 19),
(4, 'jérome', 'defret', 15),
(5, 'luke', 'sviscki', 11),
(6, 'martin', 'pugnet', 7),
(7, 'rohn', 'sauniot', 9),
(8, 'daniel', 'karl', 5),
(9, 'jean', 'jacques', 6),
(3, 'silvio', 'boulini', 3);

-- --------------------------------------------------------

--
-- Structure de la table `LICENCES`
--

CREATE TABLE IF NOT EXISTS `LICENCES` (
  `NUMERO` varchar(255) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `DATE` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `LICENCES`
--

INSERT INTO `LICENCES` (`NUMERO`, `NOM`, `PRENOM`, `DATE`) VALUES
('732843', 'MATROL', 'Georges', '2012-02-01'),
('732844', 'LINO', 'Juan', '2012-02-01'),
('0', '', '', '0000-00-00');

-- --------------------------------------------------------

--
-- Structure de la table `NATIONALITE`
--

CREATE TABLE IF NOT EXISTS `NATIONALITE` (
  `ID` int(3) default NULL,
  `PAYS` varchar(40) default NULL,
  `LABEL` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `NATIONALITE`
--

INSERT INTO `NATIONALITE` (`ID`, `PAYS`, `LABEL`) VALUES
(19, 'Espagne', 'espagne'),
(0, 'Afrique du sud', 'afriquedusud'),
(1, 'Allemagne', 'allemagne'),
(2, 'Australie', 'australie'),
(3, 'Brezil', 'brezil'),
(4, 'Croatie', 'croatie'),
(5, 'Danemark', 'danemark'),
(6, 'Equateur', 'equateur'),
(7, 'France', 'france'),
(8, 'Grande bretagne', 'grandebretagne'),
(9, 'Israël', 'israel'),
(10, 'Maroc', 'maroc'),
(11, 'Pologne', 'pologne'),
(12, 'République tchèque', 'republiquetcheque'),
(13, 'Russie', 'russie'),
(14, 'Sénégal', 'senegal'),
(15, 'Suède', 'suede'),
(16, 'Suisse', 'suisse'),
(17, 'Etats-Unis', 'usa'),
(18, 'Zimbabwe', 'zimbabwe');

-- --------------------------------------------------------

--
-- Structure de la table `PROMOS`
--

CREATE TABLE IF NOT EXISTS `PROMOS` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` varchar(255) NOT NULL,
  `UTILISE` int(11) NOT NULL,
  `REDUCTION` float NOT NULL,
  `JS` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `PROMOS`
--

INSERT INTO `PROMOS` (`ID`, `CODE`, `UTILISE`, `REDUCTION`, `JS`) VALUES
(9, 'MARTIN', 1, 33, 0),
(11, 'GRATOS', 1, 100, 0),
(12, 'SOLIDARITE', 0, 9.5, 1),
(13, 'MARC', 0, 98, 0);

-- --------------------------------------------------------

--
-- Structure de la table `RAMASSEURS`
--

CREATE TABLE IF NOT EXISTS `RAMASSEURS` (
  `ID` int(3) NOT NULL,
  `PRENOM` varchar(40) collate latin1_general_ci NOT NULL,
  `NOM` varchar(40) collate latin1_general_ci NOT NULL,
  `PAYS` int(3) NOT NULL,
  `EQUIPE` int(2) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `RAMASSEURS`
--

INSERT INTO `RAMASSEURS` (`ID`, `PRENOM`, `NOM`, `PAYS`, `EQUIPE`) VALUES
(1, 'michel', 'noliot', 1, 3),
(2, 'dimitri', 'lassange', 6, 0),
(17, 'fsd', 'fdsf', 0, 1),
(12, 'a1', 'a1', 0, 0),
(5, 'yves', 'renoir', 7, 1),
(6, 'bishop', 'johnson', 17, 0),
(7, 'malik', 'bonar', 10, 3),
(8, 'henry', 'marcolini', 5, 1),
(11, 'alpha4', 'alpha4', 0, 0),
(10, 'georges', 'donald', 8, 1),
(14, 'aa', 'aa', 0, 0),
(13, 'aa', 'aa', 0, 0),
(15, 'aa', 'aa', 0, 1),
(16, 'ff', 'ff', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `RESERVATIONS`
--

CREATE TABLE IF NOT EXISTS `RESERVATIONS` (
  `id` int(11) NOT NULL auto_increment,
  `prix` float NOT NULL,
  `idmatch` varchar(255) collate latin1_general_ci NOT NULL,
  `type` varchar(255) collate latin1_general_ci NOT NULL,
  `idtribune` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `email` text collate latin1_general_ci NOT NULL,
  `pass` text collate latin1_general_ci NOT NULL,
  `nom` text collate latin1_general_ci NOT NULL,
  `prenom` text collate latin1_general_ci NOT NULL,
  `adresse` text collate latin1_general_ci NOT NULL,
  `cp` text collate latin1_general_ci NOT NULL,
  `ville` text collate latin1_general_ci NOT NULL,
  `tel` text collate latin1_general_ci NOT NULL,
  `idtbillet` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=22 ;

--
-- Contenu de la table `RESERVATIONS`
--

INSERT INTO `RESERVATIONS` (`id`, `prix`, `idmatch`, `type`, `idtribune`, `date`, `email`, `pass`, `nom`, `prenom`, `adresse`, `cp`, `ville`, `tel`, `idtbillet`) VALUES
(4, 0, '22', 'simple', 3, '2012-02-23 15:30:05', 'email@email.email', 'rjhelayer', 'monNom', 'monPrenom', 'monAdresse 9 rue des coquelicot deoré aux ijfdfoijdoi', '75002', 'Paris', '0604040404', 1),
(5, 0, '22', 'simple', 9, '2012-02-23 15:55:11', '', 'rjhelayer', '', '', '', '', '', '', 1),
(7, 17.5, '7', 'double', 16, '2012-02-23 18:26:37', '', 'rjhelayer', '', '', '', '', '', '', 1),
(8, 17.5, '7', 'double', 16, '2012-02-23 18:27:42', '', 'rjhelayer', '', '', '', '', '', '', 2),
(9, 25, '7', 'double', 16, '2012-02-23 18:28:04', '', 'rjhelayer', '', '', '', '', '', '', 1),
(10, 10, '7', 'double', 3, '2012-02-23 18:28:15', '', 'rjhelayer', '', '', '', '', '', '', 4),
(11, 25, '7', 'double', 16, '2012-02-23 21:26:46', '', 'rjhelayer', '', '', '', '', '', '', 1),
(12, 17, '7', 'double', 15, '2012-02-23 22:23:12', '', 'rjhelayer', '', '', '', '', '', '', 1),
(13, 14, '5', 'simple', 9, '2012-02-24 10:57:14', '', '', '', '', '', '', '', '', 1),
(14, 9.5, '5', 'simple', 6, '2012-02-27 10:15:46', '', '', '', '', '', '', '', '', 4),
(15, 50, '22', 'simple', 16, '2012-02-27 10:25:39', '', '', '', '', '', '', '', '', 5),
(16, 13, '7', 'double', 1, '2012-02-28 21:24:12', '', 'rjhelayer', '', '', '', '', '', '', 1),
(17, 17.42, '5', 'simple', 16, '2012-02-29 14:50:22', '', '', '', '', '', '', '', '', 2),
(18, 17, '5', 'simple', 15, '2012-02-29 15:55:23', '', '', '', '', '', '', '', '', 1),
(19, 17.42, '5', 'simple', 16, '2012-03-05 13:40:06', '', '', '', '', '', '', '', '', 2),
(20, 50, '22', 'simple', 8, '2016-01-22 21:28:59', '', '', '', '', '', '', '', '', 5),
(21, 8.71, '5', 'simple', 16, '2016-01-23 13:57:31', '', '', '', '', '', '', '', '', 2);

-- --------------------------------------------------------

--
-- Structure de la table `SMATCHS`
--

CREATE TABLE IF NOT EXISTS `SMATCHS` (
  `ID` int(11) NOT NULL auto_increment,
  `JOUEUR1` int(11) NOT NULL,
  `JOUEUR2` int(11) NOT NULL,
  `CRENEAU` varchar(20) NOT NULL,
  `COURT` int(11) NOT NULL,
  `FINALE` int(11) NOT NULL,
  `ARBITRECHAISE` int(11) NOT NULL,
  `JOUR` int(11) NOT NULL,
  `RAMASSEURS1` int(11) NOT NULL,
  `RAMASSEURS2` int(11) NOT NULL,
  `ARBITRES` varchar(50) NOT NULL,
  `SCORES` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=75 ;

--
-- Contenu de la table `SMATCHS`
--

INSERT INTO `SMATCHS` (`ID`, `JOUEUR1`, `JOUEUR2`, `CRENEAU`, `COURT`, `FINALE`, `ARBITRECHAISE`, `JOUR`, `RAMASSEURS1`, `RAMASSEURS2`, `ARBITRES`, `SCORES`) VALUES
(3, 3, 4, '15', 0, 0, 9, 0, 0, 1, '0:1:3:4:5:6:7:8:11:', '3:0:0:0:0:0:0:'),
(22, 4, 5, '11', 0, 1, 10, 2, 0, 1, '1:3:4:5:6:7:8:11:12:', '0'),
(5, 5, 1, '21', 0, 0, 10, 0, 0, 1, '1:3:4:5:6:7:8:11:12:', '0');

-- --------------------------------------------------------

--
-- Structure de la table `TRIBUNES`
--

CREATE TABLE IF NOT EXISTS `TRIBUNES` (
  `ID` int(11) NOT NULL,
  `LABEL` varchar(255) collate latin1_general_ci NOT NULL,
  `NBPLACES` int(11) NOT NULL,
  `PRIX` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `TRIBUNES`
--

INSERT INTO `TRIBUNES` (`ID`, `LABEL`, `NBPLACES`, `PRIX`) VALUES
(1, 'Libre', 2400, 13),
(2, 'A', 0, 14.8),
(3, 'B', 2, 15.2),
(4, 'C', 150, 14.8),
(6, 'E', 150, 15.1),
(8, 'G', 150, 15.1),
(9, 'H', 200, 14),
(10, 'I', 400, 14.3),
(11, 'J', 200, 14),
(12, 'K', 1000, 14.5),
(13, 'L', 200, 16.2),
(15, 'N', 200, 17),
(16, 'Grand Public', 150, 26);

-- --------------------------------------------------------

--
-- Structure de la table `TYPEBILLETS`
--

CREATE TABLE IF NOT EXISTS `TYPEBILLETS` (
  `ID` int(11) NOT NULL auto_increment,
  `LABEL` text collate latin1_general_ci NOT NULL,
  `PRIXREDUC` float NOT NULL,
  `IMAGE` text collate latin1_general_ci NOT NULL,
  `QUANTITE` int(11) NOT NULL,
  `FIXE` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=6 ;

--
-- Contenu de la table `TYPEBILLETS`
--

INSERT INTO `TYPEBILLETS` (`ID`, `LABEL`, `PRIXREDUC`, `IMAGE`, `QUANTITE`, `FIXE`) VALUES
(1, 'Grand Public', 0, 'billetgp.png', 5300, 0),
(2, 'Licenciés', 33, 'billetlicencies.png', 100, 0),
(3, 'Promos', 0, 'billetpromos.png', 1000, 0),
(4, 'Journée Solidarité', 0, 'billetjs.png', 100, 0),
(5, 'The Big Match', 50, 'billettbm.png', 6500, 1);

-- --------------------------------------------------------

--
-- Structure de la table `USERGPTL`
--

CREATE TABLE IF NOT EXISTS `USERGPTL` (
  `ID` int(3) NOT NULL,
  `LOGIN` varchar(20) collate latin1_general_ci NOT NULL,
  `PASS` varchar(20) collate latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Contenu de la table `USERGPTL`
--

INSERT INTO `USERGPTL` (`ID`, `LOGIN`, `PASS`) VALUES
(3, 'test', 'test');

SET FOREIGN_KEY_CHECKS=1;

COMMIT;
