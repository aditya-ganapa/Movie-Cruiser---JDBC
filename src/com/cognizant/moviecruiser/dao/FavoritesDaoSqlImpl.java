package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.moviecruiser.model.Favorites;
import com.cognizant.moviecruiser.model.Movie;

public class FavoritesDaoSqlImpl implements FavoritesDao {
	@Override
	public void addFavoriteMovie(long userId, long movieId) throws ParseException {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from user where us_id=?");
			preparedStatement.setInt(1, (int) userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				preparedStatement = connection.prepareStatement("insert into user(us_id) values(?)");
				preparedStatement.setInt(1, (int) userId);
				preparedStatement.executeUpdate();
			}
			preparedStatement = connection.prepareStatement("select * from favorites where fv_us_id=? and fv_mv_id=?");
			preparedStatement.setInt(1, (int) userId);
			preparedStatement.setInt(2, (int) movieId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				preparedStatement = connection.prepareStatement("insert into favorites(fv_us_id, fv_mv_id) values(?, ?)");
				preparedStatement.setInt(1, (int) userId);
				preparedStatement.setInt(2, (int) movieId);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Favorites getAllFavoriteMovies(long userId) throws FavoritesEmptyException {
		Connection connection = ConnectionHandler.getConnection();
		List<Movie> movieList = new ArrayList<>();
		int numberOfFavorites = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from favorites inner join movie on fv_mv_id=mv_id where fv_us_id=?");
			preparedStatement.setInt(1, (int) userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next())
				throw new FavoritesEmptyException();
			Movie movie = new Movie(resultSet.getLong("mv_id"), resultSet.getString("mv_title"), resultSet.getDouble("mv_box_office"), resultSet.getString("mv_active").equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("mv_date_of_launch")), resultSet.getString("mv_genre"), resultSet.getString("mv_has_teaser").equals("Yes"));
			movieList.add(movie);
			while (resultSet.next()) {
				movie = new Movie(resultSet.getLong("mv_id"), resultSet.getString("mv_title"), resultSet.getDouble("mv_box_office"), resultSet.getString("mv_active").equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("mv_date_of_launch")), resultSet.getString("mv_genre"), resultSet.getString("mv_has_teaser").equals("Yes"));
				movieList.add(movie);
			}
			preparedStatement = connection.prepareStatement("select count(fv_id) from favorites inner join movie on fv_mv_id=mv_id where fv_us_id=?");
			preparedStatement.setInt(1, (int) userId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			numberOfFavorites = resultSet.getInt(1);
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Favorites(movieList, numberOfFavorites);
	}

	@Override
	public void removeFavoriteMovie(long userId, long movieId) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from favorites where fv_us_id=? and fv_mv_id=?");
			preparedStatement.setInt(1, (int) userId);
			preparedStatement.setInt(2, (int) movieId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}