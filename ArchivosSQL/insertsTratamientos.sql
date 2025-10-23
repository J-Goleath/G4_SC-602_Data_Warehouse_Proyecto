BULK INSERT Veterinaria_Proyecto.dbo.Tratamientos
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Tratamientos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);