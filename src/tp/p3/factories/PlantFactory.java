package tp.p3.factories;

import tp.p3.exceptions.CommandParseException;
import tp.p3.objects.Nuez;
import tp.p3.objects.Peashooter;
import tp.p3.objects.Petacereza;
import tp.p3.objects.Plant;
import tp.p3.objects.Sunflower;

public class PlantFactory {

	public static Plant[] availablePlants = { new Sunflower(), new Peashooter(), new Petacereza(), new Nuez(), };

	public static Plant getPlant(String plantName) throws CommandParseException {
		if (plantName.equalsIgnoreCase("s") || plantName.equalsIgnoreCase("sunflower")) {
			return new Sunflower();
		} else if (plantName.equalsIgnoreCase("p") || plantName.equalsIgnoreCase("peashooter")) {
			return new Peashooter();
		} else if (plantName.equalsIgnoreCase("c") || plantName.equalsIgnoreCase("petacereza")) {
			return new Petacereza();
		} else if (plantName.equalsIgnoreCase("n") || plantName.equalsIgnoreCase("nuez")) {
			return new Nuez();
		} else
			throw new CommandParseException("Unknown plant name: " + plantName);
	}

	/** listOfAvailablePlants: Lista las plantas disponibles */
	public static String listOfAvailablePlants() {
		StringBuilder sb = new StringBuilder();
		for (Plant plant : availablePlants) {
			sb.append(plant.helpText() + "\n");
		}
		return sb.toString();
	}
}
