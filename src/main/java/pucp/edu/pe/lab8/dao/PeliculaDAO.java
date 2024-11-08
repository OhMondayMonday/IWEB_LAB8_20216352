package pucp.edu.pe.lab8.dao;

import pucp.edu.pe.lab8.models.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO extends BaseDAO {

    public List<Pelicula> obtenerPeliculas() throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT p.*, g.nombre AS generoNombre FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setDirector(resultSet.getString("director"));
                pelicula.setAnoPublicacion(resultSet.getInt("anoPublicacion"));
                pelicula.setRating(resultSet.getDouble("rating"));
                pelicula.setBoxOffice(resultSet.getDouble("boxOffice"));
                pelicula.setIdGenero(resultSet.getInt("idGenero")); // Relación con la tabla Genero
                pelicula.setGeneroNombre(resultSet.getString("generoNombre")); // Nombre del género
                peliculas.add(pelicula);
            }
        }
        return peliculas;
    }

    public List<Pelicula> buscarPeliculas(String criterio) throws SQLException {
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT p.*, g.nombre AS generoNombre FROM Pelicula p " +
                "JOIN Genero g ON p.idGenero = g.idGenero WHERE p.titulo LIKE ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + criterio + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                    pelicula.setTitulo(resultSet.getString("titulo"));
                    pelicula.setDirector(resultSet.getString("director"));
                    pelicula.setAnoPublicacion(resultSet.getInt("anoPublicacion"));
                    pelicula.setRating(resultSet.getDouble("rating"));
                    pelicula.setBoxOffice(resultSet.getDouble("boxOffice"));
                    pelicula.setIdGenero(resultSet.getInt("idGenero")); // Relación con la tabla Genero
                    pelicula.setGeneroNombre(resultSet.getString("generoNombre")); // Nombre del género
                    peliculas.add(pelicula);
                }
            }
        }
        return peliculas;
    }

    public void eliminarPelicula(int idPelicula) throws SQLException {
        String deleteProtagonistasQuery = "DELETE FROM Protagonistas WHERE idPelicula = ?";
        try (Connection connection = getConnection();
             PreparedStatement deleteProtagonistasStmt = connection.prepareStatement(deleteProtagonistasQuery)) {
            deleteProtagonistasStmt.setInt(1, idPelicula);
            deleteProtagonistasStmt.executeUpdate();
        }

        String deletePeliculaQuery = "DELETE FROM Pelicula WHERE idPelicula = ?";
        try (Connection connection = getConnection();
             PreparedStatement deletePeliculaStmt = connection.prepareStatement(deletePeliculaQuery)) {
            deletePeliculaStmt.setInt(1, idPelicula);
            deletePeliculaStmt.executeUpdate();
        }
    }


    public Pelicula getPeliculaById(int idPelicula) throws SQLException {
        String query = "SELECT * FROM Pelicula WHERE idPelicula = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPelicula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                    pelicula.setTitulo(resultSet.getString("titulo"));
                    pelicula.setDirector(resultSet.getString("director"));
                    pelicula.setAnoPublicacion(resultSet.getInt("anoPublicacion"));
                    pelicula.setRating(resultSet.getDouble("rating"));
                    pelicula.setBoxOffice(resultSet.getDouble("boxOffice"));
                    pelicula.setIdGenero(resultSet.getInt("idGenero"));
                    return pelicula;
                }
            }
        }
        return null;
    }

    public void actualizarPelicula(Pelicula pelicula) throws SQLException {
        String query = "UPDATE Pelicula SET titulo = ?, director = ?, anoPublicacion = ?, rating = ?, boxOffice = ? WHERE idPelicula = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getDirector());
            statement.setInt(3, pelicula.getAnoPublicacion());
            statement.setDouble(4, pelicula.getRating());
            statement.setDouble(5, pelicula.getBoxOffice());
            statement.setInt(6, pelicula.getIdPelicula());
            statement.executeUpdate();
        }
    }

}


