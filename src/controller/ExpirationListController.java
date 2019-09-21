package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ExpirationListController implements Initializable{

	@FXML
    private AnchorPane anchorTransparent;
	@FXML
    private VBox vboxItens;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadList();
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

	
}
