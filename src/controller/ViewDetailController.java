package controller;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import model.bean.Register;
import view.util.FadeEffect;

public class ViewDetailController {

	@FXML
    private StackPane stackpaneViewDetail;
	@FXML
    private Label labelCode;
	@FXML
	private Label labelValue;
	@FXML
	private Label labelTypeRegister;
	@FXML
	private Label labelCategoryName;
	@FXML
	private Label labelParcel;
	@FXML
	private Label labelPayment;
	@FXML
	private Label labelExpirationDate;
	@FXML
	private Label labelPaidStatus;

	private StackPane stack;
	
	private Register register;
	
	public void initi(StackPane stack, Register register){
		this.stack = stack;
		this.register = register;
		this.labelCode.setText(register.getCode());
		this.labelTypeRegister.setText(register.getTypeName());
		this.labelCategoryName.setText(register.getCategoryName());
		this.labelParcel.setText(register.getParcel()+"");
		this.labelPayment.setText(register.getPayment().toString());
		this.labelExpirationDate.setText(register.getExpirationDate().toString());
		this.labelPaidStatus.setText((register.isPaid())?"Sim":"Não");
		this.labelValue.setText(register.getValueFormatted());
	}
	
	@FXML
	void exitDetails(){
		this.stack.getChildren().remove(this.stackpaneViewDetail);
	}
	
	@FXML
	void deleteThisRegister()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLDeletePopup.fxml"));
		try {
			Parent popup = loader.load();
			DeleteItemController c = loader.getController();
			c.initi(this.register, this.stack);
			new FadeEffect(popup);
			this.stack.getChildren().add(popup);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void editThisRegister()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLEditRegister.fxml"));
		try {
			Parent edit = loader.load();
			EditRegisterController c = loader.getController();
			c.initi(this.stack, this.register);
			new FadeEffect(edit);
			this.stack.getChildren().add(edit);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
