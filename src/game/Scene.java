package game;
import component.Ball;
import component.Breaker;
import component.Chrono;
import component.MapGenerator;

import java.awt.*;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Scene extends JPanel{

	private final int HEIGHT = 481; 		// Stage height
	private final int WIDTH = 582;			// Stage Width
	
	private Chrono chrono;					// time
	private Breaker breaker;
	private Ball ball;
	private MapGenerator mapGenerator;
	public Scene() {
		
		chrono = new Chrono(this);
		breaker = new Breaker(WIDTH / 2 - 45 ,100 , 10);
		ball = new Ball(30 , 30 , WIDTH / 2 - 10 , 200);
		mapGenerator = new MapGenerator(4,5);
		this.setBackground(Color.black);
	}
	
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 drawLimits(g2);

		 if(breaker.isInLife()){
			 drawBreaker(g2);
			 drawBricks(g2);
			 drawBall(g2);
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

		g2.setColor(Color.yellow);
		g2.fillOval(ball.getPosX() , ball.getPosY() , ball.getWidth() , ball.getHeight());

		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.orange);
		g2.drawOval(ball.getPosX() , ball.getPosY() , ball.getWidth() , ball.getHeight());

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

	private void drawBricks(Graphics2D g2){
		for(int i=0 ; i < mapGenerator.getMap().length; i++){
			for (int j=0 ; j < mapGenerator.getMap()[0].length ; j++){
				if(mapGenerator.getMap()[i][j] > 0) {
					g2.setColor(Color.white);
					g2.fillRect(j * mapGenerator.getBrickWidth() + 80 , i* mapGenerator.getBrickHeigth() + 50 ,
							mapGenerator.getBrickWidth() , mapGenerator.getBrickHeigth());

					g2.setStroke(new BasicStroke(3));
					g2.setColor(Color.black);
					g2.drawRect(j * mapGenerator.getBrickWidth() + 80 , i* mapGenerator.getBrickHeigth() + 50 ,
							mapGenerator.getBrickWidth() , mapGenerator.getBrickHeigth());

				}
			}
		}
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
