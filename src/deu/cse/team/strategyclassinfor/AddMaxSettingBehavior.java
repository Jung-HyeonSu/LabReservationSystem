/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.strategyclassinfor;

import deu.cse.team.singleton.DAO;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Seongchan
 */
public class AddMaxSettingBehavior implements setSeat{

    public AddMaxSettingBehavior(String classnumber, String value) {
        MaxSetting(classnumber,value);
    }


    @Override
    public void MaxSetting(String classnumber,String value) {
       DAO dao = DAO.getInstance();
       String existingNuber = dao.getClassMaxseat(classnumber);
       String finalvalue = Integer.toString(Integer.parseInt(existingNuber) + Integer.parseInt(value));
       if (Integer.parseInt(existingNuber) + Integer.parseInt(value) >40) {
            dao.UpdateClassInformation(classnumber, "40");
            showMessageDialog(null, "강의실의 최대 가용인원은 40명입니다.");
        } else {
            dao.UpdateClassInformation(classnumber, finalvalue);
        }
    }

}
