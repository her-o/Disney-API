package arg.hero.challenge.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arg.hero.challenge.model.Genre;
import arg.hero.challenge.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository repository;

	public Genre findByName(String name) {
		return repository.findByName(name).get();
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

}
