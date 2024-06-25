package OTUS.homework.item;

import jakarta.persistence.*;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;
    private Integer available;
    private Integer total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    public Item() {
    }

    public Item(Long id, Integer total) {
        this.id = id;
        this.total = total;
    }
    public Item(Long id, Integer total, Integer available) {
        this.id = id;
        this.total = total;
        this.available = available;
    }

}
