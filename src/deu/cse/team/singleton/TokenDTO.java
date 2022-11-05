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

public class TokenDTO implements Serializable {

    private String token_value;

    public TokenDTO() {
        super();
    }

    public TokenDTO(String token_value) {
        super();
        this.token_value = token_value;
    }

    public String getToken_value() {
        return token_value;
    }

    public void setToken_value(String token_value) {
        this.token_value = token_value;
    }

    

    @Override
    public String toString() {
        String str = String.format("%s",
                     token_value);
        return str;
    }

}
