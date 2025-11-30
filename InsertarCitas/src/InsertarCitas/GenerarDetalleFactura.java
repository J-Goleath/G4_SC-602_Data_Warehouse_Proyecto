package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenerarDetalleFactura {

    public static void generar() {

        String rutaArchivo = Configuracion.rutaBase + "DetalleFactura.csv";

        final int TOTAL_LINEAS = 4_932_288;
        final int TOTAL_CITAS = 2_304_000;
        final int TOTAL_TRATAMIENTOS = 2_304_000;
        final int TOTAL_PRODUCTOS = 250;
        final int TOTAL_FACTURAS = 2_304_000;

        // Cantidades EXACTAS
        final int TOTAL_CON_CITA = TOTAL_CITAS;                          
        final int TOTAL_CON_CITA_CON_PRODUCTO = 1_497_600;               
        final int TOTAL_CON_CITA_SIN_PRODUCTO = 806_400;                 
        final int TOTAL_SIN_CITA = TOTAL_LINEAS - TOTAL_CON_CITA;        

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            ArrayList<String> todasLasLineas = new ArrayList<>(TOTAL_LINEAS);
            int idDetalle = 1;

            // ============================================================
            // LISTA DE TRATAMIENTOS (1:1 con citas)
            // ============================================================
            ArrayList<Integer> tratamientosBarajados = new ArrayList<>(TOTAL_TRATAMIENTOS);
            for (int i = 1; i <= TOTAL_TRATAMIENTOS; i++) {
                tratamientosBarajados.add(i);
            }
            Collections.shuffle(tratamientosBarajados);

            int[] tratamientoPorCita = new int[TOTAL_CITAS + 1];
            for (int i = 1; i <= TOTAL_CITAS; i++) {
                tratamientoPorCita[i] = tratamientosBarajados.get(i - 1);
            }

            // ============================================================
            // LISTA DE CITAS ÚNICAS, SIN REPETIR, BARAJADAS
            // ============================================================
            ArrayList<Integer> citasUnicas = new ArrayList<>(TOTAL_CITAS);
            for (int i = 1; i <= TOTAL_CITAS; i++) {
                citasUnicas.add(i);
            }
            Collections.shuffle(citasUnicas);

            int indexCita = 0;  
            // ============================================================


            // ============================================================
            // 1) REGISTROS CON CITA Y CON PRODUCTO (1,497,600)
            // ============================================================
            for (int i = 0; i < TOTAL_CON_CITA_CON_PRODUCTO; i++) {

                int productoId = rand.nextInt(TOTAL_PRODUCTOS) + 1;
                int facturaId = rand.nextInt(TOTAL_FACTURAS) + 1;
                int cantidad = rand.nextInt(3) + 1;

                int citaId = citasUnicas.get(indexCita++);
                int tratamientoId = tratamientoPorCita[citaId];

                String linea = idDetalle + "," +
                        productoId + "," +
                        facturaId + "," +
                        cantidad + "," +
                        citaId + "," +
                        tratamientoId;

                todasLasLineas.add(linea);
                idDetalle++;

                if (i % 500000 == 0 && i > 0) {
                    System.out.println("Generados CON cita (CON producto): " + i);
                }
            }


            // ============================================================
            // 2) REGISTROS CON CITA PERO SIN PRODUCTO (806,400)
            // ============================================================
            for (int i = 0; i < TOTAL_CON_CITA_SIN_PRODUCTO; i++) {

                int facturaId = rand.nextInt(TOTAL_FACTURAS) + 1;
                int cantidad = rand.nextInt(3) + 1;

                int citaId = citasUnicas.get(indexCita++);
                int tratamientoId = tratamientoPorCita[citaId];

                String linea = idDetalle + ",," +  
                        facturaId + "," +
                        cantidad + "," +
                        citaId + "," +
                        tratamientoId;

                todasLasLineas.add(linea);
                idDetalle++;

                if (i % 500000 == 0 && i > 0) {
                    System.out.println("Generados CON cita (SIN producto): " + i);
                }
            }


            // ============================================================
            // 3) REGISTROS SIN CITA NI TRATAMIENTO (2,628,288)
            // ============================================================
            for (int i = 0; i < TOTAL_SIN_CITA; i++) {

                int productoId = rand.nextInt(TOTAL_PRODUCTOS) + 1;
                int facturaId = rand.nextInt(TOTAL_FACTURAS) + 1;
                int cantidad = rand.nextInt(3) + 1;

                String linea = idDetalle + "," +
                        productoId + "," +
                        facturaId + "," +
                        cantidad + ",,";

                todasLasLineas.add(linea);
                idDetalle++;

                if (i % 500000 == 0 && i > 0) {
                    System.out.println("Generados SIN cita: " + i);
                }
            }

            // MEZCLA FINAL DE TODAS LAS LÍNEAS
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
