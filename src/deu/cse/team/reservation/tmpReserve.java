package deu.cse.team.reservation;

import deu.cse.team.command.HeadcountGui;
import deu.cse.team.mainmenu.*;
import deu.cse.team.command.RemoteControl;
import deu.cse.team.command.Reservation;
import deu.cse.team.command.ReservationCancelCommand;
import deu.cse.team.command.ReservationOkCommand;
import deu.cse.team.singleton.ClassInformationDTO;
import deu.cse.team.singleton.ClassTimetableDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import deu.cse.team.state.SeatChecking;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Seongchan
 */
public class tmpReserve extends javax.swing.JFrame {

    /**
     * 2022.11.07 [최초작성자 20183207 김성찬] 사용자 계정관리
     */
    int max = 40;
    int resercount = 0;
    Calendar c = Calendar.getInstance();
    String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
    int checkboxcount = 0; //좌석 선택한 수 체크
    int index = 0;
    int row = 0;
    boolean iscount = false;
    String starttime;
    String endtime;
    String seatnumber;
    //"DB에서 이용규칙 가져오기. 관리자는 이용수칙을 DB에 저장하고 수정도 가능해야함";
    ArrayList<Integer> reserseatnumber = new ArrayList<Integer>(max);
    DAO dao = DAO.getInstance();
    JCheckBox[] seatcheckbox = new JCheckBox[max];
    int dayofWeek = c.get(Calendar.DAY_OF_WEEK);//요일 판단 일 ~ 토 = 1 ~ 7
    boolean[] classTime = new boolean[9];//수업시간있는지 확인하는 객체  | true = 수업 O false = 수업 X    
    List<ClassTimetableDTO> cdto = dao.getTimetableList();
    List<ClassInformationDTO> cid = dao.getClassInformation(); //디비에서 가져왔다가졍
    boolean[][][] reserseat = new boolean[cid.size()][max][16]; //좌석수,예약시간 9~24
    JLabel[] seatstatus = new JLabel[max];
    String classnumber = "915";//최종 제출시 911로 수정할 것
    List<ReservationDTO> rdto = dao.getclassReserList(classnumber);

    String id;
    boolean isselected = false;

    public tmpReserve(String id) {
        this.id = id;
        initComponents();
        max = cid.get(index).getMaxseat();

        getreserseat();
    }

    void getreserseat() {
        int numberValue;
        int reserStartValue;
        int reserEndValue;
        
        String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
        for (int k = 0; k < cid.size(); k++) {
            System.out.println(k);
            rdto = dao.getclassReserList(cid.get(k).getClassnumber());
            for (int i = 0; i < rdto.size(); i++) {
                numberValue = rdto.get(i).getSeat_number();
                reserStartValue = Integer.parseInt(rdto.get(i).getReser_starttime().split(":")[0]);
                reserEndValue = Integer.parseInt(rdto.get(i).getReser_endtime().split(":")[0]);
                if (today.equals(rdto.get(i).getReser_date()) && rdto.get(i).getOk().equals("1")) { // 예약완료되면 1 + 오늘 예약인지 확인                
                    for (int j = reserStartValue - 9; j < reserEndValue - 9; j++) {
                        reserseat[k][numberValue][j] = true;//예약이 되어있다.  
                        
                    }
                }
            }
        }
//                        }
    }

