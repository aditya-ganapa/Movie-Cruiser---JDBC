package com.cognizant.moviecruiser.dao;

import java.text.ParseException;

public class FavoritesDaoCollectionImplTest {
	public static void main(String[] args) throws ParseException, FavoritesEmptyException {
		testAddFavoriteMovie();
		testRemoveFavoriteMovie();
	}
	
	public static void testAddFavoriteMovie() throws ParseException, FavoritesEmptyException {
		FavoritesDao favoritesDao = new FavoritesDaoCollectionImpl();
		favoritesDao.addFavoriteMovie(1, 2);
		System.out.println(favoritesDao.getAllFavoriteMovies(1));
	}
	
	public static void testRemoveFavoriteMovie() {
		FavoritesDao favoritesDao = new FavoritesDaoCollectionImpl();
		favoritesDao.removeFavoriteMovie(1, 2);
		try {
			System.out.println(favoritesDao.getAllFavoriteMovies(1));
		} catch (FavoritesEmptyException e) {
			System.out.println("\nFavorites is empty.");
		}
	}
}