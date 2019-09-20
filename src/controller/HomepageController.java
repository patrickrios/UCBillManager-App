package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import view.util.FadeEffect;

public class HomepageController 
{
	@FXML
    private StackPane stackpaneHomepage;
	
	
	@FXML
    void loadExpirationList() {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLExpirationListHomepage.fxml"));
			new FadeEffect(parent);
			this.stackpaneHomepage.getChildren().add(parent);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
