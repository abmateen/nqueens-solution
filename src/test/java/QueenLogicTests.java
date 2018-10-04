import com.nqueens.Board;
import com.nqueens.Point;
import org.junit.Test;

/**
 * Created by amateen on 10/2/18.
 */
public class QueenLogicTests {

    @org.junit.Test
    public void testSafety(){
        // testing with points dat
        // a1, c2, e3
        Board board = new Board(8);
        board.placeQueen(0, 0); // Q .
        board.placeQueen(1, 1); // . Q
        board.placeQueen(2, 2); //  .  Q
        assert  board.checkLowerDiagonal(2, 2) == false; // should return false, there is a conflict
        board.clear();
        board.placeQueen(0, 0);
        board.placeQueen(1, 2);
        board.placeQueen(2, 4);
        // check if we can place at G4
        assert board.checkUpperDiagonal(3, 6) == true; // there is no conflict
        assert board.checkLowerDiagonal(3, 6) == true; // there is no conflict
        // but we can not put at G6
        assert board.checkLowerDiagonal(5, 6) == true;
        assert board.checkUpperDiagonal(5, 6) == false;
    }

    @Test
    public void checkForStraightLine(){
        Board board = new Board(8);
        Point[] points = new Point[3];
        // checking straight line a1, c2, e3.
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 2);
        points[2] = new Point(2, 4);
        // this is in a straight line, function must return true.
        assert board.isQueensInStraightLine(points) == true;
        // fault injection/invalid data point injection test.
        points[2] = new Point(7, 7);
        assert board.isQueensInStraightLine(points) == false;
    }

}
