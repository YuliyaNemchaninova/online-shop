package OTUS.homework.notification;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public List<Email> getAllEmails() {
        return emailService.getAllEmails();
    }

    @PostMapping
    public void addNewEmail(
            @RequestBody Email email) {
        emailService.sendEmail(email);
    }
}
