/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package deu.cse.team.reservation;

import deu.cse.team.command.HeadcountConfirm;
import deu.cse.team.command.IndividualCommand;
import deu.cse.team.mainmenu.*;
import deu.cse.team.command.RemoteControl;
import deu.cse.team.command.Reservation;
import deu.cse.team.command.ReservationCancelCommand;
import deu.cse.team.command.ReservationOkCommand;
import deu.cse.team.command.TeamCommand;
import deu.cse.team.singleton.ClassInformationDTO;
import deu.cse.team.singleton.ClassTimetableDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import deu.cse.team.state.SeatChecking;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author PC
 */
public class Reserve extends javax.swing.JPanel {

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
    String Message = "예약 완료";
    String seatnumber;
    //"DB에서 이용규칙 가져오기. 관리자는 이용수칙을 DB에 저장하고 수정도 가능해야함";
    ArrayList<String> reserseatnumber = new ArrayList<String>(max);
    DAO dao = DAO.getInstance();
    String headCount = "1";
    int dayofWeek = c.get(Calendar.DAY_OF_WEEK);//요일 판단 일 ~ 토 = 1 ~ 7
    boolean[] classTime = new boolean[9];//수업시간있는지 확인하는 객체  | true = 수업 O false = 수업 X    
    List<ClassTimetableDTO> cdto = dao.getTimetableList();
    List<ClassInformationDTO> cid = dao.getClassInformation(); //디비에서 가져왔다가졍
    boolean[][][] reserseat = new boolean[cid.size()][max][16]; //좌석수,예약시간 9~24

    String classnumber = "915";//최종 제출시 911로 수정할 것
    List<ReservationDTO> rdto = dao.getclassReserList(classnumber);
    List<SeatChecking> sc = new ArrayList<SeatChecking>();
    List<JCheckBox> seatcheckbox = new ArrayList<JCheckBox>();
    List<JLabel> seatstatus = new ArrayList<JLabel>();
    HeadcountConfirm headcountConfirm = new HeadcountConfirm();
    IndividualCommand individual = new IndividualCommand(headcountConfirm);
    TeamCommand team = new TeamCommand(headcountConfirm);
    String id;
    boolean isselected = false;
    RemoteControl remoteControl = new RemoteControl();
    int count = 0;

