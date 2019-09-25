package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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
    
    private String name = "";

    public void initi(String title, PersistentBean dao)
    {
        this.labelTitle.setText(title);
        this.name = title;
        this.loadItens(dao);
    }
    
    private void loadItens(PersistentBean b)
    {
    	ArrayList<Persistent> list = b.findGroup(0, 0);
    	
    	for(Persistent p : list)
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagerItem.fxml"));
        	
	    	try {
				Parent parent = loader.load();
				ManagerItemController controller = loader.getController();
				controller.initi(p.toString(), this.stackManegerLayout);
				this.vboxManagerList.getChildren().add(parent);
			} 
	    	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
