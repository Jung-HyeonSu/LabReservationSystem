/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package deu.cse.team.tokenauth;
import deu.cse.team.singleton.AccountDTO;
import deu.cse.team.singleton.DAO;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Seongchan
 */
//2022. 11. 07 [최초작성자 정현수]
public class TokenAuth extends javax.swing.JPanel {

    /**
     * Creates new form newTokenAuth
     */
    public TokenAuth() {
        initComponents();
        loadTable();
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
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(818, 477));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "학번", "인증 여부"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 1, 18)); // NOI18N
        jLabel1.setText("토큰값 인증");

        jButton1.setText("변경");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(366, 366, 366))
            .addGroup(layout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        DAO dao = DAO.getInstance();
        AccountDTO dto = new AccountDTO();
        String str = null;
        if("O".equals(model.getValueAt(row, 1).toString())){
            dao.UpdateAccount(dto, model.getValueAt(row, 0).toString(), "0");
            showMessageDialog(null,"토큰값 인증 취소");
        }
        else if ("X".equals(model.getValueAt(row, 1).toString())){
            dao.UpdateAccount(dto, model.getValueAt(row, 0).toString(), "1");
            showMessageDialog(null,"토큰값 인증 허용");
        }
        loadTable();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void loadTable(){
         
        DAO dao = DAO.getInstance();
        List<AccountDTO> accountlist = dao.getAccountList();
        DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
        dtm.setRowCount(0);
        String allowed = null;
        //DB에서 계정리스트 가져오기
        //DefaultTableModel을 통해 JTable값 추가하도록 구현
        if (accountlist.isEmpty()) {//비어있을 시 true 리턴
            showMessageDialog(null,"DB에 접속할 수 없습니다. DB를 확인해 주세요");
            //DB 연결 실패시 핸들링하기 위해 추가
            //이전 화면으로 돌아가 DB 상태를 확인하도록 유도            
        }
        else {
            for (int i = 0; i < accountlist.size(); i++) {
                if("1".equals(accountlist.get(i).getAllowed())){
                    allowed = "O";
                }
                else{
                    allowed = "X";
                }
                dtm.addRow(new Object[]{accountlist.get(i).getId(),allowed});
            }
        }
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
