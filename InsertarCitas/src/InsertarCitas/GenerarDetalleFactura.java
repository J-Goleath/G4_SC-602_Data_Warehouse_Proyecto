package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenerarDetalleFactura {

    public static void generar() {

        String rutaArchivo = Configuracion.rutaBase + "DetalleFactura.csv";

        final int TOTAL_LINEAS = 4_932_288;       // Total registros DetalleFactura
        final int TOTAL_CITAS = 2_304_000;        // Citas generadas
        final int TOTAL_TRATAMIENTOS = 2_628_288; // Tratamientos generados
        final int TOTAL_PRODUCTOS = 250;
        final int TOTAL_FACTURAS = 2_304_000;

        final double PORCENTAJE_CON_CITA = 0.65;

        int lineasConCita = (int) (TOTAL_LINEAS * PORCENTAJE_CON_CITA);
        int lineasSinCita = TOTAL_LINEAS - lineasConCita;

        // NUEVA LÓGICA: división interna del 65%
        int lineasConCitaConProducto = (int) (lineasConCita * 0.45);
        int lineasConCitaSinProducto = lineasConCita - lineasConCitaConProducto;

        Random rand = new Random();

        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            ArrayList<String> todasLasLineas = new ArrayList<>(TOTAL_LINEAS);
            int idDetalle = 1;

            // 65% — Registros CON cita/tratamiento Y con producto (45% del 65%)
            
            for (int i = 0; i < lineasConCitaConProducto; i++) {

                int productoId = rand.nextInt(TOTAL_PRODUCTOS) + 1;
                int facturaId = rand.nextInt(TOTAL_FACTURAS) + 1;
                int cantidad = rand.nextInt(3) + 1;

                int citaId = rand.nextInt(TOTAL_CITAS) + 1;
                int tratamientoId = rand.nextInt(TOTAL_TRATAMIENTOS) + 1;

                String linea = idDetalle + ","
                        + productoId + ","
                        + facturaId + ","
                        + cantidad + ","
                        + citaId + ","
                        + tratamientoId;

                todasLasLineas.add(linea);
                idDetalle++;

                if (i % 500000 == 0 && i > 0) {
                    System.out.println("Generados CON cita (CON producto): " + i);
                }
            }

            // 65% — Registros CON cita/tratamiento PERO SIN producto (55% del 65%)
            
            for (int i = 0; i < lineasConCitaSinProducto; i++) {

                int facturaId = rand.nextInt(TOTAL_FACTURAS) + 1;
                int cantidad = rand.nextInt(3) + 1;

                int citaId = rand.nextInt(TOTAL_CITAS) + 1;
                int tratamientoId = rand.nextInt(TOTAL_TRATAMIENTOS) + 1;

                // NOTA: doble coma al inicio donde va el producto
                String linea = idDetalle + ",,"
                        + facturaId + ","
                        + cantidad + ","
                        + citaId + ","
                        + tratamientoId;

                todasLasLineas.add(linea);
                idDetalle++;

                if (i % 500000 == 0 && i > 0) {
                    System.out.println("Generados CON cita (SIN producto): " + i);
                }
            }

            for (int i = 0; i < lineasSinCita; i++) {

                int productoId = rand.nextInt(TOTAL_PRODUCTOS) + 1;
                int facturaId = rand.nextInt(TOTAL_FACTURAS) + 1;
                int cantidad = rand.nextInt(3) + 1;

                String linea = idDetalle + ","
                        + productoId + ","
                        + facturaId + ","
                        + cantidad + ",,";

                todasLasLineas.add(linea);
                idDetalle++;

                if (i % 500000 == 0 && i > 0) {
                    System.out.println("Generados SIN cita: " + i);
                }
            }

            Collections.shuffle(todasLasLineas);

            for (String linea : todasLasLineas) {
                writer.append(linea).append("\n");
            }

            System.out.println("\nArchivo DetalleFactura.csv generado correctamente.");
            System.out.println("Total líneas generadas: " + TOTAL_LINEAS);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total: " + ((fin - inicio) / 1000.0) + " segundos");
    }
}
