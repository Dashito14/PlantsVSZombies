package tp.p3.factories;

import tp.p3.objects.Caracubo;
import tp.p3.objects.Deportista;
import tp.p3.objects.Zombie;
import tp.p3.objects.ZombieComun;

public class ZombieFactory {

	public static Zombie[] availableZombies = {
			new ZombieComun(), new Caracubo(), new Deportista(),
	};
	
	public static Zombie addZombieToGame(int zombie) {
		switch (zombie) {
		case 0: return new ZombieComun(); 
		case 1: return new Caracubo(); 
		case 2: return new Deportista(); 

		default: return null;
		}
	}
	
	/** listOfAvailableZombies: devuelve una cadena listando la informacion pedida por
	 * el comando zombieListCommand sobre los zombies existentes. */
	public static String listOfAvailableZombies() {
		StringBuilder sb = new StringBuilder();
		for (Zombie zombie : availableZombies) {
			sb.append(zombie.helpText() + "\n");
		}
		return sb.toString();
	}
}
