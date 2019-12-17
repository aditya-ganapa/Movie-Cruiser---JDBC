package com.cognizant.moviecruiser.dao;

import java.text.ParseException;

import com.cognizant.moviecruiser.model.Favorites;

public interface FavoritesDao {
	public void addFavoriteMovie(long userId, long movieId) throws ParseException;
	public Favorites getAllFavoriteMovies(long userId) throws FavoritesEmptyException;
	public void removeFavoriteMovie(long userId, long movieId);
}