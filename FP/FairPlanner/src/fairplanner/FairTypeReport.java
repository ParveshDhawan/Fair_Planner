/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairplanner;

import fairplanner.alpha.DBOperations;
import fairplanner.bean.FairtypeBean;
import fairplanner.reports.JasperReportGenerator;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Parvesh
 */
public class FairTypeReport extends javax.swing.JFrame {

    List<FairtypeBean> lstFairtype = null;
    DBOperations objDB = new DBOperations();

    /**
     * Creates new form FairTypeReport
     */
    public FairTypeReport() {
        initComponents();
        lstFairtype = objDB.getAllFairTypes();
        showTable();

    }

    public void showTable() {
        Object[][] data = new Object[lstFairtype.size()][4];
        for (int i = 0; i < lstFairtype.size(); i++) {
            FairtypeBean objFairtypeBean = lstFairtype.get(i);
            data[i][0] = objFairtypeBean.getFair_type_id();
            data[i][1] = objFairtypeBean.getFair_type();
            data[i][2] = objFairtypeBean.getDescription();
            data[i][3] = objFairtypeBean.getStatus();
        }
        String[] headers = {"Fair_type_id", "Fair_type", "Description", "Status"};
        tblFairTypeReport = new JTable(data, headers);
        jScrollPane1.setViewportView(tblFairTypeReport);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFairTypeReport = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        mbFairTypeReport = new javax.swing.JMenu();
        miPrint = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Fair Type Report");

        tblFairTypeReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fair Type Id", "Fair Type", "Description", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblFairTypeReport);

        mbFairTypeReport.setText("File");

        miPrint.setText("Print");
        miPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPrintActionPerformed(evt);
            }
        });
        mbFairTypeReport.add(miPrint);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        mbFairTypeReport.add(miExit);

        jMenuBar1.add(mbFairTypeReport);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void miPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPrintActionPerformed
        // TODO add your handling code here:
        new JasperReportGenerator().generateReport("FairTypeReport.jrxml");
    }//GEN-LAST:event_miPrintActionPerformed

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
            java.util.logging.Logger.getLogger(FairTypeReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FairTypeReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FairTypeReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FairTypeReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FairTypeReport().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mbFairTypeReport;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miPrint;
    private javax.swing.JTable tblFairTypeReport;
    // End of variables declaration//GEN-END:variables
}
