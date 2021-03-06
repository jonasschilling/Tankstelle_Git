package refillSimulation;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Jonas Schilling
 *
 */
public class SimulationStart extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SimulationView.fxml"));
		Scene scene = new Scene(root, 400, 570);
		scene.getStylesheets().add(getClass().getResource("SimulationStylesheet.css").toExternalForm());
		primaryStage.setTitle("Tanksimulation");
		primaryStage.getIcons().add(new Image("javafx/resources/gasPump.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
