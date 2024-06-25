package OTUS.homework.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends JpaRepository<Order, Long> {

//    @Query("SELECT s FROM Person s WHERE s.name = ?1")
//    Optional<Person> findByName(String name);
}
