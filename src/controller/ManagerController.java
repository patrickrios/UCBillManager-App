package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.dao.PersistentBean;
import model.entity.Persistent;
import view.util.FadeEffect;

public class ManagerController
{
    @FXML
    private Label labelTitle;
    @FXML
    private VBox vboxManagerList;
    @FXML
    private Label labelTotal;
    
    private StackPane stackManegerLayout;
    
    private PersistentBean persistentBean;

    public void initi(String title, PersistentBean dao, StackPane stack){
        this.labelTitle.setText(title);
        this.labelTotal.setText("("+dao.numberOfRegisters()+")");
        this.persistentBean = dao;
        this.stackManegerLayout = stack;
        this.loadItens();
        //.stackManegerLayout.setAlignment(Pos.TOP_LEFT);
    }
    
    void loadItens(){
    	ArrayList<Persistent> list = this.persistentBean.findAll();
    	this.vboxManagerList.getChildren().clear();
    	
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
    void loadAddNewLayout()
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagerAddNew.fxml"));
    	
    	try {
			Parent parent = loader.load();
			ManagerAddNewController controller = loader.getController();
			controller.inti(this.persistentBean, this.stackManegerLayout, this);
			new FadeEffect(parent);
			stackManegerLayout.getChildren().add(parent);
			controller.fullSizeConteiner();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
  	
}