/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import com.michaelbaranov.microba.calendar.CalendarPane;
import com.michaelbaranov.microba.calendar.DatePicker;
import com.michaelbaranov.microba.gradienteditor.GradientEditor;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Asus
 */
public class PoseshVremClientPerenos extends javax.swing.JDialog {

    DatePicker datePicker;
    /**
     * Creates new form PoseshVremClientPrishel
     */
    public PoseshVremClientPerenos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pan.add("DatePicker", buildDatePickerTab());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        prim = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        butSave = new javax.swing.JButton();
        butCancel = new javax.swing.JButton();
        pan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        prim.setColumns(20);
        prim.setRows(5);
        jScrollPane1.setViewportView(prim);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Причина переноса");

        butSave.setText("Сохранить");
        butSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSaveActionPerformed(evt);
            }
        });

        butCancel.setText("Отмена");
        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        pan.setLayout(new javax.swing.BoxLayout(pan, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Дата следующего визита");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butSave, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(butCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(pan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSaveActionPerformed
        
    Calendar calendar = Calendar.getInstance();
    calendar.setTime (datePicker.getDate()); // Date 
    Calendar calNow = Calendar.getInstance();
    calNow.setTime(new Date());
    String dt = "";
    
     if (calNow.get(Calendar.YEAR) >= calendar.get(Calendar.YEAR)) {
                if ((calNow.get(Calendar.MONTH) >= calendar.get(Calendar.MONTH))) {
                    if (calNow.get(Calendar.DAY_OF_MONTH) > calendar.get(Calendar.DAY_OF_MONTH)) {
                        JOptionPane.showMessageDialog(null, "Дата назначения следующего визита не должна быть раньше текущей даты", "Внимание", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                            dt = Methods.stringToDate(calendar.get (Calendar.DAY_OF_MONTH),calendar.get (Calendar.MONTH)+1,calendar.get (Calendar.YEAR));
                            Start.sDB.perenosPoseshStrVrem(SpisVremEdit.strVrem, dt, prim.getText());
                            SpisVremEdit.butAction = 1;
                            dispose();
                    }
                }
            }
    }//GEN-LAST:event_butSaveActionPerformed

    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
           int reply = JOptionPane.showConfirmDialog(null, "Изменения будут потеряны", "Внимание", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION){
               dispose();
            }        // TODO add your handling code here:
    }//GEN-LAST:event_butCancelActionPerformed

    
    
private JComponent buildDatePickerTab() {
		JPanel panel = new JPanel();
		datePicker = new DatePicker();
		// datePicker.setDateFormat(new SimpleDateFormat("HH dd yyyy"));
		datePicker.setDateFormat(SimpleDateFormat.getDateTimeInstance());
		// datePicker.setStripTime(false);
		datePicker.setEnabled(false);
		datePicker.setKeepTime(true);
		datePicker.setStripTime(false);
		datePicker.setShowNumberOfWeek(true);
		// datePicker.setEnabled(false);
		// datePicker.setPickerStyle(DatePicker.PICKER_STYLE_BUTTON);
		// datePicker.showButtonOnly(false);
		// datePicker.setToolTipText("hello!!!!");
		// datePicker.setShowNumberOfWeek(true);
		
		Map ov = new HashMap();

		ov.put(CalendarPane.COLOR_CALENDAR_GRID_FOREGROUND_ENABLED,
				Color.BLUE);
		
		datePicker.setColorOverrideMap(ov);

		panel.setLayout(new GridBagLayout());
		panel.add(datePicker, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 5, 5), 0, 0));

		datePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("DatePicker:" + datePicker.getDate());

			}
		});

		return panel;

	}

	private static class ColorAdaptor implements ChangeListener,
			ListSelectionListener {

		private GradientEditor editor;

		private JColorChooser chooser;

		public ColorAdaptor(GradientEditor editor, JColorChooser chooser) {
			super();
			this.editor = editor;
			this.chooser = chooser;

			editor.getColorSelectionModel().addListSelectionListener(this);
			chooser.getSelectionModel().addChangeListener(this);
		}

		public void valueChanged(ListSelectionEvent e) {
			int index = editor.getColorSelectionModel().getLeadSelectionIndex();
			// System.out.println(index);
			// System.out.println(e.getFirstIndex());
			// System.out.println(e.getLastIndex());
			// System.out.println("-------");

			Color c = (Color) editor.getDataModel().getValueAt(index,
					editor.getColorColumn());
			chooser.setColor(c);
		}

		public void stateChanged(ChangeEvent e) {

			if (!editor.getColorSelectionModel().isSelectionEmpty()) {
				int index = editor.getColorSelectionModel()
						.getLeadSelectionIndex();

				editor.getDataModel().setValueAt(chooser.getColor(), index,
						editor.getColorColumn());
			}

		}

	}
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PoseshVremClientPerenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PoseshVremClientPerenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PoseshVremClientPerenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PoseshVremClientPerenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PoseshVremClientPerenos dialog = new PoseshVremClientPerenos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan;
    private javax.swing.JTextArea prim;
    // End of variables declaration//GEN-END:variables
}
