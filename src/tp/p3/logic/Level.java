package tp.p3.logic;

public enum Level {
	EASY("easy", 3, 0.1), HARD("hard", 5, 0.2), INSANE("insane", 10, 0.3);

	private String nombre;
	private int numZombies;
	private double frecuencia;

	// Constructor
	private Level(String name, int numeroZs, double freq) {
		this.nombre = name;
		this.numZombies = numeroZs;
		this.frecuencia = freq;
	}

	public static Level parse(String inputString) {
		for (Level level : Level.values())
			if (level.name().equalsIgnoreCase(inputString))
				return level;
		return null;
	}

	public static String all (String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level. values() )
		sb. append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0, allLevels . length()-separator.length());
		}

	/** metodos getters y setters */
	public int getNumZombies() {
		return this.numZombies;
	}

	public double getFrecuencia() {
		return this.frecuencia;
	}

}
