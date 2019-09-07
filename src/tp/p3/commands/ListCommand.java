package tp.p3.commands;

import tp.p3.factories.PlantFactory;
import tp.p3.logic.Game;

public class ListCommand extends NoParamsCommand {
	
	public static final String CommandText = "list";
	public static final String CommandTextMsg = "[L]ist";
	public static final String HelpTextMsg = "print the list of available plants.";

	public ListCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(PlantFactory.listOfAvailablePlants());
		return false;
	}

}
