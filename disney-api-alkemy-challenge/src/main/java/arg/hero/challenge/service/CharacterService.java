package arg.hero.challenge.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arg.hero.challenge.repository.CharacterRepository;
import arg.hero.challenge.dto.CharacterDto;
import arg.hero.challenge.dto.CharacterDtoWithDetails;
import arg.hero.challenge.model.Character;
import arg.hero.challenge.model.Movie;


@Service
public class CharacterService  {
	
	
	@Autowired
	private CharacterRepository repository;
	@Autowired 
	private MovieService movieService;

	public CharacterDtoWithDetails addCharacter(CharacterDto characterDto) {
		Character character = convertFromDtoToCharacter(characterDto);
		repository.save(character);
		return convertFromCharacterToDtoWithDetails(character);
	}


	public List<CharacterDtoWithDetails> findAllCharacters() {
		return repository.findAll()
						 .stream()
						 .map(character -> convertFromCharacterToDtoWithDetails(character))
											.collect(Collectors.toList());
	}
	
	public CharacterDtoWithDetails findCharacterByName(String characterName) {
		Character character = repository.findByName(characterName).get();
		return convertFromCharacterToDtoWithDetails(character);
	}
	
	public List<CharacterDtoWithDetails> findCharacterByAge(int characterAge) {
		return repository.findByAge(characterAge)
						 .stream()
						 .map(character -> convertFromCharacterToDtoWithDetails(character))														  
														.collect(Collectors.toList());
	}
	
	public List<CharacterDtoWithDetails> findCharacterByMovieId(int movieId) {
		List<Character> characters = repository.findAll();
		List<CharacterDtoWithDetails> charactersOfTheMovie = new ArrayList<CharacterDtoWithDetails>();
		for(Character c:characters) {
			for(Movie m:c.getMovies()) {
				if(m.getId() == movieId) {
					charactersOfTheMovie.add(convertFromCharacterToDtoWithDetails(c));
				}
			}
		}
		return charactersOfTheMovie;
	}
	
	public CharacterDtoWithDetails updateCharacterByName(String characterName, CharacterDto characterDto) {
		Character updatedCharacter = repository.findByName(characterName).get();
		updatedCharacter.setName(characterDto.getName());
		updatedCharacter.setImageUrl(characterDto.getImageUrl());
		updatedCharacter.setAge(characterDto.getAge());
		updatedCharacter.setWeight(characterDto.getWeight());
		updatedCharacter.setBackground(characterDto.getBackground());
		updatedCharacter.setMovies(findMovieNamesOrCreateIfNonExistant(characterDto.getMovies()));
		repository.save(updatedCharacter);
		return convertFromCharacterToDtoWithDetails(updatedCharacter);
	}


	public void deleteCharacterByName(String name) {
		repository.deleteByName(name);
	}
	
	private Character convertFromDtoToCharacter(CharacterDto characterDto) {
		Character character = new Character();
		character.setName(characterDto.getName());
		character.setImageUrl(characterDto.getImageUrl());
		character.setAge(characterDto.getAge());
		character.setWeight(characterDto.getWeight());
		character.setBackground(characterDto.getBackground());
		character.setMovies(findMovieNamesOrCreateIfNonExistant(characterDto.getMovies()));
	
		return character;
	}
	
	private CharacterDtoWithDetails convertFromCharacterToDtoWithDetails(Character character) {
		CharacterDtoWithDetails dtoWithDetails = new CharacterDtoWithDetails();
		dtoWithDetails.setName(character.getName());
		dtoWithDetails.setImageUrl(character.getImageUrl());
		dtoWithDetails.buildDetails(character.getAge(), 
									character.getWeight(), 
									character.getBackground(), 
									character.getMovies());
		
		return dtoWithDetails;
	}

	public Set<Movie> findMovieNamesOrCreateIfNonExistant(String[] movies) {
		
		Set<Movie> setOfMovies;
		
 		if(movies == null) {
			setOfMovies = new HashSet<Movie>();
		} else {
			setOfMovies = movieService.generateSetOfMoviesFromString(movies);
		}
		return setOfMovies;
	}


}
