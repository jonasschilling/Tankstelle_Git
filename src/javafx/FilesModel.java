package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilesModel {

	static FilesModel instance;
	
	private float balance;
	

	public FilesModel() {

	}

	public static FilesModel getInstance() {
		if (FilesModel.instance == null) {
			FilesModel.instance = new FilesModel();
		}
		return FilesModel.instance;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
		
	}
	public float getBalance() {
		return balance;
	}

}
