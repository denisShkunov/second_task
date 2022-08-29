package com.example.springdata.services;

import com.example.springdata.models.Account;
import com.example.springdata.models.User;

import java.math.BigDecimal;

public interface AccountService {
    Long registerAccountForUser(User user, BigDecimal money);
    Account getInfoAboutAccountById(Long id);
    void withdrawnMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal money, Long id);
}
