BULK INSERT Veterinaria_Proyecto.dbo.DetalleFactura
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\DetalleFactura.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);