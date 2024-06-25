package OTUS.homework.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/orders")
    public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public Order addNew(
//            @RequestHeader("X-UserId")String authPersonId,
            @RequestBody Order order) throws URISyntaxException, IOException, InterruptedException {
        return orderService.addNew(order);
    }}

//
//    @DeleteMapping(path = "{personId}")
//    public void deletePerson(
//            @RequestHeader("X-UserId")String authPersonId,
//            @PathVariable("personId") Long personId){
//        if (Objects.equals(authPersonId, personId.toString())) {
//            personService.deletePerson(personId);
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.FORBIDDEN, "Forbidden for the user with id = "+authPersonId);
//        }
//
//    }
//
//    @PutMapping(path = "{personId}")
//    public void updatePerson (
//            @RequestHeader("X-UserId")String authPersonId,
//            @PathVariable("personId")Long personId,
//            @RequestBody Person person
//    ) {
//        if (Objects.equals(authPersonId, personId.toString())) {
//            personService.updatePerson(personId, person.getName());
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.FORBIDDEN, "Forbidden for the user with id = "+authPersonId);
//        }
//    }
//    @GetMapping(path = "{personId}")
//    public Person getPerson(
//            @RequestHeader("X-UserId")String authPersonId,
//            @PathVariable("personId")Long personId) {
//        if (Objects.equals(authPersonId, personId.toString())) {
//            return personService.getPersonById(personId);
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.FORBIDDEN, "Forbidden for the user with id = "+authPersonId);
//        }
//    }
//}