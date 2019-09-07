package tp.p3.objects;

public class Petacereza extends Plant {

	/* Constructora */
	public Petacereza() {
		this.life = 2;
		this.cost = 50;
		this.frequency = 2;
		this.damage = 10;
		this.simbolo = 'c';
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
		// si es el turno en el que va a explotar
		if (this.frequency == 0) {
			int x = this.x;
			int y = this.y;
			int numZombies = 8, i = 0;
			while (numZombies > 0 && i < this.game.getGoList().getSizeZ()) {
				int filaZ = this.game.getGoList().getZombieList()[i].getX();
				int columnaZ = this.game.getGoList().getZombieList()[i].getY();
				/* comprobamos que hay un zombie en alguna de las posiciones inmediatas */
				if ((x == filaZ + 1 && y == columnaZ + 1) || (x == filaZ && y == columnaZ + 1)
						|| (x == filaZ - 1 && y == columnaZ + 1) || (x == filaZ - 1 && y == columnaZ)
						|| (x == filaZ - 1 && y == columnaZ - 1) || (x == filaZ && y == columnaZ - 1)
						|| (x == filaZ + 1 && y == columnaZ - 1) || (x == filaZ + 1 && y == columnaZ)) {
					/* aplicamos el da�o */
					this.game.getGoList().getZombieList()[i].getDamaged(this.damage);
					/* comprobamos si ha muerto */
					if (this.game.getGoList().getZombieList()[i].getLife() <= 0) {
						this.game.getGoList().deleteZombieFromList(i);
					}
					// restamos uno de los zombies de su alrededor
					numZombies--;
					// restamos una posicion de memoria puesto que ha sido eliminado un zombie de la
					// lista, y se han desplazado los restantes una posicion a la izquierda
					i--;
				}
				i++;
			}
			/* eliminamos la petacereza de la lista */
			int j = 0;
			boolean encontrado = false;
			while (!encontrado) {
				if (x == this.game.getGoList().getPlantList()[j].getX()
						&& y == this.game.getGoList().getPlantList()[j].getY()) {
					encontrado = true;
				}
				j++;
			}
			this.game.getGoList().deletePlantFromList(j);
		} else
			this.cuentaCiclos();
	}

	@Override
	public void cuentaCiclos() {
		this.frequency--;
	}

	@Override
	public String toString() {
		return "C [" + this.life + "]";
	}

	@Override
	public String helpText() {
		return "Peta[c]ereza : Cost: " + this.cost + " suncoins  Harm: " + this.damage;
	}

}
