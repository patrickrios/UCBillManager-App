package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.bean.Persistent;
import model.bean.Register;

public class ListItemGridController {
	
	@FXML
    private Label labelCode;
    @FXML
    private Label labelValue;
    @FXML
    private Label labelCategoryName;
    @FXML
    private Label labelPaid;
    @FXML
    private Label labelExpiration;
    private Register register;
    
    public void initi(Persistent r)
    {
    	this.register = (Register)r;
    	this.labelCode.setText(this.register.getCode());
    	this.labelCategoryName.setText(this.register.getCategoryName());
    	this.labelPaid.setText(this.register.getPaidStatus());
    	this.labelExpiration.setText((this.register.isPaid())?"não":"sim");
    }
}
