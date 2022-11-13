/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package deu.cse.team.observer;

/**
 *
 * @author PC
 */
public class ex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NoticeData noticeData = new NoticeData();
        AdminObserver adminObserver = new AdminObserver(noticeData);
        noticeData.setNotice("안녕하세요");
        System.out.println(adminObserver.display());
    }
    
}
