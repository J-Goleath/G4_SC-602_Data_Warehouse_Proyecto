package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarTipoDiagnostico {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\TiposDiagnostico.csv";

        String[] descripciones = {
            "Enfermedades respiratorias",
            "Problemas digestivos",
            "Enfermedades de la piel",
            "Enfermedades infecciosas",
            "Problemas dentales",
            "Problemas oculares",
            "Problemas auditivos",
            "Problemas endocrinos",
            "Problemas hepaticos",
            "Problemas renales",
            "Traumatismos",
            "Parasitos internos",
            "Parasitos externos",
            "Vacunacion preventiva",
            "Chequeo general"
        };

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 0; i < descripciones.length; i++) {
                writer.append((i + 1) + "," + descripciones[i] + "\n");
            }

            System.out.println("Archivo TiposDiagnostico.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + descripciones.length);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
