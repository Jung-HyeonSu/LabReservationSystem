/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.strategyclassinfor;

/**
 *
 * @author Seongchan
 */
public class Classinformation916 extends Lectureoom  {

    public Classinformation916(String type, String value) {
        classnumber="916";
        this.value=value;
        if (type.equals("add")) setseat = new AddMaxSettingBehavior(classnumber,value);
        else setseat = new SubMaxSettingBehavior(classnumber,value);
    }
    
}
