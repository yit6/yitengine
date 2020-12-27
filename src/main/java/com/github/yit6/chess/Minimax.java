import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.Board;
import java.util.ArrayList;
import java.util.List;

public class Minimax {
	Board board;
	public boolean isParent, max;
	public ArrayList<Minimax> nodes;
	public Minimax best;
	public Move move;

	public Minimax(Board board, Boolean max) {
		this.board = board;
		isParent = false;
		this.max = max;
	}

	public Minimax(Board board, Move move, Boolean max) {
		this.board = board;
		isParent = false;
		this.max = max;
		this.move = move;
	}

	public int GetScore() {
		if (isParent) {
			Minimax maxMove = nodes.get(0), minMove = nodes.get(0);
			int maxScore = -100000000;
			int minScore =  100000000;
			for (int i = 0; i < nodes.size(); i++) {
				int s = nodes.get(i).GetScore();
				if (s > maxScore) {
					maxScore = s;
					maxMove = nodes.get(i);
				}
				if (s < minScore) {
					minScore = s;
					minMove = nodes.get(i);
				}
			}
			best = max?maxMove:minMove;
			return max?maxScore:minScore;
		} else {
			return Eval.Score(board);
		}
	}

	public void AddNodes() {
		if (isParent) {
			for (int i = 0; i < nodes.size(); i++) {
				nodes.get(i).AddNodes();
			}
		}
		else {
			if (!board.isMated() && !board.isInsufficientMaterial() && !board.isDraw() && !board.isStaleMate()) {
				isParent = true;
				nodes = new ArrayList();
				List<Move> moves = board.legalMoves();
				for (Move m : moves) {
					board.doMove(m);
					Board next = board.clone();
					board.undoMove();
					nodes.add(new Minimax(next, m,!max));
				}
			}
		}
	}

	public Move getBestMove() {
		if (isParent) {
			return best.move;
		} else {
			return move;
		}
	}
	public Move getBestResponse() {
		if (isParent) {
			return best.getBestMove();
		} else {
			return null;
		}
	}
}
