
package deu.cse.team.reservation;

import deu.cse.team.command.RemoteControl;
import deu.cse.team.command.Reservation;
import deu.cse.team.command.ReservationCancelCommand;
import deu.cse.team.command.ReservationOkCommand;
import deu.cse.team.singleton.AccountDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Seongchan
 */
public class afterReserve extends javax.swing.JFrame {

    /**
        2022.11.07 [최초작성자 20183207 김성찬]
        사용자 계정관리
    */
     int max =38;
    JCheckBox[] seatcheckbox = new JCheckBox[max];
    int row=0;
    String starttime;
    String endtime;
    String seatnumber;
    String Message ="예약 완료";
    //"DB에서 이용규칙 가져오기. 관리자는 이용수칙을 DB에 저장하고 수정도 가능해야함";
    RemoteControl remoteControl = new RemoteControl();
    Reservation reservation = new Reservation();
    ReservationOkCommand reservationOk = new ReservationOkCommand(reservation);
    ReservationCancelCommand reservationCancel = new ReservationCancelCommand(reservation);    
    DAO dao = DAO.getInstance(); 
    
    public afterReserve() {        
        initComponents();        
        remoteControl.setCommand(1, reservationOk, reservationCancel);
        max=40;//값가져와서 변경하기
        setSeat();
        responsiblename.setText("이름가져오기");         
    }
    
    
     void setSeat(){
        for (int k = 0; k < max; k++) {
                seatnumber=null;
                seatnumber = Integer.toString(((k+1)+(row*8))) + "번 좌석";
                seatcheckbox[k] = new JCheckBox();
                seatcheckbox[k].setText(seatnumber);
                if (k>=4) {
                    seatcheckbox[k].setBounds(80*k+130,50*row+170,80,30);
                }
                else
                    seatcheckbox[k].setBounds(80*k+80,50*row+170,80,30);
                seatcheckbox[k].setVisible(true);
                add(seatcheckbox[k]);
                seatcheckbox[k].addItemListener(new clickseat(((k+1)+(row*8))));
                
                if (row*8+k==max-1) break;
                if (k==7){
                    row++;
                    k=-1;
                }
            }
    }
     public class clickseat implements ItemListener   {
                String value;
                clickseat(int k){
                    this.value=Integer.toString(k);
                }
                @Override
                public void itemStateChanged(ItemEvent e) {
                    seatnumber = value;
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

        editTime = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        changebtn1 = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        starttimebox = new javax.swing.JComboBox<>();
        endtimebox = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        responsiblename = new javax.swing.JLabel();
        classnumber = new javax.swing.JLabel();
        settotal = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        changebtn = new javax.swing.JButton();
        resertimearea = new javax.swing.JLabel();
        resertime = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel1.setText("시간설정");

        jlabel.setText("시작시간");

        jLabel3.setText("종료시간");

        changebtn1.setText("변경");
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

        javax.swing.GroupLayout editTimeLayout = new javax.swing.GroupLayout(editTime.getContentPane());
        editTime.getContentPane().setLayout(editTimeLayout);
        editTimeLayout.setHorizontalGroup(
            editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTimeLayout.createSequentialGroup()
                .addGroup(editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editTimeLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1))
                    .addGroup(editTimeLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editTimeLayout.createSequentialGroup()
                                .addComponent(changebtn1)
                                .addGap(48, 48, 48)
                                .addComponent(cancelbtn))
                            .addGroup(editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(editTimeLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endtimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(editTimeLayout.createSequentialGroup()
                                    .addComponent(jlabel)
                                    .addGap(56, 56, 56)
                                    .addComponent(starttimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        editTimeLayout.setVerticalGroup(
            editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editTimeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel)
                    .addComponent(starttimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(endtimebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(editTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn1)
                    .addComponent(cancelbtn))
                .addGap(45, 45, 45))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel31.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel31.setText("관리자 책임자:");

        responsiblename.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        responsiblename.setForeground(new java.awt.Color(0, 0, 255));
        responsiblename.setText("홍길동");

        classnumber.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
        classnumber.setText("915 강의실");

        settotal.setText("예약 가능한 좌석 수:");

        jLabel35.setForeground(new java.awt.Color(255, 0, 51));
        jLabel35.setText("0/30");

        changebtn.setText("시간 변경");
        changebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtnActionPerformed(evt);
            }
        });

        resertimearea.setText("예약 시간:");

        resertime.setText("17:00 ~ 18:00");

        jButton1.setText("다음");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("이전");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resertimearea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resertime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changebtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(settotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(responsiblename)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(72, 72, 72)
                        .addComponent(jButton2))
                    .addComponent(classnumber))
                .addGap(291, 291, 291))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(classnumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(responsiblename)
                    .addComponent(settotal)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn)
                    .addComponent(resertimearea)
                    .addComponent(resertime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtnActionPerformed
        editTime.setVisible(true);
        editTime.setLocationRelativeTo(this);
        editTime.setSize(450, 400);
    }//GEN-LAST:event_changebtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if ("cancel".equals(remoteControl.B_ButtonWasPushed(1))) {
            showMessageDialog(null, "예약 취소");
            dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ("ok".equals(remoteControl.A_ButtonWasPushed(1))) {
            showMessageDialog(null, Message);            
        }
        String time[] = resertime.getText().split("~");
        starttime = time[0].trim();
        endtime = time[1].trim();
        ReservationDTO rdto = new ReservationDTO(dao.getReserLength(),Integer.parseInt(seatnumber),responsiblename.getText(),classnumber.getText(),starttime,endtime);
        boolean checkReservation = dao.InsertReservation(rdto);  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void changebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtn1ActionPerformed
        // TODO add your handling code here:
        int starttime = Integer.parseInt(starttimebox.getSelectedItem().toString());
        int endtime = Integer.parseInt(endtimebox.getSelectedItem().toString());
        if (starttime>endtime) showMessageDialog(null, "시작 시간이 종료 시간보다 클 수는 없습니다.");
        else if (starttime == endtime) showMessageDialog(null, "시작 시간이 종료 시간과 같을 수는 없습니다.");
        else{
            editTime.dispose();
            resertime.setText(starttime+":00 ~ "+endtime+":00");
        }  
       
    }//GEN-LAST:event_changebtn1ActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        editTime.dispose();
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
            java.util.logging.Logger.getLogger(afterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(afterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(afterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(afterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new afterReserve().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton changebtn;
    private javax.swing.JButton changebtn1;
    private javax.swing.JLabel classnumber;
    private javax.swing.JDialog editTime;
    private javax.swing.JComboBox<String> endtimebox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jlabel;
    public javax.swing.JLabel resertime;
    private javax.swing.JLabel resertimearea;
    private javax.swing.JLabel responsiblename;
    private javax.swing.JLabel settotal;
    private javax.swing.JComboBox<String> starttimebox;
    // End of variables declaration//GEN-END:variables
}
