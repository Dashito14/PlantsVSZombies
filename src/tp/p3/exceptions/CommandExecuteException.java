package tp.p3.exceptions;

public class CommandExecuteException extends Exception {

	private String cadena;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CommandExecuteException(String commandException) {
		this.cadena = commandException;
	}

	/*Metodos getters y setters*/
	public String getCadena() {
		return cadena;
	}

}
