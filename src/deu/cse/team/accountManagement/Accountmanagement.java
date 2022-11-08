/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.cse.team.accountManagement;

import deu.cse.team.singleton.AccountDTO;
import deu.cse.team.singleton.DAO;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Seongchan
 */
public class Accountmanagement extends javax.swing.JFrame {

    /**
    2022.11.05 [최초작성자 20183207 김성찬]
    사용자 계정관리
    */
    String id;
     String username;
     String phonenumber;
     int warning;
     String power;
     boolean allowed;
     List<AccountDTO>  accountlist;
     public void selectuser(int row){
       id = (String) accountTable.getValueAt(1, row);
        username = (String) accountTable.getValueAt(2, row);
        phonenumber = (String) accountTable.getValueAt(3, row);
        warning = (int) accountTable.getValueAt(4, row);
        power =(String) accountTable.getValueAt(5, row);
   }
     public void getInformation(int number){
         id = accountlist.get(number).getId();
         username=accountlist.get(number).getName();
         phonenumber=accountlist.get(number).getPhonenumber();
         warning=accountlist.get(number).getWarning();
         power=accountlist.get(number).getPower();
         allowed=accountlist.get(number).isAllowed();
     }
    public Accountmanagement() {
        initComponents();
        DAO dao = DAO.getInstance();
        accountlist = dao.getAccountList();
        DefaultTableModel dtm = (DefaultTableModel)accountTable.getModel();
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
                dtm.addColumn(i+1, new Object[]{id,username,phonenumber,warning,power,allowed});
                //테이블에 값들 추가
            }
        }
        accountTable.updateUI();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();
        closebtn = new javax.swing.JButton();
        editbtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "이름", "학번", "경고횟수", "계정권한", "토큰값인증"
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
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editbtn)
                    .addComponent(jButton3))
                .addGap(35, 35, 35))
            .addGroup(layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(closebtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(editbtn)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3)))
                .addGap(18, 18, 18)
                .addComponent(closebtn)
                .addContainerGap(97, Short.MAX_VALUE))
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
        int row = accountTable.getSelectedRow();
        selectuser(row);
        new Editaccount(id,username,phonenumber,warning,power,allowed);
    }//GEN-LAST:event_editbtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = accountTable.getSelectedRow();
        selectuser(row);
        //삭제구문 넣기
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
