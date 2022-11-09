/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

import java.io.Serializable;


/**
 *
 * @author user
 */
public class BoardDTO implements Serializable{
        private int no;
        private String title;
        private String content;
        private String sid;
        private String sps;
        private String type;
        private String wdate;

    public int getNo() {
        return no;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getSid() {
        return sid;
    }

    public String getSps() {
        return sps;
    }

    public String getType() {
        return type;
    }



    public void setNo(int no) {
        this.no = no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSps(String sps) {
        this.sps = sps;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    
}
