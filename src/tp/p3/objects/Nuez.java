package tp.p3.objects;

public class Nuez extends Plant {

	/* Constructora */
	public Nuez() {
		this.life = 10;
		this.cost = 50;
		this.simbolo = 'n';
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
		/* En el update de Nuez no es necesario implementar nada */
	}

	@Override
	public void cuentaCiclos() {
		/* No se cuentan ciclos para este objeto */

	}

	@Override
	public String toString() {
		return "N [" + this.life + "]";
	}

	@Override
	public String helpText() {
		return "[N]uez : Cost: " + this.cost + " suncoins  Harm: " + this.damage;
	}

}
