package arg.hero.challenge.model.dto;

public class GenreRequestDto {
	
	private String name;
	private String imageUrl;
	
	public GenreRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public GenreRequestDto(String name, String imageUrl) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
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

}
