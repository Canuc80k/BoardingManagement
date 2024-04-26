
package service;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import model.account.Account;

public class AccountServiceImpl implements AccountService{
    private AccountDAO accountDAO=null;
    public AccountServiceImpl(){
        accountDAO=new AccountDAOImpl();
    }
    @Override
    public Account Login(String username, String password) {
        return accountDAO.login(username, password);
    }
    
}
