package component;

public class MapGenerator {
    private int[][] map;
    private int brickWidth = 85;
    private int brickHeigth = 50;
    private int totalBricks;

    public MapGenerator(int row , int col){
        map = new int[row][col];

        for (int i = 0 ; i <map.length ; i++){
            for (int j=0 ; j < map[0].length ; j++) {
                map[i][j] = 1;
            }
        }

        totalBricks = row * col;
    }

    public int[][] getMap(){
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }
    public int getBrickHeigth() {
        return brickHeigth;
    }

    public void setMapValue(int value , int row , int col){
        map[row][col] = value;
    }

    public int getTotalBricks() {
        return totalBricks;
    }

    public void setTotalBricks(int totalBricks) {
        this.totalBricks = totalBricks;
    }
}
