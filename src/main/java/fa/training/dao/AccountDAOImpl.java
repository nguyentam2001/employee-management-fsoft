package fa.training.dao;

import fa.training.entities.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account getAccountByEmail(String email) {
        Account account= new Account();
       try{
           Session session=sessionFactory.getCurrentSession();
           session.beginTransaction();
           TypedQuery<Account> query=session.createQuery("from Account where email=:email",Account.class);
           query.setParameter("email",email);
           account= query.getSingleResult();
           session.getTransaction().commit();
           return account;
       }catch (Exception e){
           return account;
       }
    }

    @Override
    public Account getAccountByAccount(String account) {
        Account tmpaccount= new Account();

        try{
            Session session=sessionFactory.openSession();
            session.beginTransaction();
            TypedQuery<Account> query=session.createQuery("from Account where account=:account",Account.class);
            query.setParameter("account",account);
            tmpaccount= query.getSingleResult();
            session.getTransaction().commit();
            return tmpaccount;
        }catch (Exception e){
            return tmpaccount;
        }
    }
    public int divCalculate(int a,int b) {
        if(b>0){
            return b/a;

        }
        return  a/b;
    }
}
