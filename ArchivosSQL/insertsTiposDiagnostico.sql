BULK INSERT Veterinaria_Proyecto.dbo.Tipos_de_Diagnostico
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\TipoDiagnostico.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);