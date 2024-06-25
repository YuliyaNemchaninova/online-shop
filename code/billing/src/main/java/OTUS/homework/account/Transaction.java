package OTUS.homework.account;

import jakarta.persistence.*;

//@Entity
//@Table
public class Transaction {
//    @Id
    private Long clientId;
    private float sum;

    public Transaction() {
    }

    public Transaction(Long clientId, float sum) {
        this.clientId = clientId;
        this.sum  = sum;
    }


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
