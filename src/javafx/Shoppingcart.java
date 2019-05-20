package javafx;

public class Shoppingcart {
	
	SalesModel salesModel = SalesModel.getInstance();

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
		
		wodka = salesModel.getProduct("Wodka");
		filip = salesModel.getProduct("Filip");
		jupiter = salesModel.getProduct("Jupiter");
		bull = salesModel.getProduct("Bull");
		pizza = salesModel.getProduct("Pizza");
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
