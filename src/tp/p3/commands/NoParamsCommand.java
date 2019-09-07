package tp.p3.commands;

import tp.p3.commands.Command;
import tp.p3.exceptions.CommandParseException;

public abstract class NoParamsCommand extends Command {

	private String errorMsg = " command has no arguments";
	
	public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords[0].equalsIgnoreCase("e") || commandWords[0].equalsIgnoreCase("exit")) {
			if(commandWords.length != 1) {
				throw new CommandParseException("Exit" + this.errorMsg + "\n");
			}
			return new ExitCommand();
		} else if (commandWords[0].equalsIgnoreCase("h") || commandWords[0].equalsIgnoreCase("help")) {
			if(commandWords.length != 1) {
				throw new CommandParseException("Help" + this.errorMsg + "\n");			}
			return new HelpCommand();
		} else if (commandWords[0].equalsIgnoreCase("l") || commandWords[0].equalsIgnoreCase("list")) {
			if(commandWords.length != 1) {
				throw new CommandParseException("List" + this.errorMsg + "\n");
			}
			return new ListCommand();
		} else if (commandWords[0].equalsIgnoreCase("r") || commandWords[0].equalsIgnoreCase("reset")) {
			if(commandWords.length != 1) {
				throw new CommandParseException("Reset" + this.errorMsg + "\n");
			}
			return new ResetCommand();
		} else if (commandWords[0].equalsIgnoreCase("n") || commandWords[0].equalsIgnoreCase("none")
				|| commandWords[0].equalsIgnoreCase("")) {
			if(commandWords.length != 1) {
				throw new CommandParseException("None" + this.errorMsg + "\n");
			}
			return new UpdateCommand();
		} else if (commandWords[0].equalsIgnoreCase("z") || commandWords[0].equalsIgnoreCase("zombieList")) {
			if(commandWords.length != 1) {
				throw new CommandParseException("Zombielist" + this.errorMsg + "\n");
			}
			return new ZombieListCommand();
		} else if (commandWords[0].equalsIgnoreCase("redo")) {
			return new Redo();
		    }
		 else if (commandWords[0].equalsIgnoreCase("undo")) {
			return new Undo();
		}
		 else return null;

	}

}
