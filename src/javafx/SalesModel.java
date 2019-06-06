package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class SalesModel {

	private static SalesModel instance;
	private float totalPrice;
	private static String path = "src/javafx/resources/Preise/";
	private int amountWodka = 30;
	private int amountFilip = 100;
	private int amountJupiter = 150;
	private int amountBull = 50;
	private int amountPizza = 20;
	private float dieselPriceBuy = 0.82f, superPriceBuy = 1.04f;
	private FinancesController financesController;
	private float balance;

	Product wodka = new Product(421, "Wodka Jelzin", "Flasche", amountWodka, 30, 2.99f);
	Product filip = new Product(871, "Filip Maurice", "Packung", amountFilip, 100, 2.77f);
	Product jupiter = new Product(358, "Jupiter Schokoriegel", "Stück", amountJupiter, 150, 0.19f);
	Product bull = new Product(992, "Sitting Bull", "Dose", amountBull, 50, 0.29f);
	Product pizza = new Product(101, "TK-Pizza Deluxe", "Stück", amountPizza, 20, 0.89f);
	ArrayList<Product> products = new ArrayList<>();

	String orderNumber = null, deliveryNoteNumber = null;

	public SalesModel() {

	}

	public static SalesModel getInstance() {
		if (SalesModel.instance == null) {
			SalesModel.instance = new SalesModel();
		}
		return SalesModel.instance;
	}

	public void addProducts() {

		products.add(wodka);
		products.add(filip);
		products.add(jupiter);
		products.add(bull);
		products.add(pizza);

	}

	public Product getProduct(String productDescription) {

		if (productDescription.equals("Wodka")) {
			return wodka;
		} else if (productDescription.equals("Filip")) {
			return filip;
		} else if (productDescription.equals("Jupiter")) {
			return jupiter;
		} else if (productDescription.equals("Bull")) {
			return bull;
		} else if (productDescription.equals("Pizza")) {
			return pizza;
		} else {
			return null;
		}
	}

	public float getDieselPriceBuy() {
		return dieselPriceBuy;
	}

	public void setDieselPriceBuy(float dieselPriceBuy) {
		this.dieselPriceBuy = dieselPriceBuy;
	}

	public float getSuperPriceBuy() {
		return superPriceBuy;
	}

	public void setSuperPriceBuy(float superPriceBuy) {
		this.superPriceBuy = superPriceBuy;
	}

	public void writePrice(String productDescription, String newPrice) {
		File file = new File(path + productDescription + "Preis.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(newPrice);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String readPrice(String productDescription) {
		File file = new File(path + productDescription + "Preis.txt");
		String newPrice = null;
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				newPrice = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newPrice;
	}

	public void writeStock() {

		File file = new File("src/javafx/resources/Stock.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(balance));
			bw.newLine();
			for (Product p : products) {
				bw.write(String.valueOf(p.getAmount()));
				bw.write(";");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readStock() {

		File file = new File("src/javafx/resources/Stock.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			balance = Float.valueOf(br.readLine());
			String line;
			while ((line = br.readLine()) != null) {

				String[] output = line.split(";");
				wodka.setAmount(Integer.parseInt(output[0]));
				filip.setAmount(Integer.parseInt(output[1]));
				jupiter.setAmount(Integer.parseInt(output[2]));
				bull.setAmount(Integer.parseInt(output[3]));
				pizza.setAmount(Integer.parseInt(output[4]));
			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void setTotalPrice(float f) {
		this.totalPrice = f;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void addToTotal(float totalPricePerProduct) {
		totalPrice += totalPricePerProduct;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public String readNoOrders(String kindOfOrder) {
		File file = null;
		if (kindOfOrder.equals("Gas")) {
			file = new File("src/javafx/resources/Orders/no" + kindOfOrder + "Orders.txt");
		} else if (kindOfOrder.equals("Products")) {
			file = new File("src/javafx/resources/Orders/no" + kindOfOrder + "Orders.txt");
		}
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String number;
			while ((number = br.readLine()) != null) {
				orderNumber = number;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orderNumber;
	}

	public void writeNoDeliveryNote(String kindOfOrder) {
		File file = null;
		if (kindOfOrder.equals("Gas")) {
			file = new File("src/javafx/resources/Deliveries/no" + kindOfOrder + "DeliveryNotes.txt");
		} else if (kindOfOrder.equals("Products")) {
			file = new File("src/javafx/resources/Deliveries/no" + kindOfOrder + "Deliverynotes.txt");
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		int orderNumberPlus = 0;
		if (kindOfOrder.equals("Gas")) {
			orderNumberPlus = Integer.valueOf(readNoOrders("Gas")) + 1;
		} else if (kindOfOrder.equals("Products")) {
			orderNumberPlus = Integer.valueOf(readNoOrders("Products")) + 1;
		}
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(orderNumberPlus));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String readNoDeliveryNote(String kindOfOrder) {
		File file = null;
		if (kindOfOrder.equals("Gas")) {
			file = new File("src/javafx/resources/Deliveries/no" + kindOfOrder + "DeliveryNotes.txt");
		} else if (kindOfOrder.equals("Products")) {
			file = new File("src/javafx/resources/Deliveries/no" + kindOfOrder + "DeliveryNotes.txt");
		}
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String number;
			while ((number = br.readLine()) != null) {
				deliveryNoteNumber = number;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deliveryNoteNumber;
	}

	public void writeNoOrders(String kindOfOrder) {
		File file = null;
		if (kindOfOrder.equals("Gas")) {
			file = new File("src/javafx/resources/Orders/no" + kindOfOrder + "Orders.txt");
		} else if (kindOfOrder.equals("Products")) {
			file = new File("src/javafx/resources/Orders/no" + kindOfOrder + "Orders.txt");
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		int orderNumberPlus = 0;
		if (kindOfOrder.equals("Gas")) {
			orderNumberPlus = Integer.valueOf(readNoOrders("Gas")) + 1;
		} else if (kindOfOrder.equals("Products")) {
			orderNumberPlus = Integer.valueOf(readNoOrders("Products")) + 1;
		}
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(orderNumberPlus));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeGasOrder(String superAmount, String dieselAmount) {
		File file = new File("src/javafx/resources/Orders/GasOrder" + readNoOrders("Gas") + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("BESTELLDATUM=" + printSimpleDateFormat() + "\nDIESEL=" + dieselAmount + "\nSUPER=" + superAmount
					+ ";");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeProductOrder(ArrayList<Product> products) {
		File file = new File("src/javafx/resources/Orders/ProductOrder" + readNoOrders("Products") + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("BESTELLDATUM=" + printSimpleDateFormat() + "\nWarennummer;Bestellmenge");
			for (Product product : products) {
				bw.write("\n" + String.valueOf(product.getProdNumber() + ";"
						+ String.valueOf(product.getMaxAmount() - product.getAmount()) + ";"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeProductDeliveryNote(ArrayList<Product> products) {
		File file = new File("src/javafx/resources/Deliveries/ProductDeliveryNote" + readNoOrders("Products") + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("LIEFERDATUM=" + printSimpleDateFormat()
					+ "\nWarennummer;Bezeichnung;Lagereinheit;Menge;Einkaufspreis");
			for (Product product : products) {
				bw.write("\n" + String.valueOf(product.getProdNumber() + ";" + product.getName() + ";"
						+ product.getType() + ";" + String.valueOf(product.getMaxAmount() - product.getAmount()) + ";"
						+ String.valueOf(product.getPriceBuy())));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeGasDeliveryNote(String superAmount, String dieselAmount) {
		File file = new File("src/javafx/resources/Deliveries/GasDeliveryNote" + readNoOrders("Gas") + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("LIEFERDATUM=" + printSimpleDateFormat() + "\nDIESEL=" + dieselAmount + "\nDIESEL_PREIS="
					+ getDieselPriceBuy() + "\nSUPER=" + superAmount + "\nSUPER_PREIS=" + getSuperPriceBuy());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String printSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date currentTime = new Date();
		return (formatter.format(currentTime));
	}

	public FinancesController getFinancesController() {
		return financesController;
	}

	public void setFinancesController(FinancesController financesController) {
		this.financesController = financesController;
	}

	public float getBalance() {
		return Math.round(balance*100) /100f;
	}

	public void incBalance(float balance) {
		this.balance += balance;
	}

	public void decBalance(float balance) {
		this.balance -= balance;
	}

}
