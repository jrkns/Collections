package ui;

import java.io.*;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class HighScoreUtility {

	public static class HighScoreRecord implements Comparable<HighScoreRecord> {
		private String name = "";
		private int score = 0;

		public HighScoreRecord(String name, int score) {
			this.name = name;
			this.score = score;
		}

		public HighScoreRecord(String record) throws ScoreException {
			if (record.indexOf(":") == -1)
				throw new ScoreException(1);
			try {
				name = record.substring(0, record.indexOf(":"));
				score = Integer
						.parseInt(record.substring(record.indexOf(":") + 1));
			} catch (Exception error) {
				throw new ScoreException(0);
			}
		}

		private String getRecord() {
			return name.trim() + " : " + score + " points";
		}

		private static String[] defaultRecord() {
			return new String[] { "Jr:3000", "Tony:2000", "Fang:1500",
					"Gap:1250", "Bot:1000", "Jerry:750", "Tom:500",
					"Hello:300", "World:100", "Jay:50" };
		}

		@Override
		public int compareTo(HighScoreRecord o) {
			if (this.score > o.score)
				return -1;
			if (this.score < o.score)
				return 1;
			return 0;
		}
	}

	private static HighScoreRecord[] highScoreRecord = null;

	private static String readFileName = "highscore";

	public static void recordHighScore(int score) {
		if (!loadHighScore() || highScoreRecord == null) {
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int index = highScoreRecord.length;
		for (int i = 0; i < highScoreRecord.length; i++) {
			if (score > highScoreRecord[i].score) {
				index = i;
				break;
			}
		}
		if (index >= highScoreRecord.length) {
			JOptionPane.showMessageDialog(null, "Game Over!!" + "\nYou got "
					+ score + " points", "Let's Try Harder",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			for (int i = highScoreRecord.length - 1; i >= index + 1; i--) {
				highScoreRecord[i] = highScoreRecord[i - 1];
			}
			String name = JOptionPane.showInputDialog(null,
					"Ez .. Game Over!! \nYou got " + score + " points"
							+ "\nPlease enter your name", "You are Top 10",
					JOptionPane.INFORMATION_MESSAGE);
			try {
				highScoreRecord[index] = new HighScoreRecord(name.trim() + ":"
						+ score);
			} catch (ScoreException error) {
				// TODO Auto-generated catch block
				error.printStackTrace();
			}
			try {

				BufferedWriter out = new BufferedWriter(new FileWriter(
						"highscore"));
				String leaderBoard = "";
				for (HighScoreRecord e : highScoreRecord) {
					leaderBoard += e.getRecord() + "\n";
				}
				out.write(getXORed(leaderBoard));
				out.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
						"Error saving high score record", "Error",
						JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}

	public static void displayTop10() {
		if (!loadHighScore() || highScoreRecord == null) {
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String msg = "======= Top 10 players ======="
				+ System.getProperty("line.separator");
		int rank = 1;
		for (HighScoreRecord record : highScoreRecord) {
			msg += rank + ")  " + record.getRecord()
					+ System.getProperty("line.separator");
			rank++;
		}
		JOptionPane.showMessageDialog(null, msg.trim(), "Leader Board",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private static boolean loadHighScore() {
		File f = new File(readFileName);
		if (!f.exists()) {
			if (!createDefaultScoreFile())
				return false;
		}
		if (!readAndParseScoreFile(f)) {
			f.delete();
			if (!createDefaultScoreFile())
				return false;
			return readAndParseScoreFile(f);
		}
		return true;

	}

	private static boolean readAndParseScoreFile(File f) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			highScoreRecord = new HighScoreRecord[10];
			String str = "";
			int c;
			while ((c = in.read()) != -1) {
				str += (char) c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for (int i = 0; i < highScoreRecord.length; i++) {
				try {
					highScoreRecord[i] = new HighScoreRecord(records[i]);
				} catch (ScoreException e) {
					System.err.println("Error parsing line " + (i + 1) + ", "
							+ e.getMessage());
					highScoreRecord[i] = new HighScoreRecord("ERROR_RECORD", 0);
				}
			}
			Arrays.sort(highScoreRecord);
			return true;
		} catch (Exception e) {
			highScoreRecord = null;
		}
		return false;
	}

	private static boolean createDefaultScoreFile() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("highscore"));
			String str = "";
			for (String s : HighScoreRecord.defaultRecord()) {
				str += s + "\n";
			}
			str = str.trim();
			out.write(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreRecord = null;
			return false;
		}
	}

	private static final byte[] key = "password1234".getBytes();

	private static String getXORed(String in) {
		byte[] inData = in.getBytes();
		for (int i = 0; i < inData.length; i++) {
			inData[i] = (byte) (inData[i] ^ key[i % key.length]);
		}
		return new String(inData);
	}

	public static void setReadFileName(String name) {
		readFileName = name;
	}
}
