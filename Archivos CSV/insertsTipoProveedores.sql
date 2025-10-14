BULK INSERT Veterinaria_Proyecto.dbo.Tipos_Proveedor
FROM 'D:\Documentos\DataWareHouse\G4_SC-602_Data_Warehouse_Proyecto\Archivos CSV\TipoProveedores.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n',
    BATCHSIZE = 100,
    TABLOCK);