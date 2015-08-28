/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.util.ArrayList;
import javafx.beans.binding.StringBinding;

/**
 *
 * @author Asus
 */
public class PoseshStrRabZub {
    
    private int idRab;
    private int idZonaZub;
    private String prim = "";
    private int idPoseshStr;
    private int idRezult;
    private Integer[] color; 
    
    ArrayList<PoseshStrVrem> poseshStrVrem;

    public PoseshStrRabZub(int idRab, int idZonaZub, int idPoseshStr, int idRezult, Integer[] color) {
        this.idRab = idRab;
        this.idZonaZub = idZonaZub;
        this.idPoseshStr = idPoseshStr;
        this.idRezult = idRezult;
        this.color = color;
    }

    public PoseshStrRabZub(int idZonaZub, int idPoseshStr, int idRezult) {
        this.idZonaZub = idZonaZub;
        this.idPoseshStr = idPoseshStr;
        this.idRezult = idRezult;
    }

    public PoseshStrRabZub(int idZonaZub, int idPoseshStr, int idRezult, Integer[] color) {
        this.idZonaZub = idZonaZub;
        this.idPoseshStr = idPoseshStr;
        this.idRezult = idRezult;
        this.color = color;
    }
    
    
    public Integer[] getColor() {
        return color;
    }

    public void setColor(Integer[] color) {
        this.color = color;
    }
    
    public String getColorStr() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(color[0]).append(",").append(color[1]).append(",").append(color[2]);
        return sb.toString();
    }
    
    
    
    public int getIdRab() {
        return idRab;
    }

    public void setIdRab(int idRab) {
        this.idRab = idRab;
    }

    public int getIdZonaZub() {
        return idZonaZub;
    }

    public void setIdZonaZub(int idZonaZub) {
        this.idZonaZub = idZonaZub;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public int getIdPoseshStr() {
        return idPoseshStr;
    }

    public void setIdPoseshStr(int idPoseshStr) {
        this.idPoseshStr = idPoseshStr;
    }

    public int getIdRezult() {
        return idRezult;
    }

    public void setIdRezult(int idRezult) {
        this.idRezult = idRezult;
    }

    public ArrayList<PoseshStrVrem> getPoseshStrVrem() {
        return poseshStrVrem;
    }

    public void setPoseshStrVrem(ArrayList<PoseshStrVrem> poseshStrVrem) {
        this.poseshStrVrem = poseshStrVrem;
    }
    
    
    
    
    
}
