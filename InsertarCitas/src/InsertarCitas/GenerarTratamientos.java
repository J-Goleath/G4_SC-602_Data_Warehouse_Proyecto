package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.HashSet;

public class GenerarTratamientos {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Tratamientos.csv";

        // Total diagnósticos reales
        final int TOTAL_DIAGNOSTICOS = 2_995_200;

        // Solo 65% reciben tratamiento
        final int DIAGNOSTICOS_TRATADOS = (int) (TOTAL_DIAGNOSTICOS * 0.65);

        // Distribución correcta dentro del 65%
        final int GRUPO_1 = (int) (DIAGNOSTICOS_TRATADOS * 0.70); // 1 tratamiento
        final int GRUPO_2 = (int) (DIAGNOSTICOS_TRATADOS * 0.25); // 2 tratamientos
        final int GRUPO_3 = DIAGNOSTICOS_TRATADOS - GRUPO_1 - GRUPO_2; // 5% → 3 tratamientos

        // Medicamentos disponibles
        final int MIN_MED = 1;
        final int MAX_MED = 320;

        // Dosis disponibles
        String[] dosis = {"5", "10", "15", "20", "25", "30"};

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            int idTratamiento = 1;

            // ======== 1 TRATAMIENTO ========
            for (int i = 0; i < GRUPO_1; i++) {
                int idDiagnostico = rand.nextInt(TOTAL_DIAGNOSTICOS) + 1;
                int idMedicamento = rand.nextInt(MAX_MED - MIN_MED + 1) + MIN_MED;
                String d = dosis[rand.nextInt(dosis.length)];

                writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + d + "\n");
                idTratamiento++;

                if (i % 200000 == 0)
                    System.out.println("Progreso grupo 1: " + i);
            }

            // ======== 2 TRATAMIENTOS ========
            for (int i = 0; i < GRUPO_2; i++) {
                int idDiagnostico = rand.nextInt(TOTAL_DIAGNOSTICOS) + 1;

                HashSet<Integer> usados = new HashSet<>();

                for (int t = 0; t < 2; t++) {
                    int idMedicamento;
                    do {
                        idMedicamento = rand.nextInt(MAX_MED - MIN_MED + 1) + MIN_MED;
                    } while (usados.contains(idMedicamento));

                    usados.add(idMedicamento);

                    String d = dosis[rand.nextInt(dosis.length)];

                    writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + d + "\n");
                    idTratamiento++;
                }

                if (i % 150000 == 0)
                    System.out.println("Progreso grupo 2: " + i);
            }

            // ======== 3 TRATAMIENTOS ========
            for (int i = 0; i < GRUPO_3; i++) {
                int idDiagnostico = rand.nextInt(TOTAL_DIAGNOSTICOS) + 1;

                HashSet<Integer> usados = new HashSet<>();

                for (int t = 0; t < 3; t++) {
                    int idMedicamento;
                    do {
                        idMedicamento = rand.nextInt(MAX_MED - MIN_MED + 1) + MIN_MED;
                    } while (usados.contains(idMedicamento));

                    usados.add(idMedicamento);

                    String d = dosis[rand.nextInt(dosis.length)];

                    writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + d + "\n");
                    idTratamiento++;
                }

                if (i % 50000 == 0)
                    System.out.println("Progreso grupo 3: " + i);
            }

            System.out.println("Archivo generado correctamente");
            System.out.println("Total tratamientos generados: " + (idTratamiento - 1));

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (fin - inicio) / 1000.0 + " segundos");
    }
}
