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
public class Perenaprav {
    private int idPerenaprav;
    private int idClient;
    private int idVrachOtprav;
    private int idVrachPoluch;
    private int idSpecVrach;
    private String dtOtprav;
    private String dtPoluch;
    private boolean flOtm;
    private String prim;

    public Perenaprav(int idClient, int idVrachOtprav, int idVrachPoluch, int idSpecVrach, String dtOtprav, String dtPoluch, boolean flOtm,String prim,int idPerenaprav) {
        this.idClient = idClient;
        this.idVrachOtprav = idVrachOtprav;
        this.idVrachPoluch = idVrachPoluch;
        this.idSpecVrach = idSpecVrach;
        this.dtOtprav = dtOtprav;
        this.dtPoluch = dtPoluch;
        this.flOtm = flOtm;
        this.prim = prim;
        this.idPerenaprav = idPerenaprav;
    }

    public int getIdPerenaprav() {
        return idPerenaprav;
    }

    public void setIdPerenaprav(int idPerenaprav) {
        this.idPerenaprav = idPerenaprav;
    }

    
    
    
    
    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

        
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdVrachOtprav() {
        return idVrachOtprav;
    }

    public void setIdVrachOtprav(int idVrachOtprav) {
        this.idVrachOtprav = idVrachOtprav;
    }

    public int getIdVrachPoluch() {
        return idVrachPoluch;
    }

    public void setIdVrachPoluch(int idVrachPoluch) {
        this.idVrachPoluch = idVrachPoluch;
    }

    public int getIdSpecVrach() {
        return idSpecVrach;
    }

    public void setIdSpecVrach(int idSpecVrach) {
        this.idSpecVrach = idSpecVrach;
    }

    public String getDtOtprav() {
        return dtOtprav;
    }

    public void setDtOtprav(String dtOtprav) {
        this.dtOtprav = dtOtprav;
    }

    public String getDtPoluch() {
        return dtPoluch;
    }

    public void setDtPoluch(String dtPoluch) {
        this.dtPoluch = dtPoluch;
    }

    public boolean isFlOtm() {
        return flOtm;
    }

    public void setFlOtm(boolean flOtm) {
        this.flOtm = flOtm;
    }
    
    
    
    
    
    
}
