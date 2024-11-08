package pucp.edu.pe.lab8.dao;

import pucp.edu.pe.lab8.models.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO extends BaseDAO {

    public List<Actor> getActorsByPelicula(int idPelicula) throws SQLException {
        List<Actor> actores = new ArrayList<>();
        String query = "SELECT a.idActor, a.nombre, a.apellido, a.anoNacimiento, a.premioOscar " +
                "FROM Actor a JOIN Protagonistas p ON a.idActor = p.idActor " +
                "WHERE p.idPelicula = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPelicula);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Actor actor = new Actor();
                    actor.setIdActor(resultSet.getInt("idActor"));
                    actor.setNombre(resultSet.getString("nombre"));
                    actor.setApellido(resultSet.getString("apellido"));
                    actor.setAnoNacimiento(resultSet.getInt("anoNacimiento"));
                    actor.setPremioOscar(resultSet.getBoolean("premioOscar"));
                    actores.add(actor);
                }
            }
        }
        return actores;
    }


    public void createActor(Actor actor) throws SQLException {
        String query = "INSERT INTO Actor (Nombre, Apellido, anoNacimiento, premioOscar) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, actor.getNombre());
            statement.setString(2, actor.getApellido());
            statement.setInt(3, actor.getAnoNacimiento());
            statement.setBoolean(4, actor.isPremioOscar());
            statement.executeUpdate();
        }
    }

    public String getNombrePeliculaById(int idPelicula) throws SQLException {
        String nombrePelicula = "";
        String query = "SELECT titulo FROM Pelicula WHERE idPelicula = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPelicula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nombrePelicula = resultSet.getString("titulo");
                }
            }
        }
        return nombrePelicula;
    }
}
