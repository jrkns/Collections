package ui;

public class ScoreException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int type;

	public ScoreException(int type) {
		this.type = type;
	}

	@Override
	public String getMessage() {
		if (type == 0)
			return "record score not found";
		return "Wrong record score format";
	}
}
