package arg.hero.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.dto.MovieDto;
import arg.hero.challenge.dto.MovieDtoWithDetails;
import arg.hero.challenge.model.Movie;
import arg.hero.challenge.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
@Api(tags = "Movies")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@PostMapping("/movies")
	public MovieDtoWithDetails addMovie(@RequestBody() MovieDto movieDto) {
		return service.addMovie(movieDto);
	}
	
	@GetMapping("/movies")
	public List<MovieDtoWithDetails> findMovies(@RequestParam(required=false) String name,
												@RequestParam(required=false) String genreId,
												@RequestParam(required=false) String order) {
		
		
		
		return service.evaluateParamsAndReturnListOfMovies(name, genreId, order);
	
	}
	
	@PutMapping("/movies/{movieName}")
	public MovieDtoWithDetails updateMovieByName(@PathVariable String movieName,
												 @RequestBody MovieDto movieDto) {
		return service.updateMovieByName(movieName, movieDto);
	}
	
	@DeleteMapping("/movies/{movieName}")
	public Movie deleteMovieByName(@PathVariable String movieName) {
		return service.deleteMovieByName(movieName);
	}
	

	
	
	

}
