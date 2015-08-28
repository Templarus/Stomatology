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
public class SprKategRab {
    private int idKategRab;
    private String nameKategRab;

    public SprKategRab(int idKategRab, String nameKategRab) {
        this.idKategRab = idKategRab;
        this.nameKategRab = nameKategRab;
    }

    public int getIdKategRab() {
        return idKategRab;
    }

    public void setIdKategRab(int idKategRab) {
        this.idKategRab = idKategRab;
    }

    public String getNameKategRab() {
        return nameKategRab;
    }

    public void setNameKategRab(String nameKategRab) {
        this.nameKategRab = nameKategRab;
    }

    @Override
    public String toString() {
        return nameKategRab;
    }
    
    
    
}
