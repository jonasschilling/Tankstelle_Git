package javafx;

public class Shoppingcart {

	private int numWodka;
	private int numJupiter;
	private int numFilip;
	private int numBull;
	private int numPizza;
	private float total;
	Product wodka;
	Product filip;
	Product jupiter;
	Product bull;
	Product pizza;
//Konstruktor
	//test
	public Shoppingcart() {
		wodka = new Product(421, "Wodka Jelzin", "Flasche", 30, 30, 2.99f, 9.99f);
		filip = new Product(871, "Filip Maurice", "Packung", 100, 100, 2.77f, 6.99f);
		jupiter = new Product(358, "Jupiter Schokoriegel", "Stück", 150, 150, 0.19f, 0.90f);
		bull = new Product(992, "Sitting Bull", "Dose", 50, 50, 0.29f, 1.99f);
		pizza = new Product(101, "TK-Pizza Deluxe", "Stück", 20, 20, 0.89f, 2.49f);
		numWodka = 0;	
		numFilip = 0;
		numJupiter = 0;
		numBull = 0;
		numPizza = 0;
		total = 0;
	}

	public int getNumWodka() {
		return numWodka;
	}

	public void addWodka() {
		numWodka++;
		incTotal(wodka.getPriceSell());
	}

	public void removeWodka() {
		numWodka--;
		decTotal(wodka.getPriceSell());
	}

	public int getNumJupiter() {
		return numJupiter;
	}

	public void addJupiter() {
		numJupiter++;
		incTotal(jupiter.getPriceSell());
	}

	public void removeJupiter() {
		numJupiter--;
		decTotal(jupiter.getPriceSell());
	}

	public int getNumFilip() {
		return numFilip;
	}

	public void addFilip() {
		numFilip++;
		incTotal(filip.getPriceSell());
	}

	public void removeFilip() {
		numFilip--;
		decTotal(filip.getPriceSell());
	}

	public int getNumBull() {
		return numBull;
	}

	public void addBull() {
		numBull++;
		incTotal(bull.getPriceSell());
	}

	public void removeBull() {
		numBull--;
		decTotal(bull.getPriceSell());
	}

	public int getNumPizza() {
		return numPizza;
	}

	public void addPizza() {
		numPizza++;
		incTotal(pizza.getPriceSell());
	}

	public void removePizza() {
		numPizza--;
		decTotal(pizza.getPriceSell());
	}

	public float getTotal() {
		return (float) ((float) Math.round(total * 100) / 100.0);
	}

	public void incTotal(float price) {
		this.total += price;
	}

	public void decTotal(float price) {
		this.total -= price;
	}
	public void checkout() {
		wodka.decAmount(numWodka);
		filip.decAmount(numFilip);
		jupiter.decAmount(numJupiter);
		bull.decAmount(numBull);
		pizza.decAmount(numPizza);
		numWodka = 0;
		numFilip = 0;
		numJupiter = 0;
		numBull = 0;
		numPizza = 0;
		total = 0;
		
	}
	public void cancel() {
		numWodka = 0;
		numFilip = 0;
		numJupiter = 0;
		numBull = 0;
		numPizza = 0;
		total = 0;
		
	}
}
