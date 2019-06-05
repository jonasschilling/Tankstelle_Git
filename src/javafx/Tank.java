package javafx;

public class Tank {

	private String description;
	private float capacity;
	private float fuelLevel;
	private float pricePerLitre;
	
	public float getPricePerLitre() {
		return pricePerLitre;
	}

	public void setPricePerLitre(float pricePerLitre) {
		this.pricePerLitre = pricePerLitre;
	}

	public Tank(String description, float capacity) {
		this.description = description;
		this.capacity = capacity;
		fuelLevel = capacity;
	}

	public String getDescription() {
		return description;
	}

	public float getCapacity() {
		return capacity;
	}

	public void decreaseFuelLevel(float litresRefueled) {
		fuelLevel -= litresRefueled;
	}

	public void increaseFuelLevel(float litresOrdered) {
		fuelLevel += litresOrdered;
	}

	public float getAbsFuelLevel() {
		return fuelLevel;
	}

	public float getRelFuelLevel() {
		return fuelLevel / capacity;
	}

}
