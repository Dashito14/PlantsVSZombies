package tp.p3.objects;

public class Sunflower extends Plant {

	/** Constructor */
	public Sunflower() {
		this.life = 1;
		this.cost = 20;
		this.frequency = 2;
		this.damage = 0;
		this.simbolo = 's';
	}

	public void cuentaCiclos() {
		if (this.frequency == 0) {
			this.frequency = 2;
		}
		this.frequency--;
	}

	@Override
	public String toString() {
		return "S [" + life + "]";
	}

	@Override
	public boolean isObject(char id) {
		if (this.simbolo == id)
			return true;
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.cuentaCiclos();
		if (this.frequency == 0) {
			this.game.getSunManager().addSuncoins();
		}
	}

	@Override
	public String helpText() {
		return "[S]unflower : Cost: " + this.cost + " suncoins  Harm: " + this.damage;
	}

}
