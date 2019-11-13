package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.dao.CategoryDAO;
import model.dao.PaymentDAO;
import model.dao.RegisterDAO;
import model.entity.Category;
import model.entity.Payment;
import model.entity.Persistent;
import model.entity.Register;
import view.components.MonthPicker;
import view.util.ConfirmMessageType;
import view.util.RealFormat;

public class CreateNewController implements Initializable{
	@FXML
    private StackPane stackCreateNew;
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
    @FXML
    private Pane paneMonthPicker;
    
    private MonthPicker monthPicker;
    
	private boolean paidControl = false;
	
	private int parcel = 1;
	
	private int type = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateCategories();
		populatePayments();
		updateParcelText();
		initiExpiration();
		loadMonthPicker();
		this.stackCreateNew.setAlignment(Pos.TOP_LEFT);
	}
	
	private Register getRegisterFromForm(){
		//identify
		String   code = this.textfieldCode.getText();
		Category cat  = (Category)this.choiceboxCatgories.getValue();
		Payment  pay  = (Payment)this.choiceboxPayments.getValue();
		//value
		float 	  value  = RealFormat.realStringToFloat(this.textfieldValue.getText());
		int       parcel = Integer.parseInt(this.textfieldParcel.getText());
		Timestamp exp    = getDateExpirationFormat();
		boolean   paid   = this.paidControl;
		int 	  typ    = this.type;
		
		return new Register(code, cat, pay, value, paid, parcel, exp, typ);
	}
	
    @FXML
    void decrementParcelQuantity() {
    	if(this.parcel > 1){
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
    void markRegisterAsIn(){
    	this.type=2;
    	markAsSelectedType(buttonInType, buttonOutType);	
    }
    
    @FXML
    void save(){
    	if(validateTextField(this.textfieldCode)&& validateTextField(this.textfieldValue)){
    		String code = this.textfieldCode.getText();
    		
    		if(!new RegisterDAO().verifyExistenceOf(code))
    		{
    			Register reg = getRegisterFromForm();
            	reg.createNewIfNotExists();
            	clearForm();
            	showMessage(code, ConfirmMessageType.SUCESS);
    		}
    		else{
    			showMessage(code, ConfirmMessageType.ERROR);
    		}
    	}
    	
    	else{
    		if(!validateTextField(this.textfieldCode))
    			markTextfieldAsEmpty(this.textfieldCode);
    		
    		if(!validateTextField(this.textfieldValue))
    			markTextfieldAsEmpty(this.textfieldValue);
    	}
    }
    
    private void updateParcelText(){
    	this.textfieldParcel.setText(""+this.parcel);
    }
    
    private void populateCategories(){
    	ArrayList<Persistent> list = new CategoryDAO().findAll();	
    	choiceboxCatgories.getItems().addAll(list);	
    	
    	Persistent p = list.get(0);
    	choiceboxCatgories.setValue(p);
    }
    
    private void populatePayments(){
    	ArrayList<Persistent> list = new PaymentDAO().findAll();
    	choiceboxPayments.getItems().addAll(list);
    	
    	Persistent p = list.get(0);
    	choiceboxPayments.setValue(p);
    }
    
    private void markAsSelectedType(Button selected, Button unselected){
    	String selectedStyle = "choice-button-selected";
    	String unselectedStyle = "choice-button";
    	
    	selected.getStyleClass().clear();
    	selected.getStyleClass().add("button");
    	selected.getStyleClass().add(selectedStyle);
    	
    	unselected.getStyleClass().clear();
    	unselected.getStyleClass().add("button");
    	unselected.getStyleClass().add(unselectedStyle);
    }
    
    private void initiExpiration(){
    	this.datepickerExpiration.setValue(LocalDate.now());
    }

    private Timestamp getDateExpirationFormat(){
    	LocalDate value = this.datepickerExpiration.getValue();
    	String time = new SimpleDateFormat("hh:mm:ss").format(new Date().getTime());
    	return Timestamp.valueOf(value.toString()+" "+time);
    }
    
    private boolean validateTextField(TextField tf){
    	return !tf.getText().isEmpty();
    }
    
    private void markTextfieldAsEmpty(TextField tf){
    	tf.getStyleClass().add("textfield-empty");
    }
    
    private void showMessage(String code, String type)
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLConfirmMessage.fxml"));
    	
    	try {
			loader.load();
			ConfirmMessageController c = loader.getController();
			c.inti(code, type, this.stackCreateNew);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void clearForm()
    {
    	this.textfieldCode.setText("");
    	this.textfieldParcel.setText("1");
    	this.textfieldValue.setText("");
    }
    
    
  	private void loadMonthPicker() {
  		this.monthPicker = new MonthPicker();
  		this.paneMonthPicker.getChildren().add(this.monthPicker);
  	}
}
