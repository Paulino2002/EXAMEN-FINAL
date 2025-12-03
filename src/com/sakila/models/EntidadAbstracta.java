package com.sakila.models;

/**
 * Entidad abstracta base para todas las entidades.
 * Contiene clave primaria y timestamp de actualización.
 */
public abstract class EntidadAbstracta {
    protected int idRegistro;
    protected java.sql.Timestamp ultimaActualizacion;

    public int getIdRegistro() { return idRegistro; }
    public void setIdRegistro(int idRegistro) { this.idRegistro = idRegistro; }

    public java.sql.Timestamp getUltimaActualizacion() { return ultimaActualizacion; }
    public void setUltimaActualizacion(java.sql.Timestamp ts) { this.ultimaActualizacion = ts; }
}
