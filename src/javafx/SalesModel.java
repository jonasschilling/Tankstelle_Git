package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalesModel {

	private static SalesModel instance;
	private float totalPrice;
	private static String path = "src/javafx/resources/Preise/";
	private int amountWodka = 30;
	private int amountFilip = 100;
	private int amountJupiter = 150;
	private int amountBull = 50;
	private int amountPizza = 20;
	Product wodka = new Product(421, "Wodka Jelzin", "Flasche", amountWodka, 30, 2.99f);
	Product filip = new Product(871, "Filip Maurice", "Packung", amountFilip, 100, 2.77f);
	Product jupiter = new Product(358, "Jupiter Schokoriegel", "Stück", amountJupiter, 150, 0.19f);
	Product bull = new Product(992, "Sitting Bull", "Dose", amountBull, 50, 0.29f);
	Product pizza = new Product(101, "TK-Pizza Deluxe", "Stück", amountPizza, 20, 0.89f);
	ArrayList<Product> products = new ArrayList<>();

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

}
