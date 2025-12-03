package com.sakila.controllers;
import java.util.ArrayList;
import com.sakila.data.GestorPeliculas;
import com.sakila.models.Pelicula;

public class ControladorPeliculas {

    private final GestorPeliculas gestor;

    public ControladorPeliculas() {
        this.gestor = new GestorPeliculas();
    }


    public boolean agregarPelicula(Pelicula p) {
        return gestor.post(p);
    }

    public boolean modificarPelicula(Pelicula p) {
        return gestor.put(p);
    }

    public boolean eliminarPelicula(int id) {
        return gestor.delete(id);
    }

    public Pelicula obtenerPorId(int id) {
        return gestor.get(id);
    }


    public ArrayList<Pelicula> buscarPorTitulo(String titulo) {
        return gestor.buscarPorTitulo(titulo);
    }

    public ArrayList<Pelicula> listarTodas() {
        return gestor.get();
    }

}




