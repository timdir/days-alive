package core;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GUI implements ActionListener {

	JComboBox cbY,cbM,cbD;

	String[] birthdayYear;
	String[] birthdayMonth = {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String[] birthdayDay = {"Day","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};


	GUI() {
		birthdayYear = CalculateDays.fillYearsJComboBox();
	}


	public void addComponentToPane(Container pane) {

		JPanel mainPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel emptyTopPanel = new JPanel();

		middlePanel.setLayout(new FlowLayout());
		mainPanel.setLayout(new BorderLayout());
		
		emptyTopPanel.setPreferredSize(new Dimension(0,15));

		cbY = new JComboBox(birthdayYear);    
		cbM = new JComboBox(birthdayMonth);    
		cbD = new JComboBox(birthdayDay);    

		
		cbY.setActionCommand("cbY");
		cbM.setActionCommand("cbM");
		cbD.setActionCommand("cbD");

		
		cbY.addActionListener(this);
		cbM.addActionListener(this);
		cbD.addActionListener(this);

		JButton button = new JButton("Click me!");
		button.setActionCommand("button");
		button.addActionListener(this);

		buttonPanel.add(button);

		middlePanel.add(cbY);
		middlePanel.add(cbM);
		middlePanel.add(cbD);
		
		pane.setLayout(new BorderLayout());
		mainPanel.add(middlePanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		pane.add(emptyTopPanel, BorderLayout.NORTH);
		pane.add(mainPanel, BorderLayout.CENTER);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedYear = String.valueOf(cbY.getSelectedItem());
		String selectedMonth = String.valueOf(cbM.getSelectedItem());
		String selectedDay = String.valueOf(cbD.getSelectedItem());

		if ( "button".equals(e.getActionCommand()) ) {
			if ( "Year".equals(selectedYear) ||
					"Month".equals(selectedMonth) ||
					"Day".equals(selectedDay)) {
				JOptionPane.showMessageDialog( new JFrame(),
						"Oops!  Please enter a date.");
				return;
			}
			int days = CalculateDays.calculateDays(
					selectedYear, selectedMonth, selectedDay);
			if ( days == -1 ) {
				JOptionPane.showMessageDialog( new JFrame(),
						"So you are a time travelling newborn?\n"
						+ "I don't think so..." );
				return;
			}
			else {
				JOptionPane.showMessageDialog( new JFrame(),
						"You've been alive for " + days + " days!");
				return;
			}
		}

		String tmpD = (String) cbD.getSelectedItem();
		cbD.setSelectedItem("Day");
		cbD.removeItem("29");
		cbD.removeItem("30");
		cbD.removeItem("31");
		cbD.addItem("29");
		cbD.addItem("30");
		cbD.addItem("31");
		cbD.setSelectedItem(tmpD);

		if ( "Year".equals(String.valueOf(cbY.getSelectedItem())) ) {

			if ( !"Month".equals(String.valueOf(cbM.getSelectedItem())) ) {
				switch (selectedMonth) {
				case "February" : {
					cbD.removeItem("29");
					cbD.removeItem("30");
					cbD.removeItem("31"); break;}
				case "April" : 
				case "June" :
				case "September" :
				case "November" : {
					cbD.removeItem("31"); break;}
				}
			}
		}

		else {

			if ( !"Month".equals(String.valueOf(cbM.getSelectedItem())) ) {
				switch (selectedMonth) {
				case "February" : {
					if ( Integer.parseInt(selectedYear) % 4 != 0 ) {
						cbD.removeItem("29");
					}
					cbD.removeItem("30");
					cbD.removeItem("31"); break;}
				case "April" : 
				case "June" :
				case "September" :
				case "November" : {
					cbD.removeItem("31"); break;}
				}
			}			
		}
	}
}




