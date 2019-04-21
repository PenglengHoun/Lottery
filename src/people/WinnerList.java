package people;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class WinnerList {
	
	private static ArrayList<Winner> list;
	private static int gameNumber;
	private static final String WINNER_DESTINATION = "winners.txt";
	private static final String GAME_DESTINATION = "game.txt";
	
	static {
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(GAME_DESTINATION))){
			gameNumber = in.readInt();
		}
		catch(FileNotFoundException ex) {
			gameNumber = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		list = new ArrayList<Winner>();
	}
	
	public static void writeToFile(ArrayList<Winner> temp) {
		readFromFile();
		
		/*
		 * Write winner to file
		 */
		list.addAll(temp);
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(WINNER_DESTINATION))){
			out.writeObject(list);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Write game recode to file
		 */
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(GAME_DESTINATION))){
			out.writeInt(++gameNumber);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void readFromFile() {
		/*
		 * Read winner from file
		 */
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(WINNER_DESTINATION))){
			list = (ArrayList<Winner>) in.readObject();
		}
		catch(FileNotFoundException ex) {
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Read game record from file
		 */
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(GAME_DESTINATION))){
			gameNumber = in.readInt();
		}
		catch(FileNotFoundException ex) {
			gameNumber = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Winner> getList() {
		readFromFile();
		return list;
	}
	
	public static class Winner implements Serializable{
		
		private int gameNumber;
		private String name;
		private String phoneNumber;
		private int place;
		
		private Winner(int place, Player player) {
			this.gameNumber = WinnerList.gameNumber;
			this.name = player.getName();
			this.phoneNumber = player.getPhoneNumber();
			this.place = place;
		}
		
		public static Winner createWinner(int place, Player player) {
			return new Winner(place, player);
		}
		
		public Object[] getObject() {
			return new Object[] {gameNumber, name, phoneNumber, place + " Place"};
		}
		
		public String getName() {
			return name;
		}
		
	}
}
