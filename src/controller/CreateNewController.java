package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ChoiceBox<?> choiceboxCatgories;
    
	private boolean paidControl = false;
	
	private int parcel = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateParcelText();
		
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
    void setPaid()
    {
    	
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
    
    private void updateParcelText()
    {
    	this.textfieldParcel.setText(""+this.parcel);
    }

	


}
