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
public class SprPervich {
    private int id;
    private String kodKn;
    private String opis;
    private boolean flSanac;
    private int idKateg;
    private String nameKateg;
    private boolean flRedactir = false;
    private Integer[] color;
    private String nameIcomSmall;
    private String nameIcomBig;
    private String colorString;
    
    
    public SprPervich(String kodKn, String opis, int idKateg) {
        this.kodKn = kodKn;
        this.opis = opis;
        this.idKateg = idKateg;
    }
    
    public SprPervich(int id, String kodKn, String opis){
        this.id = id;
        this.kodKn = kodKn;
        this.opis = opis;
    }
    
    public SprPervich(int id, String kodKn, String opis, boolean flSanac, int idKateg, String nameKateg, boolean flRedactir) {
        this.id = id;
        this.kodKn = kodKn;
        this.opis = opis;
        this.flSanac = flSanac;
        this.idKateg = idKateg;
        this.nameKateg = nameKateg;
        this.flRedactir = flRedactir;
    }
    
    public SprPervich(int id, String kodKn, String opis, boolean flSanac, int idKateg, String nameKateg, boolean flRedactir, Integer[] color) {
        this.id = id;
        this.kodKn = kodKn;
        this.opis = opis;
        this.flSanac = flSanac;
        this.idKateg = idKateg;
        this.nameKateg = nameKateg;
        this.flRedactir = flRedactir;
        this.color = color;
    }

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }

    
    
    
    public String getNameIcomSmall() {
        return nameIcomSmall;
    }

    public void setNameIcomSmall(String nameIcomSmall) {
        this.nameIcomSmall = nameIcomSmall;
    }

    public String getNameIcomBig() {
        return nameIcomBig;
    }

    public void setNameIcomBig(String nameIcomBig) {
        this.nameIcomBig = nameIcomBig;
    }
    
    
    
    public boolean isFlSanac() {
        return flSanac;
    }

    public void setFlSanac(boolean flSanac) {
        this.flSanac = flSanac;
    }

    public int getIdKateg() {
        return idKateg;
    }

    public void setIdKateg(int idKateg) {
        this.idKateg = idKateg;
    }

    public String getNameKateg() {
        return nameKateg;
    }

    public void setNameKateg(String nameKateg) {
        this.nameKateg = nameKateg;
    }

    public boolean isFlRedactir() {
        return flRedactir;
    }

    public void setFlRedactir(boolean flRedactir) {
        this.flRedactir = flRedactir;
    }

    public Integer[] getColor() {
        return color;
    }

    public void setColor(Integer[] color) {
        this.color = color;
    }
    
    
    


    public int getId() {
        return id;
    }

    public String getKodKn() {
        return kodKn;
    }

    public String getOpis() {
        return opis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKodKn(String kodKn) {
        this.kodKn = kodKn;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}
