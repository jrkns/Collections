package input;

public class InputUtility {

	private static boolean[] keyPressed = new boolean[256];
	private static boolean escTriggered = false;
	private static boolean escPressed = false;
	private static String spell = "";

	public static boolean getKeyPressed(int key) {
		if (key < 0 || key > 255)
			return false;
		return keyPressed[key];
	}

	public static void setKeyPressed(int key, boolean keyPressed) {
		if (key < 0 || key > 255)
			return;
		InputUtility.keyPressed[key] = keyPressed;
	}

	public static String getSpell() {
		return spell;
	}

	public static void setSpell(String s) {
		spell += s;
	}

	public static void clearSpell() {
		spell = "";
	}

	public static boolean getEscTriggered() {
		return escTriggered;
	}

	public static void setEscTriggered(boolean x) {
		escTriggered = x;
	}

	public static boolean getEscPressed() {
		return escPressed;
	}

	public static void setEscPressed(boolean x) {
		escPressed = x;
	}

	public static void reset() {
		keyPressed = new boolean[256];
	}

}
