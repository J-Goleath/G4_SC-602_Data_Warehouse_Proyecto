BULK INSERT Veterinaria_Proyecto.dbo.Animales
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Animales.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);