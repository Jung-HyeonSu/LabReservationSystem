/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.factory;

/**
 *
 * @author eotkd
 */
public class AllowedOk implements Allowed{
    boolean permission;

    public boolean getPermission() {
        return permission;
    }
    public AllowedOk() {
        this.permission=true;
    }
}
