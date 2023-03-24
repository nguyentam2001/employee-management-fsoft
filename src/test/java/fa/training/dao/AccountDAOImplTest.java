package fa.training.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fa.training.entities.Account;
import org.junit.jupiter.api.Test;

class AccountDAOImplTest {
    /**
     * Method under test: {@link AccountDAOImpl#getAccountByEmail(String)}
     */
    @Test
    void testGetAccountByEmail() {
        AccountDAOImpl accountDAOImpl = new AccountDAOImpl();
        accountDAOImpl.getAccountByEmail("jane.doe@example.org");
        Account accountByAccount = accountDAOImpl.getAccountByAccount("3");
        assertNull(accountByAccount.getAccount());
        assertNull(accountByAccount.getStatus());
        assertNull(accountByAccount.getPassword());
        assertNull(accountByAccount.getEmployee());
        assertNull(accountByAccount.getEmail());
        assertNull(accountByAccount.getAccountId());
    }

    /**
     * Method under test: {@link AccountDAOImpl#divCalculate(int, int)}
     */
    @Test
    void testDivCalculate() {
        assertEquals(1, (new AccountDAOImpl()).divCalculate(1, 1));
        assertEquals(0, (new AccountDAOImpl()).divCalculate(3, 1));
        assertThrows(ArithmeticException.class, () -> (new AccountDAOImpl()).divCalculate(0, 1));
        assertEquals(-1, (new AccountDAOImpl()).divCalculate(-1, 1));
        assertThrows(ArithmeticException.class, () -> (new AccountDAOImpl()).divCalculate(1, 0));
        assertEquals(-1, (new AccountDAOImpl()).divCalculate(1, -1));
    }
}

