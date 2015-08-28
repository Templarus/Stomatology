
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javafx.scene.Parent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author TemplaRus
 */
public final class Popup_Pervich extends javax.swing.JDialog
{

    Integer root_count;
    int tooth_number = 0;
    int tooth_type = 0;
    String Result_Status = "";
    int idObs = MainForm.idObsled;
    private ArrayList<PervichObsledZubStatus> statusZub = new ArrayList<>();
    private ArrayList<PervichObsledDesnaStatus> statusDesna = new ArrayList<>();
    private ArrayList<PervichObsledGraniStatus> statusGrZub = new ArrayList<>();

    JPanel tab1J;
    JPanel tab2J;
    JPanel TIcon1;
    JPanel TIcon2;

    public ArrayList<PervichObsledZubStatus> getStatusZub()
    {
        return statusZub;
    }

    public void setStatusZub(ArrayList<PervichObsledZubStatus> statusZub)
    {
        this.statusZub = statusZub;
    }

    public ArrayList<PervichObsledGraniStatus> getStatusGrZub()
    {
        return statusGrZub;
    }

    public void setStatusGrZub(ArrayList<PervichObsledGraniStatus> statusGrZub)
    {
        this.statusGrZub = statusGrZub;
    }

    public ArrayList<PervichObsledDesnaStatus> getStatusDesna()
    {
        return statusDesna;
    }

    public void setStatusDesna(ArrayList<PervichObsledDesnaStatus> statusDesna)
    {
        this.statusDesna = statusDesna;
    }
    /**
     * Creates new form Pervich_Popup
     * @param parent new javax.swing.JFrame()
     * @param modal default = true
     * @param root_count - количество корней
     * @param tooth_number - номер зуба
     * @param tooth_type - объект отрисовывается для первичного обследования
     */
    public Popup_Pervich(javax.swing.JFrame parent, boolean modal, int root_count, int tooth_number, int tooth_type)
    {
        super(parent, modal);
        initComponents();
        this.root_count = root_count;
        this.tooth_type = tooth_type;
        this.tooth_number = tooth_number;

        for (PervichObsledZubStatus zubStat : MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).getStatus()) {
            PervichObsledZubStatus newStat = new PervichObsledZubStatus(zubStat.getIdSprPervich(), zubStat.isStatusBoolean());
            this.statusZub.add(newStat);
        }

