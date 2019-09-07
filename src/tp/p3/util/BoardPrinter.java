package tp.p3.util;

import tp.p3.logic.Game;

public abstract class BoardPrinter {
	
	int dimX;
	int dimY;
	String[][] board;
	int cellSize;
	final String space = " ";
	
	public abstract void encodeGame(Game game);
	
	public String boardToString(Game game) {

		int marginSize = 2;
		String vDelimiter = "|";
		String hDelimiter = "-";

		String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellSize + 1)) - 1);
		String margin = MyStringUtils.repeat(space, marginSize);
		String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);

		StringBuilder str = new StringBuilder();
		
		str.append(lineDelimiter);

		for (int i = 0; i < dimX; i++) {
			str.append(margin).append(vDelimiter);
			for (int j = 0; j < dimY; j++) {
				str.append(MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
			}
			str.append(lineDelimiter);
		}
		return str.toString();
	}
	
	/* Metodos getters y setters */

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}
}
