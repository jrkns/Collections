package entity.skill;

import render.Renderable;

public abstract class Skill implements Renderable {

	protected boolean isPlaying = false;
	protected int x, y, frameCount, count, frameWidth, frameHeight,
			attackRange, direction;

	public abstract void update();

	public int getDirection() {
		return direction;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setX(int x) {
		this.x -= x;
		attackRange = this.x;
	}

	public void play() {
		isPlaying = true;
		frameCount = 0;
		count = 0;
	}

	public void stop() {
		isPlaying = false;
		frameCount = 0;
		count = 0;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public int getZ() {
		return 1;
	}

	public int getFrameCount() {
		return frameCount;
	}

}
