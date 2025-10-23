BULK INSERT Veterinaria_Proyecto.dbo.Diagnosticos
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Diagnosticos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);