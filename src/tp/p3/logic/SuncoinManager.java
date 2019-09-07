package tp.p3.logic;

public class SuncoinManager {
	private int coins;

	/** Constructor */
	public SuncoinManager(int coins) {
		this.coins = coins;
	}

	/** addSuncoins: metodo que suma las Suncoins generadas */
	public void addSuncoins() {
		this.coins += 20;
	}

	/** lessSuncoins: metodo que resta las Suncoins utilizadas */
	public void lessSuncoins(int sc) {
		this.coins -= sc;
	}

	/* Getter y setter de la clase SuncoinManager */
	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

}
