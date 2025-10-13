package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarDiagnosticos {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\Diagnosticos.csv";

        int totalCitas = 2304000;
        int totalDiagnosticos = 2995200;
        int idDiagnostico = 1;
        Random rand = new Random();

        int totalTipos = 15;

        // Observaciones medicas veterinarias realistas
        String[] observaciones = {
            "Revisado por infeccion cutanea leve",
            "Vacunacion anual aplicada correctamente",
            "Tratamiento por parvovirus completado",
            "Desparasitacion interna recomendada",
            "Se detecto sobrepeso, dieta especial sugerida",
            "Lesion en pata tratada con antibiotico topico",
            "Control rutinario sin alteraciones",
            "Tratamiento dental realizado exitosamente",
            "Analisis de sangre dentro de parametros normales",
            "Requiere seguimiento por problema respiratorio",
            "Se sospecha alergia alimentaria",
            "Se aplico medicacion antiparasitaria",
            "Recuperacion favorable tras cirugia menor",
            "Control de heridas y limpieza programada",
            "Se recomienda chequeo cardiaco en proxima visita"
        };

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            System.out.println("Generando Diagnosticos.csv con " + totalDiagnosticos + " registros...");
            long start = System.currentTimeMillis();

            int citasConUnDiagnostico = (int) (totalCitas * 0.7);

            // 70% de citas con 1 diagnostico
            for (int idCita = 1; idCita <= citasConUnDiagnostico; idCita++) {
                int tipo = rand.nextInt(totalTipos) + 1;
                String obs = observaciones[rand.nextInt(observaciones.length)];
                writer.append(idDiagnostico + "," + idCita + "," + tipo + "," + obs + "\n");
                idDiagnostico++;

                if (idCita % 100000 == 0)
                    System.out.println("Progreso: " + idCita + " citas procesadas (1 diagnostico)");
            }

            // 30% de citas con 2 diagnosticos
            for (int idCita = citasConUnDiagnostico + 1; idCita <= totalCitas; idCita++) {
                for (int j = 0; j < 2; j++) {
                    int tipo = rand.nextInt(totalTipos) + 1;
                    String obs = observaciones[rand.nextInt(observaciones.length)];
                    writer.append(idDiagnostico + "," + idCita + "," + tipo + "," + obs + "\n");
                    idDiagnostico++;
                }

                if (idCita % 100000 == 0)
                    System.out.println("Progreso: " + idCita + " citas procesadas (2 diagnosticos)");
            }

            long end = System.currentTimeMillis();
            System.out.println("Archivo Diagnosticos.csv generado correctamente en:");
            System.out.println(rutaArchivo);
            System.out.println("Tiempo total: " + ((end - start) / 1000) + " segundos");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
