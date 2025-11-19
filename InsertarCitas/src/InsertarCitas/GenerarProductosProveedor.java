package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerarProductosProveedor {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "ProductosProveedor.csv";

        int totalProductos = 250;
        int totalProveedores = 150;

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int productoID = 1; productoID <= totalProductos; productoID++) {

                int tipoProducto = GenerarProductos.tiposProductos[productoID];

                // 🔥 Obtener proveedores que coincidan en tipo
                ArrayList<Integer> proveedoresCompatibles = new ArrayList<>();

                for (int provID = 1; provID <= totalProveedores; provID++) {
                    if (GenerarProveedores.tiposProveedores[provID] == tipoProducto) {
                        proveedoresCompatibles.add(provID);
                    }
                }

                // No debería pasar, pero por si acaso
                if (proveedoresCompatibles.isEmpty()) continue;

                Random rand = new Random();
                int proveedorSeleccionado = proveedoresCompatibles.get(
                    rand.nextInt(proveedoresCompatibles.size())
                );

                writer.append(productoID + "," + proveedorSeleccionado + "\n");
            }

            System.out.println("Archivo ProductosProveedor.csv generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
