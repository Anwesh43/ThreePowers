package com.anwesome.games.threepowers.gameobjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GridContainer {
    private ConcurrentLinkedDeque<Grid> grids = new ConcurrentLinkedDeque<>();
    private List<Grid> gridList = new ArrayList<>();
    private ConcurrentLinkedQueue<Grid> movingList = new ConcurrentLinkedQueue();
    public void populateGrid() {
        Random random = new Random();
        int index = random.nextInt(gridList.size());
        Grid grid = gridList.get(index);
        if(grid.getSquare() == null) {
            int num = random.nextInt(2);
            Square square = new Square(num,grid.getSize());
            square.setXY(grid.getX(),grid.getY());
            grid.setSquare(square);
            square.setGrid(grid);
        }
        else {
            populateGrid();
        }
    }
    public void init(int w) {
        int size = w/4,x = size/2 ,y = size/2;
        for(int i=0;i<16;i++) {
            Grid grid  = new Grid(x,y,size);
            grids.add(grid);
            gridList.add(grid);
            if(i%4 == 3) {
                x = size/2;
                y+= size;
            }
            else {
                x+=size;
            }
        }
        relateGrid(w,gridList);
    }
    private void relateGrid(int w,List<Grid> grids) {
        for(int i=0;i<grids.size();i++) {
            Grid grid = grids.get(i);
            if(i-4 >= 0) {
                grid.setUpNeighbor(grids.get(i-4));
            }
            if(i+4 < grids.size()) {
                grid.setDownNeighbor(grids.get(i+4));
            }
            if(i+1 < grids.size() && grid.getX()+grid.getSize()<w) {
                grid.setRightNeighbor(grids.get(i+1));
            }
            if(i-1 >= 0 && grid.getX()-grid.getSize()>0) {
                grid.setLeftNeighbor(grids.get(i-1));
            }
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        for (Grid grid : grids) {
            grid.draw(canvas,paint);
        }
    }
    public void move() {
        if(movingList.size() > 0) {
            for (Grid grid : movingList) {
                grid.move();
                if (grid.stopped()) {
                    movingList.remove(grid);
                }
            }
            if(movingList.size() == 0) {
                populateGrid();
            }
        }
    }
    public boolean stopped() {
        boolean stopped = true;
        for(Grid grid:movingList) {
            stopped = stopped && grid.stopped();
        }
        return stopped;
    }
    public void handleSwipeLeft() {
        int k = 0;
        for(int i=0;i<gridList.size();i++) {
            Grid grid = gridList.get(i);
            if(i%4 == 0) {
                k = 0;
            }
            if(grid.getSquare() == null) {
                k++;
            }
            else {
                Grid target = grid;
                for(int j=0;j<k;j++) {
                    if(target.getLeftNeighbor() != null) {
                        target = target.getLeftNeighbor();
                    }
                }
                if(target.getLeftNeighbor()!=null && target.getLeftNeighbor().getSquare()!=null && target.getLeftNeighbor().getSquare().getNum() == grid.getSquare().getNum()) {
                    target = target.getLeftNeighbor();
                }
                grid.setSquareTarget(target);
                if(grid!=target) {
                    movingList.add(grid);
                }
            }
        }
    }
    public void handleSwipeRight() {
        int k = 0;
        movingList = new ConcurrentLinkedQueue<>();
        for(int i=gridList.size()-1;i>=0;i--) {
            Grid grid = gridList.get(i);
            if(i%4 == 3) {
                k = 0;
            }
            if(grid.getSquare() == null) {
                k++;
            }
            else {
                Grid target = grid;
                for(int j=0;j<k;j++) {
                    if(target.getRightNeighbor() != null) {
                        target = target.getRightNeighbor();
                    }
                }
                if(target.getRightNeighbor()!=null && target.getRightNeighbor().getSquare()!=null && target.getRightNeighbor().getSquare().getNum() == grid.getSquare().getNum()) {
                    target = target.getRightNeighbor();
                }
                grid.setSquareTarget(target);
                if(grid!=target) {
                    movingList.add(grid);
                }
            }
        }
    }

    public void handleSwipeDown() {
        movingList = new ConcurrentLinkedQueue<>();
        for(int i=0;i<4;i++) {
           int k = 0;
           for(int j=gridList.size()-1-i;j>=0;j-=4) {
               Grid grid = gridList.get(i);
               if(grid.getSquare() == null) {
                   k++;
               }
               else {
                   Grid target = grid;
                   for(int e=0;e<k;e++) {
                       if(target.getDownNeighbor() != null) {
                           target = target.getDownNeighbor();
                       }
                   }
                   if(target.getDownNeighbor()!=null && target.getDownNeighbor().getSquare()!=null && target.getDownNeighbor().getSquare().getNum() == grid.getSquare().getNum()) {
                       target = target.getDownNeighbor();
                   }
                   grid.setSquareTarget(target);
                   if(grid!=target) {
                       movingList.add(grid);
                   }
               }

           }
       }
    }
    public void handleSwipeUp() {
        movingList = new ConcurrentLinkedQueue<>();
        for(int i=0;i<4;i++) {
            int k = 0;
            for(int j=i;j>=0;j+=4) {
                Grid grid = gridList.get(i);
                if(grid.getSquare() == null) {
                    k++;
                }
                else {
                    Grid target = grid;
                    for(int e=0;e<k;e++) {
                        if(target.getUpNeighbor() != null) {
                            target = target.getUpNeighbor();
                        }
                    }
                    if(target.getUpNeighbor()!=null && target.getUpNeighbor().getSquare()!=null && target.getUpNeighbor().getSquare().getNum() == grid.getSquare().getNum()) {
                        target = target.getUpNeighbor();
                    }
                    grid.setSquareTarget(target);
                    if(grid!=target) {
                        movingList.add(grid);
                    }
                }

            }
        }
    }
}
