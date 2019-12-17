package com.cognizant.moviecruiser.dao;

import java.text.ParseException;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.util.DateUtil;

public class MovieDaoCollectionImplTest {
	public static void main(String[] args) throws ParseException {
		testGetMovieListAdmin();
		testGetMovieListCustomer();
		testModifyMovie();
	}
	
	public static void testGetMovieListAdmin() throws ParseException {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		for (Movie movie : movieDao.getMovieListAdmin())
			System.out.println(movie);
	}
	
	public static void testGetMovieListCustomer() throws ParseException {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		for (Movie movie : movieDao.getMovieListCustomer())
			System.out.println(movie);
	}
	
	public static void testModifyMovie() throws ParseException {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		movieDao.modifyMovie(new Movie(0, "Joker", 1013472389, true, DateUtil.convertToDate("25/10/2019"), "Superhero", false));
		System.out.println(movieDao.getMovie(0));
	}
}