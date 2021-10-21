package co.project.prj;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BeatMaker extends JFrame {

	private static final long serialVersionUID = 1L;
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image introImage = new ImageIcon(GB.class.getResource("/Images/introMain.png")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(GB.class.getResource("/Images/menuBar.png")));

	private ImageIcon exitButtonEnteredImage = new ImageIcon(GB.class.getResource("/Images/exit2.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(GB.class.getResource("/Images/exit1.png"));

	private ImageIcon startButtonEnteredImage = new ImageIcon(GB.class.getResource("/Images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(GB.class.getResource("/Images/startButtonBasic.png"));

	private ImageIcon quitButtonEnteredImage = new ImageIcon(GB.class.getResource("/Images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(GB.class.getResource("/Images/quitButtonBasic.png"));

	private ImageIcon leftArrowEnteredImage = new ImageIcon(GB.class.getResource("/Images/leftArrowEntered.png"));
	private ImageIcon leftArrowBasicImage = new ImageIcon(GB.class.getResource("/Images/leftArrowBasic.png"));

	private ImageIcon rightArrowEnteredImage = new ImageIcon(GB.class.getResource("/Images/rightArrowEntered.png"));
	private ImageIcon rightArrowBasicImage = new ImageIcon(GB.class.getResource("/Images/rightArrowBasic.png"));

	private ImageIcon easyButtonEnteredImage = new ImageIcon(GB.class.getResource("/Images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(GB.class.getResource("/Images/easyButtonBasic.png"));

	private ImageIcon hardButtonEnteredImage = new ImageIcon(GB.class.getResource("/Images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(GB.class.getResource("/Images/hardButtonBasic.png"));

	private ImageIcon backButtonEnteredImage = new ImageIcon(GB.class.getResource("/Images/backEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(GB.class.getResource("/Images/backBasic.png"));
	
	Image[] ImgArr;
	
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);

	private JButton leftButton = new JButton(leftArrowBasicImage);
	private JButton rightButton = new JButton(rightArrowBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	private boolean isResultFrame = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	
	private Music selectedMusic;
	private Image selectedImage;
	private Image titleImage;
	Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;

	public static Game game;
	public String score;

	public BeatMaker() {
		
		trackList.add(new Track("game1Title.png", "game1Start.png", "gameMain1.png", "gameMusic1HighLight.mp3",
				"gameMusic1.mp3", "Hope", 8000));
		trackList.add(new Track("game2Title.png", "game2Start.png", "gameMain2.png", "gameMusic2HighLight.mp3",
				"gameMusic2.mp3", "CandyLand", 94000));
		trackList.add(new Track("game3Title.png", "game3Start.png", "gameMain3.png", "gameMusic3HighLight.mp3",
				"gameMusic3.mp3", "Spectre", 8000));
		
		setUndecorated(true);
		setTitle("BeatMaker");
		setSize(GB.SCREEN_WIDTH, GB.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		addKeyListener(new KeyListener());

		introMusic.start();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(40, 470, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(40, 600, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(quitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 64, 64);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftArrowEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftArrowBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 64, 64);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightArrowEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightArrowBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 64, 64);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				backMain();
			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}
	// 게임 창에 이미지를 출력시켜주는 method
	public void paint(Graphics g) {
		screenImage = createImage(GB.SCREEN_WIDTH, GB.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	// 게임 화면에 따라 다른 이미지를 출력시켜주는 method
	public void screenDraw(Graphics2D g) {
		g.drawImage(introImage, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen) {
			game.screenDraw(g);
		}
		if(isResultFrame) {
			game.resultFrame(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(GB.class.getResource("/Images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(GB.class.getResource("/Images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		introImage = new ImageIcon(GB.class.getResource("/Images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic(),trackList.get(nowSelected).getPlaytime());
		game.start();
		setFocusable(true);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				isResultFrame = true;
			}
		};
		timer.schedule(task, trackList.get(nowSelected).getPlaytime());
	}
	
	public void gameFinished(Game game) {
		score = game.score.getScore();
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introImage = new ImageIcon(GB.class.getResource("/Images/mainPage.png")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		isResultFrame = false;
		game.close();
	}

	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		introImage = new ImageIcon(GB.class.getResource("/Images/mainPage.png")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
}
