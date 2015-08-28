/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author TemplaRus
 */
public class MainForm extends javax.swing.JFrame {

    Vrach vr;
    public static Client cl;
    public static Double scaleformat = 1.0;
    public static int[][] teeth = new int[32][32];
    public static Boolean Redraw_icons = false;
    public static int idObsled = 0;//Используется в формах выбора статусов и работ для зуба
    public static int idPosesh = 0;//Используется в формах выбора статусов и работ для зуба
    public static int idObsledDB = 0;//Используется в формах выбора статусов и работ для зуба
    public static int idPoseshDB = 30000;//Используется в формах выбора статусов и работ для зуба
    public static DatabaseTableModel dbmPervObsl = new DatabaseTableModel(false);
    public static DatabaseTableModel dbmPosesh = new DatabaseTableModel(false);

    public Vrach getVr() {
        return vr;
    }

    public void setVr(Vrach vr) {
        this.vr = vr;
    }

    public Client getCl() {
        return cl;
    }

    public void setCl(Client cl) {
        this.cl = cl;
    }

    public Double getScaleformat() {
        return scaleformat;
    }

    public void setScaleformat(Double scaleformat) {
        MainForm.scaleformat = scaleformat;
    }

    public int[][] getTeeth() {
        return teeth;
    }

    public void setTeeth(int[][] teeth) {
        MainForm.teeth = teeth;
    }

    public static Boolean getRedraw_icons() {
        return Redraw_icons;
    }

    public static void setRedraw_icons(Boolean Redraw_icons) {
        MainForm.Redraw_icons = Redraw_icons;
    }

    public MainForm(Vrach vr, Client cl) {
        this.vr = vr;
        MainForm.cl = cl;
        if (Start.sDB.getNeobhodObs(cl.getId_klient()) < 1) {
            Start.sDB.setObsled(cl.getId_klient(), "", vr.getId());
            cl = Start.sDB.getPervichObsledData(cl);
            JOptionPane.showMessageDialog(null, "Всвязи с отсутствием обследований за последниеф 6 месяцев, было добавленно новое обследование", "Внимание", 1);
                
        }
        initComponents();
        this.nameVrach.setText(vr.getFIO(vr));
        this.nameClient.setText(cl.getFIO_client());
        this.nameFilial.setText(Start.idfilial);
        idObsled = cl.getPervichObsled().size() - 1;//Записываем последний index в коллекции Обследовании
        PervObsl_Table.setRowSelectionInterval(PervObsl_Table.getRowCount() - 1, PervObsl_Table.getRowCount() - 1);
        Poseshl_Table.setRowSelectionInterval(Poseshl_Table.getRowCount() - 1, Poseshl_Table.getRowCount() - 1);
        idPosesh = poseshCalc(idPoseshDB);
        PervObsl_Table.getColumnModel().getColumn(0).setPreferredWidth(20);
        Poseshl_Table.getColumnModel().getColumn(0).setPreferredWidth(20);
        DrawTeethPerv();
        DrawTeethWork();
    }

    private int poseshCalc(int idpos) {
        for (int i = 0; i < cl.getPosesh().size(); i++) {
            if (cl.getPosesh().get(i).getIdPosesh() == idpos) {
                return i;
            }
        }
        return 0;
    }

    public void Redraw() {
        if (MainForm.Redraw_icons) {
            idPosesh = poseshCalc(idPoseshDB);
            Tab1.removeAll();
            Tab2.removeAll();
            DrawTeethPerv();
            DrawTeethWork();
            MainForm.Redraw_icons = false;
        }
    }

