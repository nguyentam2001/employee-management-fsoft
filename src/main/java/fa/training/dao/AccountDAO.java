package fa.training.dao;

import fa.training.entities.Account;

public interface AccountDAO {
    Account getAccountByEmail(String email);
    Account getAccountByAccount(String account);
}
