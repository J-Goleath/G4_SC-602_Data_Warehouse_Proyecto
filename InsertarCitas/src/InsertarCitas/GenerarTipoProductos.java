package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarTipoProductos {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\TipoProductos.csv";

        String[] descripciones = {
            "Medicamento",
            "Alimento",
            "Accesorio",
            "Higiene",
            "Equipamiento"
        };

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 0; i < descripciones.length; i++) {
                writer.append((i + 1) + "," + descripciones[i] + "\n");
            }

            System.out.println("Archivo TipoProductos.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + descripciones.length);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
