package application;

import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Generator;
import model.Kraftwerksanlage;

public class Uebersicht extends TableView<Generator>
{
	private TableColumn<Generator, String> ortCol;
	private TableColumn<Generator, String> typCol;
	private TableColumn<Generator, Float> nLeisCol;
	private TableColumn<Generator, Float> aLeisCol;

	public Uebersicht()
	{
		initAndAddColumn();
		setFactories();
	}

	@SuppressWarnings("unchecked")
	private void initAndAddColumn()
	{
		ortCol = new TableColumn<>("Standort");
		typCol = new TableColumn<>("Generatortyp");
		nLeisCol = new TableColumn<>("Nennleistung");
		aLeisCol = new TableColumn<>("Aktuelle Leistung");
		
		getColumns().addAll(ortCol,typCol,nLeisCol,aLeisCol);
	}
	
	private void setFactories()
	{
		ortCol.setCellValueFactory(new PropertyValueFactory<>("standort"));
		nLeisCol.setCellValueFactory(new PropertyValueFactory<>("nennLeistung"));
		
		typCol.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getClass().getSimpleName()));
//		aLeisCol.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().berechneLeistung()) );
		
	}
	public void updateAndShow(List<Generator> generatoren)
	{
		getItems().setAll(generatoren);
	}
}
