package view.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MonthPickerCalendar extends AnchorPane{
	private Label yearValue;
	private Button buttonNext;
	private Button buttonPrev;
	private GridPane calGrid;
	private Button monthsButton[];
	
	public MonthPickerCalendar(int year) {
		this.yearValue = new Label(""+year);
	}
}
