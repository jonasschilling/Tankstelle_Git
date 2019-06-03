package javafx;

import java.io.File;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root = FXMLLoader.load(getClass().getResource("AdministrationView.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		primaryStage.setTitle("Tankstelle");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				SalesModel.writeStock();

			}
		});

	}

	public void stop() {
<<<<<<< HEAD
		// AufrÃ¤umarbeiten durchfï¿½hren
=======
		// Aufräumarbeiten durchführen
>>>>>>> branch 'master' of https://github.com/jonasschilling/Tankstelle_Git.git
	}

}
