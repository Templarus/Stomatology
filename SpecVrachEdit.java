/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;
import stomatology.JPanelSpec;
import stomatology.spravochniki.SprPolzEdit;
import static stomatology.spravochniki.SprPolzEdit.listData;
import stomatology.JCheckBoxSpec;
/**
 *
 * @author Asus
 */
public class SpecVrachEdit extends javax.swing.JDialog {
JCheckBoxSpec[] checkBox;
//private JPanelSpec[] panArr;
//private String[] listDate;
    /**
     * Creates new form SpecVrachEdit
     */
    public SpecVrachEdit(java.awt.Frame parent, boolean modal,JCheckBoxSpec[] checkBox) {
        
        super(parent, modal);
        this.checkBox = checkBox;
        //ArrayList<SprSpec> sprSpecVrach = Start.sDB.getSprSpec();
        //System.out.println("sprSpecVrach : " + sprSpecVrach.size());
        //panArr = new JPanelSpec[sprSpecVrach.size()];
        //int i = 0;
//        for(SprSpec sprSpec : sprSpecVrach){
//            JPanelSpec panSpec = new JPanelSpec(sprSpec);
//            panSpec.setLayout(new BorderLayout());
//            System.out.println("panSpec.getSprSpec().getNameSpec()" + panSpec.getSprSpec().getNameSpec());
//            JCheckBox select = new JCheckBox(panSpec.getSprSpec().getNameSpec());
//            for(String str : listDate){
//                if(panSpec.getSprSpec().getNameSpec().equals(str)){
//                select.setSelected(true);
//                }
//                }
//            select.setSize(50, 20);
//            select.setLocation(5, 5);
//            panSpec.add(select);
//            panArr[i] = panSpec;
//            i++;
//        }
//        System.out.println("panArr.ligth" + panArr.length);
//        for(JCheckBoxSpec chBox : checkBox){
//            pan.add(chBox);
//        }
        
        initComponents();
        int x = 0;
        int y = 0;
        for(JCheckBoxSpec chBox : checkBox){
            chBox.setSize(150, 20);
            chBox.setLocation(x + 5, y + 5);
            //x = x + 20;
            y= y + 20;
            pan.add(chBox);
            System.out.println("chBox.getNameSpec()" + chBox.getNameSpec());
            if(chBox.isSelect()){
                chBox.setSelected(true);
            }
            chBox.setVisible(true);
            
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

        pan = new javax.swing.JPanel();
        knOK = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(135, 320));
        setModal(true);
        setResizable(false);

        //int x = 0;
        //int y = 0;
        //for(JPanelSpec panSpec : panArr){
            //  System.out.println("!!!panSpec.getSprSpec().getNameSpec()" + panSpec.getSprSpec().getNameSpec());
            //  pan.setSize(50, 20);
            //  pan.setLocation(x+10,y);
            //pan.add(panSpec,LEFT_ALIGNMENT);
            // panSpec.setVisible(true);
            //x = x+20;
            // y = y+10;
            // }

        javax.swing.GroupLayout panLayout = new javax.swing.GroupLayout(pan);
        pan.setLayout(panLayout);
        panLayout.setHorizontalGroup(
            panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panLayout.setVerticalGroup(
            panLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        knOK.setText("ОК");
        knOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                knOKActionPerformed(evt);
            }
        });

        jButton1.setText("Отмена");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(knOK, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addComponent(pan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(knOK)
                    .addComponent(jButton1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            int reply = JOptionPane.showConfirmDialog(null, "Изменения будут потеряны", "Внимание", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION){
               dispose();
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void knOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_knOKActionPerformed
//        int i = 0;
//        for(Component cmp : pan.getComponents()) {
//             JPanelSpec panSpec = (JPanelSpec)cmp;
//             if(panSpec.getSprSpec().isSelect()){
//               i++;  
//             }
//             listDate = new String[i];
//        int row = 0;     
//             if(panSpec.getSprSpec().isSelect()){
//               listDate[row] = panSpec.getSprSpec().getNameSpec();
//               
//             }
//             
//             System.out.println("cmp.getClass()" + panSpec.getSprSpec().getNameSpec());
//       }          
        
        int i = 0;
        for(JCheckBoxSpec chBox : checkBox){
            if(chBox.isSelected()){
                chBox.setSelect(true);
                i++;
            } else {
                 chBox.setSelect(false);
            }
            
        }
        SprPolzEdit.listData = new JCheckBoxSpec[i];
        i = 0;
        for(JCheckBoxSpec chBox : checkBox){
            if(chBox.isSelect()){
                System.out.println("chBox.getName()" + chBox.getNameSpec());
                SprPolzEdit.listData[i] = chBox;
                i++;
            }
        }    
        
        SprPolzEdit.specList.setListData(SprPolzEdit.listData);
        SprPolzEdit.specList.updateUI();
        dispose();
    }//GEN-LAST:event_knOKActionPerformed

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
            java.util.logging.Logger.getLogger(SpecVrachEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpecVrachEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpecVrachEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpecVrachEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SpecVrachEdit dialog = new SpecVrachEdit(new javax.swing.JFrame(), true,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton knOK;
    private javax.swing.JPanel pan;
    // End of variables declaration//GEN-END:variables
}
