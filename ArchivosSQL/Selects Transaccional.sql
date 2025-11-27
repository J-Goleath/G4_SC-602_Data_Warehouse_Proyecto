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