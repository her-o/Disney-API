package arg.hero.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arg.hero.challenge.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	
	Optional<Genre> findByName(String name);
	boolean existsByName(String name);

}
