/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.strategyclassinfor;

/**
 *
 * @author Seongchan
 */
public class Classinformation911 extends Lectureoom  {

    public Classinformation911(String type, String value) {
        classnumber="911";
        this.value=value;
        if (type.equals("add")) setseat = new AddMaxSettingBehavior(classnumber,value);
        else setseat = new SubMaxSettingBehavior(classnumber,value);
    }
    
}
