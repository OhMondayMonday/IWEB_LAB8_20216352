package pucp.edu.pe.lab8.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pucp.edu.pe.lab8.dao.PeliculaDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/peliculas")
public class PeliculaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("eliminar")) {
            // Llamar al método para eliminar la película
            try {
                int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                PeliculaDAO peliculaDAO = new PeliculaDAO();
                peliculaDAO.eliminarPelicula(idPelicula);

                // Redirigir de vuelta a la lista de películas
                response.sendRedirect("peliculas");
            } catch (NumberFormatException | SQLException e) {
                throw new ServletException("Error al eliminar la película", e);
            }
        } else {
            // Lógica existente para listar películas
            PeliculaDAO peliculaDAO = new PeliculaDAO();
            try {
                request.setAttribute("peliculas", peliculaDAO.obtenerPeliculas());
                request.getRequestDispatcher("listaPeliculas.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error al obtener la lista de películas", e);
            }
        }
    }
}






