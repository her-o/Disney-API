package arg.hero.challenge.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="genres")
public class Genre {
	
	private Long id;
	private String name;
	private String image;
	private Set<Movie> movies;
	
	
	public Genre() {
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Set<Movie> getMovies() {
		return movies;
	}


	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
	
}
