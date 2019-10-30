package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.util.ExpiredRegister;

public class ExpirationItemController {

    @FXML
    private Label labelCode;
    @FXML
    private Label labelExpiration;
    @FXML
    private Label labelValue;
    
    public void initi(ExpiredRegister register){
    	this.labelCode.setText(register.getCode());
    	this.labelExpiration.setText(register.formattedExpDate());
    	this.labelValue.setText(register.formattedValue());
    }
}
