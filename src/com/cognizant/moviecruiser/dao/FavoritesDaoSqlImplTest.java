package com.cognizant.moviecruiser.dao;

import java.text.ParseException;

public class FavoritesDaoSqlImplTest {
	public static void main(String[] args) throws ParseException, FavoritesEmptyException {
		testAddFavoriteMovie();
		testRemoveFavoriteMovie();
	}
	
	public static void testAddFavoriteMovie() throws ParseException, FavoritesEmptyException {
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		favoritesDao.addFavoriteMovie(1, 2);
		System.out.println(favoritesDao.getAllFavoriteMovies(1));
	}
	
	public static void testRemoveFavoriteMovie() {
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		favoritesDao.removeFavoriteMovie(1, 2);
		try {
			System.out.println(favoritesDao.getAllFavoriteMovies(1));
		} catch (FavoritesEmptyException e) {
			System.out.println("\nFavorites is empty.");
		}
	}
}