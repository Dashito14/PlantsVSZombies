package tp.p3.commands;

import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;
import tp.p3.util.DebugPrinter;
import tp.p3.util.ReleasePrinter;

public class PrintModeCommand extends Command {

	public static final String CommandText = "print";
	public static final String CommandTextMsg = "[P]rintMode <mode>";
	public static final String HelpTextMsg = "Change print mode [Release | Debug].";
	private String mode;

	public PrintModeCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	
	public boolean execute(Game game) {
		if (this.mode.equalsIgnoreCase("release")) {
			game.setGamePrinter(new ReleasePrinter(game));
			return true;
		} else if (this.mode.equalsIgnoreCase("debug") || this.mode.equalsIgnoreCase("d")) {
			game.setGamePrinter(new DebugPrinter(game));
			return true;
		} else
			return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].equalsIgnoreCase("p") || commandWords[0].equalsIgnoreCase("printMode")) {
			if(commandWords.length != 2) {
				throw new CommandParseException("Incorrect number of arguments for printmode command: [P]rintmode <release|debug>.\n");
			}
			PrintModeCommand p = new PrintModeCommand();
			if (commandWords[1].equalsIgnoreCase("r") || commandWords[1].equalsIgnoreCase("release")) {
				p.mode = "release";
				return p;
			} else if (commandWords[1].equalsIgnoreCase("d") || commandWords[1].equalsIgnoreCase("debug")) {
				p.mode = "debug";
				return p;
			} else
				throw new CommandParseException("Unknown print mode: " + commandWords[1] + "\n");
		} else {
			return null;
		}
	}

}
