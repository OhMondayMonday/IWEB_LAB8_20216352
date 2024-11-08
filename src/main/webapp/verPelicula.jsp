<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Película Numero ${pelicula.idPelicula}</title>
</head>
<body>
<h1>Película Numero ${pelicula.idPelicula}</h1>

<form action="verPelicula" method="post">
    <input type="hidden" name="idPelicula" value="${pelicula.idPelicula}">

    <table border="1">
        <tr>
            <td><strong>Título</strong></td>
            <td><input type="text" name="titulo" value="${pelicula.titulo}"></td>
        </tr>
        <tr>
            <td><strong>Director</strong></td>
            <td><input type="text" name="director" value="${pelicula.director}"></td>
        </tr>
        <tr>
            <td><strong>Año Publicación</strong></td>
            <td><input type="number" name="anoPublicacion" value="${pelicula.anoPublicacion}"></td>
        </tr>
        <tr>
            <td><strong>Rating</strong></td>
            <td><input type="text" name="rating" value="${pelicula.rating}"></td>
        </tr>
        <tr>
            <td><strong>BoxOffice</strong></td>
            <td><input type="text" name="boxOffice" value="${pelicula.boxOffice}"></td>
        </tr>
        <tr>
            <td><strong>Actores</strong></td>
            <td><a href="actorServlet?idPelicula=${pelicula.idPelicula}">Ver Actores</a></td>
        </tr>
    </table>

    <button type="submit">Guardar Película</button>
</form>
</body>
</html>




