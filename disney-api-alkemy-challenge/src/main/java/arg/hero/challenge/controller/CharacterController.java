package arg.hero.challenge.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.service.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import arg.hero.challenge.dto.CharacterDto;
import arg.hero.challenge.dto.CharacterDtoWithDetails;

@RestController
@RequestMapping("/api/")
@Api(tags = "Characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@PostMapping("/characters")
	public ResponseEntity<CharacterDtoWithDetails> addCharacter(@RequestBody CharacterDto characterDto) {
												
		return new ResponseEntity<CharacterDtoWithDetails>(service.addCharacter(characterDto),HttpStatus.OK);
	}
	
	@GetMapping("/characters")
	public ResponseEntity<List<CharacterDtoWithDetails>> findAllCharacters() {
		return new ResponseEntity<List<CharacterDtoWithDetails>>(service.findAllCharacters(), HttpStatus.OK);
	}
	
	@GetMapping("/characters?name")
	public ResponseEntity<CharacterDtoWithDetails> findCharacterByName(@RequestParam(value = "name") String characterName) {
		return new ResponseEntity<CharacterDtoWithDetails>(service.findCharacterByName(characterName), HttpStatus.OK);
	}
	
	@GetMapping("/characters?age")
	public ResponseEntity<List<CharacterDtoWithDetails>> findCharacterByAge(@RequestParam("age") int characterAge) {
		return new ResponseEntity<List<CharacterDtoWithDetails>>(service.findCharacterByAge(characterAge), HttpStatus.OK);
	}
	
	@GetMapping("/characters?movie")
	public ResponseEntity<List<CharacterDtoWithDetails>> findCharacterByMovieId(@RequestParam("movie") int movieId) {
		return new ResponseEntity<List<CharacterDtoWithDetails>>(service.findCharacterByMovieId(movieId), HttpStatus.OK);
	}
	
	
	@PutMapping("characters/{characterName}")
	public ResponseEntity<CharacterDtoWithDetails> updateCharacterByName( @PathVariable("characterName") @Parameter String characterName,
															  @RequestBody CharacterDto characterDto) {
		
		return new ResponseEntity<CharacterDtoWithDetails>(service.updateCharacterByName(characterName, characterDto), HttpStatus.OK);
	}
	
	
	@DeleteMapping("characters/{characterName}")
	public ResponseEntity<?> deleteCharacterByName(@PathVariable("characterName") String characterName) {
		service.deleteCharacterByName(characterName);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