    public Reserve(String id) {
        remoteControl.setCommand(0, individual, team);
        this.id = id;
        initComponents();
        max = cid.get(index).getMaxseat();
        setSeat();
        getreserseat();
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
                if (rdto.size() != 0) {
                    reserStartValue = Integer.parseInt(rdto.get(i).getReser_starttime().split(":")[0]);
                    reserEndValue = Integer.parseInt(rdto.get(i).getReser_endtime().split(":")[0]);
                    if (today.equals(rdto.get(i).getReser_date()) && rdto.get(i).getOk().equals("1")) { // 예약완료되면 1 + 오늘 예약인지 확인                
                        for (int j = reserStartValue - 9; j < reserEndValue - 9; j++) {
                            reserseat[k][numberValue][j] = true;//예약이 되어있다.
                        }
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
            seatstatus.get(k).setVisible(false);
            SeatChecking tmpsc = new SeatChecking(seatcheckbox.get(k), seatstatus.get(k)); //전부 예약 가능한 상태
            sc.add(tmpsc);

            seatcheckbox.get(k).addItemListener(new clickseat(((count + 1) + (row * 8))));
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
            System.out.println(value);
            if (e.getStateChange() == 1) {
                checkboxcount++;
                reserseatnumber.add(value);
            } else {
                checkboxcount--;
                reserseatnumber.remove((value));
            }
            isselected = true;
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
                sc.get(i).using();
                count++;
            } else {
                sc.get(i).empty();
            }

            sc.get(i).toset();
        }
        for (int j = max; j < 40; j++) {
            sc.get(j).notuse();
            sc.get(j).toset();

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        usernumber = new javax.swing.JTextField();
        cancel1 = new javax.swing.JButton();
        nextbtn1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        editTime = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        changebtn1 = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        starttimebox = new javax.swing.JComboBox<>();
        endtimebox = new javax.swing.JComboBox<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        changebtn = new javax.swing.JButton();
        resertimearea = new javax.swing.JLabel();
        resertime = new javax.swing.JLabel();
        classnumberarea = new javax.swing.JLabel();
        okreser = new javax.swing.JButton();
        settotal = new javax.swing.JLabel();
        seattotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        adminid = new javax.swing.JLabel();

        jLabel5.setText("인원수:");

        usernumber.setEnabled(false);

        cancel1.setText("이전");

        nextbtn1.setText("다음");
        nextbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextbtn1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel6.setText("개인/팀 예약 설정");

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

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel6))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(nextbtn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancel1))
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usernumber, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addGap(37, 37, 37)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(usernumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel1)
                    .addComponent(nextbtn1))
                .addContainerGap(45, Short.MAX_VALUE))
        );

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

        starttimebox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

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

        setPreferredSize(new java.awt.Dimension(815, 504));

        changebtn.setText("시간 변경");
        changebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebtnActionPerformed(evt);
            }
        });

        resertimearea.setText("예약 시간:");

        resertime.setText("시간을 선택하세요");

        classnumberarea.setFont(new java.awt.Font("맑은 고딕", 1, 24)); // NOI18N
        classnumberarea.setText("915");

        okreser.setText("예약");
        okreser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okreserActionPerformed(evt);
            }
        });

        settotal.setText("예약된 좌석의 수");

        seattotal.setForeground(new java.awt.Color(255, 0, 51));
        seattotal.setText("시간을 선택하세요");

        jLabel2.setText("관리책임자");

        adminid.setForeground(new java.awt.Color(255, 0, 51));
        adminid.setText("조교");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(settotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seattotal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resertimearea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resertime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changebtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(adminid)
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(99, 99, 99))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(okreser))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(classnumberarea)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(classnumberarea, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(settotal)
                    .addComponent(seattotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changebtn)
                    .addComponent(resertimearea)
                    .addComponent(resertime)
                    .addComponent(adminid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addComponent(okreser)
                .addGap(29, 29, 29))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void changebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebtnActionPerformed
        getreserseat();       
        reserseatnumber.clear();
        editTime.setVisible(true);
        editTime.setLocationRelativeTo(this);
        editTime.setSize(450, 400);
    }//GEN-LAST:event_changebtnActionPerformed

    private void okreserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okreserActionPerformed
        // TODO add your handling code here:
        int head = Integer.parseInt(headCount);
        boolean isresered = false;
        isresered = dao.isuserreserd(id, today, starttime);
        //dao에 회원번호, 시작시간, 날짜 해서 오늘 해당시간에 예약 내역이 있는지 확인하고
        System.out.println(isresered);
        if (isresered)
            showMessageDialog(null, "해당시간에 이미 예약한 내역이 있습니다.");
        else {
            if (isselected && checkboxcount == head) {
                String time[] = resertime.getText().split("~");
                ReservationDTO rdto;
                String today = Integer.toString(c.get(Calendar.YEAR)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.DATE));
                for (int i = 0; i < head; i++) {
                    System.out.println("number = " + reserseatnumber.get(0));
//                sc.get().setState(sc.get(reserseatnumber.get(i) - i).getUsingState());
                    if (Integer.parseInt(endtime) > 17) {
                        rdto = new ReservationDTO(dao.getReserLength(), Integer.parseInt(reserseatnumber.get(0)), id, classnumberarea.getText(), today, starttime + ":00", endtime + ":00", "-", "0");
                    } else {
                        rdto = new ReservationDTO(dao.getReserLength(), Integer.parseInt(reserseatnumber.get(0)), id, classnumberarea.getText(), today, starttime + ":00", endtime + ":00", "조교", "1");
                        seatcheckbox.get(Integer.parseInt(reserseatnumber.get(0)) - 1).setEnabled(false);
                        seatstatus.get(Integer.parseInt(reserseatnumber.get(0)) - 1).setText("예약완료");
                    }
                    boolean checkReservation = dao.InsertReservation(rdto);
                    seatcheckbox.get(Integer.parseInt(reserseatnumber.get(0)) - 1).setSelected(false);
                }
                if (Integer.parseInt(endtime) > 17) {
                    showMessageDialog(null, "예약시간이 17시 이후여서 승인이 필요합니다");
                } else {

                }
                reserseatnumber.clear();
                Notice notice = new Notice();
                notice.setSize(400, 300);
                notice.setLocationRelativeTo(this);
                notice.setVisible(true);
            } else {
                showMessageDialog(null, "예약 명단보다 선택한 좌석이 많거나 적습니다");
            }
        }
    }//GEN-LAST:event_okreserActionPerformed

    private void nextbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextbtn1ActionPerformed
        // TODO add your handling code here:
        int firstindex = index;
        //개인 예약 구현
        if (jRadioButton1.isSelected()) {
            headCount = remoteControl.A_ButtonWasPushed(0);
            //예약된 좌석수 확인
            //              팀예약 구현
            //              팀 :  방선택 권한 | 총 예약인원 + 팀인원 >= 방의 총 수용인원 => 다음방으로
            headCount = "1";
            jDialog1.dispose();
        } else if (jRadioButton2.isSelected()) {
            headCount = remoteControl.B_ButtonWasPushed(0);
            int option = JOptionPane.showConfirmDialog(null, "현재 강의실에 사람이 많습니다.\n다음 강의실을 예약하시겠습니까 ?", "강의실 선택", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
            if (option == 0) {//yes
                index += 1;
                while (index < cid.size()) {
                    resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                    if (resercount + Integer.parseInt(usernumber.getText()) > cid.get(index).getMaxseat()) {
                        index++;
                    } else {
                        headCount = usernumber.getText();
                        jDialog1.dispose();
                        break;
                    }
                }

            } else if (option == 1) { //현재 강의실 예약
                int tmpindex = index;
                resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                if (resercount + Integer.parseInt(usernumber.getText()) > cid.get(index).getMaxseat()) {
                    index += 1;
                    while (index < cid.size()) {
                        resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);
                        if (resercount + Integer.parseInt(usernumber.getText()) > cid.get(index).getMaxseat()) {
                            index++;
                            if (tmpindex != index) {
                                showMessageDialog(null, "강의실 수용인원이 초과하였습니다. 강의실을 이동합니다.");
                            }
                        } else {
                            headCount = usernumber.getText();
                            jDialog1.dispose();
                            break;
                        }
                    }
                    // 다음 강의실도 사람 많은지 확인 하기
                } else {
                    headCount = usernumber.getText();
                    jDialog1.dispose();
                }
            }
            if (index >= cid.size()) {
                showMessageDialog(null, "설정한 시간에 해당 인원으로 예약할 수 있는 강의실이 없습니다. 다시설정해주세요");
                index = firstindex;
            }
        }
        if (Integer.parseInt(endtime) > 17) {
            adminid.setText(dao.getClassAdmin(cid.get(index).getClassnumber(), today));
        } else {
            adminid.setText("조교");
        }
        max = cid.get(index).getMaxseat();
        setseatstatus(this.max);
        classnumberarea.setText(cid.get(index).getClassnumber());
        seattotal.setText(Integer.toString(count) + " / " + max);
    }//GEN-LAST:event_nextbtn1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        usernumber.setEnabled(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:

        usernumber.setEnabled(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

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
                }
            }
            //수업이 없는 경우
            for (; index < cid.size(); index++) {

                checkingclassschedule();
                if (index == cid.size()) {
                    break;
                }
                if (!iscount) {
                    resercount = dao.getselecttimeReserLength(cid.get(index).getClassnumber(), today, starttime);

                    if (resercount == cid.get(index).getMaxseat()) {
                        showMessageDialog(null, cid.get(index).getClassnumber() + " 강의은 모두예약되어있습니다.");
                    } else if (resercount >= 0) {
                        showMessageDialog(null, "선택한 시간에 예약한 사람이 25명이 넘습니다.\n선택예약으로 이동합니다.");

                        //예약시 사람수 체크해서 알고리즘 ㄲ
                        jDialog1.setVisible(true);
                        jDialog1.setSize(400, 300);
                        jDialog1.setLocationRelativeTo(this);
                        break;
                    } else if (resercount < 25) {
                        max = cid.get(index).getMaxseat();
                        setseatstatus(this.max);
                        break;
                    }
                }
            }
            if (index == cid.size()) {
                showMessageDialog(null, "현재 예약가능한 강의실이 없습니다.");
            } else {
                if (Integer.parseInt(endtime) > 17) {
                    adminid.setText(dao.getClassAdmin(cid.get(index).getClassnumber(), today));
                } else {
                    adminid.setText("조교");
                }
                classnumberarea.setText(cid.get(index).getClassnumber());
                seattotal.setText(Integer.toString(count) + " / " + max);
            }

        }
    }//GEN-LAST:event_changebtn1ActionPerformed

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        // TODO add your handling code here:
        editTime.dispose();
    }//GEN-LAST:event_cancelbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminid;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancel1;
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton changebtn;
    private javax.swing.JButton changebtn1;
    private javax.swing.JLabel classnumberarea;
    private javax.swing.JDialog editTime;
    private javax.swing.JComboBox<String> endtimebox;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel jlabel;
    private javax.swing.JButton nextbtn1;
    private javax.swing.JButton okreser;
    public javax.swing.JLabel resertime;
    private javax.swing.JLabel resertimearea;
    private javax.swing.JLabel seattotal;
    private javax.swing.JLabel settotal;
    private javax.swing.JComboBox<String> starttimebox;
    private javax.swing.JTextField usernumber;
    // End of variables declaration//GEN-END:variables
}
