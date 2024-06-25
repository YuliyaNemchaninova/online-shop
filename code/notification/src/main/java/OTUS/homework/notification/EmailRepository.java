package OTUS.homework.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmailRepository
        extends JpaRepository<Email, Long> {

    @Query("SELECT s FROM Email s WHERE s.email = ?1")
    Optional<Email> findByEmail(String email);
}
