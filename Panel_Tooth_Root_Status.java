/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import javax.swing.JDialog;

/**
 *
 * @author TemplaRus
 */
public class Panel_Tooth_Root_Status extends javax.swing.JPanel
{

    int figure_id = 0;
    int status_id = 0;
    int icon_number = 0;
    boolean isPervich = false;
    JDialog gd;

    /**
     * Creates new form Tooth_Root_Status
     */
    public Panel_Tooth_Root_Status(int icon_number, String text, int status_id, int figure_id, boolean one_root, JDialog gd, boolean isPervich)
    {
        this.figure_id = figure_id;
        this.status_id = status_id;
        this.icon_number = icon_number;
        this.gd = gd;
        this.isPervich = isPervich;
        initComponents();
        if (one_root)
        {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/4-" + this.icon_number + ".png")));
            jLabel1.setText(text);
            System.out.println("text=" + text);

        } else
        {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/3-" + this.icon_number + ".png")));
            jLabel1.setText(text);
            System.out.println("text=" + text);
        }
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 204));
        setPreferredSize(new java.awt.Dimension(150, 17));
        setLayout(null);

        jRadioButton1.setMaximumSize(new java.awt.Dimension(19, 17));
        jRadioButton1.setMinimumSize(new java.awt.Dimension(19, 17));
        jRadioButton1.setPreferredSize(new java.awt.Dimension(19, 17));
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jRadioButton1MouseClicked(evt);
            }
        });
        add(jRadioButton1);
        jRadioButton1.setBounds(0, 0, 19, 17);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1-1.png"))); // NOI18N
        jLabel1.setText("TEXT");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 17));
        jLabel1.setMinimumSize(new java.awt.Dimension(130, 17));
        jLabel1.setPreferredSize(new java.awt.Dimension(130, 17));
        add(jLabel1);
        jLabel1.setBounds(20, 0, 130, 17);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked

        if (isPervich)
        {
            Object_Tooth.TP.getStatusGrZub().get(figure_id).setIdPervich(status_id);
            Object_Tooth.TP.Redraw();
            Object_Tooth.TP.repaint();
        }
        else
        {
            Object_Tooth.RSP.getStatusGrZub().get(figure_id).setIdRab(status_id);
            Object_Tooth.RSP.Redraw();
            Object_Tooth.RSP.repaint();
        }
Button_Tooth.TSP.dispose();
    }//GEN-LAST:event_jRadioButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    // End of variables declaration//GEN-END:variables
}
