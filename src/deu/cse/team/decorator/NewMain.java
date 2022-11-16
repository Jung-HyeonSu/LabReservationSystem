/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package deu.cse.team.decorator;

/**
 *
 * @author PC
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Time time = new SeatNumber("24");
        time = new QuarterTime(time);
        time = new OneHourTime(time);
        System.out.println(Integer.toString(time.time()));
    }
    
}
