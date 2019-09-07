package tp.p3.objects;

public class ZombieComun extends Zombie {

	/** Constructor con parametros */
	public ZombieComun() {
		this.life = 5;
		this.damage = 1;
		this.frequency = 2;
		this.simbolo = 'z';
	}

	@Override
	public void cuentaCiclos() {
		if (this.frequency == 0) {
			this.frequency = 2;
		}
		this.frequency--;
	}

	@Override
	public String toString() {
		return "Z [" + life + "]";
	}

	@Override
	public boolean isObject(char id) {
		if (this.simbolo == id) {
			return true;
		}
		return false;
	}

	@Override
	public String helpText() {
		return "[Z]ombie comun : Speed:" + this.frequency + " Harm: " + this.damage + " Life: " + this.life;
	}
}
