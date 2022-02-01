package arg.hero.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import arg.hero.challenge.repository.CharacterRepository;
import arg.hero.challenge.model.Character;
import arg.hero.challenge.model.Movie;
import arg.hero.challenge.model.dto.CharacterRequestDto;
import arg.hero.challenge.model.dto.CharacterResponseDto;


@Service
public class CharacterService  {
	
	@Autowired
	private CharacterRepository repository;
	@Autowired
	private SetService setService;

	public CharacterResponseDto addCharacter(CharacterRequestDto characterRequestDto) {
		Character character = new Character();
		repository.save(convertFromRequestDtoToCharacter(character, characterRequestDto));
		return convertFromCharacterToResponseDto(character);
	}


	public List<CharacterResponseDto> findAllCharacters() {
		return repository.findAll()
						 .stream()
						 .map(character -> convertFromCharacterToResponseDto(character))
						.collect(Collectors.toList());
	}
	
	public List<CharacterResponseDto> findCharacterByName(String characterName) {
		List<Character> charactersList = repository.findByNameContaining(characterName);
		return charactersList.stream()
							 .map(character -> convertFromCharacterToResponseDto(character))
							 .collect(Collectors.toList());
				
	}
	
	public List<CharacterResponseDto> findCharacterByAge(int characterAge) {
		return repository.findByAge(characterAge)
						 .stream()
						 .map(character -> convertFromCharacterToResponseDto(character))														  
														.collect(Collectors.toList());
	}
	
	public List<CharacterResponseDto> findCharacterByMovieId(Long movieId) {
		List<Character> characters = repository.findAll();
		List<CharacterResponseDto> charactersOfTheMovie = new ArrayList<CharacterResponseDto>();
		for(Character c:characters) {
			for(Movie m:c.getMovies()) {
				if(m.getId() == movieId) {
					charactersOfTheMovie.add(convertFromCharacterToResponseDto(c));
				}
			}
		}
		return charactersOfTheMovie;
	}
	
	public CharacterResponseDto updateCharacterById(Long characterId, CharacterRequestDto characterRequestDto) {
		Character character  = repository.findById(characterId).get();
		Character updatedCharacter = convertFromRequestDtoToCharacter(character, characterRequestDto);
		repository.save(updatedCharacter);
		return convertFromCharacterToResponseDto(updatedCharacter);
	}


	public Character deleteCharacterById(Long characterId) {
		Character character = repository.findById(characterId).get();
		repository.delete(character);
		return character;
	}
	
	private Character convertFromRequestDtoToCharacter(Character character, CharacterRequestDto characterRequestDto) {
		character.setName(characterRequestDto.getName());
		character.setImageUrl(characterRequestDto.getImageUrl());
		character.setAge(characterRequestDto.getAge());
		character.setWeight(characterRequestDto.getWeight());
		character.setBackground(characterRequestDto.getBackground());
		if(characterRequestDto.getMovies() != null) {
			character.setMovies(setService.createMovieSet(characterRequestDto.getMovies()));
		}
		return character;
	}
	
	
	private CharacterResponseDto convertFromCharacterToResponseDto(Character character) {
		CharacterResponseDto characterResponseDto = new CharacterResponseDto();
		
		characterResponseDto.setId(character.getId());
		characterResponseDto.setName(character.getName());
		characterResponseDto.setImageUrl(character.getImageUrl());
		characterResponseDto.setCharacterDetails(characterResponseDto.buildDetails(character.getAge(), 
																				   character.getWeight(), 
																                   character.getBackground(), 
																                   character.getMovies())); 
		
		return characterResponseDto;
	}


	public List<CharacterResponseDto> evaluateParamsAndReturnListOfCharacters(String name, String age,
			String movieId) {
		String[] params = {name, age, movieId};
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
			return findCharacterByName(name);
		} else if(age != null) {
			return findCharacterByAge(Integer.valueOf(age));
		} else if(movieId != null ) {
			return findCharacterByMovieId(Long.valueOf(movieId));
		} else {
			return findAllCharacters();
		}
		
	}
}
