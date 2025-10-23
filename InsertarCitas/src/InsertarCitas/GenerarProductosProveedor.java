package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class GenerarProductosProveedor {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "ProductosProveedor.csv";

        int totalProductos = 250;
        int totalProveedores = 150;

        // Distribucion de productos por tipo
        int medicamentosInicio = 1;   // Productos 1 - 120
        int alimentosInicio = 121;    // Productos 121 - 180
        int accesoriosInicio = 181;   // Productos 181 - 225
        int higieneInicio = 226;      // Productos 226 - 245
        int juguetesInicio = 246;     // Productos 246 - 250

        int proveedorActual = 1;
        int productoID = 1;

        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            // === Medicamentos ===
            // 15 proveedores con 2 productos
            for (int p = 0; p < 15; p++) {
                for (int j = 0; j < 2; j++) {
                    writer.append(productoID + "," + proveedorActual + "\n");
                    productoID++;
                }
                proveedorActual++;
            }
            // 90 proveedores con 1 producto
            for (int p = 0; p < 90; p++) {
                writer.append(productoID + "," + proveedorActual + "\n");
                productoID++;
                proveedorActual++;
            }

            // === Alimentos ===
            // 15 proveedores con 4 productos cada uno
            for (int p = 0; p < 15; p++) {
                for (int j = 0; j < 4; j++) {
                    writer.append(productoID + "," + proveedorActual + "\n");
                    productoID++;
                }
                proveedorActual++;
            }

            // === Accesorios ===
            // 15 proveedores con 3 productos
            for (int p = 0; p < 15; p++) {
                for (int j = 0; j < 3; j++) {
                    writer.append(productoID + "," + proveedorActual + "\n");
                    productoID++;
                }
                proveedorActual++;
            }

            // === Higiene ===
            // 10 proveedores con 2 productos
            for (int p = 0; p < 10; p++) {
                for (int j = 0; j < 2; j++) {
                    writer.append(productoID + "," + proveedorActual + "\n");
                    productoID++;
                }
                proveedorActual++;
            }

            // === Juguetes ===
            // 5 proveedores con 1 producto
            for (int p = 0; p < 5; p++) {
                writer.append(productoID + "," + proveedorActual + "\n");
                productoID++;
                proveedorActual++;
            }

            System.out.println("Archivo Productos_Proveedor.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + (productoID - 1));

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
