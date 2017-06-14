CREATE DATABASE  IF NOT EXISTS `lipidhome` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lipidhome`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: do1-aps-lardo.isas.de    Database: lipidhome
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `FA_scan_species`
--

DROP TABLE IF EXISTS `FA_scan_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FA_scan_species` (
  `FA_scan_species_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_species_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `identified` tinyint(1) NOT NULL,
  `score` decimal(4,2) NOT NULL,
  PRIMARY KEY (`FA_scan_species_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `l_species_id1_idx` (`l_species_id`),
  CONSTRAINT `l_species_id1` FOREIGN KEY (`l_species_id`) REFERENCES `species` (`species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27330929 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FA_scan_species_has_paper`
--

DROP TABLE IF EXISTS `FA_scan_species_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FA_scan_species_has_paper` (
  `l_FA_scan_species_id` int(11) NOT NULL,
  `l_paper_id` int(11) NOT NULL,
  `lucene_score` decimal(4,3) NOT NULL,
  PRIMARY KEY (`l_FA_scan_species_id`,`l_paper_id`),
  KEY `l_paper_id4_idx` (`l_paper_id`),
  KEY `l_FA_scan_species_id2_idx` (`l_FA_scan_species_id`),
  CONSTRAINT `l_FA_scan_species_id2` FOREIGN KEY (`l_FA_scan_species_id`) REFERENCES `FA_scan_species` (`FA_scan_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_paper_id4` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FA_scan_species_has_sub_species`
--

DROP TABLE IF EXISTS `FA_scan_species_has_sub_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FA_scan_species_has_sub_species` (
  `l_FA_scan_species_id` int(11) NOT NULL,
  `l_sub_species_id` int(11) NOT NULL,
  PRIMARY KEY (`l_FA_scan_species_id`,`l_sub_species_id`),
  KEY `l_sub_species_id1_idx` (`l_sub_species_id`),
  KEY `l_FA_scan_species_id1_idx` (`l_FA_scan_species_id`),
  CONSTRAINT `l_FA_scan_species_id1` FOREIGN KEY (`l_FA_scan_species_id`) REFERENCES `FA_scan_species` (`FA_scan_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_sub_species_id1` FOREIGN KEY (`l_sub_species_id`) REFERENCES `sub_species` (`sub_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FA_scan_species_has_xref`
--

DROP TABLE IF EXISTS `FA_scan_species_has_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FA_scan_species_has_xref` (
  `l_FA_scan_species_id` int(11) NOT NULL,
  `l_xref_id` int(11) NOT NULL,
  PRIMARY KEY (`l_FA_scan_species_id`,`l_xref_id`),
  KEY `l_xref_id4_idx` (`l_xref_id`),
  KEY `l_FA_scan_species_id5_idx` (`l_FA_scan_species_id`),
  CONSTRAINT `l_FA_scan_species_id5` FOREIGN KEY (`l_FA_scan_species_id`) REFERENCES `FA_scan_species` (`FA_scan_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_xref_id4` FOREIGN KEY (`l_xref_id`) REFERENCES `xref` (`xref_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`author_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  `model` text NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category_summary`
--

DROP TABLE IF EXISTS `category_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_summary` (
  `category_sumarry_id` int(11) NOT NULL,
  `l_category_id` int(11) NOT NULL,
  `main_classes` int(11) NOT NULL,
  `sub_classes` int(11) NOT NULL,
  `species` int(11) NOT NULL,
  `FA_scan_species` int(11) NOT NULL,
  `sub_species` int(11) NOT NULL,
  `annotated_isomers` int(11) NOT NULL,
  PRIMARY KEY (`category_sumarry_id`),
  KEY `l_category_id1_idx` (`l_category_id`),
  CONSTRAINT `l_category_id1` FOREIGN KEY (`l_category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `composition`
--

DROP TABLE IF EXISTS `composition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `composition` (
  `composition_id` int(11) NOT NULL AUTO_INCREMENT,
  `formula` varchar(45) NOT NULL,
  `exact_mass` decimal(10,5) NOT NULL,
  `natural_mass` decimal(10,5) NOT NULL,
  PRIMARY KEY (`composition_id`),
  UNIQUE KEY `formula_UNIQUE` (`formula`)
) ENGINE=InnoDB AUTO_INCREMENT=13745 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_isomer`
--

DROP TABLE IF EXISTS `fatty_acid_isomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_isomer` (
  `fatty_acid_isomer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`fatty_acid_isomer_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_isomer_has_isomer`
--

DROP TABLE IF EXISTS `fatty_acid_isomer_has_isomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_isomer_has_isomer` (
  `l_fatty_acid_isomer_id` int(11) NOT NULL,
  `l_isomer_id` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `linkage` varchar(1) NOT NULL,
  PRIMARY KEY (`l_fatty_acid_isomer_id`,`l_isomer_id`,`position`),
  KEY `l_isomer_id1_idx` (`l_isomer_id`),
  KEY `l_fatty_acid_isomer_id1_idx` (`l_fatty_acid_isomer_id`),
  CONSTRAINT `l_fatty_acid_isomer_id1` FOREIGN KEY (`l_fatty_acid_isomer_id`) REFERENCES `fatty_acid_isomer` (`fatty_acid_isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_isomer_id1` FOREIGN KEY (`l_isomer_id`) REFERENCES `isomer` (`isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_isomer_has_paper`
--

DROP TABLE IF EXISTS `fatty_acid_isomer_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_isomer_has_paper` (
  `l_fatty_acid_isomer_id` int(11) NOT NULL,
  `l_paper_id` int(11) NOT NULL,
  `lucene_score` decimal(4,3) NOT NULL,
  PRIMARY KEY (`l_fatty_acid_isomer_id`,`l_paper_id`),
  KEY `l_paper_id8_idx` (`l_paper_id`),
  KEY `l_fatty_acid_isomer_id2_idx` (`l_fatty_acid_isomer_id`),
  CONSTRAINT `l_fatty_acid_isomer_id2` FOREIGN KEY (`l_fatty_acid_isomer_id`) REFERENCES `fatty_acid_isomer` (`fatty_acid_isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_paper_id8` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_isomer_has_xref`
--

DROP TABLE IF EXISTS `fatty_acid_isomer_has_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_isomer_has_xref` (
  `l_fatty_acid_isomer_id` int(11) NOT NULL,
  `l_xref_id` int(11) NOT NULL,
  PRIMARY KEY (`l_fatty_acid_isomer_id`,`l_xref_id`),
  KEY `l_xref_id2_idx` (`l_xref_id`),
  KEY `l_fatty_acid_isomer_id3_idx` (`l_fatty_acid_isomer_id`),
  CONSTRAINT `l_fatty_acid_isomer_id3` FOREIGN KEY (`l_fatty_acid_isomer_id`) REFERENCES `fatty_acid_isomer` (`fatty_acid_isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_xref_id2` FOREIGN KEY (`l_xref_id`) REFERENCES `xref` (`xref_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_species`
--

DROP TABLE IF EXISTS `fatty_acid_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_species` (
  `fatty_acid_species_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `carbons` int(11) NOT NULL,
  `double_bonds` int(11) NOT NULL,
  `identified` tinyint(1) NOT NULL,
  PRIMARY KEY (`fatty_acid_species_id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_species_has_FA_scan_species`
--

DROP TABLE IF EXISTS `fatty_acid_species_has_FA_scan_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_species_has_FA_scan_species` (
  `l_fatty_acid_species_id` int(11) NOT NULL,
  `l_FA_scan_species_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`l_fatty_acid_species_id`,`l_FA_scan_species_id`),
  KEY `l_FA_scan_species_id3_idx` (`l_FA_scan_species_id`),
  KEY `l_fatty_acid_species_id1_idx` (`l_fatty_acid_species_id`),
  CONSTRAINT `l_FA_scan_species_id3` FOREIGN KEY (`l_FA_scan_species_id`) REFERENCES `FA_scan_species` (`FA_scan_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_fatty_acid_species_id1` FOREIGN KEY (`l_fatty_acid_species_id`) REFERENCES `fatty_acid_species` (`fatty_acid_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_species_has_modification`
--

DROP TABLE IF EXISTS `fatty_acid_species_has_modification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_species_has_modification` (
  `l_fatty_acid_species_id` int(11) NOT NULL,
  `l_modification_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`l_fatty_acid_species_id`,`l_modification_id`),
  KEY `l_modification_id2_idx` (`l_modification_id`),
  KEY `l_fatty_acid_species_id4_idx` (`l_fatty_acid_species_id`),
  CONSTRAINT `l_fatty_acid_species_id4` FOREIGN KEY (`l_fatty_acid_species_id`) REFERENCES `fatty_acid_species` (`fatty_acid_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_modification_id2` FOREIGN KEY (`l_modification_id`) REFERENCES `modification` (`modification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatty_acid_species_has_paper`
--

DROP TABLE IF EXISTS `fatty_acid_species_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fatty_acid_species_has_paper` (
  `l_fatty_acid_species_id` int(11) NOT NULL,
  `l_paper_id` int(11) NOT NULL,
  `lucene_score` decimal(4,3) NOT NULL,
  PRIMARY KEY (`l_fatty_acid_species_id`,`l_paper_id`),
  KEY `l_paper_id7_idx` (`l_paper_id`),
  KEY `l_fatty_acid_species_id3_idx` (`l_fatty_acid_species_id`),
  CONSTRAINT `l_fatty_acid_species_id3` FOREIGN KEY (`l_fatty_acid_species_id`) REFERENCES `fatty_acid_species` (`fatty_acid_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_paper_id7` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `isomer`
--

DROP TABLE IF EXISTS `isomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `isomer` (
  `isomer_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_sub_species_id` int(11) NOT NULL,
  `name` varchar(1024) NOT NULL,
  `smile` varchar(45) NOT NULL,
  `systematic_name` varchar(255) NOT NULL,
  PRIMARY KEY (`isomer_id`),
  KEY `fk_isomer_sub_species1_idx` (`l_sub_species_id`),
  CONSTRAINT `fk_isomer_sub_species1` FOREIGN KEY (`l_sub_species_id`) REFERENCES `sub_species` (`sub_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7585 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `isomer_has_paper`
--

DROP TABLE IF EXISTS `isomer_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `isomer_has_paper` (
  `l_isomer_id` int(11) NOT NULL,
  `l_paper_id` int(11) NOT NULL,
  `lucene_score` decimal(4,3) NOT NULL,
  PRIMARY KEY (`l_isomer_id`,`l_paper_id`),
  KEY `l_paper_id6_idx` (`l_paper_id`),
  KEY `l_isomer_id2_idx` (`l_isomer_id`),
  CONSTRAINT `l_isomer_id2` FOREIGN KEY (`l_isomer_id`) REFERENCES `isomer` (`isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_paper_id6` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `isomer_has_xref`
--

DROP TABLE IF EXISTS `isomer_has_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `isomer_has_xref` (
  `l_isomer_id` int(11) NOT NULL,
  `l_xref_id` int(11) NOT NULL,
  PRIMARY KEY (`l_isomer_id`,`l_xref_id`),
  KEY `l_xref_id1_idx` (`l_xref_id`),
  KEY `l_isomer_id3_idx` (`l_isomer_id`),
  CONSTRAINT `l_isomer_id3` FOREIGN KEY (`l_isomer_id`) REFERENCES `isomer` (`isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_xref_id1` FOREIGN KEY (`l_xref_id`) REFERENCES `xref` (`xref_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `main_class`
--

DROP TABLE IF EXISTS `main_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_class` (
  `main_class_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_category_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  `model` text NOT NULL,
  PRIMARY KEY (`main_class_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_main_class_category_idx` (`l_category_id`),
  CONSTRAINT `fk_main_class_category` FOREIGN KEY (`l_category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `main_class_summary`
--

DROP TABLE IF EXISTS `main_class_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_class_summary` (
  `main_class_summary_id` int(11) NOT NULL,
  `l_main_class_id` int(11) NOT NULL,
  `sub_classes` int(11) NOT NULL,
  `species` int(11) NOT NULL,
  `FA_scan_species` int(11) NOT NULL,
  `sub_species` int(11) NOT NULL,
  `annotated_isomers` int(11) NOT NULL,
  PRIMARY KEY (`main_class_summary_id`),
  KEY `l_main_class_id1_idx` (`l_main_class_id`),
  CONSTRAINT `l_main_class_id1` FOREIGN KEY (`l_main_class_id`) REFERENCES `main_class` (`main_class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mesh_term`
--

DROP TABLE IF EXISTS `mesh_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesh_term` (
  `mesh_term_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) NOT NULL,
  PRIMARY KEY (`mesh_term_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modification`
--

DROP TABLE IF EXISTS `modification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modification` (
  `modification_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  PRIMARY KEY (`modification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modification_has_fatty_acid_isomer`
--

DROP TABLE IF EXISTS `modification_has_fatty_acid_isomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modification_has_fatty_acid_isomer` (
  `l_modification_id` int(11) NOT NULL,
  `l_fatty_acid_isomer_id` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`l_modification_id`,`l_fatty_acid_isomer_id`,`position`),
  KEY `l_fatty_acid_isomer_id4_idx` (`l_fatty_acid_isomer_id`),
  KEY `l_modification_id1_idx` (`l_modification_id`),
  CONSTRAINT `l_fatty_acid_isomer_id4` FOREIGN KEY (`l_fatty_acid_isomer_id`) REFERENCES `fatty_acid_isomer` (`fatty_acid_isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_modification_id1` FOREIGN KEY (`l_modification_id`) REFERENCES `modification` (`modification_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ms_params`
--

DROP TABLE IF EXISTS `ms_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ms_params` (
  `ms_params_id` int(11) NOT NULL,
  `param` varchar(256) NOT NULL,
  PRIMARY KEY (`ms_params_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `pmid` varchar(8) NOT NULL,
  `title` varchar(1024) NOT NULL,
  `abstract` text NOT NULL,
  `date_published` date NOT NULL,
  `journal_title` varchar(1024) NOT NULL,
  `journal_data` varchar(1024) NOT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paper_has_author`
--

DROP TABLE IF EXISTS `paper_has_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper_has_author` (
  `l_paper_id` int(11) NOT NULL,
  `l_author_id` int(11) NOT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`l_paper_id`,`l_author_id`),
  KEY `l_author_id1_idx` (`l_author_id`),
  KEY `l_paper_id1_idx` (`l_paper_id`),
  CONSTRAINT `l_author_id1` FOREIGN KEY (`l_author_id`) REFERENCES `author` (`author_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_paper_id1` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paper_has_mesh_term`
--

DROP TABLE IF EXISTS `paper_has_mesh_term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper_has_mesh_term` (
  `l_paper_id` int(11) NOT NULL,
  `l_mesh_term_id` int(11) NOT NULL,
  PRIMARY KEY (`l_paper_id`,`l_mesh_term_id`),
  KEY `l_mesh_term_id1_idx` (`l_mesh_term_id`),
  KEY `l_paper_id2_idx` (`l_paper_id`),
  CONSTRAINT `l_mesh_term_id1` FOREIGN KEY (`l_mesh_term_id`) REFERENCES `mesh_term` (`mesh_term_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_paper_id2` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species` (
  `species_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_sub_class_id` int(11) NOT NULL,
  `l_composition_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `fa_double_bonds` int(11) NOT NULL,
  `fa_carbons` int(11) NOT NULL,
  `identified` tinyint(1) NOT NULL,
  `score` decimal(4,2) NOT NULL,
  `isomer_count` int(11) NOT NULL,
  `ecode` varchar(10) NOT NULL,
  PRIMARY KEY (`species_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_species_sub_class1_idx` (`l_sub_class_id`),
  KEY `fk_species_composition1_idx` (`l_composition_id`),
  CONSTRAINT `fk_species_composition1` FOREIGN KEY (`l_composition_id`) REFERENCES `composition` (`composition_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_sub_class1` FOREIGN KEY (`l_sub_class_id`) REFERENCES `sub_class` (`sub_class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20298 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `species_has_paper`
--

DROP TABLE IF EXISTS `species_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species_has_paper` (
  `l_species_id` int(11) NOT NULL,
  `l_paper_id` int(11) NOT NULL,
  `lucene_score` decimal(4,3) NOT NULL,
  PRIMARY KEY (`l_species_id`,`l_paper_id`),
  KEY `l_paper_id3_idx` (`l_paper_id`),
  KEY `l_species_id2_idx` (`l_species_id`),
  CONSTRAINT `l_paper_id3` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_species_id2` FOREIGN KEY (`l_species_id`) REFERENCES `species` (`species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `species_has_xref`
--

DROP TABLE IF EXISTS `species_has_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species_has_xref` (
  `l_species_id` int(11) NOT NULL,
  `l_xref_id` int(11) NOT NULL,
  PRIMARY KEY (`l_species_id`,`l_xref_id`),
  KEY `l_xref_id3_idx` (`l_xref_id`),
  KEY `l_species_id4_idx` (`l_species_id`),
  CONSTRAINT `l_species_id4` FOREIGN KEY (`l_species_id`) REFERENCES `species` (`species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_xref_id3` FOREIGN KEY (`l_xref_id`) REFERENCES `xref` (`xref_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spectra`
--

DROP TABLE IF EXISTS `spectra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spectra` (
  `spectra_id` int(11) NOT NULL AUTO_INCREMENT,
  `mz_array` text NOT NULL,
  `intensity_array` text NOT NULL,
  `peak_annotation` text NOT NULL,
  PRIMARY KEY (`spectra_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spectra_has_ms_params`
--

DROP TABLE IF EXISTS `spectra_has_ms_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spectra_has_ms_params` (
  `l_spectra_id` int(11) NOT NULL,
  `l_ms_params_id` int(11) NOT NULL,
  `value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`l_spectra_id`,`l_ms_params_id`),
  KEY `l_ms_params_id1_idx` (`l_ms_params_id`),
  KEY `l_spectra_id1_idx` (`l_spectra_id`),
  CONSTRAINT `l_ms_params_id1` FOREIGN KEY (`l_ms_params_id`) REFERENCES `ms_params` (`ms_params_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_spectra_id1` FOREIGN KEY (`l_spectra_id`) REFERENCES `spectra` (`spectra_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spectra_mapping`
--

DROP TABLE IF EXISTS `spectra_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spectra_mapping` (
  `parent_fk_id` int(11) NOT NULL,
  `table_type_id` int(11) NOT NULL,
  `l_spectra_id` int(11) NOT NULL,
  PRIMARY KEY (`parent_fk_id`,`table_type_id`,`l_spectra_id`),
  KEY `l_spectra_id2_idx` (`l_spectra_id`),
  KEY `l_isomer_id4_idx` (`parent_fk_id`),
  KEY `l_sub_species_id4_idx` (`parent_fk_id`),
  KEY `l_FA_scan_species_id4_idx` (`parent_fk_id`),
  KEY `l_species_id3_idx` (`parent_fk_id`),
  CONSTRAINT `l_FA_scan_species_id4` FOREIGN KEY (`parent_fk_id`) REFERENCES `FA_scan_species` (`FA_scan_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_isomer_id4` FOREIGN KEY (`parent_fk_id`) REFERENCES `isomer` (`isomer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_species_id3` FOREIGN KEY (`parent_fk_id`) REFERENCES `species` (`species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_spectra_id2` FOREIGN KEY (`l_spectra_id`) REFERENCES `spectra` (`spectra_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_sub_species_id4` FOREIGN KEY (`parent_fk_id`) REFERENCES `sub_species` (`sub_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_class`
--

DROP TABLE IF EXISTS `sub_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_class` (
  `sub_class_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_main_class_id` int(11) NOT NULL,
  `name` varchar(90) NOT NULL,
  `code` varchar(45) NOT NULL,
  `model` text NOT NULL,
  `radyl_chains` int(11) NOT NULL,
  PRIMARY KEY (`sub_class_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `l_main_class_id_idx` (`l_main_class_id`),
  CONSTRAINT `l_main_class_id` FOREIGN KEY (`l_main_class_id`) REFERENCES `main_class` (`main_class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_class_summary`
--

DROP TABLE IF EXISTS `sub_class_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_class_summary` (
  `sub_class_summary_id` int(11) NOT NULL,
  `l_sub_class_id` int(11) NOT NULL,
  `species` int(11) NOT NULL,
  `FA_scan_species` int(11) NOT NULL,
  `sub_species` int(11) NOT NULL,
  `annotated_isomers` int(11) NOT NULL,
  PRIMARY KEY (`sub_class_summary_id`),
  KEY `l_sub_class_id1_idx` (`l_sub_class_id`),
  CONSTRAINT `l_sub_class_id1` FOREIGN KEY (`l_sub_class_id`) REFERENCES `sub_class` (`sub_class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_species`
--

DROP TABLE IF EXISTS `sub_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_species` (
  `sub_species_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `identified` tinyint(1) NOT NULL,
  `score` decimal(4,2) NOT NULL,
  PRIMARY KEY (`sub_species_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=36152817 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_species_has_fatty_acid_species`
--

DROP TABLE IF EXISTS `sub_species_has_fatty_acid_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_species_has_fatty_acid_species` (
  `l_sub_species_id` int(11) NOT NULL,
  `l_fatty_acid_species_id` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `linkage` varchar(1) NOT NULL,
  PRIMARY KEY (`l_sub_species_id`,`l_fatty_acid_species_id`,`position`),
  KEY `l_fatty_acid_species_id2_idx` (`l_fatty_acid_species_id`),
  KEY `l_sub_species_id2_idx` (`l_sub_species_id`),
  CONSTRAINT `l_fatty_acid_species_id2` FOREIGN KEY (`l_fatty_acid_species_id`) REFERENCES `fatty_acid_species` (`fatty_acid_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_sub_species_id2` FOREIGN KEY (`l_sub_species_id`) REFERENCES `sub_species` (`sub_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_species_has_paper`
--

DROP TABLE IF EXISTS `sub_species_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_species_has_paper` (
  `l_sub_species_id` int(11) NOT NULL,
  `l_paper_id` int(11) NOT NULL,
  `lucene_score` decimal(4,3) NOT NULL,
  PRIMARY KEY (`l_sub_species_id`,`l_paper_id`),
  KEY `l_paper_id5_idx` (`l_paper_id`),
  KEY `l_sub_species_id3_idx` (`l_sub_species_id`),
  CONSTRAINT `l_paper_id5` FOREIGN KEY (`l_paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_sub_species_id3` FOREIGN KEY (`l_sub_species_id`) REFERENCES `sub_species` (`sub_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_species_has_xref`
--

DROP TABLE IF EXISTS `sub_species_has_xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_species_has_xref` (
  `l_sub_species_id` int(11) NOT NULL,
  `l_xref_id` int(11) NOT NULL,
  PRIMARY KEY (`l_sub_species_id`,`l_xref_id`),
  KEY `l_xref_id5_idx` (`l_xref_id`),
  KEY `l_sub_species_id5_idx` (`l_sub_species_id`),
  CONSTRAINT `l_sub_species_id5` FOREIGN KEY (`l_sub_species_id`) REFERENCES `sub_species` (`sub_species_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `l_xref_id5` FOREIGN KEY (`l_xref_id`) REFERENCES `xref` (`xref_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `xref`
--

DROP TABLE IF EXISTS `xref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xref` (
  `xref_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_resource_id` int(11) NOT NULL,
  `url` varchar(2048) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`xref_id`),
  KEY `l_resource_id1_idx` (`l_resource_id`),
  CONSTRAINT `l_resource_id1` FOREIGN KEY (`l_resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8896 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'lipidhome'
--

--
-- Dumping routines for database 'lipidhome'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-14  8:44:24
