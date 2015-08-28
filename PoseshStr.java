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
public class PoseshStr 
{
    private int idPosesh;
    private int idZub;
    
    ArrayList<PoseshStrRabZub> poseshRabZub;
    

    
    public PoseshStr(int idPosesh, int idZub){
        this.idPosesh = idPosesh;
        this.idZub = idZub;

    }
    
    public int getIdPosesh() {
        return idPosesh;
    }

    public void setIdPosesh(int idPosesh) {
        this.idPosesh = idPosesh;
    }

    public int getIdZub() {
        return idZub;
    }

    public void setIdZub(int idZub) {
        this.idZub = idZub;
    }


    public ArrayList<PoseshStrRabZub> getPoseshRabZub() {
        return poseshRabZub;
    }

    public void setPoseshRabZub(ArrayList<PoseshStrRabZub> poseshRabZub) {
        this.poseshRabZub = poseshRabZub;
    }


    
    
    
          
    
    
    
}
