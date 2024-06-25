package OTUS.homework.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/persons")
    public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public List<Person> getAllUsers() {
        return personService.getAllPersons();
    }

    @PostMapping
    public void addNewPerson(
            @RequestHeader("X-UserId")String authPersonId,
            @RequestBody Person person) {
        person.setId(Long.valueOf(authPersonId));
        personService.addNewPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(
            @RequestHeader("X-UserId")String authPersonId,
            @PathVariable("personId") Long personId){
        if (Objects.equals(authPersonId, personId.toString())) {
            personService.deletePerson(personId);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Forbidden for the user with id = "+authPersonId);
        }

    }

    @PutMapping(path = "{personId}")
    public void updatePerson (
            @RequestHeader("X-UserId")String authPersonId,
            @PathVariable("personId")Long personId,
            @RequestBody Person person
    ) {
        if (Objects.equals(authPersonId, personId.toString())) {
            personService.updatePerson(personId, person.getName());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Forbidden for the user with id = "+authPersonId);
        }
    }
    @GetMapping(path = "{personId}")
    public Person getPerson(
            @RequestHeader("X-UserId")String authPersonId,
            @PathVariable("personId")Long personId) {
        if (Objects.equals(authPersonId, personId.toString())) {
            return personService.getPersonById(personId);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Forbidden for the user with id = "+authPersonId);
        }
    }
}