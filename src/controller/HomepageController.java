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
import javafx.scene.layout.VBox;
import model.entity.TypeRegister;

public class HomepageController implements Initializable
{
	@FXML
    private StackPane stackpaneHomepage;
	@FXML
    private Label labelCurrentDate;
	@FXML
    private VBox vboxCards;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initiCurrentDate();
		loadCard(TypeRegister.DESPESA);
		loadCard(TypeRegister.RECEITA);
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
	
	private void loadCard(int type)
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLHomeCard.fxml"));
		
		try {
			Parent p = loader.load();
			HomeCardController c = loader.getController();
			c.inti(type);
			this.vboxCards.getChildren().add(p);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

