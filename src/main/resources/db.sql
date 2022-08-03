-- USERS
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  `username` varchar(45) UNIQUE NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', '1');
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('user', '$2a$12$ikTFT78RZ8vBZQ67XQJveOGtfKhlLhLjlzNEqp6LZzdBdZbjNApqK', '1');
--

-- ROLES
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
);

INSERT INTO `roles` (`name`) VALUES ('ADMIN');
INSERT INTO `roles` (`name`) VALUES ('USER');
--


-- USER_ROLES
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `user_fk_idx` (`user_id`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1); -- admin role admin
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 2); -- admin role admin
--

-- SONGS
CREATE TABLE `songs` (
  `song_id` int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  `title` nvarchar(100),
  `artist_id` nvarchar(64),
  `album_id` nvarchar(64),
  PRIMARY KEY (`song_id`)
);

INSERT INTO `songs` (`title`, `artist_id`, `album_id`) VALUES ('All Too Well', '1', '1');
INSERT INTO `songs` (`title`, `artist_id`, `album_id`) VALUES ('I Almost Do', '1', '1');
--

-- ARTISTS
CREATE TABLE `artists` (
  `artist_id` int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  `name` nvarchar(100),
  `description` nvarchar(1000),
  PRIMARY KEY (`artist_id`)
);
INSERT INTO `artists` (`name`, `description`) VALUES ('Taylor Swift', 'Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. Her discography spans multiple genres, and her narrative songwriting—often inspired by her personal life—has received critical praise and widespread media coverage. Born in West Reading, Pennsylvania, Swift moved to Nashville, Tennessee, at the age of 14 to pursue a career in country music. She signed a songwriting contract with Sony/ATV Music Publishing in 2004 and a recording deal with Big Machine Records in 2005, and released her eponymous debut studio album in 2006.');
--

-- ALBUMS
CREATE TABLE `albums` (
  `album_id` int NOT NULL UNIQUE AUTO_INCREMENT,
  `title` nvarchar(100),
  `description` nvarchar(1000),
  PRIMARY KEY (`album_id`)
);
INSERT INTO `albums` (`title`, `description`) VALUES ('Red', 'Red is the fourth studio album by American singer-songwriter Taylor Swift. It was released on October 22, 2012, by Big Machine Records. The album''s title refers to the tumultuous, "red" emotions Swift experienced during the album''s conception; its songs discuss the complex and conflicting feelings resulting from fading romance.');

--
