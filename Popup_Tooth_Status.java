/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.E;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Popup_Tooth_Status extends javax.swing.JDialog
{

    int popup_type = 0;
    int figure_id = 0;
    int tooth_number = 0;
    boolean isPervich = false;
    int idPervichNow = -1;
    //String[] TSP_names={"Отсутсвует","Удалён","Имплантат","Форм. десны","Коронка","Культ. вкладка","Бюгел. протез","Мост","Искусственный","Чужая коронка","Чужой мост","Чужой бюгел.","Полный съём.","Частичн. съём.","Чужой пол.","Чужой час."};
    //String[] TRSP_names={"Здоровый","Удалён","Частичное пломб.","Кариес","Штифт","Инор. тело","Перфорация","Пломбировка","Изменения","Вставка"};
    //String[] TSSP_names= {"Здоровый","Удалён","Частичное пломб.","Кариес","Штифт","Инор. тело","Перфорация","Пломбировка","Изменения","Вставка"};

    /**
     * Creates new form Tooth_Status_Popup
     */
    public Popup_Tooth_Status(java.awt.Frame parent, boolean modal, int popup_type, int figure_id, int tooth_number, boolean isPervich)
    {
        super(parent, modal);
        this.popup_type = popup_type;
        this.figure_id = figure_id;
        this.tooth_number = tooth_number;
        this.isPervich = isPervich;
        initComponents();

        switch (this.popup_type)
        {
            case 0:
                InitializeTSP();
                break;
            case 1:
                InitializeTRSP(true);
                break;
            case 2:
                InitializeTRSP(false);
                break;
            case 3:
                InitializeTSSP();
                break;
        }
    }

    public void InitializeTSP()
    {
        if (isPervich)

        {
            ArrayList<SprPervich> TSP_names = Start.sDB.getSprPervich(3);

            this.setPreferredSize(new Dimension(150, 17 * TSP_names.size() + 25));
            this.setSize(new Dimension(150, 17 * TSP_names.size() + 25));
            for (int i = 0; i < TSP_names.size(); i++)
            {
                JPanel j = new Panel_Tooth_status(i, TSP_names.get(i).getKodKn(), TSP_names.get(i).getId(), this.figure_id, this,isPervich);//ТУУУУУТТТ

                buttonGroup1.add((AbstractButton) j.getComponent(0)); // ЗДЕСЬ НУЖНО ПОСМОТРЕТЬ НОМЕР КОМНОНЕНТА - 0 или 1 ( radiobutton  или label  - их всего 2 ) - нужна кнопка.
                j.setBounds(0, 17 * i, 150, 17);
                j.setVisible(true);
                Frame.add(j);
            }
            jButton17.setLocation(0, 17 * TSP_names.size());
//            System.err.println("17*TSP_names.size()+25=" + (17 * TSP_names.size() + 25));
//            System.err.println("this.getPreferredSize().height=" + this.getPreferredSize().height);
//            System.err.println("this.getsize=" + this.getSize().height);
//            System.err.println("this.Frame.getsize=" + this.Frame.getSize().height);
            //this.setSize(150, 17*TSP_names.length+jButton17.getSize().height);
        } else
        {
            
            idPervichNow = Start.sDB.getIdPervichNow(tooth_number, figure_id);
            ArrayList<SprRab> TSP_names = Start.sDB.getSprRab(3,idPervichNow);
            this.setPreferredSize(new Dimension(150, 17 * TSP_names.size() + 25));
            this.setSize(new Dimension(150, 17 * TSP_names.size() + 25));
            for (int i = 0; i < TSP_names.size(); i++)
            {
                JPanel j = new Panel_Tooth_status(i, TSP_names.get(i).getNameRab(), TSP_names.get(i).getIdRab(), this.figure_id, this,isPervich);//ТУУУУУТТТ

                buttonGroup1.add((AbstractButton) j.getComponent(0)); // ЗДЕСЬ НУЖНО ПОСМОТРЕТЬ НОМЕР КОМНОНЕНТА - 0 или 1 ( radiobutton  или label  - их всего 2 ) - нужна кнопка.
                j.setBounds(0, 17 * i, 150, 17);
                j.setVisible(true);
                Frame.add(j);
            }
            jButton17.setLocation(0, 17 * TSP_names.size());
//            System.err.println("17*TSP_names.size()+25=" + (17 * TSP_names.size() + 25));
//            System.err.println("this.getPreferredSize().height=" + this.getPreferredSize().height);
//            System.err.println("this.getsize=" + this.getSize().height);
//            System.err.println("this.Frame.getsize=" + this.Frame.getSize().height);
            //this.setSize(150, 17*TSP_names.length+jButton17.getSize().height);
        }
    }

    public void InitializeTRSP(boolean one_root)
    {
        if (isPervich)
        {
            ArrayList<SprPervich> TRSP_names = Start.sDB.getSprPervich(2);
            this.setPreferredSize(new Dimension(150, 17 * TRSP_names.size() + 25));
            this.setSize(new Dimension(150, 17 * TRSP_names.size() + 25));

            for (int i = 0; i < TRSP_names.size(); i++)
            {
                JPanel j;
                if (one_root)
                {
                    j = new Panel_Tooth_Root_Status(i, TRSP_names.get(i).getKodKn(), TRSP_names.get(i).getId(), this.figure_id, true, this,isPervich);
                } // для корня одиночного
                else
                {
                    j = new Panel_Tooth_Root_Status(i, TRSP_names.get(i).getKodKn(), TRSP_names.get(i).getId(), this.figure_id, false, this,isPervich);
                }// для корня из 2 или 3 корней\

                buttonGroup1.add((AbstractButton) j.getComponent(0)); // ЗДЕСЬ НУЖНО ПОСМОТРЕТЬ НОМЕР КОМНОНЕНТА - 0 или 1 ( radiobutton  или label  - их всего 2 ) - нужен номер именно кнопки.
                j.setBounds(0, 17 * i, 150, 17);
                j.setVisible(true);
                Frame.add(j);
            }
            jButton17.setLocation(0, 17 * TRSP_names.size());
        } else
        {
            idPervichNow = Start.sDB.getIdPervichNow(tooth_number, figure_id);
            ArrayList<SprRab> TRSP_names = Start.sDB.getSprRab(2,idPervichNow);
            this.setPreferredSize(new Dimension(150, 17 * TRSP_names.size() + 25));
            this.setSize(new Dimension(150, 17 * TRSP_names.size() + 25));

            for (int i = 0; i < TRSP_names.size(); i++)
            {
                JPanel j;
                if (one_root)
                {
                    j = new Panel_Tooth_Root_Status(i, TRSP_names.get(i).getNameRab(), TRSP_names.get(i).getIdRab(), this.figure_id, true, this,isPervich);
                } // для корня одиночного
                else
                {
                    j = new Panel_Tooth_Root_Status(i, TRSP_names.get(i).getNameRab(), TRSP_names.get(i).getIdRab(), this.figure_id, false, this,isPervich);
                }// для корня из 2 или 3 корней\

                buttonGroup1.add((AbstractButton) j.getComponent(0)); // ЗДЕСЬ НУЖНО ПОСМОТРЕТЬ НОМЕР КОМНОНЕНТА - 0 или 1 ( radiobutton  или label  - их всего 2 ) - нужен номер именно кнопки.
                j.setBounds(0, 17 * i, 150, 17);
                j.setVisible(true);
                Frame.add(j);
            }
            jButton17.setLocation(0, 17 * TRSP_names.size());
        }
    }

    public void InitializeTSSP()
    {
        if (isPervich)
        {
            ArrayList<SprPervich> TSSP_names = Start.sDB.getSprPervich(1);
            this.setSize(new Dimension(150, 17 * TSSP_names.size() + 25));
            this.setPreferredSize(new Dimension(150, 17 * TSSP_names.size() + 25));
            for (int i = 0; i < TSSP_names.size(); i++)
            {
                JPanel j = new Panel_Tooth_Surface_status(TSSP_names.get(i).getColor(), TSSP_names.get(i).getKodKn(), TSSP_names.get(i).getId(), this.figure_id, this,isPervich);
                j.setBounds(0, 17 * i, 150, 17);
                j.setVisible(true);
                Frame.add(j);

            }

            System.err.println("17*TRSP_names.size()+25=" + (17 * TSSP_names.size() + 25));
            System.err.println("this.getPreferredSize().height=" + this.getPreferredSize().height);
            System.err.println("this.getsize=" + this.getSize().height);
            System.err.println("this.Frame.getsize=" + this.Frame.getSize().height);
            jButton17.setLocation(0, 17 * TSSP_names.size());
        } else
        {
            idPervichNow = Start.sDB.getIdPervichNow(tooth_number, figure_id);
            ArrayList<SprRab> TSSP_names = Start.sDB.getSprRab(1,idPervichNow);
            this.setSize(new Dimension(150, 17 * TSSP_names.size() + 25));
            this.setPreferredSize(new Dimension(150, 17 * TSSP_names.size() + 25));
            for (int i = 0; i < TSSP_names.size(); i++)
            {
                JPanel j = new Panel_Tooth_Surface_status(TSSP_names.get(i).getColor(), TSSP_names.get(i).getNameRab(), TSSP_names.get(i).getIdRab(), this.figure_id, this,isPervich);
                j.setBounds(0, 17 * i, 150, 17);
                j.setVisible(true);
                Frame.add(j);
            }

            System.err.println("17*TRSP_names.size()+25=" + (17 * TSSP_names.size() + 25));
            System.err.println("this.getPreferredSize().height=" + this.getPreferredSize().height);
            System.err.println("this.getsize=" + this.getSize().height);
            System.err.println("this.Frame.getsize=" + this.Frame.getSize().height);
            jButton17.setLocation(0, 17 * TSSP_names.size());
        }
        // а тут с кликом нужно что-то придумать.
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        Frame = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(153, 255, 153));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(25, 25));
        setModal(true);
        setName(""); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(150, 300));
        setType(java.awt.Window.Type.UTILITY);
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        Frame.setMinimumSize(new java.awt.Dimension(25, 25));
        Frame.setOpaque(false);
        Frame.setPreferredSize(new java.awt.Dimension(150, 300));
        Frame.setLayout(null);
        getContentPane().add(Frame);
        Frame.setBounds(0, 0, 150, 340);

        jButton17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton17.setText("Отменить");
        jButton17.setPreferredSize(new java.awt.Dimension(150, 25));
        jButton17.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton17ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton17);
        jButton17.setBounds(0, 190, 150, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        //this.dispose();
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Popup_Tooth_Status.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Popup_Tooth_Status.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Popup_Tooth_Status.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Popup_Tooth_Status.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() ->
        {
            Popup_Tooth_Status dialog = new Popup_Tooth_Status(new javax.swing.JFrame(), true, -1, -1, -1,false);
            dialog.addWindowListener(new java.awt.event.WindowAdapter()
            {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e)
                {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    public void closeDialog()
    {
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Frame;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton17;
    // End of variables declaration//GEN-END:variables

}
