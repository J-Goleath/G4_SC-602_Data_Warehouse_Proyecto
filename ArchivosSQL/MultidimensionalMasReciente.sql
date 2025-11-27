--------------------------------------------------------------------
-- Creacion 1 (dimension de fechas)
-- Analizar los servicios por día, mes, trimestre y año.
CREATE TABLE tra.DimFechaServicio (
    FechaServicio DATE PRIMARY KEY, 
    diaSemana     TINYINT,          
    mes           TINYINT,           
    trimestre     TINYINT,           
    anno          INT               
);
GO

-- LlenaR dimensiOn de fechas usando Factura dbo

INSERT INTO tra.DimFechaServicio (
    FechaServicio,
    diaSemana,
    mes,
    trimestre,
    anno
)
SELECT DISTINCT
    CAST(F.fecha AS DATE)              AS FechaServicio,         
    DATEPART(WEEKDAY, F.fecha)         AS diaSemana,             
    MONTH(F.fecha)                     AS mes,                   
    DATEPART(QUARTER, F.fecha)         AS trimestre,             
    YEAR(F.fecha)                      AS anno                   
FROM dbo.Factura AS F;
GO

-- query validacion factura fechas
  SELECT 
    COUNT(DISTINCT CAST(fecha AS DATE)) AS FechasTransaccional
FROM dbo.Factura;

SELECT 
    COUNT(*) AS FechasDim
FROM tra.DimFechaServicio;
USE Veterinaria_ProyectoSA;
GO


-- DimProducto
-- Incluye Tipo productos, Provedores, tipo provedor

CREATE TABLE tra.DimProducto (
    idProducto     INT          PRIMARY KEY,   
    NombreProducto NVARCHAR(50),              
    TipoProducto   NVARCHAR(100),             
    Empresa        NVARCHAR(100),             
    TipoProveedor  NVARCHAR(100)              
);
GO

-- CARGA DE LA DIMENSIÓN: tra.DimProducto
-- Fuentes: Producto, Tipo_Productos, Productos_Proveedor, Proveedores, Tipos_Proveedor
--   Un proveedor tiene varios productos pero cada producto solo debe tener un provedor

INSERT INTO tra.DimProducto (
    idProducto,
    NombreProducto,
    TipoProducto,
    Empresa,
    TipoProveedor
)
SELECT
    p.ID_Producto  AS idProducto,       
    p.Nombre AS NombreProducto,   
    tp.Descripcion AS TipoProducto,     
    prv.Nombre_Empresa AS Empresa,          
    tprv.Descripcion AS TipoProveedor     
FROM dbo.Productos p

    -- Tipo de producto
    LEFT JOIN dbo.Tipo_Productos tp
        ON p.ID_Tipo_Producto = tp.ID_Tipo_Producto
    
    LEFT JOIN dbo.Productos_Proveedor pp
        ON p.ID_Producto = pp.ID_Producto

    -- Datos del proveedor
    LEFT JOIN dbo.Proveedores prv
        ON pp.ID_Proveedor = prv.ID_Proveedor

    -- Tipo de proveedor
    LEFT JOIN dbo.Tipos_Proveedor tprv
        ON prv.ID_Tipo_Proveedor = tprv.ID_Tipo_Proveedor;
GO

-- validaciones productos
SELECT COUNT(*) AS ProductosTransaccional
FROM dbo.Productos;

SELECT COUNT(*) AS ProductosDim
FROM tra.DimProducto;

----------------------------------------------------------------------------
-- Creacion dimension Tratamiento
-- Tablas usadas Trataientos Y tipo diagnostico

CREATE TABLE tra.DimTratamientos (
    idTratamiento  INT PRIMARY KEY,   
    TipoDiagnostico NVARCHAR(200)              
);
GO

-- CARGA DE DimTratamientos

INSERT INTO tra.DimTratamientos (
    idTratamiento,
    TipoDiagnostico
)
SELECT
    t.ID_Tratamiento                    AS idTratamiento,     
    td.Descripcion                      AS TipoDiagnostico    
FROM dbo.tratamientos t
    LEFT JOIN dbo.diagnosticos d
        ON t.ID_Diagnostico = d.ID_Diagnostico

    -- Traer tipo diagnostico
    LEFT JOIN dbo.tipos_de_diagnostico td
        ON d.ID_Tipo_Diagnostico = td.ID_Tipo_Diagnostico;
GO

-- Validaicion Tratatmientos
SELECT COUNT(*) AS TratamientosTransaccional
FROM dbo.tratamientos;


SELECT COUNT(*) AS TratamientosDim
FROM tra.DimTratamientos;


USE Veterinaria_ProyectoSA;
GO

-------------------------------------------------------------------
-- Creacion dimension Medicos
--Tablas solo medicos y se crea nombre medico que es la union de nombre + apellidos
-- 
CREATE TABLE tra.DimMedico (
    idMedico  INT  PRIMARY KEY,   
    NombreMedico  NVARCHAR(80)  NOT NULL      
);
GO

-----------------------------------------------------------------------------------
-- CARGA de DimMedico

INSERT INTO tra.DimMedico (
    idMedico,
    NombreMedico
)
SELECT
    mv.ID_Medico AS idMedico,                       
    CONCAT(mv.Nombre, ' ', mv.Apellido1, ' ', mv.Apellido2) AS NombreMedico
FROM dbo.Medicos_Veterinarios AS mv;
GO

SELECT COUNT(*) AS MedicosTransaccional
FROM dbo.Medicos_Veterinarios;

SELECT COUNT(*) AS MedicosDim
FROM tra.DimMedico;


