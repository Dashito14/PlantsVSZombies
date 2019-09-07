package tp.p3.commands;

import tp.p3.exceptions.CommandParseException;

public class CommandParser {

	private static Command[] availableCommands = { new AddCommand(), new HelpCommand(), new ResetCommand(),
			new ExitCommand(), new ListCommand(), new UpdateCommand(), new PrintModeCommand(), new SaveCommand(),
			new LoadCommand(), new ZombieListCommand(), new AddZombieCommand(), new Undo(), new Redo() };

	/**
	 * parseCommand: invoca al metodo parse de cada subclase de Command
	 * 
	 * @throws CommandParseException
	 */
	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		Command com = null, comandoValido = null;
		for (Command comando : availableCommands) {
			com = comando.parse(commandWords);
			if (com != null)
				comandoValido = com;
		}
		if (comandoValido != null)
			return comandoValido;
		else {
			throw new CommandParseException("Unknown command. Use 'help' to see the available commands.\n");
		}
	}

	/** commandHelp: invoca al método helpText de cada subclase de Command. */
	public static String commandHelp() {
		StringBuilder cadena = new StringBuilder("The available commands are:\n");
		for (Command command : availableCommands) {
			cadena.append(command.helpText() + "\n");
		}
		return cadena.toString();
	}
}
