package deu.cse.team.reservation;

import deu.cse.team.command.RemoteControl;
import deu.cse.team.command.Reservation;
import deu.cse.team.command.ReservationCancelCommand;
import deu.cse.team.command.ReservationOkCommand;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JCheckBox;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Seongchan
 */
public class afterReserve extends javax.swing.JFrame {

    /**
     * 2022.11.07 [최초작성자 20183207 김성찬] 사용자 계정관리
     */
    boolean isselected = false;
    int max = 40;
    int row = 0;
    int headcount = 1;
    String id;
    int checkboxcount = 0; //좌석 선택한 수 체크
    String starttime;
    String endtime;
    ArrayList<Integer> reserseatnumber = new ArrayList<Integer>(max);
    String seatnumber;
    String Message = "예약 완료";
    //"DB에서 이용규칙 가져오기. 관리자는 이용수칙을 DB에 저장하고 수정도 가능해야함";
    RemoteControl remoteControl = new RemoteControl();
    Reservation reservation = new Reservation();
    ReservationOkCommand reservationOk = new ReservationOkCommand(reservation);
    ReservationCancelCommand reservationCancel = new ReservationCancelCommand(reservation);
    JCheckBox[] seatcheckbox = new JCheckBox[max];
    Calendar c = Calendar.getInstance();
    DAO dao = DAO.getInstance();

    boolean[][] reserseat = new boolean[max][9];
    List<ReservationDTO> rdto;

    public afterReserve(String id, String starttime, String endtime, int headcount, int max, String classnumber) {
        initComponents();
        System.out.println("생성");
        this.id = id;
        this.starttime = starttime;
        this.endtime = endtime;
        this.max = max;
        this.headcount = headcount;
        responsiblename.setText(id);
        classnumberarea.setText(classnumber);
        resertime.setText(starttime + ":00 ~ " + endtime + ":00");
        remoteControl.setCommand(1, reservationOk, reservationCancel);
        setSeat();
        rdto = dao.getclassReserList(classnumber);
        getreserseat();
        nextbtn.setEnabled(true);
        for (int j = 0; j < max; j++) {
            seatcheckbox[j].setEnabled(true);
            seatcheckbox[j].setSelected(false);
        }
        int resercount = 0;
        for (int i = 0; i < max; i++) {
            for (int j = Integer.parseInt(starttime) - 17; j < Integer.parseInt(endtime) - 17; j++) {
                if (reserseat[i][j] == true) {
                    seatcheckbox[i].setEnabled(false);
                    seatcheckbox[i].setSelected(false);
                    resercount++;
                    break;
                }

            }// 예약이랑 비교하는 알고리즘
            seattotal.setText(resercount + "/" + max);
        }
    }

    void getreserseat() {
        int numberValue;
        int reserStartValue;
        int reserEndValue;
        String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
        for (int i = 0; i < rdto.size(); i++) {
            numberValue = rdto.get(i).getSeat_number();
            reserStartValue = Integer.parseInt(rdto.get(i).getReser_starttime().split(":")[0]);
            System.out.println("start = "+reserStartValue);            
            System.out.println(today.equals(rdto.get(i).getReser_date()) && rdto.get(i).getOk().equals("1"));
            if (reserStartValue >=17 && today.equals(rdto.get(i).getReser_date()) && rdto.get(i).getOk().equals("1")) { // 예약완료되면 1 + 오늘 예약인지 확인
                reserEndValue = Integer.parseInt(rdto.get(i).getReser_endtime().split(":")[0]);
                System.out.println(reserEndValue);
                for (int j = reserStartValue - 17; j < reserEndValue - 17; j++) {
                    reserseat[numberValue][j] = true;//예약이 되어있다.
                }
            }
        }
//                        }
    }

