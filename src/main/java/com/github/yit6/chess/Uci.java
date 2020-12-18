import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

public class Uci {
	public Board board;
	public Engine engine;

	public Uci(Board board) {
		this.board = board;
		this.engine = new Engine();
	}

	public String parse(String command) {
		if (command.equals("uci")) {
			return "id name yitengine\nuciok";
		} else if (command.startsWith("position")) {
			position(command);
			return "";
		} else if (command.equals("ucinewgame")) {
			ucinewgame();
			return "";
		} else if (command.equals("isready")) {
			return "readyok";
		} else if (command.equals("d")) {
			return board.toString();
		} else if (command.equals("quit")) {
			return "exitting";
		} else if (command.startsWith("go")) {
			return "bestmove "+engine.uciGo(board).toString();
		} else {
			return "Command not found: "+command;
		}
	}	
	
	public void ucinewgame() {
		board = new Board();	
	}

	public void position(String options) {
		options = options.substring(8,options.length());
		if (options.contains("startpos")) {
			board = new Board();
		} else if (options.contains("fen")) {
			String fen = options.substring(4,options.length());
			board.loadFromFen(fen);
		}
		if (options.contains("moves")) {
			options = options.substring(options.indexOf("moves")+6);
			doMoves(options);
		}
	}

	private void doMoves(String moves) {
		String[] moveList = moves.split(" ");
		for (String move : moveList) {
			Move m = new Move(move, board.getSideToMove());
			board.doMove(new Move(move, board.getSideToMove()),false);
		}
	}
}
