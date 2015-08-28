package stomatology;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author TemplaRus
 */
public class Object_Tooth extends javax.swing.JPanel {

    int root_count = 0; //Кол-во корней передается в конструктор, для отрисовки
    int type = 0; // 0 - корнями вверх, 1 - корнями вниз, 2 и 3 - для раздельного отображения
    Integer popup_id; //Какую форму вызываем для зуба 0 - для первичного обследования, 1- для посещения.
    final int tooth_number; //Номер зуба (конкретного экземпляра)
    boolean isPervich = false;
    String Result_Status = ""; //Строка статуса собирается с подписей кнопок
    String Result_StatusD = ""; //Строка статуса десны собирается с подписей кнопок
    public static Popup_Rabota RSP; //Форма зуба вызываемая из зубной формулы посещения
    public static Popup_Pervich TP;
    int[] Num =
    {
        18, 17, 16, 15, 14, 13, 12, 11, 21, 22, 23, 24, 25, 26, 27, 28, 48, 47, 46, 45, 44, 43, 42, 41, 31, 32, 33, 34, 35, 36, 37, 38
    };
    int[] specNum =
    {
        18, 17, 16, 55, 54, 53, 52, 51, 61, 62, 63, 64, 65, 66, 67, 68, 48, 47, 46, 85, 84, 83, 82, 81, 71, 72, 73, 74, 75, 76, 77, 78
    };

    /**
     * Creates new form Tooth_object
     *
     * @param root_count количество корней
     * @param popup_id что открывать -> 0:Tooth_object_previch - для первичного
     * 1:Tooth_object_work - для посещения 2:Tooth_object_button(true) -для
     * popup первичного 3:Tooth_object_button(false) -для popup посещения
     * @param type - тип id_categ_rab из таблицы S_Pervich
     * @param tooth_number - номер зуба
     * @param isPervich - объект отрисовывается для первичного обследования
     */
    public Object_Tooth(int root_count, int popup_id, int type, int tooth_number, boolean isPervich) {
        this.tooth_number = tooth_number;
        this.type = type; // 0 - корнями вверх, 1 - корнями вниз, 2 и 3 - для раздельного отображения
        this.root_count = root_count;
        this.popup_id = popup_id;
        this.isPervich = isPervich;
        initComponents();
        switch (this.popup_id)
        {
            case 0:
                Tooth_object_previch();
                break;
            case 1:
                Tooth_object_work();
                break;
            case 2:
                Tooth_object_button(true);
                break;
            case 3:
                Tooth_object_button(false);
                break;
        }
    }

    private void Tooth_object_previch() {
        int idObs = MainForm.idObsled;
        boolean isIcon = false;
        JPanel TIcon = null;

        for (PervichObsledGraniStatus zubstat : MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).getStatGranColor())
        {
            if (zubstat.getIdZonaZub() == 12 && zubstat.getIdPervich() != -1)
            {
                //System.err.println("РИСУЕМ ИКОНКУ");
                TIcon = new Icon_PrevichTooth(this.root_count, zubstat.getIdPervich(), this.tooth_number, false, false);// ВМЕСТО 4 - нужно подставить номер чего хотим рисовать
                isIcon = true;
                this.add(TIcon);

            }
            if (zubstat.getIdPervich() == 74)
            {
                System.err.println("123123123123123");
                number.setText(Integer.toString(specNum[tooth_number]));
            } else
            {
                number.setText(Integer.toString(Num[tooth_number]));
            }
        }

