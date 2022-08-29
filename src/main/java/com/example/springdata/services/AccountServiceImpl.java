package com.example.springdata.services;

import com.example.springdata.models.Account;
import com.example.springdata.models.User;
import com.example.springdata.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService{


    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Long registerAccountForUser(User user, BigDecimal money) {
        Account account = new Account(money);
        account.setUser(user);
        this.accountRepository.save(account);
        return account.getId();

    }

    @Override
    public Account getInfoAboutAccountById(Long id) {
        return this.accountRepository.findAccountById(id);
    }

    @Override
    public void withdrawnMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository
                .findById(id)
                .orElseThrow();

        if (account.getBalance().compareTo(money) < 0) {
            System.out.println("Lack of balance");
        }

        account.setBalance(account.getBalance().subtract(money));
        this.accountRepository.save(account);

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        User user = accountRepository.findAccountById(id).getUser();

        Account account = this.accountRepository.findById(id).orElseThrow();

        account.setBalance(account.getBalance().add(money));
        this.accountRepository.save(account);

    }
}
