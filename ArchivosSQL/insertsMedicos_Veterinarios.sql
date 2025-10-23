BULK INSERT Veterinaria_Proyecto.dbo.Medicos_Veterinarios
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Medicos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);