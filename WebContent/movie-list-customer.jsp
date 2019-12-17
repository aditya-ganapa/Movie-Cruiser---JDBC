<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<a href="home.html" class="customer-logout-link">Logout</a>
		<a href="ShowFavorites" class="favorites-link">Favorites<c:if test="${favoritesNotEmpty}">[${favoritesSize}]</c:if></a>
		<a href="ShowMovieListCustomer" class="customer-movies-link">Movies</a>
	</p>
</nav>
<section>
	<h2>Movies</h2>
	<c:if test="${addFavoritesStatus}">
		<p class="notification">${addedFavoritesMovieName} added to favorites successfully.</p>
	</c:if>
	<table>
		<tr>
			<th>Title</th>
			<th>Box Office</th>
			<th>Genre</th>
			<th>Has Teaser</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${movieList}" var="movie">
			<tr>
				<td><c:out value="${movie.title}"/></td>
				<td><fmt:formatNumber value="${movie.boxOffice}" type="currency"  currencySymbol="$" maxFractionDigits='0'/></td>
				<td><c:out value="${movie.genre}"/></td>
				<td><c:if test="${movie.hasTeaser}">Yes</c:if><c:if test="${!movie.hasTeaser}">No</c:if></td>
				<td><a href="AddToFavorites?movieId=<c:out value='${movie.id}'/>">Add to Favorites</a></td>	
			</tr>
		</c:forEach>
	</table>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>