    void setSeat() {
        int count = 0;
        for (int k = 0; k < max; k++) {
            seatnumber = null;
            seatnumber = Integer.toString(((count + 1) + (row * 8))) + "번 좌석";
            seatcheckbox[k] = new JCheckBox();
            seatcheckbox[k].setText(seatnumber);
            if (count >= 4) {
                seatcheckbox[k].setBounds(80 * count + 130, 50 * row + 170, 80, 30);
            } else {
                seatcheckbox[k].setBounds(80 * count + 80, 50 * row + 170, 80, 30);
            }
            seatcheckbox[k].setVisible(true);
            add(seatcheckbox[k]);

            seatcheckbox[k].addItemListener(new clickseat(((count + 1) + (row * 8))));

            if (row * 8 + count == max - 1) {
                break;
            }
            if (count == 7) {
                row++;
                count = -1;
            }
            count++;
        }
    }

    public class clickseat implements ItemListener {

        String value;

        clickseat(int k) {
            this.value = Integer.toString(k);

        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == 1) {
                checkboxcount++;
                reserseatnumber.add(Integer.parseInt(value));
            } else {
                checkboxcount--;
                reserseatnumber.remove(value);
            }
            isselected = true;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        responsiblename = new javax.swing.JLabel();
        classtext = new javax.swing.JLabel();
        settotal = new javax.swing.JLabel();
        seattotal = new javax.swing.JLabel();
        resertimearea = new javax.swing.JLabel();
        resertime = new javax.swing.JLabel();
        nextbtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        classnumberarea = new javax.swing.JLabel();

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

        starttimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9", "10", "11", "12", "13", "14", "15", "16", "17" }));

        endtimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12", "13", "14", "15", "16", "17" }));

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

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel31.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel31.setText("관리자 책임자:");

        responsiblename.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        responsiblename.setForeground(new java.awt.Color(0, 0, 255));
        responsiblename.setText("조교");

        classtext.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
        classtext.setText("강의실");

        settotal.setText("예약된 좌석 수:");

        seattotal.setForeground(new java.awt.Color(255, 0, 51));
        seattotal.setText("0/30");

        resertimearea.setText("예약 시간:");

        resertime.setText("시간을 선택하세요");

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

        classnumberarea.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
        classnumberarea.setText("915");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 405, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(settotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seattotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classtext)
                        .addGap(86, 86, 86)))
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(responsiblename)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextbtn)
                .addGap(72, 72, 72)
                .addComponent(jButton2)
                .addGap(291, 291, 291))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(330, 330, 330)
                    .addComponent(classnumberarea)
                    .addContainerGap(425, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(responsiblename)
                            .addComponent(settotal)
                            .addComponent(seattotal)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(classtext)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resertimearea)
                    .addComponent(resertime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextbtn)
                    .addComponent(jButton2))
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(classnumberarea)
                    .addContainerGap(386, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if ("cancel".equals(remoteControl.B_ButtonWasPushed(1))) {
            showMessageDialog(null, "예약 취소");
            dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void nextbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextbtnActionPerformed

//        for (int i = 0; i < max; i++) {
//            if (seatcheckbox[i].getSelectedObjects()==null) {
//            System.out.println("1");    
//            }
//            
//        }
        if (isselected && checkboxcount == headcount) { //단체예약이면 단체로 바꿔줄 예정
            if ("ok".equals(remoteControl.A_ButtonWasPushed(1))) {
                showMessageDialog(null, Message);
            }
            
            String time[] = resertime.getText().split("~");
            starttime = time[0].trim();
            endtime = time[1].trim();
            String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
            for (int i = 0; i < headcount; i++) {
                ReservationDTO rdto = new ReservationDTO(dao.getReserLength(), reserseatnumber.get(i) - 1, id, classnumberarea.getText(), today, starttime, endtime, "-", "0");
                boolean checkReservation = dao.InsertReservation(rdto);
                Notice notice = new Notice();
                notice.setVisible(true);
                notice.setSize(359, 300);
            }

            dispose();
        } else {
            showMessageDialog(null, "예약 명단보다 선택한 좌석이 많거나 적습니다");
        }

    }//GEN-LAST:event_nextbtnActionPerformed

    private void changebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtn1ActionPerformed


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
                new afterReserve("20183207", "9", "17", 1, 40, "918").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton changebtn1;
    private javax.swing.JLabel classnumberarea;
    private javax.swing.JLabel classtext;
    private javax.swing.JDialog editTime;
    private javax.swing.JComboBox<String> endtimebox;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
