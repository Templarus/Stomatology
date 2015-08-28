/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class PervichObsled {
    
    private int id_pervich_obsled;
    private int id_klient; 
    private int id_vrach;
    private String dt_pervich_obsled;
    private int idFilial;
    private int flZapret;
    private String prim = "";
    private boolean flSixMonth;
    
    private ArrayList<PervichObsledStr> pervichObsledStr;

    
    public PervichObsled(int id_pervich_obsled,int id_klient,int id_vrach,String dt_pervich_obsled, int idFilial, int flZapret){
    this.id_pervich_obsled = id_pervich_obsled;
    this.id_klient = id_klient; 
    this.id_vrach = id_vrach;
    this.dt_pervich_obsled = dt_pervich_obsled;
    this.idFilial = idFilial;
    this.flZapret = flZapret;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public int getFlZapret() {
        return flZapret;
    }

    public void setFlZapret(int flZapret) {
        this.flZapret = flZapret;
    }
    
    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    
    public int getId_pervich_obsled() {
        return id_pervich_obsled;
    }

    public int getId_klient() {
        return id_klient;
    }

    public int getId_vrach() {
        return id_vrach;
    }

    public String getDt_pervich_obsled() {
        return dt_pervich_obsled;
    }

    public ArrayList<PervichObsledStr> getPervichObsledStr() {
        return pervichObsledStr;
    }


    public void setId_pervich_obsled(int id_pervich_obsled) {
        this.id_pervich_obsled = id_pervich_obsled;
    }

    public void setId_klient(int id_klient) {
        this.id_klient = id_klient;
    }

    public void setId_vrach(int id_vrach) {
        this.id_vrach = id_vrach;
    }

    public void setDt_pervich_obsled(String dt_pervich_obsled) {
        this.dt_pervich_obsled = dt_pervich_obsled;
    }

    public void setPervichObsledStr(ArrayList<PervichObsledStr> pervichObsledStr) {
        this.pervichObsledStr = pervichObsledStr;
    }
    
}
