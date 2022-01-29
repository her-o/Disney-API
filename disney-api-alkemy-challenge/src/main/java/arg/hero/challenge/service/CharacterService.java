package arg.hero.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arg.hero.challenge.repository.CharacterRepository;
import arg.hero.challenge.model.Character;


@Service
public class CharacterService  {
	
	@Autowired
	private CharacterRepository repository;

	public Character addCharacter(Character character) {
		return repository.save(character);
	}

	public List<Character> findAllCharacters() {
		return repository.findAll();
	}

}
