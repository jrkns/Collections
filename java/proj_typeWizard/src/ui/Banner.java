package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.RenderableHolder;
import render.Resource;

public class Banner implements Renderable, Runnable {

	public boolean isVisible = false;

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(Resource.banner, null, 200, 120);
		g.setFont(Resource.wordFont);
		g.setColor(new Color(51, 25, 0));
		g.drawString("PAUSE", 330, 170);
		g.setFont(Resource.skillFont);
		g.drawString("SKILL", 350, 300);
		g.setFont(Resource.pauseFont);
		g.drawString("press 'Q' : Exit to MainMenu ", 270, 200);
		g.drawString("press 'H' : to show LeaderBoard ", 270, 220);
		g.drawString("press 'X' : to Mute/Unmute BGM", 270, 240);
		g.drawString("press 'esc' : Pause/Unpause ", 270, 260);
		g.drawString("-SingleTarget-", 240, 330);
		g.drawString("F : fire", 280, 355);
		g.drawString("I : ice", 280, 375);
		g.drawString("-MultiTarget-", 430, 330);
		g.drawString("M : meteor", 445, 355);
		g.drawString("P : poison", 450, 375);
		g.drawString("S : spike", 450, 395);

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return isVisible;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (!isVisible) {
				synchronized (this) {
					try {
						RenderableHolder.getInstance().remove(this);
						this.wait();
						RenderableHolder.getInstance().add(this);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
