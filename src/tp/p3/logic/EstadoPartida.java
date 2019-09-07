package tp.p3.logic;

import tp.p3.objects.GameObject;

public class EstadoPartida {
	int ciclos;
	int soles;
	int zombiesRestantes;
	GameObject[] zombieList;
	GameObject[] plantList;
	
		public EstadoPartida(int ciclos, int soles, int zombiesRestantes, GameObject[] zombieList, GameObject[] plantList) {
			this.ciclos = ciclos;
			this.soles = soles;
			this.zombiesRestantes = zombiesRestantes;
			this.zombieList = zombieList;
			this.plantList = plantList;
		}

		public int getCiclos() {
			return ciclos;
		}

		public void setCiclos(int ciclos) {
			this.ciclos = ciclos;
		}

		public int getSoles() {
			return soles;
		}

		public void setSoles(int soles) {
			this.soles = soles;
		}

		public int getZombiesRestantes() {
			return zombiesRestantes;
		}

		public void setZombiesRestantes(int zombiesRestantes) {
			this.zombiesRestantes = zombiesRestantes;
		}

		public GameObject[] getZombieList() {
			return zombieList;
		}

		public void setZombieList(GameObject[] zombieList) {
			this.zombieList = zombieList;
		}

		public GameObject[] getPlantList() {
			return plantList;
		}

		public void setPlantList(GameObject[] plantList) {
			this.plantList = plantList;
		}
		
	
}
