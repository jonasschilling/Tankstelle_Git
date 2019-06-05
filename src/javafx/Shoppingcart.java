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
		incTotal(Float.valueOf(salesModel.readPrice("Wodka")));
	}

	public void removeWodka() {
		numWodka--;
		decTotal(Float.valueOf(salesModel.readPrice("Wodka")));
	}

	public int getNumJupiter() {
		return numJupiter;
	}

	public void addJupiter() {
		numJupiter++;
		incTotal(Float.valueOf(salesModel.readPrice("Jupiter")));
	}

	public void removeJupiter() {
		numJupiter--;
		decTotal(Float.valueOf(salesModel.readPrice("Jupiter")));
	}

	public int getNumFilip() {
		return numFilip;
	}

	public void addFilip() {
		numFilip++;
		incTotal(Float.valueOf(salesModel.readPrice("Filip")));
	}

	public void removeFilip() {
		numFilip--;
		decTotal(Float.valueOf(salesModel.readPrice("Filip")));
	}

	public int getNumBull() {
		return numBull;
	}

	public void addBull() {
		numBull++;
		incTotal(Float.valueOf(salesModel.readPrice("Bull")));
	}

	public void removeBull() {
		numBull--;
		decTotal(Float.valueOf(salesModel.readPrice("Bull")));
	}

	public int getNumPizza() {
		return numPizza;
	}

	public void addPizza() {
		numPizza++;
		incTotal(Float.valueOf(salesModel.readPrice("Pizza")));
	}

	public void removePizza() {
		numPizza--;
		decTotal(Float.valueOf(salesModel.readPrice("Pizza")));
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
