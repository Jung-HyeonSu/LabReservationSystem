/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.strategyclassinfor;

import deu.cse.team.singleton.DAO;

/**
 *
 * @author Seongchan
 */
public class allseat implements setmax{

    @Override
    public void seting(String classnumber) {
        DAO dao = DAO.getInstance();
        dao.UpdateClassInformaiton(classnumber, "40");
    }
    
    
}
