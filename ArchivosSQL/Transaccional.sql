USE master
GO

IF DB_ID ('Veterinaria_ProyectoSA') IS NULL
	CREATE DATABASE Veterinaria_ProyectoSA;
GO

USE Veterinaria_ProyectoSA;
GO

IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'tra')
BEGIN
    EXEC('CREATE SCHEMA tra');
END
GO

-- Esto es por si ya se habia creado algo y optamos por la solucion facil de borrar todo y crearlo de nuevo :)

IF OBJECT_ID('tra.Servicios_H') IS NOT NULL DROP TABLE tra.Servicios_H;
GO
IF OBJECT_ID('tra.DimTratamientos') IS NOT NULL DROP TABLE tra.DimTratamientos;
IF OBJECT_ID('tra.DimMedico') IS NOT NULL DROP TABLE tra.DimMedico;
IF OBJECT_ID('tra.DimAnimales') IS NOT NULL DROP TABLE tra.DimAnimales;
IF OBJECT_ID('tra.DimProducto') IS NOT NULL DROP TABLE tra.DimProducto;
IF OBJECT_ID('tra.DimFechaServicio') IS NOT NULL DROP TABLE tra.DimFechaServicio;
GO

-- DIMENSIONES

CREATE TABLE tra.DimFechaServicio (
    FechaServicio DATE NOT NULL PRIMARY KEY, diaSemana TINYINT, mes TINYINT, trimestre TINYINT, anno INT          
);
GO

CREATE TABLE tra.DimProducto (
    idProducto INT NOT NULL PRIMARY KEY, NombreProducto NVARCHAR(50), TipoProducto NVARCHAR(100), Empresa NVARCHAR(100), TipoProveedor NVARCHAR(100)              
);
GO

CREATE TABLE tra.DimAnimales (
    idAnimal INT NOT NULL PRIMARY KEY, NombreAnimal VARCHAR(50), RangoEdad VARCHAR(45), NombreRaza VARCHAR(25), NombreEspecie VARCHAR(25), 
    nombreCliente VARCHAR(60), distrito VARCHAR(50), canton VARCHAR(50), provincia VARCHAR(50)
);
GO

CREATE TABLE tra.DimMedico (
    idMedico INT NOT NULL PRIMARY KEY, NombreMedico VARCHAR(60)
);
GO

CREATE TABLE tra.DimTratamientos (
    idTratamiento INT NOT NULL PRIMARY KEY, TipoDiagnostico VARCHAR(50)
);
GO

-- CARGA DE DIMENSIONES

-- Carga de DimFechaServicio, DimProducto
INSERT INTO tra.DimFechaServicio (FechaServicio, diaSemana, mes, trimestre, anno)
SELECT DISTINCT CAST(F.fecha AS DATE), DATEPART(WEEKDAY, F.fecha), MONTH(F.fecha), DATEPART(QUARTER, F.fecha), YEAR(F.fecha)                   
FROM Veterinaria_Proyecto.dbo.Factura AS F WHERE F.fecha IS NOT NULL;
GO

INSERT INTO tra.DimProducto (idProducto, NombreProducto, TipoProducto, Empresa, TipoProveedor)
SELECT p.ID_Producto, p.Nombre, tp.Descripcion, prv.Nombre_Empresa, tprv.Descripcion     
FROM Veterinaria_Proyecto.dbo.Productos p
    LEFT JOIN Veterinaria_Proyecto.dbo.Tipo_Productos tp ON p.ID_Tipo_Producto = tp.ID_Tipo_Producto
    LEFT JOIN Veterinaria_Proyecto.dbo.Productos_Proveedor pp ON p.ID_Producto = pp.ID_Producto
    LEFT JOIN Veterinaria_Proyecto.dbo.Proveedores prv ON pp.ID_Proveedor = prv.ID_Proveedor
    LEFT JOIN Veterinaria_Proyecto.dbo.Tipos_Proveedor tprv ON prv.ID_Tipo_Proveedor = tprv.ID_Tipo_Proveedor;
GO

