package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SalesModel {
	
	private static SalesModel instance;
	private String newStock;
	
	Product wodka = new Product(421, "Wodka Jelzin", "Flasche", 30, 30, 2.99f);
	Product filip = new Product(871, "Filip Maurice", "Packung", 100, 100, 2.77f);
	Product jupiter = new Product(358, "Jupiter Schokoriegel", "Stück", 150, 150, 0.19f);
	Product bull = new Product(992, "Sitting Bull", "Dose", 50, 50, 0.29f);
	Product pizza = new Product(101, "TK-Pizza Deluxe", "Stück", 20, 20, 0.89f);
	
	public SalesModel() {

	}

	public static SalesModel getInstance() {
		if (SalesModel.instance == null) {
			SalesModel.instance = new SalesModel();
		}
		return SalesModel.instance;
	}
	
	public Product getProduct(String productDescription) {
		if(productDescription.equals("Wodka")) {
			return wodka;
		} else if(productDescription.equals("Filip")) {
			return wodka;
		} else if(productDescription.equals("Jupiter")) {
			return wodka;
		} else if(productDescription.equals("Bull")) {
			return wodka;
		} else if(productDescription.equals("Pizza")) {
			return wodka;
		} else {
			return null;
		}
	}
	
	public void writePrice(String productDescription, String newPrice) {
		File file = new File("src/javafx/resources/Preise/" + productDescription + "Preis.txt");
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
		File file = new File("src/javafx/resources/Preise/" + productDescription + "Preis.txt");
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
	
	public void writeStock(String productDescription, String newStock) {
		File file = new File("src/javafx/resources/Preise/" + productDescription + "Stock.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(newStock);
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
	
	public String readStock(String productDescription) {
		File file = new File("src/javafx/resources/Preise/" + productDescription + "Stock.txt");
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				newStock = line;
			}
		} catch (IOException e) {
		e.printStackTrace();
		}
		return newStock;
	}
	
}
