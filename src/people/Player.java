package people;

import java.io.Serializable;

public class Player implements Serializable{
	
	private String name;
	private String phoneNumber;
	
	private Player() {
		name = "";
		phoneNumber = "";
	}
	
	private Player(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public static Player createPlayer() {
		return new Player();
	}
	
	public static Player createPlayer(String name, String phoneNumber) {
		return new Player(name, phoneNumber);
	}
	
	public static Player copyPlayer(Player player) {
		return createPlayer(player.name, player.phoneNumber);
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return name + "; " + phoneNumber;
	}
	
	public Object[] getObject() {
		return new Object[] {name, phoneNumber};
	}
}
