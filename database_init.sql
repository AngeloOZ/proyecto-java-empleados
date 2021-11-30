create table puesto
(
	puesto_id bigint not null primary key,
	nombre_puesto varchar(60) unique
);



create table empleado
(
    id bigint not null primary key,
    nombre varchar(60) not null unique,
    correo varchar(60) not null unique,
    puesto_id bigint null,
    foreign key (puesto_id) references puesto(puesto_id)
);



create table empleado_imagen
(
   empleado_id bigint not null primary key,
   miniatura text
);

create sequence empleado_id_seq start with 1 increment by 1;
create sequence puesto_id_seq start with 1 increment by 1;
