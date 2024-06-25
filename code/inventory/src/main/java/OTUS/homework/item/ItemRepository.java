package OTUS.homework.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository
        extends JpaRepository<Item, Long> {

//    @Query("SELECT s FROM Person s WHERE s.name = ?1")
//    Optional<Person> findByName(String name);
}
