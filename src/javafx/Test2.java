package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Test2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		// Eingabeparameter verarbeiten
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root = FXMLLoader.load(getClass().getResource("ColorTestTab.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		primaryStage.setTitle("Shell");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void stop() {
		// Aufräumarbeiten durchführen
	}

}
