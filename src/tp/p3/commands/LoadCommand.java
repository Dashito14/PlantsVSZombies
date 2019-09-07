package tp.p3.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tp.p3.exceptions.CommandParseException;
import tp.p3.exceptions.FileContentsException;
import tp.p3.logic.Game;

public class LoadCommand extends Command {

	public static final String CommandText = "load";
	public static final String CommandTextMsg = "[Lo]ad <filename>";
	public static final String HelpTextMsg = "Load the state of the game from a file.";
	private String fileName;

	public LoadCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		Game auxGame = game.gameBackUp();
		try (FileReader file = new FileReader(fileName)){
			game.load(file);
			file.close();
		} catch (FileNotFoundException e) {
			game = auxGame.gameBackUp();
			System.err.println("File not found.\n");
		} catch (IOException e) {
			game = auxGame.gameBackUp();
			e.printStackTrace();
		} catch (FileContentsException e) {
			game = auxGame.gameBackUp();
			System.err.println(e.getMessage());
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].equalsIgnoreCase("lo") || commandWords[0].equalsIgnoreCase("load")) {
			if (commandWords.length != 2) {
				throw new CommandParseException("Incorrect number of arguments for load command: [L]oad <filename>.\n");
			}
			LoadCommand lo = new LoadCommand();
			lo.fileName = commandWords[1];
			return lo;
		} else {
			return null;
		}
	}

}
