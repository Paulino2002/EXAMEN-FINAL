package com.sakila.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ContextoDatos<T> implements iDatapost<T> {

    private static final Logger LOGGER = Logger.getLogger(ContextoDatos.class.getName());

    // ESTA VARIABLE ES NECESARIA PARA LOS GESTORES
    protected Connection conexion;

    // Metodos abstractos
    protected abstract String getTableName();
    protected abstract String getPrimaryKeyName();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
    protected abstract PreparedStatement prepareInsertStatement(Connection c, T obj, int autoKeys) throws SQLException;
    protected abstract PreparedStatement prepareUpdateStatement(Connection c, T obj) throws SQLException;


    @Override
    public final boolean post(T obj) {
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "Erika19");

            
            PreparedStatement ps = prepareInsertStatement(conexion, obj, Statement.RETURN_GENERATED_KEYS);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.getClass().getMethod("setIdRegistro", int.class).invoke(obj, id);
                }
                rs.close();
            }

            ps.close();
            conexion.close();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    // ACTUALIZAR (PUT)
    @Override
    public final boolean put(T obj) {
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "Erika19");

            PreparedStatement ps = prepareUpdateStatement(conexion, obj);

            int rows = ps.executeUpdate();
            ps.close();
            conexion.close();

            return rows > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error en put: ", e);
            return false;
        }
    }


    // ELIMINAR (DELETE)
    @Override
    public final boolean delete(int idRegistro) {
        String sql = "DELETE FROM " + getTableName() +
                     " WHERE " + getPrimaryKeyName() + "=?";

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "Erika19");

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRegistro);

            int rows = ps.executeUpdate();
            ps.close();
            conexion.close();

            return rows > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error en delete: ", e);
            return false;
        }
    }


    // OBTENER POR ID
    public final T get(int idRegistro) {
        String sql = "SELECT * FROM " + getTableName() +
                     " WHERE " + getPrimaryKeyName() + "=?";

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "Erika19");

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRegistro);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                T entidad = mapResultSetToEntity(rs);
                rs.close();
                ps.close();
                conexion.close();
                return entidad;
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error en get(id): ", e);
        }

        return null;
    }


    // OBTENER TODOS
    public final ArrayList<T> get() {
        ArrayList<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName() + " LIMIT 100";

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "Erika19");

            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapResultSetToEntity(rs));
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error en get(): ", e);
        }

        return lista;
    }
}
