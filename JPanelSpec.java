/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class JPanelSpec extends JPanel{
    private SprSpec sprSpec;
    
    public JPanelSpec(SprSpec sprSpec){
        super();
        this.sprSpec = sprSpec;
    }

    public SprSpec getSprSpec() {
        return sprSpec;
    }

    public void setSprSpec(SprSpec sprSpec) {
        this.sprSpec = sprSpec;
    }
    
    
}
