package arg.hero.challenge.model.dto;

import java.util.Set;


import arg.hero.challenge.model.Movie;

public class CharacterResponseDto {
	
	private Long id;
	private String name;
	private String imageUrl;
	private CharacterDetails characterDetails;
	
	public CharacterResponseDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CharacterResponseDto(Long id, String name, String imageUrl, CharacterDetails characterDetails) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.characterDetails = characterDetails;
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

	public CharacterDetails getCharacterDetails() {
		return characterDetails;
	}

	public void setCharacterDetails(CharacterDetails characterDetails) {
		this.characterDetails = characterDetails;
	}

	public CharacterDetails buildDetails(int age, int weight, String background, Set<Movie> movies) {
		return this.characterDetails = new CharacterDetails(age, weight, background, movies);
	}
}

class CharacterDetails {
	
	private int age;
	private int weight;
	private String background;
	private Set<Movie> movies;
	
	public CharacterDetails() {
		// TODO Auto-generated constructor stub
	}

	public CharacterDetails(int age, int weight, String background, Set<Movie> movies) {
		this.age = age;
		this.weight = weight;
		this.background = background;
		this.movies = movies;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

}


