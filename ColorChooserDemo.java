/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import stomatology.spravochniki.SprPervichEdit;

/* ColorChooserDemo.java requires no other files. */
public class ColorChooserDemo extends JPanel
                              implements ChangeListener {

    protected JColorChooser tcc;
    protected JLabel banner;
    private static JDialog frame = null;
    public ColorChooserDemo() {
        super(new BorderLayout());

        //Set up the banner at the top of the window
        banner = new JLabel("Выбор цвета",
                            JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        JButton butChoose = new JButton("Подтвердить выбор");
        butChoose.addActionListener(
        new ActionListener() {
                @Override
    public void actionPerformed(ActionEvent ae) {
                    StringBuilder sb = new StringBuilder();
                    sb = sb.append(tcc.getColor().getRed()).append(',').append(tcc.getColor().getGreen()).append(',').append(tcc.getColor().getBlue());
                    SprPervichEdit.colorField.setText(sb.toString());
                    frame.dispose();
                    //System.exit(0);
                    //System.out.println("sb.toString()" + sb.toString());
                    //System.out.println("tcc.getColor().getRGB()" + tcc.getColor().getRed());
       }
                        
                });
        bannerPanel.add(butChoose,BorderLayout.WEST);
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));

        //Set up color chooser for setting text color
        tcc = new JColorChooser(banner.getForeground());
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Text Color"));

        add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }

    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        banner.setForeground(newColor);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        
        frame = new JDialog(frame,true);
        
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooserDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static Integer[] colorRGB() {
        Integer[] colorRGB = null;
        return colorRGB;
    }
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}