/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.command;

public class TeamCommand implements Command{
    
    HeadcountConfirm headcountConfirm;
    
    public TeamCommand (HeadcountConfirm headcountConfirm){
        this.headcountConfirm = headcountConfirm;
    }
    
    @Override
    public String execute(){
        headcountConfirm.team();
        return "팀";
    }
}