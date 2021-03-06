/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import com.michaelbaranov.microba.calendar.CalendarPane;
import com.michaelbaranov.microba.calendar.DatePicker;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static stomatology.MainForm.Poseshl_Table;
import static stomatology.MainForm.dbmPosesh;

/**
 *
 * @author Asus
 */
public class AddDatePosesh extends javax.swing.JDialog {

    DatePicker datePicker;

    /**
     * Creates new form AddDatePosesh
     */
    public AddDatePosesh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pan.add("DatePicker", buildDatePickerTab());
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
        jLabel1 = new javax.swing.JLabel();
        oKBut = new javax.swing.JButton();
        cancelBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Новое посещение");

        pan.setLayout(new javax.swing.BoxLayout(pan, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Дата посещения");

        oKBut.setText("Ок");
        oKBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oKButActionPerformed(evt);
            }
        });

        cancelBut.setText("Отмена");
        cancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pan, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(oKBut, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(cancelBut, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(oKBut, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(cancelBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static int getKolNezavershPosesh() {
        int rezult = 0;
        for (Posesh pos : MainForm.cl.getPosesh()) {
            if (pos.getIdPosesh() == 0) {
                rezult = rezult + 1;
            }
        }
        return rezult;
    }

    private void oKButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oKButActionPerformed
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datePicker.getDate());
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(new Date());
        int proverkaDt = 0;
        int proverkaNeZap = 0;
        int proverkaNeZapObsled = 0;
        String dt = Methods.stringToDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        if (Start.sDB.getKolObsledPosleDt(dt, MainForm.cl.id_klient) != 0) {
            JOptionPane.showMessageDialog(null, "Дата не должна быть меньше последней даты обследования", "Внимание", JOptionPane.INFORMATION_MESSAGE);
        } else {

            for (PervichObsled pervich : MainForm.cl.getPervichObsled()) {
                if (pervich.getId_pervich_obsled() == 0) {
                    proverkaNeZapObsled = proverkaNeZapObsled + 1;
                }
            }
            if (proverkaNeZapObsled != 0) {
                JOptionPane.showMessageDialog(null, "У вас есть не записанные в БД обследования", "Внимание", JOptionPane.INFORMATION_MESSAGE);
            } else {

                for (Posesh pos : MainForm.cl.getPosesh()) {
                    if (pos.getIdPosesh() == 0) {
                        proverkaNeZap = proverkaNeZap + 1;
                    }
                }

                if (proverkaNeZap != 0) {
                    JOptionPane.showMessageDialog(null, "У вас есть не записанные в БД посещения", "Внимание", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (calNow.get(Calendar.YEAR) <= calendar.get(Calendar.YEAR)) {
                        if ((calNow.get(Calendar.MONTH) <= calendar.get(Calendar.MONTH))) {
                            if (calNow.get(Calendar.DAY_OF_MONTH) < calendar.get(Calendar.DAY_OF_MONTH)) {
                                proverkaDt = 1;
                                JOptionPane.showMessageDialog(null, "Дата посещения не может быть больше текущей даты", "Внимание", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }

                    if (proverkaDt == 0) {

                        if (getKolNezavershPosesh() != 0) {
                            JOptionPane.showMessageDialog(null, "Необходимо завершить все посещения", "Внимание", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (Start.sDB.getKolPoseshPosleDt(dt, MainForm.cl.id_klient) != 0) {
                                JOptionPane.showMessageDialog(null, "Дата не должна быть меньше последней даты посещения", "Внимание", JOptionPane.INFORMATION_MESSAGE);
                            } else {

                                int i = Start.sDB.getIdFilial(Start.idfilial);

                                Posesh pos = new Posesh(0, dt, Find.vr.getId(), MainForm.cl.getId_klient(), i);

                                ArrayList<PoseshStr> poseshStr = new ArrayList<>();
                                PoseshStr poseshStrObj;
                                for (int z = 0; z < 32; z++) {
                                    poseshStrObj = new PoseshStr(0, z);
                                    poseshStrObj.setPoseshRabZub(getPoseshRabStatus(poseshStrObj));
                                    poseshStr.add(poseshStrObj);
                                };

                                pos.setPoseshStr(poseshStr);
                                pos.setPoseshStrVrem(getPoseshStrVrem(pos));
                                pos.setPoseshSanac(getPoseshSanac(pos));
                                MainForm.cl.getPosesh().add(pos);
                                Start.sDB.setNewPoseshTemp(pos);
                                try {
                                    //MainForm.idPosesh = 0;   
                                    dbmPosesh.setData(Start.sDB.getRSNewPosesh());
                                    Start.sDB.deleteFromPoseshNew();
                                    Poseshl_Table.repaint();
                                    dispose();
                                } catch (Exception ex) {
                                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                    }

                }
            }
        }
    }//GEN-LAST:event_oKButActionPerformed

    public ArrayList<PoseshSanac> getPoseshSanac(Posesh posesh) {
        int idPosesh = posesh.getIdPosesh();
        ArrayList<PoseshSanac> poseshSanac = new ArrayList<>();
        PoseshSanac poseshSanacObj;
        for (int z = 0; z < 32; z++) {
            poseshSanacObj = new PoseshSanac(idPosesh, z, "", 0);
            poseshSanac.add(poseshSanacObj);
        }
        return poseshSanac;
    }

    public ArrayList<PoseshStrVrem> getPoseshStrVrem(Posesh posesh) {
        int idPosesh = posesh.getIdPosesh();
        ArrayList<PoseshStrVrem> poseshStrVrem = new ArrayList<>();
        PoseshStrVrem poseshStrObj;

        for (int z = 0; z < 32; z++) {
            poseshStrObj = new PoseshStrVrem(idPosesh, z, 0, "", false, "", "");
            poseshStrVrem.add(poseshStrObj);
        }

        return poseshStrVrem;
    }

    public ArrayList<PoseshStrRabZub> getPoseshRabStatus(PoseshStr poseshStr) {
        ArrayList<PoseshStrRabZub> poseshStrRabZub = new ArrayList<>();
        PoseshStrRabZub poseshStrRabZubObj;

        //Заполним -1 все грани зуба
        for (int gr = 0; gr < 13; gr++) {
            Integer[] color = {255, 255, 255};
            poseshStrRabZubObj = new PoseshStrRabZub(-1, gr, -1, 0, color);
            poseshStrRabZub.add(poseshStrRabZubObj);
        }
        return poseshStrRabZub;
    }


    private void cancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Изменения будут потеряны", "Внимание", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            dispose();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cancelButActionPerformed

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
            java.util.logging.Logger.getLogger(AddDatePosesh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDatePosesh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDatePosesh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDatePosesh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddDatePosesh dialog = new AddDatePosesh(new javax.swing.JFrame(), true);
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

    private JComponent buildDatePickerTab() {
        JPanel panel = new JPanel();
        datePicker = new DatePicker();
        // datePicker.setDateFormat(new SimpleDateFormat("HH dd yyyy"));
        datePicker.setDateFormat(SimpleDateFormat.getDateTimeInstance());
        // datePicker.setStripTime(false);
        datePicker.setEnabled(false);
        datePicker.setKeepTime(true);
        datePicker.setStripTime(false);
        datePicker.setShowNumberOfWeek(true);
        // datePicker.setEnabled(false);
        // datePicker.setPickerStyle(DatePicker.PICKER_STYLE_BUTTON);
        // datePicker.showButtonOnly(false);
        // datePicker.setToolTipText("hello!!!!");
        // datePicker.setShowNumberOfWeek(true);

        Map ov = new HashMap();

        ov.put(CalendarPane.COLOR_CALENDAR_GRID_FOREGROUND_ENABLED,
                Color.BLUE);

        datePicker.setColorOverrideMap(ov);

        panel.setLayout(new GridBagLayout());
        panel.add(datePicker, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                        5, 5, 5, 5), 0, 0));

        datePicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("DatePicker:" + datePicker.getDate());

            }
        });

        return panel;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton oKBut;
    private javax.swing.JPanel pan;
    // End of variables declaration//GEN-END:variables
}
