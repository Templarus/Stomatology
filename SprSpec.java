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
public class SprSpec {
    private int id;
    private String nameSpec;
    private boolean select; //выбранные записи
    private int statusRec; //Статус записи
    
    public SprSpec(int id, String nameSpec, int statusRec){
        this.id = id;
        this.nameSpec = nameSpec;
        this.statusRec = statusRec;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getStatusRec() {
        return statusRec;
    }

    public void setStatusRec(int statusRec) {
        this.statusRec = statusRec;
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
    
    
    
    
}
