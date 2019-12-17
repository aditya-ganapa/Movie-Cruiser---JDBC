package com.cognizant.moviecruiser.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.moviecruiser.model.Movie;

public class MovieDaoSqlImpl implements MovieDao {
	@Override
	public List<Movie> getMovieListAdmin() {
		Connection connection = ConnectionHandler.getConnection();
		List<Movie> movieListAdmin = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from movie");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Movie movie = new Movie(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
				movieListAdmin.add(movie);
			}
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
		return movieListAdmin;
	}

	@Override
	public List<Movie> getMovieListCustomer() {
		Connection connection = ConnectionHandler.getConnection();
		List<Movie> movieListCustomer = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from movie where mv_active='Yes' and datediff(curdate(), mv_date_of_launch)>=0");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Movie movie = new Movie(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
				movieListCustomer.add(movie);
			}
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
		return movieListCustomer;
	}

	@Override
	public void modifyMovie(Movie movie) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("update movie set mv_title=?, mv_box_office=?, mv_active=?, mv_date_of_launch=?, mv_genre=?, mv_has_teaser=? where mv_id=?");
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setDouble(2, movie.getBoxOffice());
			preparedStatement.setString(3, movie.isActive() ? "Yes" : "No");
			preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(movie.getDateOfLaunch()));
			preparedStatement.setString(5, movie.getGenre());
			preparedStatement.setString(6, movie.isHasTeaser() ? "Yes": "No");
			preparedStatement.setInt(7, (int) movie.getId());
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

	@Override
	public Movie getMovie(long movieId) {
		Connection connection = ConnectionHandler.getConnection();
		Movie movie = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from movie where mv_id=?");
			preparedStatement.setInt(1, (int) movieId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			movie = new Movie(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4).equals("Yes"), new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7).equals("Yes"));
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
		return movie;
	}

	@Override
	public void addMovie(Movie movie) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into movie values(?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, (int) movie.getId());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setInt(3, (int) movie.getBoxOffice());
			preparedStatement.setString(4, movie.isActive() ? "Yes" : "No");
			preparedStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(movie.getDateOfLaunch()));
			preparedStatement.setString(6, movie.getGenre());
			preparedStatement.setString(7, movie.isHasTeaser() ? "Yes" : "No");
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

	@Override
	public void removeMovie(long movieId) {
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from favorites where fv_mv_id=?");
			preparedStatement.setInt(1, (int) movieId);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement("delete from movie where mv_id=?");
			preparedStatement.setInt(1, (int) movieId);
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