    void getSchedule(int index) {
        if (dayofWeek == 1) {
            dayofWeek = 8;
        }
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

    void setSeat(int max) {
        int count = 0;
        for (int k = 0; k < max; k++) {
            seatnumber = null;
            seatnumber = Integer.toString(((count + 1) + (row * 8))) + "번 좌석";
            seatcheckbox[k] = new JCheckBox();
            seatstatus[k] = new JLabel();
            seatcheckbox[k].setText(seatnumber);
            if (count >= 4) {
                seatcheckbox[k].setBounds(80 * count + 130, 50 * row + 170, 80, 30);
                seatstatus[k].setBounds(80 * count + 150, 50 * row + 190, 80, 30);
            } else {
                seatcheckbox[k].setBounds(80 * count + 80, 50 * row + 170, 80, 30);
                seatstatus[k].setBounds(80 * count + 100, 50 * row + 190, 80, 30);
            }
            add(seatcheckbox[k]);
            add(seatstatus[k]);

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
                reserseatnumber.remove(Integer.parseInt(value));
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
        settotal = new javax.swing.JLabel();
        seattotal = new javax.swing.JLabel();
        changebtn = new javax.swing.JButton();
        resertimearea = new javax.swing.JLabel();
        resertime = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        selectclassnumber = new javax.swing.JComboBox<>();
        number = new javax.swing.JLabel();
        okreser = new javax.swing.JButton();

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

        starttimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

        endtimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

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

        settotal.setText("예약된 좌석의 수");

        seattotal.setForeground(new java.awt.Color(255, 0, 51));
        seattotal.setText("0/30");

        changebtn.setText("시간 변경");
        changebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtnActionPerformed(evt);
            }
        });

        resertimearea.setText("예약 시간:");

        resertime.setText("시간을 선택하세요");

