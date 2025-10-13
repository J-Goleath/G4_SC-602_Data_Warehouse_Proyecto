BULK INSERT Veterinaria_Proyecto.dbo.Diagnosticos
FROM 'D:\Documentos\DataWareHouse\G4_SC-602_Data_Warehouse_Proyecto\Archivos CSV\Diagnosticos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n',
    BATCHSIZE = 100,
    TABLOCK);