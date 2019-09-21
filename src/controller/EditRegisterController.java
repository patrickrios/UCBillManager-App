package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class EditRegisterController {

	@FXML
    private AnchorPane anchorpaneEdit;
    @FXML
    private Button buttonPaid;
    
	private StackPane stack;
	
	public void initi(StackPane stack)
	{
		this.stack = stack;
	}
	
	@FXML
	void closeEdit(){
		this.stack.getChildren().remove(this.anchorpaneEdit);
	}
	
	@FXML
    void setPaid() {

    }
}
