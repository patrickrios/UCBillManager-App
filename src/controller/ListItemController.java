package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.entity.Register;
import model.types.TypeDeleting;
import view.util.FadeEffect;

public class ListItemController {
	@FXML
    private AnchorPane anchorListeSaleItem;
	@FXML
    private Label labelCode;
	@FXML
    private Button buttonPaidStatus;
    @FXML
    private Label labelCategoryName;
    @FXML
    private Label labelTotalValue;
    @FXML
    private Label labelExpired;
    @FXML
    private CheckBox checkboxItem;
    @FXML
    private Button buttonFavorite;
    
    private StackPane stackList;
    
    private Register register;
    
    public void inti(Register register, StackPane stack)
    {
    	this.register = register;
    	this.stackList = stack;
    	initializeDatas();
    	initiFavorite();
    }
    
    @FXML
    public void checkboxSelected()
    {
        if(this.checkboxItem.isSelected()){
            this.anchorListeSaleItem.getStyleClass().add("list-sale-item-selected");
        }
        else{
            this.anchorListeSaleItem.getStyleClass().clear();
            this.anchorListeSaleItem.getStyleClass().add("anchor-list-item");
        }
    }
    
    @FXML
    void switchPaymentStatus(){
    	this.register.switchPaymentStatus();
    	defineButtonPaymentStatus(this.register.isPaid());
    }

    @FXML
    public void viewDetails(){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLViewDetail.fxml"));
    	try {
			Parent detailLayout = loader.load();
			ViewDetailController c = loader.getController();
			c.initi(this.stackList,this.register);
			new FadeEffect(detailLayout);
			this.stackList.getChildren().add(detailLayout);
    	} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void editThisItem(){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLEditRegister.fxml"));
    	
    	try {
			Parent edit = loader.load();
			EditRegisterController c = loader.getController();
			c.initi(this.stackList, this.register);
			new FadeEffect(edit);
			this.stackList.getChildren().add(edit);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void deleteThisItem(){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLDeletePopup.fxml"));
    	
    	try {
			Parent popup = loader.load();
			DeleteItemController c = loader.getController();
			c.initi(this.register, this.stackList, TypeDeleting.REGISTER);
			new FadeEffect(popup);
			this.stackList.getChildren().add(popup);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML
    public void switchFavoriteStatus() {
    	this.register.switchFavoriteStatus();
    	markAsFavorite();
    }

    private void markAsFavorite(){
        Image icon = null;

        if(this.register.isFavorite()){
            icon = new Image(getClass().getResourceAsStream("/view/img/list/star-favorite-selected.png"));
        }
        else{
            icon = new Image(getClass().getResourceAsStream("/view/img/list/favorite-star-unselected-light-15x14.png"));
        }

        this.buttonFavorite.setGraphic(new ImageView(icon));
    }

    private void initializeDatas(){
    	this.labelCode.setText(this.register.getCode());
    	this.labelCategoryName.setText(this.register.getCategoryName());
    	this.labelTotalValue.setText(this.register.getValueFormatted());
    	defineButtonPaymentStatus(this.register.isPaid());
    	
    }
    
    private void defineButtonPaymentStatus(boolean status){
    	String yes = "button-item-list-paid";
    	String not = "button-item-list-notpaid";
    	
    	this.buttonPaidStatus.getStyleClass().clear();
    	
    	if(status) {
    		buttonPaidStatus.getStyleClass().add(yes);
    		buttonPaidStatus.setText("sim");
    	}
    	else {
    		buttonPaidStatus.getStyleClass().add(not);
    		buttonPaidStatus.setText("não");
    	}
    	showLabelIfIsExpired();
    }
    
    private void showLabelIfIsExpired(){
    	if(this.register.isExpired() && !this.register.isPaid())
    		this.labelExpired.setText("atrasado");
    	else if(!this.register.isPaid() && !this.register.isExpired())
    		this.labelExpired.setText("a vencer");
    	else
    		this.labelExpired.setText("");
    }
    
    private void initiFavorite(){
    	if(this.register.isFavorite()){
    		markAsFavorite();
    	}
    }
}
