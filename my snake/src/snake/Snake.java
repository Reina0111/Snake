package snake;


import java.awt.*;
import java.util.ArrayList;

public class Snake {
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public int direction = DOWN, score = 0;

    ArrayList<Point> snakeParts = new ArrayList<>();

    public int ticks = 0, taillength = 1;

    public Point head;

    Snake(){
        snakeParts.add(new Point(0, 0));
        head = snakeParts.get(snakeParts.size()-1);
    }

    public void addSnakeParts(Point e){
        snakeParts.add(e);
    }

    public void removeLastPart() {
        snakeParts.remove(0);
    }

    public void setHead() {
        head = snakeParts.get(snakeParts.size()-1);
    }
    
    void reset() {
        score = 0;
        snakeParts.clear();
        head = new Point(0, 0);
        taillength = 3;
        direction = DOWN;
    }

}
