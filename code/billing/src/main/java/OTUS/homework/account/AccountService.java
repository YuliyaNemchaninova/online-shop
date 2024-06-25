package OTUS.homework.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    public void addNewAccount(Account account) {

        System.out.println(account);
        accountRepository.save(account);

    }


    @Transactional
    public void changeBalance(Long clientId, float sum) {
        Account account = accountRepository.findByClientId(clientId).orElseThrow(() ->
         new IllegalStateException("an account for the client with id " + clientId + "is not found"));
        float currentBalance = account.getBalance();
        if (currentBalance >= sum) {
            account.setBalance(currentBalance - sum);
        }
        else {
            throw new IllegalStateException("Недостаточно средств. Текущий баланс= " + account.getBalance());

        }

    }

    public Account getAccountByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId).orElseThrow(() ->
                new IllegalStateException("an account for the client with id " + clientId + "is not found"));
    }
}

