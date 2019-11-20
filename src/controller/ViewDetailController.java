package controller;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.entity.Register;
import model.types.TypeDeleting;
import view.util.FadeEffect;
import view.util.FullSizeOnStack;

public class ViewDetailController {
	@FXML
    private AnchorPane anchorViewDetail;

    @FXML
    private Label labelValue;

    @FXML
    private Label labelParcel;

    @FXML
    private Label labelPayment;

    @FXML
    private Label labelExpirationDate;

    @FXML
    private Label labelPaidStatus;

    @FXML
    private Label labelMonthRef;

    @FXML
    private Pane paneOptions;

    @FXML
    private Label labelInclusion;

    @FXML
    private Label labelCode;

    @FXML
    private Label labelTypeCategory;
	
	private boolean showOptionControl = true;

	private StackPane stack;
	
	private Register register;
	
	
	public void initi(StackPane stack, Register register){
		this.stack = stack;
		this.register = register;
		this.labelCode.setText(register.getCode());
		this.labelTypeCategory.setText(register.getTypeName()+", "+register.getCategoryName());
		this.labelParcel.setText(register.getParcel()+"");
		this.labelPayment.setText(register.getPayment().toString());
		this.labelExpirationDate.setText(register.exiprationDateFormatted());
		this.labelPaidStatus.setText(register.getPaidStatus());
		this.labelValue.setText(register.getValueFormatted());
		this.labelInclusion.setText(register.inclusionDateFormatted());
		this.labelMonthRef.setText(register.getMonthFormatted());
		definePayStyle(register.isPaid());
	}
	
	@FXML
    void showOptions() {
		this.paneOptions.setVisible(showOptionControl);
		this.showOptionControl = !this.showOptionControl;
    }
	
	@FXML
	void exitDetails(){
		this.stack.getChildren().remove(this.anchorViewDetail);
	}
	
	@FXML
	void deleteThisRegister()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLDeletePopup.fxml"));
		try {
			Parent popup = loader.load();
			DeleteItemController c = loader.getController();
			c.initi(this.register, this.stack, TypeDeleting.REGISTER);
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
	private void definePayStyle(boolean paid) {
		String yep = "label-item-type-revenue";
		String not = "label-item-type-expense";
		
		if(paid)
			this.labelPaidStatus.getStyleClass().add(yep);
		else
			this.labelPaidStatus.getStyleClass().add(not);
	}
	
	@FXML
	void hideOptionIfIsVisible() {
		if(!this.showOptionControl)
			showOptions();
	}
	
	void fullSizeConteiner() {
		FullSizeOnStack.extend(this.anchorViewDetail);
	}
}
