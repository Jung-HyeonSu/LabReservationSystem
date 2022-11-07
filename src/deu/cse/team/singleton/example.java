/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package deu.cse.team.singleton;

import java.util.List;

/*
* 2022.11.05 [최초작성자 20183215 정현수]
* 삽입 연습
 */
public class example {

    public static void main(String[] args) {
        DAO dao = DAO.getInstance();
        //삽입
        ClassTimetableDTO dto = new ClassTimetableDTO();
        dto.setClassnumber("916");
        dto.setTime1("X,X,X,X,X,X,X");
        dto.setTime2("X,X,X,X,X,X,X");
        dto.setTime3("X,X,X,X,X,X,X");
        dto.setTime4("X,X,X,X,X,X,X");
        dto.setTime5("X,X,X,X,X,X,X");
        dto.setTime6("X,X,X,X,X,X,X");
        dto.setTime7("X,X,X,X,X,X,X");
        dto.setTime8("X,X,X,X,X,X,X");
        //boolean r = dao.InsertC(dto);
        
       /* if (r) {
            System.out.println("삽입에 성공했습니다.");
        } else {
            System.out.println("삽입에 실패했습니다.");
        }*/
    }

}
