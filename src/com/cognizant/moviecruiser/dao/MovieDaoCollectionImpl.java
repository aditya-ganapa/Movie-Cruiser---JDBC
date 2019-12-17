package com.cognizant.moviecruiser.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.util.DateUtil;

public class MovieDaoCollectionImpl implements MovieDao {
	private static List<Movie> movieList;

	public MovieDaoCollectionImpl() throws ParseException {
		if (movieList == null) {
			movieList = new ArrayList<>();
			movieList.add(new Movie(0, "Avatar", 2787965087.0, true, DateUtil.convertToDate("15/03/2017"), "Science Fiction", true));
			movieList.add(new Movie(1, "The Avengers", 1518812988, true, DateUtil.convertToDate("23/12/2017"), "Superhero", false));
			movieList.add(new Movie(2, "Titanic", 2187463944.0, true, DateUtil.convertToDate("21/08/2017"), "Romance", false));
			movieList.add(new Movie(3, "Jurassic World", 1671713208, false, DateUtil.convertToDate("02/07/2017"), "Science Fiction", true));
			movieList.add(new Movie(4, "Avengers: End Game", 2750760348.0, true, DateUtil.convertToDate("02/11/2022"), "Superhero", true));
		}
	}

	@Override
	public List<Movie> getMovieListAdmin() {
		return movieList;
	}

	@Override
	public List<Movie> getMovieListCustomer() {
		List<Movie> movieListCustomer = new ArrayList<>();
		for (Movie movie : movieList)
			if ((movie.getDateOfLaunch().compareTo(new Date()) <= 0) && movie.isActive())
				movieListCustomer.add(movie);
		return movieListCustomer;
	}

	@Override
	public void modifyMovie(Movie movie) {
		for (int i = 0; i < movieList.size(); i++)
			if (movieList.get(i).getId() == movie.getId()) {
				movieList.set(i, movie);
				break;
			}
	}

	@Override
	public Movie getMovie(long movieId) {
		for (Movie movie : movieList)
			if (movie.getId() == movieId)
				return movie;
		return null;
	}

	@Override
	public void addMovie(Movie movie) {
		movieList.add(movie);
	}

	@Override
	public void removeMovie(long movieId) {
		for (int i = 0; i < movieList.size(); i++)
			if (movieList.get(i).getId() == movieId)
				movieList.remove(i);
	}
}