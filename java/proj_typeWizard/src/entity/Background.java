package entity;

import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class Background extends Moving implements Renderable {
	
	private int xc;
	private boolean isEnd, isStart;

	public Background(int x, int y) {
		super(x, y);
		xc = 0;
		isEnd = false;
		isStart = false;
	}

	public void setX(int x) {
		this.xc += x;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	@Override
	public void update() {
		if (x + xc <= Resource.land1.getWidth() - 800) {
			x += xc;
		}
		xc = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.background1.getSubimage(x, 0, 800,
				Resource.background1.getHeight()), null, 0, 0);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return -2;
	}

}
