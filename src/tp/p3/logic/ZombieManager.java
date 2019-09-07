package tp.p3.logic;

import java.util.Random;

public class ZombieManager {
	private int zombiesRestantes;

	/** Constructor */
	public ZombieManager(int zombies) {
		this.zombiesRestantes = zombies;
	}

	/** isZombieAdded: saber si hay que añadir zombie o no en el ciclo */
	public boolean isZombieAdded(Random rand, Level level) {
		if (this.zombiesRestantes != 0) {
			if (rand.nextDouble() < level.getFrecuencia()) {
				return true;
			}
		}
		return false;
	}

	/** lessZombiesLeft: metodo que resta 1 al numero de zombies restantes */
	public void lessZombiesLeft() {
		this.zombiesRestantes--;
	}

	/** noZombies: metodo booleano que comprueba si hay zombies restantes */
	public boolean noZombies() {
		return this.zombiesRestantes == 0;
	}

	/** Getter and setter de zombiesRestantes */
	public int getZombiesRestantes() {
		return zombiesRestantes;
	}

	public void setZombiesRestantes(int zombiesRestantes) {
		this.zombiesRestantes = zombiesRestantes;
	}

}
