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
public class Professor extends Account {
    private AccountFactory factory;
    public Professor(AccountFactory factory){
        this.factory=factory;
    }
    public void prepare(){
        this.allow = factory.createPower();
        this.division = factory.createDivision();
    }
    
}
