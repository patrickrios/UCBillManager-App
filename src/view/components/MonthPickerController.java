package view.components;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MonthPickerController implements Initializable {
    @FXML
    private Button buttonCurrentMonth;
    @FXML
    private AnchorPane anchorMonthList;
    
    private MonthPicker picker;
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.picker = new MonthPicker();
		this.buttonCurrentMonth.setText(picker.monthAndYear());
		
	}

}
