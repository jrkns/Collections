package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import entity.monster.Cage;
import entity.monster.Monster;

public class GameScreen extends JComponent {

	private static final long serialVersionUID = 1L;

	public GameScreen() {
		this.setPreferredSize(new Dimension(Resource.screenWidth,
				Resource.screenHeight));

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
				if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) {
					InputUtility.setSpell(KeyEvent.getKeyText(e.getKeyCode()));
				}

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					InputUtility.setEscTriggered(!InputUtility
							.getEscPressed());
					InputUtility.setEscPressed(true);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);

				InputUtility.setEscPressed(false);
			}
		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		((Graphics2D) g).setBackground(Color.BLACK);
		for (Renderable e : RenderableHolder.getInstance().getRenderableList()) {
			if (e.isVisible() && e.getZ() < -1)
				e.draw((Graphics2D) g);
		}
		for (Monster m : Cage.getInstance().getCage()) {
			if (m.isVisible()) {
				m.draw((Graphics2D) g);
			}
		}
		for (Renderable e : RenderableHolder.getInstance().getRenderableList()) {
			if (e.isVisible() && e.getZ() >= -1)
				e.draw((Graphics2D) g);
		}
	}
	
}
