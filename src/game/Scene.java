package game;
import component.Ball;
import component.Breaker;
import component.Chrono;

import java.awt.*;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Scene extends JPanel{

	private final int HEIGHT = 481; 		// Stage height
	private final int WIDTH = 582;			// Stage Width
	
	private Chrono chrono;					// time
	private Breaker breaker;
	private Ball ball;
	
	public Scene() {
		
		chrono = new Chrono(this);
		breaker = new Breaker(WIDTH / 2 - 45 ,100 , 10);
		ball = new Ball(30 , 30 , WIDTH / 2 - 10 , 200);
		this.setBackground(Color.black);

	}
	
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 drawLimits(g2);

		 if(breaker.isInLife()){
			 drawBall(g2);
			 drawBreaker(g2);
		 }

	}

	private void drawLimits(Graphics2D g2){
		g2.setColor(Color.yellow);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(1,1,WIDTH,HEIGHT);
	}

	private void drawBreaker(Graphics2D g2){
		g2.setColor(Color.white);
		g2.fillRect(breaker.getPosX(),HEIGHT - 10,breaker.getWidth(),breaker.getHeight());
	}

	private void drawBall(Graphics2D g2){

		g2.setColor(Color.white);
		g2.fillOval(ball.getPosX() , ball.getPosY() , ball.getWidth() , ball.getHeight());

		if(new Rectangle(ball.getPosX(),ball.getPosY(),ball.getWidth(),ball.getHeight())
				.intersects(new Rectangle(breaker.getPosX(),HEIGHT - 10,breaker.getWidth(),breaker.getHeight()))){
			ball.setDirectionY(ball.getDirectionY() * -1);
		}
		if(ball.getPosX() < 0 || ball.getPosX() >= WIDTH - ball.getWidth()) {
			ball.setDirectionX(ball.getDirectionX() * -1);
		}

		if(ball.getPosY() < 0) {
			ball.setDirectionY(ball.getDirectionY() * -1);
		}

		if(ball.getPosY() >= HEIGHT - ball.getHeight()){
			breaker.setInLife(false);
		}

		ball.setPosX(ball.getPosX() + ball.getDirectionX());
		ball.setPosY(ball.getPosY() + ball.getDirectionY());

	}

	public void moveLeft(){
		if(breaker.getPosX() > 0)
		breaker.setPosX( breaker.getPosX() - 10);
	}

	public void moveRigth(){
		if(breaker.getPosX() < WIDTH - breaker.getWidth())
		breaker.setPosX( breaker.getPosX() + 10);
	}
	
}
