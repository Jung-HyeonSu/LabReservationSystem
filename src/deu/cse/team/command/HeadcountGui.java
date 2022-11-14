/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package deu.cse.team.command;

import deu.cse.team.reservation.afterReserve;
import deu.cse.team.reservation.beforeReserve;
import deu.cse.team.singleton.ClassInformationDTO;
import deu.cse.team.singleton.*;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author PC
 */
public class HeadcountGui extends javax.swing.JFrame {

    /**
     * Creates new form HeadcountGui
     */
    int resercount = 0;
    RemoteControl remoteControl = new RemoteControl();
    DAO dao = DAO.getInstance();
    String id;
    int max = 40;
    HeadcountConfirm headcountConfirm = new HeadcountConfirm();
    String headCount = "1";
    public String getHeadCount() {
        return headCount;
    }
    IndividualCommand individual = new IndividualCommand(headcountConfirm);
    List<ClassInformationDTO> cid = dao.getClassInformation(); //강의실 리스트 가져오기     
    
    TeamCommand team = new TeamCommand(headcountConfirm);
    String starttime = "9";
    String endtime = "1";
    String check;
    int index;
    Calendar c = Calendar.getInstance();
    int dayofWeek = c.get(Calendar.DAY_OF_WEEK);//요일 판단 일 ~ 토 = 1 ~ 7    
    String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
    boolean[] classTime = new boolean[9];//수업시간있는지 확인하는 객체  | true = 수업 O false = 수업 X    
    List<ClassTimetableDTO> cdto = dao.getTimetableList();
    
    public HeadcountGui(String starttime, String endtime, String check, String id, int index) {
        initComponents();
        this.starttime = starttime;
        this.endtime = endtime;
        this.check = check;
        this.id = id;
        this.index = index;
        remoteControl.setCommand(0, individual, team);
    }
    
    
    
    void getSchedule(int index) {
        if (dayofWeek == 1) dayofWeek = 8;
        System.out.println("length "  + cdto.get(index).getTime1().split(",").length);
        classTime[0] = !(cdto.get(index).getTime1().split(",")[dayofWeek - 2].equals(" ")); //0=방 번호 915 916 917 918 | 0,1,2,3
        classTime[1] = !(cdto.get(index).getTime2().split(",")[dayofWeek - 2].equals(" "));
        classTime[2] = !(cdto.get(index).getTime3().split(",")[dayofWeek - 2].equals(" "));
        classTime[3] = !(cdto.get(index).getTime4().split(",")[dayofWeek - 2].equals(" "));
        classTime[4] = !(cdto.get(index).getTime5().split(",")[dayofWeek - 2].equals(" "));
        classTime[5] = !(cdto.get(index).getTime6().split(",")[dayofWeek - 2].equals(" "));
        classTime[6] = !(cdto.get(index).getTime7().split(",")[dayofWeek - 2].equals(" "));
        classTime[7] = !(cdto.get(index).getTime8().split(",")[dayofWeek - 2].equals(" "));
        classTime[8] = false;
    }
    
    
    
