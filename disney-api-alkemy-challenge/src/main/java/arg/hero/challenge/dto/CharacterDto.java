package arg.hero.challenge.dto;

import org.springframework.web.multipart.MultipartFile;

public class CharacterDto {
	
	private String name;
	private String imageUrl;
	private int age;
	private String weight;
	private String background;
	private String[] movies;
	
	public CharacterDto() {
		// TODO Auto-generated constructor stub
	}

	public CharacterDto(String name, String imageUrl, int age, String weight, String background,
			String[] movies) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.age = age;
		this.weight = weight;
		this.background = background;
		this.movies = movies;
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

	public String[] getMovies() {
		return movies;
	}

	public void setMovies(String[] movies) {
		this.movies = movies;
	}
	
}
