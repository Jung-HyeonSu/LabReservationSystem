/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.team.accountmanagement;

import deu.cse.team.classadmin.ClassAdmin;
import deu.cse.team.singleton.AccountDTO;
import deu.cse.team.singleton.DAO;
import deu.cse.team.singleton.ReservationDTO;
import static java.lang.System.exit;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Seongchan
 */
public class Accountmanagement extends javax.swing.JFrame {

    /**
    2022.11.05 [최초작성자 20183207 김성찬]
    * 사용자 계정관리
    2022.11.12 [수정 20183215 정현수] 
    * 관리권한 판단 추가
    */
    String stu_id;
    String id;
     String username;
     String phonenumber;
     int warning;
     String power;
     String allowed;
     List<AccountDTO>  accountlist;
     String selected;
     
     
     public void getInformation(int number){
         id = accountlist.get(number).getId();
         username=accountlist.get(number).getName();
         phonenumber=accountlist.get(number).getPhonenumber();
         warning=accountlist.get(number).getWarning();
         power=accountlist.get(number).getPower();
         allowed=accountlist.get(number).getAllowed();
     }
     public void reloadTable(){
         
         DAO dao = DAO.getInstance();
        accountlist = dao.getAccountList();
        DefaultTableModel dtm = (DefaultTableModel)accountTable.getModel();
        dtm.setRowCount(0);
        //DB에서 계정리스트 가져오기
        //DefaultTableModel을 통해 JTable값 추가하도록 구현
        if (accountlist.isEmpty()) {//비어있을 시 true 리턴
            showMessageDialog(null,"DB에 접속할 수 없습니다. DB를 확인해 주세요");
            //DB 연결 실패시 핸들링하기 위해 추가
            //이전 화면으로 돌아가 DB 상태를 확인하도록 유도            
        }
        else {
            for (int i = 0; i < accountlist.size(); i++) {
                getInformation(i);
                if("1".equals(allowed)){
                    dtm.addRow(new Object[]{username,id,warning,power,"O"});
                }
                else{
                    dtm.addRow(new Object[]{username,id,warning,power,"X"});
                }
                
                
                //테이블에 값들 추가
            }
        }
     }
 
