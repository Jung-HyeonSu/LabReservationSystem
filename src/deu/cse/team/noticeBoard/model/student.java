/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.noticeBoard.model;

/**
 *
 * @author user
 */
public class student {
        String SID;
        String SPS;
        String PHONE;
        int WNUM;
        boolean STOP;

    public student(String SID, String PASS, String PHONE, int WNUM, boolean stop) {
        this.SID = SID;
        this.SPS = PASS;
        this.PHONE = PHONE;
        this.WNUM = WNUM;
        this.STOP = stop;
    }

    public String getSID() {
        return SID;
    }

    public String getPASS() {
        return SPS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public int getWNUM() {
        return WNUM;
    }

    public boolean isStop() {
        return STOP;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public void setPASS(String PASS) {
        this.SPS = PASS;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public void setWNUM(int WNUM) {
        this.WNUM = WNUM;
    }

    public void setStop(boolean stop) {
        this.STOP = stop;
    }
   
}
