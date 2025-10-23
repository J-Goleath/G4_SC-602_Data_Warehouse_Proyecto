package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarDetalleFactura {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "DetalleFactura.csv";

        int totalLineas = 4932288;   // Total de lineas de DetalleFactura
        int totalCitas = 2304000;    // Total de citas
        int totalProductos = 250;    // Productos disponibles
        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalLineas; i++) {
                int productoId = rand.nextInt(totalProductos) + 1;
                int cantidad = rand.nextInt(3) + 1;
                int citaId = rand.nextInt(totalCitas) + 1;

                // Como hay una línea por tratamiento, podemos asumir que tratamientos_ID_Tratamiento = i
                int tratamientoId = i;

                writer.append(i + "," +
                              productoId + "," +
                              citaId + "," +
                              cantidad + "," +
                              citaId + "," +
                              tratamientoId + "\n");
            }

            System.out.println("Archivo DetalleFactura.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + totalLineas);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
