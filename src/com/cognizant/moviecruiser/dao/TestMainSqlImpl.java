package com.cognizant.moviecruiser.dao;

import java.text.ParseException;
import java.util.Scanner;

import com.cognizant.moviecruiser.model.Movie;
import com.cognizant.moviecruiser.util.DateUtil;

public class TestMainSqlImpl {
	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		MovieDao movieDao = new MovieDaoSqlImpl();
		FavoritesDao favoritesDao = new FavoritesDaoSqlImpl();
		while (true) {
			System.out.print("\n1.Admin 2.Customer\nEnter User Type:");
			switch (scanner.nextInt()) {
			case 1:
				boolean admin = true;
				while (admin) {
					System.out.print("\n1.View Movie List\n2.Edit Movie Details\n3.View Details of Particular Movie\n4.Add Movie\n5.Remove Movie\n6.Change User Type\nEnter Choice:");
					switch (scanner.nextInt()) {
					case 1:
						System.out.println("\nMovies:");
						for (Movie movie : movieDao.getMovieListAdmin())
							System.out.println(movie);
						break;
					case 2:
						System.out.print("Enter id of movie to be edited:");
						long id = scanner.nextLong();
						System.out.print("Enter title:");
						scanner.nextLine();
						String title = scanner.nextLine();
						System.out.print("Enter box office:");
						double boxOffice = scanner.nextDouble();
						System.out.print("Enter active status:");
						boolean active = scanner.nextBoolean();
						System.out.print("Enter date of launch:");
						scanner.nextLine();
						String dateOfLaunch = scanner.nextLine();
						System.out.print("Enter genre:");
						String genre = scanner.nextLine();
						System.out.print("Enter teaser status:");
						boolean hasTeaser = scanner.nextBoolean();
						movieDao.modifyMovie(new Movie(id, title, boxOffice, active, DateUtil.convertToDate(dateOfLaunch), genre, hasTeaser));
						System.out.println("\nMovie details edited succesfully.");
						break;
					case 3:
						System.out.print("Enter id of movie to be viewed:");
						System.out.println("\n"+movieDao.getMovie(scanner.nextLong()));
						break;
					case 4:
						System.out.print("Enter id of movie to be added:");
						long id1 = scanner.nextLong();
						System.out.print("Enter title:");
						scanner.nextLine();
						String title1 = scanner.nextLine();
						System.out.print("Enter boxOffice:");
						double boxOffice1 = scanner.nextDouble();
						System.out.print("Enter active status:");
						boolean active1 = scanner.nextBoolean();
						System.out.print("Enter date of launch:");
						scanner.nextLine();
						String dateOfLaunch1 = scanner.nextLine();
						System.out.print("Enter genre:");
						String genre1 = scanner.nextLine();
						System.out.print("Enter teaser status:");
						boolean hasTeaser1 = scanner.nextBoolean();
						movieDao.addMovie(new Movie(id1, title1, boxOffice1, active1, DateUtil.convertToDate(dateOfLaunch1), genre1, hasTeaser1));
						System.out.println("\nMovie added to list succesfully.");
						break;
					case 5:
						System.out.print("Enter id of movie to be removed:");
						movieDao.removeMovie(scanner.nextLong());
						System.out.println("\nMovie removed from list succesfully.");
						break;
					case 6:
						admin = false;
						break;
					default:
						System.out.println("\nInvalid Input");
						break;
					}
				}
				break;
			case 2:
				boolean customer = true;
				while (customer) {
					System.out.print("\n1.View Movie List\n2.Add Movie to Favorites\n3.View Favorite Movies\n4.Remove Movie from Favorites\n5.Change User Type\nEnter Choice:");
					switch (scanner.nextInt()) {
					case 1:
						System.out.println("\nMovies:");
						for (Movie movie : movieDao.getMovieListCustomer())
							System.out.println(movie);
						break;
					case 2:
						System.out.print("Enter user id:");
						long userId = scanner.nextLong();
						System.out.print("Enter id of movie to be added:");
						long movieId = scanner.nextLong();
						favoritesDao.addFavoriteMovie(userId, movieId);
						System.out.println("\nMovie added to favorites succesfully.");
						break;
					case 3:
						System.out.print("Enter user id:");
						long userId1 = scanner.nextLong();
						try {
							System.out.println(favoritesDao.getAllFavoriteMovies(userId1));
						} catch (Exception e) {
							System.out.println("\nFavorites is empty.");
						}
						break;
					case 4:
						System.out.print("Enter user id:");
						long userId11 = scanner.nextLong();
						System.out.print("Enter id of movie to be removed:");
						long movieId1 = scanner.nextLong();
						favoritesDao.removeFavoriteMovie(userId11, movieId1);
						System.out.println("\nMovie removed from favorites succesfully.");
						break;
					case 5:
						customer = false;
						break;
					default:
						System.out.println("\nInvalid Input");
						break;
					}
				}
				break;
			default:
				System.out.println("\nInvalid Input");
				break;
			}
		}
	}
}