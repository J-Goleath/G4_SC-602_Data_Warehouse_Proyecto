BULK INSERT Veterinaria_Proyecto.dbo.Clientes
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Clientes.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);