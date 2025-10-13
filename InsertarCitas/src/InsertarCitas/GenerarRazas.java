package InsertarCitas;

import java.io.FileWriter;
import java.io.IOException;

public class GenerarRazas {

    public static void generar() {
        String rutaArchivo = "D:\\Documentos\\DataWareHouse\\G4_SC-602_Data_Warehouse_Proyecto\\Archivos CSV\\Razas.csv";

        // Perros (33 razas)
        String[] razasPerros = {
            "Labrador Retriever", "Golden Retriever", "Beagle", "Poodle", "Bulldog",
            "Chihuahua", "Dachshund", "Boxer", "Rottweiler", "Pomerania",
            "Shih Tzu", "Schnauzer", "Doberman", "Husky Siberiano", "Pitbull",
            "Bóxer Miniatura", "Basset Hound", "Maltés", "Boston Terrier", "Cocker Spaniel",
            "Dálmata", "Akita", "Pastor Alemán", "Border Collie", "Samoyedo",
            "Cane Corso", "Pug", "Bull Terrier", "Galgo", "Weimaraner",
            "Whippet", "Fox Terrier", "Gran Danés"
        };

        // Gatos (17 razas)
        String[] razasGatos = {
            "Siamés", "Persa", "Maine Coon", "Bengala", "Azul Ruso",
            "Sphynx", "British Shorthair", "Scottish Fold", "Bombay", "Birmano",
            "Oriental", "Himalayo", "Somalí", "American Shorthair", "Ragdoll",
            "Devon Rex", "Chartreux"
        };

        // Conejos (2 razas)
        String[] razasConejos = {
            "Mini Lop", "Holland Lop"
        };

        // Aves (2 razas)
        String[] razasAves = {
            "Periquito Australiano", "Canario"
        };

        // Reptiles (1 raza)
        String[] razasReptiles = {
            "Iguana Verde"
        };

        // Hámster (1 raza)
        String[] razasHamster = {
            "Hámster Sirio"
        };

        try (FileWriter writer = new FileWriter(rutaArchivo)) {

            int idRaza = 1;

            // Perros
            for (String raza : razasPerros) {
                writer.append(idRaza + "," + raza + ",1\n");
                idRaza++;
            }

            // Gatos
            for (String raza : razasGatos) {
                writer.append(idRaza + "," + raza + ",2\n");
                idRaza++;
            }

            // Conejos
            for (String raza : razasConejos) {
                writer.append(idRaza + "," + raza + ",3\n");
                idRaza++;
            }

            // Aves
            for (String raza : razasAves) {
                writer.append(idRaza + "," + raza + ",4\n");
                idRaza++;
            }

            // Reptiles
            for (String raza : razasReptiles) {
                writer.append(idRaza + "," + raza + ",5\n");
                idRaza++;
            }

            // Hámster
            for (String raza : razasHamster) {
                writer.append(idRaza + "," + raza + ",6\n");
                idRaza++;
            }

            System.out.println("Archivo Razas.csv generado correctamente.");
            System.out.println("Total de razas: " + (idRaza - 1));

        } catch (IOException e) {
            System.out.println("Error al crear el archivo CSV: " + e.getMessage());
        }
    }
}
