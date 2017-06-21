package com.anwesome.games.threepowers.gameobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class Grid {
    private Square square;
    private float x,y,size;
    private Grid leftNeighbor,rightNeighbor,upNeighbor,downNeighbor;
    public Grid(float x,float y,float size) {
        this.size = size;
        this.x = x;
        this.y = y;
    }
    public float getSize() {
        return size;
    }
    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Grid getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setLeftNeighbor(Grid leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public Grid getRightNeighbor() {
        return rightNeighbor;
    }

    public void setRightNeighbor(Grid rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public Grid getUpNeighbor() {
        return upNeighbor;
    }

    public void setUpNeighbor(Grid upNeighbor) {
        this.upNeighbor = upNeighbor;
    }

    public Grid getDownNeighbor() {
        return downNeighbor;
    }

    public void setDownNeighbor(Grid downNeighbor) {
        this.downNeighbor = downNeighbor;
    }
    public void move() {
        if(square != null) {
            square.move();
        }
    }
    public void setSquareTarget(Grid target) {
        if(!this.equals(target)) {
            square.setTarget(target);
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        if(square != null) {
            square.draw(canvas,paint);
        }
        else {
            paint.setColor(Color.parseColor("#ff8a80"));
            canvas.save();
            canvas.translate(x,y);
            canvas.drawRect(new RectF(-size/3,-size/3,size/3,size/3),paint);
            canvas.restore();
        }
    }
    public int hashCode() {
        return (int)(x+y);
    }
    public boolean stopped() {
        return (square == null) || (square!=null && square.stopped());
    }
}
