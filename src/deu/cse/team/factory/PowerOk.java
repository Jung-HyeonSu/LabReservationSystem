/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.factory;

/**
 *
 * @author eotkd
 */
public class PowerOk implements Power{
    boolean permission;

    public boolean getPermission() {
        return permission;
    }
    public PowerOk() {
        this.permission=true;
    }
}