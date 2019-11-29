package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.entity.Persistent;
import model.entity.Register;
import model.util.DisposableList;
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
    @FXML
    private Pane paneOptions;
    
    private StackPane stackList;
    
    private Register register;
    
    private ArrayList<Persistent> list;
    
    private boolean showOptionsControl = false;
    
    private Pane opt;
    
    private DisposableList dispList;
    
    public void inti(Register register, StackPane stack, ArrayList<Persistent> list, Pane opt, DisposableList dispList){
    	this.register = register;
    	this.stackList = stack;
    	this.list = list;
    	this.opt = opt;
    	this.dispList = dispList;
    	initializeDatas();
    	initiFavorite();
    }
    
    @FXML
    public void checkboxSelected()
    {
        if(this.checkboxItem.isSelected()){
            this.anchorListeSaleItem.getStyleClass().add("list-sale-item-selected");
            if(this.list.isEmpty())
            	this.opt.setVisible(true);
            this.list.add(this.register);
        }
        else{
            this.anchorListeSaleItem.getStyleClass().clear();
            this.anchorListeSaleItem.getStyleClass().add("anchor-list-item");
            //this.anchorListeSaleItem.getStyleClass().add("list-view-card");
            this.list.remove(this.register);
            if(this.list.isEmpty())
            	this.opt.setVisible(false);
        }
    }
    
    @FXML
    void switchPaymentStatus(){
    	this.register.switchPaymentStatus();
    	defineButtonPaymentStatus(this.register.isPaid());
    }
    
    @FXML
    public void editThisItem(){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLEditRegister.fxml"));
    	
    	try {
			Parent edit = loader.load();
			EditRegisterController c = loader.getController();
			c.initi(this.stackList,this.register);
			new FadeEffect(edit);
			this.stackList.getChildren().add(edit);
			c.fullSizeConteiner();
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
			c.initi(this.register,this.stackList,this.dispList);
			new FadeEffect(popup);
			this.stackList.getChildren().add(popup);
			c.fullSizeConteiner();
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
    
    @FXML
    void showOptions() {
    	showOptionsControl = !showOptionsControl;
    	this.paneOptions.setVisible(showOptionsControl);
    }

    private void markAsFavorite(){
        Image icon = null;
        this.buttonFavorite.getStyleClass().clear();
        if(this.register.isFavorite()){
            icon = new Image(getClass().getResourceAsStream("/view/img/list/star-favorite-selected.png"));
            this.buttonFavorite.getStyleClass().addAll("button","button-reset","button-fav-item-selected");
        }
        else{
            icon = new Image(getClass().getResourceAsStream("/view/img/list/favorite-star-unselected-light-15x14.png"));
            this.buttonFavorite.getStyleClass().addAll("button","button-reset","button-fav-item");
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
