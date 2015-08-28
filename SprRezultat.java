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
public class SprRezultat {

    private int idRezultat;
    private String nameRezultat;

    public SprRezultat(int idRezultat, String nameRezultat) {
        this.idRezultat = idRezultat;
        this.nameRezultat = nameRezultat;
    }

    public int getIdRezultat() {
        return idRezultat;
    }

    public void setIdRezultat(int idRezultat) {
        this.idRezultat = idRezultat;
    }

    public String getNameRezultat() {
        return nameRezultat;
    }

    public void setNameRezultat(String nameRezultat) {
        this.nameRezultat = nameRezultat;
    }

    @Override
    public String toString() {
        return nameRezultat; //To change body of generated methods, choose Tools | Templates.
    }

}
