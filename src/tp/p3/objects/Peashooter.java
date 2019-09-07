package tp.p3.objects;

public class Peashooter extends Plant {

	/** Constructora */
	public Peashooter() {
		this.life = 3;
		this.cost = 50;
		this.frequency = 1;
		this.damage = 1;
		this.simbolo = 'p';
	}

	@Override
	public String toString() {
		return "P [" + this.life + "]";
	}

	@Override
	public boolean isObject(char id) {
		if (this.simbolo == id) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {

		int fila = this.x;
		int columna = this.y;
		int j = 0;
		boolean encontrado = false;
		/* buscamos el primer zombie en la misma fila que nuestro peashooter */
		while (!encontrado && j < this.game.getGoList().getSizeZ()) {
			if (this.game.getGoList().getZombieList()[j].getX() == fila
					&& this.game.getGoList().getZombieList()[j].getY() > columna)
				encontrado = true;
			else
				j++;
		}
		if (encontrado) {
			/* aplicamos da�o al zombie */
			this.game.getGoList().getZombieList()[j].getDamaged(this.damage);
			/* comprobamos si ha muerto para eliminarlo */
			if (this.game.getGoList().getZombieList()[j].getLife() == 0) {
				this.game.getGoList().deleteZombieFromList(j);

			}
		}
	}

	@Override
	public void cuentaCiclos() {
		// No se usa para los peashooter
	}

	@Override
	public String helpText() {
		return "[P]eashooter : Cost: " + this.cost + " suncoins  Harm: " + this.damage;
	}

}