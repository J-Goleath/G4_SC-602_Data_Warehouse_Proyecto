package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarTipoProductos {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "TipoProductos.csv";

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 0; i < Categorias.TIPOS.length; i++) {
                writer.append((i + 1) + "," + Categorias.TIPOS[i] + "\n");
            }

            System.out.println("Archivo TipoProductos.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + Categorias.TIPOS.length);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