    void checkreser(int i) {
        if (check.equals("before")) {
            showMessageDialog(null, cid.get(i).getClassnumber() + "강의실에서 예약을 시작합니다.");
            beforeReserve br = new beforeReserve(id, starttime, endtime, 1, cid.get(i).getMaxseat(), cid.get(i).getClassnumber());
            br.setVisible(true);
            br.setSize(818, 550);
        } else {
            showMessageDialog(null, cid.get(i).getClassnumber() + "강의실에서 예약을 시작합니다.");
            afterReserve ar = new afterReserve(id, starttime, endtime, 1, cid.get(i).getMaxseat(), cid.get(i).getClassnumber());
            ar.setVisible(true);
            ar.setSize(818, 550);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        usernumber = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        nextbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("개인 예약");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("팀 예약");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("인원수:");

        usernumber.setEnabled(false);

        cancel.setText("이전");

        nextbtn.setText("다음");
        nextbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextbtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel2.setText("개인/팀 예약 설정");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nextbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancel))
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usernumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(nextbtn))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        usernumber.setEnabled(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        usernumber.setEnabled(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed
//              25명 넘는지 확인
/*              넘으면 개인인지 팀인지 판단하기
                팀 :  방선택 권한 | 총 예약인원 + 팀인원 >= 방의 총 수용인원 => 다음방으로
                개인 : 남은 자리 예약하기
     */
    private void nextbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextbtnActionPerformed
        // TODO add your handling code here:

        //개인 예약 구현
        if (jRadioButton1.isSelected()) {
            headCount = remoteControl.A_ButtonWasPushed(0);
            //예약된 좌석수 확인
            checkreser(index);
//              팀예약 구현
//              팀 :  방선택 권한 | 총 예약인원 + 팀인원 >= 방의 총 수용인원 => 다음방으로
        } else if (jRadioButton2.isSelected()) {
            headCount = remoteControl.B_ButtonWasPushed(0);
            int option = JOptionPane.showConfirmDialog(null, "현재 강의실에 사람이 많습니다.\n다음 강의실을 예약하시겠습니까 ?", "강의실 선택", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
            /*
            YES == 0
            NO == 1
             */
            if (option == 0) {
                index += 1;
                while (index < cid.size()) {
                    resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                    if (resercount + Integer.parseInt(usernumber.getText()) > cid.get(index).getMaxseat()) {
                        index++;
                        
                    } else {
                        checkreser(index);
                    }
                }

            } else if (option == 1) {
                int tmpindex = index;
                resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                if (resercount + Integer.parseInt(usernumber.getText()) > cid.get(index).getMaxseat()) {
                    index += 1;
                    while (index < cid.size()) {
                        resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                        if (resercount + Integer.parseInt(usernumber.getText()) > cid.get(index).getMaxseat()) {
                            index++;
                        } else {
                            if (tmpindex != index)showMessageDialog(null, "강의실 수용인원이 초과하였습니다. 강의실을 이동합니다.");
                            checkreser(index);
                        }
                    }
                    // 다음 강의실 도 사람 많은지 확인 하기
                } else {
                    checkreser(index);
                }
            }
            if (index == cid.size()) {
                showMessageDialog(null, "설정한 시간에 해당 인원으로 예약할 수 있는 강의실이 없습니다. 다시설정해주세요");
            }

//            for (int i = index; i < cid.size(); i++) {
//                resercount = dao.getClassReserLength(cid.get(i).getClassnumber()); // 첫 강의실 정보 가져오기
//                if (resercount == max || resercount + Integer.parseInt(usernumber.getText()) >= cid.get(i).getMaxseat()) {
//                    if (i == cid.size() - 1) {
//                        showMessageDialog(null, "설정한 시간에 해당 인원으로 예약할 수 있는 강의실이 없습니다. 다시설정해주세요");
//                    }
//                } else {
//                    if (check.equals("before")) {
//                        showMessageDialog(null, cid.get(i).getClassnumber() + "강의실에서 예약을 시작합니다.");
//                        beforeReserve br = new beforeReserve(id, starttime, endtime, Integer.parseInt(usernumber.getText()), cid.get(i).getMaxseat(), cid.get(i).getClassnumber());
//                        br.setVisible(true);
//                        br.setSize(818, 477);
//
//                        break;
//                    } else {
//                        showMessageDialog(null, cid.get(i).getClassnumber() + "강의실에서 예약을 시작합니다.");
//                        afterReserve ar = new afterReserve(id, starttime, endtime, Integer.parseInt(usernumber.getText()), cid.get(i).getMaxseat(), cid.get(i).getClassnumber());
//                        ar.setVisible(true);
//                        ar.setSize(818, 477);
//                        break;
//                    }
//                }
//            }
        }

        dispose();
    }//GEN-LAST:event_nextbtnActionPerformed

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
            java.util.logging.Logger.getLogger(HeadcountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HeadcountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HeadcountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HeadcountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HeadcountGui("09:00", "10:00", "before", "20183207", 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JButton nextbtn;
    private javax.swing.JTextField usernumber;
    // End of variables declaration//GEN-END:variables
}
