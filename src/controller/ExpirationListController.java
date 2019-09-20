package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class ExpirationListController {

	@FXML
    private AnchorPane anchorTransparent;
	
	@FXML
	public void closeList()
	{
		StackPane stack = (StackPane)this.anchorTransparent.getParent();
		stack.getChildren().remove(this.anchorTransparent);
	}
}
