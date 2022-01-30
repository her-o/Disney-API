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

import arg.hero.challenge.model.Character;
import arg.hero.challenge.service.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import arg.hero.challenge.dto.CharacterDto;
import arg.hero.challenge.dto.CharacterDtoWithDetails;

@RestController
@RequestMapping("/api")
@Api(tags = "Characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@PostMapping("/characters")
	public CharacterDtoWithDetails addCharacter(@RequestBody CharacterDto characterDto) {					
		return service.addCharacter(characterDto);
	}
	
	@GetMapping("/characters")
	public List<CharacterDtoWithDetails> findAllCharacters(@RequestParam(required = false) String name,
														   @RequestParam(required = false) String age,
														   @RequestParam(required = false) String movieId) {
		return service.evaluateParamsAndReturnListOfCharacters(name, age, movieId);
	}
	
	
	@PutMapping("/characters/{characterName}")
	public CharacterDtoWithDetails updateCharacterByName( @PathVariable("characterName") @Parameter String characterName,
														  @RequestBody CharacterDto characterDto) {
		
		return service.updateCharacterByName(characterName, characterDto);
	}
	
	
	@DeleteMapping("/characters/{characterName}")
	public Character deleteCharacterByName(@PathVariable("characterName") String characterName) {
		return service.deleteCharacterByName(characterName);
	}

}
