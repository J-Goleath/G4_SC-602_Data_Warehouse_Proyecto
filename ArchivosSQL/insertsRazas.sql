BULK INSERT Veterinaria_Proyecto.dbo.Razas
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Razas.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);