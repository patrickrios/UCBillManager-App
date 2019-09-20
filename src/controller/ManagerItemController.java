package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ManagerItemController {

	@FXML
    private CheckBox checkboxMarker;

    @FXML
    private Label labelTitle;
    
    public void initi(String name)
    {
    	this.labelTitle.setText(name);
    }
}
