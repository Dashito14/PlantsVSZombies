package tp.p3.commands;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;
import tp.p3.factories.PlantFactory;
import tp.p3.logic.Game;
import tp.p3.objects.Plant;

public class AddCommand extends Command {

	public static final String CommandText = "add";
	public static final String CommandTextMsg = "[A]dd <plant> <x> <y>";
	public static final String HelpTextMsg = "add flower.";
	private Plant plantName;
	private int x;
	private int y;

	public AddCommand() {
		super(CommandText, CommandTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		if (!game.hayObjeto(this.x, this.y)) {
			if (game.controllerSuncoins(this.plantName.getCost())) {
				game.addPlantToGame(plantName, this.x, this.y);
				return true;
			} else {
				throw new CommandExecuteException(
						"Failed to add " + this.plantName.getSimbolo() + ": not enough suncoins to buy it.\n");
			}
		} else {
			throw new CommandExecuteException("Failed to add " + this.plantName.getSimbolo() + ": position (" + this.x
					+ "," + this.y + ") is already ocuppied.\n");
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException, NumberFormatException {
		if (commandWords[0].equalsIgnoreCase("a") || commandWords[0].equalsIgnoreCase("add")) {
			if (commandWords.length != 4) {
				throw new CommandParseException(
						"Invalid number of arguments for add command: [A]dd <plant> <x> <y>.\n");
			} else {
				AddCommand add = new AddCommand();
				add.plantName = PlantFactory.getPlant(commandWords[1]);
				add.x = Integer.parseInt(commandWords[2]);
				add.y = Integer.parseInt(commandWords[3]);
				if ((add.x >= 0 && add.x <= 3) && (add.y >= 0 && add.y <= 6)) {
					return add;
				} else {
					throw new CommandParseException("Failed to add " + add.plantName.getSimbolo() + ": (" + add.x + ","
							+ add.y + ") is an invalid position.\n");
				}
			}
		} else
			return null;
	}

}
