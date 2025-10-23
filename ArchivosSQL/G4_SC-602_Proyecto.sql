USE master
GO

IF DB_ID ('Veterinaria_Proyecto') IS NOT NULL
	DROP DATABASE Veterinaria_Proyecto;
GO

CREATE DATABASE Veterinaria_Proyecto;
GO

USE Veterinaria_Proyecto;
GO

CREATE TABLE Provincia (
    idProvincia INT IDENTITY(1,1) PRIMARY KEY,
    nombreProvincia VARCHAR(50) NOT NULL
);
GO

CREATE TABLE Canton (
    idCanton INT IDENTITY(1,1) PRIMARY KEY,
    nombreCanton VARCHAR(50) NOT NULL,
    idProvincia INT NOT NULL,
    FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
);
GO

CREATE TABLE Distrito (
    idDistrito INT IDENTITY(1,1) PRIMARY KEY,
    nombreDistrito VARCHAR(50) NOT NULL,
    idCanton INT NOT NULL,
    FOREIGN KEY (idCanton) REFERENCES Canton(idCanton)
);
GO

CREATE TABLE Especies (
    ID_Especie INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(25) NOT NULL
);
GO

CREATE TABLE Razas (
    ID_Raza INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(25) NOT NULL,
    especies_ID_Especie INT NOT NULL,
    FOREIGN KEY (especies_ID_Especie) REFERENCES Especies(ID_Especie)
);
GO

CREATE TABLE Tipos_Proveedor (
    ID_Tipo_Proveedor INT IDENTITY(1,1) PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);
GO

CREATE TABLE Proveedores (
    ID_Proveedor INT IDENTITY(1,1) PRIMARY KEY,
    ID_Tipo_Proveedor INT NOT NULL,
    Nombre_Empresa VARCHAR(100) NOT NULL,
    FOREIGN KEY (ID_Tipo_Proveedor) REFERENCES Tipos_Proveedor(ID_Tipo_Proveedor)
);
GO

CREATE TABLE Tipo_Productos (
    ID_Tipo_Producto INT IDENTITY(1,1) PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);
GO

CREATE TABLE Medicamentos (
    ID_Medicamento INT IDENTITY(1,1) PRIMARY KEY,
    Nombre_Medicamento VARCHAR(100) NOT NULL
);
GO

CREATE TABLE Productos (
    ID_Producto INT IDENTITY(1,1) PRIMARY KEY,
    ID_Medicamento INT NULL,
    ID_Tipo_Producto INT NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Precio_Unitario DECIMAL(10, 2) NOT NULL,
    Stock INT NOT NULL,
    FOREIGN KEY (ID_Medicamento) REFERENCES Medicamentos(ID_Medicamento),
    FOREIGN KEY (ID_Tipo_Producto) REFERENCES Tipo_Productos(ID_Tipo_Producto)
);
GO

CREATE TABLE Productos_Proveedor (
    ID_Producto INT NOT NULL,
    ID_Proveedor INT NOT NULL,
    PRIMARY KEY (ID_Producto, ID_Proveedor),
    FOREIGN KEY (ID_Producto) REFERENCES Productos(ID_Producto),
    FOREIGN KEY (ID_Proveedor) REFERENCES Proveedores(ID_Proveedor)
);
GO

CREATE TABLE Medicos_Veterinarios (
    ID_Medico INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(20) NOT NULL,
    Apellido1 VARCHAR(20) NOT NULL,
    Apellido2 VARCHAR(20) NULL,
    Email VARCHAR(30) NULL
);
GO

CREATE TABLE Clientes (
    ID_Cliente INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(20) NOT NULL,
    Apellido1 VARCHAR(20) NOT NULL,
    Apellido2 VARCHAR(20) NULL,
    Direccion VARCHAR(100) NULL,
    Email VARCHAR(50) NULL,
    idDistrito INT NOT NULL,
    FOREIGN KEY (idDistrito) REFERENCES Distrito(idDistrito)
);
GO

CREATE TABLE Animales (
    ID_Animal INT IDENTITY(1,1) PRIMARY KEY,
    ID_Raza INT NOT NULL,
    ID_Cliente INT NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Edad INT NULL,
    Peso FLOAT NULL,
    FOREIGN KEY (ID_Raza) REFERENCES Razas(ID_Raza),
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente)
);
GO

CREATE TABLE Factura (
    idFactura INT IDENTITY(1,1) PRIMARY KEY,
    animales_ID_Animal INT NOT NULL,
    fecha DATETIME NOT NULL,
    FOREIGN KEY (animales_ID_Animal) REFERENCES Animales(ID_Animal)
);
GO

CREATE TABLE Citas (
    ID_Cita INT IDENTITY(1,1) PRIMARY KEY,
    ID_Animal INT NOT NULL,
    ID_Medico INT NOT NULL,
    Fecha DATE NOT NULL,
    Motivo VARCHAR(255) NULL,
    montoCita DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (ID_Animal) REFERENCES Animales(ID_Animal),
    FOREIGN KEY (ID_Medico) REFERENCES Medicos_Veterinarios(ID_Medico)
);
GO

CREATE TABLE Tipos_de_Diagnostico (
    ID_Tipo_Diagnostico INT IDENTITY(1,1) PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);
GO

CREATE TABLE Diagnosticos (
    ID_Diagnostico INT IDENTITY(1,1) PRIMARY KEY,
    ID_Cita INT NOT NULL,
    ID_Tipo_Diagnostico INT NOT NULL,
    Observaciones_medicas TEXT NULL,
    FOREIGN KEY (ID_Cita) REFERENCES Citas(ID_Cita),
    FOREIGN KEY (ID_Tipo_Diagnostico) REFERENCES Tipos_de_Diagnostico(ID_Tipo_Diagnostico)
);
GO

CREATE TABLE Tratamientos (
    ID_Tratamiento INT IDENTITY(1,1) PRIMARY KEY,
    ID_Diagnostico INT NOT NULL,
    ID_Medicamento INT NOT NULL,
    dosis INT NOT NULL,
    FOREIGN KEY (ID_Diagnostico) REFERENCES Diagnosticos(ID_Diagnostico),
    FOREIGN KEY (ID_Medicamento) REFERENCES Medicamentos(ID_Medicamento)
);
GO

CREATE TABLE DetalleFactura (
    idDetalleFactura INT IDENTITY(1,1) PRIMARY KEY,
    productos_ID_Producto INT NOT NULL,
    Factura_idFactura INT NOT NULL,
    Cantidad INT NOT NULL,
    citas_ID_Cita INT NULL,
    tratamientos_ID_Tratamiento INT NULL,
    FOREIGN KEY (productos_ID_Producto) REFERENCES Productos(ID_Producto),
    FOREIGN KEY (Factura_idFactura) REFERENCES Factura(idFactura),
    FOREIGN KEY (citas_ID_Cita) REFERENCES Citas(ID_Cita),
    FOREIGN KEY (tratamientos_ID_Tratamiento) REFERENCES Tratamientos(ID_Tratamiento)
);
GO