-- CARGA de DimAnimales (Algunos tienen registro 0)
INSERT INTO tra.DimAnimales (idAnimal, NombreAnimal, RangoEdad, NombreRaza, NombreEspecie, nombreCliente, distrito, canton, provincia)
VALUES (0, 'Desconocido', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A');

INSERT INTO tra.DimAnimales (idAnimal, NombreAnimal, RangoEdad, NombreRaza, NombreEspecie, nombreCliente, distrito, canton, provincia)
SELECT
    A.ID_Animal, A.Nombre,
    CASE WHEN A.Edad IS NULL THEN 'Desconocido' WHEN A.Edad <= 1 THEN 'Cachorro/Cría' WHEN A.Edad <= 7 THEN 'Adulto' ELSE 'Senior' END, 
    R.Nombre, E.Nombre, C.Nombre + ' ' + C.Apellido1, D.nombreDistrito, CA.nombreCanton, P.nombreProvincia
FROM Veterinaria_Proyecto.dbo.Animales AS A
    INNER JOIN Veterinaria_Proyecto.dbo.Razas AS R ON A.ID_Raza = R.ID_Raza
    INNER JOIN Veterinaria_Proyecto.dbo.Especies AS E ON R.especies_ID_Especie = E.ID_Especie
    INNER JOIN Veterinaria_Proyecto.dbo.Clientes AS C ON A.ID_Cliente = C.ID_Cliente
    INNER JOIN Veterinaria_Proyecto.dbo.Distrito AS D ON C.idDistrito = D.idDistrito
    INNER JOIN Veterinaria_Proyecto.dbo.Canton AS CA ON D.idCanton = CA.idCanton
    INNER JOIN Veterinaria_Proyecto.dbo.Provincia AS P ON CA.idProvincia = P.idProvincia
WHERE A.ID_Animal NOT IN (SELECT idAnimal FROM tra.DimAnimales); 
GO

-- CARGA de DimMedico (Algunos tienen registro 0)
INSERT INTO tra.DimMedico (idMedico, NombreMedico) VALUES (0, 'Desconocido');
INSERT INTO tra.DimMedico (idMedico, NombreMedico)
SELECT MV.ID_Medico, MV.Nombre + ' ' + MV.Apellido1
FROM Veterinaria_Proyecto.dbo.Medicos_Veterinarios AS MV
WHERE MV.ID_Medico NOT IN (SELECT idMedico FROM tra.DimMedico);
GO

-- CARGA de DimTratamientos
INSERT INTO tra.DimTratamientos (idTratamiento, TipoDiagnostico)
SELECT T.ID_Tratamiento, TD.Descripcion
FROM Veterinaria_Proyecto.dbo.Tratamientos AS T
    INNER JOIN Veterinaria_Proyecto.dbo.Diagnosticos AS D ON T.ID_Diagnostico = D.ID_Diagnostico
    INNER JOIN Veterinaria_Proyecto.dbo.Tipos_de_Diagnostico AS TD ON D.ID_Tipo_Diagnostico = TD.ID_Tipo_Diagnostico;
GO

-- Servicios_H

-- Crear la tabla de Hechos y sus FK e identidades
CREATE TABLE tra.Servicios_H (
    idServicios_H INT IDENTITY(1,1) NOT NULL PRIMARY KEY, 
    cantidad DECIMAL(10, 2) NOT NULL, monto DECIMAL(10, 2) NOT NULL, tipoServicio VARCHAR(1) NOT NULL,
    DimAnimales_idAnimal INT NOT NULL, DimTratamientos_idTratamiento INT NULL, DimProducto_idProducto INT NOT NULL, DimMedico_idMedico INT NOT NULL, DimFechaServicio_FechaServicio DATE NOT NULL,
    CONSTRAINT FK_H_Animales FOREIGN KEY (DimAnimales_idAnimal) REFERENCES tra.DimAnimales(idAnimal),
    CONSTRAINT FK_H_Tratamientos FOREIGN KEY (DimTratamientos_idTratamiento) REFERENCES tra.DimTratamientos(idTratamiento),
    CONSTRAINT FK_H_Producto FOREIGN KEY (DimProducto_idProducto) REFERENCES tra.DimProducto(idProducto),
    CONSTRAINT FK_H_Medico FOREIGN KEY (DimMedico_idMedico) REFERENCES tra.DimMedico(idMedico),
    CONSTRAINT FK_H_FechaServicio FOREIGN KEY (DimFechaServicio_FechaServicio) REFERENCES tra.DimFechaServicio(FechaServicio)
);
GO

-- Insertar datos en Hechos
INSERT INTO tra.Servicios_H (
    cantidad, monto, tipoServicio, DimAnimales_idAnimal, 
    DimTratamientos_idTratamiento, DimProducto_idProducto, DimMedico_idMedico, 
    DimFechaServicio_FechaServicio
)
SELECT
    DF.Cantidad,
    (DF.Cantidad * P.Precio_Unitario),
    'P',
    ISNULL(DimA.idAnimal, 0) AS DimAnimales_idAnimal, --se le pone 0 si el idanimal no matchea con la factura
    DF.tratamientos_ID_Tratamiento,
    DF.productos_ID_Producto,
    ISNULL(C.ID_Medico, 0),
    CAST(F.fecha AS DATE)
FROM Veterinaria_Proyecto.dbo.DetalleFactura AS DF
    INNER JOIN Veterinaria_Proyecto.dbo.Factura AS F ON DF.Factura_idFactura = F.idFactura
    INNER JOIN Veterinaria_Proyecto.dbo.Productos AS P ON DF.productos_ID_Producto = P.ID_Producto
    LEFT JOIN Veterinaria_Proyecto.dbo.Citas AS C ON DF.citas_ID_Cita = C.ID_Cita
    LEFT JOIN tra.DimAnimales AS DimA ON F.animales_ID_Animal = DimA.idAnimal
WHERE F.fecha IS NOT NULL;
GO


--Revision de dimensiones

SELECT TOP (1000) 
    [FechaServicio]
    ,[diaSemana]
    ,[mes]
    ,[trimestre]
    ,[anno]
FROM [tra].[DimFechaServicio];
GO

SELECT TOP (1000) 
    [idProducto]
    ,[NombreProducto]
    ,[TipoProducto]
    ,[Empresa]
    ,[TipoProveedor]
FROM [tra].[DimProducto];
GO

SELECT TOP (1000) 
    [idAnimal]
    ,[NombreAnimal]
    ,[RangoEdad]
    ,[NombreRaza]
    ,[NombreEspecie]
    ,[nombreCliente]
    ,[provincia]
    ,[canton]
    ,[distrito]
FROM [tra].[DimAnimales];
GO

SELECT TOP (1000) 
    [idMedico]
    ,[NombreMedico]
FROM [tra].[DimMedico];
GO

SELECT TOP (1000) 
    [idTratamiento]
    ,[TipoDiagnostico]
FROM [tra].[DimTratamientos];
GO

SELECT TOP (1000) 
    [idServicios_H]
    ,[cantidad]
    ,[monto]
    ,[tipoServicio]
    ,[DimAnimales_idAnimal]
    ,[DimMedico_idMedico]
    ,[DimFechaServicio_FechaServicio]
FROM [tra].[Servicios_H]
ORDER BY idServicios_H DESC;
GO
