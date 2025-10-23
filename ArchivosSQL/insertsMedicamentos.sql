BULK INSERT Veterinaria_Proyecto.dbo.Medicamentos
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Medicamentos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);