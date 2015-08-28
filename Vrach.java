
package stomatology;

import java.util.ArrayList;

/**
 *
 * @author EvgenyVart
 */
public class Vrach {
    private int id;
    private String sqlLogin;
    private String fio;
    private String fam;
    private String imya;
    private String otch;
    private String tel;
    private String dtRojd;
    private int idRole;
    private String psw;
    
    private ArrayList<SprSpec> specVrach;
    
    
    public Vrach(int id, String sqlLogin, String fio,int idRole){
        this.id = id;
        this.fio = fio;
        this.sqlLogin = sqlLogin;
        this.idRole = idRole;
    }
    
    public Vrach(int id,String sqlLogin,String fam,String imya,String otch,String tel,String dtRojd, int idRole, String psw){
    this.id = id;
    this.sqlLogin = sqlLogin;
    this.fam = fam;
    this.imya = imya;
    this.otch = otch;
    this.tel = tel;
    this.dtRojd  = dtRojd;
    this.idRole = idRole;
    this.psw = psw;
    }

    public ArrayList<SprSpec> getSpecVrach() {
        return specVrach;
    }

    public void setSpecVrach(ArrayList<SprSpec> specVrach) {
        this.specVrach = specVrach;
    }
    
    
    
    
    public String getFIO (Vrach vrach){
        return vrach.fio;
    }

    public int getId() {
        return id;
    }

    public String getSqlLogin() {
        return sqlLogin;
    }   

    public String getFio() {
        return fio;
    }

    public String getFam() {
        return fam;
    }

    public String getImya() {
        return imya;
    }

    public String getOtch() {
        return otch;
    }

    public String getTel() {
        return tel;
    }

    public String getDtRojd() {
        return dtRojd;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getPsw() {
        return psw;
    }
    
    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setSqlLogin(String sqlLogin) {
        this.sqlLogin = sqlLogin;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public void setImya(String imya) {
        this.imya = imya;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDtRojd(String dtRojd) {
        this.dtRojd = dtRojd;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
       
}
