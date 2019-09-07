package tp.p3.exceptions;

public class CommandParseException extends Exception {

	private String cadena;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public CommandParseException(String commandWords) {
		cadena = commandWords;
	}

	/* Metodos getters y setters*/
	public String getCadena() {
		return cadena;
	}
	
}
