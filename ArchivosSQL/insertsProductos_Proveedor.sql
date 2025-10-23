BULK INSERT Veterinaria_Proyecto.dbo.Productos_Proveedor
FROM 'C:\\Users\\Kevin\\Documents\\ArchivosCSV\\ProductosProveedor.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '0x0A',
    CODEPAGE = '65001',
    TABLOCK,
    FIRSTROW = 1
);