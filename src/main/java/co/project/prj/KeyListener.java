package co.project.prj;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	// 키보드 입력을 감지할 수 있는 메소드
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (BeatMaker.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			BeatMaker.game.pressS();

		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			BeatMaker.game.pressD();

		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			BeatMaker.game.pressF();

		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			BeatMaker.game.pressSP();

		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			BeatMaker.game.pressJ();

		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			BeatMaker.game.pressK();

		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			BeatMaker.game.pressL();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (BeatMaker.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			BeatMaker.game.releaseS();
			
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			BeatMaker.game.releaseD();
			
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			BeatMaker.game.releaseF();
			
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			BeatMaker.game.releaseSP();
			
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			BeatMaker.game.releaseJ();
			
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			BeatMaker.game.releaseK();
			
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			BeatMaker.game.releaseL();
		}
	}
}
