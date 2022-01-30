package arg.hero.challenge.dto;

import java.util.Date;

import arg.hero.challenge.model.Rating;

public class MovieDto {
	
	private String name;
	private String imageUrl;
	private Date releaseDate;
	private Rating rating;
	private String[] characters;
	private String genre;
	
	public MovieDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieDto(String name, String imageUrl, Date releaseDate, Rating rating, String[] characters,
			String genre) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.characters = characters;
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
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

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String[] getCharacters() {
		return characters;
	}

	public void setCharacters(String[] characters) {
		this.characters = characters;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
