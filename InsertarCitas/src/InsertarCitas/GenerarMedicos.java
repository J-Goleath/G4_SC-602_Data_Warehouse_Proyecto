package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarMedicos {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\Medicos.csv";

        String[] nombres = {"David", "Sofia", "Carlos", "Maria", "Jose", "Ana", "Luis", "Valeria", "Andres", "Fernanda",
                            "Diego", "Camila", "Juan", "Paula", "Ricardo", "Daniela", "Esteban", "Gabriela", "Felipe", "Laura"};

        String[] apellidos = {"Garro", "Zamora", "Ramirez", "Camacho", "Sanchez", "Gomez", "Mora", "Vargas", "Rojas", "Perez",
                              "Rodriguez", "Chacon", "Jimenez", "Castro", "Solano", "Arias", "Hernandez", "Salas", "Blanco", "Leiva"};

        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= 80; i++) {
                String nombre = nombres[rand.nextInt(nombres.length)];
                String apellido1 = apellidos[rand.nextInt(apellidos.length)];
                String apellido2 = apellidos[rand.nextInt(apellidos.length)];
                while (apellido1.equals(apellido2)) {
                    apellido2 = apellidos[rand.nextInt(apellidos.length)];
                }
                String email = (nombre + apellido1 + "@email com").toLowerCase();
                writer.append(i + "," + nombre + "," + apellido1 + "," + apellido2 + "," + email + "\n");
            }

            System.out.println("Archivo Medicos.csv generado exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
