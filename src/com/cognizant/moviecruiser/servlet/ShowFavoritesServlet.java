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
import com.cognizant.moviecruiser.model.Favorites;

@WebServlet("/ShowFavorites")
public class ShowFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userId = 1;
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		Favorites favorites = null;
		boolean empty = false;
		try {
			favorites = favoritesDao.getAllFavoriteMovies(userId);
		} catch (FavoritesEmptyException | NullPointerException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("favorites-empty.jsp");
			requestDispatcher.forward(request, response);
			empty = true;
		}
		if(!empty) {
			request.setAttribute("favorites", favorites);
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