/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author TemplaRus
 */
public class Icon_RabotaTooth extends javax.swing.JPanel
{

    int root_count = 0; //Кол-во корней
    int tooth_type; // 0 - корнями вверх, 1 - корнями вниз, 2 и 3 - для раздельного отображения
    int tooth_number = 0; //Что будем закрашивать(Не используется)
    PoseshStr zubR = null;
    int[][] Rootfigure = null;
    int[][] figure0 = null;
    int[][] figure1 = null;
    int[][] figure2 = null;
    int[][] figure3 = null;
    int[][] figure4 = null;
    int[][] figure5 = null;
    int[][] figure6 = null;
    int[][] figure7 = null;
    int[][] figure8 = null;
    int[][] figure9 = null;
    int[][] dummyfigure = new int[][]{{0}, {0}, {0, 0}}; // пустышка - чтобы не корячить логику
    int[][][] figures = null;
    boolean center_splited = false;
    Polygon[] PolygonsReady;
    Color[] ColorsReady;
    Graphics2D GraphicsReadySpec;
    Boolean isSpecial = false;
    Boolean rootsUp = false;
    Boolean isScaled = false;
    Boolean forPopup = false;
    int sanated = 0;

    /**
     * Creates new form Tooth_icon
     *
     * @param id
     * @param type
     */
    public Icon_RabotaTooth(int root_count, int type, int tooth_number, boolean isScaled, boolean forPopup)
    {
        this.tooth_type = type;
        this.root_count = root_count;
        this.tooth_number = tooth_number;
        this.isScaled = isScaled;
        this.forPopup = forPopup;
        this.zubR = Find.mf.cl.getPosesh().get(MainForm.idPosesh).getPoseshStr().get(tooth_number);
        //Find.mf.cl.getPosesh().get(1).getPoseshStr().get(1).getColorRab()[1];
        initComponents();
        ///int k=Find.mf.cl.getPervichObsled().get(id).getPervichObsledStr().get(id).getStatGranColor().get(0).getColor()[1];
        // Find.mf.cl.getPervichObsled().get(id).getPervichObsledStr().get(id).getStatus().set(id, null);

    }

