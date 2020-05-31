

CREATE TABLE IF NOT EXISTS `billet` (
  `is_utilise` tinyint(1) NOT NULL,
  `exposition_id` bigint(20) DEFAULT NULL,
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `qr_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKyqum5yn6r22l31u45v218gyd` (`exposition_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;


CREATE TABLE IF NOT EXISTS `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `posted_date` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;



CREATE TABLE IF NOT EXISTS `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `evenement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `evenement` varchar(255) DEFAULT NULL,
  `id_oeuvre` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;



CREATE TABLE IF NOT EXISTS `exposition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `decription` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;



CREATE TABLE IF NOT EXISTS `exposition_comment_list` (
  `exposition_id` bigint(20) NOT NULL,
  `comment_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_mgqigvvkvm17kbvjxkwsq9t9l` (`comment_list_id`),
  KEY `FK3idnafid6p59gfhb0jo9fbwsk` (`exposition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(13);


CREATE TABLE IF NOT EXISTS `historique` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_oeuvres` bigint(20) NOT NULL DEFAULT '0',
  `evenement` varchar(60) NOT NULL,
  `date` varchar(60) NOT NULL,
  `id_oeuvre` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_oeuvres` (`id_oeuvres`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `oeuvre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_achete` bit(1) DEFAULT NULL,
  `is_expose` bit(1) DEFAULT NULL,
  `nom_oeuvre` varchar(255) DEFAULT NULL,
  `salle_id` bigint(20) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `is_restaure` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK3uktlncqtsac3tm56g6nfj12k` (`salle_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

CREATE TABLE IF NOT EXISTS `restauration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_restauration` date DEFAULT NULL,
  `id_oeuvre` bigint(20) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;


CREATE TABLE IF NOT EXISTS `salle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_disponible` bit(1) DEFAULT NULL,
  `nom_salle` varchar(255) DEFAULT NULL,
  `exposition_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2hvk0um2lbmdhs8s7lobx9rca` (`exposition_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;


CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `adresse_email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;



INSERT INTO `user` (`id`, `name`, `password`, `role`, `username`, `adresse_email`) VALUES
(1, 'jonas', '$2a$10$QA.Vb4I8sMBLRYg3c/pcTOJ7tOPZpRjt.iQPAYWMoFnIg3TfOEes6', 'ADMIN', 'jonas', ''),
(2, 'jonas', '$2a$10$kjEBLzNi0ztvZvP42y/aueSKR1RjBefdwpcfUgItCCy5UAx35jzQG', 'USER', 'jonas', ''),
(3, 'mateo', '$2a$10$JtKjYlBGYFkrfJVItbjbmep1bpBkpHltfJrPe5P..KjRV/yMTIDV.', 'USER', 'mateo', ''),
(4, 'lisan', '$2a$10$vlvkhQLbtDLk4PhD2a/jIeuw04tZj1cuXYKlBOsf5XE7r6a1GuvXu', 'USER', 'lisan', ''),
(5, 'bineta', '$2a$10$SOrkdes31TXSicljWs1MxOOKdvAirDABirwwZ0GhZfe46uw4LKEZi', 'USER', 'bineta', ''),
(6, 'selam', '$2a$10$addJG/RWAMGTBnqpSFgIMuXK2F/rQGED81p22vQfFf.v6QRGp.j2K', 'ADMIN', 'selam', ''),
(7, 'malik', '$2a$10$umqeAAmdWR9A5ZWUtSVeEeUybPLeogczYIu9iyiolWf0zXvUTY21W', 'ADMIN', 'malik', 'malikabdallah75019@gmail.com'),
(8, 'patience', '$2a$10$/MsIhyfYZEnGjPZPy3lEfua0/QxKPfLArVnKZSZeCckNNiQRgx18u', 'USER', 'patience', 'patience@gmail.com'),
(9, 'david', 'david', 'ADMIN', 'david', 'david@gmail.com'),
(10, 'tepa', '$2a$10$8E9RubxQ6.o4rBXt1buZR.tQcYMuxkCxKhICU3zNqw1OlhSjWJm6C', 'USER', 'tepa', 'tepa@gmail.com');


ALTER TABLE `billet`
  ADD CONSTRAINT `FKyqum5yn6r22l31u45v218gyd` FOREIGN KEY (`exposition_id`) REFERENCES `exposition` (`id`);


ALTER TABLE `exposition_comment_list`
  ADD CONSTRAINT `FK2iemff2l3uwhbxirpk1c435jj` FOREIGN KEY (`comment_list_id`) REFERENCES `comment` (`id`),
  ADD CONSTRAINT `FK3idnafid6p59gfhb0jo9fbwsk` FOREIGN KEY (`exposition_id`) REFERENCES `exposition` (`id`);

ALTER TABLE `oeuvre`
  ADD CONSTRAINT `FK3uktlncqtsac3tm56g6nfj12k` FOREIGN KEY (`salle_id`) REFERENCES `salle` (`id`);

ALTER TABLE `salle`
  ADD CONSTRAINT `FK2hvk0um2lbmdhs8s7lobx9rca` FOREIGN KEY (`exposition_id`) REFERENCES `exposition` (`id`);
