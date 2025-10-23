BULK INSERT Veterinaria_Proyecto.dbo.Tipos_Proveedor
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\TipoProveedores.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);