package fa.training.service;

import fa.training.dao.AccountDAO;
import fa.training.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO  accountDAO;
    @Override
    public Account getAccountByEmail(String email) {
        return accountDAO.getAccountByEmail(email);
    }
    @Override
    public Account getAccountByAccount(String account) {
        return accountDAO.getAccountByAccount(account);
    }


}
