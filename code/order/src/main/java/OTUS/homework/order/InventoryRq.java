package OTUS.homework.order;

public class InventoryRq {

    private Long id;
    private Integer orderQuantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }



    public InventoryRq() {
    }

    public InventoryRq(Long id, Integer orderQuantity) {
        this.id = id;
        this.orderQuantity = orderQuantity;
    }
}
