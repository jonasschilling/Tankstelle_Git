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

/**
 * @author Alissa Templin
 *
 */
public class Start extends Application {

	SalesModel salesModel = SalesModel.getInstance();
	EmployeeModel employeeModel = EmployeeModel.getInstance();
	ReceiptModel receiptModel = ReceiptModel.getInstance();
	
	public static void main(String[] args) {
		launch(args);
	}

	public void init() {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		receiptModel.readNoReceipts();
		salesModel.readStock();
		salesModel.addProducts();
		employeeModel.readEmployees();
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toExternalForm());
		primaryStage.setTitle("Tankstelle");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				receiptModel.writeNoReceipts();
				salesModel.writeStock();
				employeeModel.writeEmployees();

			}
		});

	}

	public void stop() {

		// Aufräumarbeiten durchführen

	}

}
