package com.example.springdata;
import com.example.springdata.models.Account;
import com.example.springdata.models.User;
import com.example.springdata.services.AccountService;
import com.example.springdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {


        User user = new User("Neo",21);

        userService.registerUser(user);
        long accountId = accountService.registerAccountForUser(user, BigDecimal.valueOf(30000));

        accountService.withdrawnMoney(new BigDecimal("20000"),accountId);
        accountService.transferMoney(new BigDecimal("30000"),accountId);
        Account account = accountService.getInfoAboutAccountById(accountId);
        System.out.println(account.getBalance());
    }
}
