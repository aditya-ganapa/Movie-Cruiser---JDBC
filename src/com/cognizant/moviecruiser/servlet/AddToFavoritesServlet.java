package com.cognizant.moviecruiser.servlet;

import java.io.IOException;
import java.text.ParseException;
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

@WebServlet("/AddToFavorites")
public class AddToFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userId = 1;
		long movieId = Long.parseLong(request.getParameter("movieId"));
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		try {
			favoritesDao.addFavoriteMovie(userId, movieId);;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MovieDao movieDao = new MovieDaoSqlImpl();
		List<Movie> movieList = movieDao.getMovieListCustomer();
		request.setAttribute("movieList",movieList);
		request.setAttribute("addFavoritesStatus", true);
		request.setAttribute("favoritesNotEmpty", true);
		request.setAttribute("addedFavoritesMovieName", movieDao.getMovie(movieId).getTitle());
		try {
			request.setAttribute("favoritesSize", favoritesDao.getAllFavoriteMovies(userId).getMovieList().size());
		} catch (FavoritesEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("movie-list-customer.jsp");
		requestDispatcher.forward(request, response);
	}
}