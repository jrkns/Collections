package entity.monster;

import entity.Hero;
import entity.Land;
import entity.skill.Skill;
import render.Renderable;

public abstract class Monster implements Renderable {

	protected int x, y, hp, type, attack, level, direction, countWalk = 0,
			frameCountWalk = 0, countDead = 0, frameCountDead = 0,
			countPanic = 0, damageTaken = 0, maxHp;
	protected double xHp;
	protected boolean isDead = false, isPanic = false;
	protected Land land;
	protected Hero hero;

	public int getLevel() {
		return level;
	}

	public void killed() {
		isDead = true;
	}

	public boolean isDead() {
		return isDead;
	}

	public int getAttack() {
		return attack;
	}

	public void setX(int x) {
		this.x -= x;
	}

	public int getHp() {
		return hp;
	}

	public int getX() {
		return x;
	}

	public int getDirection() {
		return direction;
	}

	public abstract void hit(Hero hero, Skill skill);

	public abstract void update();

}
