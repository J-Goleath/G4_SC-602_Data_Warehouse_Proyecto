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
        final int TOTAL_FACTURAS = 1_500_000;     // <-- Ajustar si tu sistema usa otro valor

        final double PORCENTAJE_CON_CITA = 0.65;

        int lineasConCita = (int) (TOTAL_LINEAS * PORCENTAJE_CON_CITA);
        int lineasSinCita = TOTAL_LINEAS - lineasConCita;

        Random rand = new Random();

        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            ArrayList<String> todasLasLineas = new ArrayList<>(TOTAL_LINEAS);
            int idDetalle = 1;

            // ============================================================
            // 65% — Registros CON cita y CON tratamiento
            // ============================================================
            for (int i = 0; i < lineasConCita; i++) {

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
                    System.out.println("Generados CON cita: " + i);
                }
            }

            // ============================================================
            // 35% — Registros SIN cita y SIN tratamiento
            // ============================================================
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

            // ============================================================
            // MEZCLA ALEATORIA DE TODAS LAS LÍNEAS
            // ============================================================
            Collections.shuffle(todasLasLineas);

            // ============================================================
            // ESCRIBIR TODAS LAS LÍNEAS AL ARCHIVO
            // ============================================================
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
