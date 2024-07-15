CREATE TABLE topicos(

     id bigint not null auto_increment,
     titulo VARCHAR(255) NOT NULL UNIQUE,
     mensaje VARCHAR(500) NOT NULL UNIQUE,
     fecha DATETIME NOT NULL,
     status ENUM('POSTEADO', 'RESUELTO') NOT NULL,
     autor VARCHAR(255) NOT NULL,
     curso VARCHAR(255) NOT NULL,

     primary key(id)
);