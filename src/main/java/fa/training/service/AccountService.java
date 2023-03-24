package fa.training.service;

import fa.training.entities.Account;

import java.util.List;

public interface AccountService {
    Account getAccountByEmail(String email);
    Account getAccountByAccount(String account);
}
