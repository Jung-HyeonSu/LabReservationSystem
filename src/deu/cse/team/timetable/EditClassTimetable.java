/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package deu.cse.team.timetable;

import deu.cse.team.singleton.ClassTimetableDTO;
import deu.cse.team.singleton.DAO;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Seongchan
 */
//2022. 11. 07 [최초작성자 정현수]
public class EditClassTimetable extends javax.swing.JPanel {

    /**
     * Creates new form newEditClassTimetable
     */
    public EditClassTimetable() {
        initComponents();
        loadTimeTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(818, 477));

        jButton3.setText("조회");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("현재 강의실:");

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("915");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"
            }
        ));
        jTable2.setRowHeight(40);
        jScrollPane1.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jLabel1.setText("수업시간표 입력");

        jLabel2.setText("※테이블 더블클릭으로 수정 가능");

        jButton1.setText("저장");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(369, 369, 369))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(jLabel1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jLabel4.setText(jComboBox1.getSelectedItem().toString());
        loadTimeTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DAO dao = DAO.getInstance();
        ClassTimetableDTO dto = new ClassTimetableDTO();
        String[] time = new String[8];

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        String[][] data = new String[10][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                switch (j) {
                    case 1:
                    if ("".equals(model.getValueAt(i, j).toString())) {
                        time[i] = " ,";
                    } else {
                        time[i] = model.getValueAt(i, j).toString() + ",";
                    }
                    break;
                    case 7:
                    if ("".equals(model.getValueAt(i, j).toString())) {
                        time[i] += " ";
                    } else {
                        time[i] += model.getValueAt(i, j).toString();
                    }
                    break;
                    default:
                    if ("".equals(model.getValueAt(i, j).toString())) {
                        time[i] += " ,";
                    } else {
                        time[i] += model.getValueAt(i, j).toString() + ",";
                    }

                    break;
                }
            }
        }

        String classnum = jComboBox1.getSelectedItem().toString();
        dao.UpdateTimetable(dto, classnum, time[0], time[1], time[2], time[3], time[4], time[5], time[6], time[7]);
        loadTimeTable();
        showMessageDialog(null, "시간표가 저장되었습니다.");

    }//GEN-LAST:event_jButton1ActionPerformed
    void loadTimeTable() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setNumRows(0);
        DAO dao = DAO.getInstance();
        List<ClassTimetableDTO> timetableList = dao.getTimetableList();
        String classNum;
        String[] time = new String[8];

        for (int i = 0; i < timetableList.size(); i++) {
            classNum = timetableList.get(i).getClassnumber();
            if (classNum.equals(jComboBox1.getSelectedItem().toString())) {
                time[0] = timetableList.get(i).getTime1();
                time[1] = timetableList.get(i).getTime2();
                time[2] = timetableList.get(i).getTime3();
                time[3] = timetableList.get(i).getTime4();
                time[4] = timetableList.get(i).getTime5();
                time[5] = timetableList.get(i).getTime6();
                time[6] = timetableList.get(i).getTime7();
                time[7] = timetableList.get(i).getTime8();
            }

        }

        String[] strArr = new String[8];
        for (int i = 0; i < 8; i++) {
            strArr = time[i].split(",");
            model.addRow(new Object[]{
                Integer.toString(i + 1) + "교시", strArr[0], strArr[1], strArr[2], strArr[3], strArr[4], strArr[5], strArr[6]});
        }

    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
