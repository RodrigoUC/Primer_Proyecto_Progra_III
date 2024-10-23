CREATE DATABASE Pos;

use Pos;

create table Categoria (
		id varchar(10) not null,
		nombre varchar(30) not null,
		Primary Key (id)
	);
	
create table Producto (
		codigo varchar(10) not null,
		descripcion varchar(30) not null,
		unidad varchar(20),
		precio float,
		existencia int,
		categoria varchar(10),
		Primary Key (codigo)	
	);

create table Cajero (
	id varchar(10) not null,
	nombre varchar(45) not null,
	Primary Key (id)
	);
	
create table Cliente (
		id varchar(10) not null,
		nombre varchar(45) not null,
		telefono varchar(8) not null,
		email varchar(45) not null,
		descuento float not null,
		Primary Key (id)
	);
	
create table Linea (
		codigo int AUTO_INCREMENT,
		producto varchar(10),
		factura int,
		cantidad int not null,
		descuento float not null,
		Primary Key (codigo)
	);
	
create table Factura (
		codigo int AUTO_INCREMENT,
		cajero varchar(10),
		cliente varchar(10),
        fecha date,
		Primary Key (codigo)
	);
	
ALTER TABLE Producto ADD Foreign Key (categoria) REFERENCES Categoria(id);
ALTER TABLE Factura ADD Foreign Key (cajero) REFERENCES Cajero(id);
ALTER Table Factura ADD Foreign Key (cliente) REFERENCES Cliente(id);
ALTER TABLE Linea ADD Foreign Key (producto) REFERENCES Producto(codigo);
ALTER TABLE Linea ADD Foreign Key (factura) REFERENCES Factura(codigo);

insert into Cliente (id, nombre, telefono, email, descuento) 
	values('001', 'Juan Mena', '62745212', 'juanmena@gmail.com', 0.0);
insert into Cliente (id, nombre, telefono, email, descuento) 
	values('002', 'Maria Sanchez', '84569012', 'mariasanchez@gmail.com', 0.0);
insert into Cliente (id, nombre, telefono, email, descuento) 
	values('003', 'Aurelio Aguilar', '70204532', 'auerlioaguilar@gmail.com', 0.0);	

insert into Cajero (id, nombre) values('001', 'Andres Fernandez');
insert into Cajero (id, nombre) values('002', 'Fernanda Sanarrusia');
insert into Cajero (id, nombre) values('003', 'Luis Rubi');

insert into Categoria (id, nombre) values('001', 'Aguas');
insert into Categoria (id, nombre) values('002', 'Dulces');
insert into Categoria (id, nombre) values('003', 'Aceites');
insert into Categoria (id, nombre) values('004', 'Vinos');

insert into Producto (codigo, descripcion, unidad, precio, existencia, categoria) 
	values('34567', 'Agua', 'Botella', 198, 300.0, '002');
insert into Producto (codigo, descripcion, unidad, precio, existencia, categoria) 
	values('45678', 'Ketchup', 'Paquete', 498, 50.0, '001');
insert into Producto (codigo, descripcion, unidad, precio, existencia, categoria) 
	values('23456', 'Pan', 'Bolsa', 20, 200.0, '003');
insert into Producto (codigo, descripcion, unidad, precio, existencia, categoria) 
	values('12345', 'Vino', 'Botella', 50, 350.0, '004');

insert into Factura (cajero, cliente, fecha) values ('001', '001', '2024-10-19');
insert into Factura (cajero, cliente, fecha) values ('001', '002', '2024-10-19');
insert into Factura (cajero, cliente, fecha) values ('001', '003', '2024-10-19');
insert into Factura (cajero, cliente, fecha) values ('002', '002', '2024-11-22');
insert into Factura (cajero, cliente, fecha) values ('002', '002', '2024-11-22');

insert into Linea (producto, factura, cantidad, descuento) values ('34567', '1', 5, 0);
insert into Linea (producto, factura, cantidad, descuento) values ('23456', '2', 2, 0);
insert into Linea (producto, factura, cantidad, descuento) values ('45678', '2', 7, 10);
insert into Linea (producto, factura, cantidad, descuento) values ('12345', '3', 2, 0);
insert into Linea (producto, factura, cantidad, descuento) values ('45678', '3', 4, 10);
insert into Linea (producto, factura, cantidad, descuento) values ('12345', '4', 1, 0);
insert into Linea (producto, factura, cantidad, descuento) values ('23456', '4', 2, 10);
insert into Linea (producto, factura, cantidad, descuento) values ('34567', '5', 7, 0);
insert into Linea (producto, factura, cantidad, descuento) values ('45678', '5', 6, 10);