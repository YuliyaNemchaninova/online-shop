package OTUS.homework.person;

import OTUS.homework.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();

    }


    public void addNewPerson(Person person) {
        Optional<Person> personOptional = personRepository.
                findByName(person.getName());
        if (personOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }
        System.out.println(person);
        personRepository.save(person);
        System.out.println(person);
    }

    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if (!exists){
            throw new IllegalStateException("person with id " + personId + "does not exists");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, String name) {
        Person person = personRepository.findById(personId).orElseThrow(() ->
         new IllegalStateException("person with id " + personId + "does not exists"));
   person.setName(name);
    }

    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElseThrow(() ->
                new IllegalStateException("person with id " + personId + "does not exists"));
    }
}

