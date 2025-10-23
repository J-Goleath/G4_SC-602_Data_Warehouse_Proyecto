BULK INSERT Veterinaria_Proyecto.dbo.Citas
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Citas.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);