package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DeleteItemController {

	@FXML
    private Pane paneDelete;
	@FXML
    private Label labelCode;
	
	private StackPane stack;
	
	public void initi(String code, StackPane stack)
	{
		this.labelCode.setText(code);
		this.stack = stack;
	}
	
	@FXML
	void cancelDeleting()
	{
		this.stack.getChildren().remove(this.paneDelete);
	}
}
