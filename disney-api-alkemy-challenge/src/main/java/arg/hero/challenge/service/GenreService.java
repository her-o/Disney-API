package arg.hero.challenge.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arg.hero.challenge.model.Genre;
import arg.hero.challenge.model.dto.GenreRequestDto;
import arg.hero.challenge.model.dto.GenreResponseDto;
import arg.hero.challenge.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository repository;
	
	public GenreResponseDto addGenre(GenreRequestDto genreRequestDto) {
		Genre genre = new Genre();
		repository.save(convertFromRequestDtoToGenre(genre, genreRequestDto));
		return convertFromGenreToResponseDto(genre);
	}


	public List<GenreResponseDto> findAllGenres() {
		return repository.findAll().stream()
								   .map(genre -> convertFromGenreToResponseDto(genre))
								   .collect(Collectors.toList());
	}
	
	public List<GenreResponseDto> findGenreById(Long genreId) {
		List<Genre> genres = repository.findAll().stream().filter(genre -> genre.getId() == genreId).toList();
		return genres.stream()
					.map(genre -> convertFromGenreToResponseDto(genre))
					.collect(Collectors.toList());
	}

	public Genre findByName(String name) {
		return repository.findByName(name).get();
	}
	
	public GenreResponseDto updateGenreById(Long genreId, GenreRequestDto genreRequestDto) {
		Genre genre  = repository.findById(genreId).get();
		Genre updatedGenre = convertFromRequestDtoToGenre(genre, genreRequestDto);
		repository.save(updatedGenre);
		return convertFromGenreToResponseDto(updatedGenre);
	}
	
	public Genre deleteGenreById(Long genreId) {
		Genre genre = repository.findById(genreId).get();
		repository.delete(genre);
		return genre;
	}

	public Genre generateGenreFromString(String genreName) {
			
		Genre genre = new Genre();
				
		if(genreName == null) {
				return null;
		} 
			
		if(repository.existsByName(genreName.trim())) {
				genre = repository.findByName(genreName.trim()).get();
			} else {
				genre.setName(genreName);
				repository.save(genre);
						
			}
			
		return genre;
	}
	
	private Genre convertFromRequestDtoToGenre(Genre genre, GenreRequestDto genreRequestDto) {
		
		genre.setName(genreRequestDto.getName());
		genre.setImageUrl(genreRequestDto.getImageUrl());
		return genre;
	}
	
	private GenreResponseDto convertFromGenreToResponseDto(Genre genre) {
		GenreResponseDto genreResponseDto = new GenreResponseDto();
		
		genreResponseDto.setId(genre.getId());
		genreResponseDto.setName(genre.getName());
		genreResponseDto.setImageUrl(genre.getImageUrl());
		genreResponseDto.setMovies(genre.getMovies()); 
		
		return genreResponseDto;
	}


	public List<GenreResponseDto> evaluateParamsAndReturnListOfGenres(Long genreId) {
		
		if(genreId != null) {
			return findGenreById(genreId);
		} else {
			return findAllGenres();
		}
	}
}
