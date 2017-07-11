package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import render.Renderable;
import render.Resource;

public class SkillStatus implements Renderable {

	double fAngle = 0.0, iAngle = 0.0, mAngle = 0.0, pAngle = 0.0,
			sAngle = 0.0;
	private boolean fCasting = false, iCasting = false, mCasting = false,
			pCasting = false, sCasting = false;
	private boolean isPause = false;

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	@Override
	public void draw(Graphics2D g) {

		g.drawImage(Resource.skillBoard, null, 215, -10);
		g.drawImage(Resource.pauseBox, null, 700, -20);
		g.setFont(Resource.pauseFont);
		g.setColor(new Color(51, 25, 0));
		g.drawString(" - ESC -", 715, 20);

		for (int i = 0; i < 5; i++) {
			g.drawImage(Resource.skillBoxBG, null, 252 + (i * 85), 20);
		}
		
		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();

		if (!isPause) {

			if (fCasting) {
				fAngle += Math.toRadians(15);
				while (fAngle > 2 * Math.PI)
					fAngle -= 2 * Math.PI;
				trans.rotate(fAngle, 290, 37);
			} else if (iCasting) {
				iAngle += Math.toRadians(15);
				while (iAngle > 2 * Math.PI)
					iAngle -= 2 * Math.PI;
				trans.rotate(iAngle, 375, 37);
			} else if (mCasting) {
				mAngle += Math.toRadians(15);
				while (mAngle > 2 * Math.PI)
					mAngle -= 2 * Math.PI;
				trans.rotate(mAngle, 460, 37);
			} else if (pCasting) {
				pAngle += Math.toRadians(15);
				while (pAngle > 2 * Math.PI)
					pAngle -= 2 * Math.PI;
				trans.rotate(pAngle, 545, 37);
			} else if (sCasting) {
				sAngle += Math.toRadians(15);
				while (sAngle > 2 * Math.PI)
					sAngle -= 2 * Math.PI;
				trans.rotate(sAngle, 630, 37);
			}
		}

		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				g.transform(trans);
				if (fCasting)
					g.drawImage(Resource.fireStatus, null, 265, 12);
				else if (iCasting)
					g.drawImage(Resource.iceStatus, null, 350, 12);
				else if (mCasting)
					g.drawImage(Resource.meteorStatus, null, 435, 12);
				else if (pCasting)
					g.drawImage(Resource.poisonStatus, null, 520, 12);
				else if (sCasting)
					g.drawImage(Resource.spikeStatus, null, 605, 12);
			}
			g.setTransform(backup);
		}
		if (!fCasting)
			g.drawImage(Resource.fireStatus, null, 265, 12);
		if (!iCasting)
			g.drawImage(Resource.iceStatus, null, 350, 12);
		if (!mCasting)
			g.drawImage(Resource.meteorStatus, null, 435, 12);
		if (!pCasting)
			g.drawImage(Resource.poisonStatus, null, 520, 12);
		if (!sCasting)
			g.drawImage(Resource.spikeStatus, null, 605, 12);

	}

	public void setfCasting(boolean fCasting) {
		this.fCasting = fCasting;
	}

	public void setiCasting(boolean iCasting) {
		this.iCasting = iCasting;
	}

	public void setmCasting(boolean mCasting) {
		this.mCasting = mCasting;
	}

	public void setpCasting(boolean pCasting) {
		this.pCasting = pCasting;
	}

	public void setsCasting(boolean sCasting) {
		this.sCasting = sCasting;
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
