package entity;

import java.awt.Graphics2D;

import render.Resource;

public class Land extends Moving {

	private int xc;
	private boolean isEnd, isStart;

	public Land(int x, int y) {
		super(x, y);
		xc = 0;
		isEnd = false;
		isStart = true;
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
		if (x + xc >= Resource.land1.getWidth() - 800) {
			isEnd = true;
		}
		if (x + xc <= 0) {
			isStart = true;
		}
		xc = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(
				Resource.land1.getSubimage(x, 0, 800,
						Resource.land1.getHeight()), null, 0, 435);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return -1;
	}

}
