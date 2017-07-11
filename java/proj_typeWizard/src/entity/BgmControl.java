package entity;

import render.Resource;

public class BgmControl implements Runnable {

	private boolean isPause = false;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if (isPause) {
				synchronized (this) {
					try {
						Resource.screenbgm.stop();
						this.wait();
						Resource.screenbgm.loop();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean x) {
		isPause = x;
	}

}
