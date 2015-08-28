/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stomatology;

import com.michaelbaranov.microba.calendar.CalendarPane;
import com.michaelbaranov.microba.calendar.DatePicker;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
/**
 *
 * @author Asus
 */
public class Methods {
    
    public static String stringToDate (int day, int month, int year){
        Integer monthI = new Integer(month);
        Integer dayI = new Integer(day);
        
        String dayStr;
        String monthStr;
        monthStr = (month < 10) ? "0" + monthI.toString() :  monthI.toString();
        dayStr = (day < 10) ? "0" + dayI.toString() : dayI.toString();
        
//        System.out.println(dayStr);
//        System.out.println(monthStr);
        
//        Integer months = null;
//        Integer days = null;
//
//        try{
//           months = (month < 10) ? new Integer("0" + monthI.toString()) : new Integer(month);
//        }catch (NumberFormatException ex){
//            System.out.println("Methods:stringToDate: Ошибка привеления String к Integer(Месяц)");
//        }
//        try{
//         days = (day < 10) ? new Integer("0" + dayI.toString()) : new Integer(day); 
//        }catch (NumberFormatException ex){
//            System.out.println("Methods:stringToDate: Ошибка привеления String к Integer(День)");
//        }

        StringBuilder stringDate = new StringBuilder();
        stringDate.append(year).append(monthStr).append(dayStr);
        return stringDate.toString();
    }
    
    public static int getCurrentYear()
    {
        Calendar calendar = Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
    
    
    private JComponent buildDatePickerTab() {
		JPanel panel = new JPanel();
		final DatePicker datePicker = new DatePicker();
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
				Color.ORANGE);
		
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
    
    
}
