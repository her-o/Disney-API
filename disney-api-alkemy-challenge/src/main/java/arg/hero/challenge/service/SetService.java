package arg.hero.challenge.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arg.hero.challenge.model.Character;
import arg.hero.challenge.model.Genre;
import arg.hero.challenge.model.Movie;
import arg.hero.challenge.repository.CharacterRepository;
import arg.hero.challenge.repository.GenreRepository;
import arg.hero.challenge.repository.MovieRepository;

@Service
public class SetService {
	
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GenreRepository genreRepository;
	
	public SetService() {
		// TODO Auto-generated constructor stub
	}
	
	public Set<Character> createCharacterSet(Movie movie, List<String> characterNames) {
		
		Set<Character> characters = new HashSet<Character>();
		
		for(String name: characterNames) {
			if(characterRepository.existsByName(name)) {
				Character character = characterRepository.findByName(name).get();
				character.getMovies().add(movie);	
				characters.add(character);
			} else {
				Character character = new Character();
				character.setName(name);
				character.getMovies().add(movie);
				characters.add(characterRepository.save(character));
			}
		}
		
		return characters;
		
	}
	
	public Set<Movie> createMovieSet(String[] movieNames) {
		
		Set<Movie> movies = new HashSet<Movie>();
		 
		for(String name: movieNames) {
			if(movieRepository.existsByName(name)) {
				Movie movie = movieRepository.findByName(name).get();
				movies.add(movie);
			} else {
				Movie movie = new Movie();
				movie.setName(name);
				movies.add(movieRepository.save(movie));
			}
		}
		
		return movies;
		
	}
		
	
	public Set<Genre> createGenreSet(Movie movie, List<String> genreNames) {
		
		Set<Genre> genres = new HashSet<Genre>();
				
		for(String name: genreNames) {
			if(genreRepository.existsByName(name)) {
				Genre genre = genreRepository.findByName(name).get();
				genre.getMovies().add(movie);	
				genres.add(genre);
			} else {
				Genre genre = new Genre();
				genre.setName(name);
				genre.getMovies().add(movie);
				genres.add(genreRepository.save(genre));
			}
		}
		
		return genres;
		
	}
	
}
