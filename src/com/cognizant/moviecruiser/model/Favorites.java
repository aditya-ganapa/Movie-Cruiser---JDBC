package com.cognizant.moviecruiser.model;

import java.util.List;

public class Favorites {
	private List<Movie> movieList;
	private int numberOfFavorites;
	
	public Favorites() {
		super();
	}
	
	public Favorites(List<Movie> movieList, int numberOfFavorites) {
		super();
		this.movieList = movieList;
		this.numberOfFavorites = numberOfFavorites;
	}
	
	public List<Movie> getMovieList() {
		return movieList;
	}
	
	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}
	
	public int getNumberOfFavorites() {
		return numberOfFavorites;
	}
	
	public void setNumberOfFavorites(int numberOfFavorites) {
		this.numberOfFavorites = numberOfFavorites;
	}
	
	@Override
	public String toString() {
		String str="\nFavorite movies are:";
		for (Movie movie : movieList)
			str = str.concat("\n" + movie.toString());
		str = str.concat("\nnumberOfFavorites=" + numberOfFavorites);
		return str;
	}
}