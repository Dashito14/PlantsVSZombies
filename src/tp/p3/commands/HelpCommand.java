package tp.p3.commands;

import tp.p3.logic.Game;

public class HelpCommand extends NoParamsCommand{
	
	public static final String CommandText = "help";
	public static final String CommandTextMsg = "[H]elp";
	public static final String HelpTextMsg = "print this help message.";

	public HelpCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}

}
