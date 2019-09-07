package tp.p3.commands;

import tp.p3.logic.Game;

public class ResetCommand extends NoParamsCommand {
	
	public static final String CommandText = "reset";
	public static final String CommandTextMsg = "[R]eset";
	public static final String HelpTextMsg = "resets game.";

	public ResetCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		game.reset();
		return true;
	}

}
