package tanken_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Printer {
//    
//	public void printFile() { 
//        
//		FileWriter pWriter = new FileWriter("C:\\Users\\d074024\\Desktop\\printertest\\test.txt");
	
	public int i = 5;

	public void print() {

		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(
					new BufferedWriter(new FileWriter("C:\\Users\\d074024\\Desktop\\printertest\\Beleg" + i + ".txt")));
			pWriter.println("Kraftstoffbestellung:");
			pWriter.println("Kraftstoffart \t Menge \t \t Preis pro Liter");
			pWriter.println("Super \t \t 8000 l \t 0,94 €");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
	}
}