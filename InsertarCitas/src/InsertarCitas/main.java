package InsertarCitas;

public class main {
    public static void main(String[] args) {

        // Generar archivo de medicos
        GenerarMedicos.generar();

        // Generar archivo de diagnosticos
        GenerarDiagnosticos.generar();
        
        // Generar archivo de Tratamientos
        GenerarTratamientos.generar();
        
        // Generar archivo de Cliente
        GenerarClientes.generar();
        
        // Generar archivo de Animales
        GenerarAnimales.generar();
        
        // Generar archivo de Especies
        GenerarEspecies.generar();
        
        // Generar archivo de Razas
        GenerarRazas.generar();
        
        // Generar archivo de Medicamentos
        GenerarMedicamentos.generar();
        
        // Generar archivo de DetalleFacturas
        GenerarDetalleFactura.generar();
        
        // Generar archivo de Facturas
        GenerarFacturas.generar();
        
        // Generar archivo de Productos
        GenerarProductos.generar();
        
        // Generar archivo de Proveedores
        GenerarProveedores.generar();
        
        // Generar archivo de ProductosProveedor
        GenerarProductosProveedor.generar();
        
        // Generar archivo de Citas
        GenerarCitas.generar();
        
        // Generar archivo de TipoDiagnostico
        GenerarTipoDiagnostico.generar();
        
        // Generar archivo de TipoDiagnostico
        GenerarTipoProductos.generar();
        
        // Generar archivo de TipoDiagnostico
        GenerarTipoProveedores.generar();
    }
}