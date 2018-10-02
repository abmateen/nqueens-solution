package com.nqueens;

/**
 * Created by amateen on 9/29/18.
 */
public class Grid<T> {

    private T[][] grid;

    public Grid(int row, int col){
        grid = (T[][]) new Object[row][col];
    }

    public void set(int row, int col, T value){
        grid[row][col] = value;
    }

    public T get(int row, int col){
        return grid[row][col];
    }

    public int rowSize(){
        return grid.length;
    }
    public int colSize(){
        return grid[0].length;
    }

}
