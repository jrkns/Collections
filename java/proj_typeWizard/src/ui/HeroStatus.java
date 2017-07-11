package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class HeroStatus implements Renderable {

	private int score, health, mana, level, maxHp, maxMp, maxXp, xp;
	private double xHp, xMp, xXp;
	private boolean isDead;

	public HeroStatus() {
		score = 0;
		xp = 0;
		level = 1;
		isDead = false;
	}

	public void update() {
		if (xp >= maxXp) {
			changeLevel();
			xp = 0;
		}
		xHp = (health * 1.0) / (maxHp * 1.0);
		xMp = (mana * 1.0) / (maxMp * 1.0);
		xXp = (xp * 1.0) / (maxXp * 1.0);
	}

	public void addXp(int x) {
		xp += x;
	}

	public void setMaxXp() {
		maxXp = level * 100;
	}

	public void setMaxHp(int x) {
		maxHp = x;
	}

	public void setCurrentHp(int x) {
		health = x;
	}

	public void setMaxMp(int x) {
		maxMp = x;
	}

	public void setCurrentMp(int x) {
		mana = x;
	}

	public boolean isDead() {
		return isDead;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void changeLevel() {
		level++;
	}

	public int getLevel() {
		return level;
	}

	public void resetLevel() {
		level = 1;
	}

	public int getScore() {
		return score;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(Resource.standardFont);
		g.drawString("Level : " + level, 20, 20);
		g.setFont(Resource.standardFont);
		g.drawString("SCORE: " + score, 100, 80);
		g.setColor(Color.BLACK);
		g.fillRect(90, 5, 120, 15);
		g.setColor(Color.RED);
		g.fillRect(93, 8, (int) (xHp * 114), 9);
		g.setColor(Color.BLACK);
		g.fillRect(90, 25, 120, 15);
		g.setColor(Color.BLUE);
		g.fillRect(93, 28, (int) (xMp * 114), 9);
		g.setColor(Color.BLACK);
		g.fillRect(90, 45, 120, 15);
		g.setColor(Color.YELLOW);
		g.fillRect(93, 48, (int) (xXp * 114), 9);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 2;
	}

}
