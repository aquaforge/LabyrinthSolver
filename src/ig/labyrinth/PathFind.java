package ig.labyrinth;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by User_2013 on 15.04.2017.
 */
public class PathFind {
    public int[][] getPix() {
        return pix;
    }

    private int pix[][];
    private HashSet<Point> wave_this;
    private HashSet<Point> wave_future = new HashSet<Point>();

    public PathFind(int[][] pix) {
        this.pix = pix;
    }

    private void PointProcess(int x, int y, int level){
        int val = pix[x][y];
        if (val < 0) return;
        if (val >0 & val <= level) return;

        pix[x][y] = level;
        if (x-1>=0) wave_future.add(new Point(x-1,y));
        if (y-1>=0) wave_future.add(new Point(x,y-1));
        if (x+1<pix.length) wave_future.add(new Point(x+1,y));
        if (y+1<pix[0].length) wave_future.add(new Point(x,y+1));
    }

    public int RunWaves(int xStart, int yStart){
        int waveCount = 0;
        if (pix[xStart][yStart] != 0) return 0;
        wave_future.add(new Point(xStart, yStart));

        while (wave_future.size()>0) {
            waveCount++;
            wave_this = wave_future;
            wave_future = new HashSet<Point>();

            for(Point p:wave_this){
                PointProcess(p.X,p.Y,waveCount);
            }


        }
        return waveCount;
    }


    public void getBackPath(int xP, int yP, int lvlP, LinkedList<Point> backpath){
        int lvl = pix[xP][yP];

        if (lvl>1 & lvl == lvlP-1) {
            backpath.add(new Point(xP,yP));
            if (xP-1 >= 0) getBackPath(xP-1,yP,lvlP-1,backpath);
            if (yP-1 >= 0) getBackPath(xP,yP-1,lvlP-1,backpath);
            if (xP+1 < pix.length) getBackPath(xP+1,yP,lvlP-1,backpath);
            if (yP+1 < pix[0].length) getBackPath(xP,yP+1,lvlP-1,backpath);
        }
    }

}
