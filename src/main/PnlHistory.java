package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import people.WinnerList;

public class PnlHistory extends JPanel {
	private JPanel panel_2;
	private JPanel panel_3;
	
	private JTextField tfName;
	
	private DefaultTableModel dtm;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JButton btnSearch;
	
	private ArrayList<WinnerList.Winner> list;
	private JButton btnReload;

	public PnlHistory() {
		setLayout(new BorderLayout(0, 20));
		initObjects();
		initCoreObjects();
		initEvents();
	}

	private void initButtonsEvents() {
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(tfName.getText().equals(""))
					return;
				dtm.setRowCount(0);
				String name = tfName.getText();
				for(WinnerList.Winner temp : list)
					if(name.equals(temp.getName())) {
						dtm.addRow(temp.getObject());
					}
			}
		});
		
		btnReload.addActionListener(
				ae -> {
					dtm.setRowCount(0);
					tfName.setText("");
					reloadTable();
				}
		);
		
		
	}
	
	private void initEvents() {
		initButtonsEvents();
	}
	
	private void initTextFields() {
		tfName = new JTextField();
		tfName.setColumns(15);
		
		//////////////////////////////
		panel_2.add(tfName);
	}
	
	private void initButtons() {	
		btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.CYAN);
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnReload = new JButton("Reload");
		btnReload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReload.setBackground(Color.GREEN);
		
		/////////////////////////////////////////////////////////////////
		panel_2.add(btnSearch);
		panel_2.add(btnReload);
	}
	
	@SuppressWarnings("serial")
	private void initTables() {
		dtm = new DefaultTableModel(null, new Object[] {"Lottery No.", "Name", "Phone Number", "Prize"}) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);
		scrollPane.setMaximumSize(new Dimension(327, 327));
		scrollPane.setSize(100, 100);

		reloadTable();
		///////////////////////////////////////
		panel_3.add(scrollPane);
	}

	private void reloadTable() {
		list = WinnerList.getList();
		for(int i = 0; i < list.size(); i++) {
			dtm.addRow(list.get(i).getObject());
			if((i+1) % 3 == 0 && (i+1) != list.size())
				dtm.addRow(new Object[] {"", "", "", ""});			
		}
	}
	
	private void initCoreObjects() {
		initTextFields();
		initButtons();
		initTables();
	}
	
	private void initObjects() {
		JLabel lblHistory = new JLabel("History");
		lblHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistory.setFont(new Font("Rockwell", Font.PLAIN, 50));
		add(lblHistory, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(20);
		panel.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblSearchByName = new JLabel("Search by Name :");
		panel_2.add(lblSearchByName);

		panel_3 = new JPanel();
		panel_3.setLayout(new BorderLayout(0, 0));
		panel.add(panel_3, BorderLayout.CENTER);
		
		JLabel label = new JLabel("     ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(label, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("                    ");
		panel_3.add(label_1, BorderLayout.EAST);
		
		JLabel label_2 = new JLabel("                    ");
		panel_3.add(label_2, BorderLayout.WEST);
	}

}
