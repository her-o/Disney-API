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

import arg.hero.challenge.model.Genre;
import arg.hero.challenge.model.dto.GenreRequestDto;
import arg.hero.challenge.model.dto.GenreResponseDto;
import arg.hero.challenge.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
@Api(tags = "Genres")
public class GenreController {
	
	@Autowired
	private GenreService service;
	
	@PostMapping("/genres")
	@ApiOperation(value = "Adds a new genre to the database",
	notes = "Provide the information to add a new genre to the database.\n"
			+ "If you pass a movie in the 'movies' value that does not exist in the database yet it will create it.\n")
	public GenreResponseDto addGenre(@RequestBody GenreRequestDto genreRequestDto) {
		return service.addGenre(genreRequestDto);
	}
	
	@GetMapping("/genres")
	@ApiOperation(value = "Retrieves the list of genres from the database.\n"
			+ "If and ID is passed to the request as a parameter it will return a List of genres containing only the"
			+ "one with the specified ID")
	public List<GenreResponseDto> findAllGenres(@ApiParam(value = "Indicate the ID of the genre you want to retrieve.") 
												@RequestParam Long genreId) {
						
		return service.evaluateParamsAndReturnListOfGenres(genreId);

	}
	
	@GetMapping("/genres/{genreId}")
	@ApiOperation(value = "Retrieves a single genre from the database based on a given ID",
	notes = "Search in the database for a specific genre based on a given ID and retrieves it.")
	public List<GenreResponseDto> findGenreById(@PathVariable Long genreId) {
		return service.findGenreById(genreId);
	}
	
	@PutMapping("/genres/{genreId}")
	@ApiOperation(value = "Updates a genre present in the database",
	notes = "Search a specific genre in the database based on a given ID and update with the request body information.")
    public GenreResponseDto updateGenreById(@PathVariable Long genreId,
    										@RequestBody GenreRequestDto genreRequestDto) {
		return service.updateGenreById(genreId, genreRequestDto);
	}
	
	@DeleteMapping("/genres/{genreId}")
	@ApiOperation(value = "Deletes a genre present in the database",
	notes = "Search a specific genre in the database based on a given ID and delete with the request body information.")
	public Genre deleteGenreById(@PathVariable Long genreId) {
		return service.deleteGenreById(genreId);
	}
	

}
