/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.team.factory;

import java.lang.String;
/**
 *
 * @author Seongchan
 */
public abstract class MakeAccount  {
    protected abstract Account newUser(String Division);
    public Account createUser (String type){
        Account account=null;
        account=newUser(type);
        account.prepare();
        account.getName();
        account.getPhoneNumber();
        account.getPassword();
        account.getStudentNumber();
        return account;
    }
}
