/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.cse.team.classadmin;

import deu.cse.team.message.SendMessage;
import deu.cse.team.reservation.ReserveAuth;
import deu.cse.team.singleton.AccountDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import deu.cse.team.strategy.AllowedStudent;
import deu.cse.team.strategy.Class911;
import deu.cse.team.strategy.Class915;
import deu.cse.team.strategy.Class916;
import deu.cse.team.strategy.Class918;
import deu.cse.team.strategy.LectureRoom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * 2022.11.12 [최초작성자 20183215 정현수] 계정 삭제, 예약 시간 연장, 예약 승인 시 관리 권한자 설정
 *
 */
public class ClassAdmin {

    public void classAdminSet(String ok, String classadmin, String classnumber, String reser_number, String reser_date, String reser_endtime, String id) { //ㅎ
        //DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        //int row = jTable1.getSelectedRow();
        DAO dao = DAO.getInstance();
        ReservationDTO dto = new ReservationDTO();
        List<ReservationDTO> reserlist = dao.getReserList();
        List<AccountDTO> accountlist = dao.getAccountList();
        int maxReserNum = -1;

        SendMessage send = new SendMessage();
        String phonenumber = "01066885399";

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String max = "16:00"; // 최장시간을 구하기 위함

        String admin = "-";
        int count = 0; //삭제 후 리스트에 값이 0개일 경우를 계산하기 위함
        boolean check = false; // 리스트에 값이 없는지 확인
        
        if ("1".equals(ok)) { //예약 승인을 취소
            if ((classadmin).contains("관리권한")) { //취소하려는 예약이 관리권한자인 경우
                for (int i = 0; i < reserlist.size(); i++) {
                    if ((reserlist.get(i).getReser_number()) != (Integer.parseInt(reser_number))) { // 리스트에서 본인을 제외하고 비교
                        if (classnumber.equals(reserlist.get(i).getClassnumber())) { //같은 강의실인 사람만 비교
                            if ("1".equals(reserlist.get(i).getOk())) { //예약 승인된 사람만
                                if ((reserlist.get(i).getReser_date()).equals(reser_date)) { //같은 날짜 예약인 사람만 비교
                                    if (Integer.parseInt((reserlist.get(i).getReser_starttime()).substring(0, 2)) >= 17) // 17시 이후 예약만 비교
                                    {
                                        count++;
                                        try {
                                            if ((formatter.parse(reserlist.get(i).getReser_endtime())).after(formatter.parse(max))) {
                                                max = reserlist.get(i).getReser_endtime();
                                                maxReserNum = reserlist.get(i).getReser_number();
                                                for (int j = 0; j < accountlist.size(); j++) { // 전화번호를 얻기 위함
                                                    if ((reserlist.get(i).getId()).equals(accountlist.get(j).getId())) {
                                                        phonenumber = accountlist.get(j).getPhonenumber();
                                                    }
                                                }
                                            }
                                        } catch (ParseException ex) {
                                            java.util.logging.Logger.getLogger(ClassAdmin.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (count == 0) { // 선택한 값을 제외하고 예약 승인된 사람이 없을 경우 
                    dao.UpdateReser(dto, reser_number, "-", "0");
                } else {

                    if (classnumber.equals("915")) {
                        send.send(phonenumber);
                        LectureRoom class915 = new Class915();
                        class915.setAllowedBehavior(new AllowedStudent());
                        dao.UpdateReser(dto, Integer.toString(maxReserNum), class915.display(), "1");
                    }
                    if (classnumber.equals("916")) {
                        send.send(phonenumber);
                        LectureRoom class916 = new Class916();
                        class916.setAllowedBehavior(new AllowedStudent());
                        dao.UpdateReser(dto, Integer.toString(maxReserNum), class916.display(), "1");
                    }
                    if (classnumber.equals("918")) {
                        send.send(phonenumber);
                        LectureRoom class918 = new Class918();
                        class918.setAllowedBehavior(new AllowedStudent());
                        dao.UpdateReser(dto, Integer.toString(maxReserNum), class918.display(), "1");
                    }
                    if (classnumber.equals("911")) {
                        send.send(phonenumber);
                        LectureRoom class911 = new Class911();
                        class911.setAllowedBehavior(new AllowedStudent());
                        dao.UpdateReser(dto, Integer.toString(maxReserNum), class911.display(), "1");
                    }
                    dao.UpdateReser(dto, reser_number, "-", "0");
                }
            } else {
                dao.UpdateReser(dto, reser_number, "-", "0");
            }
        } else if ("0".equals(ok)) {
            for (int i = 0; i < reserlist.size(); i++) {
                if (classnumber.equals(reserlist.get(i).getClassnumber()) && (reserlist.get(i).getClassadmin()).equals(classnumber + "관리권한")) { //같은 강의실, 관리권한자인 경우만 비교
                    if ((reserlist.get(i).getReser_date()).equals(reser_date)) { // 날짜가 같은 경우만 비교 
                        if ((reserlist.get(i).getOk()).equals("1")) {
                            check = true;
                            try {
                                if ((formatter.parse(reser_endtime)).after(formatter.parse(reserlist.get(i).getReser_endtime()))) {
                                    count++;
                                    dao.UpdateReser(dto, Integer.toString(reserlist.get(i).getReser_number()), "-", "1");
                                }

                            } catch (ParseException ex) {
                                java.util.logging.Logger.getLogger(ReserveAuth.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
            if (check == false) {
                for (int j = 0; j < accountlist.size(); j++) { // 전화번호를 얻기 위함
                    if (id.equals(accountlist.get(j).getId())) {
                        phonenumber = accountlist.get(j).getPhonenumber();
                    }
                }
                if (classnumber.equals("915")) {
                    send.send(phonenumber);
                    LectureRoom class915 = new Class915();
                    class915.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class915.display(), "1");
                }
                if (classnumber.equals("916")) {
                    send.send(phonenumber);
                    LectureRoom class916 = new Class916();
                    class916.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class916.display(), "1");
                }
                if (classnumber.equals("918")) {
                    send.send(phonenumber);
                    LectureRoom class918 = new Class918();
                    class918.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class918.display(), "1");
                }
                if (classnumber.equals("911")) {
                    send.send(phonenumber);
                }
                LectureRoom class911 = new Class911();
                class911.setAllowedBehavior(new AllowedStudent());
                dao.UpdateReser(dto, reser_number, class911.display(), "1");
            }
        } else {
            if (count == 0) {
                dao.UpdateReser(dto, reser_number, "-", "1");
            } else if (count >= 1) {
                for (int j = 0; j < accountlist.size(); j++) { // 전화번호를 얻기 위함
                    if (id.equals(accountlist.get(j).getId())) {
                        phonenumber = accountlist.get(j).getPhonenumber();
                    }
                }
                if (classnumber.equals("915")) {
                    send.send(phonenumber);
                    LectureRoom class915 = new Class915();
                    class915.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class915.display(), "1");
                }
                if (classnumber.equals("916")) {
                    send.send(phonenumber);
                    LectureRoom class916 = new Class916();
                    class916.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class916.display(), "1");
                }
                if (classnumber.equals("918")) {
                    send.send(phonenumber);
                    LectureRoom class918 = new Class918();
                    class918.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class918.display(), "1");
                }
                if (classnumber.equals("911")) {
                    send.send(phonenumber);
                    LectureRoom class911 = new Class911();
                    class911.setAllowedBehavior(new AllowedStudent());
                    dao.UpdateReser(dto, reser_number, class911.display(), "1");
                }
            }
        }
    }
}
