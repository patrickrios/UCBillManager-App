package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class ViewDetailController {

	@FXML
    private StackPane stackpaneViewDetail;
	
	private StackPane stack;
	
	public void initi(StackPane stack){
		this.stack = stack;
	}
	
	@FXML
	void exitDetails(){
		this.stack.getChildren().remove(this.stackpaneViewDetail);
	}
}
