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
import model.entity.Category;
import model.entity.Payment;
import model.entity.Persistent;
import model.entity.Register;
import model.exception.EmptyInputException;
import model.exception.RegisterAlreadyExistException;
import view.monthpicker.MonthPicker;
import view.monthpicker.MonthRef;
import view.util.ConfirmMessageType;
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
    @FXML
    private Pane paneMonthPicker;
    
    private MonthPicker monthPicker;
    
    private StackPane stackCreateNew;
    
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
	}
	
	public void initi(StackPane stack) {
		this.stackCreateNew = stack;
	}
	
	private Register getRegisterFromForm(){
		//identify
		String   code = this.textfieldCode.getText();
		Category cat  = (Category)this.choiceboxCatgories.getValue();
		Payment  pay  = (Payment)this.choiceboxPayments.getValue();
		//value
		float 	  value  = RealFormat.realStringToFloat(this.textfieldValue.getText());
		int       parcel = Integer.parseInt(this.textfieldParcel.getText());
		MonthRef  month  = this.monthPicker.getValue();
		Timestamp exp    = getDateExpirationFormat();
		boolean   paid   = this.paidControl;
		int 	  typ    = this.type;
		
		return new Register(code, cat, pay, value, paid, parcel, month, exp, typ);
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
    	
    	try {
    		validateTextField(this.textfieldCode);
    		validateTextField(this.textfieldValue);
    		
    		try {
    			Register reg = getRegisterFromForm();
				reg.createNewIfNotExists();
	            clearForm();
	            showMessage(reg.getCode(), ConfirmMessageType.SUCESS);
			} 
    		catch (RegisterAlreadyExistException e) {
				showMessage(this.textfieldCode.getText(), ConfirmMessageType.ERROR);
			}	
    		
		} catch (EmptyInputException ex) {
			ex.printStackTrace();
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
    
    private void validateTextField(TextField tf) throws EmptyInputException{
    	if(tf.getText().isEmpty()) {
    		markTextfieldAsEmpty(tf);
			throw new EmptyInputException(tf.getId());
    	}
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
			c.fullConteinerSize();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void clearForm(){
    	this.textfieldCode.setText("");
    	this.textfieldParcel.setText("1");
    	this.textfieldValue.setText("");
    }
    
  	private void loadMonthPicker() {
  		this.monthPicker = new MonthPicker();
  		this.paneMonthPicker.getChildren().add(this.monthPicker);
  	}
}
