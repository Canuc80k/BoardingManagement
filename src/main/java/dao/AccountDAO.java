
package dao;

import model.account.Account;

public interface AccountDAO {
    public Account login(String username,String password);
}
