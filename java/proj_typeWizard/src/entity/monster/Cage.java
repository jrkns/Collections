package entity.monster;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import entity.Hero;
import entity.Land;

public class Cage {

	private static final Cage instance = new Cage();
	private List<Monster> cage = new CopyOnWriteArrayList<Monster>();

	public static Cage getInstance() {
		return instance;
	}

	public void add(String n, Land l, Hero h) {
		int x = 800;
		if (((int) (Math.random() * 1000)) % 2 == 0)
			x = -200;

		if (n.equalsIgnoreCase("golem"))
			cage.add(new Golem(x, 265, l, h));
		else if (n.equalsIgnoreCase("yeti"))
			cage.add(new Yeti(x, 243, l, h));
		else
			return;
	}

	public synchronized void remove(int i) {
		cage.remove(i);
	}

	public void updateAll() {
		for (Monster m : cage)
			m.update();
	}

	public void removeAll() {
		cage.clear();
	}

	public List<Monster> getCage() {
		return cage;
	}

}
