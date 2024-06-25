package OTUS.homework.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository ItemRepository) {
        this.itemRepository = ItemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    public Item geItem(Long itemId) {

        return itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalStateException("item with id " + itemId + " does not exists"));
    }

    public Item addNewItem(Item item) {
        item.setAvailable(item.getTotal());
        return itemRepository.save(item);
    }

//    public void bookItem(Long itemId, Integer orderQuantity) {
//        boolean exists = itemRepository.existsById(itemId);
//        if (!exists){
//            throw new IllegalStateException("item with id " + itemId + "does not exists");
//        }
//        else {
//
//        }
//        itemRepository.deleteById(itemId);
//    }

    @Transactional
    public Item bookItem(Long itemId, Integer orderQuantity) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
         new IllegalStateException("item with id " + itemId + " does not exists"));
        if (item.getAvailable() >= orderQuantity) {
            item.setAvailable(item.getAvailable() - orderQuantity);
            return item;
        }
        else throw new IllegalStateException("available quantity = " + item.getAvailable() + " , orderQuantity = " + orderQuantity);

    }
    @Transactional
    public Item cancelBooking(Long itemId, Integer orderQuantity) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalStateException("item with id " + itemId + " does not exists"));
            item.setAvailable(item.getAvailable() + orderQuantity);
        return item;
    }

}

