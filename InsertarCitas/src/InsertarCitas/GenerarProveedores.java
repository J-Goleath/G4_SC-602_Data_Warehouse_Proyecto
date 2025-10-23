package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarProveedores {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Proveedores.csv";

        int totalProveedores = 150;
        int totalTipos = 5;
        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalProveedores; i++) {
                int idTipoProveedor = rand.nextInt(totalTipos) + 1;
                String nombreEmpresa = "Proveedor_" + i;

                writer.append(i + "," + idTipoProveedor + "," + nombreEmpresa + "\n");
            }

            System.out.println("Archivo Proveedores.csv generado correctamente.");
            System.out.println("Total de lineas generadas: " + totalProveedores);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
