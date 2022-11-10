/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.singleton;

import java.io.Serializable;

/**
 2022.11.10 [최초작성자 20183199 정현수]
 DB와 연동할 DTO 클래스
 접근자 메서드, toString메서드 생성
 */

public class NoticeDTO implements Serializable {

    private String id;
    private String name; 
    private String seperation;
    private String content;

    public NoticeDTO() {
        super();
    }
    
    public NoticeDTO(String id,String name, String seperation,String content) {
        super();
        this.id = id;
        this.name = name;
        this.seperation = seperation;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeperation() {
        return seperation;
    }

    public void setSeperation(String seperation) {
        this.seperation = seperation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        String str = String.format("%s\t%s\t%s\t%s",
                     id,name,seperation,content);
        return str;
    }

}
