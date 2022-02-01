package arg.hero.challenge.model.dto;

import java.util.Date;

import java.util.List;

import arg.hero.challenge.model.Rating;

public class MovieRequestDto {
	
	private String name;
	private String imageUrl;
	private Date releaseDate;
	private Rating rating;
	private List<String> characters;
	private List<String> genres;
	
	public MovieRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public MovieRequestDto(String name, String imageUrl, Date releaseDate, 
							Rating rating, List<String> characters,  List<String> genres) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.characters = characters;
		this.genres = genres;
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

	public List<String> getCharacters() {
		return characters;
	}

	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}

	public  List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

}
