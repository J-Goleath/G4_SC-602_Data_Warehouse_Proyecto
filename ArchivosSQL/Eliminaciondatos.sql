EXEC sp_msforeachtable "ALTER TABLE ? NOCHECK CONSTRAINT ALL";

DELETE FROM Veterinaria_Proyecto.dbo.Productos_Proveedor;
DELETE FROM Veterinaria_Proyecto.dbo.Productos_Proveedor;
DELETE FROM Veterinaria_Proyecto.dbo.DetalleFactura;
DELETE FROM Veterinaria_Proyecto.dbo.Factura;
DELETE FROM Veterinaria_Proyecto.dbo.Tratamientos;
DELETE FROM Veterinaria_Proyecto.dbo.Diagnosticos;
DELETE FROM Veterinaria_Proyecto.dbo.Citas;

DELETE FROM Veterinaria_Proyecto.dbo.Productos;
DELETE FROM Veterinaria_Proyecto.dbo.Proveedores;
DELETE FROM Veterinaria_Proyecto.dbo.Medicos_Veterinarios;
DELETE FROM Veterinaria_Proyecto.dbo.Clientes;
DELETE FROM Veterinaria_Proyecto.dbo.Animales;

DELETE FROM Veterinaria_Proyecto.dbo.Tipos_de_Diagnostico;
DELETE FROM Veterinaria_Proyecto.dbo.Razas;
DELETE FROM Veterinaria_Proyecto.dbo.Especies;

DELETE FROM Veterinaria_Proyecto.dbo.Tipo_Productos;
DELETE FROM Veterinaria_Proyecto.dbo.Tipos_Proveedor;
DELETE FROM Veterinaria_Proyecto.dbo.Medicamentos;

EXEC sp_msforeachtable "ALTER TABLE ? CHECK CONSTRAINT ALL";
