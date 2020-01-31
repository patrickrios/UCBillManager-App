package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MenuController implements Initializable
{
	@FXML
    private StackPane stackMainContent;
    @FXML
    private AnchorPane anchorpaneContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    	this.initi(this.anchorpaneContent,this.stackMainContent);
        initStackAligment();
    }
    
    private void initStackAligment() {
    	this.stackMainContent.setAlignment(Pos.TOP_LEFT);
    }

}