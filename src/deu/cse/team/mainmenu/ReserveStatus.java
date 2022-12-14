/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package deu.cse.team.mainmenu;

import deu.cse.team.reservation.Reserve;
import deu.cse.team.singleton.ClassInformationDTO;
import deu.cse.team.singleton.ClassTimetableDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import deu.cse.team.state.SeatChecking;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author PC
 */
public class ReserveStatus extends javax.swing.JPanel {

    /**
     * 2022.11.07 [최초작성자 20183207 김성찬] 사용자 계정관리
     */
    int max = 40;
    int index = 0;
    int row = 0;
    int count = 0;
    String starttime;
    String endtime;
    String seatnumber;
    //"DB에서 이용규칙 가져오기. 관리자는 이용수칙을 DB에 저장하고 수정도 가능해야함";
    List<SeatChecking> sc = new ArrayList<SeatChecking>();
    List<JLabel> seatstatus = new ArrayList<JLabel>();
    DAO dao = DAO.getInstance();
//    JCheckBox[] seatcheckbox = new JCheckBox[max];
    List<JCheckBox> seatcheckbox = new ArrayList<JCheckBox>();
    Calendar c = Calendar.getInstance();
    int dayofWeek = c.get(Calendar.DAY_OF_WEEK);//요일 판단 일 ~ 토 = 1 ~ 7
    boolean[] classTime = new boolean[9];//수업시간있는지 확인하는 객체  | true = 수업 O false = 수업 X    
    List<ClassTimetableDTO> cdto = dao.getTimetableList();
//    boolean[][] reserseat = new boolean[max][16]; //좌석수,예약시간 9~24
    List<ClassInformationDTO> cid = dao.getClassInformation();
    boolean[][][] reserseat = new boolean[cid.size()][max][16]; //좌석수,예약시간 9~24
    String classnumber = "915";//최종 제출시 911로 수정할 것
    List<ReservationDTO> rdto = dao.getclassReserList(classnumber);

    public ReserveStatus() {
        initComponents();
        max = cid.get(0).getMaxseat();
        setSeat();
        getreserseat();
        getSchedule(0);        
        for (int i = 0; i < max; i++) {
            seatcheckbox.get(i).setVisible(true);
            seatcheckbox.get(i).setEnabled(false);
            seatstatus.get(i).setVisible(false);
        }
        for (int i = max; i < 40; i++) {
            seatcheckbox.get(i).setVisible(false);
            seatstatus.get(i).setVisible(false);
        }
    }

    void setSeat() {
        int count = 0;
        for (int k = 0; k < 40; k++) {
            seatnumber = null;
            seatnumber = Integer.toString(((count + 1) + (row * 8))) + "번 좌석";
            JCheckBox tmpseatcheckbox = new JCheckBox();
            JLabel tmpseatstatus = new JLabel();
            tmpseatcheckbox.setText(seatnumber);
            if (count >= 4) {
                tmpseatcheckbox.setBounds(80 * count + 130, 50 * row + 170, 80, 30);
                tmpseatstatus.setBounds(80 * count + 150, 50 * row + 190, 80, 30);
            } else {
                tmpseatcheckbox.setBounds(80 * count + 80, 50 * row + 170, 80, 30);
                tmpseatstatus.setBounds(80 * count + 100, 50 * row + 190, 80, 30);
            }
            seatstatus.add(tmpseatstatus);
            seatcheckbox.add(tmpseatcheckbox);
            add(seatcheckbox.get(k));
            add(seatstatus.get(k));
            seatcheckbox.get(k).setVisible(false);
            seatcheckbox.get(k).setFocusable(false);
            seatstatus.get(k).setVisible(false);
            SeatChecking tmpsc = new SeatChecking(seatcheckbox.get(k), seatstatus.get(k)); //전부 예약 가능한 상태
            sc.add(tmpsc);
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

    void setseatstatus(int max) {
        count = 0;
        for (int i = 0; i < max; i++) {
            boolean bool = false;
            for (int j = Integer.parseInt(starttime) - 9; j < Integer.parseInt(endtime) - 9; j++) {
                if (reserseat[index][i][j] == true) {
                    bool = true;
                    break;
                }
            }// 예약이랑 비교하는 알고리즘
            if (bool) {
                sc.get(i).setState(sc.get(i).getUsingState());
                count++;
            } else {
                sc.get(i).setState(sc.get(i).getEmptyState());
            }
            sc.get(i).toset();
        }
        for (int j = max; j < 40; j++) {
            sc.get(j).setState(sc.get(j).getNotusing());
            sc.get(j).toset();
        }
    }

    void getreserseat() {
        int numberValue;
        int reserStartValue;
        int reserEndValue;
        String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
        for (int k = 0; k < cid.size(); k++) {
            rdto = dao.getclassReserList(cid.get(k).getClassnumber());
            for (int i = 0; i < rdto.size(); i++) {
                numberValue = rdto.get(i).getSeat_number();
                numberValue -= 1;

                reserStartValue = Integer.parseInt(rdto.get(i).getReser_starttime().split(":")[0]);
                reserEndValue = Integer.parseInt(rdto.get(i).getReser_endtime().split(":")[0]);
                if (today.equals(rdto.get(i).getReser_date()) && rdto.get(i).getOk().equals("1")) { // 예약완료되면 1 + 오늘 예약인지 확인                
                    for (int j = reserStartValue - 9; j < reserEndValue - 9; j++) {
                        reserseat[k][numberValue][j] = true;//예약이 되어있다.
                    }
                }
            }
        }
//        for (int i = 0; i < rdto.size(); i++) {
//            numberValue = rdto.get(i).getSeat_number();
//            numberValue -= 1;
//            reserStartValue = Integer.parseInt(rdto.get(i).getReser_starttime().split(":")[0]);
//            reserEndValue = Integer.parseInt(rdto.get(i).getReser_endtime().split(":")[0]);
//            if (today.equals(rdto.get(i).getReser_date()) && rdto.get(i).getOk().equals("1")) { // 예약완료되면 1 + 오늘 예약인지 확인                
//                for (int j = reserStartValue - 9; j < reserEndValue - 9; j++) {
//                    reserseat[k][numberValue][j] = true;//예약이 되어있다.               
//                }
//            }
//        }
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
//
//    void setSeat() {
//        int count = 0;
//        for (int k = 0; k < max; k++) {
//            seatnumber = null;
//            seatnumber = Integer.toString(((count + 1) + (row * 8))) + "번 좌석";
//            seatcheckbox[k] = new JCheckBox();
//            seatcheckbox[k].setText(seatnumber);
//            if (count >= 4) {
//                seatcheckbox[k].setBounds(80 * count + 130, 50 * row + 170, 80, 30);
//            } else {
//                seatcheckbox[k].setBounds(80 * count + 80, 50 * row + 170, 80, 30);
//            }
//            seatcheckbox[k].setVisible(true);
//            seatcheckbox[k].setFocusable(false);
//            add(seatcheckbox[k]);
//
//            if (row * 8 + count == max - 1) {
//                break;
//            }
//            if (count == 7) {
//                row++;
//                count = -1;
//            }
//            count++;
//        }
//    }

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
        selectclassnumber = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

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

        settotal.setText("예약된 좌석의 수");

        seattotal.setForeground(new java.awt.Color(255, 0, 51));
        seattotal.setText("시간을 선택하세요");

        changebtn.setText("시간 변경");
        changebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtnActionPerformed(evt);
            }
        });

