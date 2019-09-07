package tp.p3.commands;

import tp.p3.factories.ZombieFactory;
import tp.p3.logic.Game;
import tp.p3.objects.Zombie;

public class AddZombieCommand extends Command {

		
	public static final String CommandText = "addZombie";
	public static final String CommandTextMsg = "addzombie <zombie> <x> <y>";
	public static final String HelpTextMsg = "Add a zombie in position (x,y).";
	
	
	public AddZombieCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean execute(Game game) {
		int zombieType = game.getRand().nextInt(3);
		Zombie zombie = ZombieFactory.addZombieToGame(zombieType);
		game.addZombie(zombie);
		return false;
	}

	public Command parse(String[] commandWords) {
		return null;
	}
	
}
