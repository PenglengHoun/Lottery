package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import people.Player;
import people.PlayerList;

public class PnlAddPlayer extends JPanel {
	
	private JPanel panel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_6;
	private JPanel panel_5;
	
	private JTextField tfName;
	private JTextField tfPhoneNumber;
	
	private DefaultTableModel dtm;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnEdit;

	public PnlAddPlayer() {
		setLayout(new BorderLayout(0, 10));	
		initObjects();
		initCoreObjects();		
		initEvents();
	}
	
	private void initButtonsEvents() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(tfName.getText().equals("") || tfPhoneNumber.getText().equals(""))
					return;
				
				Player player = Player.createPlayer(tfName.getText(), tfPhoneNumber.getText());
				PlayerList.add(player);
				dtm.addRow(player.getObject());
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int i = table.getSelectedRow();
				if(i < 0) return;
				
				PlayerList.remove(i);				
				dtm.removeRow(i);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(tfName.getText().equals("") || tfPhoneNumber.getText().equals("") || table.getRowCount() == 0)
					return;
				
				Player player = Player.createPlayer(tfName.getText(), tfPhoneNumber.getText());
				PlayerList.setPlayerAt(table.getSelectedRow(), player);			
				dtm.setValueAt(tfName.getText(), table.getSelectedRow(), 0);
				dtm.setValueAt(tfPhoneNumber.getText(), table.getSelectedRow(), 1);
			}
		});
	}
	
	private void initTablesEvents() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if(table.getSelectedRowCount() == 0) return;
				tfName.setText(dtm.getValueAt(table.getSelectedRow(), 0) + "");
				tfPhoneNumber.setText(dtm.getValueAt(table.getSelectedRow(), 1) + "");
			}
		});
	}
	
	private void initEvents() {
		initButtonsEvents();
		initTablesEvents();
	}
	
	@SuppressWarnings("serial")
	private void initTables() {
		dtm = new DefaultTableModel(null, new Object[] {"Name", "Phone Number"}) {
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);
				
		//////////////////////////////////////////////////		
		panel_6.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initTextFields() {
		tfName = new JTextField();		
		tfName.setColumns(10);	
		tfPhoneNumber = new JTextField();	
		tfPhoneNumber.setColumns(10);
		
		///////////////////////////////////////////////////
		panel_3.add(tfName);
		panel_4.add(tfPhoneNumber);
	}
	
	private void initButtons() {
		btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.CYAN);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		
		btnRemove = new JButton("Remove");
		btnRemove.setBackground(Color.GREEN);
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		
		btnEdit = new JButton("Edit");
		btnEdit.setBackground(Color.ORANGE);
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		/////////////////////////////////////////////////////////////////
		panel_5.add(btnAdd);
		panel_5.add(btnRemove);
		panel_5.add(btnEdit);
	}
	
	private void initCoreObjects() {
		initTables();
		initTextFields();
		initButtons();
	}
	
	private void initObjects() {
		JLabel lblAddPlayer = new JLabel("Add Player");
		lblAddPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPlayer.setFont(new Font("Rockwell", Font.PLAIN, 50));
		add(lblAddPlayer, BorderLayout.NORTH);
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 10));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 10, 10));
		
		panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 2, 10, 0));
		
		JLabel lblPlayersName = new JLabel("Player's Name :");
		panel_3.add(lblPlayersName);		
		
		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 2, 10, 0));
		
		JLabel lblPlayersPhoneNumber = new JLabel("Player's Phone Number :");
		panel_4.add(lblPlayersPhoneNumber);	
			
		panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		panel_6 = new JPanel();
		panel_6.setLayout(new BorderLayout(0, 0));
		panel.add(panel_6, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("          ");
		panel_6.add(lblNewLabel, BorderLayout.WEST);
		
		JLabel label = new JLabel("          ");
		panel_6.add(label, BorderLayout.EAST);
	}

}
