package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.bean.Persistent;
import model.bean.Register;

public class ListLayoutController {

	@FXML
    private VBox vboxItens;
	
	private ArrayList<Persistent> list;
	
	private StackPane stack;
	
	public void initi(ArrayList<Persistent> list, StackPane stack){
		this.list = list;
		this.stack = stack;
		loadList();
	}
	
	public void loadList(){ 
		this.vboxItens.getChildren().clear();
		
    	for(Persistent p : this.list){
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLListItem.fxml"));
    		
    		try{
				Parent item = loader.load();
				ListItemController c = loader.getController();
				c.inti((Register)p, this.stack);
				this.vboxItens.getChildren().add(item);
    		} 
    		catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
	
}