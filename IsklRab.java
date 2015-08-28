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
public class IsklRab {
    private int id;
    private String rab;
    private ArrayList<IsklRabStr> isklRabStr;
    
    
    public IsklRab (int id, String rab){
        this.id = id;
        this.rab = rab;
    }

    public int getId() {
        return id;
    }

    public String getRab() {
        return rab;
    }

    public ArrayList<IsklRabStr> getIsklRabStr() {
        return isklRabStr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRab(String rab) {
        this.rab = rab;
    }

    public void setIsklRabStr(ArrayList<IsklRabStr> isklRabStr) {
        this.isklRabStr = isklRabStr;
    }
    
    
}
