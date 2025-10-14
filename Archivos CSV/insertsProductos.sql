BULK INSERT Veterinaria_Proyecto.dbo.Productos
FROM 'D:\Documentos\DataWareHouse\G4_SC-602_Data_Warehouse_Proyecto\Archivos CSV\Productos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n',
    BATCHSIZE = 100,
    TABLOCK);