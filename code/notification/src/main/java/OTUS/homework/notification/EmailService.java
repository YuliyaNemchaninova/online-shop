package OTUS.homework.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmailService {
    private final EmailRepository emailRepository;
    @Autowired
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();

    }

    public void sendEmail(Email email) {
        email.setDatetime(String.valueOf(System.currentTimeMillis()));
        emailRepository.save(email);
        System.out.println(email);
    }

}

