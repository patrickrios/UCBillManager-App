package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import view.util.FadeEffect;

public class ManagerItemController {

	@FXML
    private CheckBox checkboxMarker;

    @FXML
    private Label labelTitle;
    
    private StackPane stack;
    
    public void initi(String name, StackPane stack){
    	this.labelTitle.setText(name);
    	this.stack = stack;
    }
    
    @FXML
    void editThisItem() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLEditManagerItem.fxml"));
    	try {
			Parent popup = loader.load();
			EditManagerItemController c = loader.getController();
			c.initi(this.labelTitle.getText(), this.stack);
			new FadeEffect(popup);
			this.stack.getChildren().add(popup);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void deleteThisItem()
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLDeletePopup.fxml"));
    	
    	try {
			Parent delete = loader.load();
			DeleteItemController c = loader.getController();
			c.initi(this.labelTitle.getText(), this.stack);
			new FadeEffect(delete);
			this.stack.getChildren().add(delete);
		} 
    	
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
}
