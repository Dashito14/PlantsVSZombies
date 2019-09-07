package tp.p3.logic;

import java.util.Scanner;
import tp.p3.commands.Command;
import tp.p3.commands.CommandParser;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.CommandParseException;

public class Controller {
	private Game game;
	private Scanner in;
	private boolean noPrintGame = true;

	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}

	public void run() {
		printGame();
		do {
			System.out.println("Comando > ");
			String comando = this.in.nextLine();
			String[] partesComando = comando.toLowerCase().split(" ");
			try {
				Command command = CommandParser.parseCommand(partesComando);
				if (command != null) {
					if (command.execute(game)) {
						printGame();
					}
				}
			} catch (CommandParseException e) {
				System.err.println(e.getCadena());
			} catch (CommandExecuteException e) {
				System.err.println(e.getCadena());
			} catch (NumberFormatException n) {
				System.err.println("Invalid argument for add command, number expected: [A]dd <plant> <x> <y>.\n");
			}
		} while (!this.game.hasGanado() && !this.game.hasPerdido());

		if (this.game.hasGanado()) {
			System.out.println("  PLAYER WINS\n");
			this.game.exit();
		}
		if (this.game.hasPerdido()) {
			System.err.println("  ZOMBIES WIN\n");
			this.game.exit();
		}
	}

	public void printGame() {
		if (noPrintGame) {
			System.out.println("Number of cycles: " + this.game.getCiclo());
			System.out.println("Sun Coins: " + this.game.getSunManager().getCoins());
			System.out.println("Remaining zombies: " + this.game.getzManager().getZombiesRestantes());
			System.out.println(this.game.getGamePrinter().printGame(game));
		}
	}

	/* Metodo setter noPrintGame */
	public void setNoPrintGame(boolean noPrintGame) {
		this.noPrintGame = noPrintGame;
	}

}
