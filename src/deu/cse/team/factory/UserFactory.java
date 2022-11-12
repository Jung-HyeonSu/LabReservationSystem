/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.factory;

/**
 *
 * @author eotkd
 */
public class UserFactory implements AccountFactory{
     @Override
    public Power createPower() {
        return new NoPower();
    }

    @Override
    public Division createDivision() {
        return new DivisionStudent();
    }
}
