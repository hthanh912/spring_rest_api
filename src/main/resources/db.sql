drop database if exists db;
create database db;
use db;

-- USERS
CREATE TABLE users (
  user_id int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  username varchar(45) UNIQUE NOT NULL,
  password varchar(64) NOT NULL,
  enabled tinyint(4) DEFAULT NULL,
  PRIMARY KEY (user_id)
);

-- ROLES
CREATE TABLE roles (
  role_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  PRIMARY KEY (role_id)
);

-- USER_ROLES
CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  CONSTRAINT users_roles_fk_role_id FOREIGN KEY (role_id) REFERENCES roles (role_id),
  CONSTRAINT users_roles_fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- ARTISTS
CREATE TABLE artists (
  artist_id int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  name nvarchar(100),
  description nvarchar(1000),
  PRIMARY KEY (artist_id)
);
-- ALBUMS
CREATE TABLE albums (
  album_id int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  artist_id int(11),
  title nvarchar(100),
  description nvarchar(1000),
  PRIMARY KEY (album_id),
  FOREIGN KEY (artist_id) REFERENCES artists (artist_id)
);

-- SONGS
CREATE TABLE songs (
  song_id int(11) NOT NULL UNIQUE AUTO_INCREMENT,
  title nvarchar(100),
  artist_id int(11),
  album_id int(11),
  PRIMARY KEY (song_id),
  FOREIGN KEY (artist_id) REFERENCES artists (artist_id) ON DELETE CASCADE,
  FOREIGN KEY (album_id) REFERENCES albums (album_id) ON DELETE CASCADE
);

CREATE TABLE users_songs (
    user_id int(11),
    song_id int(11),
	CONSTRAINT users_songs_fk_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT users_songs_fk_song FOREIGN KEY (song_id) REFERENCES songs (song_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- insert
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', '1');
INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$12$ikTFT78RZ8vBZQ67XQJveOGtfKhlLhLjlzNEqp6LZzdBdZbjNApqK', '1');
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1); -- admin role admin
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2); -- admin role admin
INSERT INTO artists (name, description) VALUES ('Taylor Swift', 'Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. Her discography spans multiple genres, and her narrative songwriting—often inspired by her personal life—has received critical praise and widespread media coverage. Born in West Reading, Pennsylvania, Swift moved to Nashville, Tennessee, at the age of 14 to pursue a career in country music. She signed a songwriting contract with Sony/ATV Music Publishing in 2004 and a recording deal with Big Machine Records in 2005, and released her eponymous debut studio album in 2006.');
INSERT INTO artists (name, description) VALUES ('Linkin Park', 'Linkin Park is an American rock band from Agoura Hills, California. The band''s current lineup comprises vocalist/rhythm guitarist/keyboardist Mike Shinoda, lead guitarist Brad Delson, bassist Dave Farrell, DJ/turntablist Joe Hahn and drummer Rob Bourdon, all of whom are founding members. Vocalists Mark Wakefield and Chester Bennington are former members of the band. Categorized as alternative rock, Linkin Park''s earlier music spanned a fusion of heavy metal and hip hop, while their later music features more electronica and pop elements.');
INSERT INTO artists (name, description) VALUES ('Evanescence', 'Evanescence is an American rock band founded in Little Rock, Arkansas, in 1995 by singer, pianist, and keyboardist Amy Lee and guitarist Ben Moody. After recording independent albums, the band released their first full-length album, Fallen, on Wind-up Records in 2003. On the strength of hit singles "Bring Me to Life" and "My Immortal", Fallen sold more than 17 million copies worldwide and helped the band win two Grammy Awards out of six nominations. A year later, Evanescence released their first live album, Anywhere but Home, which sold more than one million copies worldwide. In 2006, the band released their second studio album, The Open Door, which sold more than five million copies.');
INSERT INTO artists (name, description) VALUES ('Of Monsters and Men', 'Of Monsters and Men is an Icelandic indie folk/rock band formed in Reykjavík in 2010. The members are lead singer and guitarist Nanna Bryndís Hilmarsdóttir, singer and guitarist Ragnar \"Raggi\" Þórhallsson, lead guitarist Brynjar Leifsson, drummer Arnar Rósenkranz Hilmarsson and bassist Kristján Páll Kristjánsson.[8][9] The band won the Músíktilraunir in 2010, an annual battle of the bands competition in Iceland.');
INSERT INTO albums (title, description, artist_id) VALUES ('Red', 'Red is the fourth studio album by American singer-songwriter Taylor Swift. It was released on October 22, 2012, by Big Machine Records. The album''s title refers to the tumultuous, "red" emotions Swift experienced during the album''s conception; its songs discuss the complex and conflicting feelings resulting from fading romance.', 1);
INSERT INTO albums (title, description, artist_id) VALUES ('My Head Is an Animal', 'My Head Is an Animal is the debut studio album by the Icelandic indie folk band Of Monsters and Men,[2] released through Record Records in Iceland on 20 September 2011. After their success, topping the Icelandic charts with their debut single, \"Little Talks\", the band signed with Universal Music Group and the album was released internationally through Republic Records on 3 April 2012. The title of the album comes from the second line in \"Dirty Paws\".', 4);
INSERT INTO songs (title, artist_id, album_id) VALUES ('All Too Well', 1, 1);
INSERT INTO songs (title, artist_id, album_id) VALUES ('I Almost Do', 1, 1);
INSERT INTO users_songs VALUES(1,1);
