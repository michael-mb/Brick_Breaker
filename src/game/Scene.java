package game;
import component.*;

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
	private Score score;
	private Font Scorefont ;

	public boolean startPressed;
	public Scene() {
		startPressed = false;
		chrono = new Chrono(this);
		breaker = new Breaker(WIDTH / 2 - 45 ,100 , 10);
		ball = new Ball(30 , 30 , WIDTH / 2 - 10 , HEIGHT - 100);
		mapGenerator = new MapGenerator(4,5);
		Scorefont = new Font("Arial", Font.BOLD, 15);
		score = new Score();
		this.setBackground(Color.black);
	}
	
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 drawLimits(g2);
		 drawBricks(g2);
		 if(breaker.isInLife() && !gameOver()){
			 drawBall(g2);
			 drawBreaker(g2);
			 drawScore(g2);
		 }else{
		 	if(breaker.isInLife()){
		 		// draw lose
			}else{
		 		//draw win
			}
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

		g2.setColor(Color.blue);
		g2.fillOval(ball.getPosX() , ball.getPosY() , ball.getWidth() , ball.getHeight());

		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.yellow);
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
			stop();
		}

		if(startPressed){
			ball.setPosX(ball.getPosX() + ball.getDirectionX());
			ball.setPosY(ball.getPosY() + ball.getDirectionY());
		}

	}

	private void drawBricks(Graphics2D g2){
		A: for(int i=0 ; i < mapGenerator.getMap().length; i++){
			for (int j=0 ; j < mapGenerator.getMap()[0].length ; j++){
				if(mapGenerator.getMap()[i][j] > 0) {
					g2.setColor(Color.white);
					g2.fillRect(j * mapGenerator.getBrickWidth() + 80 , i* mapGenerator.getBrickHeigth() + 50 ,
							mapGenerator.getBrickWidth() , mapGenerator.getBrickHeigth());

					g2.setStroke(new BasicStroke(3));
					g2.setColor(Color.black);
					g2.drawRect(j * mapGenerator.getBrickWidth() + 80 , i* mapGenerator.getBrickHeigth() + 50 ,
							mapGenerator.getBrickWidth() , mapGenerator.getBrickHeigth());

					Rectangle brickRect = new Rectangle(j * mapGenerator.getBrickWidth() + 80 , i* mapGenerator.getBrickHeigth() + 50 ,
							mapGenerator.getBrickWidth() , mapGenerator.getBrickHeigth()) ;
					Rectangle ballRect = new Rectangle(ball.getPosX(),ball.getPosY(),ball.getWidth(),ball.getHeight());

					if((brickRect).intersects( ballRect)){
						ball.setDirectionY(ball.getDirectionY() * -1);
						mapGenerator.setMapValue(0,i,j);
						mapGenerator.setTotalBricks(mapGenerator.getTotalBricks() - 1);
						score.setScore(score.getScore() +1 );
						if(ball.getPosX()  <= brickRect.x ||  ball.getPosX() + 1 >= brickRect.x + brickRect.width){
							ball.setDirectionX( ball.getDirectionX() * -1);
						}else{
							ball.setDirectionY(ball.getDirectionY() * -1);
						}

						break A;
					}
				}
			}
		}
	}

	private void drawScore(Graphics2D g2) {

		g2.setColor(Color.white);
		g2.setFont(Scorefont);
		g2.drawString(score.getMessage(), 10  ,HEIGHT - 10); // on affiche le score
	}

	public void moveLeft(){
		if(breaker.getPosX() > 0)
		breaker.setPosX( breaker.getPosX() - 10);
	}

	public void moveRigth(){
		if(breaker.getPosX() < WIDTH - breaker.getWidth())
		breaker.setPosX( breaker.getPosX() + 10);
	}

	public void start(){
		startPressed = true;
	}

	public void stop(){
		startPressed = false;
	}

	public boolean gameOver(){
		return mapGenerator.getTotalBricks() == 0;
	}
}
