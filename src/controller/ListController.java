package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.dao.RegisterDAO;
import model.entity.Persistent;
import model.entity.Register;
import view.util.FadeEffect;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListController implements Initializable
{
	@FXML
	private StackPane stackpaneList;
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
    private Label labelTotalRegisters;
    @FXML
    private ScrollPane scrollListItens;
    @FXML
    private VBox vboxListItens;
    
    ArrayList<Persistent> list ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.list = new RegisterDAO().findGroup(0,0);
		this.stackpaneList.setAlignment(Pos.TOP_LEFT);
		this.loadList();
		this.loadListView();
		this.scrollListItens.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

    private void loadList(){ 
    	this.list = new RegisterDAO().findGroup(0,0);
    }
    
    @FXML
    void loadListView()
    {
    	this.vboxListItens.getChildren().clear();
    	
    	try {
			Parent header = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLListViewHeader.fxml"));
			this.vboxListItens.getChildren().add(header);
			
			for(Persistent p : this.list){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLListItem.fxml"));
				Parent item = loader.load();
				ListItemController c = loader.getController();
				c.inti((Register)p, this.stackpaneList);
				this.vboxListItens.getChildren().add(item);
			}
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void loadListCards()
    {
    	FXMLLoader l = new FXMLLoader(getClass().getResource("/view/fxml/FXMLListCardsLayout.fxml"));
    	
    	try {
			Parent p = l.load();
			ListGridLayoutController c = l.getController();
			c.initi(this.list, this.stackpaneList);
			new FadeEffect(p);
			//this.paneListLayout.getChildren().setAll(p);
			
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    public void viewGridLayout(){
    	loadListCards();
        markButtonView(this.buttonViewGrid);
        unmarkButtonView(this.buttonViewList);
        changeViewGridIconOn();
        changeViewListIconOff();
    }

    @FXML
    void viewListLayout(){
    	loadList();
        markButtonView(this.buttonViewList);
        unmarkButtonView(this.buttonViewGrid);
        changeViewListIconOn();
        changeViewGridIconOff();
    }

    private void markButtonView (Button button){
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("button-pagination-nav");
        button.getStyleClass().add("button-view-selected");
    }

    private void unmarkButtonView(Button button){
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("button-pagination-nav");
    }

    private void changeViewGridIconOn(){
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-grid-selected-17x13.png"));
        this.buttonViewGrid.setGraphic(new ImageView(icon));
    }

    private void changeViewGridIconOff(){
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-grid-unselected-17x13.png"));
        this.buttonViewGrid.setGraphic(new ImageView(icon));
    }

    private void changeViewListIconOn(){
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-list-selected-17x14.png"));
        this.buttonViewList.setGraphic(new ImageView(icon));
    }

    private void changeViewListIconOff(){
        Image icon = null;
        icon = new Image(getClass().getResourceAsStream("/view/img/list/view-list-unselected-17x14.png"));
        this.buttonViewList.setGraphic(new ImageView(icon));
    }    
}