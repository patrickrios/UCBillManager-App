package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.dao.PersistentBean;
import model.entity.Persistent;
import view.util.ConfirmMessageType;

public class ManagerController
{
	@FXML
    private StackPane stackManegerLayout;
    @FXML
    private Label labelTitle;
    @FXML
    private VBox vboxManagerList;
    @FXML
    private TextField textfieldInput;
    
    private PersistentBean persistentBean;
    
    private int deleteType;

    public void initi(String title, PersistentBean dao, int deleteType){
        this.labelTitle.setText(title);
        this.persistentBean = dao;
        this.deleteType = deleteType;
        this.loadItens(dao);
        this.stackManegerLayout.setAlignment(Pos.TOP_LEFT);
    }
    
    private void loadItens(PersistentBean b){
    	ArrayList<Persistent> list = b.findAll();
    	this.vboxManagerList.getChildren().clear();
    	
    	for(Persistent p : list)
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagerItem.fxml"));
        	
	    	try {
				Parent parent = loader.load();
				ManagerItemController controller = loader.getController();
				controller.initi(p, this.stackManegerLayout, this.deleteType);
				this.vboxManagerList.getChildren().add(parent);
			} 
	    	catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    void create()
    {
    	if(textInputIsValid())
    	{
    		String input = this.textfieldInput.getText();
    		
    		if(this.persistentBean.verifyExistenceOf(input)){
    			showMessage(input, ConfirmMessageType.ERROR);
    		}
    		else{
        		this.persistentBean.createNew(input);
        		cleanTexfieldInput();
        		showMessage(input, ConfirmMessageType.SUCESS);
        		loadItens(persistentBean);
    		}
    	}
    	else{
    		this.textfieldInput.getStyleClass().add("textfield-empty");
    	}
    }
    
    private boolean textInputIsValid(){
    	return !this.textfieldInput.getText().isEmpty();
    }
    
    private void cleanTexfieldInput(){
    	this.textfieldInput.clear();
    }
    
    private void showMessage(String input, String type)
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLConfirmMessage.fxml"));
    	
    	try {
			loader.load();
			ConfirmMessageController c = loader.getController();
			c.inti(input, type, this.stackManegerLayout);
		} catch (IOException e) {
			e.printStackTrace();
		}
    			
    }
}