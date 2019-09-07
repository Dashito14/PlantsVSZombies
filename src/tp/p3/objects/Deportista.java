package tp.p3.objects;

public class Deportista extends Zombie {

	/* Constructora */
	public Deportista() {
		this.life = 2;
		this.damage = 1;
		this.frequency = 1;
		this.simbolo = 'x';
	}

	@Override
	public boolean isObject(char id) {
		if (this.simbolo == id) {
			return true;
		}
		return false;
	}

	@Override
	public void cuentaCiclos() {
		if (this.frequency == 0) {
			this.frequency = 1;
		}
		this.frequency--;
	}

	@Override
	public String toString() {
		return "X [" + this.life + "]";
	}

	@Override
	public String helpText() {
		return "[Z]ombie deportista : Speed: " + this.frequency + " Harm: " + this.damage + " Life: " + this.life;

	}
}
