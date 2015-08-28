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
public class SprRab {
    private int idRab;
    private int idKategRab;
    private String nameRab;
    private String colorStr;
    private Integer[] color;
    private boolean flVrem;
    private boolean flChastVyp;
    
    public SprRab(int idRab,int idKategRab,String nameRab,String colorStr){
        this.idRab = idRab;
        this.idKategRab = idKategRab;
        this.nameRab = nameRab;
        this.colorStr = colorStr;
    }

    public SprRab(int idRab, int idKategRab, String nameRab, String colorStr, boolean flVrem, boolean flChastVyp) {
        this.idRab = idRab;
        this.idKategRab = idKategRab;
        this.nameRab = nameRab;
        this.colorStr = colorStr;
        this.flVrem = flVrem;
        this.flChastVyp = flChastVyp;
    }

    public Integer[] getColor() {
        return color;
    }

    public void setColor(Integer[] color) {
        this.color = color;
    }

    public boolean isFlVrem() {
        return flVrem;
    }

    public void setFlVrem(boolean flVrem) {
        this.flVrem = flVrem;
    }

    public boolean isFlChastVyp() {
        return flChastVyp;
    }

    public void setFlChastVyp(boolean flChastVyp) {
        this.flChastVyp = flChastVyp;
    }

       
    public int getIdRab() {
        return idRab;
    }

    public void setIdRab(int idRab) {
        this.idRab = idRab;
    }

    public int getIdKategRab() {
        return idKategRab;
    }

    public void setIdKategRab(int idKategRab) {
        this.idKategRab = idKategRab;
    }

    public String getNameRab() {
        return nameRab;
    }

    public void setNameRab(String nameRab) {
        this.nameRab = nameRab;
    }

    public String getColorStr() {
        return colorStr;
    }

    public void setColorStr(String colorStr) {
        this.colorStr = colorStr;
    }

    @Override
    public String toString() {
        return nameRab; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
