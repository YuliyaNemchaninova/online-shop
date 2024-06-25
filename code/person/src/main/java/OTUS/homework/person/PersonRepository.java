package OTUS.homework.person;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long> {

    @Query("SELECT s FROM Person s WHERE s.name = ?1")
    Optional<Person> findByName(String name);
}
