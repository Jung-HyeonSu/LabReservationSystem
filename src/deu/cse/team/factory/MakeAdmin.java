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
public class MakeAdmin extends MakeAccount {
     Account account =null;
     AccountFactory accountfactory = null;

    protected Account newUser(String Division){
        if (Division.equals("Professor")) {
            account = new Professor(accountfactory);
        }
        else
        {
            account = new Assistant(accountfactory);
        }
        return account;
    }
}
