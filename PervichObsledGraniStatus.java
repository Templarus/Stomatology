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
public class PervichObsledGraniStatus {
    
    private int idPervich; //Код выбранной работы
    private int idZonaZub;
    private boolean flStatus;
    private String prim;
    private Integer[] color;

    public PervichObsledGraniStatus(int idPervich, int idZonaZub, String prim, Integer[] color) {
        this.idPervich = idPervich;
        this.idZonaZub = idZonaZub;
        this.prim = prim;
        this.color = color;
    }

    public int getIdPervich() {
        return idPervich;
    }

    public void setIdPervich(int idPervich) {
        this.idPervich = idPervich;
    }

    public int getIdZonaZub() {
        return idZonaZub;
    }

    public void setIdZonaZub(int idZonaZub) {
        this.idZonaZub = idZonaZub;
    }

    public boolean isFlStatus() {
        return flStatus;
    }

    public void setFlStatus(boolean flStatus) {
        this.flStatus = flStatus;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public Integer[] getColor() {
        return color;
    }

    public void setColor(Integer[] color) {
        this.color = color;
    }
    
    public String getColorString(){
        StringBuilder st = new StringBuilder();
        for(Integer c : color){
            st.append(c.toString());
        }
        return st.toString();
    }
    
    
    
    
    
    
    
    
    
    
}
