package component;

public class MapGenerator {
    private int[][] map;
    private int brickWidth = 85;
    private int brickHeight = 50;
    private int totalBricks;

    public MapGenerator(int row , int col , int brickWidth , int brickHeight){
        map = new int[row][col];
        for (int i = 0 ; i <map.length ; i++){
            for (int j=0 ; j < map[0].length ; j++) {
                map[i][j] = 1;
            }
        }
        totalBricks = row * col;

        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
    }

    public int[][] getMap(){
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }
    public int getBrickHeight() {
        return brickHeight;
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
