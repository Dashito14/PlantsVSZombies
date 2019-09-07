package tp.p3.commands;

import tp.p3.factories.ZombieFactory;
import tp.p3.logic.Game;

public class ZombieListCommand extends NoParamsCommand {
	
	public static final String CommandText = "zombieList";
	public static final String CommandTextMsg = "[Z]ombieList";
	public static final String HelpTextMsg = "print the list of zombies.";

	public ZombieListCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(ZombieFactory.listOfAvailableZombies());
		return false;
	}

}
