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
import model.entity.TypeRegister;
import model.util.ExpirationDate;
import model.util.ExpiredRegister;

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
		testExp();
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
	
	private void testExp()
	{
		ExpiredDAO dao = new ExpiredDAO();
		ArrayList<ExpiredRegister> list = dao.loadMonthlyExpiration();
		System.out.println("\nMONTHLY EXPIRATIONS");
		printList(list);
		System.out.println("\nWEEKLY EXPIRATIONS");
		list = dao.loadWeeklyExpiration();
		printList(list);
		System.out.println("\nDAILY EXPIRATIONS");
		list = dao.loadDailyExpiration();
		printList(list);
	}
	
	private void printList(ArrayList<ExpiredRegister> list)
	{
		for(ExpiredRegister r : list){
			System.out.println("Expired {code="+r.getCode()+", value="+r.formattedValue()+", expiration="+r.formattedExpDate()+"}");
		}
	}
	
}
