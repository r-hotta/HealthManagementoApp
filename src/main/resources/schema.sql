CREATE TABLE form(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	seibetu VARCHAR(100),
	age INT NOT NULL,
	sinntyou INT NOT NULL,
	taijuu INT NOT NULL,
	ketuatuue INT NOT NULL,
	ketuatusita INT NOT NULL,
	memo VARCHAR (100),
	type VARCHAR (100),
	PRIMARY KEY(id)
);
CREATE TABLE batform(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	seibetu VARCHAR(100),
	age INT NOT NULL,
	sinntyou  INT NOT NULL,
	taijuu INT NOT NULL,
	ketuatuue INT NOT NULL,
	ketuatusita INT NOT NULL,
	memo VARCHAR (100) ,
	type VARCHAR (100) ,
	PRIMARY KEY(id)
);