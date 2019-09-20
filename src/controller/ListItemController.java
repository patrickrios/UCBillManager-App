package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ListItemController {
	
	@FXML
    private AnchorPane anchorListeSaleItem;

    @FXML
    private Label labelSaleCode;

    @FXML
    private Label labelNumItens;

    @FXML
    private Label labelTotalValue;

    @FXML
    private Label labelDateTime;

    @FXML
    private CheckBox checkboxItem;

    @FXML
    private Button buttonFavorite;

    private boolean favoriteControl = false;
    
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

}
