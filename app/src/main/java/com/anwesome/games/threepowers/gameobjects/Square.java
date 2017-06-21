package com.anwesome.games.threepowers.gameobjects;

import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.anwesome.games.threepowers.AppConstants;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class Square {
    private int num = 0,animCounter = 0;
    private float x,y,lx,ly,size;
    private Grid grid,target;
    public Square(int num,float size) {
        this.num = num;
        this.size = size;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public void setTarget(Grid target) {
        this.lx = (target.getX()-x)/3;
        this.ly = (target.getY()-y)/3;
        this.target = target;
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
    public boolean stopped() {
        return lx == 0 && ly == 0;
    }
    public void draw(Canvas canvas,Paint paint) {
        paint.setColor(AppConstants.colors[num]);
        canvas.save();
        canvas.translate(x,y);
        canvas.drawRect(new RectF(-size/3,-size/3,size/3,size/3),paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(size/4);
        String text = ""+(int)Math.pow(3,num+1);
        float tw = paint.measureText(text)/2;
        canvas.drawText(text,-tw,0,paint);
        canvas.restore();
    }
    public void move() {
        if(lx != 0 || ly!=0 || target != null) {
            x += lx;
            y += ly;
            animCounter++;
            if (animCounter == 3) {
                if (this.grid != null) {
                    if (target != null) {
                        if (target.getSquare() == null) {
                            grid.setSquare(null);
                            target.setSquare(this);
                            grid = target;
                            lx = 0;
                            ly = 0;
                        } else {
                            if (target.getSquare() != null && target.getSquare().getNum() == num) {
                                Square square = new Square(num + 1, size);
                                grid.setSquare(null);
                                square.setGrid(target);
                                target.setSquare(square);
                                lx = 0;
                                ly = 0;
                            }
                        }

                    }
                }
            }
        }
    }
}