        for (PervichObsledGraniStatus statGrZub : MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).getStatGranColor()) {
            PervichObsledGraniStatus newstatGrZub = new PervichObsledGraniStatus(statGrZub.getIdPervich(), statGrZub.getIdZonaZub(), statGrZub.getPrim(), statGrZub.getColor());
            this.statusGrZub.add(newstatGrZub);
        }
        for (PervichObsledDesnaStatus statusDesna : MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).getStatDesna()) {
            PervichObsledDesnaStatus newstatusDesna = new PervichObsledDesnaStatus(statusDesna.getIdSprPervich(), statusDesna.isStatusBoolean());
            this.statusDesna.add(newstatusDesna);
        }

        FillTabs();
        FillButtons();
    }

    public void Redraw()
    {
        jPanel2.removeAll();
        FillTabs();
    }

    public void FillButtons()
    {
        for (int i = 0; i < Start.sDB.getSprPervichData(4).size(); i++) {
            JToggleButton jb = new JToggleButton();//Добавояем кнопки первичного обследования
            jb.setFont(new java.awt.Font("Arial", 1, 14));
            jb.setText(Start.sDB.getSprPervichData(4).get(i).getKodKn());

            jb.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt)
                {
                    jbMouseClicked(evt);
                }
            });
            if (statusZub.get(i).isStatusBoolean()) {
                jb.setSelected(true);
            }
            jPanel4.add(jb);
        }
        for (int i = 0; i < Start.sDB.getSprPervichData(5).size(); i++) {
            JToggleButton jb = new JToggleButton();//Добавояем кнопки первичного обследования
            jb.setFont(new java.awt.Font("Arial", 1, 14));
            jb.setText(Start.sDB.getSprPervichData(5).get(i).getKodKn());

            jb.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt)
                {
                    jbMouseClicked2(evt);
                }
            });
            if (statusDesna.get(i).isStatusBoolean()) {
                jb.setSelected(true);
            }
            jPanel3.add(jb);
        }
    }

    public void FillTabs()
    {
        boolean isIcon = false;
        boolean drawRoots = false;
        for (PervichObsledGraniStatus zubstat : this.statusGrZub) {
            if (zubstat.getIdZonaZub() == 12 & zubstat.getIdPervich() != -1 & zubstat.getIdPervich()!= 74) {
                TIcon1 = new Icon_PrevichTooth(this.root_count, zubstat.getIdPervich(), this.tooth_number, true, true);
                if (zubstat.getIdPervich() != 61 & zubstat.getIdPervich() != 75) {
                    TIcon1.setBounds(36, 5, 104, 104);
                    drawRoots = true;
                } else {
                    TIcon1.setBounds(113, 5, 104, 104);
                }
                jPanel2.add(TIcon1);
                isIcon = true;
            }
        }
        if (!isIcon) {
            if (this.tooth_type == 0) {
                tab1J = new Object_Tooth(this.root_count, 2, 4, this.tooth_number, true);
            } else {
                tab1J = new Object_Tooth(this.root_count, 2, 5, this.tooth_number, true);
            }
            tab1J.setBounds(36, 5, 104, 104);
            tab1J.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            jPanel2.add(tab1J);
            drawRoots = true;
        }
        if (drawRoots) {
            tab2J = new Object_Tooth(this.root_count, 2, 5 + this.root_count, this.tooth_number, true); //4 3
            tab2J.setBounds(190, 5, 104, 104);
            tab2J.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
            jPanel2.add(tab2J);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button_Accept = new javax.swing.JButton();
        button_Discard = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(330, 540));
        setModalExclusionType(null);
        setResizable(false);
        setSize(new java.awt.Dimension(330, 540));
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setMaximumSize(new java.awt.Dimension(330, 550));
        jPanel1.setMinimumSize(new java.awt.Dimension(330, 550));
        jPanel1.setPreferredSize(new java.awt.Dimension(330, 540));
        jPanel1.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMaximumSize(new java.awt.Dimension(324, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(324, 230));
        jPanel4.setPreferredSize(new java.awt.Dimension(324, 230));
        jPanel4.setLayout(new java.awt.GridLayout(6, 6));
        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 324, 230);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Весь зуб");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setMaximumSize(new java.awt.Dimension(240, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(240, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(240, 20));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 240, 240, 20);

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Десна");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setMaximumSize(new java.awt.Dimension(240, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(240, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(240, 20));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 310, 240, 20);

        button_Accept.setText("Подтвердить");
        button_Accept.setMaximumSize(new java.awt.Dimension(165, 40));
        button_Accept.setMinimumSize(new java.awt.Dimension(165, 40));
        button_Accept.setPreferredSize(new java.awt.Dimension(165, 40));
        button_Accept.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                button_AcceptActionPerformed(evt);
            }
        });
        jPanel1.add(button_Accept);
        button_Accept.setBounds(0, 460, 160, 40);

        button_Discard.setText("Отмена");
        button_Discard.setMaximumSize(new java.awt.Dimension(165, 40));
        button_Discard.setMinimumSize(new java.awt.Dimension(165, 40));
        button_Discard.setPreferredSize(new java.awt.Dimension(165, 40));
        button_Discard.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                button_DiscardActionPerformed(evt);
            }
        });
        jPanel1.add(button_Discard);
        button_Discard.setBounds(170, 460, 160, 40);

        jPanel2.setPreferredSize(new java.awt.Dimension(330, 110));
        jPanel2.setLayout(null);
        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 340, 330, 110);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(324, 40));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 265, 324, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 330, 540);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel1MouseClicked
    {//GEN-HEADEREND:event_jLabel1MouseClicked
        Point mloc = MouseInfo.getPointerInfo().getLocation();
        Button_Tooth.TSP = new Popup_Tooth_Status(new javax.swing.JFrame(), true, 0, 0, this.tooth_number, true);
        Button_Tooth.TSP.setBounds(mloc.x, mloc.y, Button_Tooth.TSP.getPreferredSize().width, Button_Tooth.TSP.getPreferredSize().height);
        Button_Tooth.TSP.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel2MouseClicked
    {//GEN-HEADEREND:event_jLabel2MouseClicked
        Point mloc = MouseInfo.getPointerInfo().getLocation();
        Button_Tooth.TSP = new Popup_Tooth_Status(new javax.swing.JFrame(), true, 0, 0, this.tooth_number, true);
        Button_Tooth.TSP.setBounds(mloc.x, mloc.y, Button_Tooth.TSP.getPreferredSize().width, Button_Tooth.TSP.getPreferredSize().height);
        Button_Tooth.TSP.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void button_AcceptActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_button_AcceptActionPerformed
    {//GEN-HEADEREND:event_button_AcceptActionPerformed
        MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).setStatus(statusZub);
        MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).setStatGranColor(statusGrZub);
        MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).setStatDesna(statusDesna);

        MainForm.Redraw_icons = true;
        Find.mf.Redraw();
        this.dispose();
    }//GEN-LAST:event_button_AcceptActionPerformed

    private void button_DiscardActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_button_DiscardActionPerformed
    {//GEN-HEADEREND:event_button_DiscardActionPerformed
        this.statusZub.removeAll(statusZub);
        this.statusDesna.removeAll(statusDesna);
        this.statusGrZub.removeAll(statusGrZub);
        this.dispose();
        MainForm.Redraw_icons = true;
        Find.mf.Redraw();
    }//GEN-LAST:event_button_DiscardActionPerformed

    public void jbMouseClicked(java.awt.event.MouseEvent evt)
    {
        javax.swing.JToggleButton jb = (javax.swing.JToggleButton) evt.getSource();
        if (!statusZub.get(jPanel4.getComponentZOrder(jb)).isStatusBoolean()) //Запись в объект статуса
        {
            statusZub.get(jPanel4.getComponentZOrder(jb)).setStatusBoolean(true);
        } else {
            statusZub.get(jPanel4.getComponentZOrder(jb)).setStatusBoolean(false);
        }
    }

    public void jbMouseClicked2(java.awt.event.MouseEvent evt)
    {
        javax.swing.JToggleButton jb = (javax.swing.JToggleButton) evt.getSource();
        if (!statusDesna.get(jPanel3.getComponentZOrder(jb)).isStatusBoolean()) //Запись в объект статуса
        {
            statusDesna.get(jPanel3.getComponentZOrder(jb)).setStatusBoolean(true);
        } else {
            statusDesna.get(jPanel3.getComponentZOrder(jb)).setStatusBoolean(false);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(() ->
        {
            Popup_Pervich dialog = new Popup_Pervich(new javax.swing.JFrame(), true, -1, -1, -1);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Accept;
    private javax.swing.JButton button_Discard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
