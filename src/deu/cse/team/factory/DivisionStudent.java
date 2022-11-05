/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.factory;

/**
 *
 * @author eotkd
 */
public class DivisionStudent implements Division{
     String Division=null;
     @Override
     public String getDivision() {
 		return Division ;
 	}
    public DivisionStudent() {
        this.Division="Student";
    }
}