USE Veterinaria_ProyectoSA;
GO

-------------------------------------------------------------------------------
-- Creacion dimension Animales
CREATE TABLE tra.DimAnimales (
    idAnimal       INT  PRIMARY KEY,   
    NombreAnimal   NVARCHAR(50),              
    RangoEdad      NVARCHAR(45),             
    NombreRaza     NVARCHAR(50),               
    NombreEspecie  NVARCHAR(50),               
    nombreCliente  NVARCHAR(100),             
    distrito       NVARCHAR(50),              
    canton         NVARCHAR(50),              
    provincia      NVARCHAR(50)             
);
GO

-- ============================================================
-- CARGA DE Animales
--Incluye informacion de las siguientes tablas: Animales, Razas, Especies, Clientes, Distrito, Canton, Provincia

INSERT INTO tra.DimAnimales (
    idAnimal,
    NombreAnimal,
    RangoEdad,
    NombreRaza,
    NombreEspecie,
    nombreCliente,
    distrito,
    canton,
    provincia
)
SELECT
    a.ID_Animal AS idAnimal,
    a.Nombre AS NombreAnimal,

    -- clasificicaion por rangos de edades
    CASE 
        WHEN a.Edad IS NULL THEN NULL
        WHEN a.Edad BETWEEN 0 AND 1 THEN 'Cachorro'
        WHEN a.Edad BETWEEN 2 AND 6 THEN 'Adulto'
        WHEN a.Edad >= 7 THEN 'Senior'
    END   AS RangoEdad,

    rz.Nombre  AS NombreRaza,
    es.Nombre  AS NombreEspecie,

    -- logica para agregar full name del dueno del animalito
    CONCAT(cl.Nombre, ' ', cl.Apellido1,
           CASE 
               WHEN cl.Apellido2 IS NOT NULL AND cl.Apellido2 <> '' 
                    THEN CONCAT(' ', cl.Apellido2)
               ELSE ''
           END)AS nombreCliente,

    dst.nombreDistrito AS distrito,
    cnt.nombreCanton AS canton,
    prv.nombreProvincia AS provincia

FROM dbo.Animales a
    -- Raza y especie
    LEFT JOIN dbo.Razas rz
        ON a.ID_Raza = rz.ID_Raza
    LEFT JOIN dbo.Especies es
        ON rz.especies_ID_Especie = es.ID_Especie

    -- id del cliente
    LEFT JOIN dbo.Clientes cl
        ON a.ID_Cliente = cl.ID_Cliente

    -- de donde es el dueno del animalito???
    LEFT JOIN dbo.Distrito  dst
        ON cl.idDistrito = dst.idDistrito
    LEFT JOIN dbo.Canton    cnt
        ON dst.idCanton = cnt.idCanton
    LEFT JOIN dbo.Provincia prv
        ON cnt.idProvincia = prv.idProvincia;
GO

-- Validacion de la cantidad de datos
SELECT COUNT(*) AS AnimalesTransaccional
FROM dbo.Animales;


SELECT COUNT(*) AS AnimalesDim
FROM tra.DimAnimales;


USE Veterinaria_ProyectoSA;
GO

--------------------------------------------------------------
-- Ceacion TABLA hechos
CREATE TABLE tra.Servicios_H (
    idServicios_H INT IDENTITY(1,1) PRIMARY KEY,          
    cantidad      INT           NOT NULL,                 
    monto         DECIMAL(10,2) NOT NULL,                 
    tipoServicio  CHAR(1)       NOT NULL,                 

    idAnimal  INT  NOT NULL
        REFERENCES tra.DimAnimales(idAnimal),

    idTratamiento INT NULL
        REFERENCES tra.DimTratamientos(idTratamiento),

    idProducto INT  NOT NULL
        REFERENCES tra.DimProducto(idProducto),
    idMedico INT  NULL                    
        REFERENCES tra.DimMedico(idMedico),
    FechaServicio DATE NOT NULL
        REFERENCES tra.DimFechaServicio(FechaServicio)
);
GO

-- ============================================================
-- CARGA tabla HECHOS con las dimensiones ya creadaas ( No esta funcional hay que corregir datos)

INSERT INTO tra.Servicios_H (
    cantidad,
    monto,
    tipoServicio,
    idAnimal,
    idTratamiento,
    idProducto,
    idMedico,
    FechaServicio
)
SELECT
    df.Cantidad AS cantidad,
    CAST(df.Cantidad AS DECIMAL(10,2)) AS monto,

    -- tipo servicio clasificacion
    CASE 
        WHEN df.citas_ID_Cita IS NOT NULL THEN 'C'   -- tieneuna cita asociada
        ELSE 'P'                                     -- si no hay cita y es solo producto
    END AS tipoServicio,

    f.animales_ID_Animal AS idAnimal,         
    df.tratamientos_ID_Tratamiento AS idTratamiento,     
    df.productos_ID_Producto  AS idProducto,        

    c.ID_Medico AS idMedico,          
    CAST(f.fecha AS DATE)  AS FechaServicio      

FROM dbo.DetalleFactura df
    INNER JOIN dbo.Factura f
        ON df.Factura_idFactura = f.idFactura
    LEFT JOIN dbo.Citas c
        ON df.citas_ID_Cita = c.ID_Cita;
GO

--------------------------------------
--Validaciones
SELECT COUNT(*) AS Lineas_DetalleFactura
FROM Veterinaria_Proyecto.dbo.DetalleFactura;

-- Total de líneas en la tabla de hechos
SELECT COUNT(*) AS Lineas_Servicios_H
FROM Veterinaria_ProyectoSA.tra.Servicios_H;
