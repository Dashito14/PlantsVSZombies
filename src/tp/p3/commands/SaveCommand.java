package tp.p3.commands;

import java.io.FileWriter;
import java.io.IOException;

import tp.p3.logic.Game;
import tp.p3.util.MyStringUtils;

public class SaveCommand extends Command {

	public static final String CommandText = "save";
	public static final String CommandTextMsg = "[S]ave <filename>";
	public static final String HelpTextMsg = "Save the state of the game to a file.";

	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";

	public static final String nameNotValid = " file name not valid.";
	private String nombreFichero;

	public SaveCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		if (MyStringUtils.isValidFilename(this.nombreFichero)) {
			try (FileWriter archivo = new FileWriter(this.nombreFichero + ".dat")) {
				game.store(archivo);
				System.out.println("Game successfully saved in file " + this.nombreFichero
						+ ".dat. Use the load command to reload it");
				archivo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			System.err.println(this.nombreFichero + nameNotValid);
			return false;
		}

	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].equalsIgnoreCase("s") || commandWords[0].equalsIgnoreCase("save")) {
			SaveCommand save = new SaveCommand();
			save.nombreFichero = commandWords[1];
			return save;
		}

		return null;

	}

}
