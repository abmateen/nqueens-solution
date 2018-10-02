import com.nqueens.Board;

/**
 * Created by amateen on 10/2/18.
 */
public class QueenLogicTests {

    @org.junit.Test
    public void testSafety(){
        Board board = new Board(8);
        board.placeQueen(0, 0); // Q .
        board.placeQueen(1, 1); // . Q
        board.placeQueen(2, 2); //  .  Q
        assert  board.checkLowerDiagonal(2, 2) == false; // should return false, there is a conflict

        board = new Board(8);
        board.placeQueen(0, 0);
        board.placeQueen(2, 2);
        board.placeQueen(5, 3);
        assert board.checkForStraightLine() == false;



    }
}
