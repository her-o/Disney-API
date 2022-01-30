package arg.hero.challenge.model;

import java.util.Date;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@Column(unique = true)
	private String name;
	private String imageUrl;
	@Nullable
	private Date releaseDate;
	@Enumerated
	private Rating rating;
	@JsonBackReference
	@ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Character> characters = new HashSet<Character>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="genre_id")
	private Genre genre;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public Set<Character> getCharacters() {
		return characters;
	}
	
	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}

	public String getGenreName() {
		String genreName = "";
		if(this.genre != null) {
			genreName = this.genre.getName();
		}
		return genreName;
	}
	
	
	
}
