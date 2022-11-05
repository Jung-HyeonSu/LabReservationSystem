/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

import java.io.Serializable;

/**
 2022.11.05 [최초작성자 20183215 정현수]
 DB와 연동할 DTO 클래스
 접근자 메서드, toString메서드 생성
 */

public class ReservationDTO implements Serializable {

    private String reser_number;
    private String stu_id;
    private String classnumber;
    private String reser_starttime;
    private String reser_endtime;

    public ReservationDTO() {
        super();
    }

    public ReservationDTO(String reser_number,String stu_id,String classnumber,String reser_starttime,String reser_endtime) {
        super();
        this.reser_number = reser_number;
        this.stu_id = stu_id;
        this.classnumber = classnumber;
        this.reser_starttime = reser_starttime;
        this.reser_endtime = reser_endtime;
    }

    public String getReser_number() {
        return reser_number;
    }

    public void setReser_number(String reser_number) {
        this.reser_number = reser_number;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }

    public String getReser_starttime() {
        return reser_starttime;
    }

    public void setReser_starttime(String reser_starttime) {
        this.reser_starttime = reser_starttime;
    }

    public String getReser_endtime() {
        return reser_endtime;
    }

    public void setReser_endtime(String reser_endtime) {
        this.reser_endtime = reser_endtime;
    }

    @Override
    public String toString() {
        String str = String.format("%s\t%s\t%s\t%s\t%s",
                     reser_number,stu_id,classnumber,reser_starttime,reser_endtime);
        return str;
    }

}