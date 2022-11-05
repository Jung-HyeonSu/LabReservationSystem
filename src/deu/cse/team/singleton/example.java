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
        AccountDTO dto = new AccountDTO();
        dto.setStu_id("20183215");
        dto.setPassword("abcd");
        dto.setName("정현수");
        dto.setPhonenumber("01066885399");
        dto.setWarning(0);
        dto.setPower("O");
        dto.setAllowed(false);
        boolean r = dao.InsertAccount(dto);
        
        if (r) {
            System.out.println("삽입에 성공했습니다.");
        } else {
            System.out.println("삽입에 실패했습니다.");
        }
        
        
        List<AccountDTO> list = dao.getList();
        //list의 모든 데이터에 접근해서 출력
        for (AccountDTO account : list) {
            //출력하는 메서드에 참조형 변수를 사용하면 toString()메서드가 호출되서 출력됩니다.
            System.out.println(account);
        }
    }

}
