package OTUS.homework.notification;

import jakarta.persistence.*;

@Entity
@Table
public class Email {
    @Id

    @SequenceGenerator(
            name = "email_sequence",
            sequenceName = "email_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "email_sequence"
    )
    private Long id;
    private String message;
    private String email;
    private String datetime;


    public Email() {
    }

    public Email(Long id, String message, String email, String datetime) {
        this.id = id;
        this.message = message;
        this.email = email;
        this.datetime = datetime;
    }

//    public Email(String ) {
//        this.name = name;
//    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }



//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
