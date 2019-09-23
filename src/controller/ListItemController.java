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
import model.bean.Register;
import view.util.FadeEffect;

public class ListItemController {
	
	@FXML
    private AnchorPane anchorListeSaleItem;
	@FXML
    private Label labelCode;
    @FXML
    private Label labelCategoryName;
    @FXML
    private Label labelTotalValue;
    @FXML
    private Label labelPaid;
    @FXML
    private Label labelExpired;
    @FXML
    private CheckBox checkboxItem;
    @FXML
    private Button buttonFavorite;
    
    private StackPane stackList;

    private boolean favoriteControl = false;
    
    private Register register;
    
    public void inti(Register register, StackPane stack)
    {
    	this.register = register;
    	this.stackList = stack;
    	initializeDatas();
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
    public void viewDetails()
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLViewDetail.fxml"));
    	try {
			Parent detailLayout = loader.load();
			ViewDetailController c = loader.getController();
			c.initi(this.stackList);
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
			c.initi(this.stackList);
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
			c.initi(this.register.getCode(), this.stackList);
			new FadeEffect(popup);
			this.stackList.getChildren().add(popup);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    public void markAsFavorite()
    {
        Image icon = null;

        if(favoriteControl){
            this.favoriteControl = false;
            icon = new Image(getClass().getResourceAsStream("/view/img/list/star-favorite-unselected.png"));
        }

        else{
            this.favoriteControl = true;
            icon = new Image(getClass().getResourceAsStream("/view/img/list/star-favorite-selected.png"));
        }

        this.buttonFavorite.setGraphic(new ImageView(icon));
    }

    private void initializeDatas()
    {
    	this.labelCode.setText(this.register.getCode());
    	this.labelCategoryName.setText(this.register.getCategoryName());
    	this.labelTotalValue.setText(this.register.getValueFormatted());
    	defineBoolLabel(this.register.isPaid(), this.labelPaid);
    	defineBoolLabel(!this.register.isPaid(), this.labelExpired);
    }
    
    private void defineBoolLabel(boolean is, Label l)
    {
    	String yes = "label-item-yes";
    	String not = "label-item-not";
    	
    	if(is) {
    		l.getStyleClass().add(yes);
    		l.setText("sim");
    	}
    	else {
    		l.getStyleClass().add(not);
    		l.setText("não");
    	}
    }
}
