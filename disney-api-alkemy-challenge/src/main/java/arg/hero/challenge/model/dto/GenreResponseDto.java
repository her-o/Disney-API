package arg.hero.challenge.model.dto;

import java.util.Set;


import arg.hero.challenge.model.Movie;

public class GenreResponseDto {
	
	private Long id;
	private String name;
	private String imageUrl;
	private Set<Movie> movies;
	
	public GenreResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public GenreResponseDto(Long id, String name, String imageUrl, Set<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.movies = movies;
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

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	
}
