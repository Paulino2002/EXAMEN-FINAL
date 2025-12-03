package com.sakila.data;
import java.util.ArrayList;
import com.sakila.models.Pelicula;
import com.sakila.models.Idioma;

import java.sql.*;

public class GestorPeliculas extends ContextoDatos<Pelicula> {

    @Override
    protected String getTableName() {
        return "film";
    }

    @Override
    protected String getPrimaryKeyName() {
        return "film_id";
    }

    @Override
    protected Pelicula mapResultSetToEntity(ResultSet rs) throws SQLException {

        return new Pelicula(
                rs.getInt("film_id"),
                rs.getString("title"),
                rs.getInt("release_year"),
                new Idioma(rs.getInt("language_id")),
                rs.getInt("length"),
                rs.getDouble("rental_rate"),
                rs.getString("description"),
                rs.getTimestamp("last_update")
        );
    }

    @Override
    protected PreparedStatement prepareInsertStatement(Connection c, Pelicula p, int autoKeys) throws SQLException {
        String sql = "INSERT INTO film (title, release_year, language_id, length, rental_rate, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        // Usamos el parámetro autoKeys
        PreparedStatement ps = c.prepareStatement(sql, autoKeys);

        ps.setString(1, p.getTitulo());
        ps.setObject(2, p.getAnoLanzamiento());
        ps.setInt(3, p.getIdioma().getIdRegistro());
        ps.setObject(4, p.getDuracion());
        ps.setObject(5, p.getCostoRenta());
        ps.setString(6, p.getDescripcion());

        return ps;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Connection c, Pelicula p) throws SQLException {

        String sql = "UPDATE film SET title=?, release_year=?, language_id=?, length=?, rental_rate=?, description=? " +
                     "WHERE film_id=?";

        PreparedStatement ps = c.prepareStatement(sql);

        ps.setString(1, p.getTitulo());
        ps.setObject(2, p.getAnoLanzamiento());
        ps.setInt(3, p.getIdioma().getIdRegistro());
        ps.setObject(4, p.getDuracion());
        ps.setObject(5, p.getCostoRenta());
        ps.setString(6, p.getDescripcion());
        ps.setInt(7, p.getIdRegistro());

        return ps;
    }
    
    public ArrayList<Pelicula> buscarPorTitulo(String titulo) {
        ArrayList<Pelicula> lista = new ArrayList<>();

        String sql = "SELECT * FROM film WHERE title LIKE ? LIMIT 50";

        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC",
                    "root",
                    "Erika19"
            );

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + titulo + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapResultSetToEntity(rs));
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
