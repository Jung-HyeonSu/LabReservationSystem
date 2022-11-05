/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.team.factory;

/**
 *
 * @author Seongchan
 */
public class MakeUser extends MakeAccount {
       Account account =null;
       AccountFactory accountfactory = new UserFactory();
  
    protected Account newUser(String Division){
        account = new Student(accountfactory);
//        account.allow=accountfactory.
        return account;
    }
}
