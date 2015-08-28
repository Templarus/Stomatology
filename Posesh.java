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
public class Posesh {
    private int idPosesh;
    private String dtPosesh;
    private int idVrach;
    private int idClient;
    private int idFilial;
    
    private ArrayList<PoseshStr> poseshStr;
    private ArrayList<PoseshStrVrem> poseshStrVrem;
    private ArrayList<PoseshSanac> poseshSanac;
    
    public Posesh ( int idPosesh,String dtPosesh,int idVrach,int idClient,int idFilail){
       this.idPosesh = idPosesh;
       this.dtPosesh = dtPosesh;
       this.idVrach = idVrach;
       this.idClient = idClient;
       this.idFilial = idFilail;
    }

   
    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public ArrayList<PoseshSanac> getPoseshSanac() {
        return poseshSanac;
    }

    public void setPoseshSanac(ArrayList<PoseshSanac> poseshSanac) {
        this.poseshSanac = poseshSanac;
    }

    
    
    
    
    public int getIdPosesh() {
        return idPosesh;
    }

    public void setIdPosesh(int idPosesh) {
        this.idPosesh = idPosesh;
    }

    public String getDtPosesh() {
        return dtPosesh;
    }

    public void setDtPosesh(String dtPosesh) {
        this.dtPosesh = dtPosesh;
    }

    public int getIdVrach() {
        return idVrach;
    }

    public void setIdVrach(int idVrach) {
        this.idVrach = idVrach;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public ArrayList<PoseshStr> getPoseshStr() {
        return poseshStr;
    }

    public void setPoseshStr(ArrayList<PoseshStr> poseshStr) {
        this.poseshStr = poseshStr;
    }

    public ArrayList<PoseshStrVrem> getPoseshStrVrem() {
        return poseshStrVrem;
    }

    public void setPoseshStrVrem(ArrayList<PoseshStrVrem> poseshStrVrem) {
        this.poseshStrVrem = poseshStrVrem;
    }
    
    
    
    
    
}
