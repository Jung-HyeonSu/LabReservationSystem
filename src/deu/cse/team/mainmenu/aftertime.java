/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deu.cse.team.mainmenu;

import deu.cse.team.command.HeadcountGui;
import deu.cse.team.reservation.afterReserve;
import deu.cse.team.reservation.beforeReserve;
import deu.cse.team.singleton.ClassInformationDTO;
import deu.cse.team.singleton.ClassTimetableDTO;
import deu.cse.team.singleton.DAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Seongchan
 */
public class aftertime extends javax.swing.JFrame {

    /**
     * 2022.11.9 [최초작성자 20183207 김성찬] 사용자 계정관리
     */
    String id;
    boolean[] classTime = new boolean[9];//수업시간있는지 확인하는 객체  | true = 수업 O false = 수업 X    
    DAO dao = DAO.getInstance();
    List<ClassTimetableDTO> cdto = dao.getTimetableList();
    Calendar c = Calendar.getInstance();
    int dayofWeek = c.get(Calendar.DAY_OF_WEEK);//요일 판단 일 ~ 토 = 1 ~ 7
    int max = 40;
    int resercount=0;
    int index = 0;

    public aftertime(String id) {
        initComponents();
        this.id = id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        changebtn1 = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        starttimebox = new javax.swing.JComboBox<>();
        endtimebox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("시간설정");

        jlabel.setText("시작시간");

        jLabel3.setText("종료시간");

        changebtn1.setText("설정");
        changebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtn1ActionPerformed(evt);
            }
        });

        cancelbtn.setText("취소");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        starttimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "17", "18", "19", "20", "21", "22", "23", "24" }));

        endtimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "18", "19", "20", "21", "22", "23", "24" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(changebtn1)
                                .addGap(48, 48, 48)
                                .addComponent(cancelbtn))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endtimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jlabel)
                                    .addGap(56, 56, 56)
                                    .addComponent(starttimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel)
                    .addComponent(starttimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(endtimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn1)
                    .addComponent(cancelbtn))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtn1ActionPerformed
        // TODO add your handling code here:
        DAO dao = DAO.getInstance();
        String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
        String starttime = starttimebox.getSelectedItem().toString();
        String endtime = endtimebox.getSelectedItem().toString();
        if (Integer.parseInt(starttime) > Integer.parseInt(endtime)) {
            showMessageDialog(null, "시작 시간이 종료 시간보다 클 수는 없습니다.");
        } else if (Integer.parseInt(starttime) == Integer.parseInt(endtime)) {
            showMessageDialog(null, "시작 시간이 종료 시간과 같을 수는 없습니다.");
        } else {            
            //강의실별 예약현황 확인 dao 추가하기
            List<ClassInformationDTO> cid = dao.getClassInformation(); //디비에서 가져왔다가졍
            index=0;
            //1번 17시이후는 시간표가 없으므로 비교 X
            //index == 테이블에서 가져온 빈 강의실 번호를 가지는 index
//           1. 시간표랑 비교 /구현 완료
//           2. 풀방인지 확인
/*             풀방이면 넘기고 아니면아래로            
*/
//           3. 25명 넘는지 확인
/*              넘으면 개인인지 팀인지 판단하기
                팀 :  방선택 권한 | 총 예약인원 + 팀인원 >= 방의 총 수용인원 => 다음방으로
                개인 : 남은 자리 예약하기
*/      
//            옵션패널로  방 옮길지 말지 네 아니오 구현하면 될 듯
//              for문으로  모든 좌석이 예약되었는지 확인하기
            
            for ( ; index < cid.size(); index++) {
                resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(),today, starttime, endtime);
                if (resercount == cid.get(index).getMaxseat()) {
//              showMessageDialog(null,"설정한 시간에 모든 좌석 예약이 완료되어있습니다");
                System.out.println(cid.get(index).getClassnumber()+"모든 좌석 예약 완료");
                }
//             2. 풀방인지 확인
/*             풀방이면 넘기고 아니면아래로     /구현 완료  */   
                
                else if(resercount >= 25){
                    showMessageDialog(null, "선택한 시간에 예약한 사람이 25명이 넘습니다.\n선택예약으로 이동합니다.");
                    //다음 확인하고 ++
                    HeadcountGui h = new HeadcountGui(starttime, endtime, "before", id, index); //생성자에 강의실 번호 추가하기0
                    //옮긴다고하면 index +=1로 수정 아니면 그냥 그래도 ㄱㄱ                    
                    h.setVisible(true);
                    break;
                }
//             3. 25명 넘는지 확인   
                
                else if (resercount < 25){
                    afterReserve ar = new afterReserve(id, starttime, endtime, 1, cid.get(index).getMaxseat(), cid.get(index).getClassnumber());
                    ar.setVisible(true);
                    ar.setSize(818, 477);
                    break;
                }
//                    4. 25명 안 넘으면 예약가능한 강의실로 이동 //구현 완
            }
            if (index == cid.size() ) {
                showMessageDialog(null, "현재 예약가능한 강의실이 없습니다.");
                dispose();
//              5. 모든 강의실이 수업중인경우
//                 모든 강의실이 예약이 된경우
            }   
        }


    }//GEN-LAST:event_changebtn1ActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancelbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(aftertime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aftertime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aftertime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aftertime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new aftertime("20183207").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton changebtn1;
    private javax.swing.JComboBox<String> endtimebox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jlabel;
    private javax.swing.JComboBox<String> starttimebox;
    // End of variables declaration//GEN-END:variables
}
