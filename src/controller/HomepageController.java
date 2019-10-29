package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import view.util.FadeEffect;

public class HomepageController implements Initializable
{
	@FXML
    private StackPane stackpaneHomepage;
	@FXML
    private Label labelCurrentDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initiCurrentDate();
	}
	
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
	
	private void initiCurrentDate(){
		Date today = new Date();
		DateFormat formated = DateFormat.getDateInstance(DateFormat.FULL);
		this.labelCurrentDate.setText(formated.format(today));
	}

}