    private void DrawTeethPerv() {
        javax.swing.JPanel TObj = null;
        for (int i = 0; i < 32; i++) {
            if (i < 3) {
                TObj = new Object_Tooth(3, 0, 0, i, true);
            }
            if (i > 2 & i < 5) {
                TObj = new Object_Tooth(2, 0, 0, i, true);
            }
            if (i > 4 & i < 8) {
                TObj = new Object_Tooth(1, 0, 2, i, true);
            }

            if (i > 7 & i < 11) {
                TObj = new Object_Tooth(1, 0, 2, i, true);
            }
            if (i > 10 & i < 13) {
                TObj = new Object_Tooth(2, 0, 0, i, true);
            }
            if (i > 12 & i < 16) {
                TObj = new Object_Tooth(3, 0, 0, i, true);
            }

            if (i > 15 & i < 19) {
                TObj = new Object_Tooth(3, 0, 1, i, true);
            }
            if (i > 18 & i < 21) {
                TObj = new Object_Tooth(2, 0, 1, i, true);
            }
            if (i > 20 & i < 24) {
                TObj = new Object_Tooth(1, 0, 3, i, true);
            }

            if (i > 23 & i < 27) {
                TObj = new Object_Tooth(1, 0, 3, i, true);
            }
            if (i > 26 & i < 29) {
                TObj = new Object_Tooth(2, 0, 1, i, true);
            }
            if (i > 28) {
                TObj = new Object_Tooth(3, 0, 1, i, true);
            }
            TObj.setName("Object_" + i);

            Integer k;
            if (i < 8) {
                k = 8 - i;
                TObj.setBounds(10 + i * 66, 2, 56, 160);
            } else if (i > 7 & i < 16) {
                k = i - 7;
                TObj.setBounds(10 + i * 66 + 10, 5, 56, 160);
            }

            if (i > 15 & i < 24) {
                TObj.setBounds(10 + (i - 16) * 66, 168, 56, 160);
            } else if (i > 23) {
                TObj.setBounds(10 + (i - 16) * 66 + 10, 168, 56, 160);
            }
            Tab1.add(TObj);
            TObj.repaint();
        }
    }

