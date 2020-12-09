 CREATE database usuarios;
\c usuarios;  \*para postgres*\
USE usuarios;  \*para MySQL*\
CREATE table admin (
	correo varchar (50) not null, 
	pass varchar(10) not null
);
INSERT into admin values('rtecla2020','poo2cm4');