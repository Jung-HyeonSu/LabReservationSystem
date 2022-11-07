/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

/**
 *
 * @author eotkd
 */
public class BoardDTO {
        int NO;
        String TITLE;
        String CONTENT;
        String SID;
        String DATE;
        String TYPE;
        String BPS;

    public int getBNUM() {
        return NO;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public String getSID() {
        return SID;
    }

    public String getDATE() {
        return DATE;
    }

    public String getBPS() {
        return BPS;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setBNUM(int BNUM) {
        this.NO = BNUM;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public void setBPS(String BPS) {
        this.BPS = BPS;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
    
}
