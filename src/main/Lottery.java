package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import people.Player;
import people.PlayerList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Color;

public class Lottery extends JFrame {

	private JPanel panel_1;
	
	private JButton btnAddPlayer;
	private JButton btnPlay;
	private JButton btnHistory;
	
	/////////////////////////////////
	private JPanel outerMain;
	private JPanel main;
	private CardLayout cardLayout;
	
	private JPanel contentPane;
	private PnlAddPlayer pnlAddPlayer;
	private PnlPlay pnlPlay;
	private PnlHistory pnlHistory;
	private JPanel panel_2;
	private JButton btnBack;
	////////////////////////////////
	
	private static boolean isNewGame = false;
	
	////////////////////////////////

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Lottery frame = new Lottery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Lottery() {			
		initMainPanel();		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);				
		initObjects();
		initCoreObjects();
		initEvents();
	}
	
	private void initButtonsEvents() {
		btnBack.addActionListener(
				ae -> {
					cardLayout.show(main, "menu");
					btnBack.setVisible(false);
				}
		);
		
		ThreeMainButtonsEvents events = new ThreeMainButtonsEvents();
		btnAddPlayer.addActionListener(events);
		btnPlay.addActionListener(events);
		btnHistory.addActionListener(events);
	}
	
	private void initEvents() {
		initButtonsEvents();
	}
	
	private void initButtons() {
		btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setForeground(Color.BLACK);
		btnAddPlayer.setBackground(Color.CYAN);
		btnAddPlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnAddPlayer.setPreferredSize(new Dimension(300, 70));
		btnAddPlayer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		
		btnPlay = new JButton("Play");
		btnPlay.setBackground(Color.GREEN);
		btnPlay.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnPlay.setPreferredSize(new Dimension(300, 70));
		btnPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		
		btnHistory = new JButton("History");
		btnHistory.setBackground(Color.ORANGE);
		btnHistory.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		btnHistory.setPreferredSize(new Dimension(300, 70));
		btnHistory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnBack = new JButton("Back");
		btnBack.setBackground(Color.RED);
		btnBack.setVisible(false);
			
		//////////////////////////////////////////////////////////////////////
		panel_1.add(btnAddPlayer);
		panel_1.add(btnPlay);
		panel_1.add(btnHistory);
		panel_2.add(btnBack);
	}
	
	private void initMainPanel() {
		cardLayout = new CardLayout();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));	
		
		pnlAddPlayer = new PnlAddPlayer();
		pnlPlay = new PnlPlay();
		pnlHistory = new PnlHistory();

		main = new JPanel();
		main.setLayout(cardLayout);
		main.add(contentPane, "menu");
		cardLayout.show(main, "menu");
	}
	
	private void initCoreObjects() {
		initButtons();
	}
	
	private void initObjects() {
		outerMain = new JPanel();
		outerMain.setLayout(new BorderLayout());
		outerMain.add(main, BorderLayout.CENTER);
		setContentPane(outerMain);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(15, 0, 0, 0));
		outerMain.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblLottery = new JLabel("Lottery");
		lblLottery.setFont(new Font("Rockwell", Font.PLAIN, 50));
		lblLottery.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLottery, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(100);
		contentPane.add(panel, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 0, 25));
	}

	private class ThreeMainButtonsEvents implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			String name = "name";
			if(ae.getSource() == btnAddPlayer) {
				if(isNewGame) {
					pnlAddPlayer = new PnlAddPlayer();
					isNewGame = false;
				}
				
				main.add(pnlAddPlayer, name);
				btnBack.setVisible(true);
			}
			else if(ae.getSource() == btnPlay) {				
				if(PlayerList.getList().size() < 3) {
					JOptionPane.showMessageDialog(null, "!!!Please Input At Least 3 Players!!!");
					return;
				}
				
				isNewGame = true;
				main.remove(pnlPlay);
				pnlPlay = new PnlPlay();
				main.add(pnlPlay, name);
			}
			else {
				main.remove(pnlHistory);
				pnlHistory = new PnlHistory();
				main.add(pnlHistory, name);
				btnBack.setVisible(true);
			}
	
			cardLayout.show(main, name);			
		}
	}
}
