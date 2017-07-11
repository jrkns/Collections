package entity;

import java.awt.event.KeyEvent;
import java.util.List;

import entity.monster.Cage;
import entity.monster.Golem;
import entity.monster.Yeti;
import input.InputUtility;
import render.GameTitle;
import render.Renderable;
import render.RenderableHolder;
import render.Resource;
import ui.*;

public class GameLogic {

	private Land land;
	private Hero hero;
	private HeroStatus heroStatus;
	private SkillStatus skillStatus;
	private boolean castFail = false, iCast = false, fCast = false,
			mCast = false, pCast = false, sCast = false;
	private Word ice = null, fire = null, meteor = null, poison = null,
			spike = null;
	private int tick = 0;
	private Background background;
	private boolean isPause = true;
	private final int speed = 20;
	private Banner pauseBanner = new Banner();
	private GameTitle title;

	private Thread t1, t2;
	private BgmControl bgm;

	public GameLogic(GameTitle title) {

		this.title = title;

		this.land = new Land(0, 0);
		this.background = new Background(0, 0);
		this.heroStatus = new HeroStatus();
		this.hero = new Hero(20, 370, this.land, this.background,
				this.heroStatus);
		this.skillStatus = new SkillStatus();

		bgm = new BgmControl();
		t1 = new Thread(bgm);
		t1.start();
		t2 = new Thread(pauseBanner);
		t2.start();

		RenderableHolder.getInstance().add(land);
		RenderableHolder.getInstance().add(background);
		RenderableHolder.getInstance().add(heroStatus);
		RenderableHolder.getInstance().add(hero);
		RenderableHolder.getInstance().add(skillStatus);
		RenderableHolder.getInstance().add(pauseBanner);

		pauseBanner.setVisible(!pauseBanner.isVisible);

	}

