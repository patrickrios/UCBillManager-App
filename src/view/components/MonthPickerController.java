package view.components;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MonthPickerController implements Initializable {
	@FXML
	private VBox vboxMonthPicker;
    @FXML
    private Button buttonCurrentMonth;
    @FXML
    private AnchorPane anchorMonthList;
    
    @FXML
    private Label labelYear;
    @FXML
    private GridPane gridMonths;
    
    private AnchorPane clone;
    
    private MonthPicker picker;
    
    private boolean calendarControl = true;
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.picker = new MonthPicker();
		this.buttonCurrentMonth.setText(picker.monthAndYear());
		populateCalendar();
		this.clone = this.anchorMonthList;
		setYearName();
		showCalendar();
	}
	
	@FXML
	void showCalendar() {
		this.calendarControl = !this.calendarControl;
		
		if(this.calendarControl) {
			this.vboxMonthPicker.getChildren().add(this.clone);
		}
			
		else {
			this.vboxMonthPicker.getChildren().remove(this.clone);
			this.clone = this.anchorMonthList;
		}	
	}

	@FXML
	void nextYear() {
		this.picker.nextYear();
		setYearName();
	}
	@FXML
	void previousYear() {
		this.picker.previousYear();
		setYearName();
	}
	
	private void setYearName() {
		this.labelYear.setText(""+this.picker.yearNumber());
	}
	
	private void populateCalendar() {
		String[] names = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};
		int m=0,i,j;
		
		for(i=0; i<3; i++) {
			for(j=0; j<4; j++) {
				Button b = new Button(names[m]);
				b.getStyleClass().add("button-month-unselected");
				this.gridMonths.add(b,j,i);
				m++;
			}
		}
	}
}
