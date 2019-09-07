package tp.p3.commands;

import tp.p3.logic.Game;

public class UpdateCommand extends NoParamsCommand {
	
	public static final String CommandText = "none";
	public static final String CommandTextMsg = "[n]one";
	public static final String HelpTextMsg = "skips cycle.";

	public UpdateCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		AddZombieCommand addZombieCommand = new AddZombieCommand();
		addZombieCommand.execute(game);
		return true;
	}

}
