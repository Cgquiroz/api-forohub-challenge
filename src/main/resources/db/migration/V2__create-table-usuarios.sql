create table usuarios(
    id bigint not null auto_increment,
    login VARCHAR(100) NOT NULL,
    clave VARCHAR(300) NOT NULL,

    primary key(id)
);