/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import stomatology.spravochniki.Spravochniki;

/**
 *
 * @author Asus
 */
public class Start extends javax.swing.JFrame {

    //--------------------------
    private Connection conn;
    private Statement st;
    private String sql;
    private String loginString;
    public static Vrach vrach;
    public static ServerDb sDB;
    public static String idfilial;
    public static String connectionString = "jdbc:sqlserver://MSI:1433;databaseName=stom";
    //--------------------------

    public Start() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        login = new javax.swing.JComboBox();
        try {
            conn = DriverManager.getConnection(connectionString, "reader_stom", "reader_stom");//Подключаемся к баде данных
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Делаем многонаправленный ResultSet
            System.out.println("SUCCESSFULLY connected with database!");
            sql = "SELECT id_vrach as id, ISNULL(fam_vrach,'') + ' ' + ISNULL(imya_vrach,'') + ' ' + ISNULL(otch_vrach,'') as fio, sql_login as sqlLogin FROM  D_vrach ORDER BY ISNULL(fam_vrach,'') + ' ' + ISNULL(imya_vrach,'') + ' ' + ISNULL(otch_vrach,'')";
            ResultSet rs;
            rs = st.executeQuery(sql);
            while(rs.next()){
                login.addItem(rs.getString(2));
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getData():Ошибка подключения или создание Statement");
        }
        loginLab = new javax.swing.JLabel();
        passLab = new javax.swing.JLabel();
        enter = new javax.swing.JButton();
        otm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        filial = new javax.swing.JComboBox();
        try {
            conn = DriverManager.getConnection(connectionString, "reader_stom", "reader_stom");//Подключаемся к баде данных
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Делаем многонаправленный ResultSet
            sql = "SELECT name_filial FROM S_filial";
            ResultSet rs;
            rs = st.executeQuery(sql);
            while(rs.next()){
                filial.addItem(rs.getString(1));
            }
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getData():Ошибка подключения или создание Statement");
        }
        loginLab1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Авторизация");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(525, 250));
        setMinimumSize(new java.awt.Dimension(525, 250));
        setResizable(false);
        setSize(new java.awt.Dimension(525, 250));
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setMinimumSize(new java.awt.Dimension(646, 262));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(null);

        login.setMaximumRowCount(10);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginKeyPressed(evt);
            }
        });
        jPanel1.add(login);
        login.setBounds(130, 90, 380, 20);
        login.getAccessibleContext().setAccessibleDescription("");

        loginLab.setText("Пользователь");
        jPanel1.add(loginLab);
        loginLab.setBounds(10, 90, 110, 14);

        passLab.setText("Пароль");
        jPanel1.add(passLab);
        passLab.setBounds(10, 120, 76, 20);

        enter.setText("Вход");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        jPanel1.add(enter);
        enter.setBounds(100, 160, 123, 34);

        otm.setText("Отмена");
        otm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otmActionPerformed(evt);
            }
        });
        jPanel1.add(otm);
        otm.setBounds(230, 160, 115, 34);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Форма авторизации");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 460, 40);

        pass.setPreferredSize(new java.awt.Dimension(100, 20));
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });
        jPanel1.add(pass);
        pass.setBounds(130, 120, 100, 20);

        filial.setMaximumRowCount(10);
        filial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filialActionPerformed(evt);
            }
        });
        filial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filialKeyPressed(evt);
            }
        });
        jPanel1.add(filial);
        filial.setBounds(130, 60, 380, 20);

        loginLab1.setText("Филиал");
        jPanel1.add(loginLab1);
        loginLab1.setBounds(10, 60, 76, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 520, 200);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginActionPerformed

    private void otmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otmActionPerformed
        dispose();
    }//GEN-LAST:event_otmActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        conn();
    }//GEN-LAST:event_enterActionPerformed

    public void conn() {
        Vrach vr = null;
        String vrName = "";
        try {
            vrName = login.getSelectedItem().toString();
            sDB = new ServerDb(connectionString, "reader_stom", "reader_stom");
            vr = sDB.getVrachData(vrName);
            loginString = vr.getSqlLogin();
            sDB.disconnect();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Необходимо выбрать пользователя", "Внимание", 0);
        }

        char[] ch = pass.getPassword();
        if (ch.length < 1) {
            JOptionPane.showMessageDialog(null, "Необходимо ввести пароль", "Внимание", 0);
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : ch) {
                sb.append(c);
            }
            sDB = new ServerDb(connectionString, loginString, sb.toString());
            try {
                if (sDB.getConn().isValid(100)) {
                    this.idfilial = filial.getSelectedItem().toString();
                    if (idfilial != "") {
                        if (vr.getIdRole() == 1) {
                            Spravochniki spr = new Spravochniki();
                            dispose();
                            spr.setVisible(true);
//                             Find find = new Find(vr);
//                             dispose();
//                             find.setVisible(true);
                        } else if (vr.getIdRole() == 2) {
                            Find find = new Find(vr);
                            dispose();
                            find.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Необходимо выбрать филиал", "Внимание", 0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "У пользователя " + vr.getFIO(vr) + "не выбранна роль", "Внимание", 0);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Не удалось установить соединение с БД", "Внимание", 0);
            }
        }
    }

    private void filialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filialActionPerformed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed

    }//GEN-LAST:event_jPanel1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            conn();
        }
    }//GEN-LAST:event_passKeyPressed

    private void filialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filialKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            conn();
        }
    }//GEN-LAST:event_filialKeyPressed

    private void loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginKeyPressed
        if (KeyEvent.VK_ENTER == evt.getKeyCode()) {
            conn();
        }
    }//GEN-LAST:event_loginKeyPressed

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
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton enter;
    private javax.swing.JComboBox filial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox login;
    private javax.swing.JLabel loginLab;
    private javax.swing.JLabel loginLab1;
    private javax.swing.JButton otm;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel passLab;
    // End of variables declaration//GEN-END:variables

}
