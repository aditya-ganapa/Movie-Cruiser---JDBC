package com.cognizant.moviecruiser.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.moviecruiser.dao.MovieDao;
import com.cognizant.moviecruiser.dao.MovieDaoSqlImpl;

@WebServlet("/DeleteMovie")
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDao movieDao = new MovieDaoSqlImpl();
		long movieId = Long.parseLong(request.getParameter("movieId"));
		request.setAttribute("removedMovieName", movieDao.getMovie(movieId).getTitle());
		movieDao.removeMovie(movieId);
		request.setAttribute("removeMovieStatus", true);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowMovieListAdmin");
		requestDispatcher.forward(request, response);
	}
}