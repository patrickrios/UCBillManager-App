package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListController
{

    @FXML
    private Label labelListTitle;

    @FXML
    private TextField textfieldSearch;

    @FXML
    private Button buttonPagPreviousPage;

    @FXML
    private Label labelPaginationInfo;

    @FXML
    private Button buttonPagNextPage;

    @FXML
    private Button buttonDeleteAll;

    @FXML
    private Button buttonViewList;

    @FXML
    private Button buttonViewGrid;

    @FXML
    private AnchorPane anchorListHeader;

    @FXML
    private VBox vboxItens;

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


    private void loadList()
    {

    }

    void setListHeader(String path)
    {
        try
        {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            anchorListHeader.getChildren().setAll(parent);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void viewGridLayout()
    {
        markButtonView(this.buttonViewGrid);
        unmarkButtonView(this.buttonViewList);
        changeViewGridIconOn();
        changeViewListIconOff();
    }

    @FXML
    void viewListLayout()
    {
        markButtonView(this.buttonViewList);
        unmarkButtonView(this.buttonViewGrid);
        changeViewListIconOn();
        changeViewGridIconOff();
    }

    private void markButtonView (Button button)
    {
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("button-pagination-nav");
        button.getStyleClass().add("button-view-selected");
    }

    private void unmarkButtonView(Button button)
    {
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("button-pagination-nav");
    }

    private void changeViewGridIconOn()
    {
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-grid-selected-17x13.png"));
        this.buttonViewGrid.setGraphic(new ImageView(icon));
    }

    private void changeViewGridIconOff()
    {
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-grid-unselected-17x13.png"));
        this.buttonViewGrid.setGraphic(new ImageView(icon));
    }

    private void changeViewListIconOn()
    {
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-list-selected-17x14.png"));
        this.buttonViewList.setGraphic(new ImageView(icon));
    }

    private void changeViewListIconOff()
    {
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-list-unselected-17x14.png"));
        this.buttonViewList.setGraphic(new ImageView(icon));
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