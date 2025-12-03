package com.sakila.data;

import com.sakila.models.Pelicula;
import com.sakila.models.Idioma;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestor de Peliculas con implementaciones insert/update/delete/get.
 */
public class GestorPeliculas extends ContextoDatos<Pelicula> {

    private static final Logger LOGGER = Logger.getLogger(GestorPeliculas.class.getName());

    public GestorPeliculas() throws SQLException {
        super();
    }

    @Override
    protected String getTableName() { return "film"; }

    @Override
    protected String getPrimaryKeyName() { return "film_id"; }

    @Override
    protected PreparedStatement prepareInsertStatement(Connection c, Pelicula p) throws SQLException {
        String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getTitulo());
        ps.setString(2, p.getDescripcion());
        if (p.getAnoLanzamiento() != null) ps.setInt(3, p.getAnoLanzamiento()); else ps.setNull(3, Types.INTEGER);
        ps.setInt(4, p.getIdioma() != null ? p.getIdioma().getIdRegistro() : 0);
        ps.setInt(5, p.getDuracion() != null ? p.getDuracion() : 3);
        ps.setDouble(6, p.getCostoRenta() != null ? p.getCostoRenta() : 4.99);
        ps.setNull(7, Types.INTEGER); // length
        ps.setDouble(8, 19.99); // replacement_cost default
        ps.setString(9, "G");
        ps.setNull(10, Types.VARCHAR);
        return ps;
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Connection c, Pelicula p) throws SQLException {
        String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=?, last_update=CURRENT_TIMESTAMP WHERE film_id=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, p.getTitulo());
        ps.setString(2, p.getDescripcion());
        if (p.getAnoLanzamiento() != null) ps.setInt(3, p.getAnoLanzamiento()); else ps.setNull(3, Types.INTEGER);
        ps.setInt(4, p.getIdioma() != null ? p.getIdioma().getIdRegistro() : 0);
        ps.setInt(5, p.getDuracion() != null ? p.getDuracion() : 3);
        ps.setDouble(6, p.getCostoRenta() != null ? p.getCostoRenta() : 4.99);
        ps.setNull(7, Types.INTEGER);
        ps.setDouble(8, 19.99);
        ps.setString(9, "G");
        ps.setNull(10, Types.VARCHAR);
        ps.setInt(11, p.getIdRegistro());
        return ps;
    }

    @Override
    protected Pelicula mapResultSetToEntity(ResultSet rs) throws SQLException {
        Idioma idioma = null;
        int langId = rs.getInt("language_id");
        if (!rs.wasNull()) idioma = new Idioma(langId, null, null);
        return new Pelicula(
            rs.getInt("film_id"),
            rs.getString("title"),
            rs.getInt("release_year"),
            idioma,
            rs.getInt("rental_duration"),
            rs.getDouble("rental_rate"),
            rs.getString("description"),
            rs.getTimestamp("last_update")
        );
    }

    // Métodos adicionales específicos
    public ArrayList<Pelicula> buscarPorTitulo(String titulo) {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT f.*, l.name AS language_name "
                   + "FROM film f "
                   + "LEFT JOIN language l ON f.language_id = l.language_id "
                   + "WHERE f.title LIKE ? LIMIT 50";

        // Abrimos una nueva conexión cada vez
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + titulo + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Idioma idioma = new Idioma(
                            rs.getInt("language_id"),
                            rs.getString("language_name"),
                            rs.getTimestamp("last_update")
                    );

                    Pelicula p = new Pelicula(
                            rs.getInt("film_id"),
                            rs.getString("title"),
                            rs.getInt("release_year"),
                            idioma,
                            rs.getInt("rental_duration"),
                            rs.getDouble("rental_rate"),
                            rs.getString("description"),
                            rs.getTimestamp("last_update")
                    );

                    peliculas.add(p);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar por título", e);
        }

        return peliculas;
    }
}
