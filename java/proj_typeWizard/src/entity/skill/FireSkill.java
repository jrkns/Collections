package entity.skill;

import java.awt.Graphics2D;

import render.Resource;

public class FireSkill extends Skill {

	public FireSkill(int x, int y, int direction) {
		this.direction=direction;
	
		this.x = x + 80;
			attackRange=this.x;
		
		this.y = 350;
		try {
			frameWidth = Resource.fire1.getWidth() / 4;
			frameHeight = Resource.fire1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}

	@Override
	public void update() {
		if (isPlaying) {
			if(frameCount==0 && count==0){
				Resource.fireskill.play();
			}
			if (count == 10) {
				count = 0;
				frameCount++;
				if (frameCount == 4)
					stop();
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (isPlaying && (Resource.fire1 != null)) {
			g.drawImage(
					Resource.fire1.getSubimage(Resource.fire1.getWidth() / 4
							* frameCount, 0, frameWidth, frameHeight), null, x,
					y);
		}
	}

	@Override
	public boolean isVisible() {
		return isPlaying;
	}

}
