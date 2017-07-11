package render;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RenderableHolder {

	private static final RenderableHolder instance = new RenderableHolder();
	private List<Renderable> entities = new CopyOnWriteArrayList<Renderable>();

	public static RenderableHolder getInstance() {
		return instance;
	}

	public void add(Renderable o) {
		entities.add(o);
		Collections.sort(entities, new Comparator<Renderable>() {
			@Override
			public int compare(Renderable o1, Renderable o2) {
				return Integer.compare(o1.getZ(), o2.getZ());
			}
		});
	}

	public void remove(Renderable o) {
		entities.remove(o);
	}

	public void removeAll() {
		entities.clear();
	}

	public List<Renderable> getRenderableList() {
		return entities;
	}

}
