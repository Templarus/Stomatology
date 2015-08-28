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
public class JCheckBoxSpec extends JCheckBox{
    private int id;
    private String nameSpec;
    private boolean select;
    
    public JCheckBoxSpec(int id, String nameSpec, boolean select){
        super(nameSpec);
        this.id = id;
        this.nameSpec = nameSpec;
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSpec() {
        return nameSpec;
    }

    public void setNameSpec(String nameSpec) {
        this.nameSpec = nameSpec;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
    
    @Override
    public String toString(){
      return nameSpec;
    }
}
