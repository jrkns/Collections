import javax.swing.JFrame;

import render.*;
import entity.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Type Wizard");

		GameScreen screen = new GameScreen();
		GameTitle title = new GameTitle("Now Loading ...");
		GameLogic logic = new GameLogic(title);

		f.add(title);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		title.requestFocus();

		while (true) {
			try {
				Thread.sleep(logic.getSpeed());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (title.isSwap()) {
				f.setContentPane(screen);
				f.revalidate();
				screen.requestFocus();
				title.setSwap(false);
				if (title.isTitle()) {
					title = new GameTitle("Now Refreshing ...");
					f.setContentPane(title);
					f.revalidate();
					title.requestFocus();
					title.setSwap(false);
					logic = new GameLogic(title);
				}
			}
			if (!(title.isTitle())) {
				logic.update();
				screen.repaint();
			} else {
				title.update();
				title.repaint();
			}
		}
	}

}
