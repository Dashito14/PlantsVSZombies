package tp.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.factories.PlantFactory;
import tp.p3.factories.ZombieFactory;
import tp.p3.lists.GameObjectList;
import tp.p3.objects.GameObject;
import tp.p3.objects.Plant;
import tp.p3.objects.Zombie;
import tp.p3.util.GamePrinter;
import tp.p3.util.BoardPrinter;
import tp.p3.util.ReleasePrinter;
import tp.p3.logic.EstadoPartida;

public class Game {

	static final int FILA = 4;
	static final int COLUMNA = 8;
	static final String wrongPrefixMsg = "unknown game attribute: ";
	static final String lineTooLongMsg = "too many words on line commencing: ";
	static final String lineTooShortMsg = "missing data on line commencing: ";

	private int ciclo;
	private int solesIniciales;
	private Random rand;
	private Level level;
	private ZombieManager zManager;
	private SuncoinManager sunManager;
	private GameObjectList goList;
	private GamePrinter gamePrinter;

	private EstadoPartida[] estado;
	int contadorArray;
	int inicio;
	
	/** Constructura */
	public Game(Level level, Random random) {
		this.ciclo = 0;
		this.solesIniciales = 50;
		this.rand = random;
		this.level = level;
		this.zManager = new ZombieManager(this.level.getNumZombies());
		this.sunManager = new SuncoinManager(this.solesIniciales);
		this.goList = new GameObjectList();
		this.gamePrinter = new ReleasePrinter(this);
		this.estado = new EstadoPartida[10];
		this.contadorArray = 0;
		this.push(this.ciclo, this.sunManager.getCoins(), this.zManager.getZombiesRestantes(),
				this.goList.getZombieList(), this.goList.getPlantList());
	}

	/**
	 * update: metodo que actualiza la situacion del tablero realizando todos los
	 * movimientos y acciones de los objetos sobre el tablero.
	 */
	public void update() {
	
		push(this.ciclo, this.sunManager.getCoins(), this.zManager.getZombiesRestantes(),
				this.goList.getZombieList(), this.goList.getPlantList());
		
		
		/* update de la lista de plantas */
		for (int i = 0; i < this.goList.getSizeP(); i++) {
			this.goList.getPlantList()[i].update();
		}
		/* update de la lista de zombies */
		for (int i = 0; i < this.goList.getSizeZ(); i++) {
			this.goList.getZombieList()[i].update();
		}

		/* sumamos ciclo */
		this.ciclo++;
		
	
		
	}
	
	void push(int ciclo, int soles, int zombiesRestantes,
			GameObject[] zombieList, GameObject[] plantList) {
		
		
		if(contadorArray > 9) {
			contadorArray = 0;
		}
		
		this.estado[contadorArray] = new EstadoPartida(ciclo, soles, zombiesRestantes, zombieList, plantList);
		
		contadorArray++;
		
	}
	
	public void pop() {
		contadorArray--;
		if(contadorArray < 0) {
			contadorArray = 9;
		}
		this.ciclo = estado[contadorArray].getCiclos();
		this.sunManager.setCoins(estado[contadorArray].getSoles());
		this.zManager.setZombiesRestantes(estado[contadorArray].getZombiesRestantes());
		this.goList.setPlantList(estado[contadorArray].plantList);
		this.goList.setZombieList(estado[contadorArray].zombieList);
	}
	
	public void undo() {
		this.pop();
	}
	
	public void redo() {
		contadorArray = contadorArray + 2;
		pop();
	}

	/** reset: Metodo que reinicia una partida */
	public void reset() {
		this.ciclo = 0;
		this.solesIniciales = 50;
		this.getGoList().setSizeP(0);
		this.getGoList().setSizeZ(0);
		this.getGoList().setCapacityP(this.getGoList().MAX);
		this.getGoList().setCapacityZ(this.getGoList().MAX);
		this.sunManager.setCoins(this.solesIniciales);
		this.zManager.setZombiesRestantes(this.level.getNumZombies());
	}

	/** exit: metodo que termina la ejecucion de una partida */
	public void exit() {
		System.out.println("** Game over!: User exit **\n");
		System.exit(0);
	}

	/** hasGanado: metodo booleano que comprueba si has ganado la partida */
	public boolean hasGanado() {
		return (this.zManager.noZombies() && this.goList.getSizeZ() == 0);
	}

	/** hasPerdido: metodo booleano que comprueba si has perdido la partida */
	public boolean hasPerdido() {
		for (int i = 0; i < this.goList.getSizeZ(); i++) {
			if (this.goList.getZombieList()[i].getY() == 0)
				return true;
		}
		return false;
	}

