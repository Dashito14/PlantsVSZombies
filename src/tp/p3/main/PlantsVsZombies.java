package tp.p3.main;

import java.util.Random;
import java.util.Scanner;

import tp.p3.logic.Controller;
import tp.p3.logic.Game;
import tp.p3.logic.Level;

public class PlantsVsZombies {

	public static void main(String[] args) {
		Random random = new Random();

		try {
			if (args.length < 1 || args.length > 2)
				throw new RuntimeException("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]\n");
			Level level;
			if (args[0].equalsIgnoreCase("easy"))
				level = Level.EASY;
			else if (args[0].equalsIgnoreCase("hard"))
				level = Level.HARD;
			else if (args[0].equalsIgnoreCase("insane"))
				level = Level.INSANE;
			else
				throw new RuntimeException(
						"Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE\n");

			random = (args.length > 1) ? new Random(Long.parseLong(args[1])) : new Random();
			Game game = new Game(level, random);
			System.out.println("Welcome to PlantVsZombies v3.0.");
			if (args.length > 1)
				System.out.println("Random seed used: " + args[1] + "\n");
			else
				System.out.println("The seed used has been randomly obtained.\n");
			Controller controller = new Controller(game, new Scanner(System.in));
			controller.run();
		} catch (NumberFormatException n) {
			System.err.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number\n");
			System.exit(0);
		} catch (RuntimeException r) {
			System.err.println(r.getMessage());
			System.exit(0);
		}

	}

}
