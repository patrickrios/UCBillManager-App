package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.entity.Persistent;
import model.entity.Register;
import model.exception.DeleteITemException;

public class DeleteListItemController {
    @FXML
    private Label labelCode;
    @FXML
    private Label labelTypeCat;
    @FXML
    private Label labelVenc;
    @FXML
    private Label labelValue;
    @FXML
    private Label labelStatus;
    
    private Persistent item;
    
    public void init(Persistent item) {
    	this.item = item;
    	initiLabels();
    }
    
    public int deleteThisItem() {
    	int count=0;
    	try {
			this.item.deleteThis();
			markItemAsDeleted();
			count=1;
		} catch (DeleteITemException e) {
			markItemAsFailed();
			e.printStackTrace();
		}
    	return count;
    }
    
    private void markItemAsDeleted() {
    	this.labelStatus.setText("Apagado");
    	this.labelStatus.getStyleClass().add("label-deleted-item");
    	this.labelStatus.setVisible(true);
    }
    
    private void markItemAsFailed() {
    	this.labelStatus.setText("Falhou! Tente novamente");
    	this.labelStatus.getStyleClass().add("label-failed-item");
    	this.labelStatus.setVisible(true);
    }
    
    private void initiLabels() {
    	Register register = (Register)this.item;
    	this.labelCode.setText(register.getCode());
    	this.labelTypeCat.setText(register.getTypeName()+", "+register.getCategoryName());
    	this.labelVenc.setText(register.exiprationDateFormatted());
    	this.labelValue.setText(register.getValueFormatted());
    	this.labelStatus.setVisible(false);
    }

}
