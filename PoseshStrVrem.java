/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

/**
 *
 * @author Asus
 */
public class PoseshStrVrem {
private int idPosesh;
private int idZub;
private int idStrVrem;
private String dtSledVisist;
private boolean flObrab;
private String dtObrab;
private String prim;
private String dtRabot;
private String clientFIO;

public PoseshStrVrem(int idPosesh,int idZub,int idStrVrem,String dtSledVisist,boolean flObrab,String dtObrab,String prim){
    this.idPosesh = idPosesh;
    this.idZub = idZub;
    this.idStrVrem = idStrVrem;
    this.dtSledVisist = dtSledVisist;
    this.flObrab = flObrab;
    this.dtObrab = dtObrab;
    this.prim = prim;
}

    public String getClientFIO() {
        return clientFIO;
    }

    public void setClientFIO(String clientFIO) {
        this.clientFIO = clientFIO;
    }

    
    
public String getDtRabot() {
        return dtRabot;
    }

    public void setDtRabot(String dtRabot) {
        this.dtRabot = dtRabot;
    }

    

    public int getIdPosesh() {
        return idPosesh;
    }

    public void setIdPosesh(int idPosesh) {
        this.idPosesh = idPosesh;
    }

    public int getIdStrVrem() {
        return idStrVrem;
    }

    public void setIdStrVrem(int idStrVrem) {
        this.idStrVrem = idStrVrem;
    }

    public String getDtSledVisist() {
        return dtSledVisist;
    }

    public void setDtSledVisist(String dtSledVisist) {
        this.dtSledVisist = dtSledVisist;
    }

    public boolean isFlObrab() {
        return flObrab;
    }

    public void setFlObrab(boolean flObrab) {
        this.flObrab = flObrab;
    }

    public String getDtObrab() {
        return dtObrab;
    }

    public void setDtObrab(String dtObrab) {
        this.dtObrab = dtObrab;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public int getIdZub() {
        return idZub;
    }

    public void setIdZub(int idZub) {
        this.idZub = idZub;
    }
  
}
