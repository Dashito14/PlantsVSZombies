package tp.p3.objects;

public class Caracubo extends Zombie {

	/* Constructora */
	public Caracubo() {
		this.life = 8;
		this.damage = 1;
		this.frequency = 4;
		this.simbolo = 'w';
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
			this.frequency = 4;
		}
		this.frequency--;
	}

	@Override
	public String toString() {
		return "W [" + this.life + "]";
	}

	@Override
	public String helpText() {
		return "[Z]ombie caracubo : Speed: " + this.frequency + " Harm: " + this.damage + " Life: " + this.life;
	}

}
