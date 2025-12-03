package com.sakila.models;

import java.sql.Timestamp;

/**
 * Modelo simple Pelicula.
 */
public class Pelicula extends EntidadAbstracta {

    private String titulo;
    private Integer anoLanzamiento;
    private Idioma idioma;
    private Integer duracion;
    private Double costoRenta;
    private String descripcion;

    // Constructor para inserciones (sin id)
    public Pelicula(String titulo, Integer anoLanzamiento, Idioma idioma, Integer duracion, Double costoRenta, String descripcion) {
        this.titulo = titulo;
        this.anoLanzamiento = anoLanzamiento;
        this.idioma = idioma;
        this.duracion = duracion;
        this.costoRenta = costoRenta;
        this.descripcion = descripcion;
    }

    // Constructor con id (para updates / selects)
    public Pelicula(int idRegistro, String titulo, Integer anoLanzamiento, Idioma idioma, Integer duracion, Double costoRenta, String descripcion, Timestamp ultimaActualizacion) {
        this.idRegistro = idRegistro;
        this.titulo = titulo;
        this.anoLanzamiento = anoLanzamiento;
        this.idioma = idioma;
        this.duracion = duracion;
        this.costoRenta = costoRenta;
        this.descripcion = descripcion;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Pelicula(int idRegistro) { this.idRegistro = idRegistro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getAnoLanzamiento() { return anoLanzamiento; }
    public void setAnoLanzamiento(Integer ano) { this.anoLanzamiento = ano; }

    public Idioma getIdioma() { return idioma; }
    public void setIdioma(Idioma idioma) { this.idioma = idioma; }

    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }

    public Double getCostoRenta() { return costoRenta; }
    public void setCostoRenta(Double costoRenta) { this.costoRenta = costoRenta; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}