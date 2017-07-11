package entity.skill;

import java.awt.Graphics2D;

import render.Resource;

public class PoisonSkill extends Skill {

	public PoisonSkill(int x, int y, int direction) {
		this.direction = direction;
		if (direction == 1){
			this.x = x + 50;
			attackRange=-1000;
		}
		else {
			this.x = x - 100;
			attackRange=-1000;
		}
		this.y = 340;
		try {
			frameWidth = Resource.poison1.getWidth() / 7;
			frameHeight = Resource.poison1.getHeight();
		} catch (NullPointerException e) {
			frameWidth = 0;
			frameHeight = 0;
		}
	}

	@Override
	public void update() {
		if (isPlaying) {
			if(count==0 && frameCount==0){
				Resource.poisonskill.play();
			}
			if (count == 10) {
				count = 0;
				frameCount++;
				if (frameCount == 7)
					stop();
			}
			if (direction == 1){
				x += 3;
				attackRange=this.x;
			}
			else{
				x -= 3;
				attackRange=this.x;
			}
			count++;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (isPlaying && (Resource.poison1 != null)) {
			g.drawImage(
					Resource.poison1.getSubimage(Resource.poison1.getWidth()
							/ 7 * frameCount, 0, frameWidth, frameHeight),
					null, x, y);
		}
	}

	@Override
	public boolean isVisible() {
		return isPlaying;
	}

}
