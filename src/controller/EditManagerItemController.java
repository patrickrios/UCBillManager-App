package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.bean.Persistent;

public class EditManagerItemController {

	@FXML
    private Pane paneEditManegerItem;
	@FXML
    private Label labelItemName;
    @FXML
    private TextField textfieldItemName;
    
    private StackPane stack;
    
    private Persistent persistent;

	public void initi(Persistent p, StackPane stack){
		this.stack = stack;
		this.labelItemName.setText(p.toString());
		this.textfieldItemName.setText(p.toString());
		this.persistent = p;
	}

    @FXML
    void cancelEditing() {
    	this.stack.getChildren().remove(this.paneEditManegerItem);
    }

    @FXML
    void updateItem() {
    	String newName = this.textfieldItemName.getText();
    	this.persistent.updateIdentify(newName);
    }
    
}
