package arg.hero.challenge.service;

import java.util.ArrayList;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import arg.hero.challenge.model.Genre;
import arg.hero.challenge.model.Movie;
import arg.hero.challenge.model.dto.MovieRequestDto;
import arg.hero.challenge.model.dto.MovieResponseDto;
import arg.hero.challenge.repository.MovieRepository;


@Service
public class MovieService {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	private MovieRepository repository;
	@Autowired
	private SetService setService;
	
	
	public MovieResponseDto addMovie(MovieRequestDto movieRequestDto) {
		
		Movie movie = new Movie();
		repository.save(convertFromRequestDtoToMovie(movie, movieRequestDto));
		return convertFromMovieToResponseDto(movie);
	
	}

	public List<MovieResponseDto> findAllMovies() {
		return repository.findAll()
						 .stream()
						 .map(movie -> convertFromMovieToResponseDto(movie))
						 .collect(Collectors.toList());
	}
	
	public List<MovieResponseDto> findMovieByName(String name) {
		List<Movie> listOfMovies = repository.findByNameContaining(name);
		
		return listOfMovies.stream()
							.map(movie -> convertFromMovieToResponseDto(movie))
							.collect(Collectors.toList());
	}
	
	public List<MovieResponseDto> findMovieByGenreId(Long genreId) {
		List<Movie> movies = new ArrayList<Movie>();
		for(Movie m: movies) {
			for(Genre g: m.getGenres()) {
				if(g.getId() == genreId) {
					movies.add(m);
				}
			}
		}
		return movies.stream()
					 .map(movie -> convertFromMovieToResponseDto(movie))
					 .collect(Collectors.toList());
				
	}
	
	public List<MovieResponseDto> findAllMoviesAsc() {
		// TODO Auto-generated method stub
		return repository.findAllByOrderByNameAsc()
							 .stream()
							 .map(movie -> convertFromMovieToResponseDto(movie))
							 .collect(Collectors.toList());
	}
	
	public List<MovieResponseDto> findAllMoviesDesc() {
		return repository.findAllByOrderByNameDesc()
							 .stream()
							 .map(movie -> convertFromMovieToResponseDto(movie))
							 .collect(Collectors.toList());
	}
	
	public MovieResponseDto updateMovieById(Long movieId, MovieRequestDto movieRequestDto) {
		Movie movie = repository.findById(movieId).get();
		Movie updatedMovie = convertFromRequestDtoToMovie(movie, movieRequestDto);
		repository.save(updatedMovie);
		return convertFromMovieToResponseDto(movie);
	}
	
	public Movie deleteMovieById(Long movieId) {
		Movie movie = repository.findById(movieId).get();
		repository.delete(movie);
		return movie;
	}

	
	private Movie convertFromRequestDtoToMovie(Movie movie, MovieRequestDto movieRequestDto) {
		movie.setName(movieRequestDto.getName());
		movie.setImageUrl(movieRequestDto.getImageUrl());
		movie.setReleaseDate(movieRequestDto.getReleaseDate());
		movie.setRating(movieRequestDto.getRating());
		if(movieRequestDto.getCharacters() != null) {
			movie.setCharacters(setService.createCharacterSet(movie, movieRequestDto.getCharacters()));
		}
		if(movieRequestDto.getGenres() != null) {
			movie.setGenres(setService.createGenreSet(movie, movieRequestDto.getGenres()));
			logger.info("Created genre set");

		} else {
			logger.error("genres are null");
		}
		
		return movie;
	}

	private MovieResponseDto convertFromMovieToResponseDto(Movie movie) {	
		
		MovieResponseDto responseDto = new MovieResponseDto();
		responseDto.setId(movie.getId());
		responseDto.setName(movie.getName());
		responseDto.setImageUrl(movie.getImageUrl());
		responseDto.setReleaseDate(movie.getReleaseDate());
		responseDto.buildDetails(movie.getRating(), 
								 movie.getCharacters(),
								 movie.getGenres());
		
		return responseDto;
	}

	public List<MovieResponseDto> evaluateParamsAndReturnListOfMovies(String name, String genreId, String order) {
		
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