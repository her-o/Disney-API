package arg.hero.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arg.hero.challenge.service.CharacterService;
import arg.hero.challenge.model.Character;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@PostMapping
	public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
		return new ResponseEntity<Character>(service.addCharacter(character),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Character>> findAllCharacters() {
		return new ResponseEntity<List<Character>>(service.findAllCharacters(), HttpStatus.OK);
	}

}
