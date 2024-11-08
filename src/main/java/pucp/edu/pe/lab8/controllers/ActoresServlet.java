package pucp.edu.pe.lab8.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pucp.edu.pe.lab8.dao.ActorDAO;
import pucp.edu.pe.lab8.models.Actor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/actorServlet")
public class ActoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPelicula;
        try {
            idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de película inválido.");
            return;
        }

        ActorDAO actorDAO = new ActorDAO();
        try {
            List<Actor> actores = actorDAO.getActorsByPelicula(idPelicula);
            String nombrePelicula = actorDAO.getNombrePeliculaById(idPelicula);

            System.out.println("Nombre de la película: " + nombrePelicula);
            for (Actor actor : actores) {
                System.out.println("Actor: " + actor.getIdActor() + ", " + actor.getNombre() + ", " + actor.getApellido() +
                        ", " + actor.getAnoNacimiento() + ", " + actor.isPremioOscar());
            }

            request.setAttribute("actores", actores);
            request.setAttribute("nombrePelicula", nombrePelicula);
            request.setAttribute("idPelicula", idPelicula);
            request.getRequestDispatcher("/listaActores.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener los actores de la película", e);
        }
    }
}






