package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.entity.Persistent;
import model.entity.Register;

public class DeleteListItensController {
	@FXML
    private AnchorPane anchorDeleteList;
    @FXML
    private Label labelTotalItens;
    @FXML
    private Label labelDeletingStatus;
    @FXML
    private VBox vboxItens;
    @FXML
    private Button deleteAllItens;
    @FXML
    private Button closeDeleting;
    
    private ArrayList<Persistent> list;
    
    private StackPane stack;
    
    int total;
    
    public void init(ArrayList<Persistent> list, StackPane stack) {
    	this.list = list;
    	this.stack = stack;
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
    	int i = 1;
    	
    	for(Persistent p : this.list) {
    		this.labelDeletingStatus.setText("Apagando "+i+" de "+this.total);
    		//p.deleteThis();
    	}

    }
    
    private void loadList() {
    	
    	for(Persistent p : this.list) {
    		Register r = (Register)p;
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLExpirationItem.fxml"));
    		try {
				Parent item = loader.load();
				ExpirationItemController c = loader.getController();
				c.initi(r);
				this.vboxItens.getChildren().add(item);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

}
