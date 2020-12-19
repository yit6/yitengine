import com.github.bhlangonijr.chesslib.*;

public class Eval {
	
	private static int[] pieceValues = {1,3,3,5,9,0,-1,-3,-3,-5,-9, 0, 0};

	/*
	 * high favors white, low favors black
	 */
	public static int Score(Board board) {
		char[] fen = board.getFen().toCharArray();
		int material = 0;
		int s = 0;
		for (Square sq : Square.values()) {
			Piece p = board.getPiece(sq);
			material += pieceValues[p.ordinal()];
		}
		s += material*3;
		//if (board.isMated()) {
			//if (board.getSideToMove() == Side.WHITE) {
				//s = -10000;
			//} else {
				//s = 10000;
			//}
		//}
		//if (board.isKingAttacked()) {
			//if (board.getSideToMove() == Side.WHITE) {
				//s = -1;
			//} else {
				//s = 1;
			//}
		//}
		return s;
	}
}
