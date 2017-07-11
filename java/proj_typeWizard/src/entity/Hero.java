package entity;

import input.InputUtility;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.monster.Cage;
import entity.monster.Monster;
import entity.monster.Yeti;
import entity.skill.*;
import render.*;
import ui.HeroStatus;

public class Hero extends Moving {

	private int frameCount = 0, count = 0, countA = 0, frameCountA = 0,
			direction = 1, temp, i = 5, countD = 0, frameCountD = 0, tick = 0;
	private int gravity = 1, velocityY;
	private boolean isJumped, isDead, isOutOfMana = false, isHitting = false;
	private boolean isRight, isLeft, isStop;
	private boolean isSkill, isCasting;
	private Land land;
	private Background background;
	private HeroStatus heroStatus;
	private Skill[] skills = new Skill[6];
	private static Word[] words = new Word[5];
	private int STR, INT, level, hp, attack, mana, manaTick = 0, maxMp, maxHp;
	private Monster nearMon;
	private int notBeHitCount = 0;
	private boolean visible = true;

	// direction 1 : RIGHT direction 2 : LEFT

	public Hero(int x, int y, Land land, Background background,
			HeroStatus heroStatus) {
		super(x, y);
		this.heroStatus = heroStatus;
		this.land = land;
		this.background = background;
		temp = y;
		isJumped = false;
		velocityY = 0;
		isRight = false;
		isSkill = false;
		isCasting = false;
		isDead = false;
		isStop = false;
		this.heroStatus.resetLevel();
		level = this.heroStatus.getLevel();
		STR = 10;
		INT = 10;
		maxHp = STR * 20;
		maxMp = INT * 20;
		hp = maxHp;
		mana = maxMp;
		this.heroStatus.setMaxHp(maxHp);
		this.heroStatus.setMaxMp(maxMp);
		this.heroStatus.setCurrentHp(hp);
		this.heroStatus.setCurrentMp(mana);
		this.heroStatus.setMaxXp();
		attack = (int) (INT * 1.5);

		skills[0] = new IceSkill(x, y, direction);
		skills[1] = new FireSkill(x, y, direction);
		skills[2] = new MeteorSkill(x, y, direction);
		skills[3] = new PoisonSkill(x, y, direction);
		skills[4] = new SpikeSkill(x, y, direction);
		skills[5] = new SpikeSkill(x, y, direction); // dummy For Exception

		words[0] = new Word("ICE");
		words[1] = new Word("FIRE");
		words[2] = new Word("METEOR");
		words[3] = new Word("POISON");
		words[4] = new Word("SPIKE");

		nearMon = new Yeti(1000, 1000, land, this);
	}

	@Override
	public void update() {

		// check level
		checkLevel();

		// update hp mp bar.
		setHpMp();

		if (manaTick == 20) {
			manaTick = 0;
			manaRegen();
		}
		manaTick++;

		if (tick == 100) {
			tick = 0;
			isOutOfMana = false;
		}
		tick++;

		// Dead Animation
		if (hp == 0) {
			deadAnimation();
		} else if (isCasting) {
			// Idle Spell Animation
			idleSpellAnimation();
		} else {
			// Idle Animation Controller
			idleAnimation();
		}

		// Invisible Phase
		if (isHitting && hp > 0) {
			if (notBeHitCount % 6 == 0)
				this.visible = !this.visible;
			if (notBeHitCount >= 100) {
				this.visible = true;
				isHitting = false;
				notBeHitCount = 0;
			}
			notBeHitCount++;
		}
		if (hp > 0) {

			// Jump
			jump();

			// Moving
			if (InputUtility.getKeyPressed(KeyEvent.VK_LEFT)) {
				walkLeft();
			} else if (InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)) {
				walkRight();
			}

			// Casting Skill
			if (isSkill) {
				castingSkill();
			} else {
				checkCastingSkill();
			}
		}

