package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerarDistritos {

    static class Distrito {
        int idDistrito;
        String nombreDistrito;
        int idCanton;

        public Distrito(int id, String nombre, int canton) {
            this.idDistrito = id;
            this.nombreDistrito = nombre;
            this.idCanton = canton;
        }
    }

    public static void generar() {

        String rutaArchivo = "Distritos.csv";

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            writer.write("idDistrito,nombreDistrito,idCanton\n");

            List<Distrito> distritos = obtenerDistritos();

            for (Distrito d : distritos) {
                writer.write(d.idDistrito + "," + d.nombreDistrito + "," + d.idCanton + "\n");
            }

            System.out.println("Archivo Distritos.csv generado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Distrito> obtenerDistritos() {
        List<Distrito> lista = new ArrayList<>();

        // Aquí se agregarán los distritos cuando el usuario los solicite
        // Ejemplo de estructura:
        // lista.add(new Distrito(1, "Patalillo", 1));

        return lista;
    }
}