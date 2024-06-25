package OTUS.homework.account;

import jakarta.persistence.*;

@Entity
@Table
public class Account {
    @Column(unique=true)
    private Long clientId;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    @SequenceGenerator(
            name = "account_sequence",
//            sequenceName = "account_sequence",
            allocationSize = 1,
            initialValue = 1000000000
    )

    private Long accountNumber;
    private float balance;

    public Account() {
    }

    public Account(Long clientId, Long accountNumber, float balance) {
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(Long clientId, float balance) {
        this.clientId = clientId;
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
