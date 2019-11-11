package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.dao.CategoryDAO;
import model.dao.PaymentDAO;
import model.entity.Category;
import model.entity.Payment;
import model.entity.Persistent;
import model.entity.Register;
import view.util.ConfirmMessageType;
import view.util.RealFormat;

public class EditRegisterController {
	@FXML
    private AnchorPane anchorpaneEdit;
    @FXML
    private ChoiceBox<String> choiceboxType;
    @FXML
    private ChoiceBox<Persistent> choiceboxCategory;
    @FXML
    private TextField textfieldParcel;
    @FXML
    private ChoiceBox<Persistent> choiceboxPayment;
    @FXML
    private DatePicker datepickerExpiration;
    @FXML
    private TextField textfieldValue;
    @FXML
    private Button buttonPaid;
    @FXML
    private Label labelCode;
    @FXML
    private TextField textfieldCode;
    
	private StackPane stack;
	
	private Register register;
	
	private boolean paidControl;
	
	private int parcelValue;
	
	public void initi(StackPane stack, Register register)
	{
		this.stack = stack;
		this.register = register;
		this.labelCode.setText(register.getCode());
		this.textfieldCode.setText(register.getCode());
		this.textfieldParcel.setText(register.getParcel()+"");
		this.textfieldValue.setText(register.getValueWithoutPrefix());
		this.paidControl = !register.isPaid();
		this.parcelValue = register.getParcel();
		switchPaidStatus();
		initiChoiceboxCategories();
		initiChoiceboxPayments();
		initiChoiceboxType();
		initiExpDate();
	}
	
	@FXML
    void decrementParcelQuantity() {
		if(this.parcelValue > 1) {
			this.parcelValue--;
			this.textfieldParcel.setText(""+this.parcelValue);
		}
    }

    @FXML
    void incrementParcelQuantity() {
    	this.parcelValue++;
    	this.textfieldParcel.setText(""+this.parcelValue);
    }
	
	@FXML
	void closeEdit(){
		this.stack.getChildren().remove(this.anchorpaneEdit);
	}
	
	@FXML
    void switchPaidStatus(){
    	
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
	 void save() {
		updateDatasOfRegister();
		boolean ok = this.register.updateDatas();
		if(ok)
			showMessage(ConfirmMessageType.UPDATE);
		else
			showMessage(ConfirmMessageType.ERROR);
		
	}
	
	private void initiChoiceboxCategories(){		
		ArrayList<Persistent> list = new CategoryDAO().findAll();
		
		for(Persistent persistent : list) {
			Category cat = (Category)persistent;
			if(cat.getId() == this.register.getCategory().getId())
				this.choiceboxCategory.getItems().add(register.getCategory());
			else
				this.choiceboxCategory.getItems().add(persistent);
		}
		this.choiceboxCategory.setValue(this.register.getCategory());
	}
	
	private void initiChoiceboxPayments() {
		ArrayList<Persistent> list = new PaymentDAO().findAll();
		
		for(Persistent persistent :list) {
			Payment pay = (Payment)persistent;
			if(pay.getId()==register.getPayment().getId())
				this.choiceboxPayment.getItems().add(register.getPayment());
			else
				this.choiceboxPayment.getItems().add(persistent);
		}
		this.choiceboxPayment.setValue(register.getPayment());
	}
	
	private void initiChoiceboxType() {
		this.choiceboxType.getItems().addAll("Despesa", "Receita");
		this.choiceboxType.setValue(register.getTypeName());
	}
	
	private void initiExpDate() {
    	LocalDate value = this.register.getExpirationDate().toLocalDateTime().toLocalDate(); 
    	this.datepickerExpiration.setValue(value);
	}
	
	private void updateDatasOfRegister() {
		//identify
		String   code = textfieldCode.getText();
		int    	 type = getTypeValue();
		Category cat  = (Category)choiceboxCategory.getSelectionModel().getSelectedItem();
		Payment  pay  = (Payment)choiceboxPayment.getSelectionModel().getSelectedItem();
		//values
		float     value  = RealFormat.realStringToFloat(textfieldValue.getText());
		int       parcel = Integer.parseInt(textfieldParcel.getText());
		Timestamp exp    = getDateExpirationFormat();
		boolean   paid   = paidControl;
		
		this.register.updateDatas(code, cat, pay, value, paid, parcel, exp, type);
		
	}
	
	private Timestamp getDateExpirationFormat(){
    	LocalDate value = this.datepickerExpiration.getValue();
    	String time = new SimpleDateFormat("hh:mm:ss").format(new Date().getTime());
    	return Timestamp.valueOf(value.toString()+" "+time);
    }
	
	private int getTypeValue() {
		
		int value = 1;
		String type = choiceboxType.getSelectionModel().getSelectedItem();
		
		if(type.equals("Receita"))
			value = 2;
		return value;
	}
	
	private void showMessage(String type) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLConfirmMessage.fxml"));
		try {
			loader.load();
			ConfirmMessageController c = loader.getController();
			c.inti(this.register.getCode(), type, this.stack);
		} catch (IOException e) {
			e.printStackTrace();
		}; 
	}
}
