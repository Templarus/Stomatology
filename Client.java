/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.util.ArrayList;


public class Client{
    int id_klient;
    private int id_vrach;
    private String fam_klient = "";
    private String imya_klient = "";
    private String otch_klient = "";
    private String tel_klient = "";
    private String dt_roj_klient;
    
    private ArrayList<PervichObsled> pervichObsled;
    private ArrayList<Posesh> posesh;
    
    public Client(int id_vrach,String fam_klient,String imya_klient,String otch_klient,String tel_klient,String dt_roj_klient) {
    this.id_vrach = id_vrach;
    this.fam_klient = fam_klient;
    this.imya_klient = imya_klient;
    this.otch_klient = otch_klient;
    this.tel_klient = tel_klient;
    this.dt_roj_klient = dt_roj_klient;
    }
    
    
    public Client(int id_klient,int id_vrach,String fam_klient,String imya_klient,String otch_klient,String tel_klient,String dt_roj_klient) {
    this.id_klient = id_klient;
    this.id_vrach = id_vrach;
    this.fam_klient = fam_klient;
    this.imya_klient = imya_klient;
    this.otch_klient = otch_klient;
    this.tel_klient = tel_klient;
    this.dt_roj_klient = dt_roj_klient;
    }

    public ArrayList<PervichObsled> getPervichObsled() {
        return pervichObsled;
    }

    public ArrayList<Posesh> getPosesh() {
        return posesh;
    }

    
    
    public String getFIO_client(){
        StringBuilder st = new StringBuilder();
        st.append(fam_klient).append(" ").append(imya_klient).append(" ").append(otch_klient).trimToSize();
        return st.toString(); 
    }
    
    public int getId_klient() {
        return id_klient;
    }

    public int getId_vrach() {
        return id_vrach;
    }

    public String getFam_klient() {
        return fam_klient;
    }

    public String getImya_klient() {
        return imya_klient;
    }

    public String getOtch_klient() {
        return otch_klient;
    }

    public String getTel_klient() {
        return tel_klient;
    }

    public String getDt_roj_klient() {
        return dt_roj_klient;
    }

    public void setId_klient(int id_klient) {
        this.id_klient = id_klient;
    }

    public void setId_vrach(int id_vrach) {
        this.id_vrach = id_vrach;
    }

    public void setFam_klient(String fam_klient) {
        this.fam_klient = fam_klient;
    }

    public void setImya_klient(String imya_klient) {
        this.imya_klient = imya_klient;
    }

    public void setOtch_klient(String otch_klient) {
        this.otch_klient = otch_klient;
    }

    public void setTel_klient(String tel_klient) {
        this.tel_klient = tel_klient;
    }

    public void setDt_roj_klient(String dt_roj_klient) {
        this.dt_roj_klient = dt_roj_klient;
    }

    public void setPervichObsled(ArrayList<PervichObsled> pervichObsled) {
        this.pervichObsled = pervichObsled;
    }

    public void setPosesh(ArrayList<Posesh> posesh) {
        this.posesh = posesh;
    }
   
    
}
