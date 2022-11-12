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
        Account b=null;
        Account c=null;
        MakeAccount f = new MakeUser();
        MakeAccount f2 = new MakeAdmin();
        MakeAccount f3 = new MakeAdmin();
        a=f.createUser("Student");
        b=f2.createUser("Professor");
        c=f3.createUser("Asistant");
        System.out.println(a.getDivision()+" "+a.getAllow());
        System.out.println(b.getDivision()+" "+b.getAllow());
        System.out.println(c.getDivision()+" "+c.getAllow());
    }
    
}
