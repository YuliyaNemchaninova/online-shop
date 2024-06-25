package OTUS.homework.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;
@Configuration
public class PersonConfig {
    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
return args -> {
//    Person julia = new Person(
//            "Julia");
//    Person jack = new Person(
//            "Jack");
    Person person = new Person((long) 1L, UUID.randomUUID().toString().replaceAll("-", "")
            );
//    repository.saveAll(List.of(julia, jack,person)
    repository.saveAll(List.of(person)
);
};
    }
}
