package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarProveedores {

    public static int[] tiposProveedores;  // 🔥 Guardar tipo de cada proveedor

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Proveedores.csv";

        int totalProveedores = 150;
        int totalTipos = Categorias.TIPOS.length;

        tiposProveedores = new int[totalProveedores + 1];

        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalProveedores; i++) {

                int idTipoProveedor = rand.nextInt(totalTipos) + 1;
                tiposProveedores[i] = idTipoProveedor;  // 🔥 Guardamos tipo

                String nombreEmpresa = "Proveedor_" + i;

                writer.append(i + "," + idTipoProveedor + "," + nombreEmpresa + "\n");
            }

            System.out.println("Archivo Proveedores.csv generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
