package entity.skill;

import java.awt.Graphics2D;

import render.Resource;

public class MeteorSkill extends Skill {

	private int frameCountX, frameCountY;

	public MeteorSkill(int x, int y, int direction) {
		this.direction = direction;
		if (direction == 1) {
			this.x = x + 50;
			attackRange = -1000;
		} else {
			this.x = x - 100;
			attackRange = -1000;
		}
		this.y = 305;
		try {
			frameWidth = Resource.meteor1.getWidth() / 7;
			frameHeight = Resource.meteor1.getHeight() / 2;
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}

	@Override
	public void update() {
		if (isPlaying) {
			if (count == 1 && frameCountX == 2 && frameCountY == 0) {
				Resource.meteorskill2.play();
			}
			if (count == 8) {
				count = 0;
				frameCountX++;
				if (frameCountY == 1 && frameCountX == 7) {
					stop();
				}
				if (frameCountX == 7) {
					frameCountX = 0;
					frameCountY++;
					Resource.meteorskill.play();
					if (direction == 1) {
						x += 250;
						attackRange = this.x;
					} else {
						x -= 200;
						attackRange = this.x;
					}
				}
			}
			count++;
		}
	}

	@Override
	public void play() {
		isPlaying = true;
		frameCountX = 0;
		frameCountY = 0;
		count = 0;
	}

	@Override
	public void stop() {
		isPlaying = false;
		frameCountX = 0;
		frameCountY = 0;
		count = 0;
	}

	@Override
	public void draw(Graphics2D g) {
		if (isPlaying && (Resource.meteor1 != null)) {
			if (direction == 2)
				g.drawImage(Resource.meteor1.getSubimage(
						Resource.meteor1.getWidth() / 7 * frameCountX,
						Resource.meteor1.getHeight() / 2 * frameCountY,
						frameWidth, frameHeight), null, x, y);
			else
				g.drawImage(Resource.meteor1_2.getSubimage(
						Resource.meteor1_2.getWidth() / 7 * (6 - frameCountX),
						Resource.meteor1.getHeight() / 2 * frameCountY,
						frameWidth, frameHeight), null, x, y);
		}
	}

	@Override
	public boolean isVisible() {
		return isPlaying;
	}

}