        if (!isIcon)
        {
            TIcon = new Icon_PrevichTooth(this.root_count, this.type, this.tooth_number, false, false);
            this.add(TIcon);
        }
        if (tooth_number < 16)
        {
            Desna_status.setLocation(0, 0);
            TIcon.setBounds(0, 24, 56, 104);
            Tooth_status.setLocation(0, 128);
            number.setLocation(0, 144);
        } else
        {
            number.setLocation(0, 0);
            TIcon.setBounds(0, 36, 56, 104);
            Tooth_status.setLocation(0, 16);
            Desna_status.setLocation(0, 144);

        }
        for (int i = 0; i < Start.sDB.getSprPervichData(4).size(); i++)
        {
            if (MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).getStatus().get(i).isStatusBoolean())
            {
                Result_Status = Result_Status + Start.sDB.getSprPervichData(4).get(i).getKodKn() + ",";
            }
        }
        for (int i = 0; i < Start.sDB.getSprPervichData(5).size(); i++)
        {
            if (MainForm.cl.getPervichObsled().get(idObs).getPervichObsledStr().get(tooth_number).getStatDesna().get(i).isStatusBoolean())
            {
                Result_StatusD = Result_StatusD + Start.sDB.getSprPervichData(5).get(i).getKodKn() + ",";
            }
        }
        if (!"".equals(Result_Status))
        {
            Result_Status = Result_Status.substring(0, Result_Status.length() - 1);
        }
        if (!"".equals(Result_StatusD))
        {
            Result_StatusD = Result_StatusD.substring(0, Result_StatusD.length() - 1);
        }
        Tooth_status.setText(Result_Status);
        Tooth_status.setToolTipText(Result_Status);
        Desna_status.setText(Result_StatusD);
        Desna_status.setToolTipText(Result_StatusD);
        jPanel1.setToolTipText("Зуб № " + number.getText());
    }

    private void Tooth_object_work() {
        boolean isIcon = false;
        JPanel TIcon = null;
        int idPos = 0;//MainForm.idPosesh;

        for (PoseshStrRabZub zubrab : MainForm.cl.getPosesh().get(idPos).getPoseshStr().get(tooth_number).getPoseshRabZub())
        {
            if (zubrab.getIdZonaZub() == 12 && zubrab.getIdRab() != -1)
            {
                //System.err.println("РИСУЕМ ИКОНКУ");
                TIcon = new Icon_RabotaTooth(this.root_count, zubrab.getIdRab(), this.tooth_number, false, false);// ВМЕСТО 4 - нужно подставить номер чего хотим рисовать
                isIcon = true;
                TIcon.setBounds(0, 24, 56, 104);
                this.add(TIcon);
            }
        }

        if (!isIcon)
        {
            TIcon = new Icon_RabotaTooth(this.root_count, this.type, this.tooth_number, false, false);//В конструктор передаем массив работ и цветов проведенным над зубом
            TIcon.setBounds(0, 24, 56, 104);
            this.add(TIcon);
        }
        if (tooth_number < 16)
        {
            TIcon.setBounds(0, 20, 56, 104);
            number.setLocation(0, 144);
        } else
        {
            number.setLocation(0, 0);
            TIcon.setBounds(0, 36, 56, 104);
        }
        Tooth_status.setVisible(false);
        Desna_status.setVisible(false);

        if (this.type == 74)
        {
            number.setText(Integer.toString(specNum[tooth_number]));
        } else
        {
            number.setText(Integer.toString(Num[tooth_number]));
        }

        jPanel1.setToolTipText("Work_string");
    }

    private void Tooth_object_button(boolean isPervich) {
        this.setSize(104, 104);
        Button_Tooth[] JButtonsReady = new Button_Tooth[6];
        for (int i = 0; i < 6; i++)
        {
            JButtonsReady[i] = new Button_Tooth(-1, -1, -1, -1, false);
            JButtonsReady[i].setEnabled(false);
        }
        System.out.println("Tooth_object PAINTED!");
        System.out.println("TYPE PAINTED! " + this.type);
        int selector = 0;
        if (isPervich)
        {
            selector = 1;
        } else
        {
            selector = 2;
        }
        switch (type)
        {
            case 4://рисуем ТОЛЬКО центр для PervichPopup - ЗУБ С ДВУМЯ ЦЕНТРАЛЬНЫМИ ЧАСТЯМИ
                JButtonsReady[0] = new Button_Tooth(this.tooth_number, 0, this.root_count, selector, this.isPervich);
                JButtonsReady[1] = new Button_Tooth(this.tooth_number, 1, this.root_count, selector, this.isPervich);
                JButtonsReady[2] = new Button_Tooth(this.tooth_number, 2, this.root_count, selector, this.isPervich);
                JButtonsReady[3] = new Button_Tooth(this.tooth_number, 4, this.root_count, selector, this.isPervich);
                JButtonsReady[4] = new Button_Tooth(this.tooth_number, 5, this.root_count, selector, this.isPervich);
                JButtonsReady[5] = new Button_Tooth(this.tooth_number, 6, this.root_count, selector, this.isPervich);
                JButtonsReady[0].setEnabled(true);
                JButtonsReady[1].setEnabled(true);
                JButtonsReady[2].setEnabled(true);
                JButtonsReady[3].setEnabled(true);
                JButtonsReady[4].setEnabled(true);
                JButtonsReady[5].setEnabled(true);
                break;
            case 5://рисуем ТОЛЬКО центр для PervichPopup - ЗУБ С ОДНОЙ ЦЕНТРАЛЬНЫМИ ЧАСТЯМИ
                JButtonsReady[0] = new Button_Tooth(this.tooth_number, 0, this.root_count, selector, this.isPervich);
                JButtonsReady[1] = new Button_Tooth(this.tooth_number, 3, this.root_count, selector, this.isPervich);
                JButtonsReady[2] = new Button_Tooth(this.tooth_number, 4, this.root_count, selector, this.isPervich);
                JButtonsReady[3] = new Button_Tooth(this.tooth_number, 5, this.root_count, selector, this.isPervich);
                JButtonsReady[4] = new Button_Tooth(this.tooth_number, 6, this.root_count, selector, this.isPervich);
                JButtonsReady[0].setEnabled(true);
                JButtonsReady[1].setEnabled(true);
                JButtonsReady[2].setEnabled(true);
                JButtonsReady[3].setEnabled(true);
                JButtonsReady[4].setEnabled(true);
                break;
            case 6: //1 кореньдля PervichPopup
                JButtonsReady[0] = new Button_Tooth(this.tooth_number, 9, this.root_count, selector, this.isPervich);
                JButtonsReady[0].setEnabled(true);
                break;
            case 7: //левый и правый для PervichPopup
                JButtonsReady[0] = new Button_Tooth(this.tooth_number, 7, this.root_count, selector, this.isPervich);
                JButtonsReady[1] = new Button_Tooth(this.tooth_number, 8, this.root_count, selector, this.isPervich);
                JButtonsReady[0].setEnabled(true);
                JButtonsReady[1].setEnabled(true);
                break;
            case 8:// все корни для PervichPopup
                JButtonsReady[0] = new Button_Tooth(this.tooth_number, 7, this.root_count, selector, this.isPervich);
                JButtonsReady[1] = new Button_Tooth(this.tooth_number, 8, this.root_count, selector, this.isPervich);
                JButtonsReady[2] = new Button_Tooth(this.tooth_number, 9, this.root_count, selector, this.isPervich);
                JButtonsReady[0].setEnabled(true);
                JButtonsReady[1].setEnabled(true);
                JButtonsReady[2].setEnabled(true);
                break;
        }
        int count = 0;
        for (Button_Tooth j : JButtonsReady)
        {
            if (j.isEnabled())
            {
                count++;
            }
        }
        for (int i = 0; i < count; i++)
        { //JButtonsReady[i].setLocation(0, 0);
            JButtonsReady[i].setBounds(0, 0, 104, 104);
            JButtonsReady[i].setVisible(true);
            this.add(JButtonsReady[i]);
        }
        this.remove(jPanel1);
        this.remove(Tooth_status);
        this.remove(Desna_status);
        this.remove(number);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        Tooth_status = new javax.swing.JLabel();
        Desna_status = new javax.swing.JLabel();
        number = new javax.swing.JLabel();

        setBackground(new java.awt.Color(211, 207, 203));
        setMaximumSize(new java.awt.Dimension(54, 160));
        setMinimumSize(new java.awt.Dimension(54, 160));
        setPreferredSize(new java.awt.Dimension(54, 160));
        setLayout(null);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(54, 110));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(null);
        add(jPanel1);
        jPanel1.setBounds(0, 14, 54, 110);

        Tooth_status.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Tooth_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tooth_status.setText("Status");
        Tooth_status.setMaximumSize(new java.awt.Dimension(50, 20));
        Tooth_status.setMinimumSize(new java.awt.Dimension(50, 20));
        Tooth_status.setPreferredSize(new java.awt.Dimension(56, 10));
        add(Tooth_status);
        Tooth_status.setBounds(0, 120, 56, 20);

        Desna_status.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Desna_status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Desna_status.setText("DStat");
        Desna_status.setPreferredSize(new java.awt.Dimension(56, 10));
        add(Desna_status);
        Desna_status.setBounds(0, 0, 56, 20);

        number.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        number.setForeground(new java.awt.Color(51, 51, 255));
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("Num");
        number.setPreferredSize(new java.awt.Dimension(56, 10));
        add(number);
        number.setBounds(0, 140, 56, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

        Point mloc = MouseInfo.getPointerInfo().getLocation();
        switch (this.popup_id)
        {
            case 0:

                //if(!Start.sDB.getFlZapretObcled(MainForm.cl.getPervichObsled().get(MainForm.idObsled).getId_pervich_obsled()))
                if (MainForm.cl.getPervichObsled().get(MainForm.idObsled).getFlZapret() == 0)
                {
                    TP = new Popup_Pervich(new javax.swing.JFrame(), true, this.root_count, this.tooth_number, this.type);
                    TP.setBounds(mloc.x, mloc.y, TP.getMinimumSize().width, TP.getMinimumSize().height);
                    TP.setName(this.getName());
                    TP.setTitle("Первичное обследование зуба № " + number.getText());
                    TP.setVisible(true);
                } else
                {
                    JOptionPane.showMessageDialog(null, "Нельзя редактировать проведенные обследования", "Внимание", 0);
                }
                break;
            case 1:
                //if(!Start.sDB.getgetKolNeZavObsled(MainForm.cl.getId_klient()))
                if (AddDatePervichObsled.getKolNezavershObsled() == 0)
                {
                    if (MainForm.idPoseshDB != 30000)
                    {
                        RSP = new Popup_Rabota(new javax.swing.JFrame(), true, this.root_count, this.tooth_number, this.type);
                        RSP.setBounds(mloc.x, mloc.y - 300, 300, 400);
                        RSP.setName("RSP_" + this.tooth_number);
                        RSP.setTitle("Операции над зубом номер:" + number.getText());
                        RSP.setVisible(true);
                    }
                } else
                {
                    JOptionPane.showMessageDialog(null, "Необходимо подтвердить проведения обсделования", "Внимание", 0);
                }
                break;
            case 2:
                System.err.println("OLOLO");
                break;
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Desna_status;
    private javax.swing.JLabel Tooth_status;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel number;
    // End of variables declaration//GEN-END:variables
}