	public void update() {

		// Pause
		if (InputUtility.getKeyPressed(KeyEvent.VK_ESCAPE)) {
			InputUtility.setKeyPressed(KeyEvent.VK_ESCAPE, false);
			skillStatus.setPause(!skillStatus.isPause());
			pauseBanner.setVisible(!pauseBanner.isVisible);
			if (pauseBanner.isVisible) {
				synchronized (pauseBanner) {
					pauseBanner.notifyAll();
				}
			}
		}

		if (pauseBanner.isVisible()) {
			if (InputUtility.getKeyPressed(KeyEvent.VK_Q)) {
				InputUtility.reset();
				title.setTitle(true);
				title.setSwap(true);
				Resource.screenbgm.stop();
				Resource.titlebgm.loop();
				this.setPause(!isPause);
				pauseBanner.setVisible(!pauseBanner.isVisible);
				skillStatus.setPause(!skillStatus.isPause());
				RenderableHolder.getInstance().removeAll();
				Cage.getInstance().removeAll();
			} else if (InputUtility.getKeyPressed(KeyEvent.VK_H)) {
				InputUtility.setKeyPressed(KeyEvent.VK_H, false);
				HighScoreUtility.displayTop10();
			} else if (InputUtility.getKeyPressed(KeyEvent.VK_X)) {
				InputUtility.setKeyPressed(KeyEvent.VK_X, false);
				bgm.setPause(!bgm.isPause());
				if (!bgm.isPause()) {
					synchronized (bgm) {
						bgm.notifyAll();
					}
				}
			}
		}

		heroStatus.update();

		if (pauseBanner.isVisible()) {
			skillStatus.setPause(true);
			return;
		}

		if (this.hero.isDead()) {
			HighScoreUtility.recordHighScore(this.heroStatus.getScore());
			HighScoreUtility.displayTop10();
			InputUtility.reset();
			title.setTitle(true);
			title.setSwap(true);
			Resource.screenbgm.stop();
			Resource.titlebgm.loop();
			this.setPause(!isPause);
			pauseBanner.setVisible(!pauseBanner.isVisible);
			skillStatus.setPause(!skillStatus.isPause());
			RenderableHolder.getInstance().removeAll();
			Cage.getInstance().removeAll();
		}

		if (InputUtility.getSpell() != "") {
			if (InputUtility.getSpell().length() > 6
					|| ("IFMPS").indexOf(InputUtility.getSpell()
							.substring(0, 1)) < 0)
				InputUtility.clearSpell();
		}

		if (castFail) {
			castFail = false;
			iCast = false;
			fCast = false;
			mCast = false;
			pCast = false;
			sCast = false;
			skillStatus.setiCasting(false);
			skillStatus.setfCasting(false);
			skillStatus.setmCasting(false);
			skillStatus.setpCasting(false);
			skillStatus.setsCasting(false);
			InputUtility.clearSpell();
		}

		boolean cast = iCast || fCast || mCast || pCast || sCast;

		// Add Word
		if (InputUtility.getSpell() != "") {
			if (!cast) {
				if (InputUtility.getSpell().equalsIgnoreCase("I")) {
					skillStatus.setiCasting(true);
					ice = new Word("ICE");
					ice.setVisible(true);
					iCast = true;
					RenderableHolder.getInstance().add(ice);
				} else if (InputUtility.getSpell().equalsIgnoreCase("F")) {
					skillStatus.setfCasting(true);
					fire = new Word("FIRE");
					fire.setVisible(true);
					fCast = true;
					RenderableHolder.getInstance().add(fire);
				} else if (InputUtility.getSpell().equalsIgnoreCase("M")) {
					skillStatus.setmCasting(true);
					meteor = new Word("METEOR");
					meteor.setVisible(true);
					mCast = true;
					RenderableHolder.getInstance().add(meteor);
				} else if (InputUtility.getSpell().equalsIgnoreCase("P")) {
					skillStatus.setpCasting(true);
					poison = new Word("POISON");
					poison.setVisible(true);
					pCast = true;
					RenderableHolder.getInstance().add(poison);
				} else if (InputUtility.getSpell().equalsIgnoreCase("S")) {
					skillStatus.setsCasting(true);
					spike = new Word("SPIKE");
					spike.setVisible(true);
					sCast = true;
					RenderableHolder.getInstance().add(spike);
				} else if (InputUtility.getSpell().length() == 1) {
					castFail = true;
				}
			}

			// Fill Color
			String lastWord = InputUtility.getSpell().substring(
					InputUtility.getSpell().length() - 1,
					InputUtility.getSpell().length());

			if (iCast)
				ice.cast(lastWord);
			else if (fCast)
				fire.cast(lastWord);
			else if (mCast && !lastWord.equalsIgnoreCase("E"))
				meteor.cast(lastWord);
			else if (mCast)
				meteor.setRed(InputUtility.getSpell().length());
			else if (pCast && !lastWord.equalsIgnoreCase("O"))
				poison.cast(lastWord);
			else if (pCast)
				poison.setRed(InputUtility.getSpell().length());
			else if (sCast)
				spike.cast(lastWord);
		}

		List<Renderable> list = RenderableHolder.getInstance()
				.getRenderableList();

		// Remove Word
		for (Renderable e : list) {
			if (e instanceof Word) {
				if (((Word) e).getString().indexOf(InputUtility.getSpell()) < 0) {
					RenderableHolder.getInstance().remove(e);
					castFail = true;
				}
				if (((Word) e).getString().equalsIgnoreCase(
						InputUtility.getSpell())) {
					RenderableHolder.getInstance().remove(e);
					iCast = false;
					fCast = false;
					mCast = false;
					pCast = false;
					sCast = false;
					skillStatus.setiCasting(false);
					skillStatus.setfCasting(false);
					skillStatus.setmCasting(false);
					skillStatus.setpCasting(false);
					skillStatus.setsCasting(false);
				}
			}

		}

		// Hitting Hero
		if (hero.getNearMon() instanceof Golem
				&& hero.getNearMon().getX() - 20 <= hero.getX()
				&& hero.getX() <= hero.getNearMon().getX() + 190)
			hero.hit(hero.getNearMon());
		else if (hero.getNearMon().getDirection() != 1
				&& hero.getNearMon() instanceof Yeti
				&& hero.getNearMon().getX() + 80 <= hero.getX()
				&& hero.getX() <= hero.getNearMon().getX() + 170
				&& (((Yeti) hero.getNearMon()).getFrameCountAttack() == 4 || ((Yeti) hero
						.getNearMon()).getFrameCountAttack() == 5))
			hero.hit(hero.getNearMon());
		else if (hero.getNearMon() instanceof Yeti
				&& hero.getNearMon().getX() - 10 <= hero.getX()
				&& hero.getX() <= hero.getNearMon().getX() + 115
				&& (((Yeti) hero.getNearMon()).getFrameCountAttack() == 4 || ((Yeti) hero
						.getNearMon()).getFrameCountAttack() == 5))
			hero.hit(hero.getNearMon());

		if (tick >= 300) {
			tick = 0;

			// How to summon here;
			Cage.getInstance().add("golem", this.land, this.hero);
			Cage.getInstance().add("yeti", this.land, this.hero);
		}

		Cage.getInstance().updateAll();
		hero.update();
		background.update();
		land.update();
		tick++;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	public int getSpeed() {
		return speed;
	}
}
