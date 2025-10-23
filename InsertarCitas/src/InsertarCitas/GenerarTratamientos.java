package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class GenerarTratamientos {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Tratamientos.csv";

        // Total de diagnósticos
        final int TOTAL_DIAGNOSTICOS = 2_995_200;

        // Distribución
        final int DIAG_1 = (int) (TOTAL_DIAGNOSTICOS * 0.70); // 70 % con 1 tratamiento
        final int DIAG_2 = (int) (TOTAL_DIAGNOSTICOS * 0.25); // 25 % con 2 tratamientos
        final int DIAG_3 = TOTAL_DIAGNOSTICOS - DIAG_1 - DIAG_2; // 5 % con 3 tratamientos

        // Rango de medicamentos
        final int MIN_MEDICAMENTO = 1;
        final int MAX_MEDICAMENTO = 320;

        // Dosis posibles
        String[] dosisPosibles = {"5", "10", "15", "20", "25", "30",
                                  "5", "10", "15", "20", "25", "30"};

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            int idTratamiento = 1;

            // Paso 1: Todos los diagnósticos reciben al menos 1 tratamiento
            for (int idDiagnostico = 1; idDiagnostico <= TOTAL_DIAGNOSTICOS; idDiagnostico++) {
                int idMedicamento = rand.nextInt(MAX_MEDICAMENTO - MIN_MEDICAMENTO + 1) + MIN_MEDICAMENTO;
                String dosis = dosisPosibles[rand.nextInt(dosisPosibles.length)];

                writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + dosis + "\n");
                idTratamiento++;

                if (idDiagnostico % 500000 == 0) {
                    System.out.println("Asignado tratamiento base a diagnostico " + idDiagnostico);
                }
            }

            // Paso 2: Seleccionar diagnósticos para un 2do tratamiento (25 %)
            for (int i = 0; i < DIAG_2; i++) {
                int idDiagnostico = rand.nextInt(TOTAL_DIAGNOSTICOS) + 1;
                Set<Integer> usados = new HashSet<>();

                // Para evitar repetir medicamento ya asignado al mismo diagnóstico
                int idMedicamentoBase = rand.nextInt(MAX_MEDICAMENTO - MIN_MEDICAMENTO + 1) + MIN_MEDICAMENTO;
                usados.add(idMedicamentoBase);

                int idMedicamento;
                do {
                    idMedicamento = rand.nextInt(MAX_MEDICAMENTO - MIN_MEDICAMENTO + 1) + MIN_MEDICAMENTO;
                } while (usados.contains(idMedicamento));

                String dosis = dosisPosibles[rand.nextInt(dosisPosibles.length)];
                writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + dosis + "\n");
                idTratamiento++;

                if (i % 250000 == 0 && i > 0) {
                    System.out.println("Progreso grupo 2: " + i + " tratamientos generados");
                }
            }

            // Paso 3: Seleccionar diagnósticos para un 3er tratamiento (5 %)
            for (int i = 0; i < DIAG_3; i++) {
                int idDiagnostico = rand.nextInt(TOTAL_DIAGNOSTICOS) + 1;
                Set<Integer> usados = new HashSet<>();

                // Añadir 2 medicamentos adicionales diferentes
                for (int t = 0; t < 2; t++) {
                    int idMedicamento;
                    do {
                        idMedicamento = rand.nextInt(MAX_MEDICAMENTO - MIN_MEDICAMENTO + 1) + MIN_MEDICAMENTO;
                    } while (usados.contains(idMedicamento));
                    usados.add(idMedicamento);

                    String dosis = dosisPosibles[rand.nextInt(dosisPosibles.length)];
                    writer.append(idTratamiento + "," + idDiagnostico + "," + idMedicamento + "," + dosis + "\n");
                    idTratamiento++;
                }

                if (i % 100000 == 0 && i > 0) {
                    System.out.println("Progreso grupo 3: " + i + " tratamientos generados");
                }
            }

            System.out.println("Archivo generado correctamente");
            System.out.println("Total tratamientos generados: " + (idTratamiento - 1));

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        double segundos = (fin - inicio) / 1000.0;
        System.out.println("Tiempo total: " + segundos + " segundos");
    }
}
