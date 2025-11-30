package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class GenerarTratamientos {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Tratamientos.csv";

        // Total diagnósticos reales
        final int TOTAL_DIAGNOSTICOS = 2_995_200;

        // Total diagnósticos que SÍ tendrán tratamiento
        final int TOTAL_TRATAMIENTOS = 2_304_000;

        // Medicamentos disponibles
        final int MIN_MED = 1;
        final int MAX_MED = 320;

        // Dosis disponibles
        String[] dosis = {"5", "10", "15", "20", "25", "30"};

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            int idTratamiento = 1;

            // Seleccionar aleatoriamente 2,304,000 diagnósticos sin repetir

            ArrayList<Integer> diagnosticos = new ArrayList<>(TOTAL_DIAGNOSTICOS);
            for (int i = 1; i <= TOTAL_DIAGNOSTICOS; i++) {
                diagnosticos.add(i);
            }

            // Mezclar todos los diagnósticos
            Collections.shuffle(diagnosticos);

            // Tomar solo los primeros 2,304,000 (son los que tendrán tratamiento)
            ArrayList<Integer> diagnosticosConTratamiento =
                    new ArrayList<>(diagnosticos.subList(0, TOTAL_TRATAMIENTOS));

            // ============================================================

            for (int i = 0; i < TOTAL_TRATAMIENTOS; i++) {

                int idDiagnostico = diagnosticosConTratamiento.get(i);
                int idMedicamento = rand.nextInt(MAX_MED - MIN_MED + 1) + MIN_MED;
                String d = dosis[rand.nextInt(dosis.length)];

                writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + d + "\n");

                idTratamiento++;

                if (i % 200000 == 0)
                    System.out.println("Progreso: " + i);
            }

            System.out.println("Archivo generado correctamente");
            System.out.println("Diagnósticos totales: " + TOTAL_DIAGNOSTICOS);
            System.out.println("Tratamientos generados: " + (idTratamiento - 1));
            System.out.println("Diagnósticos SIN tratamiento: " + (TOTAL_DIAGNOSTICOS - TOTAL_TRATAMIENTOS));

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (fin - inicio) / 1000.0 + " segundos");
    }
}