    public Accountmanagement() {
        initComponents();
        
        reloadTable();
        //테이블 데이터 갱신
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
        jLabel10 = new javax.swing.JLabel();
        editbtn1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        namearea = new javax.swing.JTextField();
        idarea = new javax.swing.JTextField();
        passwordarea = new javax.swing.JTextField();
        phonenumberarea = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        warningarea = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        closebtn = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel10.setText("계정 정보");

        editbtn1.setText("변경");
        editbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtn1ActionPerformed(evt);
            }
        });

        jButton1.setText("삭제");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setText("토큰 인증");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O", "X" }));

        jLabel4.setText("전화번호");

        namearea.setText("name");
        namearea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameareaActionPerformed(evt);
            }
        });

        idarea.setText("id");
        idarea.setFocusable(false);
        idarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idareaActionPerformed(evt);
            }
        });

        passwordarea.setText("password");

        phonenumberarea.setText("phonenumber");

        jLabel8.setText("경고횟수");

        warningarea.setText("warning");

        jLabel9.setText("관리자 권한");

        jLabel1.setText("이름");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O", "X" }));

        jLabel2.setText("아이디");

        jLabel3.setText("비밀번호");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel10))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(editbtn1)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)))
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(91, 91, 91)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialog1Layout.createSequentialGroup()
                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(79, 79, 79)
                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(passwordarea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addComponent(idarea, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(namearea, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(phonenumberarea, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(jDialog1Layout.createSequentialGroup()
                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11))
                            .addGap(63, 63, 63)
                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jDialog1Layout.createSequentialGroup()
                                    .addComponent(warningarea)
                                    .addContainerGap(114, Short.MAX_VALUE))
                                .addGroup(jDialog1Layout.createSequentialGroup()
                                    .addComponent(jComboBox1, 0, 80, Short.MAX_VALUE)
                                    .addContainerGap(102, Short.MAX_VALUE))
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editbtn1)
                    .addComponent(jButton1))
                .addGap(72, 72, 72))
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(106, 106, 106)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(namearea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(idarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(passwordarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(phonenumberarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(warningarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialog1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(106, Short.MAX_VALUE)))
        );

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel5.setText("계정을 삭제하시겠습니까?");

        jLabel6.setText("학번을 한번 더 입력해주세요.");

        jButton2.setText("삭제");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("취소");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialog2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialog2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel6))))
                    .addComponent(jLabel5))
                .addGap(30, 30, 30))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "이름", "학번", "경고횟수", "관리자권한", "토큰 인증"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(accountTable);

        closebtn.setText("닫기");
        closebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closebtnActionPerformed(evt);
            }
        });

        editbtn.setText("수정");
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });

        jButton3.setText("삭제");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editbtn)
                    .addComponent(jButton3))
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(closebtn)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(editbtn)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3)
                        .addGap(204, 204, 204))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addComponent(closebtn)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closebtnActionPerformed
        // TODO add your handling code here:
        dispose();
        //현재 창 닫기
    }//GEN-LAST:event_closebtnActionPerformed
   
    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
        // TODO add your handling code here:
        jDialog1.setVisible(true);
        jDialog1.setLocationRelativeTo(this);
        jDialog1.setSize(450, 600);
        
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        int row = accountTable.getSelectedRow();
        DAO dao = DAO.getInstance();
        accountlist = dao.getAccountList();
        idarea.setText((model.getValueAt(row, 1).toString()));
        for (int i = 0; i < accountlist.size(); i++) {
            if ((model.getValueAt(row, 1).toString()).equals(accountlist.get(i).getId())) {      
                namearea.setText(accountlist.get(i).getName());
                passwordarea.setText(accountlist.get(i).getPassword());
                phonenumberarea.setText(accountlist.get(i).getPhonenumber());
                warningarea.setText(Integer.toString(accountlist.get(i).getWarning()));
                if("O".equals(accountlist.get(i).getPower())){
                    jComboBox1.setSelectedIndex(0);
                }
                else{
                    jComboBox1.setSelectedIndex(1);
                }
                if("1".equals(accountlist.get(i).getAllowed())){
                    jComboBox2.setSelectedIndex(0);
                }
                else{
                    jComboBox2.setSelectedIndex(1);
                }
                
                
            }
        }
        
        
        
 
    }//GEN-LAST:event_editbtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        int row = accountTable.getSelectedRow();
        selected = model.getValueAt(row, 1).toString();
        
        jDialog2.setVisible(true);
        jDialog2.setLocationRelativeTo(this);
        jDialog2.setSize(250, 200);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
   
        if(selected.equals(jTextField1.getText())){
            DAO dao = DAO.getInstance();
            AccountDTO dto = new AccountDTO();
            dao.DeleteAccount(dto, selected);

            List<ReservationDTO> reserlist = dao.getReserList();
            for (int i = 0; i < reserlist.size(); i++) {
                if(selected.equals(reserlist.get(i).getId())){
                    ReservationDTO dto2 = new ReservationDTO();
                    ClassAdmin classadmin = new ClassAdmin();
                    classadmin.classAdminSet(reserlist.get(i).getOk(), reserlist.get(i).getClassadmin(),reserlist.get(i).getClassnumber(), Integer.toString(reserlist.get(i).getReser_number()), reserlist.get(i).getReser_date(), reserlist.get(i).getReser_endtime());
                    dao.DeleteReser(dto2, selected);
                }
            }
            JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다. 프로그램을 종료합니다.");
            exit(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "학번을 제대로 입력해주세요.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jDialog1.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jDialog2.setVisible(true);
        jDialog2.setLocationRelativeTo(this);
        jDialog2.setSize(450, 400);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void editbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtn1ActionPerformed
        // TODO add your handling code here:
        String allowedCheck;
        if("O".equals(jComboBox2.getSelectedItem().toString())){
            allowedCheck="1";
        }
        else{
            allowedCheck="0";
        }
        DAO dao = DAO.getInstance();
        AccountDTO dto = new AccountDTO();
        dao.UpdateAccount(dto, idarea.getText(), passwordarea.getText(), namearea.getText(), phonenumberarea.getText(),Integer.parseInt(warningarea.getText()),jComboBox1.getSelectedItem().toString(),allowedCheck);
        reloadTable();
        JOptionPane.showMessageDialog(null, "계정 정보가 수정되었습니다.");

    }//GEN-LAST:event_editbtn1ActionPerformed

    private void nameareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameareaActionPerformed

    private void idareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idareaActionPerformed

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
            java.util.logging.Logger.getLogger(Accountmanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accountmanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accountmanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accountmanagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Accountmanagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JButton closebtn;
    private javax.swing.JButton editbtn;
    private javax.swing.JButton editbtn1;
    private javax.swing.JTextField idarea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField namearea;
    private javax.swing.JTextField passwordarea;
    private javax.swing.JTextField phonenumberarea;
    private javax.swing.JTextField warningarea;
    // End of variables declaration//GEN-END:variables
}
