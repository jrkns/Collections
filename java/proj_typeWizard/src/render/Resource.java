package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {

	public static final int screenWidth = 800, screenHeight = 600;
	public static final Font wordFont = new Font("Tahoma", Font.BOLD, 45);
	public static final Font standardFont = new Font("Tahoma", Font.PLAIN, 15);
	public static final Font biggerFont = new Font("Tahoma", Font.PLAIN, 25);
	public static final Font pauseFont = new Font("Tahoma", Font.BOLD, 17);
	public static final Font skillFont = new Font("Tahoma", Font.BOLD, 35);

	// Hero
	public static BufferedImage hero = getImage("res/hero/Hero_2.png");
	public static BufferedImage hero_f = getImage("res/hero/Hero_2_2.png");
	public static BufferedImage hero_a = getImage("res/hero/Hero_2_3.png");
	public static BufferedImage hero_af = getImage("res/hero/Hero_2_4.png");
	public static BufferedImage hero_d = getImage("res/hero/Hero_2_5.png");
	public static BufferedImage hero_df = getImage("res/hero/Hero_2_5_f.png");

	// Monster
	public static BufferedImage monster_golem_1_1 = getImage("res/monster/golem_1_1.png");
	public static BufferedImage monster_golem_1_2 = getImage("res/monster/golem_1_2.png");
	public static BufferedImage monster_golem_1_3 = getImage("res/monster/golem_1_3.png");
	public static BufferedImage monster_golem_1_1_f = getImage("res/monster/golem_1_1_f.png");
	public static BufferedImage monster_golem_1_2_f = getImage("res/monster/golem_1_2_f.png");
	public static BufferedImage monster_golem_1_3_f = getImage("res/monster/golem_1_3_f.png");
	public static BufferedImage monster_yeti_1_1 = getImage("res/monster/yeti_1_1.png");
	public static BufferedImage monster_yeti_1_2 = getImage("res/monster/yeti_1_2.png");
	public static BufferedImage monster_yeti_1_3 = getImage("res/monster/yeti_1_3.png");
	public static BufferedImage monster_yeti_1_4 = getImage("res/monster/yeti_1_4.png");
	public static BufferedImage monster_yeti_1_1_f = getImage("res/monster/yeti_1_1_f.png");
	public static BufferedImage monster_yeti_1_2_f = getImage("res/monster/yeti_1_2_f.png");
	public static BufferedImage monster_yeti_1_3_f = getImage("res/monster/yeti_1_3_f.png");
	public static BufferedImage monster_yeti_1_4_f = getImage("res/monster/yeti_1_4_f.png");

	// UI
	public static BufferedImage skillBoard = getImage("res/ui/skillBoard.png");
	public static BufferedImage pauseBox = getImage("res/ui/pauseBox.png");
	public static BufferedImage banner = getImage("res/ui/banner.png");

	// BG
	public static BufferedImage land1 = getImage("res/map/land1.png");
	public static BufferedImage background1 = getImage("res/map/background1.png");
	public static BufferedImage titleBG = getImage("res/ui/titleBG.png");

	// Skill
	public static BufferedImage ice1 = getImage("res/skill/ice1.png");
	public static BufferedImage fire1 = getImage("res/skill/fire1.png");
	public static BufferedImage meteor1 = getImage("res/skill/meteor1.png");
	public static BufferedImage meteor1_2 = getImage("res/skill/meteor1_2.png");
	public static BufferedImage poison1 = getImage("res/skill/poison1.png");
	public static BufferedImage spike1 = getImage("res/skill/spike1.png");

	// SkillStatus
	public static BufferedImage skillBoxBG = getImage("res/ui/skillBoxBg.png");
	public static BufferedImage fireStatus = getImage("res/ui/fire.png");
	public static BufferedImage iceStatus = getImage("res/ui/ice.png");
	public static BufferedImage meteorStatus = getImage("res/ui/meteor.png");
	public static BufferedImage poisonStatus = getImage("res/ui/poison.png");
	public static BufferedImage spikeStatus = getImage("res/ui/spike.png");

	// Debuff
	public static BufferedImage burn = getImage("res/skill/burn.png");
	public static BufferedImage poison = getImage("res/skill/poisoned.png");
	public static BufferedImage freeze = getImage("res/skill/freeze.png");

	static BufferedImage getImage(String directory) {
		BufferedImage b;
		try {
			ClassLoader loader = Resource.class.getClassLoader();
			b = ImageIO.read(loader.getResource(directory));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return b;
	}

	// Sound
	public static AudioClip titlebgm = getSound("res/sound/titlebgm.wav");
	public static AudioClip screenbgm = getSound("res/sound/screenbgm.wav");
	public static AudioClip hit = getSound("res/sound/hitted.wav");
	public static AudioClip jump = getSound("res/sound/jump.wav");
	public static AudioClip levelup = getSound("res/sound/levelup.wav");
	public static AudioClip score = getSound("res/sound/score.wav");
	public static AudioClip iceskill = getSound("res/sound/iceskill.wav");
	public static AudioClip meteorskill = getSound("res/sound/meteorskill.wav");
	public static AudioClip meteorskill2 = getSound("res/sound/meteorskill2.wav");
	public static AudioClip fireskill = getSound("res/sound/fireskill.wav");
	public static AudioClip poisonskill = getSound("res/sound/poisonskill.wav");
	public static AudioClip spikeskill = getSound("res/sound/spikeskill.wav");

	static AudioClip getSound(String directory) {
		AudioClip a;
		try {
			ClassLoader loader = Resource.class.getClassLoader();
			a = Applet.newAudioClip(loader.getResource(directory));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return a;
	}

}
