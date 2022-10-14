/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.command;

/**
 *
 * @author PC
 */
public class ReservationOkCommand implements Command{
    Reservation reservation;
    
    public ReservationOkCommand (Reservation reservation){
        this.reservation = reservation;
    }
    
    @Override
    public String execute(){
        reservation.ok();
        return "ok";
    }
    
}