        jButton2.setText("이전");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        selectclassnumber.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        selectclassnumber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915 강의실", "916 강의실", "911 강의실", "918 강의실" }));
        selectclassnumber.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectclassnumberItemStateChanged(evt);
            }
        });
        selectclassnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectclassnumberActionPerformed(evt);
            }
        });

        number.setText("강의실");

        okreser.setText("예약");
        okreser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okreserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okreser)
                .addGap(90, 90, 90)
                .addComponent(jButton2)
                .addGap(291, 291, 291))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(seattotal))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(selectclassnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(number)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(selectclassnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(number))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settotal)
                    .addComponent(seattotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn)
                    .addComponent(resertimearea)
                    .addComponent(resertime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(okreser))
                .addGap(29, 29, 29))
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
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    void setseatstatus() {
        for (int i = 0; i < max; i++) {
            System.out.println(i);
            boolean bo = false;
            SeatChecking sc;
            for (int j = Integer.parseInt(starttime) - 9; j < Integer.parseInt(endtime) - 9; j++) {
                if (reserseat[index][i][j] == true) {
                    bo = true;
                    System.out.println(bo);
                    resercount++;
                    break;
                }

            }// 예약이랑 비교하는 알고리즘
            if (bo) {
                sc = new SeatChecking(true, seatcheckbox[i], seatstatus[i]);
            } else {
                sc = new SeatChecking(false, seatcheckbox[i], seatstatus[i]);
            }
            sc.toset();
            seattotal.setText(resercount + "/" + max);
        }
    }

    void checkingclassschedule() {
        for (; index < cid.size(); index++) {
            getSchedule(index);
            iscount = false;
            int end = Integer.parseInt(endtime) - 9;
            if (end >= 9) {
                end = 8;
            }
            for (int i = Integer.parseInt(starttime) - 9; i < end; i++) {
                if (classTime[i] == true) {
                    iscount = true;

                }
            }// 시간표랑 비교하는 알고리즘
            if (!iscount) {
                break;
            } else {
                showMessageDialog(null, cid.get(index).getClassnumber() + "강의실은 선택한 시간사이에 수업이 있습니다.");
            }
        }
    }
    private void changebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtn1ActionPerformed
        // TODO add your handling code here:
        index = 0;
        starttime = starttimebox.getSelectedItem().toString();
        endtime = endtimebox.getSelectedItem().toString();
        boolean iscount = false;
        if (Integer.parseInt(starttime) > Integer.parseInt(endtime)) {
            showMessageDialog(null, "시작 시간이 종료 시간보다 클 수는 없습니다.");
        } else if (Integer.parseInt(starttime) == Integer.parseInt(endtime)) {
            showMessageDialog(null, "시작 시간이 종료 시간과 같을 수는 없습니다.");
        } else {
            editTime.dispose();
            resertime.setText(starttime + ":00 ~ " + endtime + ":00");
            for (; index < cid.size(); index++) {
                getSchedule(index);
                iscount = false;
                int end = Integer.parseInt(endtime) - 9;
                if (end >= 9) {
                    end = 8;
                }
                for (int i = Integer.parseInt(starttime) - 9; i < end; i++) {
                    if (classTime[i] == true) {
                        iscount = true;
                    }
                }// 시간표랑 비교하는 알고리즘
                if (!iscount) {
                    break;
                } else {
                    showMessageDialog(null, cid.get(index).getClassnumber() + "강의실은 선택한 시간사이에 수업이 있습니다.");
//                    for (int i = 0; i < max; i++) {
//                        SeatChecking sc = new SeatChecking(true, seatcheckbox[i], seatstatus[i]);
//                    }
//좌석 보여주는 것 컨트롤 필요
                }
            }
            //수업이 없는 경우
            for (; index < cid.size(); index++) {

                checkingclassschedule();
                if (index == cid.size()) {
                    break;
                }
                System.out.println("index = " + index);
                if (!iscount) {
                    resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                    if (resercount == cid.get(index).getMaxseat()) {
                        showMessageDialog(null, cid.get(index).getClassnumber() + " 강의은 모두예약되어있습니다.");
                    } else if (resercount >= 25) {
                        showMessageDialog(null, "선택한 시간에 예약한 사람이 25명이 넘습니다.\n선택예약으로 이동합니다.");
                        HeadcountGui h = new HeadcountGui(starttime, endtime, "before", id, index);
                        //예약시 사람수 체크해서 알고리즘 ㄲ
                        h.setVisible(true);
                        break;
                    } else if (resercount < 25) {

                        max = cid.get(index).getMaxseat();
                        setSeat(this.max);
                        setseatstatus();
                        break;
                    }
                }
            }
            if (index == cid.size()) {
                showMessageDialog(null, "현재 예약가능한 강의실이 없습니다.");
            } else {
                number.setText(cid.get(index).getClassnumber());
            }

        }

    }//GEN-LAST:event_changebtn1ActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        editTime.dispose();
    }//GEN-LAST:event_cancelbtnActionPerformed

    private void selectclassnumberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectclassnumberItemStateChanged
        // TODO add your handling code here:
        String select = selectclassnumber.getSelectedItem().toString().split(" ")[0];
        
        getreserseat();
        getSchedule(selectclassnumber.getSelectedIndex());
        max = cid.get(selectclassnumber.getSelectedIndex()).getMaxseat();
        resertime.setText("시간을 선택하세요");
        for (int i = 0; i < max; i++) {
            seatcheckbox[i].setEnabled(false);
            seatcheckbox[i].setSelected(false);
        }
        seattotal.setText("0/" + max);
    }//GEN-LAST:event_selectclassnumberItemStateChanged

    private void selectclassnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectclassnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectclassnumberActionPerformed

    private void okreserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okreserActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_okreserActionPerformed

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
            java.util.logging.Logger.getLogger(tmpReserve.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tmpReserve.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tmpReserve.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tmpReserve.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new tmpReserve("20183207").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton changebtn;
    private javax.swing.JButton changebtn1;
    private javax.swing.JDialog editTime;
    private javax.swing.JComboBox<String> endtimebox;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel number;
    private javax.swing.JButton okreser;
    public javax.swing.JLabel resertime;
    private javax.swing.JLabel resertimearea;
    private javax.swing.JLabel seattotal;
    private javax.swing.JComboBox<String> selectclassnumber;
    private javax.swing.JLabel settotal;
    private javax.swing.JComboBox<String> starttimebox;
    // End of variables declaration//GEN-END:variables
}
