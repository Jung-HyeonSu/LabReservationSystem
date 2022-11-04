/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.factory;

/**
 *
 * @author eotkd
 */
public class NotAllowed implements Allowed{
    boolean permission;
    public NotAllowed() {
        this.permission=false;
    }

    @Override
    public boolean getPermission() {
        return permission;
    }
}
