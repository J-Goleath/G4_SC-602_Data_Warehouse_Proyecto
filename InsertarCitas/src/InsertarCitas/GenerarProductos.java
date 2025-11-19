package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarProductos {

    public static int[] tiposProductos;  // 🔥 Guardar tipo de cada producto

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Productos.csv";

        int totalProductos = 250;
        int totalMedicamentos = 320;
        int totalTipos = Categorias.TIPOS.length;

        tiposProductos = new int[totalProductos + 1];
        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalProductos; i++) {

                int idMedicamento = rand.nextInt(totalMedicamentos) + 1;
                int idTipoProducto = rand.nextInt(totalTipos) + 1;
                tiposProductos[i] = idTipoProducto;  // 🔥 Guardar tipo del producto

                String nombre = "Producto_" + i;
                int precioUnitario = 2000 + rand.nextInt(48001);
                int stock = 10 + rand.nextInt(491);

                writer.append(
                    i + "," +
                    idMedicamento + "," +
                    idTipoProducto + "," +
                    nombre + "," +
                    precioUnitario + "," +
                    stock + "\n"
                );
            }

            System.out.println("Archivo Productos.csv generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
