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
--   Un proveedor tiene varios productos,
--   Cada producto solo un provedor

INSERT INTO tra.DimProducto (
    idProducto,
    NombreProducto,
    TipoProducto,
    Empresa,
    TipoProveedor
)
SELECT
    p.ID_Producto                      AS idProducto,       
    p.Nombre                           AS NombreProducto,   
    tp.Descripcion                     AS TipoProducto,     
    prv.Nombre_Empresa                 AS Empresa,          
    tprv.Descripcion                   AS TipoProveedor     
FROM dbo.Productos p

    -- Tipo de producto
    LEFT JOIN dbo.Tipo_Productos tp
        ON p.ID_Tipo_Producto = tp.ID_Tipo_Producto
    -
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