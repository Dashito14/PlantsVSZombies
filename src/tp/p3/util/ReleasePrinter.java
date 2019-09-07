package tp.p3.util;

import tp.p3.logic.Game;

public class ReleasePrinter extends BoardPrinter implements GamePrinter {

	public ReleasePrinter(Game game) {
		this.cellSize = 7;
		this.dimX = Game.getFila();
		this.dimY = Game.getColumna();
		encodeGame(game);
	}

	@Override
	public String printGame(Game game) {
		this.encodeGame(game);
		return this.boardToString(game);
	}

	@Override
	public void encodeGame(Game game) {
		// TODO Auto-generated method stub
		board = new String[dimX][dimY];
		for (int i = 0; i < dimX; i++) {
			for (int j = 0; j < dimY; j++) {
				boolean encontrado = false;
				int k = 0;
				if (game.getGoList().getSizeP() != 0) {
					while (!encontrado && k < game.getGoList().getSizeP()) {
						if (game.getGoList().getPlantList()[k].getX() == i
								&& game.getGoList().getPlantList()[k].getY() == j) {
							encontrado = true;
							board[i][j] = game.getGoList().getPlantList()[k].toString();
						}
						k++;
					}

				}
				if (!encontrado && game.getGoList().getSizeZ() != 0) {
					k = 0;
					while (!encontrado && k < game.getGoList().getSizeZ()) {
						if (game.getGoList().getZombieList()[k].getX() == i
								&& game.getGoList().getZombieList()[k].getY() == j) {
							encontrado = true;
							board[i][j] = game.getGoList().getZombieList()[k].toString();
						}
						k++;
					}

				}
				if(!encontrado) {
					board[i][j] = space;
				}
			}
		}
	}

}
