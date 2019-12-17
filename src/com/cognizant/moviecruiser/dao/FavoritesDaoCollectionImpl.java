package com.cognizant.moviecruiser.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.moviecruiser.model.Favorites;
import com.cognizant.moviecruiser.model.Movie;

public class FavoritesDaoCollectionImpl implements FavoritesDao {
	private static HashMap<Long, Favorites> userFavorites;

	public FavoritesDaoCollectionImpl() {
		if (userFavorites == null)
			userFavorites = new HashMap<>();
	}

	@Override
	public void addFavoriteMovie(long userId, long movieId) throws ParseException {
		MovieDao movieDao = new MovieDaoCollectionImpl();
		Movie movie = movieDao.getMovie(movieId);
		if (userFavorites.containsKey(userId)) {
			List<Movie> movieList = userFavorites.get(userId).getMovieList();
			if (!movieList.contains(movie))
				movieList.add(movie);
		}
		else {
			Favorites favorites = new Favorites();
			List<Movie> movieList = new ArrayList<>();
			movieList.add(movie);
			favorites.setMovieList(movieList);
			userFavorites.put(userId, favorites);
		}
	}

	@Override
	public Favorites getAllFavoriteMovies(long userId) throws FavoritesEmptyException {
		List<Movie> movieList = userFavorites.get(userId).getMovieList();
		if (movieList.isEmpty())
			throw new FavoritesEmptyException();
		int numberOfFavorites = movieList.size();
		return new Favorites(movieList, numberOfFavorites);
	}

	@Override
	public void removeFavoriteMovie(long userId, long movieId) {
		List<Movie> movieList = userFavorites.get(userId).getMovieList();
		for (int i = 0; i < movieList.size(); i++)
			if (movieList.get(i).getId() == movieId)
				movieList.remove(i);
	}
}