		// check and remove dead monster
		removeMonster();

		isRight = false;
		isLeft = false;
	}

	public void removeMonster() {
		for (int j = 0; j < Cage.getInstance().getCage().size(); j++) {
			if (Cage.getInstance().getCage().get(j).isDead()) {
				heroStatus.addScore(20 * Cage.getInstance().getCage().get(j)
						.getLevel());
				heroStatus.addXp(20 * Cage.getInstance().getCage().get(j)
						.getLevel());
				Resource.score.play();
				Cage.getInstance().getCage().remove(j);
			}
		}
	}

	public void checkCastingSkill() {
		if ((words[0].getWord().length == InputUtility.getSpell().length())) {
			if (words[0].cast(InputUtility.getSpell())) {
				if (mana >= 10) {
					skills[0] = new IceSkill(nearMon.getX(), y,
							nearMon.getDirection());
					RenderableHolder.getInstance().add(skills[0]);
					skills[0].play();
					isSkill = true;
					isCasting = true;
					i = 0;
					lossMana(10);
					isOutOfMana = false;
					InputUtility.clearSpell();
				} else {
					isOutOfMana = true;
					InputUtility.clearSpell();
				}
			}
		}
		if ((words[1].getWord().length == InputUtility.getSpell().length())) {
			if (words[1].cast(InputUtility.getSpell())) {
				if (mana >= 10) {
					skills[1] = new FireSkill(nearMon.getX(), y,
							nearMon.getDirection());
					RenderableHolder.getInstance().add(skills[1]);
					skills[1].play();
					isSkill = true;
					isCasting = true;
					i = 1;
					lossMana(10);
					isOutOfMana = false;
					InputUtility.clearSpell();
				} else {
					isOutOfMana = true;
					InputUtility.clearSpell();
				}
			}
		}
		if ((words[2].getWord().length == InputUtility.getSpell().length())) {
			if (words[2].cast(InputUtility.getSpell())) {
				if (mana >= 20) {
					skills[2] = new MeteorSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[2]);
					skills[2].play();
					isSkill = true;
					isCasting = true;
					i = 2;
					lossMana(20);
					InputUtility.clearSpell();
					isOutOfMana = false;
				} else {
					isOutOfMana = true;
					InputUtility.clearSpell();
				}
			}
		}
		if ((words[3].getWord().length == InputUtility.getSpell().length())) {
			if (words[3].cast(InputUtility.getSpell())) {
				if (mana >= 20) {
					skills[3] = new PoisonSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[3]);
					skills[3].play();
					isSkill = true;
					isCasting = true;
					i = 3;
					lossMana(20);
					isOutOfMana = false;
					InputUtility.clearSpell();
				} else {
					isOutOfMana = true;
					InputUtility.clearSpell();
				}
			}
		}
		if ((words[4].getWord().length == InputUtility.getSpell().length())) {
			if (words[4].cast(InputUtility.getSpell())) {
				if (mana >= 30) {
					skills[4] = new SpikeSkill(x, y, direction);
					RenderableHolder.getInstance().add(skills[4]);
					skills[4].play();
					isSkill = true;
					isCasting = true;
					i = 4;
					lossMana(30);
					isOutOfMana = false;
					InputUtility.clearSpell();
				} else {
					isOutOfMana = true;
					InputUtility.clearSpell();
				}
			}
		}
	}

	public void castingSkill() {
		if (x >= 400 && isRight) {
			skills[i].setX(5);
		} else if (x <= 200 && isLeft) {
			skills[i].setX(-5);
		}

		// interaction
		for (Monster m : Cage.getInstance().getCage()) {

			// ice + fire
			if (i == 0 || i == 1) {
				if (skills[i].getFrameCount() == 1
						&& m.getX() <= nearMon.getX() + 50
						&& nearMon.getX() - 50 <= m.getX()) {
					m.hit(this, skills[i]);
					nearMon = new Yeti(1000, 1000, land, this);
				}
			} else if (i == 2) { // meteor
				if (skills[i].getFrameCount() < 3
						&& skills[i].getAttackRange() != -1000
						&& (skills[i].getAttackRange() - 20 < m.getX() + 150)
						&& (m.getX() < skills[i].getAttackRange() + 100)) {
					m.hit(this, skills[i]);
					nearMon = new Yeti(1000, 1000, land, this);
				}
			} else if (i == 3) { // poison
				if (skills[i].getAttackRange() != -1000
						&& (skills[i].getAttackRange() - 5 < m.getX() + 150)
						&& (m.getX() < skills[i].getAttackRange() + 5)) {
					m.hit(this, skills[i]);
					nearMon = new Yeti(1000, 1000, land, this);
				}
			} else if (i == 4) { // spike
				if (skills[i].getFrameCount() < 5
						&& (skills[i].getAttackRange() < m.getX() + 100)
						&& (m.getX() < skills[i].getAttackRange() + 450)) {
					m.hit(this, skills[i]);
					nearMon = new Yeti(1000, 1000, land, this);
				}
			}
		}
		skills[i].update();

		if (!skills[0].isPlaying() && !skills[1].isPlaying()
				&& !skills[2].isPlaying() && !skills[3].isPlaying()
				&& !skills[4].isPlaying()) {
			isSkill = false;
			i = 5;
		}
	}

	public void walkLeft() {
		direction = 2;
		if (x > 200 || land.isStart()) {
			if (x + 20 > 0)
				x -= 5;
		} else if (!(x > 200 || land.isStart())) {
			land.setX(-5);
			background.setX(-2);
			land.setEnd(false);
		}
	}

	public void walkRight() {
		direction = 1;
		if (x < 400 || land.isEnd()) {
			if (x < 720)
				x += 5;
		} else if (!(x < 400 || land.isEnd())) {
			land.setX(5);
			background.setX(2);
			land.setStart(false);
		}
	}

	public void jump() {
		if (InputUtility.getKeyPressed(KeyEvent.VK_UP)) {
			if (!isJumped) {
				isJumped = true;
				velocityY = -17;
				Resource.jump.play();
			}
		}
		if (isJumped) {
			velocityY += gravity;
			y += velocityY;
			if (y > temp) {
				y = temp;
				isJumped = false;
				velocityY = 0;
			}
		}
	}

	public void idleAnimation() {
		if (count == 10) {
			count = 0;
			frameCount++;
			frameCount %= 6;
		}
		count++;
	}

	public void idleSpellAnimation() {
		if (countA == 8) {
			countA = 0;
			frameCountA++;
			if (frameCountA >= 10) {
				isCasting = false;
				frameCountA = 0;
				countA = 0;
			}
		}
		countA++;
	}

	public void deadAnimation() {
		if (countD == 10) {
			countD = 0;
			frameCountD++;
			if (frameCountD == 5) {
				isDead = true;
				countD = 0;
				frameCountD = 0;
			}
		}
		countD++;
	}

	public int getFrameCountD() {
		return frameCountD;
	}

	public void setHpMp() {
		heroStatus.setCurrentHp(hp);
		heroStatus.setCurrentMp(mana);
	}

	public void checkLevel() {
		if (level != heroStatus.getLevel()) {
			level = heroStatus.getLevel();
			STR = (int) (10 + 1.5 * level);
			INT = (int) (10 + 2.5 * level);
			maxHp = STR * 20;
			maxMp = INT * 20;
			attack = (int) (INT * 1.5);
			hp = maxHp;
			mana = maxMp;
			heroStatus.setMaxHp(maxHp);
			heroStatus.setMaxMp(maxMp);
			heroStatus.setCurrentHp(hp);
			heroStatus.setCurrentMp(mana);
			heroStatus.setMaxXp();
			Resource.levelup.play();
		}
	}

	public boolean isDead() {
		return isDead;
	}

	@Override
	public void draw(Graphics2D g) {
		if (hp == 0) {
			if (direction == 2) {
				g.drawImage(
						Resource.hero_d.getSubimage(Resource.hero_d.getWidth()
								/ 5 * frameCountD, 0,
								Resource.hero_d.getWidth() / 5,
								Resource.hero_d.getHeight()), null, x - 20,
						y - 50);
			} else if (direction == 1) {
				g.drawImage(Resource.hero_df.getSubimage(
						Resource.hero_df.getWidth() / 5 * (4 - frameCountD), 0,
						Resource.hero_df.getWidth() / 5,
						Resource.hero_df.getHeight()), null, x - 20, y - 50);
			}

		} else if (isCasting) {
			if (direction == 2) {
				g.drawImage(Resource.hero_a.getSubimage(
						Resource.hero_a.getWidth() / 10 * frameCountA, 0,
						Resource.hero_a.getWidth() / 10,
						Resource.hero_a.getHeight()), null, x - 20, y - 20);
			} else if (direction == 1) {
				g.drawImage(Resource.hero_af.getSubimage(
						Resource.hero_af.getWidth() / 10 * (9 - frameCountA),
						0, Resource.hero_af.getWidth() / 10,
						Resource.hero_af.getHeight()), null, x - 20, y - 20);
			}
		} else {
			if (direction == 2) {
				g.drawImage(Resource.hero.getSubimage(Resource.hero.getWidth()
						/ 6 * frameCount, 0, Resource.hero.getWidth() / 6,
						Resource.hero.getHeight()), null, x, y);
			} else if (direction == 1) {
				g.drawImage(
						Resource.hero_f.getSubimage(Resource.hero_f.getWidth()
								/ 6 * (5 - frameCount), 0,
								Resource.hero_f.getWidth() / 6,
								Resource.hero.getHeight()), null, x, y);
			}
		}

		// Find near
		if (nearMon.isDead() || nearMon.getHp() <= 0
				|| this.direction != nearMon.getDirection())
			nearMon = new Yeti(1000, 1000, land, this);

		for (Monster m : Cage.getInstance().getCage()) {
			boolean skip = false;
			if (skills[i].getFrameCount() != 0 || m.getX() < -100
					|| m.getX() > 800)
				skip = true;
			if (Math.abs(m.getX() + 120 - x) <= Math.abs(nearMon.getX() + 120
					- x)
					&& !skip
					&& m.getDirection() == this.direction
					&& !m.isDead() && !(m.getHp() <= 0))
				nearMon = m;
		}

		if (isOutOfMana) {
			g.setColor(Color.RED);
			g.setFont(Resource.biggerFont);
			g.drawString("OUT OF MANA", x, y - 20);
		}

	}

	public Monster getNearMon() {
		return nearMon;
	}

	public int getLevel() {
		return level;
	}

	public void lossMana(int x) {
		mana -= x;
		if (mana <= 0) {
			mana = 0;
		}
	}

	public void manaRegen() {
		mana += 2;
		if (mana > maxMp) {
			mana = maxMp;
		}
	}

	public int getStr() {
		return STR;
	}

	public int getInt() {
		return INT;
	}

	public int getAttack() {
		return attack;
	}

	public int getX() {
		return x;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	@Override
	public boolean isVisible() {
		return !isDead && visible;
	}

	@Override
	public int getZ() {
		return 1;
	}

	public boolean getIsMid() {
		return isRight;
	}

	public void hit(Monster m) {
		int i = 1;
		if (m instanceof Yeti)
			i++;
		if (!isHitting) {
			Resource.hit.play();
			isHitting = true;
			if (hp - m.getAttack() * i >= 0)
				hp -= m.getAttack() * i;
			else {
				hp = 0;
			}
		}
	}

	public boolean getStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

}
