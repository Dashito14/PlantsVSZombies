package tp.p3.commands;

import tp.p3.logic.Game;

public class ExitCommand extends NoParamsCommand {
	
	public static final String CommandText = "exit";
	public static final String CommandTextMsg = "[E]xit";
	public static final String HelpTextMsg = "terminate the program.";
	
	public ExitCommand(){
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}	

	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}

	@Override
	public String helpText() {
		return super.helpText();
	}
	
}
