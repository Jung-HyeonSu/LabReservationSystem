/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

/**
 *
 * @author Seongchan
 */
public class ClassInformationDTO {
    private String classnumber;
    private int maxseat;
    
    public ClassInformationDTO() {
        super();
    }

    public ClassInformationDTO(String classnumber, int maxseat) {
        super();
        this.classnumber = classnumber;
        this.maxseat = maxseat;
    }
    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }

    public int getMaxseat() {
        return maxseat;
    }

    public void setMaxseat(int maxseat) {
        this.maxseat = maxseat;
    }

}
