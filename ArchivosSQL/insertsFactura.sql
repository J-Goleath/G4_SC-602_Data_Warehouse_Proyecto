BULK INSERT Veterinaria_Proyecto.dbo.Factura
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\Facturas.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);