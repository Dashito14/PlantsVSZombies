package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.logic.Game;

public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;

	public Command(String commandText, String commandInfo, String helpInfo) {
		this.commandText = commandInfo; // exit
		helpText = helpInfo; // terminate the program
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}

	/*
	 * Some commands may generate an error in the execute or parse methods. In the
	 * absence of exceptions , they must the tell the controller not to print the
	 * board
	 */
	public abstract boolean execute(Game game) throws CommandExecuteException;

	public abstract Command parse(String[] commandWords) throws CommandParseException;

	public String helpText() {
		return " " + commandText + ": " + helpText;
	}

}
