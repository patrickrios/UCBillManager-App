package controller;

import java.util.ArrayList;
import javafx.fxml.FXML;
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
		setPaid();
		initiChoiceboxCategories();
		initiChoiceboxPayments();
		initiChoiceboxType();
		initiDateExp();
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
	 void save() {
		this.register.updateThis();
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
	
	private void initiDateExp() {
		
	}
}
