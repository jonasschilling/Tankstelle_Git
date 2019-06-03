
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
	Product wodka = new Product(421, "Wodka Jelzin", "Flasche", 30, 30, 2.99f);
	Product filip = new Product(871, "Filip Maurice", "Packung", 100, 100, 2.77f);
	Product jupiter = new Product(358, "Jupiter Schokoriegel", "Stück", 150, 150, 0.19f);
	Product bull = new Product(992, "Sitting Bull", "Dose", 50, 50, 0.29f);
	Product pizza = new Product(101, "TK-Pizza Deluxe", "Stück", 20, 20, 0.89f);
	ArrayList<Product> products = new ArrayList<>();
//	Shoppingcart shoppingCart = new Shoppingcart();

	public SalesModel() {

	}
//            Shoppingcart shoppingCart = new Shoppingcart();

//            public Shoppingcart getShoppingcart() {
//                           return shoppingCart;
//            }
//
//            public void setScart(Shoppingcart shoppingCart) {
//                           this.shoppingCart = shoppingCart;
//            }

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
				bw.write(p.getAttributes());
				bw.newLine();
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
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] output = line.split(";");
				products.add(new Product(Integer.parseInt(output[0]), output[1], output[2], Integer.parseInt(output[3]),
						Integer.parseInt(output[4]), Float.parseFloat(output[5])));
			}
		} catch (IOException e) {
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

//            public String getProduct(Product p) {
//                           
//                           return p.getAttributes();
//                           
//            }

}
