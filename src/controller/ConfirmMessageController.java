package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ConfirmMessageController {

	@FXML
    private AnchorPane anchorConfirmMessage;
    @FXML
    private Label labelCode;
    @FXML
    private Label labelMessage;
    
    private StackPane stack;
    
    public void inti(String code, int type, StackPane stack)
    {
    	this.labelCode.setText(code);
    	this.stack = stack;
    	this.defineLabelMessage(type);
    	stack.getChildren().add(this.anchorConfirmMessage);
    }
    
    @FXML
    void closeMessage() {
    	this.stack.getChildren().remove(this.anchorConfirmMessage);
    }
    
    private void defineLabelMessage(int type)
    {
    	if(type == 1) {
    		this.labelMessage.setText("registrado com sucesso");
    		this.anchorConfirmMessage.getStyleClass().add("confirm-sucess");
    	}
    	else if(type==2)
    	{
    		this.labelMessage.setText("já existe. Tente novamento com um código diferente");
    		this.anchorConfirmMessage.getStyleClass().add("confirm-error");
    	}
    }

}