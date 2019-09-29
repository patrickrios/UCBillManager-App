package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import view.util.ConfirmMessageType;

public class ConfirmMessageController {

	@FXML
    private AnchorPane anchorConfirmMessage;
    @FXML
    private Label labelCode;
    @FXML
    private Label labelMessage;
    
    private StackPane stack;
    
    public void inti(String code, String type, StackPane stack)
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
    
    private void defineLabelMessage(String type)
    {
    	if(type.equals(ConfirmMessageType.SUCESS)) {
    		this.labelMessage.setText("registrado com sucesso");
    		this.anchorConfirmMessage.getStyleClass().add("confirm-sucess");
    	}
    	else if(type.equals(ConfirmMessageType.ERROR)){
    		this.labelMessage.setText("já existe. Tente novamento com um código diferente");
    		this.anchorConfirmMessage.getStyleClass().add("confirm-error");
    	}
    	else if(type.equals(ConfirmMessageType.DELETING)){
    		this.labelMessage.setText("apagado do sistema");
    		this.anchorConfirmMessage.getStyleClass().add("confirm-error");
    	}
    }

}