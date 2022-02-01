package arg.hero.challenge.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.model.Character;
import arg.hero.challenge.model.dto.CharacterRequestDto;
import arg.hero.challenge.model.dto.CharacterResponseDto;
import arg.hero.challenge.service.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
@Api(tags = "Characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@PostMapping("/characters")
	@ApiOperation(value = "Adds a new character to the database",
					notes = "Provide the information to add a new character to the database.\n"
							+ "If you pass a movie in the 'movies' value that does not exist in the database yet it will create it.")
	public CharacterResponseDto addCharacter(@RequestBody CharacterRequestDto characterRequestDto) {					
		return service.addCharacter(characterRequestDto);
	}
	
	@GetMapping("/characters")
	@ApiOperation(value = "Retrieves a list of characters from the database",
	notes = "Search and filters the database for a list of characters with the specific request.\n"
			+ "Multiple parameters are not allowed.\n"
			+ "If no parameters are indicated the method will retrieve all the characters present in the database.")
	public List<CharacterResponseDto> findCharacters(@ApiParam(value = "Indicate the name of the character or characters you want to retrieve.")  
														@RequestParam(required = false) String name,
														@ApiParam(value = "Indicate the age of the character or characters you want to retrieve.")
														@RequestParam(required = false) String age,
														@ApiParam(value = "Indicate the ID of the movie you want to get the characters from.")
														@RequestParam(required = false) String movieId,
														HttpServletResponse response) {
		
		 return service.evaluateParamsAndReturnListOfCharacters(name, age, movieId);

	}
		
	
	
	@PutMapping("/characters/{characterId}")
	@ApiOperation(value = "Updates a character present in the the database",
	notes = "Search a specific character in the database based on a given ID and update with the request body information.")
	public CharacterResponseDto updateCharacterByName(@ApiParam("Enter the ID of the character you want to update.")
														 @PathVariable("characterId") @Parameter Long characterId,
														 @RequestBody CharacterRequestDto characterRequestDto) {
		
		return service.updateCharacterById(characterId, characterRequestDto);
	}
	
	
	@DeleteMapping("/characters/{characterId}")
	@ApiOperation(value = "Deletes a character present in the database",
	notes = "Search a specific character in the database based on a given ID and delete it.")
	public Character deleteCharacterByName(@ApiParam("Enter the ID of the character you want to delete.")
										   @PathVariable("characterId") Long characterId) {
		return service.deleteCharacterById(characterId);
	}
	
}
