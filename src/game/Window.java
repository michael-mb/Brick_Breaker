package game;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{

	private Scene myScene ;

	public Window() {
		initParts();
		initWindow();
		initListener();
		this.setVisible(true);
	}

	private void initParts() {
		myScene = new Scene();
	}

	private void initWindow() {
	
		this.setTitle("Brick Breaker");
		this.setSize(600,522);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setLocationRelativeTo(null);
		this.add(myScene , BorderLayout.CENTER);
	}
	
	private void initListener() {
		KeyboardListener keyboardListener = new KeyboardListener();
		this.addKeyListener(keyboardListener);
	}

	class KeyboardListener implements KeyListener{

		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode() == 37) {
				myScene.moveLeft();
			}
			else if(event.getKeyCode() == 39) {
				myScene.moveRigth();
			}
		}

		public void keyReleased(KeyEvent event) {
			if(event.getKeyCode() == 32) {
				myScene.start();
			}
			if(event.getKeyCode() == 82) {
				myScene.reset();
			}

		}
		public void keyTyped(KeyEvent event) {}
	}
}
