package OTUS.homework.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/items")
    public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public Item addNewItem(
            @RequestBody Item item)
    {
        return itemService.addNewItem(item);
    }
    @PostMapping(path = "/book")
    public Item bookItem(
            @RequestBody ItemBooking itemBooking) {
        return itemService.bookItem(itemBooking.getId(),
                itemBooking.getOrderQuantity());

    }

    @PostMapping(path = "/cancelBooking")
    public Item cancelBooking(
            @RequestBody ItemBooking itemBooking) {
        return itemService.cancelBooking(itemBooking.getId(),
                itemBooking.getOrderQuantity());

    }
    @GetMapping(path = "{itemId}")
    public Item getItem(
            @PathVariable("itemId")Long itemId) {

            return itemService.geItem(itemId);

       }

}