<!DOCTYPE html>
<html>
<head>
	<script src="js/script.js"></script>
	<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>
<nav>
	<p>
		<a href="home.html" class="home-link">
			Movie Cruiser
			<img src="images/logo.jpg" alt="logo">
		</a>
		<a href="home.html" class="admin-logout-link">Logout</a>
		<a href="ShowMovieListAdmin" class="admin-movies-link">Menu</a>
	</p>
</nav>
<section>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<h2>Movies</h2>
	<c:if test="${removeMovieStatus}">
		<p class="notification">${removedMovieName} removed from list successfully.</p>
	</c:if>
	<table>
		<tr>
			<th>Title</th>
			<th>Box Office</th>
			<th>Active</th>
			<th>Date of Launch</th>
			<th>Genre</th>
			<th>Has Teaser</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${movieList}" var="movie">
			<tr>
				<td><c:out value="${movie.title}"/></td>
				<td><fmt:formatNumber value="${movie.boxOffice}" type="currency" currencySymbol="$" maxFractionDigits='0'/></td>
				<td><c:if test="${movie.active}">Yes</c:if><c:if test="${!movie.active}">No</c:if></td>
				<td><fmt:formatDate value="${movie.dateOfLaunch}" pattern="dd/MM/yyyy"/></td>	
				<td><c:out value="${movie.genre}"/></td>		
				<td><c:if test="${movie.hasTeaser}">Yes</c:if><c:if test="${!movie.hasTeaser}">No</c:if></td>		
				<td><a href="ShowEditMovie?movieId=<c:out value='${movie.id}'/>">Edit</a> | <a href="DeleteMovie?movieId=<c:out value='${movie.id}'/>">Delete</a></td>	
			</tr>
		</c:forEach>
	</table>
	<p><a href="add-movie.html"><button>Add New Movie</button></a></p>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>