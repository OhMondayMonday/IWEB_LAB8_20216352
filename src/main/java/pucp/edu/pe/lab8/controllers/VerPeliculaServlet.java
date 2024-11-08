package pucp.edu.pe.lab8.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pucp.edu.pe.lab8.dao.PeliculaDAO;
import pucp.edu.pe.lab8.models.Pelicula;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verPelicula")
public class VerPeliculaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPelicula;
        try {
            idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de película inválido.");
            return;
        }

        PeliculaDAO peliculaDAO = new PeliculaDAO();
        try {
            Pelicula pelicula = peliculaDAO.getPeliculaById(idPelicula);
            if (pelicula != null) {
                request.setAttribute("pelicula", pelicula);
                request.getRequestDispatcher("/verPelicula.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Película no encontrada.");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al obtener los detalles de la película", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        String titulo = request.getParameter("titulo");
        String director = request.getParameter("director");
        int anoPublicacion = Integer.parseInt(request.getParameter("anoPublicacion"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        double boxOffice = Double.parseDouble(request.getParameter("boxOffice"));

        Pelicula pelicula = new Pelicula();
        pelicula.setIdPelicula(idPelicula);
        pelicula.setTitulo(titulo);
        pelicula.setDirector(director);
        pelicula.setAnoPublicacion(anoPublicacion);
        pelicula.setRating(rating);
        pelicula.setBoxOffice(boxOffice);

        PeliculaDAO peliculaDAO = new PeliculaDAO();
        try {
            peliculaDAO.actualizarPelicula(pelicula);
            response.sendRedirect("peliculas");
        } catch (SQLException e) {
            throw new ServletException("Error al guardar la película", e);
        }
    }
}

