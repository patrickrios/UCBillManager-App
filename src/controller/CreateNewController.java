package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bean.Category;
import model.bean.Payment;
import model.bean.Persistent;
import model.bean.Register;
import model.dao.CategoryDAO;
import model.dao.PaymentDAO;
import view.util.RealFormat;

public class CreateNewController implements Initializable{
	
	@FXML
    private TextField textfieldCode;
    @FXML
    private TextField textfieldValue;
    @FXML
    private TextField textfieldParcel;
    @FXML
    private DatePicker datepickerExpiration;
    @FXML
    private Button buttonPaid;
    @FXML
    private ChoiceBox<Persistent> choiceboxPayments;
    @FXML
    private ChoiceBox<Persistent> choiceboxCatgories;
    @FXML
    private Button buttonOutType;
    @FXML
    private Button buttonInType;
    
	private boolean paidControl = false;
	
	private int parcel = 1;
	
	private int type = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateCategories();
		populatePayments();
		updateParcelText();
		initiExpiration();
	}
	
	private Register getRegisterFromForm()
	{
		String code = this.textfieldCode.getText();
		Category c = (Category)this.choiceboxCatgories.getSelectionModel().getSelectedItem();
		Payment p = (Payment)this.choiceboxPayments.getSelectionModel().getSelectedItem();
		float value = RealFormat.realStringToFloat(this.textfieldValue.getText());
		int parcel = Integer.parseInt(this.textfieldParcel.getText());
		Timestamp exp = getDateExpiratinFormat();
		boolean paid = this.paidControl;
		int t = this.type;
		
		return new Register(code, c, p, value, paid, parcel, exp, t);
	}
	
    @FXML
    void decrementParcelQuantity() {
    	if(this.parcel > 1)
    	{
    		this.parcel--;
    		updateParcelText();
    	}
    }

    @FXML
    void incrementParcelQuantity() {
    	this.parcel++;
    	updateParcelText();
    }

    @FXML
    void setPaid(){
    	
    	if(this.paidControl){
    		this.paidControl = false;
    		Image img = new Image(getClass().getResourceAsStream("/view/img/toggle-off-icon-min.png"));
    		this.buttonPaid.setGraphic(new ImageView(img));
    	}
    	
    	else{
    		this.paidControl = true;
    		Image img = new Image(getClass().getResourceAsStream("/view/img/toggle-on-icon-min.png"));
    		this.buttonPaid.setGraphic(new ImageView(img));
    	}
    }
    
    @FXML
    void markRegisterAsOut(){
    	this.type = 1;
    	markAsSelectedType(buttonOutType, buttonInType);
    }
    
    @FXML
    void markRegisterAsIn()
    {
    	this.type=2;
    	markAsSelectedType(buttonInType, buttonOutType);
    	
    }
    
    @FXML
    void save()
    {
    	Register reg = getRegisterFromForm();
    	reg.createNewIfNotExists();
    }
    
    private void updateParcelText(){
    	this.textfieldParcel.setText(""+this.parcel);
    }
    
    private void populateCategories()
    {
    	ArrayList<Persistent> list = new CategoryDAO().findGroup(0,0);	
    	choiceboxCatgories.getItems().addAll(list);	
    	
    	Persistent p = list.get(0);
    	choiceboxCatgories.setValue(p);
    }
    
    private void populatePayments()
    {
    	ArrayList<Persistent> list = new PaymentDAO().findGroup(0,0);
    	choiceboxPayments.getItems().addAll(list);
    	
    	Persistent p = list.get(0);
    	choiceboxPayments.setValue(p);
    }
    
    private void markAsSelectedType(Button selected, Button unselected)
    {
    	String selectedStyle = "choice-button-selected";
    	String unselectedStyle = "choice-button";
    	
    	selected.getStyleClass().clear();
    	selected.getStyleClass().add("button");
    	selected.getStyleClass().add(selectedStyle);
    	
    	unselected.getStyleClass().clear();
    	unselected.getStyleClass().add("button");
    	unselected.getStyleClass().add(unselectedStyle);
    }
    
    private void initiExpiration()
    {
    	this.datepickerExpiration.setValue(LocalDate.now());
    }

    private Timestamp getDateExpiratinFormat()
    {
    	LocalDate d = this.datepickerExpiration.getValue();
    	Timestamp t = Timestamp.valueOf(d.toString()+" 00:00:00.00");
    	
    	return t;
    }
}
