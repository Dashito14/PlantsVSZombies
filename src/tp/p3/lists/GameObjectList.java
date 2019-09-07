package tp.p3.lists;

import tp.p3.objects.GameObject;

public class GameObjectList {

	private GameObject[] zombieList;
	private GameObject[] plantList;
	private int sizeZ;
	private int sizeP;
	private int capacityZ;
	private int capacityP;
	public final int MAX = 5;

	public int getCapacityZ() {
		return capacityZ;
	}

	public void setCapacityZ(int capacityZ) {
		this.capacityZ = capacityZ;
	}

	public int getCapacityP() {
		return capacityP;
	}

	public void setCapacityP(int capacityP) {
		this.capacityP = capacityP;
	}

	/** Constructora */
	public GameObjectList() {
		this.zombieList = new GameObject[MAX];
		this.plantList = new GameObject[MAX];
		this.sizeZ = 0;
		this.sizeP = 0;
		this.capacityZ = MAX;
		this.capacityP = MAX;
	}

	/** addZombieToList: metodo que añade un zombie cualquiera a la lista */
	public void addZombieToList(GameObject go) {
		if (this.sizeZ == this.capacityZ) {
			this.zombieList = resizeZombieList();
		}
		this.zombieList[this.sizeZ] = go;
		this.sizeZ++;
	}

	/** addPlantToList: metodo que añade una planta cualquiera a la lista */
	public void addPlantToList(GameObject go) {
		if (this.sizeP == this.capacityP) {
			this.plantList = resizePlantList();
		}
		this.plantList[this.sizeP] = go;
		this.sizeP++;
	}

	/** deleteZombieFromList: metodo que borra un zombie de la lista */
	public void deleteZombieFromList(int pos) {
		for (int i = pos; i < this.sizeZ - 1; i++) {
			this.zombieList[i] = this.zombieList[i + 1];
		}
		this.sizeZ--;
	}

	/** deletePlantList: metodo que borra una planta de la lista */
	public void deletePlantFromList(int pos) {
		for (int i = pos; i < this.sizeP - 1; i++) {
			this.plantList[i] = this.plantList[i + 1];
		}
		this.sizeP--;
	}

	/** resizePlantList: redimensionamos el array de planta cuando esté lleno */
	private GameObject[] resizePlantList() {
		GameObject aux[] = new GameObject[2 * this.capacityP];
		for (int i = 0; i < this.getSizeP(); i++) {
			aux[i] = this.plantList[i];
		}
		this.capacityP *= 2;
		return aux;

	}
	
	/** resizeZombieList: redimensionamos el array de zombie cuando esté lleno */
	private GameObject[] resizeZombieList() {
		GameObject aux[] = new GameObject[2 * this.capacityZ];
		for (int i = 0; i < this.getSizeZ(); i++) {
			aux[i] = this.zombieList[i];
		}
		this.capacityZ *= 2;
		return aux;

	}

	/* Métodos getters y setters de la clase GameObjectList */

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

	public int getSizeZ() {
		return sizeZ;
	}

	public void setSizeZ(int sizeZ) {
		this.sizeZ = sizeZ;
	}

	public int getSizeP() {
		return sizeP;
	}

	public void setSizeP(int sizeP) {
		this.sizeP = sizeP;
	}

}
