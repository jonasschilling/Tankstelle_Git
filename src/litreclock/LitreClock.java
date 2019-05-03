package litreclock;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LitreClock extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		Scene scene = new Scene(root, 400, 330);
		scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm());
		primaryStage.setTitle("Tanksimulation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
