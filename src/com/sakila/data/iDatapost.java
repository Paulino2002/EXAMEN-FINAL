package com.sakila.data;

import java.util.ArrayList;

/**
 * Interface generica para operaciones CRUD.
 */
public interface iDatapost<T> {
    boolean post(T objeto);
    boolean put(T objeto);
    boolean delete(int idRegistro);
    T get(int idRegistro);
    ArrayList<T> get(); // lista completa
}
