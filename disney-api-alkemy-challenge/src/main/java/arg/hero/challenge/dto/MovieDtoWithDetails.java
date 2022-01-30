package arg.hero.challenge.dto;

import java.util.Date;

import arg.hero.challenge.model.Rating;

public class MovieDtoWithDetails {
	
	private String name;
	private String imageUrl;
	private Date releaseDate;
	private MovieDetails details;
	
	public MovieDtoWithDetails() {
		// TODO Auto-generated constructor stub
	}

	public MovieDtoWithDetails(String name, String imageUrl, Date releaseDate) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public void setTitle(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public MovieDetails getDetails() {
		return details;
	}
	
	public void setDetails(MovieDetails details) {
		this.details = details;
	}
	
	public void buildDetails(Rating rating, String genre, String[] characters) {
		this.details = new MovieDetails(rating, genre, characters);
	}

}

class MovieDetails {
	
	private Rating rating;
	private String genre;
	private String[] characters;
	
	public MovieDetails() {
		// TODO Auto-generated constructor stub
	}

	public MovieDetails(Rating rating, String genre, String[] characters) {
		this.rating = rating;
		this.genre = genre;
		this.characters = characters;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String[] getCharacters() {
		return characters;
	}

	public void setCharacters(String[] characters) {
		this.characters = characters;
	}
	

}
