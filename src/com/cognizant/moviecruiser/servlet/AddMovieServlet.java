package com.cognizant.moviecruiser.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.moviecruiser.dao.MovieDao;
import com.cognizant.moviecruiser.dao.MovieDaoSqlImpl;
import com.cognizant.moviecruiser.model.Movie;

@WebServlet("/AddMovie")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Movie movie = new Movie();
		movie.setId(Long.parseLong(request.getParameter("id")));
		movie.setTitle(request.getParameter("title"));
		movie.setBoxOffice(Double.parseDouble(request.getParameter("boxOffice")));
		movie.setActive(request.getParameter("active").equals("yes"));
		try {
			movie.setDateOfLaunch(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dateOfLaunch")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movie.setGenre(request.getParameter("genre"));
		movie.setHasTeaser(request.getParameter("hasTeaser") != null);
		MovieDao movieDao = new MovieDaoSqlImpl();
		movieDao.addMovie(movie);;
		request.setAttribute("title", request.getParameter("title"));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("add-movie-status.jsp");
		requestDispatcher.forward(request, response);
	}
}