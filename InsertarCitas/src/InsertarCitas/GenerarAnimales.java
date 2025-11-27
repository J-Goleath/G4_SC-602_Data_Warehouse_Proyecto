package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarAnimales {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Animales.csv";

        final int TOTAL_CLIENTES = 360_000;
        final int TOTAL_RAZAS = 55;
        final int TOTAL_ANIMALES_ESPERADO = 504_000;

        // Distribución de mascotas por cliente
        final double PROB_1 = 0.70;
        final double PROB_2 = 0.22;
        final double PROB_3 = 0.06;
        final double PROB_4 = 0.02;

        // Posibles nombres de mascotas
        String[] nombresMascotas = {
            "Luna", "Max", "Rocky", "Bella", "Milo", "Toby", "Nala", "Coco", "Simba", "Kira",
            "Lucky", "Chispa", "Chispi", "Tommy", "Canela", "Rex", "Zeus", "Chico", "Bobby", "Daisy",
            "Toby", "Lola", "Moka", "Peluche", "Chocolate", "Tina", "Ginger", "Chanel", "Puppy", "Chispa"
        };

        Random rand = new Random();
        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            int idAnimal = 1;

            for (int idCliente = 1; idCliente <= TOTAL_CLIENTES; idCliente++) {

                // Determinar cuántos animales tiene este cliente según la distribución
                double p = rand.nextDouble();
                int numAnimales;
                if (p < PROB_1) {
                    numAnimales = 1;
                } else if (p < PROB_1 + PROB_2) {
                    numAnimales = 2;
                } else if (p < PROB_1 + PROB_2 + PROB_3) {
                    numAnimales = 3;
                } else {
                    numAnimales = 4;
                }

                for (int j = 0; j < numAnimales; j++) {
                    int idRaza = rand.nextInt(TOTAL_RAZAS) + 1;
                    String nombreMascota = nombresMascotas[rand.nextInt(nombresMascotas.length)];
                    int edad = rand.nextInt(21); // 0 a 20 años
                    double peso = 1 + (49 * rand.nextDouble()); // 1 a 50 kg
                    peso = Math.round(peso * 10.0) / 10.0; // Redondear a 1 decimal

                    writer.append(idAnimal + "," + idRaza + "," + idCliente + "," +
                                  nombreMascota + "," + edad + "," + peso + "\n");
                    idAnimal++;
                }

                if (idCliente % 50_000 == 0) {
                    System.out.println("Clientes procesados: " + idCliente +
                                       " | Animales generados: " + idAnimal);
                }
            }

            int totalGenerados = idAnimal - 1;

            if (totalGenerados < TOTAL_ANIMALES_ESPERADO) {

                int faltantes = TOTAL_ANIMALES_ESPERADO - totalGenerados;

                System.out.println("Se generaron menos animales de lo esperado: " +
                                   totalGenerados + ". Faltantes: " + faltantes);

                for (int k = 0; k < faltantes; k++) {

                    int idRaza = rand.nextInt(TOTAL_RAZAS) + 1;
                    String nombreMascota = nombresMascotas[rand.nextInt(nombresMascotas.length)];
                    int edad = rand.nextInt(21);
                    double peso = 1 + (49 * rand.nextDouble());
                    peso = Math.round(peso * 10.0) / 10.0;

                    // Cliente aleatorio válido
                    int idCliente = rand.nextInt(TOTAL_CLIENTES) + 1;

                    writer.append(idAnimal + "," + idRaza + "," + idCliente + "," +
                                  nombreMascota + "," + edad + "," + peso + "\n");

                    idAnimal++;
                }
            }

            System.out.println("Archivo Animales.csv generado correctamente.");
            System.out.println("Total de animales generados: " + (idAnimal - 1));

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        double segundos = (fin - inicio) / 1000.0;
        System.out.println("Tiempo total: " + segundos + " segundos");
    }
}
