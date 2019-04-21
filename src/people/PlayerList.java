package people;

import java.util.ArrayList;

public class PlayerList {

	private static ArrayList<Player> list;	
	
	static {
		list = new ArrayList<Player>();
	}
	
	public static void add(Player player) {
		list.add(player);
	}
	
	public static void remove(int i) {
		list.remove(i);
	}
	
	public static void clear() {
		list.clear();
	}
	
	public static ArrayList<Player> getList() {
		return list;
	}

	public static void setPlayerAt(int i, Player player) {
		list.set(i, player);
	}
}
