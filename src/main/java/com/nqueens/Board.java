package com.nqueens;

import java.util.ArrayList;
import java.util.List;

/**
 * Chess Board representation. This class encapsulates Grid interface for a chess board.
 * Created by amateen on 9/29/18.
 */
public class Board {

    // Chess Board grid with boolean values
    private Grid<Boolean> board;
    private List<Point> positions;
    public Board(int i) {
        board = new Grid<Boolean>(i, i);
        positions = new ArrayList<>();
        init();

    }

    // init default empty board
    private void init(){
        for ( int i=0; i<board.rowSize(); i++)
            for ( int j=0; j<board.colSize(); j++)
                board.set(i, j, false); // init by default to false. Empty board
    }

    // mark queen at given row and col
    public void placeQueen(int row, int col){
        board.set(row, col, true);
    }

    // unmark queen at given row and col
    public void removeQueen(int row, int col){
        board.set(row, col, false);
    }

    // This is the main logic which tests if it is safe to place queen at given row and col
    // logic is to check upper and lower diagonals of a queen to check if the queen is in conflict with previous placed
    // queens, also it checks if there is no other queen at the current row.
    public boolean isSafe(int row, int col){
        return (checkUpperDiagonal(row, col) && isRowClear(row, col) && checkLowerDiagonal(row, col));
    }

    // checks if the given row has no other queen
    public boolean isRowClear(int row, int col){
        for ( int i=0; i<col; i++){
            if ( board.get(row, i) == true)
                return false;
        }
        return true;
    }


    // check lower diagonal of queen if any diagnoal has any conflict with current queen
    // the diagonal

    public boolean checkLowerDiagonal(int row, int col){

        for ( int i=row, j=col; j >=0 && i < board.rowSize(); i++, j--){
            if (board.get(i, j) == true) {
                return false;
            }
        }
        return true;
    }

    // check
    public boolean checkUpperDiagonal(int row, int col){
        for ( int i=row, j=col; i >= 0 && j >= 0; i--, j--){
            if ( board.get(i, j) == true){
                return false;
            }
        }
        return true;
    }

    public boolean checkForStraightLine(){
        List<Point> cachePoints = new ArrayList<Point>();
        return checkForStraightLine(positions, 0, cachePoints);
    }

    public boolean checkForStraightLine(List<Point> points){
        List<Point> cachePoints = new ArrayList<Point>();
        return checkForStraightLine(points, 0, cachePoints);
    }

    private boolean checkForStraightLine(List<Point> points, int i, List<Point> cachePoints){
        if ( cachePoints.size() == 3 ) {
            boolean inline = isQueensInStraightLine(cachePoints);
            return inline;

        }else{
            for ( int x = i; x < points.size(); x++){
                cachePoints.add(points.get(x));
                if ( checkForStraightLine(points, x+1, cachePoints) == true ) return true;
                cachePoints.remove(cachePoints.size() - 1);

            }
        }
        return false;
    }


    public boolean solve(){
        return solve(0);
    }

    /*
    Uses backtracking approach to find a solution for given criteri i.e no queen should attack each other
    and no three queen should form a straight line at any angle
     */
    private boolean solve(int col){
        if ( col >= board.colSize()  ) {
            if ( !checkForStraightLine(positions) ) {
                printBoard();
                return true;
            }
        }else{
            for ( int row = 0; row < board.rowSize(); row++){
                if ( isSafe(row, col)){
                    placeQueen(row, col);
                    positions.add(new Point(row + 1, col + 1));
                    if ( solve(col+1) ) return true; // return on first solution found
                    removeQueen(row, col);
                    positions.remove(positions.size() - 1);
                }
            }
        }
        return false;
    }

    public boolean isQueensInStraightLine(List<Point> points){

        Point[] ps = new Point[points.size()];
        ps = points.toArray(ps);
        boolean inline = isQueensInStraightLine(ps);
        return inline;
    }

    /*
        It returns true if (y0 - y1) * ( x0 - x2 )
     */
    public boolean isQueensInStraightLine(Point[] points){

        if ( points == null ) return false;
        if ( points.length <= 2 ) return false; // Our constraint is to check for 3 or more lines.

        int c0 = points[0].col;
        int c1 = points[1].col;
        int c2 = points[2].col;

        int r0 = points[0].row;
        int r1 = points[1].row;
        int r2 = points[2].row;

        // its modification of two-point formula to test whether three points forms a straight line.
        return ((c0 - c1) * (r0 - r2) == (c0 - c2) * (r0 - r1));
    }

    public void printBoard(){
        System.out.println("                        ");
        System.out.println("                        ");

        for ( int i=0; i<board.rowSize(); i++){
            for ( int j=0; j<board.colSize(); j++){
                if ( board.get(i, j))
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }




}
