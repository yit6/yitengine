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
		boolean max = board.getSideToMove()==Side.WHITE;
		Minimax minimax = new Minimax(board, max);
		addToTree(minimax);
		addToTree(minimax);
		addToTree(minimax);
		Move out = minimax.getBestMove();
		return out;
	}
	private void addToTree(Minimax minimax) {
		minimax.AddNodes();
		minimax.GetScore();
	}
}
