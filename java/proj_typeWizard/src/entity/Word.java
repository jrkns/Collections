package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Renderable;
import render.Resource;

public class Word implements Renderable {

	private String[] words;
	private int[] x;
	private boolean[] press;
	private boolean isVisible = false;

	public Word(String s) {
		words = new String[s.length()];
		x = new int[s.length()];
		press = new boolean[s.length()];
		press[0] = true;

		for (int i = 0; i < s.length(); i++)
			words[i] = s.substring(i, i + 1);

		if (s.length() % 2 == 1) {
			x[((s.length() + 1) / 2) - 1] = 0;
			for (int i = 1; i <= (s.length() - 1) / 2; i++) {
				x[((s.length() + 1) / 2) + i - 1] = i * 100;
				x[((s.length() + 1) / 2) - i - 1] = i * -100;
			}
		} else {
			x[(s.length() / 2) - 1] = -50;
			x[(s.length() / 2)] = 50;
			for (int i = 1; i <= (s.length() / 2) - 1; i++) {
				x[(s.length() / 2) - 1 - i] = (-50) + i * (-100);
				x[(s.length() / 2) + i] = (50) + i * 100;
			}

		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(Resource.wordFont);
		for (int i = 0; i < words.length; i++) {
			if (press[i]) {
				if (words[0].equalsIgnoreCase("I"))
					g.setColor(new Color(51, 255, 255));
				else if (words[0].equalsIgnoreCase("F"))
					g.setColor(new Color(255, 51, 51));
				else if (words[0].equalsIgnoreCase("M"))
					g.setColor(new Color(153, 51, 255));
				else if (words[0].equalsIgnoreCase("P"))
					g.setColor(new Color(0, 255, 128));
				else
					g.setColor(new Color(255, 255, 51));
			} else
				g.setColor(Color.lightGray);
			g.drawString(words[i], 400 + x[i], 200);
		}
	}

	public void setVisible(boolean b) {
		this.isVisible = b;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public int getZ() {
		return 0;
	}

	public boolean isEqual(Word w) {
		if (this.words.length != w.words.length)
			return false;
		return true;
	}

	public String[] getWord() {
		return words;
	}

	public boolean cast(String s) {
		int w = 0;
		for (int i = 0; i < words.length; i++) {
			if (!press[i] && words[i].equalsIgnoreCase(s))
				press[i] = true;
		}
		if (words.length == s.length()) {
			for (int i = 0; i < words.length; i++)
				if (s.substring(i, i + 1).equalsIgnoreCase(words[i]))
					w++;
			if (w == words.length)
				return true;
		}
		return false;
	}

	public String getString() {
		String str = "";
		for (String e : words)
			str += e;
		return str;
	}

	public void setRed(int i) {
		press[i - 1] = true;
	}

}
