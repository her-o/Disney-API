package arg.hero.challenge.model.dto;

import java.util.Date;

import java.util.Set;

import arg.hero.challenge.model.Genre;
import arg.hero.challenge.model.Rating;
import arg.hero.challenge.model.Character;

public class MovieResponseDto {
	
	private Long id;
	private String name;
	private String imageUrl;
	private Date releaseDate;
	private MovieDetails details;
	
	public MovieResponseDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MovieResponseDto(Long id, String name, String imageUrl, Date releaseDate, MovieDetails details) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.releaseDate = releaseDate;
		this.details = details;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public MovieDetails getDetails() {
		return details;
	}

	public void setDetails(MovieDetails details) {
		this.details = details;
	}

	public void buildDetails(Rating rating, Set<Character> characters, Set<Genre> genres) {
		this.details = new MovieDetails(rating, characters, genres);
	}
	
 
}

class MovieDetails {
	
	private Rating rating;
	private Set<Character> characters;
	private Set<Genre> genres;

	public MovieDetails() {
		// TODO Auto-generated constructor stub
	}

	public MovieDetails(Rating rating, Set<Character> characters, Set<Genre> genres) {
		this.rating = rating;
		this.characters = characters;
		this.genres = genres;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	public Set<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}

	public  Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres( Set<Genre> genres) {
		this.genres = genres;
	}
	
}


