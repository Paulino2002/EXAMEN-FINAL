package com.sakila.reports;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Generador sencillo de reportes
 */
public class GeneradorReportes {

    public boolean generarCSV(String nombreArchivo, String encabezados, ArrayList<String[]> datos) {
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            fw.append(encabezados).append("\n");
            for (String[] row : datos) {
                fw.append(String.join(",", row)).append("\n");
            }
            fw.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean generarCSV(String nombreArchivo, String encabezados, java.util.List<String[]> datos) {
        return generarCSV(nombreArchivo, encabezados, new ArrayList<>(datos));
    }
}