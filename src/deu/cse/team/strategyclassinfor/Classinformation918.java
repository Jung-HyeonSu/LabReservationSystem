/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.strategyclassinfor;

/**
 *
 * @author Seongchan
 */
public class Classinformation918 extends Classinformation  {

    public Classinformation918(String type, String value) {
        classnumber="918";
        this.value=value;
        if (type.equals("add")) setseat = new AddMaxSettingBehavior(classnumber,value);
        else setseat = new SubMaxSettingBehavior(classnumber,value);
    }
    
}
