package application;

import java.io.File;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import model.KraftwerkException;
import model.Kraftwerksanlage;

public class RootBorderPane extends BorderPane
{
	private MenuBar menuBar;
	private Menu menuDatei, menuGeneratoren, menuSetRunning;
	private MenuItem miLaden, miSpeichern, miImportieren, miExportieren, miBeenden,
				     miLoeschen, miRunningEin, miRunningAus;
	private Kraftwerksanlage kraftwerksanlage;
	private Uebersicht uebersicht;
	private FlowPane flowPaneBottom;
	private Button btSortLeistung, btSortStandort, btBeenden;
	private RadioButton rbtNennleistung, rbtAktLeistung;
	
	public RootBorderPane()
	{
		initComponents();
		addComponents();
		addHandlers();
		disableComponents(true);
	}

	private void initComponents()
	{
		menuBar = new MenuBar();
		
		menuDatei = new Menu("Datei");
		menuGeneratoren = new Menu("Generatoren");
		menuSetRunning = new Menu("Set running");
		
		miLaden = new MenuItem("Laden");
		miSpeichern = new MenuItem("Speichern");
		miImportieren = new MenuItem("Importieren");
		miExportieren = new MenuItem("Exportieren");
		miBeenden = new MenuItem("Beenden");
		
		miRunningEin = new MenuItem("ein");
		miRunningAus = new MenuItem("aus");
		miLoeschen = new MenuItem("Löschen");
		
		kraftwerksanlage = new Kraftwerksanlage();
		uebersicht = new Uebersicht();
		
		flowPaneBottom = new FlowPane();
		flowPaneBottom.setAlignment(Pos.CENTER);			
		flowPaneBottom.setPadding(new Insets(10));
		flowPaneBottom.setHgap(15);
	
		btSortLeistung = new Button("Sort Leistung");
		btSortStandort = new Button("Sort Standort");
		btBeenden = new Button("Beenden");
		rbtNennleistung = new RadioButton("Nennleistung");
		rbtAktLeistung = new RadioButton("akt.Leistung");
		rbtAktLeistung.setSelected(true);
	}

	private void addComponents() 
	{
		menuBar.getMenus().addAll(menuDatei, menuGeneratoren);
		menuDatei.getItems().addAll(miLaden,miSpeichern,new SeparatorMenuItem(),miImportieren,miExportieren,new SeparatorMenuItem(),miBeenden);
		menuGeneratoren.getItems().addAll(menuSetRunning,miLoeschen);
		menuSetRunning.getItems().addAll(miRunningEin,miRunningAus);
		
		flowPaneBottom.getChildren().addAll(btSortLeistung, rbtNennleistung, rbtAktLeistung, btSortStandort, btBeenden);
		
		setTop(menuBar);
		setCenter(uebersicht);
		setBottom(flowPaneBottom);
	}
	
	private void addHandlers()
	{
		miBeenden.setOnAction(event->beenden());
		btBeenden.setOnAction(event->beenden());
		miLaden.setOnAction(event->laden());
		miSpeichern.setOnAction(event->speichern());
		btSortStandort.setOnAction(event->sortStandort());
		btSortLeistung.setOnAction(event->sortLeistung());
	}
	 
	private void disableComponents(boolean disable)
	{
		uebersicht.setDisable(disable);
		flowPaneBottom.setDisable(disable);

	}
//------------------------------------------------Handler Methoden-----------------------------------------------------------------------------	
	private void laden()
	{
		FileChooser fc = new FileChooser();
		File initialDir = new File("c:\\");
		fc.setInitialDirectory(initialDir);
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null)
		{
			String filename = selectedFile.getAbsolutePath();
			try
			{
				kraftwerksanlage.loadGeneratoren(filename);
				disableComponents(false);
				uebersicht.updateAndShow(kraftwerksanlage.getGeneratoren());
			}
			catch (KraftwerkException e)
			{
				Main.showAlert(AlertType.ERROR, e.getMessage());
			}
		}
		else 
			Main.showAlert(AlertType.INFORMATION, "Keine Auswahl getroffen!");
	}
	
	private void speichern()
	{
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("C:\\"));
		File selectedFile = fc.showSaveDialog(null);
		if (selectedFile != null)
		{
			try
			{
				kraftwerksanlage.saveGeneratoren(selectedFile.getAbsolutePath());
				Main.showAlert(AlertType.INFORMATION, "Daten gespeichert!");
			}
			catch (KraftwerkException e)
			{
				Main.showAlert(AlertType.ERROR, e.getMessage());
			}
		}
		else
			Main.showAlert(AlertType.INFORMATION, "Benutzer-Abbruch");
	}
	
	private void beenden()
	{
		Platform.exit();
	}
//------------------------------------------------sortieren-----------------------------------------------------------------------------	
	
	private void sortLeistung()
	{
		kraftwerksanlage.sortLeistung(true);
		uebersicht.updateAndShow(kraftwerksanlage.getGeneratoren());
	}
	private void sortStandort()
	{
		kraftwerksanlage.sortStandort();
		uebersicht.updateAndShow(kraftwerksanlage.getGeneratoren());
	}

}





