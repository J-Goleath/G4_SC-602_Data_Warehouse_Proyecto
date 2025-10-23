BULK INSERT Veterinaria_Proyecto.dbo.Productos
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Productos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);