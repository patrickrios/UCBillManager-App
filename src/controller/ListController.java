package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.entity.List;
import model.entity.Persistent;
import model.entity.Register;
import model.types.TypeList;
import model.types.TypeListLayout;

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
    private Button buttonPreviousPage;
    @FXML
    private Label labelPaginationInfo;
    @FXML
    private Button buttonNextPage;
    @FXML
    private Button buttonDeleteAll;
    @FXML
    private Button buttonFavAll;
    @FXML
    private Button buttonViewList;
    @FXML
    private Button buttonViewGrid;
    @FXML
    private ChoiceBox<String> choiceboxType;
    @FXML
    private AnchorPane anchorListHeader;
    @FXML
    private Label labelTotalRegisters;
    @FXML
    private ScrollPane scrollListItens;
    @FXML
    private VBox vboxListItens;
    
    private ArrayList<Persistent> itens;
    
    private List list = new List();
    
    private int listLayout = TypeListLayout.LIST;
    
    private int itensType = TypeList.ALL;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadList();
		loadViewLayout();
		initComponents();
	}

    private void loadList(){ 
    	this.itens = this.list.getItens(this.itensType);
    }
    
    private void loadViewLayout() {
    	if(this.listLayout == TypeListLayout.LIST)
    		viewListLayout();
    	else
    		viewGridLayout();
    }
    
    private void loadListLayout(){
    	this.vboxListItens.getChildren().clear();
    	try {
			Parent header = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLListViewHeader.fxml"));
			this.vboxListItens.getChildren().add(header);
			
			for(Persistent p : this.itens){
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
    
    private void loadGridLayout(){
    	this.vboxListItens.getChildren().clear();
    	FlowPane flow = new FlowPane();
    	flow.getChildren().clear();
    	flow.setHgap(30);
    	flow.setVgap(25);
    	flow.setPrefWidth(795);
    	
    	for(Persistent p : this.itens) {
	    	FXMLLoader grid = new FXMLLoader(getClass().getResource("/view/fxml/FXMLListItemCard.fxml"));
	    	try {
				Parent card = grid.load();
				ListItemController c = grid.getController();
				c.inti((Register)p, this.stackpaneList);
				flow.getChildren().add(card);
			} 
	    	catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	this.vboxListItens.getChildren().add(flow);
    }

    @FXML
    public void viewGridLayout(){
    	this.listLayout = TypeListLayout.GRID;
    	loadGridLayout();
        markButtonView(this.buttonViewGrid);
        unmarkButtonView(this.buttonViewList);
        changeViewGridIconOn();
        changeViewListIconOff();
    }

    @FXML
    void viewListLayout(){
    	this.listLayout = TypeListLayout.LIST;
    	loadListLayout();
        markButtonView(this.buttonViewList);
        unmarkButtonView(this.buttonViewGrid);
        changeViewListIconOn();
        changeViewGridIconOff();
    }

    @FXML
    void searchItens(){
    	String input = this.textfieldSearch.getText();
    	this.list.resetPagination();
    	this.itens = this.list.searchItens(input);
    	loadViewLayout();
    }
    @FXML
    void showNextPage(){
    	this.itens = list.loadNextPage(this.itensType);
    	loadViewLayout();
    	updatePaginationInfo();
    	updatePaginationControls();
    	
    }
    @FXML
    void showPreviousPage(){
    	this.itens = list.loadPreviousPage(this.itensType);
    	loadViewLayout();
    	updatePaginationInfo();
    	updatePaginationControls();
    }
    @FXML
    void showFavorites(){
        	this.itensType = TypeList.FAV;
        	this.list.resetPagination();
        	this.itens = list.getItens(this.itensType);
        	loadViewLayout();		
    }
    
    private void markButtonView (Button button){
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("button-list-control-selected");
    }

    private void unmarkButtonView(Button button){
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("button-list-control");
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
    
    private void initComponents(){
    	this.labelTotalRegisters.setText("("+this.list.valueOfTotalRegister()+")");
    	updatePaginationInfo();
    	updatePaginationControls();
    	this.scrollListItens.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.stackpaneList.setAlignment(Pos.TOP_LEFT);
		this.buttonDeleteAll.setDisable(true);
		this.buttonFavAll.setDisable(true);
		initiChoicebox();
    }

    private void updatePaginationInfo(){
    	this.labelPaginationInfo.setText(this.list.paginationInfo());
    }
    
    private void updatePaginationControls(){
    	if(this.list.isFirstPage())
    		this.buttonPreviousPage.setDisable(true);
    	else
    		this.buttonPreviousPage.setDisable(false);
    	
    	if(this.list.isLastPage())
    		this.buttonNextPage.setDisable(true);
    	else
    		this.buttonNextPage.setDisable(false);	
    }
    
    private void initiChoicebox() {
    	this.choiceboxType.getItems().addAll("Todos", "Despesas", "Receitas", "Favoritos");
    	this.choiceboxType.setValue("Todos");
    	
    	this.choiceboxType.getSelectionModel().selectedIndexProperty().addListener(new 
				ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,
					Number newValue) {
					if(newValue.intValue()==3)
						showFavorites();
					if(newValue.intValue()==0) {
						itensType = TypeList.ALL;
			        	list.resetPagination();
			        	loadList();
			    		loadViewLayout();
					}
				}		
		});
    }
}