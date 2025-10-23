package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarMedicamentos {

    public static void generar() {
        String rutaArchivo = Configuracion.rutaBase + "Medicamentos.csv";

        String[] nombresBase = {
            "Amoxicilina", "Cefalexina", "Ivermectina", "Prednisolona", "Doxiciclina",
            "Furosemida", "Meloxicam", "Metronidazol", "Tramadol", "Omeprazol",
            "Enrofloxacina", "Ketamina", "Clorfenamina", "Atropina", "Clindamicina",
            "Ranitidina", "Ceftriaxona", "Ciprofloxacina", "Levamisol", "Gentamicina",
            "Albendazol", "Cloranfenicol", "Fipronil", "Selamectina", "Fenbendazol",
            "Loratadina", "Toltrazuril", "Suero Ringer", "Lidocaina", "Sulfadiazina",
            "Diclofenaco", "Amitraz", "Ketoprofeno", "Enalapril", "Carprofeno", "Acepromazina",
            "Dexametasona", "Ciclosporina", "Isoflurano", "Bromhexina", "Tiabendazol",
            "Propranolol", "Levetiracetam", "Gabapentina", "Fentanilo", "Cefotaxima",
            "Tetraciclina", "Cefuroxima", "Clorhexidina", "Cefixima"
        };

        int totalMedicamentos = 150;
        Random rand = new Random();

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            for (int i = 1; i <= totalMedicamentos; i++) {
                String base = nombresBase[rand.nextInt(nombresBase.length)];
                String nombre = base + " " + i;
                writer.append(i + "," + nombre + "\n");
            }

            System.out.println("Archivo Medicamentos.csv generado correctamente.");
            System.out.println("Total de medicamentos: " + totalMedicamentos);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
