package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerarCitas {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\Citas.csv";

        int totalCitas = 2304000;
        int totalAnimales = 504000;
        int totalMedicos = 80;

        String[] motivos = {
            "Vacunacion","Chequeo general","Control de peso", "Emergencia", "Desparasitacion","Cirugia",
            "Consulta de seguimiento", "Peluqueria","Problemas digestivos","Problemas respiratorios"
        };

        Random rand = new Random();

        // Rango de fechas: 01/01/2019 a 31/12/2024 (6 años)
        LocalDate fechaInicio = LocalDate.of(2020, 1, 1);
        LocalDate fechaFin = LocalDate.of(2025, 12, 31);
        long diasEntre = fechaFin.toEpochDay() - fechaInicio.toEpochDay();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalCitas; i++) {
                int idAnimal = rand.nextInt(totalAnimales) + 1;
                int idMedico = rand.nextInt(totalMedicos) + 1;

                // Fecha aleatoria
                long diasAleatorios = (long) (rand.nextDouble() * diasEntre);
                LocalDate fecha = fechaInicio.plusDays(diasAleatorios);
                String fechaStr = fecha.format(formatoFecha);

                // Motivo aleatorio
                String motivo = motivos[rand.nextInt(motivos.length)];

                // Monto de la cita entre 10000 y 50000 colones
                int montoCita = 10000 + rand.nextInt(40001);

                writer.append(i + "," + idAnimal + "," + idMedico + "," + fechaStr + "," + motivo + "," + montoCita + "\n");

                if (i % 100000 == 0) {
                    System.out.println("Progreso: " + i + " citas generadas...");
                }
            }

            System.out.println("Archivo Citas.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + totalCitas);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
