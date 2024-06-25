package OTUS.homework.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/accounts")
    public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public Account addAccount(
            @RequestBody Account account) {
        accountService.addNewAccount(account);
        return account;
    }

    @PostMapping(path = "/transactions/byClientId")
    public Account createTransactionByClientId(
            @RequestBody Transaction transaction) {
        try {
            accountService.changeBalance(transaction.getClientId(), transaction.getSum());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return accountService.getAccountByClientId(transaction.getClientId());
    }


    @GetMapping(path = "/byClientId/{clientId}")
    public Account getAccount(
            @PathVariable("clientId")Long clientId) {
        return accountService.getAccountByClientId(clientId);
    }
    }