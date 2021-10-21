package co.project.prj;

import java.awt.Image;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon(GB.class.getResource("/Images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / GB.SLEEP_TIME * GB.EASY_SPEED) * GB.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	Score score;
	String difficulty;
	
	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String noteType, Score score, String difficulty) {
		this.score = score;
		this.difficulty = difficulty;
		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("SP")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (noteType.equals("SP")) {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		} else {
			g.drawImage(noteBasicImage, x, y, null);
		}
	}

	public void easydrop() {
		y += GB.EASY_SPEED;
		if (y > 620) {
//			System.out.println("Miss");
			close();
		}
	}
	
	public void harddrop() {
		y += GB.HARD_SPEED;
		if (y > 620) {
//			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				if(difficulty.equals("Easy")) {
					easydrop();
				} else {
					harddrop();
				}
				if (proceeded) {
					Thread.sleep(GB.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
				Thread.sleep(GB.SLEEP_TIME);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public String judge() {
		if (y >= 613) {
//			System.out.println("Miss");
			close();
			score.setScore(0);
			return "Miss";
		} else if (y >= 600) {
//			System.out.println("Good");
			close();
			score.setScore(30);
			return "Good";
		} else if (y >= 587) {
//			System.out.println("Great");
			close();
			score.setScore(50);
			return "Great";
		} else if (y >= 573) {
//			System.out.println("Perfect");
			close();
			score.setScore(100);
			return "Perfect";
		} else if (y >= 565) {
//			System.out.println("Great");
			close();
			score.setScore(50);
			return "Great";
		} else if (y >= 550) {
//			System.out.println("Good");
			close();
			score.setScore(30);
			return "Good";
		} else if (y >= 535) {
//			System.out.println("Miss");
			score.setScore(10);
			close();
			return "Miss ";
		}
		return "None";

	}
	
	public int getY() {
		return y;
	}

}
