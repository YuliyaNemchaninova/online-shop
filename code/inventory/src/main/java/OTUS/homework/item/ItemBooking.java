package OTUS.homework.item;

import jakarta.persistence.*;


public class ItemBooking {

    private Long id;
    private Integer orderQuantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemBooking() {
    }

    public ItemBooking(Long id, Integer orderQuantity) {
        this.id = id;
        this.orderQuantity = orderQuantity;


    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

}
