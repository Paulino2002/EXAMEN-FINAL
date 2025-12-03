package com.sakila.data;

import com.sakila.models.Renta;
import com.sakila.models.Inventario;
import com.sakila.models.Cliente;
import com.sakila.models.Personal;

import java.sql.*;
import java.util.ArrayList;

public class GestorRentas extends ContextoDatos<Renta> {

    public GestorRentas() throws SQLException {
        super();
    }

    @Override
    protected String getTableName() { return "rental"; }

    @Override
    protected String getPrimaryKeyName() { return "rental_id"; }

    @Override
    protected PreparedStatement prepareInsertStatement(Connection c, Renta r) throws SQLException {

        String sql = "INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update) " +
                     "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setTimestamp(1, r.getFechaRenta());
        ps.setInt(2, r.getInventario().getIdRegistro());
        ps.setInt(3, r.getCliente().getIdRegistro());

        // 🔹 Evita error si está en null
        if (r.getFechaRetorno() != null)
            ps.setTimestamp(4, r.getFechaRetorno());
        else
            ps.setNull(4, Types.TIMESTAMP);

        ps.setInt(5, r.getStaff().getIdRegistro());

        return ps;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Connection c, Renta r) throws SQLException {

        String sql = "UPDATE rental SET rental_date=?, inventory_id=?, customer_id=?, return_date=?, staff_id=?, " +
                     "last_update=CURRENT_TIMESTAMP WHERE rental_id=?";

        PreparedStatement ps = c.prepareStatement(sql);

        ps.setTimestamp(1, r.getFechaRenta());
        ps.setInt(2, r.getInventario().getIdRegistro());
        ps.setInt(3, r.getCliente().getIdRegistro());

        if (r.getFechaRetorno() != null)
            ps.setTimestamp(4, r.getFechaRetorno());
        else
            ps.setNull(4, Types.TIMESTAMP);

        ps.setInt(5, r.getStaff().getIdRegistro());
        ps.setInt(6, r.getIdRegistro());

        return ps;
    }


    @Override
    protected Renta mapResultSetToEntity(ResultSet rs) throws SQLException {

        return new Renta(
        		new Inventario(rs.getInt("inventory_id")),
        		new Cliente(rs.getInt("customer_id")),
        		new Personal(rs.getInt("staff_id")),
                rs.getTimestamp("rental_date"),
                rs.getTimestamp("return_date"),
                rs.getInt("rental_id")
        );
    }

    // METODOS PERSONALIZADOS

    public int crearRenta(int inventoryId, int customerId, int staffId) {

        Renta r = new Renta(
                new Inventario(inventoryId),
                new Cliente(customerId),
                new Personal(staffId)
        );

        boolean exito = post(r);

        if (exito)
            return r.getIdRegistro();  // ID ya asignado en ContextoDatos

        return -1;
    }

    public boolean marcarRetorno(int idRenta) {

        Renta r = get(idRenta);

        if (r != null) {
            r.setFechaRetorno(new Timestamp(System.currentTimeMillis()));
            return put(r);
        }

        return false;
    }

    public ArrayList<String[]> obtenerDatosReporteInventario() {

        ArrayList<String[]> lista = new ArrayList<>();

        String sql = "SELECT inventory_id, film.title AS title, store_id " +
                     "FROM inventory JOIN film ON inventory.film_id = film.film_id " +
                     "LIMIT 1000";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                        String.valueOf(rs.getInt("inventory_id")),
                        rs.getString("title"),
                        String.valueOf(rs.getInt("store_id"))
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}


