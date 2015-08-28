
package stomatology;

import java.util.ArrayList;

public class PervichObsledStr {
    
    private int id_pervich_obsled;
    private int id_zub;
    private ArrayList<PervichObsledGraniStatus> statGranColor;
    private ArrayList<PervichObsledZubStatus> status;
    private ArrayList<PervichObsledDesnaStatus> statDesna;
    
    PervichObsledStr(int id_pervich_obsled,int id_zub) {
    this.id_pervich_obsled = id_pervich_obsled;
    this.id_zub = id_zub;
    }

    public ArrayList<PervichObsledGraniStatus> getStatGranColor() {
        return statGranColor;
    }

    public void setStatGranColor(ArrayList<PervichObsledGraniStatus> statGranColor) {
        this.statGranColor = statGranColor;
    }

    public ArrayList<PervichObsledDesnaStatus> getStatDesna() {
        return statDesna;
    }

    public void setStatDesna(ArrayList<PervichObsledDesnaStatus> statDesna) {
        this.statDesna = statDesna;
    }

    
    
    public int getId_pervich_obsled() {
        return id_pervich_obsled;
    }

    public  ArrayList<PervichObsledZubStatus> getStatus() {
        return status;
    }

//    public int getId_pervich() {
//        return id_pervich;
//    }
    
    

    public int getId_zub() {
        return id_zub;
    }

    public void setId_pervich_obsled(int id_pervich_obsled) {
        this.id_pervich_obsled = id_pervich_obsled;
    }

    public void setStatus(ArrayList<PervichObsledZubStatus> status) {
        this.status = status;
        //this.status.addAll(status);
        //this.status = status;
    }
    public void setStatusUpdate(ArrayList<PervichObsledZubStatus> status) {
        //for(PervichObsledZubStatus stat : statusZub){
           // System.out.println("statusZub&%^ " + stat.isStatusBoolean());
       // }
        


//this.status.removeAll(this.status);
        //this.status.addAll(status);
        //this.status = status;
    }
    

    public void setId_zub(int id_zub) {
        this.id_zub = id_zub;
    }

//    public void setId_pervich(int id_pervich) {
//        this.id_pervich = id_pervich;
//    }

    
}
