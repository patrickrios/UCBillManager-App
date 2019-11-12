package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
    private StackPane stackManegerLayout;
    @FXML
    private Label labelTitle;
    @FXML
    private VBox vboxManagerList;
    
    private PersistentBean persistentBean;
    
    private int deleteType;

    public void initi(String title, PersistentBean dao, int deleteType){
        this.labelTitle.setText(title);
        this.persistentBean = dao;
        this.deleteType = deleteType;
        this.loadItens();
        this.stackManegerLayout.setAlignment(Pos.TOP_LEFT);
        //loadPicker();
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
				controller.initi(p, this.stackManegerLayout, this.deleteType);
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
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }

  //só para teste
  /*	private void loadPicker() {
  		try {
  			Parent p = FXMLLoader.load(getClass().getResource("/view/components/FXMLMonthPicker.fxml"));
  			this.vboxManagerList.getChildren().add(p);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	}
  	*/
}