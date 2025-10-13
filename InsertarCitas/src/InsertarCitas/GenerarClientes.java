package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarClientes {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\Clientes.csv";

        final int TOTAL_CLIENTES = 360_000;
        final int TOTAL_DISTRITOS = 488;

        String[] nombres = {"David", "Sofia", "Carlos", "Maria", "Jose", "Ana", "Luis", "Valeria", "Andres", "Fernanda",
                            "Diego", "Camila", "Juan", "Paula", "Ricardo", "Daniela", "Esteban", "Gabriela", "Felipe", "Laura"};

        String[] apellidos = {"Garro", "Zamora", "Ramirez", "Camacho", "Sanchez", "Gomez", "Mora", "Vargas", "Rojas", "Perez",
                              "Rodriguez", "Chacon", "Jimenez", "Castro", "Solano", "Arias", "Hernandez", "Salas", "Blanco", "Leiva"};

        Random rand = new Random();

        long inicio = System.currentTimeMillis();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int idCliente = 1; idCliente <= TOTAL_CLIENTES; idCliente++) {
                String nombre = nombres[rand.nextInt(nombres.length)];
                String apellido1 = apellidos[rand.nextInt(apellidos.length)];
                String apellido2 = apellidos[rand.nextInt(apellidos.length)];
                while (apellido1.equals(apellido2)) {
                    apellido2 = apellidos[rand.nextInt(apellidos.length)];
                }

                // Dirección y distrito
                int idDistrito = rand.nextInt(TOTAL_DISTRITOS) + 1;
                int numeroCalle = rand.nextInt(999) + 1;
                String direccion = "Calle " + numeroCalle + ", Distrito " + idDistrito;

                // Email
                String email = (nombre + "." + apellido1 + "@email.com").toLowerCase();

                writer.append(idCliente + "," + nombre + "," + apellido1 + "," + apellido2 + "," + direccion + "," + email + "," + idDistrito + "\n");

                if (idCliente % 50_000 == 0) {
                    System.out.println("Clientes generados: " + idCliente);
                }
            }

            System.out.println("Archivo Clientes.csv generado correctamente.");
            System.out.println("Total clientes: " + TOTAL_CLIENTES);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }

        long fin = System.currentTimeMillis();
        double segundos = (fin - inicio) / 1000.0;
        System.out.println("Tiempo total: " + segundos + " segundos");
    }
}
