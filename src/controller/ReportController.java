package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import view.monthpicker.MonthPicker;

public class ReportController implements Initializable{
    @FXML
    private Label labelGenQuant;
    @FXML
    private Label labelExpenseQuant;
    @FXML
    private Label labelRevenueQuant;
    @FXML
    private Label labelGenTotal;
    @FXML
    private Label labelExpenseTotal;
    @FXML
    private Label labelRevenueTotal;
    @FXML
    private Pane paneMonthFilter;
    
    private MonthPicker monthPicker;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.monthPicker = new MonthPicker();
		this.paneMonthFilter.getChildren().add(this.monthPicker);
		
	}
}