package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class EditManagerItemController {

	@FXML
    private Pane paneEditManegerItem;
	@FXML
    private Label labelItemName;
    @FXML
    private TextField textfieldItemName;
    
    private StackPane stack;

	public void initi(String name, StackPane stack)
	{
		this.stack = stack;
		this.labelItemName.setText(name);
		this.textfieldItemName.setText(name);
	}

    @FXML
    void cancelEditing() {
    	this.stack.getChildren().remove(this.paneEditManegerItem);
    }

    @FXML
    void updateItem() {

    }
    
}
