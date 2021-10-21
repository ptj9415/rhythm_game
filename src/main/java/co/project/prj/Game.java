package co.project.prj;

import java.awt.Color;




import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	// 게임에 필요한 이미지 파일을 불러오기 위한 변수 선언

	private Image noteRouteLineImage = new ImageIcon(GB.class.getResource("/Images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(GB.class.getResource("/Images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(GB.class.getResource("/Images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteSP1Image = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteSP2Image = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
	private Image keyPadSImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadSP1Image = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadSP2Image = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	private Image scorebackgroundImage = new ImageIcon(GB.class.getResource("/Images/scorebackground.png")).getImage();
	private Image blueFlareImage;
	private Image judgeImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
//	private int playtime;

	Score score = new Score();

	private boolean gameMaker = false;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle, int playtime) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
//		this.playtime = playtime;
		gameMusic = new Music(this.musicTitle, false);

	}

	// 게임 화면에 출력될 화면 생성을 위한 method 생성
	public void screenDraw(Graphics2D g) {

		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSP1Image, 540, 30, null);
		g.drawImage(noteRouteSP2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);

		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);

		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(GB.class.getResource("/Images/miss.png")).getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.BLUE);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawImage(blueFlareImage, 390, 430, null);
		g.drawImage(judgeImage, 460, 420, null);
		g.drawString(score.getScore(), 565, 702);

		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSP1Image, 540, 580, null);
		g.drawImage(keyPadSP2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);

	}

	public void resultFrame(Graphics2D g) {

		String grade = null;
		int totalScore = Integer.parseInt(score.getScore());
		if (totalScore > 9500) {
			grade = "S";
		} else if (totalScore > 6000) {
			grade = "A";
		} else if (totalScore > 3000) {
			grade = "B";
		} else if (totalScore >= 0) {
			grade = "C";
		}
		g.drawImage(scorebackgroundImage, 253, 45, null);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.setColor(Color.white);
		g.drawString(score.getScore(), 500, 290);
		g.setColor(Color.pink);
		g.drawString(grade, 600, 400);
	}

	// 키보드를 입력 시 이미지 변경 및 사운드 출력
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " S");
		}
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " D");
		}
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " F");
		}
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	public void pressSP() {
		judge("SP");
		noteRouteSP1Image = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		noteRouteSP2Image = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadSP1Image = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		keyPadSP2Image = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumBig1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " SP");
		}
	}

	public void releaseSP() {
		noteRouteSP1Image = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		noteRouteSP2Image = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadSP1Image = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
		keyPadSP2Image = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " J");
		}
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " K");
		}
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(GB.class.getResource("/Images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(GB.class.getResource("/Images/keypad.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
		if (gameMaker == true) {
			System.out.println(gameMusic.getTime() + " L");
		}
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(GB.class.getResource("/Images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(GB.class.getResource("/Images/keypadbasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(titleName);
	}

	public void close() { // 실행되는 음악을 종료하는 method
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) { // 게임에 출력되는 note를 만드는 method
		Beat[] beats = null;
		if (titleName.equals("Hope") && difficulty.equals("Easy")) {
			int startTime = 1000 - GB.REACH_TIME * 1000;
//			int gap = 228;
			beats = new Beat[] { new Beat(startTime, "S"),
					

			};
		} else if (titleName.equals("CandyLand") && difficulty.equals("Easy")) {
			int startTime = 100;
			int gap = 470;
			beats = new Beat[] { new Beat(startTime, "S"),					
					new Beat(startTime + gap *2, "D"),
					new Beat(startTime + gap *4, "L"),
					new Beat(startTime + gap *6, "K"),
					new Beat(startTime + gap *8, "F"),
					new Beat(startTime + gap *10, "F"),
					new Beat(startTime + gap *12, "J"),
					new Beat(startTime + gap *14, "J"),
					new Beat(startTime + gap *16, "D"),
					new Beat(startTime + gap *18, "K"),
					new Beat(startTime + gap *20, "F"),
					new Beat(startTime + gap *22, "J"),
					new Beat(startTime + gap *24, "S"),
					new Beat(startTime + gap *26, "L"),
					new Beat(startTime + gap *28, "L"),
					new Beat(startTime + gap *30, "S"),
					new Beat(startTime + gap *32, "D"),
					new Beat(startTime + gap *34, "F"),
					new Beat(startTime + gap *36, "K"),
					new Beat(startTime + gap *38, "J"),
					new Beat(startTime + gap *40, "F"),
					new Beat(startTime + gap *42, "J"),
					new Beat(startTime + gap *44, "D"),
					new Beat(startTime + gap *46, "K"),
					new Beat(startTime + gap *48, "S"),
					new Beat(startTime + gap *50, "L"),
					new Beat(startTime + gap *52, "L"),
					new Beat(startTime + gap *54, "S"),
					new Beat(startTime + gap *56, "K"),
					new Beat(startTime + gap *58, "D"),
					new Beat(startTime + gap *60, "J"),
					new Beat(startTime + gap *62, "F"),
					new Beat(startTime + gap *64, "D"),
					new Beat(startTime + gap *66, "S"),
					new Beat(startTime + gap *68, "F"),
					new Beat(startTime + gap *70, "K"),
					new Beat(startTime + gap *72, "J"),
					new Beat(startTime + gap *74, "L"),
					new Beat(startTime + gap *76, "D"),
					new Beat(startTime + gap *78, "F"),
					new Beat(startTime + gap *80, "S"),
					new Beat(startTime + gap *82, "D"),
					new Beat(startTime + gap *84, "K"),
					new Beat(startTime + gap *86, "J"),
					new Beat(startTime + gap *88, "L"),
					new Beat(startTime + gap *90, "J"),
					new Beat(startTime + gap *92, "F"),
					new Beat(startTime + gap *94, "J"),
					new Beat(startTime + gap *96, "S"),
					new Beat(startTime + gap *98, "L"),
					new Beat(startTime + gap *100, "D"),
					new Beat(startTime + gap *102, "K"),
					new Beat(startTime + gap *104, "K"),
					new Beat(startTime + gap *106, "D"),
					new Beat(startTime + gap *108, "L"),
					new Beat(startTime + gap *110, "J"),
					new Beat(startTime + gap *112, "S"),
					new Beat(startTime + gap *114, "D"),
					new Beat(startTime + gap *116, "K"),
					new Beat(startTime + gap *118, "F"),
					new Beat(startTime + gap *120, "D"),
					new Beat(startTime + gap *122, "S"),
					new Beat(startTime + gap *124, "S"),
					new Beat(startTime + gap *126, "F"),
					new Beat(startTime + gap *128, "K"),
					new Beat(startTime + gap *130, "S"),
					new Beat(startTime + gap *132, "L"),
					new Beat(startTime + gap *134, "F"),
					new Beat(startTime + gap *136, "J"),
					new Beat(startTime + gap *138, "D"),
					new Beat(startTime + gap *140, "S"),
					new Beat(startTime + gap *142, "S"),
					new Beat(startTime + gap *144, "D"),
					new Beat(startTime + gap *146, "D"),
					new Beat(startTime + gap *148, "F"),
					new Beat(startTime + gap *150, "F"),
					new Beat(startTime + gap *152, "L"),
					new Beat(startTime + gap *154, "L"),
					new Beat(startTime + gap *156, "K"),
					new Beat(startTime + gap *158, "K"),
					new Beat(startTime + gap *160, "J"),
					new Beat(startTime + gap *162, "J"),
					new Beat(startTime + gap *164, "S"),
					new Beat(startTime + gap *166, "K"),
					new Beat(startTime + gap *168, "D"),
					new Beat(startTime + gap *170, "F"),
					new Beat(startTime + gap *172, "J"),
					new Beat(startTime + gap *174, "L"),
					new Beat(startTime + gap *176, "K"),
					new Beat(startTime + gap *178, "K"),
					new Beat(startTime + gap *180, "L"),
					new Beat(startTime + gap *182, "F"),
					new Beat(startTime + gap *184, "D"),



			};
		} else if (titleName.equals("Spectre") && difficulty.equals("Easy")) {
			int startTime = 1000 - GB.REACH_TIME * 1000;
//			int gap = 215;
			beats = new Beat[] { new Beat(startTime, "S"), 

			};

		} else if (titleName.equals("Hope") && difficulty.equals("Hard")) {
			int startTime = 1000 - GB.REACH_TIME * 1000;
			beats = new Beat[] { new Beat(startTime, "S"),

			};

		} else if (titleName.equals("CandyLand") && difficulty.equals("Hard")) {
			int startTime = 3300 - GB.REACH_TIME * 1000;
			int gap = 235;
			beats = new Beat[] { 
//					new Beat(startTime, "S"), new Beat(startTime + gap * 1, "F"), new Beat(startTime + gap * 5, "K"),
//					new Beat(startTime + gap * 7, "S"), new Beat(startTime + gap * 10, "K"),
//					new Beat(startTime + gap * 11, "K"), new Beat(startTime + gap * 13, "D"),
//					new Beat(startTime + gap * 16, "K"), new Beat(startTime + gap * 17, "K"),
//					new Beat(startTime + gap * 18, "F"), new Beat(startTime + gap * 20, "K"),
//					new Beat(startTime + gap * 21, "K"), new Beat(startTime + gap * 22, "F"),
//					new Beat(startTime + gap * 24, "F"), new Beat(startTime + gap * 26, "K"),
//					new Beat(startTime + gap * 27, "K"), new Beat(startTime + gap * 30, "F"),
//					new Beat(startTime + gap * 31, "K"), new Beat(startTime + gap * 32, "S"),
//					new Beat(startTime + gap * 36, "D"), new Beat(startTime + gap * 37, "K"),
//					new Beat(startTime + gap * 39, "K"), new Beat(startTime + gap * 42, "F"),
//					new Beat(startTime + gap * 44, "K"), new Beat(startTime + gap * 46, "S"),
//					new Beat(startTime + gap * 50, "F"), new Beat(startTime + gap * 52, "K"),
//					new Beat(startTime + gap * 53, "L"), new Beat(startTime + gap * 56, "J"),
//					new Beat(startTime + gap * 58, "K"), new Beat(startTime + gap * 60, "S"),
//					new Beat(startTime + gap * 62, "S"), new Beat(startTime + gap * 64, "K"),
//					new Beat(startTime + gap * 66, "K"), new Beat(startTime + gap * 69, "S"),
//					new Beat(startTime + gap * 73, "K"), new Beat(startTime + gap * 75, "F"),
//					new Beat(startTime + gap * 78, "K"), new Beat(startTime + gap * 80, "K"),
//					new Beat(startTime + gap * 81, "D"), new Beat(startTime + gap * 83, "J"),
//					new Beat(startTime + gap * 85, "K"), new Beat(startTime + gap * 86, "K"),
//					new Beat(startTime + gap * 97, "SP"), new Beat(startTime + gap * 99, "K"),
//					new Beat(startTime + gap * 100, "S"), new Beat(startTime + gap * 102, "D"),
//					new Beat(startTime + gap * 104, "K"), new Beat(startTime + gap * 106, "F"),
//					new Beat(startTime + gap * 110, "D"), new Beat(startTime + gap * 115, "K"),
//					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 124, "F"),
//					new Beat(startTime + gap * 126, "K"), new Beat(startTime + gap * 127, "SP"),
//					new Beat(startTime + gap * 129, "F"), new Beat(startTime + gap * 130, "K"),
//					new Beat(startTime + gap * 132, "K"), new Beat(startTime + gap * 136, "D"),
//					new Beat(startTime + gap * 137, "K"), new Beat(startTime + gap * 139, "K"),
//					new Beat(startTime + gap * 142, "F"), new Beat(startTime + gap * 144, "K"),
//					new Beat(startTime + gap * 146, "K"), new Beat(startTime + gap * 150, "F"),
//					new Beat(startTime + gap * 152, "K"), new Beat(startTime + gap * 153, "F"),
//					new Beat(startTime + gap * 156, "J"), new Beat(startTime + gap * 158, "K"),
//					new Beat(startTime + gap * 10, "K"), new Beat(startTime + gap * 160, "S"),
//					new Beat(startTime + gap * 162, "S"), new Beat(startTime + gap * 164, "K"),
//					new Beat(startTime + gap * 166, "K"), new Beat(startTime + gap * 169, "SP"),
//					new Beat(startTime + gap * 170, "K"), new Beat(startTime + gap * 171, "S"),
//					new Beat(startTime + gap * 174, "K"), new Beat(startTime + gap * 176, "K"),
//					new Beat(startTime + gap * 178, "D"), new Beat(startTime + gap * 181, "F"),
//					new Beat(startTime + gap * 182, "K"), new Beat(startTime + gap * 183, "J"),
//					new Beat(startTime + gap * 186, "K"), new Beat(startTime + gap * 190, "K"),
//					new Beat(startTime + gap * 194, "SP"), new Beat(startTime + gap * 198, "S"),
//					new Beat(startTime + gap * 205, "K"), new Beat(startTime + gap * 212, "D"),
//					new Beat(startTime + gap * 216, "F"), new Beat(startTime + gap * 219, "K"),
//					new Beat(startTime + gap * 220, "F"), new Beat(startTime + gap * 224, "SP"),
//					new Beat(startTime + gap * 225, "K"), new Beat(startTime + gap * 227, "K"),
//					new Beat(startTime + gap * 229, "F"), new Beat(startTime + gap * 230, "K"),
//					new Beat(startTime + gap * 232, "K"), new Beat(startTime + gap * 236, "D"),
//					new Beat(startTime + gap * 237, "K"), new Beat(startTime + gap * 239, "K"),
//					new Beat(startTime + gap * 242, "F"), new Beat(startTime + gap * 243, "K"),
//					new Beat(startTime + gap * 246, "K"), new Beat(startTime + gap * 250, "F"),
//					new Beat(startTime + gap * 251, "K"), new Beat(startTime + gap * 253, "L"),
//					new Beat(startTime + gap * 256, "J"), new Beat(startTime + gap * 257, "K"),
//					new Beat(startTime + gap * 260, "S"), new Beat(startTime + gap * 262, "S"),
//					new Beat(startTime + gap * 263, "K"), new Beat(startTime + gap * 266, "K"),
//					new Beat(startTime + gap * 269, "S"), new Beat(startTime + gap * 270, "K"),
//					new Beat(startTime + gap * 271, "S"), new Beat(startTime + gap * 274, "K"),
//					new Beat(startTime + gap * 275, "K"), new Beat(startTime + gap * 278, "D"),
//					new Beat(startTime + gap * 281, "F"), new Beat(startTime + gap * 282, "K"),
//					new Beat(startTime + gap * 283, "J"), new Beat(startTime + gap * 286, "K"),
//					new Beat(startTime + gap * 287, "K"), new Beat(startTime + gap * 297, "SP"),
//					new Beat(startTime + gap * 300, "S"), new Beat(startTime + gap * 301, "K"),
//					new Beat(startTime + gap * 302, "D"), new Beat(startTime + gap * 306, "F"),
//					new Beat(startTime + gap * 307, "K"), new Beat(startTime + gap * 310, "J"),
//					new Beat(startTime + gap * 320, "S"), new Beat(startTime + gap * 321, "K"),
//					new Beat(startTime + gap * 322, "L"), new Beat(startTime + gap * 324, "F"),
//					new Beat(startTime + gap * 326, "K"), new Beat(startTime + gap * 327, "K"),
//					new Beat(startTime + gap * 329, "F"), new Beat(startTime + gap * 330, "K"),
//					new Beat(startTime + gap * 332, "K"), new Beat(startTime + gap * 336, "D"),
//					new Beat(startTime + gap * 337, "K"), new Beat(startTime + gap * 339, "K"),
//					new Beat(startTime + gap * 342, "F"), new Beat(startTime + gap * 343, "K"),
//					new Beat(startTime + gap * 346, "K"), new Beat(startTime + gap * 350, "F"),
//					new Beat(startTime + gap * 351, "K"), new Beat(startTime + gap * 353, "L"),
//					new Beat(startTime + gap * 356, "J"), new Beat(startTime + gap * 357, "K"),
//					new Beat(startTime + gap * 360, "S"), new Beat(startTime + gap * 362, "S"),
//					new Beat(startTime + gap * 363, "K"), new Beat(startTime + gap * 366, "K"),
//					new Beat(startTime + gap * 369, "L"), new Beat(startTime + gap * 370, "K"),
//					new Beat(startTime + gap * 371, "S"), new Beat(startTime + gap * 374, "K"),
//					new Beat(startTime + gap * 375, "K"), new Beat(startTime + gap * 378, "D"),
//					new Beat(startTime + gap * 381, "F"), new Beat(startTime + gap * 382, "K"),
					new Beat(startTime, "J"), 
					new Beat(startTime + gap *1, "K"),
					new Beat(startTime + gap *2, "L"),
					new Beat(startTime + gap *3, "K"),
					new Beat(startTime + gap *4, "J"),
					new Beat(startTime + gap *6, "K"),
					new Beat(startTime + gap *6, "SP"),
					new Beat(startTime + gap *8, "F"),
					new Beat(startTime + gap *9, "D"),
					new Beat(startTime + gap *10, "S"),
					new Beat(startTime + gap *11, "D"),
					new Beat(startTime + gap *12, "F"),
					new Beat(startTime + gap *14, "D"),
					new Beat(startTime + gap *14, "SP"),
					new Beat(startTime + gap *16, "D"),
					new Beat(startTime + gap *18, "K"),
					new Beat(startTime + gap *20, "S"),
					new Beat(startTime + gap *22, "L"),
					new Beat(startTime + gap *24, "J"),
					new Beat(startTime + gap *26, "F"),
					new Beat(startTime + gap *28, "S"),
					new Beat(startTime + gap *28, "L"),
					new Beat(startTime + gap *30, "D"),
					new Beat(startTime + gap *32, "F"),
					new Beat(startTime + gap *34, "L"),
					new Beat(startTime + gap *35, "J"),
					new Beat(startTime + gap *36, "F"),
					new Beat(startTime + gap *37, "K"),
					new Beat(startTime + gap *38, "S"),
					new Beat(startTime + gap *39, "L"),
					new Beat(startTime + gap *40, "SP"),
					new Beat(startTime + gap *41, "J"),
					new Beat(startTime + gap *42, "F"),
					new Beat(startTime + gap *43, "L"),
					new Beat(startTime + gap *44, "D"),
					new Beat(startTime + gap *45, "J"),
					new Beat(startTime + gap *46, "F"),
					new Beat(startTime + gap *48, "S"),
					new Beat(startTime + gap *50, "L"),
					new Beat(startTime + gap *52, "F"),
					new Beat(startTime + gap *54, "J"),
					new Beat(startTime + gap *56, "D"),
					new Beat(startTime + gap *58, "K"),
					new Beat(startTime + gap *60, "L"),
					new Beat(startTime + gap *62, "S"),
					new Beat(startTime + gap *64, "J"),
					new Beat(startTime + gap *66, "J"),
					new Beat(startTime + gap *68, "F"),
					new Beat(startTime + gap *70, "F"),
					new Beat(startTime + gap *72, "S"),
					new Beat(startTime + gap *74, "L"),
					new Beat(startTime + gap *76, "D"),
					new Beat(startTime + gap *78, "K"),
					new Beat(startTime + gap *80, "F"),
					new Beat(startTime + gap *82, "J"),
					new Beat(startTime + gap *84, "D"),
					new Beat(startTime + gap *86, "S"),
					new Beat(startTime + gap *88, "F"),
					new Beat(startTime + gap *90, "K"),
					new Beat(startTime + gap *92, "L"),
					new Beat(startTime + gap *94, "J"),
					new Beat(startTime + gap *96, "S"),
					new Beat(startTime + gap *96, "L"),
					new Beat(startTime + gap *97, "K"),
					new Beat(startTime + gap *98, "L"),
					new Beat(startTime + gap *99, "K"),
					new Beat(startTime + gap *100, "J"),
					new Beat(startTime + gap *102, "K"),
					new Beat(startTime + gap *102, "SP"),
					new Beat(startTime + gap *104, "F"),
					new Beat(startTime + gap *105, "D"),
					new Beat(startTime + gap *106, "S"),
					new Beat(startTime + gap *107, "D"),
					new Beat(startTime + gap *108, "F"),
					new Beat(startTime + gap *110, "D"),
					new Beat(startTime + gap *110, "SP"),
					new Beat(startTime + gap *112, "D"),
					new Beat(startTime + gap *114, "K"),
					new Beat(startTime + gap *116, "S"),
					new Beat(startTime + gap *118, "L"),
					new Beat(startTime + gap *120, "J"),
					new Beat(startTime + gap *124, "F"),
					new Beat(startTime + gap *126, "S"),
					new Beat(startTime + gap *126, "L"),
					new Beat(startTime + gap *128, "SP"),
					new Beat(startTime + gap *130, "D"),
					new Beat(startTime + gap *131, "J"),
					new Beat(startTime + gap *132, "S"),
					new Beat(startTime + gap *133, "J"),
					new Beat(startTime + gap *134, "L"),
					new Beat(startTime + gap *135, "K"),
					new Beat(startTime + gap *136, "F"),
					new Beat(startTime + gap *137, "S"),
					new Beat(startTime + gap *138, "K"),
					new Beat(startTime + gap *139, "J"),
					new Beat(startTime + gap *140, "F"),
					new Beat(startTime + gap *141, "L"),
					new Beat(startTime + gap *142, "D"),
					new Beat(startTime + gap *143, "J"),
					new Beat(startTime + gap *144, "S"),
					new Beat(startTime + gap *145, "F"),
					new Beat(startTime + gap *146, "K"),
					new Beat(startTime + gap *147, "L"),
					new Beat(startTime + gap *148, "S"),
					new Beat(startTime + gap *149, "F"),
					new Beat(startTime + gap *150, "D"),
					new Beat(startTime + gap *151, "K"),
					new Beat(startTime + gap *152, "L"),
					new Beat(startTime + gap *153, "J"),
					new Beat(startTime + gap *154, "D"),
					new Beat(startTime + gap *155, "F"),
					new Beat(startTime + gap *156, "S"),
					new Beat(startTime + gap *157, "D"),
					new Beat(startTime + gap *158, "F"),
					new Beat(startTime + gap *159, "L"),
					new Beat(startTime + gap *160, "K"),
					new Beat(startTime + gap *161, "K"),
					new Beat(startTime + gap *162, "J"),
					new Beat(startTime + gap *163, "L"),
					new Beat(startTime + gap *164, "S"),
					new Beat(startTime + gap *165, "F"),
					new Beat(startTime + gap *166, "S"),
					new Beat(startTime + gap *167, "D"),
					new Beat(startTime + gap *168, "K"),
					new Beat(startTime + gap *169, "F"),
					new Beat(startTime + gap *170, "L"),
					new Beat(startTime + gap *171, "S"),
					new Beat(startTime + gap *172, "J"),
					new Beat(startTime + gap *173, "D"),
					new Beat(startTime + gap *174, "F"),
					new Beat(startTime + gap *175, "L"),
					new Beat(startTime + gap *176, "J"),
					new Beat(startTime + gap *177, "D"),
					new Beat(startTime + gap *178, "F"),
					new Beat(startTime + gap *179, "L"),
					new Beat(startTime + gap *180, "S"),
					new Beat(startTime + gap *181, "D"),
					new Beat(startTime + gap *182, "F"),
					new Beat(startTime + gap *183, "J"),
					new Beat(startTime + gap *184, "K"),
					new Beat(startTime + gap *185, "L"),
					new Beat(startTime + gap *186, "F"),
					new Beat(startTime + gap *187, "D"),
					new Beat(startTime + gap *188, "S"),
					new Beat(startTime + gap *189, "F"),
					new Beat(startTime + gap *190, "L"),
					new Beat(startTime + gap *191, "J"),
					new Beat(startTime + gap *192, "D"),
					new Beat(startTime + gap *193, "F"),
					new Beat(startTime + gap *194, "J"),
					new Beat(startTime + gap *195, "L"),
					new Beat(startTime + gap *196, "D"),
					new Beat(startTime + gap *197, "S"),
					new Beat(startTime + gap *198, "F"),
					new Beat(startTime + gap *199, "J"),
					new Beat(startTime + gap *200, "K"),
					new Beat(startTime + gap *201, "L"),
					new Beat(startTime + gap *202, "J"),
					new Beat(startTime + gap *203, "D"),
					new Beat(startTime + gap *204, "S"),
					new Beat(startTime + gap *205, "F"),
					new Beat(startTime + gap *206, "L"),
					new Beat(startTime + gap *207, "K"),
					new Beat(startTime + gap *208, "J"),
					new Beat(startTime + gap *209, "D"),
					new Beat(startTime + gap *210, "S"),
					new Beat(startTime + gap *211, "F"),
					new Beat(startTime + gap *212, "J"),
					new Beat(startTime + gap *213, "K"),
					new Beat(startTime + gap *214, "J"),
					new Beat(startTime + gap *215, "F"),
					new Beat(startTime + gap *216, "D"),
					new Beat(startTime + gap *217, "F"),
					new Beat(startTime + gap *218, "S"),
					new Beat(startTime + gap *219, "D"),
					new Beat(startTime + gap *220, "J"),
					new Beat(startTime + gap *221, "L"),
					new Beat(startTime + gap *222, "K"),
					new Beat(startTime + gap *223, "J"),
					new Beat(startTime + gap *224, "L"),
					new Beat(startTime + gap *225, "F"),
					new Beat(startTime + gap *226, "S"),
					new Beat(startTime + gap *227, "S"),
					new Beat(startTime + gap *228, "L"),
					new Beat(startTime + gap *229, "K"),
					new Beat(startTime + gap *230, "J"),
					new Beat(startTime + gap *231, "K"),
					new Beat(startTime + gap *232, "J"),
					new Beat(startTime + gap *233, "L"),
					new Beat(startTime + gap *234, "D"),
					new Beat(startTime + gap *235, "K"),
					new Beat(startTime + gap *236, "F"),
					new Beat(startTime + gap *237, "S"),
					new Beat(startTime + gap *238, "D"),
					new Beat(startTime + gap *239, "K"),
					new Beat(startTime + gap *240, "F"),
					new Beat(startTime + gap *241, "K"),
					new Beat(startTime + gap *242, "D"),
					new Beat(startTime + gap *243, "L"),
					new Beat(startTime + gap *244, "F"),
					new Beat(startTime + gap *245, "J"),
					new Beat(startTime + gap *246, "S"),
					new Beat(startTime + gap *247, "D"),
					new Beat(startTime + gap *248, "F"),
					new Beat(startTime + gap *249, "L"),
					new Beat(startTime + gap *250, "D"),
					new Beat(startTime + gap *251, "F"),
					new Beat(startTime + gap *252, "J"),
					new Beat(startTime + gap *253, "D"),
					new Beat(startTime + gap *254, "L"),
					new Beat(startTime + gap *255, "S"),
					new Beat(startTime + gap *256, "J"),
					new Beat(startTime + gap *257, "F"),
					new Beat(startTime + gap *258, "D"),
					new Beat(startTime + gap *259, "K"),
					new Beat(startTime + gap *260, "F"),
					new Beat(startTime + gap *261, "L"),
					new Beat(startTime + gap *262, "S"),
					new Beat(startTime + gap *263, "F"),
					new Beat(startTime + gap *264, "D"),
					new Beat(startTime + gap *265, "L"),
					new Beat(startTime + gap *266, "K"),
					new Beat(startTime + gap *267, "D"),
					new Beat(startTime + gap *268, "F"),
					new Beat(startTime + gap *269, "J"),
					new Beat(startTime + gap *270, "S"),
					new Beat(startTime + gap *271, "L"),
					new Beat(startTime + gap *272, "D"),
					new Beat(startTime + gap *273, "J"),
					new Beat(startTime + gap *274, "L"),
					new Beat(startTime + gap *275, "D"),
					new Beat(startTime + gap *276, "L"),
					new Beat(startTime + gap *277, "F"),
					new Beat(startTime + gap *278, "D"),
					new Beat(startTime + gap *279, "S"),
					new Beat(startTime + gap *280, "F"),
					new Beat(startTime + gap *281, "K"),
					new Beat(startTime + gap *282, "D"),
					new Beat(startTime + gap *283, "L"),
					new Beat(startTime + gap *284, "F"),
					new Beat(startTime + gap *285, "D"),
					new Beat(startTime + gap *286, "F"),
					new Beat(startTime + gap *287, "S"),
					new Beat(startTime + gap *288, "L"),
					new Beat(startTime + gap *289, "F"),
					new Beat(startTime + gap *290, "K"),
					new Beat(startTime + gap *291, "J"),
					new Beat(startTime + gap *292, "L"),
					new Beat(startTime + gap *293, "D"),
					new Beat(startTime + gap *294, "F"),
					new Beat(startTime + gap *295, "D"),
					new Beat(startTime + gap *296, "L"),
					new Beat(startTime + gap *297, "K"),
					new Beat(startTime + gap *298, "L"),
					new Beat(startTime + gap *299, "K"),
					new Beat(startTime + gap *300, "F"),
					new Beat(startTime + gap *301, "J"),
					new Beat(startTime + gap *302, "S"),
					new Beat(startTime + gap *303, "L"),
					new Beat(startTime + gap *304, "F"),
					new Beat(startTime + gap *305, "J"),
					new Beat(startTime + gap *306, "D"),
					new Beat(startTime + gap *307, "F"),
					new Beat(startTime + gap *308, "L"),
					new Beat(startTime + gap *309, "D"),
					new Beat(startTime + gap *310, "S"),
					new Beat(startTime + gap *311, "F"),
					new Beat(startTime + gap *312, "F"),
					new Beat(startTime + gap *313, "L"),
					new Beat(startTime + gap *314, "J"),
					new Beat(startTime + gap *315, "D"),
					new Beat(startTime + gap *316, "S"),
					new Beat(startTime + gap *317, "F"),
					new Beat(startTime + gap *318, "K"),
					new Beat(startTime + gap *319, "J"),
					new Beat(startTime + gap *320, "L"),
					new Beat(startTime + gap *321, "J"),
					new Beat(startTime + gap *322, "D"),
					new Beat(startTime + gap *323, "F"),
					new Beat(startTime + gap *324, "D"),
					new Beat(startTime + gap *325, "J"),
					new Beat(startTime + gap *326, "K"),
					new Beat(startTime + gap *327, "L"),
					new Beat(startTime + gap *328, "S"),
					new Beat(startTime + gap *329, "F"),
					new Beat(startTime + gap *330, "J"),
					new Beat(startTime + gap *331, "D"),
					new Beat(startTime + gap *332, "L"),
					new Beat(startTime + gap *333, "D"),
					new Beat(startTime + gap *334, "J"),
					new Beat(startTime + gap *335, "D"),
					new Beat(startTime + gap *336, "L"),
					new Beat(startTime + gap *337, "J"),
					new Beat(startTime + gap *338, "D"),
					new Beat(startTime + gap *339, "L"),
					new Beat(startTime + gap *340, "F"),
					new Beat(startTime + gap *341, "S"),
					new Beat(startTime + gap *342, "D"),
					new Beat(startTime + gap *343, "K"),
					new Beat(startTime + gap *344, "F"),
					new Beat(startTime + gap *345, "L"),
					new Beat(startTime + gap *346, "D"),
					new Beat(startTime + gap *347, "F"),
					new Beat(startTime + gap *348, "S"),
					new Beat(startTime + gap *349, "K"),
					new Beat(startTime + gap *350, "D"),
					new Beat(startTime + gap *351, "J"),
					new Beat(startTime + gap *352, "S"),
					new Beat(startTime + gap *353, "L"),
					new Beat(startTime + gap *354, "D"),
					new Beat(startTime + gap *355, "J"),
					new Beat(startTime + gap *356, "K"),
					new Beat(startTime + gap *357, "D"),
					new Beat(startTime + gap *358, "S"),
					new Beat(startTime + gap *359, "J"),
					new Beat(startTime + gap *360, "F"),
					new Beat(startTime + gap *361, "L"),
					new Beat(startTime + gap *362, "D"),
					new Beat(startTime + gap *363, "J"),
					new Beat(startTime + gap *364, "L"),
					new Beat(startTime + gap *365, "S"),
					new Beat(startTime + gap *366, "F"),
					new Beat(startTime + gap *367, "L"),
					new Beat(startTime + gap *368, "J"),
					new Beat(startTime + gap *369, "S"),
					new Beat(startTime + gap *370, "K"),
					new Beat(startTime + gap *371, "S"),
					new Beat(startTime + gap *372, "D"),
					new Beat(startTime + gap *373, "F"),
					new Beat(startTime + gap *374, "K"),
					new Beat(startTime + gap *375, "J"),
					new Beat(startTime + gap *376, "F"),
					new Beat(startTime + gap *377, "K"),
					new Beat(startTime + gap *378, "J"),
					new Beat(startTime + gap *379, "L"),
					new Beat(startTime + gap *380, "K"),
					new Beat(startTime + gap *381, "S"),



			};

		} else if (titleName.equals("Spectre") && difficulty.equals("Hard")) {
			int startTime = 2800 - GB.REACH_TIME * 1000;
			beats = new Beat[] { new Beat(startTime, "S"),

			};
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteMane(), score, difficulty);
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5); //Thread close 확인
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}	
	
	
	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public Score getScore() {
		return score;
	}

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			blueFlareImage = new ImageIcon(GB.class.getResource("/Images/flare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(GB.class.getResource("/Images/miss.png")).getImage();
		}
		if (judge.equals("Good")) {
			judgeImage = new ImageIcon(GB.class.getResource("/Images/good.png")).getImage();
		}
		if (judge.equals("Great")) {
			judgeImage = new ImageIcon(GB.class.getResource("/Images/great.png")).getImage();
		}
		if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(GB.class.getResource("/Images/perfect.png")).getImage();
		}
	}
}
