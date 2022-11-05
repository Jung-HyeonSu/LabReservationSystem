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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Account a= null;
        MakeAccount f = new MakeUser();
        MakeAccount f2 = new MakeAdmin();
        a=f.createUser("Stuent");
        
        System.out.println(a.getAllow());
    }
    
}
