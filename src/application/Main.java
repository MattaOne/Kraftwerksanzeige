package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root = new RootBorderPane();
			Scene scene = new Scene(root, 500, 200);
			primaryStage.setTitle("Kraftwerks-Verwaltung");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (Exception e)
		{
			showAlert(AlertType.ERROR, e.getMessage());
		}
	}
	
	public static void showAlert(AlertType alertType, String message)
	{
		Alert alert = new Alert(alertType, message, ButtonType.OK);
		alert.setHeaderText(null);
		alert.setTitle("Achtung!!");
		alert.showAndWait();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}
