package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import people.Player;
import people.PlayerList;
import people.WinnerList;

import java.awt.Cursor;

public class PnlPlay extends JPanel {
	private JPanel panel_4;
	private JPanel panel_3;
	private JPanel panel_5;
	
	private JButton btnSelect;
	
	private static JLabel lblRandomName;
	private JLabel lblThirdPlace;
	private JLabel lblSecondPlace;
	private JLabel lblFirstPlace;
	
	private static int count;
	private static Player player;
	private RollPlayer rp;
	private static Player[] winners;
	
	static {
		count = 0;
		winners = new Player[3];
		winners[0] = Player.createPlayer();
		winners[1] = Player.createPlayer();
		winners[2] = Player.createPlayer();
	}
	
	public PnlPlay() {
		setLayout(new BorderLayout(0, 20));
		
		initObjects();
		initCoreObjects();
		initEvents();
	}
	
	private void initButtonsEvents() {
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				count++;	
				
				/*
				 * First press the button (btnSelect) to start the thread
				 */
				if(btnSelect.getText().equals("Start")) {
					rp.start();
				}
				
				/*
				 * Users have to click 3 time in order to go back
				 * ELSE they have to continue clicking
				 */
				if(count > 3) {
					if(btnSelect.getText().equals("Back")) {					
						/*
						 * Write to File
						 */
						
						ArrayList<WinnerList.Winner> list = new ArrayList<WinnerList.Winner>();
						for(int i = 0; i < winners.length; i++)
							list.add(WinnerList.Winner.createWinner((i == 0) ? 3 : (i == 1) ? 2 : 1, winners[i]));				
						WinnerList.writeToFile(list);
						
						/*
						 * Reset the game
						 */
						count = 0;
						PlayerList.clear();
						setVisible(false);
						return;
					}
					btnSelect.setText("Back");
				}
				else
					btnSelect.setText("Select");

				
				/*
				 * 	Pick the name from the running thread
				 *  Then delete it
				 */
				if(count > 1) {
					switch(count-1) {
						case 1 : lblThirdPlace.setText(player + "");
								 winners[0] = Player.copyPlayer(player);
							break;
						case 2 : lblSecondPlace.setText(player + "");
								 winners[1] = Player.copyPlayer(player);
							break;
						case 3 : lblFirstPlace.setText(player + "");
								 winners[2] = Player.copyPlayer(player);
							break;
					}
					
					PlayerList.getList().remove(player);
					
				}
				
			}
		});
	}
	
	private void initEvents() {
		initButtonsEvents();
	}
	
	private void initButtons() {	
		btnSelect = new JButton("Start");
		btnSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelect.setBackground(Color.CYAN);
		btnSelect.setSize(new Dimension(100, 20));
		
		///////////////////////////////////////////////////////////
		panel_4.add(btnSelect);
	}

	private void initLabels() {
		lblRandomName = new JLabel("(!!!Click Start!!!)");
		lblRandomName.setBorder(new CompoundBorder());
		lblRandomName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRandomName.setHorizontalAlignment(SwingConstants.CENTER);		
		
		lblThirdPlace = new JLabel("___________________");
		lblThirdPlace.setIconTextGap(50);
		lblThirdPlace.setHorizontalAlignment(SwingConstants.LEFT);
		lblThirdPlace.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblThirdPlace.setIcon(new ImageIcon("img\\3.jpg"));		
		
		lblSecondPlace = new JLabel("___________________");
		lblSecondPlace.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSecondPlace.setIconTextGap(50);
		lblSecondPlace.setIcon(new ImageIcon("img\\2.jpg"));		
		
		lblFirstPlace = new JLabel("___________________");
		lblFirstPlace.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFirstPlace.setIconTextGap(50);
		lblFirstPlace.setIcon(new ImageIcon("img\\1.jpg"));		
		
		////////////////////////////////////////////////////////
		panel_3.add(lblRandomName);
		panel_5.add(lblThirdPlace);
		panel_5.add(lblSecondPlace);
		panel_5.add(lblFirstPlace);
	}
	
	private void initCoreObjects() {
		
		rp = new RollPlayer();
		
		initButtons();
		initLabels();
	}
	
	private void initObjects() {
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlay.setFont(new Font("Rockwell", Font.PLAIN, 50));
		add(lblPlay, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 20));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(2, 1, 0, 20));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel label_9 = new JLabel("                    Players' Name :");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_9, BorderLayout.WEST);
		
		JLabel label_1 = new JLabel("                                            ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_1, BorderLayout.EAST);		
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel label_5 = new JLabel("                    ");
		panel_6.add(label_5, BorderLayout.WEST);
		
		JLabel label_6 = new JLabel("                    ");
		panel_6.add(label_6, BorderLayout.EAST);
	}
	
	private class RollPlayer extends Thread{
		
		@Override
		public void run() {
			while(count < 4) {
				ArrayList<Player> list = new ArrayList<Player>(PlayerList.getList());
				for(Player temp : list) {
					player = temp;
					lblRandomName.setText(temp.getName() + "");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			lblRandomName.setText(winners[2].getName() + "");
		}
	}
}
