package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReceiptModel {
	private static String path;
	private static ReceiptModel instance;

	public static ReceiptModel getInstance() {
		if (ReceiptModel.instance == null) {
			ReceiptModel.instance = new ReceiptModel();
		}
		return ReceiptModel.instance;
	}

	public void setPath(String path) {
		ReceiptModel.path = path;
	}

	public String generateText() {
		ArrayList<String> receiptArray = new ArrayList<String>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((line = br.readLine()) != null) {
				receiptArray.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = "";
		for (String receiptLine : receiptArray) {
			text += receiptLine + "\n";
		}
		return text;
	}

}
