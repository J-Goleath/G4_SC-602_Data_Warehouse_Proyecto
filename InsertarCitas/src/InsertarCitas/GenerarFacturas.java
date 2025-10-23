package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerarFacturas {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Facturas.csv";

        int totalFacturas = 2304000;   // Total de facturas (igual que citas)
        int totalAnimales = 504000;    // Total de animales
        Random rand = new Random();

        // Rango de fechas: 01/01/2020 a 31/12/2024
        LocalDate fechaInicio = LocalDate.of(2020, 1, 1);
        LocalDate fechaFin = LocalDate.of(2024, 12, 31);
        long diasEntre = fechaFin.toEpochDay() - fechaInicio.toEpochDay();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalFacturas; i++) {
                int idAnimal = rand.nextInt(totalAnimales) + 1;

                // Fecha aleatoria entre inicio y fin
                long diasAleatorios = (long) (rand.nextDouble() * diasEntre);
                LocalDate fecha = fechaInicio.plusDays(diasAleatorios);
                String fechaStr = fecha.format(formatoFecha);

                writer.append(i + "," + idAnimal + "," + fechaStr + "\n");
            }

            System.out.println("Archivo Factura.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + totalFacturas);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
