package OTUS.homework.order;

import jakarta.persistence.*;

@Entity
@Table(name="zakaz")
public class Order {
    @Id
//    @SequenceGenerator(
//            name = "person_sequence",
//            sequenceName = "person_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "person_sequence"
//    )
    private Long orderId;
    private Long clientId;
    private Long itemId;
    private Integer orderQuantity;
    private Float totalSum;
    private String status;

    public Order() {
    }

    public Order(Long orderId, Long clientId, Long itemId, Integer orderQuantity, Float totalSum) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.totalSum = totalSum;
        this.clientId = clientId;
    }
    public Order(Long orderId, Long clientId, Long itemId, Integer orderQuantity, Float totalSum, String status) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.totalSum = totalSum;
        this.clientId = clientId;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Float totalSum) {
        this.totalSum = totalSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
