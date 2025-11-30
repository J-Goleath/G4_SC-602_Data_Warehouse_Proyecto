select * into Veterinaria_ProyectoDW.dbo.Animales
from VeterinariaSA.tra.DimAnimales

alter table Veterinaria_ProyectoDW.dbo.Animales add primary key (idAnimal)

select * into Veterinaria_ProyectoDW.dbo.FechaServicio
from VeterinariaSA.tra.DimFechaServicio

alter table Veterinaria_ProyectoDW.dbo.FechaServicio add primary key (FechaServicio)

select * into Veterinaria_ProyectoDW.dbo.Medico
from VeterinariaSA.tra.DimMedico

alter table Veterinaria_ProyectoDW.dbo.Medico add primary key (idMedico)

select * into Veterinaria_ProyectoDW.dbo.Producto
from VeterinariaSA.tra.DimProducto

alter table Veterinaria_ProyectoDW.dbo.Producto add primary key (idProducto)

select * into Veterinaria_ProyectoDW.dbo.Tratamientos
from VeterinariaSA.tra.DimTratamientos

alter table Veterinaria_ProyectoDW.dbo.Tratamientos add primary key (idTipoDiagnostico)

select * into Veterinaria_ProyectoDW.dbo.Servicios_H
from VeterinariaSA.tra.Servicios_H

alter table Veterinaria_ProyectoDW.dbo.Servicios_H add primary key (idServicios_H)
alter table Veterinaria_ProyectoDW.dbo.Servicios_H add foreign key (DimAnimales_idAnimal) references Veterinaria_ProyectoDW.dbo.Animales (idAnimal)
alter table Veterinaria_ProyectoDW.dbo.Servicios_H add foreign key (DimFechaServicio_FechaServicio) references Veterinaria_ProyectoDW.dbo.FechaServicio (FechaServicio)
alter table Veterinaria_ProyectoDW.dbo.Servicios_H add foreign key (DimMedico_idMedico) references Veterinaria_ProyectoDW.dbo.Medico (idMedico)
alter table Veterinaria_ProyectoDW.dbo.Servicios_H add foreign key (DimProducto_idProducto) references Veterinaria_ProyectoDW.dbo.Producto (idProducto)
alter table Veterinaria_ProyectoDW.dbo.Servicios_H add foreign key (DimTratamientos_idTratamiento) references Veterinaria_ProyectoDW.dbo.Tratamientos (idTipoDiagnostico)
