package ig.labyrinth;

/**
 * Created by User_2013 on 15.04.2017.
 */

public class Point {
    public int X=0;
    public int Y=0;

    public Point(int x, int y) {
        this.X=x;
        this.Y=y;
    }

    public String toString(){
        return X + ":" + Y;
    }

    public boolean equals(Point p){
        return (p.X == X) & (p.Y == Y);
    }
}
