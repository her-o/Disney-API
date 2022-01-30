package arg.hero.challenge.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arg.hero.challenge.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	Optional<Movie> findByName(String name);
	boolean existsByName(String name);
	
	List<Movie> findByGenreIdLike(Long genreId);
	
	List<Movie> findAllByOrderByNameAsc();
	List<Movie> findAllByOrderByNameDesc();
	Optional<Movie> deleteByName(String name);

}
