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
public class IsklRabStr {
    private int id;
    private String rab;
        
    public IsklRabStr (int id, String rab){
        this.id = id;
        this.rab = rab;
    }

    public int getId() {
        return id;
    }

    public String getRab() {
        return rab;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRab(String rab) {
        this.rab = rab;
    }
    
    
    
}
