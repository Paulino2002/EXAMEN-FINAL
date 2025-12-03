package com.sakila.models;

import java.sql.Timestamp;

public class Renta {
    private int idRegistro;
    private Inventario inventario;
    private Cliente cliente;
    private Personal staff;
    private Timestamp fechaRenta;
    private Timestamp fechaRetorno;

    // Constructor para crear una nueva renta
    public Renta(Inventario inventario, Cliente cliente, Personal staff) {
        this.inventario = inventario;
        this.cliente = cliente;
        this.staff = staff;
        this.fechaRenta = new Timestamp(System.currentTimeMillis());
    }

    // Constructor para mapear desde DB
    public Renta(Inventario inventario, Cliente cliente, Personal staff, Timestamp fechaRenta, Timestamp fechaRetorno, int idRegistro) {
        this.inventario = inventario;
        this.cliente = cliente;
        this.staff = staff;
        this.fechaRenta = fechaRenta;
        this.fechaRetorno = fechaRetorno;
        this.idRegistro = idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }
    
    public int getIdRegistro() { return idRegistro; }
    public Inventario getInventario() { return inventario; }
    public Cliente getCliente() { return cliente; }
    public Personal getStaff() { return staff; }
    public Timestamp getFechaRenta() { return fechaRenta; }
    public Timestamp getFechaRetorno() { return fechaRetorno; }
    public void setFechaRetorno(Timestamp fechaRetorno) { this.fechaRetorno = fechaRetorno; }
    

}

