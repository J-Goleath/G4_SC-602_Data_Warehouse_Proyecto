package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarEspecies {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Especies.csv";

        String[] especies = {
            "Perro",
            "Gato",
            "Conejo",
            "Ave",
            "Reptil",
            "Hamster"
        };

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 0; i < especies.length; i++) {
                writer.append((i + 1) + "," + especies[i] + "\n");
            }

            System.out.println("Archivo Especies.csv generado correctamente.");
            System.out.println("Total de especies: " + especies.length);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
