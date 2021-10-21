package co.project.prj;

import java.io.BufferedInputStream;


//import java.io.File;
//import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private Player player;
	private boolean isLoop;
//	private File file;
//	private FileInputStream fis;
	InputStream inputStream;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
//			file = new File(GB.class.getResource("/Music/" + name).toURI());
//			fis = new FileInputStream(file);
			inputStream = getClass().getResource("/Music/" + name).openStream();
			bis = new BufferedInputStream(inputStream);

			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
//				fis = new FileInputStream(file);
				bis = new BufferedInputStream(inputStream);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
