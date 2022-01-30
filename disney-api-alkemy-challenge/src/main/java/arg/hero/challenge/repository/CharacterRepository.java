package arg.hero.challenge.repository;

import arg.hero.challenge.model.Character;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

	Optional<Character> findByName(String characterName);
	List<Character> findByNameContaining(String name);
	List<Character> findByAge(Integer characterAge);
	boolean existsByName(String name);
	void deleteByName(String name);

}
