
package deu.cse.team.reservation;

import deu.cse.team.command.RemoteControl;
import deu.cse.team.command.Reservation;
import deu.cse.team.command.ReservationCancelCommand;
import deu.cse.team.command.ReservationOkCommand;
import deu.cse.team.singleton.ClassTimetableDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JCheckBox;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Seongchan
 */
public class tmpafterReserve extends javax.swing.JFrame {

    /**
        2022.11.07 [최초작성자 20183207 김성찬]
        사용자 계정관리
    */
     int max =40;
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
    //[좌석][예약]해서 좌석별 시간별 리스트를 가져오고선택한시간에 있으면 설정
    boolean [][] reserseat = new boolean[max][8];
    List<ReservationDTO> rdto = dao.getReserList();
    
    void getreserseat(){
        int numberValue;
        int reserStartValue;
        int reserEndValue;
        for (int i = 0; i < rdto.size(); i++) {
            numberValue = rdto.get(i).getSeat_number();
            System.out.println(rdto.get(i).getReser_starttime());
            reserStartValue = Integer.parseInt(rdto.get(i).getReser_starttime().split(":")[0]);
            if (reserStartValue>=17) {
                System.out.println(rdto.get(i).getReser_starttime());            
                reserEndValue = Integer.parseInt(rdto.get(i).getReser_endtime().split(":")[0]);
                for (int j = reserStartValue-17; j < reserEndValue-17; j++) {
                reserseat[numberValue][j]=true;//예약이 되어있다.
            }
            }
            
        }
    }
    
    
    public tmpafterReserve() {        
        initComponents();        
        remoteControl.setCommand(1, reservationOk, reservationCancel);
        max=40;//값가져와서 변경하기
        setSeat();
        getreserseat();
        responsiblename.setText("이름가져오기");
        
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(reserseat[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    
     void setSeat(){
         int count = 0;
        for (int k = 0; k < max; k++) {
                seatnumber=null;
                seatnumber = Integer.toString(((count+1)+(row*8))) + "번 좌석";
                seatcheckbox[k] = new JCheckBox();
                seatcheckbox[k].setText(seatnumber);
                if (count>=4) {
                    seatcheckbox[k].setBounds(80*count+130,50*row+170,80,30);
                }
                else
                    seatcheckbox[k].setBounds(80*count+80,50*row+170,80,30);
                seatcheckbox[k].setVisible(true);
                add(seatcheckbox[k]);
                seatcheckbox[k].addItemListener(new clickseat(((count+1)+(row*8))));
                
                if (row*8+count==max-1) break;
                if (count==7){
                    row++;
                    count=-1;
                }
                count++;
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
        seattotal = new javax.swing.JLabel();
        changebtn = new javax.swing.JButton();
        resertimearea = new javax.swing.JLabel();
        resertime = new javax.swing.JLabel();
        nextbtn = new javax.swing.JButton();
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

        seattotal.setForeground(new java.awt.Color(255, 0, 51));
        seattotal.setText("0/30");

        changebtn.setText("시간 변경");
        changebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtnActionPerformed(evt);
            }
        });

        resertimearea.setText("예약 시간:");

        resertime.setText("17:00 ~ 18:00");

        nextbtn.setText("다음");
        nextbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextbtnActionPerformed(evt);
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
                        .addComponent(seattotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(responsiblename)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nextbtn)
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
                    .addComponent(seattotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn)
                    .addComponent(resertimearea)
                    .addComponent(resertime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextbtn)
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

    private void nextbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextbtnActionPerformed
        if ("ok".equals(remoteControl.A_ButtonWasPushed(1))) {
            showMessageDialog(null, Message);            
        }
        String time[] = resertime.getText().split("~");
        starttime = time[0].trim();
        endtime = time[1].trim();
        System.out.println(starttime);
        System.out.println(endtime);
        ReservationDTO rdto = new ReservationDTO(dao.getReserLength(),Integer.parseInt(seatnumber)-1,responsiblename.getText(),classnumber.getText(),starttime,endtime,"-","0");
        boolean checkReservation = dao.InsertReservation(rdto);  
    }//GEN-LAST:event_nextbtnActionPerformed

    private void changebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtn1ActionPerformed
        // TODO add your handling code here:
        starttime = starttimebox.getSelectedItem().toString();
        endtime = endtimebox.getSelectedItem().toString();  
        if (Integer.parseInt(starttime) > Integer.parseInt(endtime)) showMessageDialog(null, "시작 시간이 종료 시간보다 클 수는 없습니다.");
        else if (Integer.parseInt(starttime) == Integer.parseInt(endtime)) showMessageDialog(null, "시작 시간이 종료 시간과 같을 수는 없습니다.");
        else{
            editTime.dispose();
            resertime.setText(starttime+":00 ~ "+endtime+":00");
            for (int i = 0; i < max; i++) {
                int icount=0;
                for (int j = Integer.parseInt(starttime)-17; j < Integer.parseInt(endtime)-17; j++) {
                    if (reserseat[i][j]==true) {                        
                        seatcheckbox[i].setEnabled(false);
                        seatcheckbox[i].setSelected(false);   
                        icount++;
                        break;
                    }
                }// 시간표랑 비교하는 알고리즘
                if (icount==0) {
                    seatcheckbox[i].setEnabled(true);
                }
            }
            
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
            java.util.logging.Logger.getLogger(tmpafterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tmpafterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tmpafterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tmpafterReserve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new tmpafterReserve().setVisible(true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jlabel;
    private javax.swing.JButton nextbtn;
    public javax.swing.JLabel resertime;
    private javax.swing.JLabel resertimearea;
    private javax.swing.JLabel responsiblename;
    private javax.swing.JLabel seattotal;
    private javax.swing.JLabel settotal;
    private javax.swing.JComboBox<String> starttimebox;
    // End of variables declaration//GEN-END:variables
}

