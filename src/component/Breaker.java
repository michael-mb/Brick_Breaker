package component;

public class Breaker {
    private int posX;
    private int width;
    private int height;
    private boolean inLife;

    public Breaker (int posX , int width , int height){
        this.inLife = true;
        this.posX = posX;
        this.width = width;
        this.height = height;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public boolean isInLife() {
        return inLife;
    }

    public void setInLife(boolean inLife) {
        this.inLife = inLife;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
