package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarTipoProveedores {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "TipoProveedores.csv";

        String[] descripciones = {
            "Farmaceutico",
            "Alimentos",
            "Accesorios",
            "Higiene",
            "Equipamiento"
        };

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 0; i < descripciones.length; i++) {
                writer.append((i + 1) + "," + descripciones[i] + "\n");
            }

            System.out.println("Archivo TipoProveedores.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + descripciones.length);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
