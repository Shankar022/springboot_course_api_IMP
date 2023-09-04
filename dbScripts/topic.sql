CREATE DATABASE  IF NOT EXISTS `course_spring`;
USE `course_spring`;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name`varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

select * from topic;

