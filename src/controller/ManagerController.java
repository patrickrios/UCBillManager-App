package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.bean.Persistent;
import model.dao.PersistentBean;

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
    
    private PersistentBean pBean;

    public void initi(String title, PersistentBean dao){
        this.labelTitle.setText(title);
        this.pBean = dao;
        this.loadItens(dao);
    }
    
    private void loadItens(PersistentBean b){
    	ArrayList<Persistent> list = b.findAll();
    	
    	for(Persistent p : list)
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagerItem.fxml"));
        	
	    	try {
				Parent parent = loader.load();
				ManagerItemController controller = loader.getController();
				controller.initi(p, this.stackManegerLayout);
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
    	if(textInputIsValid()){
    		String input = this.textfieldInput.getText();
    		this.pBean.createNew(input);
    		cleanTexfieldInput();
    	}
    	else{
    		this.textfieldInput.getStyleClass().add("textfield-empty");
    	}
   
    }
    
    private boolean textInputIsValid()
    {
    	return !this.textfieldInput.getText().isEmpty();
    }
    
    private void cleanTexfieldInput()
    {
    	this.textfieldInput.clear();
    }
}
