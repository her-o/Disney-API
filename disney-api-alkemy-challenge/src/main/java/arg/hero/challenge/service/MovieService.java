package arg.hero.challenge.service;

import java.util.ArrayList;


import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import arg.hero.challenge.model.Character;
import arg.hero.challenge.model.Genre;
import arg.hero.challenge.dto.MovieDto;
import arg.hero.challenge.dto.MovieDtoWithDetails;
import arg.hero.challenge.model.Movie;
import arg.hero.challenge.repository.CharacterRepository;
import arg.hero.challenge.repository.MovieRepository;


@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	@Autowired
	private CharacterRepository characterRepository;
	@Autowired
	private GenreService genreService;
	
	
	public MovieDtoWithDetails addMovie(MovieDto movieDto) {
		
		Movie movie = new Movie();
		repository.save(convertFromDtoToMovie(movie, movieDto));
		return convertFromMovieToDtoWithDetails(movie);
	
	}

	public List<MovieDtoWithDetails> findAllMovies() {
		return repository.findAll()
						 .stream()
						 .map(movie -> convertFromMovieToDtoWithDetails(movie))
						 .collect(Collectors.toList());
	}
	
	public List<MovieDtoWithDetails> findMovieByName(String name) {
		List<Movie> listOfMovies = repository.findByNameContaining(name);
		
		return listOfMovies.stream()
							.map(movie -> convertFromMovieToDtoWithDetails(movie))
							.collect(Collectors.toList());
	}
	
	public List<MovieDtoWithDetails> findMovieByGenreId(Long genreId) {
		return repository.findByGenreIdLike(genreId)
						 .stream()
						 .map(movie -> convertFromMovieToDtoWithDetails(movie))
						 .collect(Collectors.toList());
	}
	
	public List<MovieDtoWithDetails> findAllMoviesAsc() {
		// TODO Auto-generated method stub
		return repository.findAllByOrderByNameAsc()
							 .stream()
							 .map(movie -> convertFromMovieToDtoWithDetails(movie))
							 .collect(Collectors.toList());
	}
	
	public List<MovieDtoWithDetails> findAllMoviesDesc() {
		// TODO Auto-generated method stub
		return repository.findAllByOrderByNameDesc()
							 .stream()
							 .map(movie -> convertFromMovieToDtoWithDetails(movie))
							 .collect(Collectors.toList());
	}
	
	public MovieDtoWithDetails updateMovieByName(String name, MovieDto movieDto) {
		Movie movie = repository.findByName(name).get();
		Movie updatedMovie = convertFromDtoToMovie(movie, movieDto);
		repository.save(updatedMovie);
		return convertFromMovieToDtoWithDetails(movie);
	}
	
	public Movie deleteMovieByName(String name) {
		Movie movie = repository.findByName(name).get();
		repository.delete(movie);
		return movie;
	}

	
	private Movie convertFromDtoToMovie(Movie movie, MovieDto movieDto) {
		movie.setName(movieDto.getName());
		movie.setImageUrl(movieDto.getImageUrl());
		movie.setReleaseDate(movieDto.getReleaseDate());
		movie.setRating(movieDto.getRating());
		movie.setCharacters(findCharacterNamesOrCreateIfNonExistant(movieDto.getCharacters(), movie));
		movie.setGenre(findGenreNameOrCreateIfNonExistant(movieDto.getGenre()));
		
		return movie;
	}

	private MovieDtoWithDetails convertFromMovieToDtoWithDetails(Movie movie) {	
		
		MovieDtoWithDetails dtoWithDetails = new MovieDtoWithDetails();
		dtoWithDetails.setTitle(movie.getName());
		dtoWithDetails.setImageUrl(movie.getImageUrl());
		dtoWithDetails.setReleaseDate(movie.getReleaseDate());
		dtoWithDetails.buildDetails(movie.getRating(), 
									movie.getGenreName(), 
									getCharacterNames(movie));
		
		return dtoWithDetails;
	}


	private String[] getCharacterNames(Movie movie) {
		
		List<String> names = new ArrayList<String>();
		if(movie.getCharacters() != null) {
			for(Character c: movie.getCharacters()) {
				names.add(c.getName());
			}
		}
		
		return (String[]) names.toArray(new String[names.size()]);
	}

	public Set<Movie> generateSetOfMoviesFromString(String[] movies) {
		
		Set<Movie> setOfMovies = new HashSet<Movie>();
		
		if(movies == null) {
			return setOfMovies;
		} else {
			
				for(String movieName: movies) {
				
				if(repository.existsByName(movieName.trim())) {
					Movie existantMovie = repository.findByName(movieName.trim()).get();
					setOfMovies.add(existantMovie);
				} else {
					Movie newMovie = new Movie();
					newMovie.setName(movieName.trim());
					setOfMovies.add(newMovie);
				}
			}
		}
		 
		return setOfMovies;
	}
	
	private Set<Character> findCharacterNamesOrCreateIfNonExistant(String[] characterNames, Movie movie) {
			
		Set<Character> setOfCharacters;
				
		 if(characterNames == null) {
			 setOfCharacters = new HashSet<Character>();
				} else {
					
					setOfCharacters = generateSetOfCharactersFromStringArray(characterNames, movie);
				}
		 
				return setOfCharacters;
	}
	
	private Set<Character> generateSetOfCharactersFromStringArray(String[] characterNames, Movie movie) {
		Set<Character> characters = new HashSet<Character>();
		
		for(String name: characterNames) {
			if(characterRepository.existsByName(name.trim())) {
				Character character = characterRepository.findByName(name.trim()).get();
				characters.add(character);
				character.getMovies().add(movie);
			} else {
				Character character = new Character();
				character.setName(name.trim());
				character.getMovies().add(movie);
				characters.add(characterRepository.save(character));
			}
		}
		
		return characters;
	}

	private Genre findGenreNameOrCreateIfNonExistant(String genreName) {
		
		Genre genre;
				
		 		if(genreName == null) {
					return null;
				} else {
					genre = genreService.generateGenreFromString(genreName);
				}
				return genre;
	}

	public List<MovieDtoWithDetails> evaluateParamsAndReturnListOfMovies(String name, String genreId, String order) {
		
		String[] params = {name, genreId, order};
		int numberOfParams = 0;
		
		for(String p: params) {
			if(p != null) {
				numberOfParams++;
			}
		}
		
		if(numberOfParams > 1) {
			throw  new ResponseStatusException(
		            HttpStatus.BAD_REQUEST,"Too many params");
		} else if(name != null) {
			return findMovieByName(name);
		} else if(genreId != null) {
			return findMovieByGenreId(Long.valueOf(genreId));
		} else if(order != null && !order.toUpperCase().trim().equals("ASC") && !order.toUpperCase().trim().equals("DESC")) {
			throw  new ResponseStatusException(
		            HttpStatus.BAD_REQUEST,"Bad Request");
		} else if(order != null && order.toUpperCase().trim().equals("ASC")) {
			return findAllMoviesAsc();
		} else if(order != null && order.toUpperCase().trim().equals("DESC")) {
			return findAllMoviesDesc();
		} else {
			return findAllMovies();
		}
		
	}

	




	



}