--Reiniciar IDs
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Animales', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Citas', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Clientes', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.DetalleFactura', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Diagnosticos', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Especies', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Factura', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Medicamentos', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Medicos_Veterinarios', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Productos', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Proveedores', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Razas', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Tipo_Productos', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Tipos_de_Diagnostico', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Tipos_Proveedor', RESEED, 0);
DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Tratamientos', RESEED, 0);



DBCC CHECKIDENT ('Veterinaria_Proyecto.dbo.Distrito', RESEED, 0);