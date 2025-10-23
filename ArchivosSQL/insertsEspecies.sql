BULK INSERT Veterinaria_Proyecto.dbo.Especies
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Especies.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);