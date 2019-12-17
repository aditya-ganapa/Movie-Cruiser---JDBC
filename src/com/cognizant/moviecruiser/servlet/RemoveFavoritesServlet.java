package com.cognizant.moviecruiser.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.moviecruiser.dao.FavoritesDao;
import com.cognizant.moviecruiser.dao.FavoritesDaoSqlImpl;
import com.cognizant.moviecruiser.dao.FavoritesEmptyException;
import com.cognizant.moviecruiser.dao.MovieDao;
import com.cognizant.moviecruiser.dao.MovieDaoSqlImpl;
import com.cognizant.moviecruiser.model.Favorites;

@WebServlet("/RemoveFavorites")
public class RemoveFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDao movieDao = new MovieDaoSqlImpl();
		long movieId = Long.parseLong(request.getParameter("movieId"));
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		long userId = 1;
		favoritesDao.removeFavoriteMovie(userId, movieId);
		request.setAttribute("removeFavoritesMovieStatus", true);
		Favorites favorites = null;
		boolean empty = false;
		try {
			favorites = favoritesDao.getAllFavoriteMovies(userId);
		} catch (FavoritesEmptyException e) {
			request.setAttribute("removedFavoritesMovieName", movieDao.getMovie(movieId).getTitle());
			request.setAttribute("removeLastFavoritesMovieStatus", true);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("favorites.jsp");
			requestDispatcher.forward(request, response);
			empty = true;
		}
		if (!empty) {
			request.setAttribute("favorites", favorites);
			request.setAttribute("removedFavoritesMovieName", movieDao.getMovie(movieId).getTitle());
			request.setAttribute("favoritesNotEmpty", true);
			request.setAttribute("favoritesSize", favorites.getMovieList().size());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("favorites.jsp");
	//	try {
			requestDispatcher.forward(request, response);
	//	} catch (IllegalStateException e) {
	//	}
		}
	}
}