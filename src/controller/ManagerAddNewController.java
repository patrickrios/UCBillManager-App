package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.dao.PersistentBean;
import view.util.ConfirmMessageType;
import view.util.FullSizeOnStack;

public class ManagerAddNewController {
	@FXML
	private AnchorPane anchorAddNewManager;
    @FXML
    private TextField textfieldInput;
    private PersistentBean bean;
    private StackPane stack;
    private ManagerController controller;
    
    public void inti(PersistentBean manager, StackPane stack, ManagerController controller) {
    	this.bean = manager;
    	this.stack = stack;
    	this.controller = controller;
    }

    @FXML
    void closeAddLayout() {
    	this.stack.getChildren().remove(this.anchorAddNewManager);
    }

    @FXML
    void createNew() {
    	String input = textfieldInput.getText();
    	this.bean.createNew(input);
    	showMessage(input);
		controller.loadItens();
		closeAddLayout();
    }
    
    private void showMessage(String input) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLConfirmMessage.fxml"));
    	try {
			loader.load();
			ConfirmMessageController c = loader.getController();
			c.inti(input, ConfirmMessageType.SUCESS, this.stack);
		}
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void fullSizeConteiner() {
    	FullSizeOnStack.extend(this.anchorAddNewManager);
    }
}