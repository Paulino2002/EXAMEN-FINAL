package com.sakila.models;

/**
 * Modelo Inventario (referencia a film y store).
 */
public class Inventario extends EntidadAbstracta {
    private int filmId;
    private int storeId;

    public Inventario() {}
    public Inventario(int idRegistro) { this.idRegistro = idRegistro; }
    public Inventario(int idRegistro, int filmId, int storeId) {
        this.idRegistro = idRegistro;
        this.filmId = filmId;
        this.storeId = storeId;
    }

    public int getFilmId() { return filmId; }
    public void setFilmId(int filmId) { this.filmId = filmId; }

    public int getStoreId() { return storeId; }
    public void setStoreId(int storeId) { this.storeId = storeId; }
}
