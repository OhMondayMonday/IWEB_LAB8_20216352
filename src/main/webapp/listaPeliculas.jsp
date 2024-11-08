<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="pucp.edu.pe.lab8.models.Pelicula" %>
<html>
<head>
    <title>Lista de películas</title>
</head>
<body>
<h1>Lista de películas</h1>

<form method="get" action="peliculas">
    <input type="text" name="criterio" placeholder="Buscar película..." value="<%= request.getParameter("criterio") != null ? request.getParameter("criterio") : "" %>">
    <button type="submit">Buscar</button>
</form>

<table border="1">
    <tr>
        <th>Título</th>
        <th>Director</th>
        <th>Año Publicación</th>
        <th>Rating</th>
        <th>BoxOffice</th>
        <th>Género</th>
        <th>Actores</th>
        <th>Accionable</th>
    </tr>

    <%
        List<pucp.edu.pe.lab8.models.Pelicula> peliculas = (List<pucp.edu.pe.lab8.models.Pelicula>) request.getAttribute("peliculas");
        if (peliculas != null) {
            for (pucp.edu.pe.lab8.models.Pelicula pelicula : peliculas) {
    %>
    <tr>
        <td><a href="verPelicula?idPelicula=<%= pelicula.getIdPelicula() %>"><%= pelicula.getTitulo() %></a></td>
        <td><%= pelicula.getDirector() %></td>
        <td><%= pelicula.getAnoPublicacion() %></td>
        <td><%= String.format("%.1f", pelicula.getRating()) %>/10</td> <!-- Formato de 1 decimal para Rating -->
        <td>$ <%= String.format("%.2f", pelicula.getBoxOffice()) %></td> <!-- Formato de 2 decimales para BoxOffice -->
        <td><%= pelicula.getGeneroNombre() %></td> <!-- Nombre del género -->
        <td><a href="actorServlet?idPelicula=<%= pelicula.getIdPelicula() %>">Ver actores</a></td>
        <td><a href="peliculas?action=eliminar&idPelicula=<%= pelicula.getIdPelicula() %>" onclick="return confirm('¿Estás seguro de que deseas eliminar esta película?');">Eliminar</a></td> <!-- Enlace modificado -->
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="8">No se encontraron películas.</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>




