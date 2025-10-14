package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarProductos {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\Productos.csv";

        int totalProductos = 250;
        int totalMedicamentos = 320;
        int totalTipos = 5;

        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalProductos; i++) {
                int idMedicamento = rand.nextInt(totalMedicamentos) + 1;
                int idTipoProducto = rand.nextInt(totalTipos) + 1;
                String nombre = "Producto_" + i;

                // Precio entre 2000 y 50000 colones
                int precioUnitario = 2000 + rand.nextInt(48001);

                // Stock entre 10 y 500
                int stock = 10 + rand.nextInt(491);

                writer.append(i + "," + idMedicamento + "," + idTipoProducto + "," + nombre + "," + precioUnitario + "," + stock + "\n");
            }

            System.out.println("Archivo Productos.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + totalProductos);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
