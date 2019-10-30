package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.util.ExtendedAnchorConst;

public class ExpirationListController{

	@FXML
    private AnchorPane anchorTransparent;
	@FXML
    private ChoiceBox<String> choiceboxInterval;
    @FXML
    private Label labelTotalExpirations;
	@FXML
    private VBox vboxItens;
	
	public void initi(int values[], int typeExpInt){
		loadList();
		ExtendedAnchorConst.setConstrants(anchorTransparent);
		initiChoiceboxInterval();
		setLabelTotalExp(values[typeExpInt]);	
		selectChoiceInt(typeExpInt);
	}
	
 	private void loadList(){
		for(int i=0;i<3;i++){
			try {
				Parent item = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLExpirationItem.fxml"));
				this.vboxItens.getChildren().add(item);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	public void closeList(){
		StackPane stack = (StackPane)this.anchorTransparent.getParent();
		stack.getChildren().remove(this.anchorTransparent);
	}

	private void initiChoiceboxInterval(){
		this.choiceboxInterval.getItems().add("Hoje");
		this.choiceboxInterval.getItems().add("Semanal");
		this.choiceboxInterval.getItems().add("Mensal");
		//this.choiceboxInterval.setValue("Hoje");
	}
	
	private void setLabelTotalExp(int total)
	{
		this.labelTotalExpirations.setText("("+total+")");
	}
	
	private void selectChoiceInt(int index)
	{
		this.choiceboxInterval.setValue(choiceboxInterval.getItems().get(index));
	}
}
