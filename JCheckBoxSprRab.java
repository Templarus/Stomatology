/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import javax.swing.JCheckBox;

/**
 *
 * @author Asus
 */
public class JCheckBoxSprRab extends JCheckBox{
    private int id;
    private String nameRab;
    private boolean select;
    private int KategRab;
    
    public JCheckBoxSprRab(int id, String nameRab, boolean select, int KategRab){
        super(nameRab);
        this.id = id;
        this.nameRab = nameRab;
        this.select = select;
        this.KategRab = KategRab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    
    @Override
    public String toString(){
      return nameRab;
    }

    public String getNameRab() {
        return nameRab;
    }

    public void setNameRab(String nameRab) {
        this.nameRab = nameRab;
    }

    public int getKategRab() {
        return KategRab;
    }

    public void setKategRab(int KategRab) {
        this.KategRab = KategRab;
    }
    
    
    
    
}
