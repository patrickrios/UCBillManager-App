package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import model.bean.Persistent;
import model.bean.Register;

public class ListGridLayoutController {

	@FXML
    private FlowPane flowpaneListCards;
	
	private ArrayList<Persistent> list;
	
	private StackPane stack;
	
	public void initi(ArrayList<Persistent> l, StackPane stack)
	{
		this.list = l;
		this.stack = stack;
		loadListCards();
	}
	
	@FXML
    public void loadListCards()
    {
    	this.flowpaneListCards.getChildren().clear();
    	
    	for(Persistent p : list)
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLListItemCard.fxml"));
    		try 
    		{
				Parent item = loader.load();
				ListItemGridController c = loader.getController();
				c.initi((Register)p, this.stack);
				this.flowpaneListCards.getChildren().add(item);
    		} 
    		catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}
