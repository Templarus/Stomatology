/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author TemplaRus
 */
public class Button_Tooth extends JToggleButton
{

    int tooth_number = 0; //Что будем закрашивать(Не используется)
    int figure_id = 0;
    int root_count = 0;
    PervichObsledGraniStatus zub = null;
    PoseshStrRabZub zubR = null;
    int[][] figure = null;
    int[][] Rootfigure = null;
    int object_param = 0;
    boolean isPervich = false;
    Polygon p;
    Shape shape;
    Graphics2D GraphicsReady;
    public static Popup_Tooth_Status TSP;

    /**
     * Creates new form Tooth_icon
     *
     * @param id
     * @param type
     */
    public Button_Tooth(int tooth_number, int figure_id, int root_count, int object_param, boolean isPervich)
    {
        super();
        setContentAreaFilled(false);
        this.isPervich = isPervich;
        this.figure_id = figure_id;
        this.object_param = object_param;
        this.tooth_number = tooth_number;
        initComponents();
        this.setName("Tooth_button=" + tooth_number);
        this.setToolTipText("Figure " + figure_id);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        //Выполняеся только после InitComponent
        super.paintComponent(g);//Стандартная запись перезачи сформированой графики
        GraphicsReady = (Graphics2D) g;//Массив частей зуба и отдельная графика для него(нужно для расскраски)
        switch (figure_id) {
            case 0:
                //левая центр фигура
                figure = new int[][]{{40, 20, 8, 8, 20, 40, 36}, {40, 20, 40, 72, 92, 72, 56}, {-4, -4}};//координаты икс,координаты игрек,приращения ,цвет
                break;
            case 1:
                //центр левая фигура
                figure = new int[][]{{40, 56, 56, 40, 36}, {40, 36, 76, 72, 56}, {-4, -4}};//координаты икс,координаты игрек,приращения 
                break;
            case 2:
                //центр правая фигура
                figure = new int[][]{{72, 56, 56, 72, 76}, {40, 36, 76, 72, 56}, {-4, -4}};
                break;
            case 3:
                //центр фигура
                figure = new int[][]{{40, 56, 72, 76, 72, 56, 40, 36}, {40, 36, 40, 56, 72, 76, 72, 56}, {-4, -4}};
                break;
            case 4:
                //--правая центр фигура
                figure = new int[][]{{72, 92, 104, 104, 92, 72, 76}, {40, 20, 40, 72, 92, 72, 56}, {-4, -4}};
                break;
            case 5:
                //--нижняя фигура
                figure = new int[][]{{40, 20, 40, 72, 92, 72, 56}, {72, 92, 104, 104, 92, 72, 76}, {-4, -4}};
                break;
            case 6:
                //--верхняя фигура
                figure = new int[][]{{40, 20, 40, 72, 92, 72, 56}, {40, 20, 8, 8, 20, 40, 36}, {-4, -4}};
                break;
            case 7:
                //левый корень
                figure = new int[][]{{4, 8, 16, 24, 32, 40, 32, 24, 32}, {8, 40, 64, 80, 88, 88, 72, 48, 8}, {0, 0}};
                break;
            case 8:
                //правый корень
                figure = new int[][]{{100, 96, 88, 80, 72, 64, 72, 80, 72}, {8, 40, 64, 80, 88, 88, 72, 48, 8}, {0, 0}};
                break;
            case 9:
                //центр корень
                figure = new int[][]{{36, 36, 40, 40, 48, 56, 64, 64, 68, 68}, {8, 40, 56, 64, 88, 88, 64, 56, 40, 8}, {0, 0}};
                break;
        }

        int[] xv = figure[0]; //Выбмраем массив X
        int[] yv = figure[1]; // выбираем массив Y
        int polLength = figure[0].length;// считаем сколько точек полигона(не важно по Y или X)
        int xadd = figure[2][0];//Смещение по оси X
        int yadd = figure[2][1];//Смещение по оси Y
        int i = 0;
        for (int j : xv)//Массиву координат фигуры задаем смещение
        {
            xv[i] = j + xadd;
            yv[i] = yv[i] + yadd;
            i++;
        }

        this.p = new Polygon(xv, yv, polLength);//В массив полиговно пишем новый полигон уже с фактическими координатами

        Color c = Colorizer();
        if (getModel().isPressed()) {
            GraphicsReady.setColor(Color.lightGray);
        } else {
            GraphicsReady.setColor(c);
        }

        //GraphicsReady.setPaint(c);
        GraphicsReady.fillPolygon(p);
        GraphicsReady.setPaint(Color.black);
        GraphicsReady.drawPolygon(p);
        int selector = 0;

        if (object_param < 2) {
            selector = zub.getIdPervich();
        } else {
            selector = zubR.getIdRab();
        }
        if (selector != -1) {

            switch (selector) {
                case 42:
                    Rooth_Icon_type0();
                    break;
                case 45:
                    Rooth_Icon_type1();
                    break;
                case 46:
                    Rooth_Icon_type2();
                    break;
                case 47:
                    Rooth_Icon_type3();
                    break;
                case 48:
                    Rooth_Icon_type4();
                    break;
                case 49:
                    Rooth_Icon_type5();
                    break;
                case 50:
                    Rooth_Icon_type6();
                    break;
                case 51:
                    Rooth_Icon_type7();
                    break;
                case 52:
                    Rooth_Icon_type8();
                    break;
                case 53:
                    Rooth_Icon_type9();
                    break;
            }

        }
        super.paintComponent(GraphicsReady);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(211, 207, 203));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setMaximumSize(new java.awt.Dimension(50, 60));
        setMinimumSize(new java.awt.Dimension(50, 60));
        setPreferredSize(new java.awt.Dimension(50, 60));
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                formMouseClicked(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents
public void Rooth_Icon_type0() //статус корня - здоровый
    {

    }

    private void Rooth_Icon_type1() //статус корня - удалён
    {
        GraphicsReady.setPaint(Color.red);
        switch (figure_id) {
            case 7:
                GraphicsReady.drawLine(2, 20, 34, 55);
                ;
                GraphicsReady.drawLine(34, 20, 2, 55);
                break;
            case 8:
                GraphicsReady.drawLine(74, 20, 102, 55);
                GraphicsReady.drawLine(102, 20, 74, 55);
                break;
            case 9:
                GraphicsReady.drawLine(34, 20, 70, 55);
                GraphicsReady.drawLine(70, 20, 34, 55);
                break;
        }

    }

    private void Rooth_Icon_type2() //статус корня - частичное пломбирование
    {
//    figure=new int[][]{{4,8,16,24,32,40,32,24,32},{8,40,64,80,88,88,72,48,8},{0,0}};
//    figure=new int[][]{{100,96,88,80,72,64,72,80,72},{8,40,64,80,88,88,72,48,8}  ,{0,0}};
//    figure=new int[][]{{36,36,40,40,48,56,64,64,68,68},{8,40,56,64,88,88,64,56,40,8},{0,0}};
        GraphicsReady.setPaint(Color.blue);
        switch (figure_id) {
            case 7:
                Rootfigure = new int[][]{{8, 12, 16, 18, 22, 28, 24, 20, 28}, {8, 36, 54, 64, 72, 72, 57, 44, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
            case 8: //{8,44,57,72,72,64,54,36,8}
                Rootfigure = new int[][]{{96, 92, 88, 86, 82, 76, 80, 84, 76}, {8, 36, 54, 64, 72, 72, 57, 44, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
            case 9:
                Rootfigure = new int[][]{{40, 40, 44, 44, 48, 56, 60, 60, 64, 64}, {8, 30, 46, 54, 68, 68, 54, 46, 30, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
        }

    }

    private void Rooth_Icon_type3()//статус корня - кариес
    {
        GraphicsReady.setPaint(Color.red);
        switch (figure_id) {
            case 7:
                Rootfigure = new int[][]{{8, 12, 16, 18, 22, 28, 24, 20, 28}, {8, 36, 54, 64, 72, 72, 57, 44, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
            case 8:
                Rootfigure = new int[][]{{96, 92, 88, 86, 82, 76, 80, 84, 76}, {8, 36, 54, 64, 72, 72, 57, 44, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
            case 9:
                Rootfigure = new int[][]{{40, 40, 44, 44, 48, 56, 60, 60, 64, 64}, {8, 30, 46, 54, 68, 68, 54, 46, 30, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
        }
    }

    private void Rooth_Icon_type4()//статус корня - штифт
    {
        GraphicsReady.setPaint(Color.gray);
        switch (figure_id) {
            case 7:
                Rootfigure = new int[][]{{14, 14, 18, 22, 22}, {8, 26, 30, 26, 8}, {0, 0}};
                Polygon p = new Polygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                GraphicsReady.fillPolygon(p);
                break;
            case 8:
                Rootfigure = new int[][]{{90, 90, 86, 82, 82}, {8, 26, 30, 26, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
            case 9:
                Rootfigure = new int[][]{{46, 46, 52, 58, 58}, {8, 26, 30, 26, 8}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                break;
        }

    }

    private void Rooth_Icon_type5()//статус корня -инородное тело
    {
        GraphicsReady.setPaint(Color.black);
        switch (figure_id) {
            case 7:
                GraphicsReady.fillOval(8, 35, 9, 9);
                break;
            case 8:
                GraphicsReady.fillOval(88, 35, 9, 9);
                break;
            case 9:
                GraphicsReady.fillOval(48, 35, 9, 9);
                break;
        }
    }

    private void Rooth_Icon_type6()//статус корня - перфорация
    {
        GraphicsReady.setPaint(Color.CYAN);
        switch (figure_id) {
            case 7:
                Rootfigure = new int[][]{{6, 25, 25, 6}, {20, 35, 45, 30}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                GraphicsReady.setPaint(Color.black);
                GraphicsReady.drawLine(6, 20, 25, 35);
                GraphicsReady.drawLine(6, 30, 25, 45);
                break;
            case 8:
                Rootfigure = new int[][]{{98, 78, 78, 98}, {30, 45, 35, 20}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                GraphicsReady.setPaint(Color.black);
                GraphicsReady.drawLine(78, 35, 98, 20);
                GraphicsReady.drawLine(78, 45, 98, 30);
                break;
            case 9:
                Rootfigure = new int[][]{{40, 64, 64, 40}, {40, 40, 50, 50}, {0, 0}};
                GraphicsReady.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                GraphicsReady.setPaint(Color.black);
                GraphicsReady.drawLine(40, 40, 64, 40);
                GraphicsReady.drawLine(40, 50, 64, 50);

                break;
        }
    }

    private void Rooth_Icon_type7()//статус корня - пломбировка
    {
        GraphicsReady.setPaint(new Color(0, 0, 200));
        GraphicsReady.fillPolygon(p);
        GraphicsReady.setPaint(Color.black);
        GraphicsReady.drawPolygon(p);
    }

    private void Rooth_Icon_type8()//статус корня - изменения
    {

        GraphicsReady.setPaint(Color.gray);
        Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 12);
        switch (figure_id) {
            case 7:
                GraphicsReady.fillOval(8, 15, 15, 15);
                GraphicsReady.setFont(font);
                GraphicsReady.setColor(Color.red);
                GraphicsReady.drawString("И", 12, 28);
                break;
            case 8:
                GraphicsReady.fillOval(82, 15, 15, 15);
                GraphicsReady.setFont(font);
                GraphicsReady.setColor(Color.red);
                GraphicsReady.drawString("И", 86, 28);
                break;
            case 9:
                GraphicsReady.fillOval(45, 15, 15, 15);
                GraphicsReady.setFont(font);
                GraphicsReady.setColor(Color.red);
                GraphicsReady.drawString("И", 49, 28);
                break;
        }
    }

    private void Rooth_Icon_type9()//статус корня - вставка
    {

        GraphicsReady.setPaint(Color.green);
        switch (figure_id) {
            case 7:
                GraphicsReady.fillRect(13, 12, 10, 20);
                break;
            case 8:
                GraphicsReady.fillRect(81, 12, 10, 20);
                break;
            case 9:
                GraphicsReady.fillRect(47, 12, 10, 20);
                break;
        }
    }

    private Color Colorizer()
    {
        switch (object_param) {
            case 0:
                this.zub = Find.mf.cl.getPervichObsled().get(MainForm.idObsled).getPervichObsledStr().get(tooth_number).getStatGranColor().get(figure_id);
                return new Color(zub.getColor()[0], zub.getColor()[1], zub.getColor()[2]);
            case 1:
                this.zub = Object_Tooth.TP.getStatusGrZub().get(figure_id); /// ДЛЯ Первички
                return new Color(zub.getColor()[0], zub.getColor()[1], zub.getColor()[2]);
            case 2:
                this.zubR = Object_Tooth.RSP.getStatusGrZub().get(figure_id); /// ДЛЯ RABOTA_STATUS
                return new Color(zubR.getColor()[0], zubR.getColor()[1], zubR.getColor()[2]);
            case 3:
                this.zubR = Find.mf.cl.getPosesh().get(MainForm.idPosesh).getPoseshStr().get(tooth_number).getPoseshRabZub().get(figure_id);
                return new Color(zubR.getColor()[0], zubR.getColor()[1], zubR.getColor()[2]);
        }
        return new Color(240, 240, 240);

    }
    private void formMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseClicked
    {//GEN-HEADEREND:event_formMouseClicked
        //if (MainForm.idPoseshDB == 0) {
            javax.swing.JToggleButton jb = (javax.swing.JToggleButton) evt.getSource();
            Point mloc = MouseInfo.getPointerInfo().getLocation();

            if (figure_id < 7) {
                TSP = new Popup_Tooth_Status(new javax.swing.JFrame(), true, 3, this.figure_id, this.tooth_number, this.isPervich);
                TSP.setBounds(mloc.x, mloc.y, TSP.getPreferredSize().width, TSP.getPreferredSize().height);
                TSP.setVisible(true);
            } else {
                if (this.root_count == 1) {
                    TSP = new Popup_Tooth_Status(new javax.swing.JFrame(), true, 1, this.figure_id, this.tooth_number, this.isPervich);
                } else {
                    TSP = new Popup_Tooth_Status(new javax.swing.JFrame(), true, 2, this.figure_id, this.tooth_number, this.isPervich);
                }
                TSP.setBounds(mloc.x, mloc.y, TSP.getPreferredSize().width, TSP.getPreferredSize().height);
                TSP.setVisible(true);
            }
            System.out.println("JB=" + figure_id);
        //}
    }//GEN-LAST:event_formMouseClicked

    @Override
    public boolean contains(int x, int y)
    {
//        // If the button has changed size, make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
//            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
            shape = p;
        }
        return shape.contains(x, y);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
