package stomatology;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import stomatology.spravochniki.SprPolzEdit;

public class ServerDb implements Constatnts {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    protected Connection conn;
    private Statement st;
    private String sql;
    private ResultSet rs;
    public ResultSet rsClientFind;
    public String err = "";
    private Client client;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public ServerDb(String url, String user, String password) {
        if (connect(url, user, password) != Constatnts.READY) {
            JOptionPane.showMessageDialog(null, "Не удается устрановить соединение с базой", "Внимание", 0);
        }
        System.out.println("SERVERDb: constructor");
    }

    public int connect(String url, String user, String password) {

        try {
            conn = DriverManager.getConnection(url, user, password);//Подключаемся к баде данных
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Делаем многонаправленный ResultSet
            System.out.println("SUCCESSFULLY connected with database!");
        } catch (SQLException ex) {
            System.out.println("ServerDb:getData():Ошибка подключения или создание Statement");
            return Constatnts.ERROR;
        }
        return Constatnts.READY;
    }

    @Override
    public int connect() {
        System.out.println("SERVERDb: connect");
        String[] args = {""};
        if (this.connect(args) == Constatnts.ERROR) {
            return Constatnts.ERROR;
        }
        return Constatnts.READY;
    }

    @Override
    public int connect(String[] args) {
        System.out.println("SERVERDb: connect");
        String url = args[0];//--Разбираем массив параметров
        String user = args[1];
        String password = args[2];
        try {
            conn = DriverManager.getConnection(url, user, password);//Подключаемся к баде данных
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//Делаем многонаправленный ResultSet
            System.out.println("SUCCESSFULLY connected with database!");
        } catch (SQLException ex1) {
            System.out.println("ServerDb:getData():Ошибка подключения или создание Statement");
            return Constatnts.ERROR;
        }
        System.out.println("ok good connection with DATABASE");
        return Constatnts.READY;
    }

//    @Override
    public Vrach getVrachData(String vrName) {
        Vrach vrach = null;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            sqlQ = "SELECT id_vrach as id, ISNULL(fam_vrach,'') + ' ' + ISNULL(imya_vrach,'') + ' ' + ISNULL(otch_vrach,'') as fio, sql_login as sqlLogin, id_role as role FROM  D_vrach WHERE ISNULL(fam_vrach,'') + ' ' + ISNULL(imya_vrach,'') + ' ' + ISNULL(otch_vrach,'') = '" + vrName + "'";
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                vrach = new Vrach(rsPos.getInt(1), rsPos.getString(3), rsPos.getString(2), rsPos.getInt(4));
            }
            rsPos.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getData():Ошибка подключения или создание Statement");
        }
        return vrach;
    }

    public ArrayList<Client> getClientData(int id, String fam, String imya, String otch) {
        ArrayList<Client> al = new ArrayList<>();
        Client client = null;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        String sqlQR;
        int idVrach = Find.vr.getId();
        try {
            stat = conn.createStatement();
            if (id != 0) {
                sqlQ = "SELECT [id_klient],[id_vrach],[fam_klient],[imya_klient],[otch_klient],[tel_klient],FORMAT([dt_roj_klient],'d', 'de-de') FROM [dbo].[D_klient] WHERE id_klient = " + id + "";
                sqlQR = "SELECT [id_klient],[dbo].[getFIOVrach]([id_vrach]),[fam_klient],[imya_klient],[otch_klient],[tel_klient],FORMAT([dt_roj_klient],'d', 'de-de') FROM [dbo].[D_klient] WHERE id_klient = " + id + "";
            } else {
                sqlQ = "SELECT Код,Ответственный,Фамилия,Имя,Отчество,Телефон,[Дата рождения] \n"
                        + "FROM\n"
                        + "(\n"
                        + "SELECT [id_klient] as Код,[id_vrach] as Ответственный,[fam_klient] as Фамилия,[imya_klient] as Имя,[otch_klient] as Отчество,[tel_klient] as Телефон,FORMAT([dt_roj_klient],'d', 'de-de') as [Дата рождения] FROM [dbo].[D_klient] WHERE id_vrach = " + idVrach + "\n"
                        + "UNION ALL\n"
                        + "SELECT        D_klient.id_klient AS Код, D_klient.id_vrach AS Ответственный, D_klient.fam_klient AS Фамилия, D_klient.imya_klient AS Имя, D_klient.otch_klient AS Отчество, D_klient.tel_klient AS Телефон, \n"
                        + "                         FORMAT(D_klient.dt_roj_klient, 'd', 'de-de') AS [Дата рождения]\n"
                        + "FROM            D_klient INNER JOIN\n"
                        + "                         D_Perenaprav ON D_klient.id_klient = D_Perenaprav.id_klient\n"
                        + "WHERE        (D_Perenaprav.id_vrach_poluch = " + idVrach + "))T1";

                sqlQR = "SELECT Код,Ответственный,Фамилия,Имя,Отчество,Телефон,[Дата рождения] \n"
                        + "FROM\n"
                        + "(\n"
                        + "SELECT [id_klient] as Код,[dbo].[getFIOVrach]([id_vrach]) as Ответственный,[fam_klient] as Фамилия,[imya_klient] as Имя,[otch_klient] as Отчество,[tel_klient] as Телефон,FORMAT([dt_roj_klient],'d', 'de-de') as [Дата рождения] FROM [dbo].[D_klient] WHERE id_vrach = " + idVrach + "\n"
                        + "UNION ALL\n"
                        + "SELECT        D_klient.id_klient AS Код, [dbo].[getFIOVrach]([id_vrach]) AS Ответственный, D_klient.fam_klient AS Фамилия, D_klient.imya_klient AS Имя, D_klient.otch_klient AS Отчество, D_klient.tel_klient AS Телефон, \n"
                        + "                         FORMAT(D_klient.dt_roj_klient, 'd', 'de-de') AS [Дата рождения]\n"
                        + "FROM            D_klient INNER JOIN\n"
                        + "                         D_Perenaprav ON D_klient.id_klient = D_Perenaprav.id_klient\n"
                        + "WHERE        (D_Perenaprav.id_vrach_poluch = " + idVrach + "))T1";

            }
            if (!fam.equals("default") || !imya.equals("default") || !otch.equals("default")) {
                sqlQ = "SELECT Код,Ответственный,Фамилия,Имя,Отчество,Телефон,[Дата рождения] \n"
                        + "FROM\n"
                        + "(\n"
                        + "SELECT [id_klient] as Код,[id_vrach] as Ответственный,[fam_klient] as Фамилия,[imya_klient] as Имя,[otch_klient] as Отчество,[tel_klient] as Телефон,FORMAT([dt_roj_klient],'d', 'de-de') as [Дата рождения] FROM [dbo].[D_klient] WHERE id_vrach = " + idVrach + "\n"
                        + "UNION ALL\n"
                        + "SELECT        D_klient.id_klient AS Код, D_klient.id_vrach AS Ответственный, D_klient.fam_klient AS Фамилия, D_klient.imya_klient AS Имя, D_klient.otch_klient AS Отчество, D_klient.tel_klient AS Телефон, \n"
                        + "FORMAT(D_klient.dt_roj_klient, 'd', 'de-de') AS [Дата рождения]\n"
                        + "FROM            D_klient INNER JOIN\n"
                        + "D_Perenaprav ON D_klient.id_klient = D_Perenaprav.id_klient\n"
                        + "WHERE (D_Perenaprav.id_vrach_poluch = " + idVrach + "))T1";

                sqlQR = "SELECT Код,Ответственный,Фамилия,Имя,Отчество,Телефон,[Дата рождения] \n"
                        + "FROM\n"
                        + "(\n"
                        + "SELECT [id_klient] as Код,[dbo].[getFIOVrach]([id_vrach]) as Ответственный,[fam_klient] as Фамилия,[imya_klient] as Имя,[otch_klient] as Отчество,[tel_klient] as Телефон,FORMAT([dt_roj_klient],'d', 'de-de') as [Дата рождения] FROM [dbo].[D_klient] WHERE id_vrach = " + idVrach + "\n"
                        + "UNION ALL\n"
                        + "SELECT        D_klient.id_klient AS Код, [dbo].[getFIOVrach]([id_vrach]) AS Ответственный, D_klient.fam_klient AS Фамилия, D_klient.imya_klient AS Имя, D_klient.otch_klient AS Отчество, D_klient.tel_klient AS Телефон, \n"
                        + "FORMAT(D_klient.dt_roj_klient, 'd', 'de-de') AS [Дата рождения]\n"
                        + "FROM            D_klient INNER JOIN\n"
                        + "D_Perenaprav ON D_klient.id_klient = D_Perenaprav.id_klient\n"
                        + "WHERE (D_Perenaprav.id_vrach_poluch = " + idVrach + "))T1";

                if (!fam.equals("default")) {
                    sqlQ = sqlQ + " WHERE [Фамилия] like '" + fam + "'";
                    sqlQR = sqlQR + " WHERE [Фамилия] like '" + fam + "'";
                }

                if (!fam.equals("default") & !imya.equals("default")) {
                    sqlQ = sqlQ + " AND [Имя] like '" + imya + "'";
                    sqlQR = sqlQR + " AND [Имя] like '" + imya + "'";
                } else if (fam.equals("default") & !imya.equals("default")) {
                    sqlQ = sqlQ + " WHERE [Имя] like '" + imya + "'";
                    sqlQR = sqlQR + " AND [Имя] like '" + imya + "'";
                }

            }
            sqlQ = sqlQ + " GROUP BY Код, Ответственный, Фамилия, Имя, Отчество, Телефон, [Дата рождения]";
            sqlQR = sqlQR + " GROUP BY Код, Ответственный, Фамилия, Имя, Отчество, Телефон, [Дата рождения]";
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                client = new Client(rsPos.getInt(1), rsPos.getInt(2), rsPos.getString(3), rsPos.getString(4), rsPos.getString(5), rsPos.getString(6), rsPos.getString(7));
                al.add(client);
            }
            this.rsClientFind = st.executeQuery(sqlQR);
            rsPos.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getData():Ошибка подключения или создание Statement");
        }
        return al;
    }

    public Client getPervichObsledData(Client cl) {
        int id_client = cl.getId_klient();
        ArrayList<PervichObsled> pervichObsled = new ArrayList<>();
        PervichObsled pervich;
        ResultSet rsCl;
        Statement stat;
        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT [id_pervich_obsled],[id_klient],[id_vrach],FORMAT([dt_pervich_obsled],'d', 'de-de'), id_filial, fl_zapret FROM [dbo].[D_pervich_obsled] WHERE [id_klient] = " + id_client + " ORDER BY id_pervich_obsled";
            rsCl = stat.executeQuery(sql);
            while (rsCl.next()) {
                pervich = new PervichObsled(rsCl.getInt(1), rsCl.getInt(2), rsCl.getInt(3), rsCl.getString(4), rsCl.getInt(5), rsCl.getInt(6));
                pervich.setPervichObsledStr(getPervichObsledStrData(pervich));
                pervichObsled.add(pervich);
            }
            rsCl.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichObsledData:Ошибка подключения или создание Statement");
        }
        cl.setPervichObsled(pervichObsled);
        return cl;
    }

    public ArrayList<PervichObsledStr> getPervichObsledStrData(PervichObsled pervich) {
        int id_pervich_obsled = pervich.getId_pervich_obsled();
        ArrayList<PervichObsledStr> pervichObsledStr = new ArrayList<>();
        PervichObsledStr pervichStr;
        for (int z = 0; z < 32; z++) {
            pervichStr = new PervichObsledStr(id_pervich_obsled, z);
            pervichStr.setStatus(getPervichObsledStrStatusData(pervichStr));
            //pervichStr.setStatGranColor(getPervichObsledGraniStatus(pervichStr));
            pervichStr.setStatGranColor(getPervichObsledGraniStatus(pervichStr.getId_pervich_obsled(), z));
            pervichStr.setStatDesna(getPervichObsledDesnaStatus(pervichStr));
            pervichObsledStr.add(pervichStr);
        };
        return pervichObsledStr;
    }

    public ArrayList<PervichObsledGraniStatus> getPervichObsledGraniStatus(int idPerObsled, int z) {
        int idPervichObsled = idPerObsled;//pervichStr.getId_pervich_obsled();
        int idZub = z;//pervichStr.getId_zub();
        ArrayList<PervichObsledGraniStatus> pervichObsledGraniStatus = new ArrayList<>();
        PervichObsledGraniStatus pervichObsledGraniStatusObj;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;

        //Заполним -1 все грани зуба
        for (int gr = 0; gr < 13; gr++) {
            Integer[] color = {255, 255, 255};
            pervichObsledGraniStatusObj = new PervichObsledGraniStatus(-1, gr, "", color);
            pervichObsledGraniStatus.add(pervichObsledGraniStatusObj);
        }

        try {
            stat = conn.createStatement();
            sqlQ = "SELECT id_pervich, fl_status, id_zona_zub, prim FROM D_pervich_obsled_str WHERE (id_pervich_obsled = " + idPervichObsled + ") AND (id_zub = " + idZub + ") AND (id_zona_zub <> -1)";
            rsPos = stat.executeQuery(sqlQ);
            //System.out.println("kjs;dlkfgjsd;lkfgjkdlsf" + sqlQ);   
            while (rsPos.next()) {
                for (PervichObsledGraniStatus grStat : pervichObsledGraniStatus) {
                    if (grStat.getIdZonaZub() == rsPos.getInt(3)) {
                        //System.out.println("Зана зуба !!!!  = " +  rsPos.getInt(3));
                        grStat.setFlStatus(rsPos.getBoolean(2));

                        grStat.setColor(getColorPervich(rsPos.getInt(1)));

                        grStat.setPrim(rsPos.getString(4));
                        grStat.setIdPervich(rsPos.getInt(1));
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichObsledGraniStatus():Ошибка подключения или создание Statement");
        }
        return pervichObsledGraniStatus;
    }

    public ArrayList<PervichObsledZubStatus> getPervichObsledStrStatusData(PervichObsledStr pervichStr) {
        int idPervichObsled = pervichStr.getId_pervich_obsled();
        int sprPervich = 0;
        ArrayList<PervichObsledZubStatus> pervichObsledStrStatus = new ArrayList<>();
        PervichObsledZubStatus pervichObsledZubStatusObj;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        ArrayList<SprPervich> sprPervichData = getSprPervichData(4);
        sprPervich = sprPervichData.size();
        for (int r = 0; r < sprPervich; r++) {
            pervichObsledZubStatusObj = new PervichObsledZubStatus(r, false);
            pervichObsledStrStatus.add(pervichObsledZubStatusObj);
        };
        try {
            stat = conn.createStatement();
            sqlQ = "SELECT id_zub, id_pervich, fl_status FROM D_pervich_obsled_str WHERE id_zub = " + pervichStr.getId_zub() + " AND (id_pervich_obsled = " + idPervichObsled + ")";
            //System.out.println(sql);
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                int idPer = rsPos.getInt(2);
                for (int i = 0; i < sprPervich; i++) {
                    if (pervichObsledStrStatus.get(i).getIdSprPervich() == idPer) {
                        pervichObsledStrStatus.get(i).setStatusBoolean(true);
                    }
                }
            }
            rsPos.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichObsledStrStatusData():Ошибка подключения или создание Statement");
        }
        return pervichObsledStrStatus;
    }

    public ArrayList<PervichObsledDesnaStatus> getPervichObsledDesnaStatus(PervichObsledStr pervichStr) {
        int idPervichObsled = pervichStr.getId_pervich_obsled();
        int sprPervich = 0;
        ArrayList<PervichObsledDesnaStatus> pervichObsledDesnaStatus = new ArrayList<>();
        PervichObsledDesnaStatus PervichObsledDesnaStatusObj;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        ArrayList<SprPervich> sprPervichData = getSprPervichData(5);
        sprPervich = sprPervichData.size();
        for (int r = 0; r < sprPervich; r++) {
            PervichObsledDesnaStatusObj = new PervichObsledDesnaStatus(r, false);
            pervichObsledDesnaStatus.add(PervichObsledDesnaStatusObj);
        };
        try {
            stat = conn.createStatement();
            sqlQ = "SELECT id_zub, id_pervich, fl_status FROM D_pervich_obsled_str WHERE id_zub = " + pervichStr.getId_zub() + " AND (id_pervich_obsled = " + idPervichObsled + ")";
            //System.out.println(sql);
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                int idPer = rsPos.getInt(2);
                for (int i = 0; i < sprPervich; i++) {
                    if (pervichObsledDesnaStatus.get(i).getIdSprPervich() == idPer) {
                        pervichObsledDesnaStatus.get(i).setStatusBoolean(true);
                    }
                }
            }
            rsPos.close();
            stat.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichObsledDesnaStatus():Ошибка подключения или создание Statement");
        }
        return pervichObsledDesnaStatus;
    }

    public int setClient(Client cl) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            sqlQ = "INSERT INTO [dbo].[D_klient] ([fam_klient],[imya_klient],[otch_klient],[tel_klient],[id_vrach],[dt_roj_klient]) "
                    + "VALUES ('" + cl.getFam_klient() + "','" + cl.getImya_klient() + "','" + cl.getOtch_klient() + "','" + cl.getTel_klient() + "','" + cl.getId_vrach() + "','" + cl.getDt_roj_klient() + "')";

            rezult = stat.executeUpdate(sqlQ);
            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setClient():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setClient():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public int setClientInfo(Client cl) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        ResultSet rsSQL;
        int idPervichNew = 0;
        int idPoseshNew = 0;
        try {
            stat = conn.createStatement();

            for (PervichObsled pervich : cl.getPervichObsled()) {

                if (getPervichInDB(pervich.getId_pervich_obsled()) != 1) {
                    sqlQ = "INSERT INTO [dbo].[D_pervich_obsled] ([dt_pervich_obsled],[id_vrach],[id_klient],[id_filial],[fl_zapret],[prim]) "
                            + "VALUES ('" + pervich.getDt_pervich_obsled() + "'," + pervich.getId_vrach() + "," + cl.getId_klient() + "," + pervich.getIdFilial() + ",'true','" + pervich.getPrim() + "')";
                    rezult = stat.executeUpdate(sqlQ);
                    sql = "SELECT MAX(id_pervich_obsled) FROM D_pervich_obsled WHERE id_klient = " + cl.getId_klient() + " AND id_vrach = " + pervich.getId_vrach() + "";
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        idPervichNew = rs.getInt(1);
                        pervich.setId_pervich_obsled(idPervichNew);
                    }
                } else {
                    if (getPervichInDB(pervich.getId_pervich_obsled()) != 0 & getPervichStrInDB(pervich.getId_pervich_obsled()) == 0) {
                        idPervichNew = pervich.getId_pervich_obsled();
                        sqlQ = "UPDATE [dbo].[D_pervich_obsled] SET [fl_zapret] = 1 WHERE id_pervich_obsled = " + idPervichNew + "";
                        rezult = stat.executeUpdate(sqlQ);
                    } else {
                        continue;
                    }
                }
                //Обработка статусов(которые в Lable)
                for (PervichObsledStr pervischStr : cl.getPervichObsled().get(cl.getPervichObsled().size() - 1).getPervichObsledStr()) {
                    //Перебераем статусы(которые в Lable) на весь зуб
                    for (PervichObsledZubStatus perObZubStat : pervischStr.getStatus()) {
                        if (perObZubStat.isStatusBoolean()) {
                            sqlQ = "INSERT INTO [dbo].[D_pervich_obsled_str] ([id_pervich_obsled],[id_zub],[id_pervich],[fl_status],[id_zona_zub],[prim]) "
                                    + "VALUES (" + idPervichNew + "," + pervischStr.getId_zub() + "," + perObZubStat.getIdSprPervich() + ",0,-1,'" + perObZubStat.getPrim() + "')";
                            //System.out.println("SQL(setClientInfo) = " + sqlQ);
                            rezult = stat.executeUpdate(sqlQ);
                        }

                    }
                }
                //Обработка статусы на грани зуба()
                for (PervichObsledStr pervischStr : cl.getPervichObsled().get(cl.getPervichObsled().size() - 1).getPervichObsledStr()) {

                    for (PervichObsledGraniStatus perObGraniZubStat : pervischStr.getStatGranColor()) {
                        if (perObGraniZubStat.getIdPervich() != -1) {
                            sqlQ = "INSERT INTO [dbo].[D_pervich_obsled_str] ([id_pervich_obsled],[id_zub],[id_pervich],[fl_status],[id_zona_zub],[prim]) "
                                    + "VALUES (" + idPervichNew + "," + pervischStr.getId_zub() + "," + perObGraniZubStat.getIdPervich() + ",0," + perObGraniZubStat.getIdZonaZub() + ",'" + perObGraniZubStat.getPrim() + "')";
                            //System.out.println("SQL(perObGraniZubStat) = " + sqlQ);
                            rezult = stat.executeUpdate(sqlQ);
                        }

                    }
                }
                //Обработка статусов десны(которые в Lable)
                for (PervichObsledStr pervischStr : cl.getPervichObsled().get(cl.getPervichObsled().size() - 1).getPervichObsledStr()) {
                    for (PervichObsledDesnaStatus perObGraniDesnaStat : pervischStr.getStatDesna()) {
                        if (perObGraniDesnaStat.isStatusBoolean()) {
                            sqlQ = "INSERT INTO [dbo].[D_pervich_obsled_str] ([id_pervich_obsled],[id_zub],[id_pervich],[fl_status],[id_zona_zub],[prim]) "
                                    + "VALUES (" + idPervichNew + "," + pervischStr.getId_zub() + "," + perObGraniDesnaStat.getIdSprPervich() + ",0,11,'" + perObGraniDesnaStat.getPrim() + "')";
                            //System.out.println("SQL(perObGraniZubStat) = " + sqlQ);
                            rezult = stat.executeUpdate(sqlQ);
                        }

                    }
                }

            }

            for (Posesh posesh : cl.getPosesh()) {
                //System.out.println("posesh.getIdPosesh() = " + posesh.getIdPosesh());

                if (posesh.getIdPosesh() != 30000) {
                    // System.out.println("Mы тут1234");
                    if (getPoseshInDB(posesh.getIdPosesh()) != 1) {
                        //System.out.println("Даты первичных обследований" + pervich.getDt_pervich_obsled());
                        sqlQ = "INSERT INTO [dbo].[D_posesh]([dt_posesh],[id_vrach],[id_klient],[id_filial]) "
                                + "VALUES ('" + posesh.getDtPosesh() + "'," + posesh.getIdVrach() + "," + posesh.getIdClient() + "," + posesh.getIdFilial() + ")";
                        //System.out.println("SQL(setClientInfo) posesh = " + sqlQ);
                        rezult = stat.executeUpdate(sqlQ);
                        sql = "SELECT MAX(id_posesh) FROM D_posesh WHERE id_klient = " + posesh.getIdClient() + " AND id_vrach = " + posesh.getIdVrach() + "";
                        rs = st.executeQuery(sql);
                        while (rs.next()) {
                            idPoseshNew = rs.getInt(1);
                        }

                        sqlQ = "INSERT INTO D_Sanac (id_posesh, id_zub, id_sanac) SELECT " + idPoseshNew + ", id_zub,0 FROM S_zub";
                        rezult = stat.executeUpdate(sqlQ);

                        for (PoseshStr poseshStr : posesh.getPoseshStr()) {
                            // System.out.println("1");
                            for (PoseshStrRabZub poseshRabZub : poseshStr.getPoseshRabZub()) {
                                //System.out.println("2");
                                System.out.println("poseshRabZub.getIdRab()" + poseshRabZub.getIdRab());
                                if (poseshRabZub.getIdRab() != -1) {
                                    //System.out.println("3");
                                    sqlQ = "INSERT INTO D_posesh_str (id_posesh, id_rab, id_zub, id_zona_zub, prim, id_rezultat) "
                                            + "VALUES (" + idPoseshNew + "," + poseshRabZub.getIdRab() + "," + poseshStr.getIdZub() + "," + poseshRabZub.getIdZonaZub() + ",'" + poseshRabZub.getPrim() + "'," + poseshRabZub.getIdRezult() + ")";
                                    //System.out.println("poseshRabZub.getIdRab() != -1 = " + sqlQ);
                                    rezult = stat.executeUpdate(sqlQ);
                                }

                            }
                        }
                        //Временные работы
                        for (PoseshStrVrem poseshStrVrem : posesh.getPoseshStrVrem()) {
                            if (!poseshStrVrem.getDtSledVisist().equals("")) {
                                sqlQ = "INSERT INTO D_Posesh_str_vrem(id_posesh, id_zub, dt_sled_vizit, primech) "
                                        + " VALUES (" + idPoseshNew + "," + poseshStrVrem.getIdZub() + ",'" + poseshStrVrem.getDtSledVisist() + "','" + poseshStrVrem.getPrim() + "')";
                                System.out.println("SQL == " + sqlQ);
                                rezult = stat.executeUpdate(sqlQ);
                            }
                        }

                        for (PoseshSanac san : posesh.getPoseshSanac()) {
                            if (san.getIdSanac() == 1) {
                                sqlQ = "UPDATE D_Sanac SET D_Sanac.id_sanac = 1 WHERE (id_posesh = " + idPoseshNew + ") AND (id_zub = " + san.getIdZub() + ")";
                                rezult = stat.executeUpdate(sqlQ);
                            }
                            if (san.getIdSanac() == 2) {
                                sqlQ = "UPDATE D_Sanac SET D_Sanac.id_sanac = 2,dt_sanac = '" + san.getDtSanac() + "'  WHERE (id_posesh = " + idPoseshNew + ") AND (id_zub = " + san.getIdZub() + ")";
                                rezult = stat.executeUpdate(sqlQ);
                            }
                        }

                    }
                }
            }

            //}
            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setClient():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setClient():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;

    }

    public int getPervichInDB(int idPervichObsled) {
        int rezulr = 0;
        try {
            sql = "SELECT [dbo].[getPervichInDB](" + idPervichObsled + ")";
            //st.clearBatch();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezulr = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichInDB():Ошибка подключения или создание Statement");
        }
        return rezulr;
    }

    public int getPervichStrInDB(int idPervichObsled) {
        int rezulr = 0;
        try {
            sql = "SELECT [dbo].[getPervichStrInDB](" + idPervichObsled + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezulr = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichStrInDB():Ошибка подключения или создание Statement");
        }
        return rezulr;
    }

    public int getPoseshInDB(int idPosesh) {
        int rezulr = 0;
        try {
            sql = "SELECT [dbo].[getPoseshInDB](" + idPosesh + ")";
            //st.clearBatch();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezulr = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPoseshInDB():Ошибка подключения или создание Statement");
        }
        return rezulr;
    }

    public ResultSet getRSObsledData(Client cl) {
        try {
            sql = "SELECT [id_pervich_obsled] as Код,[dt_pervich_obsled] as [Дата обследования] FROM [dbo].[D_pervich_obsled] WHERE id_klient = " + cl.getId_klient() + " ORDER BY id_pervich_obsled";
            //st.clearBatch();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSObsledData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSPoseshData(Client cl) {
        try {
            sql = "SELECT [id_posesh] as Код,CAST(FORMAT(dt_posesh,'d', 'de-de') as nvarchar(10)) as [Дата посещения] FROM [dbo].[D_posesh] WHERE id_klient = " + cl.getId_klient() + " "
                    + "UNION ALL "
                    + " SELECT 30000, 'Последнее состояние' as [Дата посещения]   ORDER BY [Дата посещения]";

            System.out.println("SQL = " + sql);
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSPoseshData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSRabData(int idKategRab) {
        try {
            sql = "SELECT id_rab as Код, name_rab as [Наименование работ] FROM S_rab WHERE (id_kateg_rab = " + idKategRab + ") ORDER BY id_rab";
            //st.clearBatch();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSRabData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSKategRabData() {
        try {
            sql = "SELECT [id_kateg_rab] as Код,[name_kateg_rab] as [Наименование категорий] FROM [dbo].[S_kateg_rab]";
            //st.clearBatch();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSKategRabData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSVrachData() {
        ResultSet rsPos = null;
        Statement stat;
        String sqlQ;

        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlQ = "SELECT id_vrach as Код,fam_vrach as Фамилия,imya_vrach as Имя,otch_vrach as Отчество,tel_osn as Телефон,sql_login as [SQL логин], dtroj_vrach as [Дата рождения],r.name_role as [Роль] FROM dbo.D_vrach vr LEFT JOIN dbo.S_Role r ON vr.id_role = r.id_role";
            System.out.println(sqlQ);
            rsPos = st.executeQuery(sqlQ);
            stat.close();
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSVrachData():Ошибка подключения или создание Statement");
        }

        return rsPos;

    }

    public int setVrach(Vrach vr) {
        int rezult = 0;
        int[] otvetServer = new int[4];
        PervichObsled pervich;
        Statement stat;
        String sqlQ;
        String dbRole = getDbRole(vr);
        try {
            stat = conn.createStatement();
            sqlQ = "USE [master] CREATE LOGIN [" + vr.getSqlLogin() + "] WITH PASSWORD=N'" + vr.getPsw() + "', DEFAULT_DATABASE=[stom], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF";
            System.out.println("SQLQ1 = " + sqlQ);
            otvetServer[0] = stat.executeUpdate(sqlQ);
            System.out.println("vr.getIdRole() = " + vr.getIdRole());
            if (vr.getIdRole() == 1) {
                sqlQ = "USE [master] ALTER SERVER ROLE sysadmin ADD MEMBER [" + vr.getSqlLogin() + "]";
                System.out.println("SQLQ2 = " + sqlQ);
                otvetServer[1] = stat.executeUpdate(sqlQ);
            }
            sqlQ = "USE [stom] CREATE USER [" + vr.getSqlLogin() + "] FOR LOGIN [" + vr.getSqlLogin() + "] ALTER USER [" + vr.getSqlLogin() + "] WITH DEFAULT_SCHEMA=[dbo] ALTER ROLE [" + dbRole + "] ADD MEMBER [" + vr.getSqlLogin() + "]";
            System.out.println("SQLQ3 = " + sqlQ);
            otvetServer[1] = stat.executeUpdate(sqlQ);

            sqlQ = "INSERT INTO D_vrach (fam_vrach, imya_vrach, otch_vrach, tel_osn, sql_login, dtroj_vrach, id_role) "
                    + "VALUES ('" + vr.getFam() + "','" + vr.getImya() + "','" + vr.getOtch() + "','" + vr.getTel() + "','" + vr.getSqlLogin() + "','" + vr.getDtRojd() + "'," + vr.getIdRole() + ")";
            System.out.println("SQL4 = " + sqlQ);
            otvetServer[2] = stat.executeUpdate(sqlQ);
            if (SprPolzEdit.listData.length > 0) {
                System.err.println("Тут1");
                for (JCheckBoxSpec chBox : SprPolzEdit.listData) {
                    System.err.println("Тут1");
                    String fio = "";
                    StringBuilder sb = new StringBuilder();
                    sb = sb.append(vr.getFam()).append(" ").append(vr.getImya()).append(" ").append(vr.getOtch());
                    fio = sb.toString().trim();
                    System.out.println("123");
                    sqlQ = "INSERT INTO Vrach_Spec (id_vrach, id_spec) "
                            + "VALUES (dbo.getIdVrach('" + fio + "')," + chBox.getId() + ")";
                    otvetServer[3] = otvetServer[3] + stat.executeUpdate(sqlQ);
                }
                //otvetServer = stat.executeBatch();
            }
            for (int i : otvetServer) {
                rezult = rezult + i;
            }
            stat.close();
        } catch (SQLTimeoutException ex) {
            err = ex.toString();
            System.out.println("ServerDb:setVrach():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            err = ex.toString();
            System.out.println("ServerDb:setVrach():Ошибка подключения или создание Statement" + ex);
        }

        return rezult;
    }

    public String getDbRole(Vrach vr) {
        String nameRole = "";

        Statement stat;
        String sqlQ;
        ResultSet rsPos;
        try {
            stat = conn.createStatement();
            //st.clearBatch();
            System.out.println("vr.getIdRole() = " + vr.getIdRole());
            sqlQ = "SELECT name_role_sql FROM dbo.S_Role WHERE id_role = " + vr.getIdRole() + "";
            System.out.println("sqlgetdbRole = " + sqlQ);
            rsPos = stat.executeQuery(sqlQ);

            while (rsPos.next()) {
                nameRole = rsPos.getString(1);
            }
            System.out.println("nameRole = " + nameRole);
            System.out.println(nameRole);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getDbRole():Ошибка подключения или создание Statement");
        }
        return nameRole;
    }

    public int getIdRole(String nameRole) {
        int idRole = 0;
        Statement stat;
        String sqlQ;
        ResultSet rsPos;
        try {
            stat = conn.createStatement();
            System.out.println("nameRole.trim() = " + nameRole.trim());
            sqlQ = "SELECT TOP 1 id_role  FROM dbo.S_Role WHERE name_role = '" + nameRole.trim() + "'";
            System.out.println(sqlQ);
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                idRole = rsPos.getInt(1);
            }
            System.out.println("idRole = " + idRole);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIdRole():Ошибка подключения или создание Statement");
        }
        return idRole;
    }

    public int getCountPolzov(String namePol) {
        int countPol = 0;
        Statement stat;
        String sqlQ;
        ResultSet rsPos;
        try {
            stat = conn.createStatement();
            sqlQ = "SELECT COUNT(*) AS kol FROM D_vrach WHERE (sql_login = N'" + namePol + "')";
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                countPol = rsPos.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getCountPolzov():Ошибка подключения или создание Statement");
        }
        return countPol;
    }

    public ResultSet getRSIskluchData() {
        try {
            sql = "SELECT D_Iskluch_Rab.id AS Код, S_rab.name_rab AS [Наименование работ], S_kateg_rab.name_kateg_rab AS [Категория работ] FROM D_Iskluch_Rab INNER JOIN S_rab ON D_Iskluch_Rab.id_rab = S_rab.id_rab INNER JOIN S_kateg_rab ON S_rab.id_kateg_rab = S_kateg_rab.id_kateg_rab";
            //st.clearBatch();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSIskluchData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSIskluchStrData(int id) {
        try {
            sql = "SELECT D_Iskluch_Rab_Str.id, S_rab.name_rab, S_kateg_rab.name_kateg_rab FROM D_Iskluch_Rab_Str INNER JOIN S_rab ON D_Iskluch_Rab_Str.id_rab = S_rab.id_rab INNER JOIN S_kateg_rab ON S_rab.id_kateg_rab = S_kateg_rab.id_kateg_rab WHERE (D_Iskluch_Rab_Str.id = " + id + ")";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSIskluchStrData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSPoseshVrem(int idVrach) {
        try {
            sql = "SELECT D_Posesh_str_vrem.id_str_vrem AS Код, dbo.getFIOVrach(D_posesh.id_vrach) AS [Врач назначивший], [dbo].[getFIOClient](D_posesh.id_klient) as Клиент, FORMAT(dt_sled_vizit,'d', 'de-de') as [Дата визита планируемая] FROM D_Posesh_str_vrem INNER JOIN D_posesh ON D_Posesh_str_vrem.id_posesh = D_posesh.id_posesh INNER JOIN D_vrach ON D_posesh.id_vrach = D_vrach.id_vrach INNER JOIN D_klient ON D_posesh.id_klient = D_klient.id_klient WHERE D_klient.id_vrach = " + Find.vr.getId() + " AND ISNULL(fl_obrab,0) = 0 ORDER BY dt_sled_vizit";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSPoseshVrem():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public IsklRab getIsklRabData(int id) {
        IsklRab isklRab = null;
        System.out.println("Start getIsklRabData");
        try {
            sql = "SELECT D_Iskluch_Rab.id AS Код, S_rab.name_rab AS [Наименование работ], S_kateg_rab.name_kateg_rab as [Группа работ] FROM D_Iskluch_Rab INNER JOIN S_rab ON D_Iskluch_Rab.id_rab = S_rab.id_rab INNER JOIN S_kateg_rab ON S_rab.id_kateg_rab = S_kateg_rab.id_kateg_rab WHERE id = " + id + "";
            System.out.println("getIsklRabData " + sql);
            rs = st.executeQuery(sql);

            while (rs.next()) {
                isklRab = new IsklRab(rs.getInt(1), rs.getString(2));
                isklRab.setIsklRabStr(getIsklRabStrData(isklRab));
            }

        } catch (SQLException ex) {
            System.out.println("ServerDb:getIsklRabData():Ошибка подключения или создание Statement");
        }
        System.out.println("getIsklRabData Формирование закончено ");
        return isklRab;
    }

    public ArrayList<IsklRabStr> getIsklRabStrData(IsklRab isklRab) {
        int idIsklRab = isklRab.getId();
        ArrayList<IsklRabStr> isklRabStr = new ArrayList<>();
        IsklRabStr isklRabStrObj;
        try {
            sql = "SELECT D_Iskluch_Rab_Str.id, S_rab.name_rab FROM D_Iskluch_Rab_Str INNER JOIN S_rab ON D_Iskluch_Rab_Str.id_rab = S_rab.id_rab WHERE (D_Iskluch_Rab_Str.id = " + idIsklRab + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                isklRabStrObj = new IsklRabStr(rs.getInt(1), rs.getString(2));
                isklRabStr.add(isklRabStrObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIsklRabStrData():Ошибка подключения или создание Statement");
        }
        return isklRabStr;
    }

    public PoseshStrVrem poseshStrVrem(int idStrVrem) {

        PoseshStrVrem strVrem = null;

        try {
            sql = "SELECT D_Posesh_str_vrem.id_posesh, id_zub, FORMAT(D_Posesh_str_vrem.dt_sled_vizit,'d', 'de-de') as dt_sled_vizit, primech, id_str_vrem, fl_obrab, FORMAT(dt_obrab,'d', 'de-de') as dt_obrab, FORMAT(dt_posesh,'d', 'de-de') as dt_posesh, dbo.getFIOClient(id_klient) as clientFio FROM D_Posesh_str_vrem INNER JOIN D_posesh ON D_Posesh_str_vrem.id_posesh = D_posesh.id_posesh WHERE (D_Posesh_str_vrem.id_str_vrem = " + idStrVrem + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.err.println("rs.getString(4) = " + rs.getString(4));
                strVrem = new PoseshStrVrem(rs.getInt(1), rs.getInt(2), idStrVrem, rs.getString(3), rs.getBoolean(6), rs.getString(7), rs.getString(4));
                strVrem.setDtRabot(rs.getString(8));
                strVrem.setClientFIO(rs.getString(9));
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:poseshStrVrem():Ошибка подключения или создание Statement");
        }
        return strVrem;
    }

    public int updatePoseshStrVrem(PoseshStrVrem strVrem) {

        int rezult = 0;

        try {
            sql = "UPDATE D_Posesh_str_vrem SET primech = '" + strVrem.getPrim() + "', fl_obrab = 1, dt_obrab = '" + strVrem.getDtObrab() + "' WHERE id_str_vrem = " + strVrem.getIdStrVrem() + "";
            rezult = st.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("ServerDb:updatePoseshStrVrem():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int perenosPoseshStrVrem(PoseshStrVrem strVrem, String dt, String prim) {

        int rezult = 0;

        try {
            sql = "UPDATE stom.dbo.D_Posesh_str_vrem SET fl_obrab = 1, dt_obrab = getDate() WHERE id_str_vrem = " + strVrem.getIdStrVrem() + "";
            System.out.println("SQL = " + sql);
            rezult = st.executeUpdate(sql);
            sql = "INSERT INTO D_Posesh_str_vrem (id_posesh, id_zub, dt_sled_vizit, primech) SELECT id_posesh, id_zub, '" + dt + "', '" + prim + "' FROM D_Posesh_str_vrem AS D_Posesh_str_vrem_1 WHERE D_Posesh_str_vrem_1.id_str_vrem = " + strVrem.getIdStrVrem() + "";
            rezult = st.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("ServerDb:perenosPoseshStrVrem():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    /////////////////////////////////////////////////////////////////////// 
    public ArrayList<SprPervich> getSprPervichData(int idKateg) {
        SprPervich sprPervichObj = null;
        ArrayList<SprPervich> sprPervich = new ArrayList<>();
        try {
            sql = "SELECT S_pervich.id_pervich AS Код, S_pervich.kod_kn_pervich AS Наименование, S_pervich.opisanie_pervich AS Описание, S_kateg_rab.name_kateg_rab AS Категория, S_pervich.fl_vozm_sanac, S_pervich.id_kateg_rab\n"
                    + "FROM S_pervich INNER JOIN S_kateg_rab ON S_pervich.id_kateg_rab = S_kateg_rab.id_kateg_rab WHERE S_pervich.id_kateg_rab = " + idKateg + "";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprPervichObj = new SprPervich(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(5), rs.getInt(6), rs.getString(4), false);
                sprPervich.add(sprPervichObj);
            }
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!" + sprPervich.size());
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIsklRabData():Ошибка подключения или создание Statement");
        }
        //System.out.println("getIsklRabData Формирование закончено ");
        return sprPervich;
    }

    public ResultSet getRSSprPervichData() {
        try {
            sql = "SELECT S_pervich.id_pervich AS Код, S_pervich.kod_kn_pervich AS Наименование, S_pervich.opisanie_pervich AS Описание, S_kateg_rab.name_kateg_rab as Категория FROM S_pervich INNER JOIN S_kateg_rab ON S_pervich.id_kateg_rab = S_kateg_rab.id_kateg_rab";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSSprPervichData():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    ////////////////////////////////////////////////////////////////////////
    public ArrayList<SprRab> getSprRab(int idKatRab, int idPervich) {
        ArrayList<SprRab> sprRabList = new ArrayList<>();
        SprRab sprRabObj;
        try {
            sql = "SELECT [id_rab],[id_kateg_rab],[name_rab],[color],[fl_vrem],[fl_chast_vyp] FROM [dbo].[S_rab] WHERE id_kateg_rab = " + idKatRab + " AND id_rab NOT IN (SELECT [id_rab] FROM [stom].[dbo].[Pervich_Rabot] WHERE id_pervich = " + idPervich + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprRabObj = new SprRab(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getBoolean(6));
                sprRabObj.setColor(getColorPosesh(rs.getInt(1)));
                sprRabList.add(sprRabObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSprRab():Ошибка подключения или создание Statement");
        }
        return sprRabList;
    }
    
    
    
    
    

    public ArrayList<JCheckBoxSprRab> getPervichRab(int idPervich) {
        ArrayList<JCheckBoxSprRab> check = new ArrayList<>();
        JCheckBoxSprRab checkObj;
        try {
            sql = "SELECT [id_rab],[id_kateg_rab],[name_rab],[dbo].[getSelectRab](id_rab," + idPervich + ") as selected FROM [dbo].[S_rab]";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                checkObj = new JCheckBoxSprRab(rs.getInt(1), rs.getNString(3), rs.getBoolean(4), rs.getInt(2));
                check.add(checkObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPervichRab():Ошибка подключения или создание Statement");
        }
        return check;
    }

    public ArrayList<SprRab> getSprRabVrem(int idStrVrem) {
        ArrayList<SprRab> sprRab = new ArrayList<>();
        SprRab sprRabObj;
        try {
            sql = "SELECT S_rab.id_rab, S_rab.name_rab, D_Posesh_str_vrem.id_str_vrem, D_Posesh_str_vrem.id_zub "
                    + " FROM D_Posesh_str_vrem INNER JOIN D_posesh_str ON D_Posesh_str_vrem.id_posesh = D_posesh_str.id_posesh INNER JOIN S_rab ON D_posesh_str.id_rab = S_rab.id_rab INNER JOIN D_posesh ON D_posesh_str.id_posesh = D_posesh.id_posesh  "
                    + " GROUP BY S_rab.id_rab, S_rab.name_rab, D_Posesh_str_vrem.id_str_vrem, D_Posesh_str_vrem.id_zub HAVING (D_Posesh_str_vrem.id_str_vrem = " + idStrVrem + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprRabObj = new SprRab(rs.getInt(1), 0, rs.getString(2), "");
                sprRab.add(sprRabObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSprRabVrem():Ошибка подключения или создание Statement");
        }
        return sprRab;
    }

    public Client getPoseshData(Client cl) {
        this.client = cl;
        int id_client = cl.getId_klient();
        ArrayList<Posesh> posesh = new ArrayList<>();
        Posesh poseshObj;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        int countPosech = 0;
        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlQ = "SELECT id_posesh, FORMAT(dt_posesh,'d', 'de-de') as dt, id_vrach, id_klient, id_filial FROM D_posesh WHERE id_klient = " + id_client + " "
                    + "UNION ALL "
                    + "SELECT 30000, 'Последнее состояние' as dt, " + Find.vr.getId() + ", " + id_client + ", [dbo].[getIdFilial]('" + Start.idfilial + "')";
            System.out.println("SQL = " + sqlQ);
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {

                if (rsPos.getInt(1) == 30000) {
                    System.out.println("Мы тут!");
                    poseshObj = new Posesh(rsPos.getInt(1), rsPos.getString(2), rsPos.getInt(3), rsPos.getInt(4), rsPos.getInt(5));
                    poseshObj.setPoseshStr(getPoseshStrData(poseshObj));
                    posesh.add(poseshObj);

                } else {

                    poseshObj = new Posesh(rsPos.getInt(1), rsPos.getString(2), rsPos.getInt(3), rsPos.getInt(4), rsPos.getInt(5));
                    poseshObj.setPoseshStr(getPoseshStrData(poseshObj));
                    poseshObj.setPoseshStrVrem(getPoseshStrVrem(poseshObj));
                    poseshObj.setPoseshSanac(getPoseshSanac(poseshObj));
                    posesh.add(poseshObj);

                }

            }

        } catch (SQLException ex) {
            System.out.println("ServerDb:getPoseshData:Ошибка подключения или создание Statement " + ex);
        }
        cl.setPosesh(posesh);
        System.out.println("Количесво посещение = " + posesh.size());

        return cl;
    }

    public ArrayList<PoseshSanac> getPoseshSanac(Posesh posesh) {
        int idPosesh = posesh.getIdPosesh();
        ArrayList<PoseshSanac> poseshSanac = new ArrayList<>();
        PoseshSanac poseshSanacObj;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        for (int z = 0; z < 32; z++) {
            poseshSanacObj = new PoseshSanac(idPosesh, z, "", 0);
            poseshSanac.add(poseshSanacObj);
        }
        try {
            stat = conn.createStatement();
            sqlQ = "SELECT id_posesh, id_zub, ISNULL(FORMAT(dt_sanac,'d', 'de-de'),'') as dt_sanac, id_sanac FROM D_Sanac WHERE id_posesh = " + idPosesh + "";
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                for (PoseshSanac san : poseshSanac) {
                    if (san.getIdZub() == rsPos.getInt(2)) {
                        san.setDtSanac(rsPos.getString(3));
                        san.setIdSanac(rsPos.getInt(4));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPoseshSanac():Ошибка подключения или создание Statement " + ex);
        }
//        for (PoseshSanac san : poseshSanac) {
//            System.out.println("san.getIdPosesh() = " + san.getIdPosesh());
//            System.out.println("san.getIdZub() = " + san.getIdZub());
//            System.out.println("san.getDtSanac = " + san.getDtSanac());
//            System.out.println("san.getIdSanac = " + san.getIdSanac());
//                           
//        }
//        
        return poseshSanac;
    }

    public ArrayList<PoseshStrVrem> getPoseshStrVrem(Posesh posesh) {
        int idPosesh = posesh.getIdPosesh();
        ArrayList<PoseshStrVrem> poseshStrVrem = new ArrayList<>();
        PoseshStrVrem poseshStrObj;

        ResultSet rsPos;
        Statement stat;
        String sqlQ;

        for (int z = 0; z < 32; z++) {
            poseshStrObj = new PoseshStrVrem(idPosesh, z, 0, "", false, "", "");
            poseshStrVrem.add(poseshStrObj);
        }

        try {
            stat = conn.createStatement();
            sqlQ = "SELECT [id_posesh],[id_zub],FORMAT(dt_sled_vizit,'d', 'de-de') as [dt_sled_vizit],[primech],[id_str_vrem],[fl_obrab],FORMAT(dt_obrab,'d', 'de-de') as [dt_obrab] FROM [stom].[dbo].[D_Posesh_str_vrem] WHERE ISNULL(fl_obrab,0) = 0 AND id_posesh =  " + idPosesh + "";

            rsPos = stat.executeQuery(sqlQ);

            while (rsPos.next()) {
                for (PoseshStrVrem strVrem : poseshStrVrem) {
                    if (strVrem.getIdZub() == rsPos.getInt(2)) {
                        strVrem.setDtSledVisist(rsPos.getString(3));
                        strVrem.setPrim(rsPos.getString(4));
                        strVrem.setIdStrVrem(rsPos.getInt(5));
                        strVrem.setFlObrab(rsPos.getBoolean(6));
                        strVrem.setDtObrab(rsPos.getString(7));

                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("ServerDb:getPoseshStrVrem():Ошибка подключения или создание Statement " + ex);
        }

        return poseshStrVrem;
    }

    public ArrayList<PoseshStr> getPoseshStrData(Posesh posesh) {
        int idPosesh = posesh.getIdPosesh();
        ArrayList<PoseshStr> poseshStr = new ArrayList<>();
        PoseshStr poseshStrObj;

        for (int z = 0; z < 32; z++) {
//            if (idPosesh == 30000) {
//                poseshStrObj = new PoseshStr(idPosesh, z);
//                poseshStrObj.setPoseshRabZub(getPoseshRabStatus(poseshStrObj));
//                poseshStr.add(poseshStrObj);
//            } else {
            poseshStrObj = new PoseshStr(idPosesh, z);
            poseshStrObj.setPoseshRabZub(getPoseshRabStatus(poseshStrObj));
            poseshStr.add(poseshStrObj);
        }
        //};

        return poseshStr;
    }

    public ArrayList<PoseshStrRabZub> getPoseshRabStatus(PoseshStr poseshStr) {
        int idPosesh = poseshStr.getIdPosesh();//pervichStr.getId_pervich_obsled();
        int idZub = poseshStr.getIdZub();//pervichStr.getId_zub();
        ArrayList<PoseshStrRabZub> poseshStrRabZub = new ArrayList<>();
        PoseshStrRabZub poseshStrRabZubObj;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        //Заполним -1 все грани зуба
        for (int gr = 0; gr < 13; gr++) {
            Integer[] color = {255, 255, 255};
            poseshStrRabZubObj = new PoseshStrRabZub(-1, gr, -1, 0, color);
            poseshStrRabZub.add(poseshStrRabZubObj);
        }

        try {
            if (idPosesh == 30000) {
                //System.out.println("Мы тут 2 !");    
                stat = conn.createStatement();
                sqlQ = "SELECT 30000, dbo.getIdLastRab(S_Zona_Zub.id_zona_zub,S_zub.id_zub," + client.getId_klient() + ",1) as id_rab, S_zub.id_zub , S_Zona_Zub.id_zona_zub, dbo.getIdLastRabPrim(S_Zona_Zub.id_zona_zub, S_zub.id_zub," + client.getId_klient() + ") as prim,0 as id_posesh_str, dbo.getIdLastRab(S_Zona_Zub.id_zona_zub, S_zub.id_zub," + client.getId_klient() + ",2) as id_rezultat FROM S_Zona_Zub CROSS JOIN S_zub WHERE (S_Zona_Zub.id_zona_zub <> -1) AND (S_zub.id_zub = " + idZub + ") ORDER BY S_zub.id_zub";
                //System.out.println("SQL \"SELECT 30000 = " + sqlQ);
                rsPos = stat.executeQuery(sqlQ);
                //System.out.println("Мы тут 3 !"); 
            } else {
                stat = conn.createStatement();
                sqlQ = "SELECT [id_posesh],[id_rab],[id_zub],[id_zona_zub],[prim],[id_posesh_str],[id_rezultat] FROM [dbo].[D_posesh_str] WHERE (id_posesh = " + idPosesh + ") AND (id_zub = " + idZub + ") AND (id_zona_zub <> -1)";
                //System.out.println("SQL2 = " + sqlQ);
                rsPos = stat.executeQuery(sqlQ);
            }

            while (rsPos.next()) {

                for (PoseshStrRabZub grStat : poseshStrRabZub) {

                    if (grStat.getIdZonaZub() == rsPos.getInt(4)) {
                        grStat.setColor(getColorPosesh(rsPos.getInt(2)));
                        grStat.setPrim(rsPos.getString(5));
                        grStat.setIdRab(rsPos.getInt(2));
                        grStat.setIdPoseshStr(rsPos.getInt(6));
                        grStat.setIdRezult(rsPos.getInt(7));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPoseshRabStatus():Ошибка подключения или создание Statement " + ex);
        }
        return poseshStrRabZub;
    }

    public ResultSet getRSSprPerenaprav(int idVrach, int idClient) {
        try {
            sql = "SELECT        D_Perenaprav.id_perenaprav as Код, D_Perenaprav.dt_otprav as [Дата перенаправления],RTRIM(LTRIM(ISNULL(D_klient.fam_klient,'') + ' ' + ISNULL(D_klient.imya_klient,'') + ' ' + ISNULL(D_klient.otch_klient,''))) as [Клиент], \n"
                    + "RTRIM(LTRIM(ISNULL(D_vrach.fam_vrach,'') + ' ' + ISNULL(D_vrach.imya_vrach,'') + ' ' + ISNULL(D_vrach.otch_vrach,''))) as [Врач], S_Spec.name_spec as [Специализация], D_Perenaprav.dt_poluch as [Дата подтверждения]\n"
                    + "FROM D_Perenaprav LEFT JOIN D_klient ON D_Perenaprav.id_klient = D_klient.id_klient LEFT JOIN S_Spec ON D_Perenaprav.id_spec_vrach_poluch = S_Spec.id_spec LEFT JOIN D_vrach ON D_Perenaprav.id_vrach_poluch = D_vrach.id_vrach WHERE (D_Perenaprav.id_vrach_otprav = " + idVrach + ") AND (D_Perenaprav.id_klient = " + idClient + ") AND (D_Perenaprav.fl_otm <> 1)";

            System.out.println(sql);
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSSprPerenaprav():Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ArrayList<SprSpec> getSprSpec() {

        ArrayList<SprSpec> sprSpec = new ArrayList<>();
        SprSpec sprSpecObj = null;
        try {
            sql = "SELECT [id_spec],[name_spec] FROM [dbo].[S_Spec]";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprSpecObj = new SprSpec(rs.getInt(1), rs.getString(2), Constatnts.ORIGIN);
                sprSpec.add(sprSpecObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSprSpec():Ошибка подключения или создание Statement");
        }
        return sprSpec;
    }

    public int getNeobhodObs(int idClient) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getNeobhodObsle(" + idClient + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSprSpec():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int getKolObsledPosleDt(String dt, int idClient) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getKolObsledPosleDt('" + dt + "'," + idClient + ")";
            // System.out.println("SELECT dbo.getKolObsledPosleDt" + sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getKolObsledPosleDt():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int getKolPoseshPosleDt(String dt, int idClient) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getKolPoseshPosleDt('" + dt + "'," + idClient + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getKolPoseshPosleDt():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int getIdFilial(String filialName) {
        int idFilial = 0;
        try {
            sql = "SELECT dbo.getIdFilial('" + filialName + "')";
            System.out.println("SELECT dbo.getIdFilial" + sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                idFilial = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIdFilial():Ошибка подключения или создание Statement");
        }
        return idFilial;
    }

    public boolean getFlZapretObcled(int idObsled) {
        boolean rezult = true;

        try {
            sql = "SELECT dbo.getFlZapretObcled(" + idObsled + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getBoolean(1);
                System.out.println("glkflkd" + rs.getBoolean(1));
                System.out.println("sdfsdf" + idObsled);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getFlZapretObcled():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public boolean getgetKolNeZavObsled(int idClient) {
        boolean rezult = true;
        try {
            sql = "SELECT dbo.getKolNeZavObsled(" + idClient + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getgetKolNeZavObsled():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int setObsled(int idClient, String dtObsled, int idVrach) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            sqlQ = "INSERT INTO D_pervich_obsled (dt_pervich_obsled, id_vrach, id_klient,fl_zapret,id_filial) VALUES (GETDATE()," + idVrach + "," + idClient + ",0,dbo.getIdFilial('" + Start.idfilial + "'))";
            System.out.println(sqlQ);
            rezult = stat.executeUpdate(sqlQ);
            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setObsled():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setObsled():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public ResultSet getRSSprSpec() {
        try {
            sql = "SELECT name_spec FROM S_Spec";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSSprSpec():Ошибка подключения или создание Statement");
        }

        return rs;
    }

    public ResultSet getRSVrachPoSpec(String nameSpec) {
        try {
            sql = "SELECT RTRIM(LTRIM(ISNULL(D_vrach.fam_vrach,'') + ' ' + ISNULL(D_vrach.imya_vrach,'') + ' ' + ISNULL(D_vrach.otch_vrach,''))) "
                    + "FROM D_vrach INNER JOIN Vrach_Spec ON D_vrach.id_vrach = Vrach_Spec.id_vrach INNER JOIN S_Spec ON Vrach_Spec.id_spec = S_Spec.id_spec WHERE (S_Spec.name_spec = N'" + nameSpec + "' AND D_vrach.id_vrach <> " + Find.vr.getId() + ")";
            System.out.println(sql);
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSVrachPoSpec():Ошибка подключения или создание Statement");
        }

        return rs;
    }

    public int getIdVrach(String nameVrach) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getIdVrach('" + nameVrach + "')";
            //System.out.println("getIdVrach()" + sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIdVrach():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int getIdSpec(String nameSpec) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getIdSpec('" + nameSpec + "')";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIdSpec():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int setPerenaprav(Perenaprav perenaprav, int constant) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            if (constant == Constatnts.INSERT) {
                sqlQ = "INSERT INTO D_Perenaprav "
                        + "(id_klient, id_vrach_otprav, id_vrach_poluch, id_spec_vrach_poluch, dt_otprav, fl_otm, prim) "
                        + "VALUES (" + perenaprav.getIdClient() + "," + perenaprav.getIdVrachOtprav() + "," + perenaprav.getIdVrachPoluch() + "," + perenaprav.getIdSpecVrach() + ",'" + perenaprav.getDtOtprav() + "',0,'" + perenaprav.getPrim() + "')";
                rezult = stat.executeUpdate(sqlQ);
            }
            if (constant == Constatnts.UPDATE) {
                sqlQ = "UPDATE D_Perenaprav SET dt_poluch = '" + perenaprav.getDtPoluch() + "', prim ='" + perenaprav.getPrim().trim() + "'  WHERE (id_perenaprav = " + perenaprav.getIdPerenaprav() + ")";
                System.out.println(sqlQ);
                rezult = stat.executeUpdate(sqlQ);
            }

            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setPerenaprav():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setPerenaprav():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public Perenaprav getPerenaprav(int idPerenaprav) {
        Perenaprav perenaprav = null;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlQ = "SELECT  id_klient, id_vrach_otprav, id_vrach_poluch, id_spec_vrach_poluch, FORMAT([dt_otprav],'d', 'de-de') as dt_otprav, FORMAT([dt_poluch],'d', 'de-de') as dt_poluch, fl_otm, prim,id_perenaprav FROM D_Perenaprav WHERE (id_perenaprav = " + idPerenaprav + ")";
            System.out.println(sqlQ);
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                perenaprav = new Perenaprav(rsPos.getInt(1), rsPos.getInt(2), rsPos.getInt(3), rsPos.getInt(4), rsPos.getString(5), rsPos.getString(6), rsPos.getBoolean(7), rsPos.getString(8), rsPos.getInt(9));
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getPerenaprav:Ошибка подключения или создание Statement");
        }
        return perenaprav;
    }

    public ArrayList<SprSpec> getSpecVrach(int idVrach) {
        ArrayList<SprSpec> sprSpec = new ArrayList<>();
        SprSpec sprSpecObj = null;
        try {
            sql = "SELECT S_Spec.id_spec, S_Spec.name_spec FROM Vrach_Spec INNER JOIN S_Spec ON Vrach_Spec.id_spec = S_Spec.id_spec WHERE (Vrach_Spec.id_vrach = " + idVrach + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprSpecObj = new SprSpec(rs.getInt(1), rs.getString(2), Constatnts.ORIGIN);
                sprSpec.add(sprSpecObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSpecVrach():Ошибка подключения или создание Statement");
        }
        return sprSpec;
    }

    public ArrayList<SprRezultat> getSprRezult() {
        ArrayList<SprRezultat> sprRezult = new ArrayList<>();
        SprRezultat sprRezultObj = null;
        try {
            sql = "SELECT [id_rezultat],[name_rezultat] FROM [stom].[dbo].[S_Rezultat]";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprRezultObj = new SprRezultat(rs.getInt(1), rs.getString(2));
                sprRezult.add(sprRezultObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSprRezult():Ошибка подключения или создание Statement" + ex);
        }
        return sprRezult;
    }

    public ResultSet getRSSpecVrach(int idVrach) {
        try {
            sql = "SELECT S_Spec.id_spec, S_Spec.name_spec FROM Vrach_Spec INNER JOIN S_Spec ON Vrach_Spec.id_spec = S_Spec.id_spec WHERE (Vrach_Spec.id_vrach = " + idVrach + ")";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSSpecVrach():Ошибка подключения или создание Statement");
        }

        return rs;
    }

    public JCheckBoxSpec[] getStringArrSpec(int idVrach) {
        //statRow статус который определяет выбиорать по врачу или нет
        JCheckBoxSpec[] specArr = null;
        if (idVrach > -1) {
            sql = "SELECT S_Spec.id_spec, S_Spec.name_spec FROM Vrach_Spec INNER JOIN S_Spec ON Vrach_Spec.id_spec = S_Spec.id_spec WHERE (Vrach_Spec.id_vrach = " + idVrach + ")";
        } else {
            sql = "SELECT S_Spec.id_spec, S_Spec.name_spec FROM S_Spec";
        }
        try {
            rs = st.executeQuery(sql);
            rs.last();//--last row position
            int resulSetSize = rs.getRow();
            rs.beforeFirst();
            specArr = new JCheckBoxSpec[resulSetSize];
            int i = 0;
            while (rs.next()) {
                if (idVrach > -1) {
                    specArr[i] = new JCheckBoxSpec(rs.getInt(1), rs.getString(2), true);
                } else {
                    specArr[i] = new JCheckBoxSpec(rs.getInt(1), rs.getString(2), false);
                }

                i++;
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getStringArrSpec():Ошибка подключения или создание Statement");
        }
        System.out.println("Длина массива specArr" + specArr.length);
        return specArr;
    }

    public int setPodtvObsled(int idObsled) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            sqlQ = "UPDATE D_pervich_obsled SET fl_zapret = 1 WHERE id_pervich_obsled = " + idObsled + "";
            rezult = stat.executeUpdate(sqlQ);
            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setPodtvObsled():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setPodtvObsled():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public Integer[] getColorPervich(int idPervich) {
        Integer[] color = new Integer[3];
        String[] colorStr = new String[3];
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlQ = "SELECT ISNULL(color,''),id_kateg_rab FROM S_pervich WHERE (id_pervich = " + idPervich + ")";
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                colorStr = rsPos.getString(1).split(",");
                if (rsPos.getInt(2) == 1) {
                    for (int i = 0; i < colorStr.length; i++) {
                        color[i] = new Integer(colorStr[i]);
                    }
                } else {
                    for (int i = 0; i < colorStr.length; i++) {
                        color[i] = new Integer(255);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getColorPervich:Ошибка подключения или создание Statement");
        }
        return color;
    }

    public Integer[] getColorPosesh(int idRab) {
        Integer[] color = new Integer[3];
        String[] colorStr = new String[3];
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        if (idRab == -1) {
            for (int i = 0; i < 3; i++) {
                color[i] = 255;
            }
            return color;
        }

        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlQ = "SELECT ISNULL(color,'') FROM [S_rab] WHERE (id_rab = " + idRab + ")";
            rsPos = stat.executeQuery(sqlQ);
            while (rsPos.next()) {
                colorStr = rsPos.getString(1).split(",");
                for (int i = 0; i < colorStr.length; i++) {
                    color[i] = new Integer(colorStr[i]);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getColorPosesh:Ошибка подключения или создание Statement");
        }
        return color;
    }

    public ArrayList<SprPervich> getSprPervich(int idKateg) {
        ArrayList<SprPervich> sprPervich = new ArrayList<>();
        SprPervich sprPervichObj = null;

        try {
            sql = "SELECT S_pervich.id_pervich, S_pervich.kod_kn_pervich, S_pervich.opisanie_pervich, S_pervich.fl_vozm_sanac, S_pervich.id_kateg_rab, S_pervich.color, S_kateg_rab.name_kateg_rab\n"
                    + "FROM S_pervich LEFT OUTER JOIN S_kateg_rab ON S_pervich.id_kateg_rab = S_kateg_rab.id_kateg_rab WHERE (S_pervich.id_kateg_rab = " + idKateg + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                sprPervichObj = new SprPervich(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5), rs.getString(7), false, getColorPervich(rs.getInt(1)));
                sprPervich.add(sprPervichObj);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getSprPervich():Ошибка подключения или создание Statement");
        }
        return sprPervich;
    }

    public SprKategRab[] getKategRab() {
        SprKategRab[] sprKategRab = null;
        ResultSet rsPos;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlQ = "SELECT [id_kateg_rab],[name_kateg_rab] FROM [dbo].[S_kateg_rab]";
            System.out.println(sqlQ);
            rsPos = stat.executeQuery(sqlQ);
            rsPos.last();//--last row position
            int resulSetSize = rsPos.getRow();
            //System.out.println("resulSetSize" + resulSetSize);
            rsPos.beforeFirst();
            //rsPos = stat.executeQuery(sqlQ);
            sprKategRab = new SprKategRab[resulSetSize];
            //System.out.println("SprKategRab[resulSetSize]"+ sprKategRab.length);
            int i = 0;
            //System.out.println("i" + i);
            while (rsPos.next()) {
                //System.out.println("rs.getString(2)" + rsPos.getString(2));
                sprKategRab[i] = new SprKategRab(rsPos.getInt(1), rsPos.getString(2));
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getKategRab:Ошибка подключения или создание Statement" + ex);
        }
        for (SprKategRab spr : sprKategRab) {
            System.out.println("spr.getNameKategRab()" + spr.getNameKategRab());
        }
        return sprKategRab;
    }

    public int setPervich(SprPervich spr) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            sqlQ = "INSERT INTO S_pervich\n"
                    + "                         (kod_kn_pervich, opisanie_pervich, fl_vozm_sanac, id_kateg_rab, color, nameIconSmall, nameIcomBig)\n"
                    + "               VALUES    ('" + spr.getKodKn() + "','" + spr.getOpis() + "','" + spr.isFlSanac() + "'," + spr.getIdKateg() + ",'" + spr.getColorString() + "','" + spr.getNameIcomSmall() + "','" + spr.getNameIcomBig() + "')";

            rezult = stat.executeUpdate(sqlQ);

            stat.close();
        } catch (SQLTimeoutException ex) {
            err = ex.toString();
            System.out.println("ServerDb:setPervich():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            err = ex.toString();
            System.out.println("ServerDb:setPervich():Ошибка подключения или создание Statement" + ex);
        }

        return rezult;
    }

    public ResultSet getRSNewPervichObsled() {
        try {
            sql = "SELECT [id_pervich_obsled],[dt_pervich_obsled] FROM [dbo].[D_pervich_obsled_NEW] WHERE id_vrach = " + Find.vr.getId() + " AND id_klient = " + MainForm.cl.getId_klient() + "";
            //st.clearBatch();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSNewPervichObsled:Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public ResultSet getRSNewPosesh() {
        try {
            sql = "SELECT id_posesh, FORMAT([dt_posesh],'d', 'de-de') FROM D_posesh_New WHERE id_vrach = " + Find.vr.getId() + " AND id_klient = " + MainForm.cl.getId_klient() + "";
            //st.clearBatch();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getRSNewPosesh:Ошибка подключения или создание Statement");
        }
        return rs;
    }

    public int deleteFromPervichNew() {
        int rezult = 0;
        try {
            sql = "DELETE FROM [dbo].[D_pervich_obsled_NEW] WHERE id_vrach = " + Find.vr.getId() + " AND id_klient = " + MainForm.cl.getId_klient() + "";
            System.out.println("SQL = " + sql);
            rezult = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:deleteFromPervichNew:Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int deleteFromPoseshNew() {
        int rezult = 0;
        try {
            sql = "DELETE FROM [dbo].[D_posesh_New] WHERE id_vrach = " + Find.vr.getId() + " AND id_klient = " + MainForm.cl.getId_klient() + "";
            System.out.println("SQL = " + sql);
            rezult = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("ServerDb:deleteFromPoseshNew:Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int setNewObsledTemp(PervichObsled pervich) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();

            sqlQ = "INSERT INTO D_pervich_obsled_NEW\n"
                    + "(id_pervich_obsled,dt_pervich_obsled, id_vrach,id_klient)\n"
                    + "VALUES (0,'" + pervich.getDt_pervich_obsled() + "'," + pervich.getId_vrach() + "," + pervich.getId_klient() + ")";

            System.out.println("setNewObsledTemp = " + sqlQ);
            rezult = stat.executeUpdate(sqlQ);
            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setNewObsledTemp():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setNewObsledTemp():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public int setNewPoseshTemp(Posesh pos) {
        int rezult = 0;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            sqlQ = "INSERT INTO D_posesh_New(id_posesh, dt_posesh, id_vrach, id_klient, id_filial) "
                    + "VALUES (0,'" + pos.getDtPosesh() + "'," + pos.getIdVrach() + "," + pos.getIdClient() + "," + pos.getIdFilial() + ")";
            System.out.println("setNewObsledTemp = " + sqlQ);
            rezult = stat.executeUpdate(sqlQ);
            stat.close();
        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setNewPoseshTemp():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setNewPoseshTemp():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public int setIsklPepvichRab(ArrayList<JCheckBoxSprRab> checkList, int idPervich) {
        int rezult = 0;
        int[] rezArr;
        Statement stat;
        String sqlQ;
        try {
            stat = conn.createStatement();
            stat.clearBatch();
            stat.addBatch("BEGIN ");
            stat.addBatch("DELETE FROM [dbo].[Pervich_Rabot] WHERE id_pervich = " + idPervich + "");
            for (JCheckBoxSprRab check : checkList) {
                sqlQ = "INSERT INTO [dbo].[Pervich_Rabot]([id_pervich],[id_rab]) VALUES (" + idPervich + "," + check.getId() + ")";
                stat.addBatch(sqlQ);
            }
            stat.addBatch(" END");
            rezArr = stat.executeBatch();
            for (int i = 0; i < rezArr.length; i++) {
                rezult = rezult + rezArr[i];
            }

        } catch (SQLTimeoutException ex) {
            System.out.println("ServerDb:setIsklPepvichRab():Ошибка подключения или создание Statement" + ex);
        } catch (SQLException ex) {
            System.out.println("ServerDb:setIsklPepvichRab():Ошибка подключения или создание Statement" + ex);
        }
        return rezult;
    }

    public String getFIOClient(int idCLient) {
        String FIOClient = "";
        try {
            //st.clearBatch();
            sql = "SELECT dbo.getFIOClient(" + idCLient + ")";
            System.out.println("SQLLLLL = " + sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                FIOClient = rs.getString(1);
            }
            //System.out.println(FIOClient);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getFIOClient():Ошибка подключения или создание Statement");
        }
        return FIOClient;
    }

    public String getFIOVrach(int idVrach) {
        String FIOVrach = "";
        try {
            //st.clearBatch();
            sql = "SELECT dbo.getFIOVrach(" + idVrach + ")";
            System.out.println("SQLLLLL = " + sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                FIOVrach = rs.getString(1);
            }
            //System.out.println(FIOClient);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getFIOVrach():Ошибка подключения или создание Statement");
        }
        return FIOVrach;
    }

    public int getCountVrem(int idVrach) {
        int rezult = 0;
        try {
            //st.clearBatch();
            sql = "SELECT dbo.getCountVrem(" + idVrach + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
            //System.out.println(FIOClient);
        } catch (SQLException ex) {
            System.out.println("ServerDb:getCountVrem():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int getIdPervichNow(int idZub, int idFigure) {
        int rezult = 0;
        int idVrach = Find.vr.getId();
        try {
            sql = "SELECT dbo.getIdPervichNow(" + idZub + "," + idFigure + "," + idVrach + "," + MainForm.cl.getId_klient() + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIdPervichNow():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    
    public int getCountPerenaprav(int idClient, int idVrach) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getCountPerenaprav(" + idClient + "," + idVrach + ")";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIsPerenap():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public int getIdPerenaprav(int idClient, int idVrach) {
        int rezult = 0;
        try {
            sql = "SELECT dbo.getIdPerenaprav(" + idClient + "," + idVrach + ")";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getIdPerenaprav():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    public String getNamrSpec(int idSpec) {
        String rezult = "";
        try {
            sql = "SELECT dbo.getNamrSpec(" + idSpec + ")";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                rezult = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println("ServerDb:getNamrSpec():Ошибка подключения или создание Statement");
        }
        return rezult;
    }

    @Override
    public int disconnect() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Constatnts.DISCONNECT;
    }

}
