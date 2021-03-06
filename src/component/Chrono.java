package component;


import game.Scene;

public class Chrono implements Runnable {

	private Scene scene ;
	private boolean run = true  ;
	private final int PAUSE = 10 ;
	
	public Chrono(Scene scene) {
		this.scene = scene ;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		
		while(run) {
			scene.repaint();
			
			try {
				Thread.sleep(PAUSE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
