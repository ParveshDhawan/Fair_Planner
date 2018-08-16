/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fairplanner;

import fairplanner.alpha.DBOperations;
import fairplanner.bean.FairstallsBean;
import fairplanner.reports.JasperReportGenerator;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Parvesh
 */
public class FairStallsReport extends javax.swing.JFrame {
    
    List<FairstallsBean> lstFairstalls = null;
    DBOperations objDB = new DBOperations();

    /**
     * Creates new form FairStallsReport
     */
    public FairStallsReport() {
        initComponents();
        lstFairstalls = objDB.getAllFairstalls();
        showTable();
    }
    private void showTable() {
        Object[][] data = new Object[lstFairstalls.size()][7];
        for (int i = 0; i < lstFairstalls.size(); i++) {
            FairstallsBean objFairstallsBean = lstFairstalls.get(i);
            data[i][0] = objFairstallsBean.getFair_stall_id();
            data[i][1] =  objFairstallsBean.getFair_stall();
            data[i][2] =  objFairstallsBean.getSize();
            data[i][3] =  objFairstallsBean.getPrice();
            data[i][4] =  objFairstallsBean.getStatus();
            data[i][5] =  objFairstallsBean.getNo_of_stalls();
            data[i][6] =  objFairstallsBean.getFair_id();
        }
        String[] headers = {"Fair stall Id", "Fair stall", "Size", "Price", "Status", "No of stalls", "Fair Id"};
        tblFairStallsReport = new JTable(data, headers);
        jScrollPane1.setViewportView(tblFairStallsReport);
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
        tblFairStallsReport = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miPrint = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblFairStallsReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Fair Stall ID", "Fair Stall", "Size", "Price", "Status", "No of Stalls", "Fair ID"
            }
        ));
        jScrollPane1.setViewportView(tblFairStallsReport);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Fair Stalls Report");

        jMenu1.setText("File");

        miPrint.setText("Print");
        miPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPrintActionPerformed(evt);
            }
        });
        jMenu1.add(miPrint);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPrintActionPerformed
        // TODO add your handling code here:
        new JasperReportGenerator().generateReport("FairStallsReport.jrxml");
    }//GEN-LAST:event_miPrintActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_miExitActionPerformed

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
            java.util.logging.Logger.getLogger(FairStallsReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FairStallsReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FairStallsReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FairStallsReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FairStallsReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miPrint;
    private javax.swing.JTable tblFairStallsReport;
    // End of variables declaration//GEN-END:variables
}
