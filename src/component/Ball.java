package component;

public class Ball {

    private int width;
    private int height;
    private int posX;
    private int posY;

    public Ball (int width , int height , int posX , int posY){
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
}
