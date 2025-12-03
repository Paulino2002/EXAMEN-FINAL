package com.sakila.models;

import java.sql.Timestamp;

/**
 * Modelo Idioma.
 */
public class Idioma extends EntidadAbstracta {
    private String nombre;

    public Idioma(int idRegistro, String nombre, Timestamp ultimaActualizacion) {
        this.idRegistro = idRegistro;
        this.nombre = nombre;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Idioma(int idRegistro) { this.idRegistro = idRegistro; }

    public Idioma(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}