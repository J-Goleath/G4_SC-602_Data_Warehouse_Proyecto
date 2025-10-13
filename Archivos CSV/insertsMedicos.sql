BULK INSERT Veterinaria_Proyecto.dbo.Medicos_Veterinarios
FROM 'D:\Documentos\DataWareHouse\G4_SC-602_Data_Warehouse_Proyecto\Archivos CSV\Medicos.csv'
WITH (
    FIELDTERMINATOR = ',',
    ROWTERMINATOR = '\n',
    BATCHSIZE = 100,
    TABLOCK);