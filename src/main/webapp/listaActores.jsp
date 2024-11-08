<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="pucp.edu.pe.lab8.models.Actor" %>
<html>
<head>
    <title><%= request.getAttribute("nombrePelicula") %></title>
</head>
<body>
<h1><%= request.getAttribute("nombrePelicula") %></h1>

<table border="1">
    <tr>
        <th>idActor</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Año Nacimiento</th>
        <th>Ganador Premio Oscar</th>
    </tr>
    <%
        // Obtener la lista de actores desde el atributo de la solicitud
        List<Actor> actores = (List<Actor>) request.getAttribute("actores");
        if (actores != null && !actores.isEmpty()) {
            for (Actor actor : actores) {
    %>
    <tr>
        <td><%= actor.getIdActor() %></td>
        <td><%= actor.getNombre() %></td>
        <td><%= actor.getApellido() %></td>
        <td><%= actor.getAnoNacimiento() %></td>
        <td><%= actor.isPremioOscar() ? "Sí" : "No" %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">No se encontraron actores para esta película.</td>
    </tr>
    <%
        }
    %>
</table>

<br>
<button onclick="location.href='crearActor?idPelicula=<%= request.getAttribute("idPelicula") %>'">Crear Actor</button>
</body>
</html>




