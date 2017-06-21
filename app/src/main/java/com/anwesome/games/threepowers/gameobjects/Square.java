package com.anwesome.games.threepowers.gameobjects;

import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;

import com.anwesome.games.threepowers.AppConstants;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class Square {
    private int num = 0,animCounter = 0;
    private float x,y,lx,ly,size;
    private Grid grid;
    public Square(int num,float size) {
        this.num = num;
        this.size = size;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public void setSpeeds(float lx,float ly) {
        this.lx = lx;
        this.ly = ly;
    }
    public void setXY(float x,float y) {
        this.x = x;
        this.y = y;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int hashCode() {
        return num;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setColor(AppConstants.colors[num]);
        canvas.save();
        canvas.translate(x,y);
        canvas.drawRect(new RectF(-size/3,-size/3,size/3,size/3),paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(size/4);
        String text = ""+Math.pow(3,num+1);
        float tw = paint.measureText(text)/2;
        canvas.drawText(text,-tw,-tw/2,paint);
        canvas.restore();
    }
    public void move() {
        x+=(lx*size/3);
        y+=(ly*size/3);
        animCounter++;
        if(animCounter == 3) {
            if (this.grid != null) {
                Grid neighbor = null;
                if (lx > 0) {
                    neighbor = grid.getRightNeighbor();
                }
                if (lx < 0) {
                    neighbor = grid.getLeftNeighbor();
                }
                if (ly < 0) {
                    neighbor = grid.getUpNeighbor();
                }
                if (ly > 0) {
                    neighbor = grid.getDownNeighbor();
                }
                if (neighbor != null) {
                    if (neighbor.getSquare() == null) {
                        grid.setSquare(null);
                        neighbor.setSquare(this);
                        grid = neighbor;
                    } else {
                        if (neighbor.getSquare() != null && neighbor.getSquare().getNum() == num) {
                            Square square = new Square(num + 1, size);
                            grid.setSquare(null);
                            square.setGrid(neighbor);
                            neighbor.setSquare(square);
                        }
                    }

                }
            }
        }
    }
}
