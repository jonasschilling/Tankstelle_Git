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
    private static String path = "src/javafx/resources/Preise/";
	private String newStock;
	private float totalPrice;
	
	static Product wodka = new Product(421, "Wodka Jelzin", "Flasche", 30, 30, 2.99f);
	static Product filip = new Product(871, "Filip Maurice", "Packung", 100, 100, 2.77f);
	static Product jupiter = new Product(358, "Jupiter Schokoriegel", "Stück", 150, 150, 0.19f);
	static Product bull = new Product(992, "Sitting Bull", "Dose", 50, 50, 0.29f);
	static Product pizza = new Product(101, "TK-Pizza Deluxe", "Stück", 20, 20, 0.89f);
	
	static ArrayList<Product> products = new ArrayList<>();

//	Shoppingcart shoppingCart = new Shoppingcart();
	
	public SalesModel() {
		
	}
	
//	public Shoppingcart getShoppingcart() {
//		return shoppingCart;
//	}
//
//	public void setScart(Shoppingcart shoppingCart) {
//		this.shoppingCart = shoppingCart;
//	}

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
			return filip;
		} else if(productDescription.equals("Jupiter")) {
			return jupiter;
		} else if(productDescription.equals("Bull")) {
			return bull;
		} else if(productDescription.equals("Pizza")) {
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
	
	public static void writeStock() {
		products.add(wodka);
		products.add(filip);
		products.add(jupiter);
		products.add(bull);
		products.add(pizza);
		
		File file = new File(System.getProperty("user.dir") + "\\Stock.txt\\");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("Nummer;Name;Lagereinheit;Menge;Einkaufspreis;Verkaufspreis");
			bw.newLine();
			for(Product p : products) {
				bw.write(p.getAttributes());
				bw.newLine();
				}
		
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Stage is closing");
		}
//		System.out.println("Stage is closing");

	}
	
//	public void writeStock(String productDescription, String newStock) {
//		File file = new File(path + productDescription + "Stock.txt");
//		FileWriter fw = null;
//		BufferedWriter bw = null;
//
//		try {
//			fw = new FileWriter(file);
//			bw = new BufferedWriter(fw);
//			bw.write(newStock);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (bw != null) {
//				try {
//					bw.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	public String readStock(String productDescription) {
		File file = new File(path + productDescription + "Stock.txt");
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
	
	public void setTotalPrice(float f) {
		this.totalPrice = f;
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	public void addToTotal(float totalPricePerProduct) {
		totalPrice += totalPricePerProduct;
	}
	
//	public String getProduct(Product p) {
//		
//		return p.getAttributes();
//		
//	}

}
