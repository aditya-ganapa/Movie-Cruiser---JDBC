package com.cognizant.moviecruiser.servlet;

import java.io.IOException;
import java.util.List;

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
import com.cognizant.moviecruiser.model.Movie;

@WebServlet("/ShowMovieListCustomer")
public class ShowMovieListCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDao movieDao = new MovieDaoSqlImpl();
		List<Movie> movieList = movieDao.getMovieListCustomer();
		request.setAttribute("movieList", movieList);
		long userId = 1;
		long favoritesSize = 0;
		boolean empty = false;
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		try {
			favoritesSize = favoritesDao.getAllFavoriteMovies(userId).getNumberOfFavorites();
		} catch (FavoritesEmptyException | NullPointerException e) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("movie-list-customer.jsp");
			requestDispatcher.forward(request, response);
			empty = true;
		}
		if(!empty) {
			request.setAttribute("favoritesNotEmpty", true);
			request.setAttribute("favoritesSize", favoritesSize);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("movie-list-customer.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}