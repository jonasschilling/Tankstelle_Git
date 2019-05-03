package javafx_test2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tankverwaltung extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		// Eingabeparameter verarbeiten
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Tankverwaltung.fxml"));
		Scene scene = new Scene(root);
//		scene.getStylesheets().add(
//				getClass().getResource("Stylesheet.css").toExternalForm());
		primaryStage.setTitle("Shell");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void stop() {
		// Aufräumarbeiten durchführen
	}

}
