package com.nqueens;

/**
 * Created by amateen on 9/29/18.
 */
public class Point {

    int row, col;
    public Point(int x, int y){
        this.row = x;
        this.col = y;
    }

    @Override
    public String toString() {
        return new String("(" + this.row + ", " + this.col + ")");
    }
}
