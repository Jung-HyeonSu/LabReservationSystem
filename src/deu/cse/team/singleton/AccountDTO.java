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

public class AccountDTO implements Serializable {

    private String id;
    private String password;
    private String name;
    private String phonenumber;
    private int warning;
    private String power;
    private String allowed;

    public AccountDTO() {
        super();
    }

    public AccountDTO(String id, String password, String name, String phonenumber, int warning, String power, String allowed) {
        super();
        this.id = id;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
        this.warning = warning;
        this.power = power;
        this.allowed = allowed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAllowed() {
        return allowed;
    }

    public void setAllowed(String allowed) {
        this.allowed = allowed;
    }

    @Override
    public String toString() {
        String str = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s",
                     id, password, name,
                     phonenumber, warning, power,
                     allowed);
        return str;
    }

}
