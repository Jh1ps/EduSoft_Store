drop table institucion;
create table institucion(
id int primary key,
nombre varchar(100) not null,
provincia varchar(50) not null
);

drop table persona;
create table persona(
id int primary key,
nombre varchar(20) not null,
apellido1 varchar(20) not null,
apellido2 varchar(20) not null,
telefono varchar(20),
correo varchar(30) not null,
direccion varchar(30) not null,
idInstitucion int not null,
foreign key (idInstitucion) references institucion(id)
);

drop table usuario;
create table usuario(
correo varchar(50) not null,
contrasena varchar(30) not null,
idPersona int not null,
privilegio varchar(20),
primary key(idPersona),
foreign key (idPersona) references persona(id)
);

drop table datoPago;
create table datoPago(
id int primary key,
n_tarjeta int not null,
exp date not null,
cvv int not null
);

drop table fuente;
create table fuente(
id int not null primary key,
nombreFuente varchar(50),
descripcion varchar(100),
enlace varchar(100)
);

drop table prodSoftware;
create table prodSoftware(
id int primary key,
nombre varchar(30) not null,
descripcion varchar(100) not null,
tamano varchar(20) not null,
precio decimal(7,2) not null,
disponibilidad char(8) not null,
version varchar(30) not null,
categoria varchar(50) not null
);

drop table prodEbook;
create table prodEbook(
id int primary key,
nombre varchar(30) not null,
descripcion varchar(100) not null,
tamano varchar(20) not null,
precio decimal(7,2) not null,
disponibilidad char(8) not null,
categoria varchar(50) not null
);

drop table fuente_software;
create table fuente_software(
idArticulo int not null,
idSoftware int not null,
idFuente int not null,
primary key(idArticulo),
foreign key (idSoftware) references prodSoftware (id),
foreign key (idFuente) references fuente (id)
);

drop table fuente_ebook;
create table fuente_ebook(
idArticulo int not null,
idEbook int not null,
idFuente int not null,
primary key(idArticulo),
foreign key (idEbook) references prodEbook (id),
foreign key (idFuente) references fuente (id)
);

drop table compra;
create table compra(
numeroCompra int primary key,
idSoft int not null,
idEbook int,
idPersona int,
idDatoPago int not null, 
foreign key (idSoft) references fuente_software(idArticulo),
foreign key (idEbook) references fuente_ebook(idArticulo),
foreign key (idPersona) references persona(id),
foreign key (idDatoPago) references datoPago(id) 
);

drop table transaccion;
create table transaccion(
id int not null primary key,
fecha date not null,
tipo varchar(15) not null,
numeroCompra int not null,
foreign key (numeroCompra) references compra(numeroCompra) 
);

insert into institucion values(1, 'Universidad Fidelitas', 'San Jose');
insert into persona values(1, 'Juan', 'Hernandez', 'Mora', '8888888', 'juan@gmail.com', 'San Jose, San Pedro', 1);
insert into persona values(2, 'Maria', 'Zamora', 'Loaiza', '5555555', 'maria@gmail.com', 'San Jose, San Pedro', 1);
insert into usuario values('juan@gmail.com', '123', 1, 'usuario');
insert into usuario values('maria@gmail.com', '321', 2, 'respaldo');
insert into datoPago values(1, 123456789, '2020-06-29', 649);
insert into fuente values(1, 'microsoft', 'Producto software varios', 'https://www.microsoft.com/es-cr/');
insert into prodSoftware values(1, 'Office', 'suite ofimatica', '600mb', 65000, 'pago', '2019', 'ofimatica');
insert into prodEbook values(1, 'Introduccion a Office 2019', 'suite ofimatica', '75kb', 0, 'gratis', 'ofimatica');
insert into fuente_software values(1, 1, 1);
insert into fuente_ebook values(1, 1, 1);
insert into compra values(1, 1, null, 1, 1);
insert into transaccion values(1, '2020-03-29', 'compra', 1);

