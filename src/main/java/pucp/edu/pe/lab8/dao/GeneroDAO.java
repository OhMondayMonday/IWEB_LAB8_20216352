package pucp.edu.pe.lab8.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pucp.edu.pe.lab8.models.Genero;

public class GeneroDAO extends BaseDAO {

    public List<Genero> getAllGeneros() throws SQLException {
        List<Genero> generos = new ArrayList<>();
        String query = "SELECT * FROM Genero";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(resultSet.getInt("idGenero"));
                genero.setNombre(resultSet.getString("nombre"));
                generos.add(genero);
            }
        }
        return generos;
    }
}
