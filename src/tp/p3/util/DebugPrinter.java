 package tp.p3.util;

import tp.p3.logic.Game;

public class DebugPrinter extends BoardPrinter implements GamePrinter {

	public DebugPrinter(Game game) {
		this.cellSize = 18;
		this.dimX = 1;
		//this.dimY = game.getGoList().getSizeP() + game.getGoList().getSizeZ();
		this.dimY = 10;
		encodeGame(game);
	}

	@Override
	public String printGame(Game game) {
		this.encodeGame(game);
		return this.boardToString(game);
	}

	@Override
	public void encodeGame(Game game) {
		
		board = new String[dimX][dimY];

		int tamZList = game.getGoList().getSizeZ();
		int tamPList = game.getGoList().getSizeP();

		int contZ = 0;
		
		for(int i = 0; i < this.dimY; i++) {
			this.board[0][i] = space;
		}
		for (int i = 0; i < (tamZList + tamPList); i++) {
			if (i < tamPList) {
				this.board[0][i] = game.getGoList().getPlantList()[i].getSimbolo() + "[l:"
						+ game.getGoList().getPlantList()[i].getLife() + ",x:"
						+ game.getGoList().getPlantList()[i].getX() + ",y:" + game.getGoList().getPlantList()[i].getY()
						+ ",t:" + game.getGoList().getPlantList()[i].getFrequency() + "]";

			} else {
				this.board[0][i] = game.getGoList().getZombieList()[contZ].getSimbolo() + "[l:"
						+ game.getGoList().getZombieList()[contZ].getLife() + ",x:"
						+ game.getGoList().getZombieList()[contZ].getX() + ",y:"
						+ game.getGoList().getZombieList()[contZ].getY() + ",t:"
						+ game.getGoList().getZombieList()[contZ].getFrequency() + "]";

				contZ++;
			}
		}
	}

}
