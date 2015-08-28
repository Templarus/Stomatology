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
public class PervichObsledZubStatus {
    private int idSprPervich;
    private boolean statusBoolean;
    private String prim = "";
   
    public PervichObsledZubStatus (int idSprPervich,boolean statusBoolean){
        this.idSprPervich = idSprPervich;
        this.statusBoolean = statusBoolean;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    

    public int getIdSprPervich() {
        return idSprPervich;
    }

    public boolean isStatusBoolean() {
        return statusBoolean;
    }


    public void setIdSprPervich(int idSprPervich) {
        this.idSprPervich = idSprPervich;
    }

    public void setStatusBoolean(boolean statusBoolean) {
        this.statusBoolean = statusBoolean;
    }
    

    
    
    
}
