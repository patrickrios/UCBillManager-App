package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
    private StackPane stack;
    
    public void initi(Register r, StackPane stack)
    {
    	this.register = r;
    	this.stack = stack;
    	this.labelCode.setText(this.register.getCode());
    	this.labelCategoryName.setText(this.register.getCategoryName());
    	this.labelPaid.setText(this.register.getPaidStatus());
    	this.labelExpiration.setText((this.register.isPaid())?"não":"sim");
    	this.labelValue.setText(this.register.getValueFormatted());
    }
}
