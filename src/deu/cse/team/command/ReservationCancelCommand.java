/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.command;

/**
 *
 * @author PC
 */
public class ReservationCancelCommand implements Command{
    Reservation reservation;
    
    public ReservationCancelCommand (Reservation reservation){
        this.reservation = reservation;
    }
    
    @Override
    public String execute(){
        reservation.cancel();
        return "cancel";
    }
    
}

    
    