    private void DrawTeethWork() {
        javax.swing.JPanel TObj = null;
        for (int i = 0; i < 32; i++) {
            if (i < 3) {
                TObj = new Object_Tooth(3, 1, 0, i, false);
            }
            if (i > 2 & i < 5) {
                TObj = new Object_Tooth(2, 1, 0, i, false);
            }
            if (i > 4 & i < 8) {
                TObj = new Object_Tooth(1, 1, 2, i, false);
            }

            if (i > 7 & i < 11) {
                TObj = new Object_Tooth(1, 1, 2, i, false);
            }
            if (i > 10 & i < 13) {
                TObj = new Object_Tooth(2, 1, 0, i, false);
            }
            if (i > 12 & i < 16) {
                TObj = new Object_Tooth(3, 1, 0, i, false);
            }

            if (i > 15 & i < 19) {
                TObj = new Object_Tooth(3, 1, 1, i, false);
            }
            if (i > 18 & i < 21) {
                TObj = new Object_Tooth(2, 1, 1, i, false);
            }
            if (i > 20 & i < 24) {
                TObj = new Object_Tooth(1, 1, 3, i, false);
            }

            if (i > 23 & i < 27) {
                TObj = new Object_Tooth(1, 1, 3, i, false);
            }
            if (i > 26 & i < 29) {
                TObj = new Object_Tooth(2, 1, 1, i, false);
            }
            if (i > 28) {
                TObj = new Object_Tooth(3, 1, 1, i, false);
            }
            TObj.setName("Object_" + i);

            Integer k;//Создаем нумерацию зубов
            if (i < 8) {
                k = 8 - i;
                TObj.setBounds(10 + i * 66, 2, 56, 160);
            } else if (i > 7 & i < 16) {
                k = i - 7;
                TObj.setBounds(10 + i * 66 + 10, 2, 56, 160);
            }

            if (i > 15 & i < 24) {
                TObj.setBounds(10 + (i - 16) * 66, 168, 56, 160);
            } else if (i > 23) {
                TObj.setBounds(10 + (i - 16) * 66 + 10, 168, 56, 160);
            }
            Tab2.add(TObj);
            TObj.repaint();
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

        jPanel3 = new javax.swing.JPanel();
        Tab1 = new javax.swing.JPanel();
        Teeth_group_number_1 = new javax.swing.JLabel();
        Teeth_group_number_2 = new javax.swing.JLabel();
        Teeth_group_number_3 = new javax.swing.JLabel();
        Teeth_group_number_4 = new javax.swing.JLabel();
        Tab2 = new javax.swing.JPanel();
        Teeth_group_number_5 = new javax.swing.JLabel();
        Teeth_group_number_6 = new javax.swing.JLabel();
        Teeth_group_number_7 = new javax.swing.JLabel();
        Teeth_group_number_8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Poseshl_Table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Name1 = new javax.swing.JLabel();
        nameClient = new javax.swing.JLabel();
        nameVrach = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PervObsl_Table = new javax.swing.JTable();
        sprPerenaprav = new javax.swing.JButton();
        newPerenaprav = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nameFilial = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        addObsled = new javax.swing.JButton();
        addObsled1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        addObsled2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Зубная формула");
        setBackground(new java.awt.Color(211, 207, 203));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(211, 207, 203));
        jPanel3.setLayout(null);

        Tab1.setBackground(new java.awt.Color(211, 207, 203));
        Tab1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tab1.setMaximumSize(new java.awt.Dimension(1080, 330));
        Tab1.setMinimumSize(new java.awt.Dimension(1080, 330));
        Tab1.setPreferredSize(new java.awt.Dimension(1080, 330));
        Tab1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                Tab1ComponentShown(evt);
            }
        });
        Tab1.setLayout(null);

        Teeth_group_number_1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_1.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_1.setText("4");
        Tab1.add(Teeth_group_number_1);
        Teeth_group_number_1.setBounds(0, 210, 10, 17);

        Teeth_group_number_2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_2.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_2.setText("1");
        Tab1.add(Teeth_group_number_2);
        Teeth_group_number_2.setBounds(0, 80, 10, 17);

        Teeth_group_number_3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_3.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_3.setText("3");
        Tab1.add(Teeth_group_number_3);
        Teeth_group_number_3.setBounds(1070, 210, 10, 17);

        Teeth_group_number_4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_4.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_4.setText("2");
        Tab1.add(Teeth_group_number_4);
        Teeth_group_number_4.setBounds(1070, 80, 10, 17);

        jPanel3.add(Tab1);
        Tab1.setBounds(0, 60, 1080, 330);

        Tab2.setBackground(new java.awt.Color(211, 207, 203));
        Tab2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tab2.setMaximumSize(new java.awt.Dimension(1080, 330));
        Tab2.setMinimumSize(new java.awt.Dimension(1080, 330));
        Tab2.setPreferredSize(new java.awt.Dimension(1080, 330));
        Tab2.setLayout(null);

        Teeth_group_number_5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_5.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_5.setText("1");
        Tab2.add(Teeth_group_number_5);
        Teeth_group_number_5.setBounds(0, 80, 10, 17);

        Teeth_group_number_6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_6.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_6.setText("2");
        Tab2.add(Teeth_group_number_6);
        Teeth_group_number_6.setBounds(1070, 80, 8, 17);

        Teeth_group_number_7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_7.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_7.setText("3");
        Tab2.add(Teeth_group_number_7);
        Teeth_group_number_7.setBounds(1070, 200, 8, 17);

        Teeth_group_number_8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Teeth_group_number_8.setForeground(new java.awt.Color(102, 102, 255));
        Teeth_group_number_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Teeth_group_number_8.setText("4");
        Tab2.add(Teeth_group_number_8);
        Teeth_group_number_8.setBounds(0, 200, 8, 17);

        jPanel3.add(Tab2);
        Tab2.setBounds(0, 390, 1080, 330);
        Tab2.getAccessibleContext().setAccessibleName("");

        jScrollPane2.setMaximumSize(new java.awt.Dimension(280, 300));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(280, 300));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(280, 300));

        Poseshl_Table.setModel(dbmPosesh);
        try {
            dbmPosesh.setDataSource(Start.sDB.getRSPoseshData(cl));
        } catch (Exception ex){
            System.out.println("Создание таблицы посещения ошибка доступа к RS"+ ex);
        }
        Poseshl_Table.getTableHeader().setReorderingAllowed(false);
        Poseshl_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Poseshl_TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Poseshl_Table);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(1080, 390, 200, 300);

        jPanel2.setBackground(new java.awt.Color(211, 207, 203));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 20));
        jPanel2.setLayout(null);
        jPanel3.add(jPanel2);
        jPanel2.setBounds(0, 90, 590, 20);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Врач");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 0, 30, 20);

        Name1.setBackground(new java.awt.Color(211, 207, 203));
        Name1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Name1.setText("Клиент");
        Name1.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(Name1);
        Name1.setBounds(10, 20, 50, 20);

        nameClient.setBackground(new java.awt.Color(211, 207, 203));
        nameClient.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameClient.setForeground(new java.awt.Color(102, 102, 255));
        nameClient.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameClient.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(nameClient);
        nameClient.setBounds(70, 20, 260, 20);

        nameVrach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameVrach.setForeground(new java.awt.Color(102, 102, 255));
        nameVrach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameVrach.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(nameVrach);
        nameVrach.setBounds(70, 0, 260, 20);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(280, 250));

        PervObsl_Table.setModel(dbmPervObsl);
        try {

            dbmPervObsl.setDataSource(Start.sDB.getRSObsledData(cl));
        } catch (Exception ex){
            System.out.println("Создание таблицы основные обследования ошибка доступа к RS"+ ex);
        }
        PervObsl_Table.getTableHeader().setReorderingAllowed(false);
        PervObsl_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PervObsl_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(PervObsl_Table);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(1080, 60, 200, 300);

        sprPerenaprav.setText("Список перенаправленных");
        sprPerenaprav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sprPerenapravActionPerformed(evt);
            }
        });
        jPanel3.add(sprPerenaprav);
        sprPerenaprav.setBounds(1080, 30, 200, 30);

        newPerenaprav.setText("Создать перенаправление");
        newPerenaprav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPerenapravActionPerformed(evt);
            }
        });
        jPanel3.add(newPerenaprav);
        newPerenaprav.setBounds(1080, 0, 200, 30);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Филиал");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 40, 50, 20);

        nameFilial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nameFilial.setForeground(new java.awt.Color(102, 102, 255));
        nameFilial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nameFilial.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(nameFilial);
        nameFilial.setBounds(70, 40, 260, 20);

        jButton1.setText("Вернуться к списку клиентов");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(640, 30, 220, 30);

        addObsled.setText("Закончить");
        addObsled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addObsledActionPerformed(evt);
            }
        });
        jPanel3.add(addObsled);
        addObsled.setBounds(1180, 360, 100, 30);

        addObsled1.setText("Новое");
        addObsled1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addObsled1ActionPerformed(evt);
            }
        });
        jPanel3.add(addObsled1);
        addObsled1.setBounds(1080, 360, 100, 30);

        jButton2.setText("Подтвердить изменения");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(860, 30, 220, 30);

        addObsled2.setText("Новое");
        addObsled2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addObsled2ActionPerformed(evt);
            }
        });
        jPanel3.add(addObsled2);
        addObsled2.setBounds(1080, 690, 200, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void Tab1ComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_Tab1ComponentShown
    {//GEN-HEADEREND:event_Tab1ComponentShown
    }//GEN-LAST:event_Tab1ComponentShown
    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void PervObsl_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PervObsl_TableMouseClicked
        updatePervichObsledTable();
    }//GEN-LAST:event_PervObsl_TableMouseClicked

    private void updatePervichObsledTable() {
        Integer idPervich = (Integer) PervObsl_Table.getValueAt(PervObsl_Table.getSelectedRow(), 0);
        for (PervichObsled per : cl.getPervichObsled()) {
            if (per.getId_pervich_obsled() == idPervich) {
                idObsled = cl.getPervichObsled().indexOf(per);
            }
        }
        MainForm.Redraw_icons = true;
        Find.mf.Redraw();
    }

    private void updatePoseshTable() {

        Integer idPos = (Integer) Poseshl_Table.getValueAt(Poseshl_Table.getSelectedRow(), 0);

        for (Posesh pos : cl.getPosesh()) {
            if (pos.getIdPosesh() == idPos) {
                //idPosesh = poseshCalc(idPos);
                idPoseshDB = idPos;
                //System.out.println("cl.getPervichObsled().indexOf(pos) = " + cl.getPervichObsled().indexOf(pos));
            }
        }
        MainForm.Redraw_icons = true;
        Find.mf.Redraw();
    }

    private void sprPerenapravActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sprPerenapravActionPerformed
        SprPerenaprav sprPerenar = new SprPerenaprav(vr);
        sprPerenar.setBounds(600, 400, sprPerenar.getMinimumSize().width, sprPerenar.getMinimumSize().height);
        sprPerenar.setVisible(true);
    }//GEN-LAST:event_sprPerenapravActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Find find = new Find(vr);
        dispose();
        find.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void newPerenapravActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPerenapravActionPerformed
        SprPerenapravEdit perenEdit = new SprPerenapravEdit(this, true, 1, cl.getId_klient());
        perenEdit.setBounds(600, 400, perenEdit.getMinimumSize().width, perenEdit.getMinimumSize().height);
        perenEdit.setVisible(true);
    }//GEN-LAST:event_newPerenapravActionPerformed

    private void addObsledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addObsledActionPerformed
        if (MainForm.cl.getPervichObsled().get(MainForm.idObsled).getFlZapret() == 0) {
            //if(Start.sDB.setPodtvObsled(MainForm.cl.getPervichObsled().get(MainForm.idObsled).getId_pervich_obsled()) == 1){
            MainForm.cl.getPervichObsled().get(MainForm.idObsled).setFlZapret(1);
            JOptionPane.showMessageDialog(null, "Данные успешно обновленны", "Внимание", 2);
        } else {
            JOptionPane.showMessageDialog(null, "Данное обследование уже завершено", "Внимание", 0);
        }
    }//GEN-LAST:event_addObsledActionPerformed

    private void addObsled1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addObsled1ActionPerformed
        AddDatePervichObsled dtPervichNew = new AddDatePervichObsled(this, true);
        dtPervichNew.setBounds(500, 300, dtPervichNew.getMinimumSize().width, dtPervichNew.getMinimumSize().height);
        dtPervichNew.setVisible(true);
        PervObsl_Table.setRowSelectionInterval(PervObsl_Table.getRowCount() - 1, PervObsl_Table.getRowCount() - 1);
        updatePervichObsledTable();
    }//GEN-LAST:event_addObsled1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Start.sDB.setClientInfo(cl);

        //вывести сообщение
        Find find = new Find(vr);
        dispose();
        find.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void addObsled2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addObsled2ActionPerformed
        AddDatePosesh dtPoseshNew = new AddDatePosesh(this, true);
        dtPoseshNew.setBounds(600, 400, dtPoseshNew.getMinimumSize().width, dtPoseshNew.getMinimumSize().height);
        dtPoseshNew.setVisible(true);
        Poseshl_Table.setRowSelectionInterval(Poseshl_Table.getRowCount() - 1, Poseshl_Table.getRowCount() - 1);
        updatePoseshTable();        // TODO add your handling code here:
    }//GEN-LAST:event_addObsled2ActionPerformed

    private void Poseshl_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Poseshl_TableMouseClicked
        updatePoseshTable();
    }//GEN-LAST:event_Poseshl_TableMouseClicked

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm(null, null).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Name1;
    public static javax.swing.JTable PervObsl_Table;
    public static javax.swing.JTable Poseshl_Table;
    private javax.swing.JPanel Tab1;
    private javax.swing.JPanel Tab2;
    private javax.swing.JLabel Teeth_group_number_1;
    private javax.swing.JLabel Teeth_group_number_2;
    private javax.swing.JLabel Teeth_group_number_3;
    private javax.swing.JLabel Teeth_group_number_4;
    private javax.swing.JLabel Teeth_group_number_5;
    private javax.swing.JLabel Teeth_group_number_6;
    private javax.swing.JLabel Teeth_group_number_7;
    private javax.swing.JLabel Teeth_group_number_8;
    private javax.swing.JButton addObsled;
    private javax.swing.JButton addObsled1;
    private javax.swing.JButton addObsled2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameClient;
    private javax.swing.JLabel nameFilial;
    private javax.swing.JLabel nameVrach;
    private javax.swing.JButton newPerenaprav;
    private javax.swing.JButton sprPerenaprav;
    // End of variables declaration//GEN-END:variables
}
