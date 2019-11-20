package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.dao.ExpiredDAO;
import model.util.ExpirationInterval;
import model.util.ExpiredRegister;
import view.util.ExtendedAnchorConst;
import view.util.FullSizeOnStack;

public class ExpirationListController{

	@FXML
    private AnchorPane anchorTransparent;
	@FXML
    private ChoiceBox<String> choiceboxInterval;
    @FXML
    private Label labelTotalExpirations;
	@FXML
    private VBox vboxItens;
	int values[];
	
	public void initi(int values[], int typeExpInt){
		loadList(typeExpInt);
		ExtendedAnchorConst.setConstrants(anchorTransparent);
		initiChoiceboxInterval();
		setLabelTotalExp(values[typeExpInt]);	
		selectChoiceInt(typeExpInt);
		loadListFromChoiceboxListener();
		this.values = values;
	}
	
	void loadListFromChoiceboxListener()
	{
		this.choiceboxInterval.getSelectionModel().selectedIndexProperty().addListener(new 
				ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,
					Number newValue) {
					loadList(newValue.intValue());
					setLabelTotalExp(values[newValue.intValue()]);
				}		
		});
	}
	
 	private void loadList(int expInterval)
 	{
 		String typeExpInt = ExpirationInterval.getIntervalOfInt(expInterval);
 		ArrayList<ExpiredRegister> list = new ExpiredDAO().loadExpiration(typeExpInt);
 		this.vboxItens.getChildren().clear();
 		
		for(ExpiredRegister r: list){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLExpirationItem.fxml"));
			try {
				Parent item = loader.load();
				ExpirationItemController c = loader.getController();
				c.initi(r);
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
	}
	
	private void setLabelTotalExp(int total){
		this.labelTotalExpirations.setText("("+total+")");
	}
	
	private void selectChoiceInt(int index){
		this.choiceboxInterval.setValue(choiceboxInterval.getItems().get(index));
	}
	
	public void extendedTransparent() {
		FullSizeOnStack.extend(this.anchorTransparent);
	}
}