        resertimearea.setText("예약 시간:");

        resertime.setText("시간을 선택하세요");

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

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        jLabel2.setText("좌석 현황 조회");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(234, 234, 234)
                        .addComponent(selectclassnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectclassnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settotal)
                    .addComponent(seattotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn)
                    .addComponent(resertimearea)
                    .addComponent(resertime))
                .addContainerGap(371, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtnActionPerformed
        cid = dao.getClassInformation();
        editTime.setVisible(true);
        editTime.setLocationRelativeTo(this);
        editTime.setSize(450, 400);
    }//GEN-LAST:event_changebtnActionPerformed

    private void selectclassnumberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectclassnumberItemStateChanged
        // TODO add your handling code here:
        String select = selectclassnumber.getSelectedItem().toString().split(" ")[0];
        rdto = dao.getclassReserList(select);
        getreserseat();
        getSchedule(selectclassnumber.getSelectedIndex());
        index = selectclassnumber.getSelectedIndex();
        cid = dao.getClassInformation();
        max = cid.get(selectclassnumber.getSelectedIndex()).getMaxseat();
        resertime.setText("시간을 선택하세요");
        
        for (int i = 0; i < max; i++) {
            seatcheckbox.get(i).setVisible(true);
            seatcheckbox.get(i).setEnabled(false);
            seatstatus.get(i).setVisible(false);
        }
        for (int i = max; i < 40; i++) {
            seatcheckbox.get(i).setVisible(false);
            seatstatus.get(i).setVisible(false);
        }
        seattotal.setText("시간을 선택하세요");
    }//GEN-LAST:event_selectclassnumberItemStateChanged

    private void selectclassnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectclassnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectclassnumberActionPerformed

    private void changebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtn1ActionPerformed
        // TODO add your handling code here:
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
            if (Integer.parseInt(starttime) <= 17) {
                iscount = false;
                int end = Integer.parseInt(endtime) - 9;
                if (end >= 9) {
                    end = 8;
                }
                for (int i = Integer.parseInt(starttime) - 9; i < end; i++) {
                    if (classTime[i] == true) {
                        iscount = true;
                        for (int j = 0; j < max; j++) {
                            seatcheckbox.get(j).setVisible(true);
                            seatcheckbox.get(j).setSelected(false);
                            seatcheckbox.get(j).setEnabled(false);
                            seatstatus.get(j).setVisible(false);
                        }
                        showMessageDialog(null, "선택한 시간사이에 수업이 있습니다.");
                        break;
                    }
                }// 시간표랑 비교하는 알고리즘
            }
            if (!iscount) {
                max = cid.get(index).getMaxseat();
                setseatstatus(this.max);
                seattotal.setText(count + "/" + max);

            }

        }
    }//GEN-LAST:event_changebtn1ActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        editTime.dispose();
    }//GEN-LAST:event_cancelbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton changebtn;
    private javax.swing.JButton changebtn1;
    private javax.swing.JDialog editTime;
    private javax.swing.JComboBox<String> endtimebox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jlabel;
    public javax.swing.JLabel resertime;
    private javax.swing.JLabel resertimearea;
    private javax.swing.JLabel seattotal;
    private javax.swing.JComboBox<String> selectclassnumber;
    private javax.swing.JLabel settotal;
    private javax.swing.JComboBox<String> starttimebox;
    // End of variables declaration//GEN-END:variables
}
