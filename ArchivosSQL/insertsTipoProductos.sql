BULK INSERT Veterinaria_Proyecto.dbo.Tipo_Productos
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\TipoProductos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);