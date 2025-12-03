package com.sakila.reports;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.sakila.models.Pelicula;

public class GeneradorJSONPeliculas {

    public boolean generarJSON(String nombreArchivo, List<Pelicula> peliculas) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {

            fw.write("[\n");

            for (int i = 0; i < peliculas.size(); i++) {

                Pelicula p = peliculas.get(i);

                fw.write("  {\n");
                fw.write("    \"id\": " + p.getIdRegistro() + ",\n");
                fw.write("    \"titulo\": \"" + escapar(p.getTitulo()) + "\",\n");
                fw.write("    \"descripcion\": \"" + escapar(p.getDescripcion()) + "\",\n");
                fw.write("    \"ano\": " + p.getAnoLanzamiento() + ",\n");
                fw.write("    \"idioma_id\": " + p.getIdioma().getIdRegistro() + ",\n");
                fw.write("    \"duracion\": " + p.getDuracion() + ",\n");
                fw.write("    \"costo_renta\": " + p.getCostoRenta() + "\n");
                fw.write("  }");

                if (i < peliculas.size() - 1) fw.write(",");

                fw.write("\n");
            }

            fw.write("]");
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    private String escapar(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}

