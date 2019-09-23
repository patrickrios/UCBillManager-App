package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import view.util.FadeEffect;

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
	
	@FXML
	void deleteThisRegister()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLDeletePopup.fxml"));
		
		try {
			Parent popup = loader.load();
			DeleteItemController c = loader.getController();
			c.initi("$code", this.stack);
			new FadeEffect(popup);
			this.stack.getChildren().add(popup);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void editThisRegister()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLEditRegister.fxml"));
		
		try 
		{
			Parent edit = loader.load();
			EditRegisterController c = loader.getController();
			c.initi(this.stack);
			new FadeEffect(edit);
			this.stack.getChildren().add(edit);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
