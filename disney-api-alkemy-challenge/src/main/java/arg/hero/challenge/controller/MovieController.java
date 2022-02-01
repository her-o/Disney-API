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

import arg.hero.challenge.model.Movie;
import arg.hero.challenge.model.dto.MovieRequestDto;
import arg.hero.challenge.model.dto.MovieResponseDto;
import arg.hero.challenge.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
@Api(tags = "Movies")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@PostMapping("/movies")
	@ApiOperation(value = "Adds a new movie to the database",
	notes = "Provide the information to add a new movie to the database.\n"
			+ "If you pass a character in the 'characters' value that does not exist in the database yet it will create it.\n"
			+ "Also if you pass a genre in the 'genre' value that does not exists in the database either, it will create it.")
	public MovieResponseDto addMovie(@RequestBody() MovieRequestDto movieRequestDto) {
		return service.addMovie(movieRequestDto);
	}
	
	@GetMapping("/movies")
	@ApiOperation(value = "Retrieves a list of movies from the database",
	notes = "Search and filters the database for a list of movies with the specific request.\n"
			+ "Multiple parameters are not allowed.\n"
			+ "If no parameters are indicated the method will retrieve all the movies present in the database.")
	public List<MovieResponseDto> findMovies(@ApiParam(value = "Indicate the name of the movie or movies you want to retrieve.")  
												@RequestParam(required=false) String name,
												@ApiParam(value = "Indicate the ID of the genre to which belongs the movie or movies you want to retrieve.")
												@RequestParam(required=false) String genreId,
												@ApiParam(value = "Indicate the alphabetical order in which you want to retrieve the movies. Enter 'ASC' for ascending order or 'DESC' for descending order."
														+ "Any other input will be rejected.")
												@RequestParam(required=false) String order) {
			
		return service.evaluateParamsAndReturnListOfMovies(name, genreId, order);

	}
	
	@PutMapping("/movies/{movieId}")
	@ApiOperation(value = "Updates a movie present in the database",
	notes = "Search a specific character in the database based on a given ID and update with the request body information.")
	public MovieResponseDto updateMovieById(@ApiParam("Enter the ID of the movie you want to update.")
												 @PathVariable Long movieId,
												 @RequestBody MovieRequestDto movieRequestDto) {
		return service.updateMovieById(movieId, movieRequestDto);
	}
	
	@DeleteMapping("/movies/{movieId}")
	@ApiOperation(value = "Deletes a movie present in the database",
	notes = "Search a specific movie in the database based on a given ID and delete with the request body information.")
	public Movie deleteMovieByName(@ApiParam("Enter the ID of the movie you want to delete.")
									@PathVariable Long movieId) {
		return service.deleteMovieById(movieId);
	}
	

	
	
	

}
