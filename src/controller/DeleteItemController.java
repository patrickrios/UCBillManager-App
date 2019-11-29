package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.entity.Persistent;
import view.util.ConfirmMessageType;
import view.util.FullSizeOnStack;

public class DeleteItemController {

	@FXML
    private Pane paneDelete;
	@FXML
    private Label labelCode;
	
	private StackPane stack;
	
	private Persistent persistent;
	
	public void initi(Persistent p, StackPane stack){
		this.labelCode.setText(p.toString());
		this.stack = stack;
		this.persistent = p;
	}
	
	@FXML
	void cancelDeleting(){
		this.stack.getChildren().remove(this.paneDelete);
	}
	
	@FXML
	void deleteItem(){
		this.persistent.deleteThis();
		showMessage();
		cancelDeleting();
	}
	
	private void showMessage(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLConfirmMessage.fxml"));
		
		try {
			loader.load();
			ConfirmMessageController c = loader.getController();
			c.inti(this.persistent.toString(), ConfirmMessageType.DELETING, this.stack);
			c.fullConteinerSize();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void fullSizeConteiner() {
		FullSizeOnStack.extend(this.paneDelete);
	}
}
