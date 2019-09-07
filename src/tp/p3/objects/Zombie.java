package tp.p3.objects;

public abstract class Zombie extends GameObject {

	/** avanzaZombie: avanza una posicion */
	public void avanzaZombie() {
		this.y--;
	}

	@Override
	public void update() {
		/* movemos los zombies */
		/*
		 * hallamos la posicion de cada Zombie para poder comparar con los elementos del
		 * tablero
		 */
		this.cuentaCiclos();
		
		int x = this.getX();
		int y = this.getY();

		boolean pFound = false;
		boolean zFound = false;
		int iFound = 0;
		int posZombie = 0;
		/* buscamos el elemento que haya justo delante en el tablero */
		while (!pFound && (iFound < this.game.getGoList().getSizeP())) {
			if (this.game.getGoList().getPlantList()[iFound].getX() == x
					&& this.game.getGoList().getPlantList()[iFound].getY() == y - 1) {
				pFound = true;
			} else
				iFound++;
		}

		if (pFound) {
			/* Aplicamos da�o */
			this.game.getGoList().getPlantList()[iFound].getDamaged(this.damage);
			/* Comprobamos si ha muerto */
			if (this.game.getGoList().getPlantList()[iFound].getLife() == 0) {
				/* Eliminamos del array */
				this.game.getGoList().deletePlantFromList(iFound);
			}
		} else {
			/* Si no hay planta buscamos un zombie */
			while (!zFound && posZombie < this.game.getGoList().getSizeZ()) {
				if (this.game.getGoList().getZombieList()[posZombie].getX() == x
						&& this.game.getGoList().getZombieList()[posZombie].getY() == y - 1) {
					zFound = true;
				} else
					posZombie++;
			}
			/* Si no hay zombie ni planta, avanza */
			if (!zFound && this.frequency == 0) {
				this.avanzaZombie();
			}
		}
	}
}
