package arg.hero.challenge.model;

import java.util.HashSet;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "characters")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@Column(unique=true)
	private String name;
	private String imageUrl;
	private int age;
	private String weight;
	@Lob
	private String background;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name="characters_movies",
			   joinColumns = {@JoinColumn(name="character_id")},
			   inverseJoinColumns = {@JoinColumn(name="movie_id")})
	private Set<Movie> movies = new HashSet<Movie>();
	
	
	public Character() {
	
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


	public Set<Movie> getMovies() {
		return movies;
	}


	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	

}
