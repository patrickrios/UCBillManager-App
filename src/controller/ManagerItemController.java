package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.bean.Persistent;
import view.util.FadeEffect;

public class ManagerItemController {

	@FXML
    private CheckBox checkboxMarker;

    @FXML
    private Label labelTitle;
    
    private StackPane stack;
    
    private Persistent persistent;
    
    public void initi(Persistent p, StackPane stack){
    	this.labelTitle.setText(p.toString());
    	this.stack = stack;
    	this.persistent = p;
    }
    
    @FXML
    void editThisItem() {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLEditManagerItem.fxml"));
    	try {
			Parent popup = loader.load();
			EditManagerItemController c = loader.getController();
			c.initi(this.persistent, this.stack);
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
			c.initi(this.persistent, this.stack);
			new FadeEffect(delete);
			this.stack.getChildren().add(delete);
		} 
    	
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
}
