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
	<h2>Edit Movie</h2>
	<form name="movieForm" onsubmit="return validateMovieForm()" method="post" action="EditMovie?movieId=<c:out value='${movie.id}'/>">
		<table>
			<tr>
				<td colspan="4">
					<label for="field-title">Title</label><br>
					<input type="text" id="field-title" name="title" value="<c:out value='${movie.title}'/>">
				</td>
			</tr>
			<tr>
				<td>
					<label for="field-boxOffice">Gross ($)</label><br>
					<input type="text" id="field-boxOffice" name="boxOffice" value="<fmt:formatNumber value='${movie.boxOffice}' minFractionDigits='0' maxFractionDigits='2' groupingUsed = "false"/>">
				</td>
				<td>
					Active<br>
					<input type="radio" id="field-active-yes" name="active" value="yes" <c:if test="${movie.active}">checked</c:if>><label for="field-active-yes"> Yes </label><input type="radio" id="field-active-no" name="active" value="no" <c:if test="${!movie.active}">checked</c:if>><label for="field-active-no"> No</label>
				</td>
				<td>
					<label for="field-dateOfLaunch">Date of Launch</label><br>
					<input type="text" id="field-dateOfLaunch" name="dateOfLaunch" value="<fmt:formatDate value='${movie.dateOfLaunch}' pattern='dd/MM/yyyy'/>">
				</td>
				<td>
					<label for="field-genre">Genre</label><br>
					<select id="field-genre" name="genre">
						<option value=""></option>
						<option value="Science Fiction" ${movie.genre == "Science Fiction" ? "selected" : ""}>Science Fiction</option>
						<option value="Superhero" ${movie.genre == "Superhero" ? "selected" : ""}>Superhero</option>
						<option value="Romance" ${movie.genre == "Romance" ? "selected" : ""}>Romance</option>
						<option value="Comedy" ${movie.genre == "Comedy" ? "selected" : ""}>Comedy</option>
						<option value="Adventure" ${movie.genre == "Adventure" ? "selected" : ""}>Adventure</option>
						<option value="Thriller" ${movie.genre == "Thriller" ? "selected" : ""}>Thriller</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" id="field-hasTeaser" name="hasTeaser" value="yes" <c:if test="${movie.hasTeaser}">checked</c:if>><label for="field-hasTeaser"> Has Teaser</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="submit" value="Save">
				</td>
			</tr>
		</table>
	</form>
</section>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</body>
</html>