	/**
	 * controllerSuncoins: Controla que quedan monedas para poder aï¿½adir las
	 * monedas
	 */
	public boolean controllerSuncoins(int coste) {
		if (this.sunManager.getCoins() < coste) {
			return false;
		} else
			return true;
	}

	/**
	 * addPlantToGame: añade una planta anteriorimente creada a la lista de objetos
	 */
	public void addPlantToGame(Plant plant, int x, int y) {
		plant.setX(x);
		plant.setY(y);
		plant.setGame(this);
		this.goList.addPlantToList(plant);
		this.sunManager.lessSuncoins(plant.getCost());
		this.update();
	}

	/**
	 * addZombie: añade un zombie al juego si le corresponde la probabilidad en
	 * dicho turno
	 */
	public void addZombie(Zombie zombieCreado) {
		if (zombieCreado != null) {
			if (this.zManager.isZombieAdded(this.rand, this.level)) {
				boolean added = false;
				do {
					int fila = this.rand.nextInt(4);
					if (!hayObjeto(fila, 7)) {
						this.goList.addZombieToList(zombieCreado);
						zombieCreado.setX(fila);
						zombieCreado.setY(7);
						zombieCreado.setGame(this);
						this.zManager.lessZombiesLeft();
						added = true;
					}
				} while (!added);
			}
		}
		;
	}

	/**
	 * load: guarda los datos de una partida especificada desde un fichero
	 * 
	 * @throws IOException
	 * @throws FileContentsException
	 */
	public void load(FileReader file) throws IOException, FileContentsException {

		BufferedReader bf = new BufferedReader(file);
		String cabecera = null;
		String[] atributos;
		if ((cabecera = bf.readLine()).equalsIgnoreCase("Plants Vs Zombies v3.0")) {
			cabecera = bf.readLine(); // line en blanco de la cabecera

			/* ciclos */
			atributos = loadLine(bf, "cycle", false);
			int cycle = Integer.parseInt(atributos[0]);
			if (cycle >= 0)
				this.ciclo = cycle;
			else
				throw new FileContentsException("Load failed: invalid file contents.\n");

			/* sunCoins */
			atributos = loadLine(bf, "sunCoins", false);
			int sc = Integer.parseInt(atributos[0]);
			if (sc >= 0)
				this.sunManager.setCoins(sc);
			else
				throw new FileContentsException("Load failed: invalid file contents.\n");

			/* level */
			atributos = loadLine(bf, "level", false);
			Level lvl = Level.parse(atributos[0]);
			if (lvl != null)
				this.level = lvl;
			else
				throw new FileContentsException("Load failed: invalid file contents.\n");

			/* remZombies */
			atributos = loadLine(bf, "remZombies", false);
			int numZ = Integer.parseInt(atributos[0]);
			if (numZ >= 0)
				this.zManager.setZombiesRestantes(numZ);
			else
				throw new FileContentsException("Load failed: invalid file contents.\n");

			/* plantList */
			atributos = loadLine(bf, "plantList", true);
			for (int i = 0; i < atributos.length; i++) {
				String[] object = atributos[i].split(":");
				try {
					Plant plant = PlantFactory.getPlant(object[0]);
					plant.setLife(Integer.parseInt(object[1]));
					plant.setX(Integer.parseInt(object[2]));
					plant.setY(Integer.parseInt(object[3]));
					plant.setFrequency(Integer.parseInt(object[4]));
					plant.setGame(this);
					this.goList.addPlantToList(plant);
				} catch (CommandParseException e) {
					throw new FileContentsException("Load failed: invalid file contents.\n");
				} catch (NumberFormatException n) {
					throw new FileContentsException("Load failed: invalid file contents.\n");
				}
			}

			/* zombieList */
			atributos = loadLine(bf, "zombieList", true);
			for (int i = 0; i < atributos.length; i++) {
				String[] object = atributos[i].split(":");
				int zombieType = -1;
				switch (object[0]) {
				case "z": // zombieComun
					zombieType = 0;
					break;
				case "w": // caracubo
					zombieType = 1;
					break;
				case "x": // deportista
					zombieType = 2;
					break;
				default:
					throw new FileContentsException("Load failed: invalid file contents.\n");
				}
				Zombie zombie = ZombieFactory.addZombieToGame(zombieType);
				try {
					zombie.setLife(Integer.parseInt(object[1]));
					zombie.setX(Integer.parseInt(object[2]));
					zombie.setY(Integer.parseInt(object[3]));
					zombie.setFrequency(Integer.parseInt(object[4]));
					zombie.setGame(this);
					this.goList.addZombieToList(zombie);
				} catch (NumberFormatException n) {
					throw new FileContentsException("Load failed: invalid file contents.\n");
				}
			}

		} else
			throw new FileContentsException("Load failed: invalid file contents.\n");

		bf.close();
	}

