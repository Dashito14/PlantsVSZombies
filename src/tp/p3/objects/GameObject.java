package tp.p3.objects;

import tp.p3.logic.Game;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected int life;
	protected int frequency;
	protected int damage;
	protected char simbolo;
	protected Game game;

	/** isObject: comprueba que objeto es comparando su simbolo */
	public abstract boolean isObject(char id);

	/** update: actualiza el estado del objeto */
	public abstract void update();

	/** getDamaged: metodo que realiza la resta de vida segun el daño recibido */
	public void getDamaged(int damageReceived) {
		this.life -= damageReceived;
	}

	/** cuentaciclos: cuenta los ciclos */
	public abstract void cuentaCiclos();

	/** helpText: devuelve un string con la informacion de la vida y el coste. */
	public abstract String helpText();

	/** toString: muestra un string con el simbolo y la vida actual de un objeto */
	public abstract String toString();

	/* metodos getters y setters de la clase abstracta GameObject */

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
