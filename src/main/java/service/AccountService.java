/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import model.account.Account;

/**
 *
 * @author huant
 */
public interface AccountService {
    public Account Login(String username,String password);
}
