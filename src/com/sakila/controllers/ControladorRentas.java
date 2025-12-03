package com.sakila.controllers;

import com.sakila.data.GestorRentas;
import com.sakila.models.Inventario;
import com.sakila.models.Cliente;
import com.sakila.models.Personal;
import com.sakila.reports.GeneradorReportes;

import java.sql.SQLException;
import java.util.List;

public class ControladorRentas {

    private final GestorRentas gestorRentas;
    private final GeneradorReportes generadorReportes;

    public ControladorRentas() {
        GestorRentas temp = null;
        try {
            temp = new GestorRentas();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        this.gestorRentas = temp;

        this.generadorReportes = new GeneradorReportes();
    }

    public int rentarPeliculaYRetornarID(Cliente cliente, Inventario inventario, Personal personal) {
        return gestorRentas.crearRenta(
                inventario.getIdRegistro(),
                cliente.getIdRegistro(),
                personal.getIdRegistro()
        );
    }

    public boolean devolverPelicula(int idRenta) {
        return gestorRentas.marcarRetorno(idRenta);
    }

    public boolean eliminarRenta(int idRenta) {
        return gestorRentas.delete(idRenta);
    }

    public boolean generarReporteInventario() {
        List<String[]> datos = gestorRentas.obtenerDatosReporteInventario();
        String encabezados = "ID_INVENTARIO,TITULO_PELICULA,ID_TIENDA";
        String nombreArchivo = "ReporteInventarioSakila.csv";
        return generadorReportes.generarCSV(nombreArchivo, encabezados, datos);
    }
}


