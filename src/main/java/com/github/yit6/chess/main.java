import com.github.bhlangonijr.chesslib.Board;
import java.util.Scanner;

class main {
	public static void main(String[] args) {
		Board board = new Board();
		Scanner sc = new Scanner(System.in);
		Uci uci = new Uci(board);
		String in,out;
		while (true) {
			in = sc.nextLine();
			out = uci.parse(in);
			System.out.println(out);
			if (out.equals("exitting")) {
				break;
			}
		}
	}
}
