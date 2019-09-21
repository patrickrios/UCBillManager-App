package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ManagementController
{
	@FXML
    private StackPane stackManegerLayout;
    @FXML
    private Label labelTitle;
    
    @FXML
    private VBox vboxManagerList;
    
    private String name = "";

    public void initi(String title)
    {
        this.labelTitle.setText(title);
        this.name = title;
        this.loadItens();
    }
    
    private void loadItens()
    {
    	
    	for(int i=0;i<5; i++) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagerItem.fxml"));
        	
	    	try {
				Parent parent = loader.load();
				ManagerItemController controller = loader.getController();
				controller.initi(this.name+" "+(i+1), this.stackManegerLayout);
				this.vboxManagerList.getChildren().add(parent);
			} 
	    	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
