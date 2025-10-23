BULK INSERT Veterinaria_Proyecto.dbo.Proveedores
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Proveedores.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);