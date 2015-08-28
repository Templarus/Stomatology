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
public class PoseshSanac {
    
    private int idPosesh;
    private int idZub;
    private String dtSanac;
    private int idSanac;
    
    public PoseshSanac(int idPosesh, int idZub, String dtSanac,int idSanac) {
        this.idPosesh = idPosesh;
        this.idZub = idZub;
        this.dtSanac = dtSanac;
        this.idSanac = idSanac;
    }

    public int getIdSanac() {
        return idSanac;
    }

    public void setIdSanac(int idSanac) {
        this.idSanac = idSanac;
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

    public String getDtSanac() {
        return dtSanac;
    }

    public void setDtSanac(String dtSanac) {
        this.dtSanac = dtSanac;
    }
    
    
    
}