	/**
	 * store: guarda los datos de la partida actual en un fichero
	 * 
	 * @throws IOException
	 */
	public void store(FileWriter archivo) throws IOException {
		String dosP = ":";
		BufferedWriter bw;
		bw = new BufferedWriter(archivo);
		/* escribe la cabecera */
		bw.write("Plants Vs Zombies v3.0\n\n");

		/* cycle */
		bw.write("cycle: " + this.ciclo + "\n");

		/* Suncoins */
		bw.write("sunCoins: " + this.sunManager.getCoins() + "\n");

		/* Level */
		bw.write("level: " + this.level.toString() + "\n");

		/* Remaining Zombies */
		bw.write("remZombies: " + this.zManager.getZombiesRestantes() + "\n");

		/* plantList */
		bw.write("plantList: ");
		for (int i = 0; i < this.goList.getSizeP(); i++) {
			bw.write(this.goList.getPlantList()[i].getSimbolo() + dosP + this.goList.getPlantList()[i].getLife() + dosP
					+ this.goList.getPlantList()[i].getX() + dosP + this.goList.getPlantList()[i].getY() + dosP
					+ this.goList.getPlantList()[i].getFrequency());
			if (i != this.goList.getSizeP() - 1) {
				bw.write(",");
			}
		}
		bw.write("\n");
		/* zombieList */
		bw.write("zombieList: ");
		for (int i = 0; i < this.goList.getSizeZ(); i++) {
			bw.write(this.goList.getZombieList()[i].getSimbolo() + dosP + this.goList.getZombieList()[i].getLife()
					+ dosP + this.goList.getZombieList()[i].getX() + dosP + this.goList.getZombieList()[i].getY() + dosP
					+ this.goList.getZombieList()[i].getFrequency());
			if (i != this.goList.getSizeZ() - 1) {
				bw.write(",");
			}
		}

		bw.close();
	}
	

	/**
	 * hayObjeto: devuelve un booleano que indica si una posición está o no ocupada.
	 */
	public boolean hayObjeto(int x, int y) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < this.goList.getSizeP()) {
			if (this.goList.getPlantList()[i].getX() == x && this.goList.getPlantList()[i].getY() == y) {
				encontrado = true;
			} else
				i++;
		}
		i = 0;
		while (!encontrado && i < this.goList.getSizeZ()) {
			if (this.goList.getZombieList()[i].getX() == x && this.goList.getZombieList()[i].getY() == y) {
				encontrado = true;
			} else
				i++;
		}
		return encontrado;
	}
	

	/** setGamePrinter: metodo que establece el metodo de impresion del juego. */
	public void setGamePrinter(BoardPrinter printer) {
		this.gamePrinter = (GamePrinter) printer;
	}
	

	/** loadLine: obtiene la informacion de las lineas guardadas en un archivo */
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
			throws IOException, FileContentsException {
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if (!line.startsWith(prefix + ":"))
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line
		// then trim it to get the attribute contents
		String contentString = line.substring(prefix.length() + 1).trim();
		String[] words;
		// the attribute contents are not empty
		if (!contentString.equals("")) {
			if (!isList) {
				// split non-list attribute contents into words
				// using 1-or-more-white-spaces as separator
				words = contentString.split("\\s+");
				// a non-list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0-or-more-white-spaces as separator
				words = contentString.split(",\\s*");
			// the attribute contents are empty
		} else {
			// a non-list attribute with empty contents is invalid
			if (!isList)
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attibute with empty contents is valid;
			// use a zero-length array to store its words
			words = new String[0];
		}
		return words;
	}
	public Game gameBackUp() {
		Game auxGame = new Game(this.level, this.rand);
		auxGame.ciclo = this.ciclo;
		auxGame.gamePrinter = this.gamePrinter;
		auxGame.goList = this.goList;
		auxGame.sunManager = this.sunManager;
		auxGame.zManager = this.zManager;
		
		return auxGame;
	}
	
	/* Metodos setters y getters */

	public int getCiclo() {
		return ciclo;
	}

	public int getSolesIniciales() {
		return solesIniciales;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public ZombieManager getzManager() {
		return zManager;
	}

	public SuncoinManager getSunManager() {
		return sunManager;
	}

	public GameObjectList getGoList() {
		return goList;
	}

	public static int getFila() {
		return FILA;
	}

	public static int getColumna() {
		return COLUMNA;
	}

	public GamePrinter getGamePrinter() {
		return gamePrinter;
	}

}