    public void Tooth_full_type0()//рисуем зуб корнями вверх - статус внизу ЗУБ С 2 центральными частями 
    {
        figure0 = new int[][]{{20, 10, 4, 4, 10, 20, 18}, {20, 10, 20, 36, 46, 36, 28}, {0, 48}};
        figure1 = new int[][]{{20, 28, 28, 20, 18}, {20, 18, 38, 36, 28}, {0, 48}};
        figure2 = new int[][]{{36, 28, 28, 36, 38}, {20, 18, 38, 36, 28}, {0, 48}};
        figure3 = dummyfigure;
        figure4 = new int[][]{{36, 46, 52, 52, 46, 36, 38}, {20, 10, 20, 36, 46, 36, 28}, {0, 48}};
        figure5 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {36, 46, 52, 52, 46, 36, 38}, {0, 48}};
        figure6 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {20, 10, 4, 4, 10, 20, 18}, {0, 48}};
        figure7 = new int[][]{{2, 4, 8, 12, 16, 20, 16, 12, 16}, {40, 24, 12, 4, 0, 0, 8, 20, 40}, {2, 5}};
        figure8 = new int[][]{{50, 48, 44, 40, 36, 32, 36, 40, 36}, {40, 24, 12, 4, 0, 0, 8, 20, 40}, {2, 5}};
        figure9 = new int[][]{{18, 18, 20, 20, 24, 28, 32, 32, 34, 34}, {40, 24, 16, 12, 0, 0, 12, 16, 24, 40}, {2, 5}};
        center_splited = true;
    }

    public void Tooth_full_type1()//рисуем зуб корнями вниз= статус вверху  ЗУБ С 2 центральными частями 
    {
        figure0 = new int[][]{{20, 10, 4, 4, 10, 20, 18}, {20, 10, 20, 36, 46, 36, 28}, {0, 0}};//координаты икс,координаты игрек,приращения ,цвет
        figure1 = new int[][]{{20, 28, 28, 20, 18}, {20, 18, 38, 36, 28}, {0, 0}};//координаты икс,координаты игрек,приращения 
        figure2 = new int[][]{{36, 28, 28, 36, 38}, {20, 18, 38, 36, 28}, {0, 0}};
        figure3 = dummyfigure;
        figure4 = new int[][]{{36, 46, 52, 52, 46, 36, 38}, {20, 10, 20, 36, 46, 36, 28}, {0, 0}};
        figure5 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {36, 46, 52, 52, 46, 36, 38}, {0, 0}};
        figure6 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {20, 10, 4, 4, 10, 20, 18}, {0, 0}};
        figure7 = new int[][]{{2, 4, 8, 12, 16, 20, 16, 12, 16}, {4, 20, 32, 40, 44, 44, 36, 24, 4}, {2, 56}};
        figure8 = new int[][]{{50, 48, 44, 40, 36, 32, 36, 40, 36}, {4, 20, 32, 40, 44, 44, 36, 24, 4}, {2, 56}};
        figure9 = new int[][]{{18, 18, 20, 20, 24, 28, 32, 32, 34, 34}, {4, 20, 28, 32, 44, 44, 32, 28, 20, 4}, {2, 56}};
        center_splited = true;
    }

    public void Tooth_full_type2()//рисуем зуб корнями вверх - статус внизу ЗУБ С ЦЕЛОЙ центральной частью 
    {
        figure0 = new int[][]{{20, 10, 4, 4, 10, 20, 18}, {20, 10, 20, 36, 46, 36, 28}, {0, 48}};
        figure1 = dummyfigure;
        figure2 = dummyfigure;
        figure3 = new int[][]{{20, 28, 36, 38, 36, 28, 20, 18}, {20, 18, 20, 28, 36, 38, 36, 28}, {0, 48}};
        figure4 = new int[][]{{36, 46, 52, 52, 46, 36, 38}, {20, 10, 20, 36, 46, 36, 28}, {0, 48}};
        figure5 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {36, 46, 52, 52, 46, 36, 38}, {0, 48}};
        figure6 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {20, 10, 4, 4, 10, 20, 18}, {0, 48}};
        figure7 = new int[][]{{2, 4, 8, 12, 16, 20, 16, 12, 16}, {40, 24, 12, 4, 0, 0, 8, 20, 40}, {2, 5}};
        figure8 = new int[][]{{50, 48, 44, 40, 36, 32, 36, 40, 36}, {40, 24, 12, 4, 0, 0, 8, 20, 40}, {2, 5}};
        figure9 = new int[][]{{18, 18, 20, 20, 24, 28, 32, 32, 34, 34}, {40, 24, 16, 12, 0, 0, 12, 16, 24, 40}, {2, 5}};
    }

    public void Tooth_full_type3()//рисуем зуб корнями вниз= статус вверху  ЗУБ С ЦЕЛОЙ центральной частью
    {
        figure0 = new int[][]{{20, 10, 4, 4, 10, 20, 18}, {20, 10, 20, 36, 46, 36, 28}, {0, 0}};//координаты икс,координаты игрек,приращения ,цвет
        figure1 = dummyfigure;
        figure2 = dummyfigure;
        figure3 = new int[][]{{20, 28, 36, 38, 36, 28, 20, 18}, {20, 18, 20, 28, 36, 38, 36, 28}, {0, 0}};
        figure4 = new int[][]{{36, 46, 52, 52, 46, 36, 38}, {20, 10, 20, 36, 46, 36, 28}, {0, 0}};
        figure5 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {36, 46, 52, 52, 46, 36, 38}, {0, 0}};
        figure6 = new int[][]{{20, 10, 20, 36, 46, 36, 28}, {20, 10, 4, 4, 10, 20, 18}, {0, 0}};
        figure7 = new int[][]{{2, 4, 8, 12, 16, 20, 16, 12, 16}, {4, 20, 32, 40, 44, 44, 36, 24, 4}, {2, 56}};
        figure8 = new int[][]{{50, 48, 44, 40, 36, 32, 36, 40, 36}, {4, 20, 32, 40, 44, 44, 36, 24, 4}, {2, 56}};
        figure9 = new int[][]{{18, 18, 20, 20, 24, 28, 32, 32, 34, 34}, {4, 20, 28, 32, 44, 44, 32, 28, 20, 4}, {2, 56}};
    }

    private void Tooth_onlyroots_type0() // корни вверх для коронки
    {
        figure0 = dummyfigure;
        figure1 = dummyfigure;
        figure2 = dummyfigure;
        figure3 = dummyfigure;
        figure4 = dummyfigure;
        figure5 = dummyfigure;
        figure6 = dummyfigure;
        figure7 = new int[][]{{2, 4, 8, 12, 16, 20, 16, 12, 16}, {40, 24, 12, 4, 0, 0, 8, 20, 40}, {2, 5}};
        figure8 = new int[][]{{50, 48, 44, 40, 36, 32, 36, 40, 36}, {40, 24, 12, 4, 0, 0, 8, 20, 40}, {2, 5}};
        figure9 = new int[][]{{18, 18, 20, 20, 24, 28, 32, 32, 34, 34}, {40, 24, 16, 12, 0, 0, 12, 16, 24, 40}, {2, 5}};
    }

    private void Tooth_onlyroots_type1() // корни внизу для коронки
    {
        figure0 = dummyfigure;
        figure1 = dummyfigure;
        figure2 = dummyfigure;
        figure3 = dummyfigure;
        figure4 = dummyfigure;
        figure5 = dummyfigure;
        figure6 = dummyfigure;
        figure7 = new int[][]{{2, 4, 8, 12, 16, 20, 16, 12, 16}, {4, 20, 32, 40, 44, 44, 36, 24, 4}, {2, 56}};
        figure8 = new int[][]{{50, 48, 44, 40, 36, 32, 36, 40, 36}, {4, 20, 32, 40, 44, 44, 36, 24, 4}, {2, 56}};
        figure9 = new int[][]{{18, 18, 20, 20, 24, 28, 32, 32, 34, 34}, {4, 20, 28, 32, 44, 44, 32, 28, 20, 4}, {2, 56}};
    }

    private void Tooth_Icon_default(Color c, boolean isAlien)
    {
        if (!forPopup) {
            Switch_roots();
            Draw_polygons();
            if (tooth_number < 16) {
                GraphicsReadySpec.setPaint(c);
                GraphicsReadySpec.fillOval(0, 48, 54, 54);
                GraphicsReadySpec.setPaint(Color.black);
                GraphicsReadySpec.drawOval(0, 48, 54, 54);
                if (isAlien) {
                    Font font = new Font("Tahoma", Font.BOLD, 12);
                    GraphicsReadySpec.setFont(font);
                    GraphicsReadySpec.setColor(Color.red);
                    GraphicsReadySpec.drawString("Чуж.", 15, 76);
                }
            } else {
                GraphicsReadySpec.setPaint(c);
                GraphicsReadySpec.fillOval(0, 0, 54, 54);
                GraphicsReadySpec.setPaint(Color.black);
                GraphicsReadySpec.drawOval(0, 0, 54, 54);
                if (isAlien) {
                    Font font = new Font("Tahoma", Font.BOLD, 12);
                    GraphicsReadySpec.setFont(font);
                    GraphicsReadySpec.setColor(Color.red);
                    GraphicsReadySpec.drawString("Чуж.", 15, 32);
                }
            }
        } else {
            GraphicsReadySpec.setPaint(c);
            GraphicsReadySpec.fillOval(0, 0, 54, 54);
            GraphicsReadySpec.setPaint(Color.black);
            GraphicsReadySpec.drawOval(0, 0, 54, 54);
            if (isAlien) {
                Font font = new Font("Tahoma", Font.BOLD, 12);
                GraphicsReadySpec.setFont(font);
                GraphicsReadySpec.setColor(Color.red);
                GraphicsReadySpec.drawString("Чуж.", 15, 32);
            }
        }
    }

    private void Tooth_Icon_type0()// статус зуба - отсутствует
    {
        GraphicsReadySpec.setPaint(Color.white);
        GraphicsReadySpec.fillOval(0, 20, 54, 54);
        GraphicsReadySpec.setPaint(Color.black);
        for (int i = 0; i < 18; i++) {
            GraphicsReadySpec.drawArc(0, 20, 54, 54, -10 + i * 20, 10);
        }
        Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 12);
        GraphicsReadySpec.setFont(font);
        GraphicsReadySpec.setColor(Color.red);
        GraphicsReadySpec.drawString("Зуб", 15, 52);
    }

    public void Tooth_Icon_type1()//статус зуба - удалён
    {
        Font font = new Font("Tahoma", Font.BOLD | Font.ITALIC, 12);
        GraphicsReadySpec.setFont(font);
        GraphicsReadySpec.setColor(Color.BLACK);
        GraphicsReadySpec.drawString("Удал.", 15, 44);
    }

    private void Tooth_Icon_type2()//статус зуба - форм десны
    {
        GraphicsReadySpec.setPaint(Color.MAGENTA);
        GraphicsReadySpec.fillOval(10, 30, 28, 28);
        GraphicsReadySpec.setPaint(Color.white);
        GraphicsReadySpec.fillOval(15, 35, 19, 19);
        GraphicsReadySpec.setPaint(Color.blue);
        GraphicsReadySpec.drawOval(15, 35, 19, 19);
        GraphicsReadySpec.drawLine(17, 37, 32, 52);
        GraphicsReadySpec.drawLine(32, 37, 17, 52);
    }

    private void Tooth_Icon_type3()//статус зуба - мост
    {
        GraphicsReadySpec.setPaint(Color.yellow);
        GraphicsReadySpec.fillRect(7, 12, 40, 50);
        GraphicsReadySpec.setPaint(Color.black);
        GraphicsReadySpec.drawRect(7, 12, 40, 50);

    }

    private void Tooth_Icon_type4()//статус зуба - искусственный
    {
        GraphicsReadySpec.setPaint(Color.white);
        Font font = new Font("Tahoma", Font.BOLD, 12);

        if (tooth_number < 16) {
            GraphicsReadySpec.fillOval(0, 0, 54, 54);
            GraphicsReadySpec.setColor(Color.black);
            GraphicsReadySpec.setFont(font);
            GraphicsReadySpec.drawOval(0, 0, 54, 54);
            GraphicsReadySpec.drawString("Иск", 15, 32);
        } else {
            GraphicsReadySpec.fillOval(0, 48, 54, 54);
            GraphicsReadySpec.setColor(Color.black);
            GraphicsReadySpec.setFont(font);
            GraphicsReadySpec.drawOval(0, 0, 54, 54);
            GraphicsReadySpec.drawString("Иск", 15, 76);
        }
    }

    public void Tooth_Icon_type5()//статус зуба - чужой мост
    {
        GraphicsReadySpec.setPaint(Color.yellow);
        GraphicsReadySpec.fillRect(7, 12, 40, 30);
        GraphicsReadySpec.setPaint(Color.black);
        GraphicsReadySpec.drawRect(7, 12, 40, 30);
        Font font = new Font("Tahoma", Font.BOLD, 12);
        GraphicsReadySpec.setFont(font);
        GraphicsReadySpec.setColor(Color.red);
        GraphicsReadySpec.drawString("Чуж.", 15, 52);
    }

    private void Tooth_Icon_type6()//статус зуба - Отсутсвует
    {
        Font font = new Font("Tahoma", Font.BOLD, 12);
        GraphicsReadySpec.setFont(font);
        GraphicsReadySpec.setColor(Color.black);
        GraphicsReadySpec.drawString("---> <---", 0, 52);
    }

    private void Tooth_Icon_type7()//статус зуба - Молочный
    {
        int i = this.tooth_number;
        if (i < 5) {
            Tooth_full_type0();
        }
        if (i > 4 & i < 11) {
            Tooth_full_type2();
        }
        if (i > 10 & i < 16) {
            Tooth_full_type0();
        }

        if (i > 15 & i < 21) {
            Tooth_full_type1();
        }
        if (i > 20 & i < 27) {
            Tooth_full_type3();
        }
        if (i > 26) {
            Tooth_full_type1();
        }
    }

    public void Switch_roots()
    {
        switch (root_count) {
            case 1:
                if (center_splited) {
                    figures = new int[][][]{figure0, figure1, figure2, figure3, figure4, figure5, figure6, dummyfigure, figure8, dummyfigure};
                } else {
                    figures = new int[][][]{figure0, figure1, figure2, figure3, figure4, figure5, figure6, dummyfigure, figure9, dummyfigure};
                }
                break;

            case 2:
                if (center_splited) {
                    figures = new int[][][]{figure0, figure1, figure2, figure3, figure4, figure5, figure6, figure7, figure8, dummyfigure};
                } else {
                    figures = new int[][][]{figure0, figure1, figure2, figure3, figure4, figure5, figure6, figure7, figure8, dummyfigure};
                }
                break;

            case 3:
                if (center_splited) {
                    figures = new int[][][]{figure0, figure1, figure2, figure3, figure4, figure5, figure6, figure7, figure8, figure9};
                } else {
                    figures = new int[][][]{figure0, figure1, figure2, figure3, figure4, figure5, figure6, figure7, figure8, figure9};
                }
                break;
        }
        PolygonsReady = new Polygon[figures.length];//Массив полиговоп длинной массива фигур(координат)
        ColorsReady = new Color[figures.length];//Массив цветов пока не используется
    }

    private void Rooth_Icon_type0(int figure) //статус корня - здоровый
    {

    }

    private void Rooth_Icon_type1(int figure) //статус корня - удалён
    {

        GraphicsReadySpec.setPaint(Color.red);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.drawLine(0, 20, 20, 35);
                    GraphicsReadySpec.drawLine(20, 20, 0, 35);
                    break;
                case 8:
                    GraphicsReadySpec.drawLine(35, 20, 54, 35);
                    GraphicsReadySpec.drawLine(54, 20, 35, 35);
                    break;
                case 9:
                    GraphicsReadySpec.drawLine(18, 20, 38, 35);
                    GraphicsReadySpec.drawLine(38, 20, 18, 35);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.drawLine(0, 70, 20, 85);
                    GraphicsReadySpec.drawLine(20, 70, 0, 85);
                    break;
                case 8:
                    GraphicsReadySpec.drawLine(35, 70, 54, 85);
                    GraphicsReadySpec.drawLine(54, 70, 35, 85);
                    break;
                case 9:
                    GraphicsReadySpec.drawLine(18, 70, 38, 85);
                    GraphicsReadySpec.drawLine(38, 70, 18, 85);
                    break;
            }
        }
    }

    private void Rooth_Icon_type2(int figure) //статус корня - частичное пломбирование
    {
        GraphicsReadySpec.setColor(Color.blue);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    Rootfigure = new int[][]{{7, 9, 10, 12, 14, 17, 15, 13, 17}, {45, 31, 32, 17, 13, 13, 21, 27, 45}, {0, 0}};
                    Polygon p = new Polygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.fillPolygon(p);
                    break;
                case 8:
                    Rootfigure = new int[][]{{51, 49, 48, 46, 44, 41, 43, 45, 41}, {45, 31, 32, 17, 13, 13, 21, 27, 45}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
                case 9:
                    Rootfigure = new int[][]{{23, 23, 25, 25, 27, 31, 33, 33, 35, 35}, {45, 34, 26, 22, 15, 15, 22, 26, 34, 45}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    Rootfigure = new int[][]{{7, 9, 10, 12, 14, 17, 15, 13, 17}, {60, 74, 83, 88, 92, 92, 73, 76, 60}, {0, 0}};
                    Polygon p = new Polygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.fillPolygon(p);
                    break;
                case 8:
                    Rootfigure = new int[][]{{51, 49, 48, 46, 44, 41, 43, 45, 41}, {60, 74, 83, 88, 92, 92, 73, 76, 60}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
                case 9:
                    Rootfigure = new int[][]{{23, 23, 25, 25, 27, 31, 33, 33, 35, 35}, {60, 71, 79, 83, 90, 90, 83, 79, 71, 60}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
            }
        }
    }

    private void Rooth_Icon_type3(int figure_id)//статус корня - кариес
    {
        if (tooth_number < 16) {
            Tooth_onlyroots_type0();
        } else {
            Tooth_onlyroots_type1();
        }
        Switch_roots();
        int y = 0;
        for (int[][] figure : figures)//Для каждой фигуры в из массива фигур
        {

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
            PolygonsReady[y] = new Polygon(xv, yv, polLength);//В массив полигонов пишем новый полигон уже с фактическими координатами
            y++;
        }
        GraphicsReadySpec.setPaint(Color.red);
        GraphicsReadySpec.fillPolygon(PolygonsReady[figure_id]);
        GraphicsReadySpec.setPaint(Color.black);
        GraphicsReadySpec.drawPolygon(PolygonsReady[figure_id]);
        //GraphicsReady.get(i).setStroke(dashed);

    }

    private void Rooth_Icon_type4(int figure)//статус корня - штифт 
    {
        GraphicsReadySpec.setPaint(Color.gray);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    Rootfigure = new int[][]{{6, 6, 11, 16, 16}, {45, 35, 30, 35, 45}, {0, 0}};
                    Polygon p = new Polygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.fillPolygon(p);
                    break;
                case 8:
                    Rootfigure = new int[][]{{50, 50, 45, 40, 40}, {45, 35, 30, 35, 45}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
                case 9:
                    Rootfigure = new int[][]{{23, 23, 28, 33, 33}, {45, 35, 30, 35, 45}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    Rootfigure = new int[][]{{6, 6, 11, 16, 16}, {60, 70, 75, 70, 60}, {0, 0}};
                    Polygon p = new Polygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.fillPolygon(p);
                    break;
                case 8:
                    Rootfigure = new int[][]{{50, 50, 45, 40, 40}, {60, 70, 75, 70, 60}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
                case 9:
                    Rootfigure = new int[][]{{23, 23, 28, 33, 33}, {60, 70, 75, 70, 60}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    break;
            }
        }

    }

    private void Rooth_Icon_type5(int figure)//статус корня- Инородное тео
    {

        GraphicsReadySpec.setPaint(Color.black);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.fillOval(5, 35, 9, 9);
                    break;
                case 8:
                    GraphicsReadySpec.fillOval(45, 35, 9, 9);
                    break;
                case 9:
                    GraphicsReadySpec.fillOval(22, 35, 9, 9);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.fillOval(5, 63, 9, 9);
                    break;
                case 8:
                    GraphicsReadySpec.fillOval(45, 63, 9, 9);
                    break;
                case 9:
                    GraphicsReadySpec.fillOval(22, 63, 9, 9);
                    break;

            }
        }
    }

    private void Rooth_Icon_type6(int figure)//статус корня -перфорация
    {

        GraphicsReadySpec.setPaint(Color.cyan);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    Rootfigure = new int[][]{{5, 14, 14, 5}, {32, 25, 29, 37}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.setPaint(Color.black);
                    GraphicsReadySpec.drawLine(5, 32, 14, 24);
                    GraphicsReadySpec.drawLine(5, 37, 14, 29);
                    break;
                case 8:
                    Rootfigure = new int[][]{{51, 41, 41, 51}, {32, 25, 29, 37}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.setPaint(Color.black);
                    GraphicsReadySpec.drawLine(41, 24, 51, 32);
                    GraphicsReadySpec.drawLine(41, 29, 51, 37);
                    break;
                case 9:
                    Rootfigure = new int[][]{{22, 34, 34, 22}, {26, 26, 31, 31}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.setPaint(Color.black);
                    GraphicsReadySpec.drawLine(22, 26, 34, 26);
                    GraphicsReadySpec.drawLine(22, 31, 34, 31);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    Rootfigure = new int[][]{{5, 14, 14, 5}, {66, 74, 79, 71}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.setPaint(Color.black);
                    GraphicsReadySpec.drawLine(5, 66, 14, 74);
                    GraphicsReadySpec.drawLine(5, 71, 14, 79);
                    break;
                case 8:
                    Rootfigure = new int[][]{{51, 41, 41, 51}, {71, 79, 74, 66}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.setPaint(Color.black);
                    GraphicsReadySpec.drawLine(41, 74, 51, 66);
                    GraphicsReadySpec.drawLine(41, 79, 51, 71);
                    break;
                case 9:
                    Rootfigure = new int[][]{{22, 34, 34, 22}, {76, 76, 81, 81}, {0, 0}};
                    GraphicsReadySpec.fillPolygon(Rootfigure[0], Rootfigure[1], Rootfigure[0].length);
                    GraphicsReadySpec.setPaint(Color.black);
                    GraphicsReadySpec.drawLine(22, 76, 34, 76);
                    GraphicsReadySpec.drawLine(22, 81, 34, 81);
                    break;

            }
        }
    }

    private void Rooth_Icon_type7(int figure_id)//статус корня -пломбировка
    {
       if (tooth_number < 16) {
            Tooth_onlyroots_type0();
        } else {
            Tooth_onlyroots_type1();
        }
        Switch_roots();
        int y = 0;
        for (int[][] figure : figures)//Для каждой фигуры в из массива фигур
        {

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
            PolygonsReady[y] = new Polygon(xv, yv, polLength);//В массив полигонов пишем новый полигон уже с фактическими координатами
            y++;
        }
        GraphicsReadySpec.setPaint(Color.blue);
        GraphicsReadySpec.fillPolygon(PolygonsReady[figure_id]);
        GraphicsReadySpec.setPaint(Color.black);
        GraphicsReadySpec.drawPolygon(PolygonsReady[figure_id]);
    }

    private void Rooth_Icon_type8(int figure)//статус корня -изменения
    {

        GraphicsReadySpec.setPaint(Color.gray);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.fillOval(5, 35, 9, 9);
                    break;
                case 8:
                    GraphicsReadySpec.fillOval(45, 35, 9, 9);
                    break;
                case 9:
                    GraphicsReadySpec.fillOval(22, 35, 9, 9);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.fillOval(5, 63, 9, 9);
                    break;
                case 8:
                    GraphicsReadySpec.fillOval(45, 63, 9, 9);
                    break;
                case 9:
                    GraphicsReadySpec.fillOval(22, 63, 9, 9);
                    break;

            }
        }
    }

    private void Rooth_Icon_type9(int figure)//статус корня -вставка 
    {

        GraphicsReadySpec.setPaint(Color.green);
        if (tooth_number < 16) {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.fillRect(8, 30, 5, 15);
                    break;
                case 8:
                    GraphicsReadySpec.fillRect(43, 30, 5, 15);
                    break;
                case 9:
                    GraphicsReadySpec.fillRect(25, 30, 5, 15);
                    break;
            }
        } else {
            switch (figure) {
                case 7:
                    GraphicsReadySpec.fillRect(8, 62, 5, 15);
                    break;
                case 8:
                    GraphicsReadySpec.fillRect(43, 62, 5, 15);
                    break;
                case 9:
                    GraphicsReadySpec.fillRect(25, 62, 5, 15);
                    break;
            }
        }
    }

    private void DrawSanation()
    {
        GraphicsReadySpec.setPaint(Color.black);
        GraphicsReadySpec.drawOval(45, 92, 10, 10);
        switch (sanated) {
            case 0:
                GraphicsReadySpec.setPaint(Color.red);
                break;
            case 1:
                GraphicsReadySpec.setPaint(Color.green);
                break;
            case 2:
                GraphicsReadySpec.setPaint(Color.white);
                break;
        }

        GraphicsReadySpec.fillOval(45, 92, 10, 10);
    }

    public void Draw_polygons()
    {
        int y = 0;
        for (int[][] figure : figures)//Для каждой фигуры в из массива фигур
        {

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

            PolygonsReady[y] = new Polygon(xv, yv, polLength);//В массив полигонов пишем новый полигон уже с фактическими координатами

            ColorsReady[y] = new Color(zubR.getPoseshRabZub().get(y).getColor()[0], zubR.getPoseshRabZub().get(y).getColor()[1], zubR.getPoseshRabZub().get(y).getColor()[2]);//Грани зуба привязываем цвет

            y++;
        }
        for (int i = 0; i < figures.length; i++) //Устанавливаем цвет грани зуба
        {

            GraphicsReadySpec.setPaint(ColorsReady[i]);
            GraphicsReadySpec.fillPolygon(PolygonsReady[i]);
            GraphicsReadySpec.setPaint(Color.black);
            GraphicsReadySpec.drawPolygon(PolygonsReady[i]);
            //GraphicsReady.get(i).setStroke(dashed);
        }
        for (int i = 6; i < 12; i++) {
            if (zubR.getPoseshRabZub().get(i).getIdRab() != -1) {

                switch (zubR.getPoseshRabZub().get(i).getIdRab()) {
                    case 42:
                        Rooth_Icon_type0(i);
                        break;
                    case 45:
                        Rooth_Icon_type1(i);
                        break;
                    case 46:
                        Rooth_Icon_type2(i);
                        break;
                    case 47:
                        Rooth_Icon_type3(i);
                        break;
                    case 48:
                        Rooth_Icon_type4(i);
                        break;
                    case 49:
                        Rooth_Icon_type5(i);
                        break;
                    case 50:
                        Rooth_Icon_type6(i);
                        break;
                    case 51:
                        Rooth_Icon_type7(i);
                        break;
                    case 52:
                        Rooth_Icon_type8(i);
                        break;
                    case 53:
                        Rooth_Icon_type9(i);
                        break;
                }
            }
        }
    }

    public void Switch_type()
    {
        switch (tooth_type) {
            case 0: //рисуем зуб корнями вверх - статус внизу ЗУБ С 2 центральными частями 
                Tooth_full_type0();
                break;
            case 1://рисуем зуб корнями вниз= статус вверху  ЗУБ С 2 центральными частями 
                Tooth_full_type1();
                break;
            case 2: //рисуем зуб корнями вверх - статус внизу ЗУБ С ЦЕЛОЙ центральной частью 
                Tooth_full_type2();
                break;
            case 3://рисуем зуб корнями вниз= статус вверху  ЗУБ С ЦЕЛОЙ центральной частью
                Tooth_full_type3();
                break;
            case 74://рисуем зуб корнями вниз= статус вверху  ЗУБ С ЦЕЛОЙ центральной частью
                Tooth_Icon_type7();
                break;
            default:
                isSpecial = true;
                break;
        }
        if (tooth_type > 53 & tooth_type != 74) {
            isSpecial = true;

            if (tooth_type != 61) {
                if (this.tooth_number < 16) {
                    Tooth_onlyroots_type0();
                } else {
                    Tooth_onlyroots_type1();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {

        //Выполняеся только после InitComponent
        super.paintComponent(g);//Стандартная запись перезачи сформированой графики
        GraphicsReadySpec = (Graphics2D) g;
        if (isScaled) {

            AffineTransform at = GraphicsReadySpec.getTransform();
            at.scale(1.5, 1.5);
            GraphicsReadySpec.setTransform(at);
            System.err.println("OLOO transformed" + at.toString());
        }

        Switch_type();
        if (!isSpecial) {
            //СЛОЖЕНИЕ ГРАНЕЙ ЗУБА В ОДИН МАССИВ
            Switch_roots();
            Draw_polygons();
        } else { // здесь нужно сделать switch по тому, что мы рисуем исходя из номера

            switch (tooth_type) {
                case 29:
                    Tooth_Icon_type0();// статус зуба - отсутствует
                    break;
                case 54:
                    Tooth_Icon_type1();//статус зуба - удалён
                    break;
                case 55:
                    Tooth_Icon_default(Color.blue, false);//статус зуба - имплантат
                    break;
                case 56:
                    Tooth_Icon_type2();//статус зуба - форм десны
                    break;
                case 57:
                    Tooth_Icon_default(Color.yellow, false);//статус зуба - коронка
                    break;
                case 58:
                    Tooth_Icon_default(new Color(0, 130, 0), false);//статус зуба - культ. вкладка.
                    break;
                case 59:
                    Tooth_Icon_default(Color.green, false);//статус зуба - бюгель протез.
                    break;
                case 60:
                    Tooth_Icon_type3();//статус зуба - мост
                    break;
                case 61:
                    Tooth_Icon_type4();//статус зуба - искусственный
                    break;
                case 62:
                    Tooth_Icon_default(Color.yellow, true);//статус зуба - чужая коронка
                    break;
                case 63:
                    Tooth_Icon_type5();//статус зуба - чужой мост
                    break;
                case 64:
                    Tooth_Icon_default(Color.green, true);//статус зуба - чужой бюгель.
                    break;
                case 65:
                    Tooth_Icon_default(new Color(0, 0, 200), false);//статус зуба - Полный съём
                    break;
                case 66:
                    Tooth_Icon_default(Color.CYAN, false);//статус зуба - Частичный съём
                    break;
                case 67:
                    Tooth_Icon_default(new Color(0, 0, 200), true);//статус зуба - Чужой Полный съём
                    break;
                case 68:
                    Tooth_Icon_default(Color.CYAN, true);//статус зуба - Чужой частичный съём
                    break;
                case 75:
                    Tooth_Icon_type6();//статус зуба - Отсутсвует
            }

        }
        DrawSanation();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(211, 207, 203));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setMaximumSize(new java.awt.Dimension(50, 60));
        setMinimumSize(new java.awt.Dimension(50, 60));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(50, 60));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents
    protected void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        this.setForeground(Color.BLACK);//Не работает т.к. не рисется обводка
        //graph.drawPolygon(p);//--границы        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
