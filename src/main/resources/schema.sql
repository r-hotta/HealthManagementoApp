CREATE TABLE form(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	seibetu VARCHAR(100),
	age INT,
	sinntyou INT,
	taijuu INT,
	ketuatuue INT,
	ketuatusita INT,
	memo VARCHAR (100),
	type VARCHAR (100),
	PRIMARY KEY(id)
);
CREATE TABLE batform(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	seibetu VARCHAR(100),
	age INT,
	sinntyou  INT,
	taijuu INT,
	ketuatuue INT,
	ketuatusita INT,
	memo VARCHAR (100) ,
	type VARCHAR (100) ,
	PRIMARY KEY(id)
);