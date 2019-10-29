package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.entity.Persistent;
import model.entity.Register;

public class ListItemGridController {
	
	@FXML
    private Label labelCode;
    @FXML
    private Label labelValue;
    @FXML
    private Label labelCategoryName;
    @FXML
    private Label labelPaid;
    @FXML
    private Label labelExpiration;
    @FXML
    private Button buttonFavorite;
    @FXML
    private CheckBox checkboxItem;
    private boolean favoriteControl = false;
    private Register register;
    private StackPane stack;
    
    public void initi(Register r, StackPane stack)
    {
    	this.register = r;
    	this.stack = stack;
    	this.labelCode.setText(this.register.getCode());
    	this.labelCategoryName.setText(this.register.getCategoryName());
    	this.labelPaid.setText(this.register.getPaidStatus());
    	this.labelExpiration.setText((this.register.isPaid())?"não":"sim");
    	this.labelValue.setText(this.register.getValueFormatted());
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

    @FXML
    public void checkboxSelected()
    {
       
    }
}
