package arg.hero.challenge.dto;

import java.util.Set;

import arg.hero.challenge.model.Movie;

public class CharacterDtoWithDetails {
	
	private String name;
	private String imageUrl;
	private CharacterDetails characterDetails;
	
	public CharacterDtoWithDetails() {
		// TODO Auto-generated constructor stub
	}

	public CharacterDtoWithDetails(String name, String imageUrl, CharacterDetails characterDetails) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.characterDetails = characterDetails;
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
	
	public void buildDetails(int age, String weight, String background, Set<Movie> movies) {
		this.characterDetails = new CharacterDetails(age, weight, background, movies);
	}
	
}

class CharacterDetails {
	private int age;
	private String weight;
	private String background;
	private Set<Movie> movies;
	
	public CharacterDetails() {
		// TODO Auto-generated constructor stub
	}

	public CharacterDetails(int age, String weight, String background, Set<Movie> movies) {
		super();
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
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
