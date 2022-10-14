/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.command;

/**
 *
 * @author Yool
 */
public class IndividualCommand implements Command{
    HeadcountConfirm headcountConfirm;
    
    public IndividualCommand (HeadcountConfirm headcountConfirm){
        this.headcountConfirm = headcountConfirm;
    }
    
    @Override
    public String execute(){
        headcountConfirm.individual();
        return "individual";
    }
}
