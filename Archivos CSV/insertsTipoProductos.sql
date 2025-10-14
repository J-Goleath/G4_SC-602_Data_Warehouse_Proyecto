BULK INSERT Veterinaria_Proyecto.dbo.Tipo_Productos
FROM 'D:\Documentos\DataWareHouse\G4_SC-602_Data_Warehouse_Proyecto\Archivos CSV\TipoProductos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n',
    BATCHSIZE = 100,
    TABLOCK);