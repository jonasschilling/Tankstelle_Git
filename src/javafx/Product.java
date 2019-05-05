package javafx;

public class Product {

	private String name;
	private float priceBuy;
	private float priceSell;
	private int prodNumber;
	private String type;
	private int amount;
	private int maxAmount;

	public Product(int prodNumber, String name, String type, int amount, int maxAmount, float priceBuy, float priceSell) {
		this.name = name;
		this.priceBuy = priceBuy;
		this.priceSell = priceSell;
		this.prodNumber = prodNumber;
		this.type = type;
		this.amount = amount;
		this.maxAmount = maxAmount;

	}

	public String getName() {
		return name;
	}

	public float getPriceBuy() {
		return priceBuy;
	}

	public void setPriceBuy(float priceBuy) {
		this.priceBuy = priceBuy;
	}

	public float getPriceSell() {
		return priceSell;
	}

	public void setPriceSell(float priceSell) {
		this.priceSell = priceSell;
	}

	public int getProdNumber() {
		return prodNumber;
	}

	public String getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void incAmount(int amount) {
		this.amount += amount;
	}

	public void decAmount() {
		this.amount -= amount;
	}

}