insert into fuente values(2, 'Apache Software Foundation', 'soporte a los proyectos de software bajo la denominaci�n Apache', 'https://netbeans.apache.org/');
insert into fuente values(3, 'Autodesk', 'compa��a dedicada al software de dise�o en 2D y 3D', 'https://www.autodesk.com/');
insert into fuente values(4, 'Foxit Software, Inc', 'productos y servicios de PDF', 'https://www.foxitsoftware.com/es-la/');
insert into fuente values(5, 'Cisco Systems', 'dedicada a la fabricaci�n, venta, mantenimiento y consultor�a de equipos de telecomunicaciones.', 'https://www.netacad.com/es');
insert into fuente values(6, 'Axure Software Solutions', 'compa�ia dedicada al software para el manejo de proyectos y soluciones en prototipos', 'https://www.axure.com/');
insert into fuente values(7, 'Duolingo', 'proyecto social destinado al aprendizaje gratuito de idiomas', 'https://es.duolingo.com/');
insert into fuente values(8, 'GitHub Inc', 'forja para alojar proyectos utilizando el sistema de control de versiones', 'https://github.com/');
insert into fuente values(9, 'Evernote', 'desarrollo de software dirigido a la organizaci�n de informaci�n', 'https://evernote.com/intl/es');
insert into fuente values(10, 'RarLab', 'desarrollo de software de compresi�n de datos', 'https://www.rarlab.com/');
insert into fuente values(11, 'StataCorp', 'l�der en software estad�stico', 'https://www.stata.com/');


insert into prodsoftware values(2, 'Netbeans', 'compilador', '180mb', 0, 'gratis', '11.2', 'desarrollado software');
insert into prodsoftware values(3, 'SQL server management', 'manejo de base de datos', '551mb', 0, 'trial', '2019', 'base de datos');
insert into prodsoftware values(4, 'Autocad', 'diseno grafico', '4gb', 0, 'trial', '2020', 'diseno grafico');
insert into prodsoftware values(5, 'Packet Tracer', 'simulador de redes', '203mb', 0, 'gratis', '7.2.2', 'redes');
insert into prodsoftware values(6, 'Foxit PhantomPDF', 'lector de archivos', '116mb', 0, 'trial', 'Standar', 'ofimatica');
insert into prodsoftware values(7, 'Axure', 'software de enmarcado', '112mb', 0, 'trial', '2020', 'desarrollado software');
insert into prodsoftware values(8, 'Doulingo', 'aprendizaje de idiomas', '15.5mb', 0, 'gratis', 'android', 'idiomas');
insert into prodsoftware values(9, 'Atom', 'compilador', '185mb', 0, 'gratis', '1.45.0', 'desarrollado software');
insert into prodsoftware values(10, 'Evernote', 'organizador', '125mb', 0, 'gratis', '6.24.2', 'ofimatica');
insert into prodsoftware values(11, 'Winrar', 'compresor', '3.17mb', 0, 'trial', '5.90', 'compresion');
insert into prodsoftware values(12, 'Stata', 'controlador de datos', '1gb', 15000, 'pago', '11.2', 'estadistica');

insert into fuente_software values(2,2,2);
insert into fuente_software values(3,3,1);
insert into fuente_software values(4,4,3);
insert into fuente_software values(5,5,5);
insert into fuente_software values(6,6,4);
insert into fuente_software values(7,7,6);
insert into fuente_software values(8,8,7);
insert into fuente_software values(9,9,8);
insert into fuente_software values(10,10,9);
insert into fuente_software values(11,11,10);
insert into fuente_software values(12,12,11);

CREATE TRIGGER TG_PERSONA
AFTER UPDATE ON PERSONA
REFERENCING  NEW AS NEW
FOR EACH ROW MODE DB2SQL
UPDATE USUARIO SET CORREO=NEW.CORREO
WHERE USUARIO.IDPERSONA=NEW.ID;