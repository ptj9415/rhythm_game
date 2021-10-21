package co.project.prj;

public class Beat {

	private int time;
	private String noteMane;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getNoteMane() {
		return noteMane;
	}

	public void setNoteMane(String noteMane) {
		this.noteMane = noteMane;
	}

	public Beat(int time, String noteMane) {
		super();
		this.time = time;
		this.noteMane = noteMane;
	}

}
