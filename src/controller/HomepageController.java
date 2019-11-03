package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.mysql.cj.exceptions.FeatureNotAvailableException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import view.util.FadeEffect;
import javafx.scene.layout.VBox;
import model.dao.ExpiredDAO;
import model.types.TypeExpInterval;
import model.types.TypeRegister;
import model.util.ExpirationDate;
import model.util.ExpiredRegister;

public class HomepageController implements Initializable
{
	@FXML
    private StackPane stackpaneHomepage;
	@FXML
    private Label labelCurrentDate;
	@FXML
    private Label labelDailyExp;
    @FXML
    private Label labelWeeklyExp;
    @FXML
    private Label labelMonthlyExp;
	@FXML
    private VBox vboxCards;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initiCurrentDate();
		loadCard(TypeRegister.DESPESA);
		loadCard(TypeRegister.RECEITA);
		initExpLabels();
	}
	
	@FXML
    void loadDailyExpirationList() {
		loadExpirationList(0);
    }
	
    @FXML
    void loadWeeklyExpirationList() {
    	loadExpirationList(1);
    }

    @FXML
    void loadMonthlyExpirationList() {
    	loadExpirationList(2);
    }

    private void loadExpirationList(int typeExpInt) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLExpirationList.fxml"));
		try {
			Parent parent = loader.load();
			ExpirationListController c = loader.getController();
			c.initi(loadNumbersExpirations(), typeExpInt);
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
	
	private void loadCard(int type){
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
	
	private int[] loadNumbersExpirations(){
		int values[] = new ExpiredDAO().numberOfExpirations();
		return values;
	}
	
	private void initExpLabels(){
		int m[] = new ExpiredDAO().numberOfExpirations();
		this.labelDailyExp.setText(labelDailyExp.getText().replace("0",""+m[0]));
		this.labelWeeklyExp.setText(labelWeeklyExp.getText().replace("0",""+m[1]));
		this.labelMonthlyExp.setText(labelMonthlyExp.getText().replace("0",""+m[2]));
	}	
}