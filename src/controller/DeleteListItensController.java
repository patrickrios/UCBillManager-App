package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.entity.Persistent;
import model.entity.Register;
import model.util.DisposableList;
import view.util.FullSizeOnStack;

public class DeleteListItensController {
	@FXML
    private AnchorPane anchorDeleteList;
    @FXML
    private Label labelTotalItens;
    @FXML
    private VBox vboxItens;
    @FXML
    private Button deleteAllItens;
    @FXML
    private HBox hboxConfirm;
    @FXML
    private Label labelTotalAfterDeleting;
    
    private ArrayList<Persistent> list;
    
    private StackPane stack;
    
    private int total;
    
    private DisposableList dList;
    
    private ArrayList<DeleteListItemController> controllers = new ArrayList<>();
    
    public void init(ArrayList<Persistent> list, StackPane stack, DisposableList disList) {
    	this.list = list;
    	this.stack = stack;
    	this.dList = disList;
    	this.total = list.size();
    	this.labelTotalItens.setText("("+this.total+")");
    	loadList();
    }
    
    @FXML
    void cancelDeleting() {
    	this.stack.getChildren().remove(this.anchorDeleteList);
    }

    @FXML
    void deleteAll() {
    	int count = 0;
    	for(DeleteListItemController c : this.controllers) {
    		count += c.deleteThisItem();
    	}
    	this.deleteAllItens.setDisable(true);
    	this.labelTotalAfterDeleting.setText(count+" itens apagados.");
    	this.hboxConfirm.setVisible(true);
    	this.dList.updateListAfterDeleting();
    }
    
    private void loadList() {
    	
    	for(Persistent p : this.list) {
    		Register r = (Register)p;
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLDeleteListItem.fxml"));
    		try {
				Parent item = loader.load();
				DeleteListItemController c = loader.getController();
				c.init(r);
				this.vboxItens.getChildren().add(item);
				this.controllers.add(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    void fullSizeConteiner() {
    	FullSizeOnStack.extend(this.anchorDeleteList);
    }

}
