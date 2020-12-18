import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class Engine {
	Random rand = new Random();
	public Move uciGo(Board board) {
		List<Move> moves = board.legalMoves();
		int maxScore = -100000000;
		int minScore =  100000000;
		Move minMove = moves.get(0);
		Move maxMove = moves.get(0);
		for (Move m : moves) {
			board.doMove(m);
			int s = Eval.Score(board);
			if (s > maxScore) {
				maxScore = s;
				maxMove = m;
			}
			if (s < minScore) {
				minScore = s;
				minMove = m;
			}
			board.undoMove();
		}
		if (board.getSideToMove()==Side.WHITE) {
		      return maxMove;
		} else {
		      return minMove;
		}
	}
}
