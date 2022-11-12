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
public class Account {
    protected String Name;
    protected String StudentNumber;
    protected String Password;
    protected String PhoneNumber;
    private String token;
    protected Power allow;
    protected Division division;
    public void prepare(){
        
    }
    public void getName() {
//        this.name=text.getString()
    }

    public void getStudentNumber() {
        //        this.StudentNumber=text.getString()
    }

    public void getPassword() {
    //        this.Password=text.getString(){
    }

    public void getPhoneNumber() {
        //        this.PhoneNumber=text.getString()
    }

    public boolean getAllow() {
        return allow.getPermission();
    }
    public String getDivision() {
        return division.getDivision();
    }
}
