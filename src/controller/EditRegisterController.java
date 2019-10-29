package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    private TextField textfieldPArcel;
    @FXML
    private ChoiceBox<Persistent> choiceboxPayment;
    @FXML
    private DatePicker datepickerExpiration;
    @FXML
    private TextField textfieldValue;
    @FXML
    private Button buttonPaid;
    
	private StackPane stack;
	
	private Register register;
	
	private boolean paidControl;
	
	public void initi(StackPane stack, Register register)
	{
		this.stack = stack;
		this.register = register;
		this.choiceboxType.getItems().add(register.getTypeName());
		this.choiceboxType.setValue(register.getTypeName());
		this.choiceboxCategory.getItems().add(register.getCategory());
		this.choiceboxCategory.setValue(register.getCategory());
		this.textfieldPArcel.setText(register.getParcel()+"");
		this.choiceboxPayment.getItems().add(register.getPayment());
		this.choiceboxPayment.setValue(register.getPayment());
		this.textfieldValue.setText(register.getValueWithoutPrefix());
		this.paidControl = !register.isPaid();
		setPaid();
		
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

	}
}
