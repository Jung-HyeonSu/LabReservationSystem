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
        dto.setClassnumber("918");
        dto.setTime1("　,　,　,　,　,　,　");
        dto.setTime2("　,　,　,　,　,　,　");
        dto.setTime3("　,　,　,　,　,　,　");
        dto.setTime4("　,　,　,　,　,　,　");
        dto.setTime5("　,　,　,　,　,　,　");
        dto.setTime6("　,　,　,　,　,　,　");
        dto.setTime7("　,　,　,　,　,　,　");
        dto.setTime8("　,　,　,　,　,　,　");
        boolean r = dao.InsertC(dto);
        
        if (r) {
            System.out.println("삽입에 성공했습니다.");
        } else {
            System.out.println("삽입에 실패했습니다.");
        }
    }

}
