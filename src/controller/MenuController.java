package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.dao.CategoryDAO;
import model.dao.PaymentDAO;
import view.util.FadeEffect;
import java.io.IOException;

public class MenuController{
	@FXML
    private AnchorPane anchorMenu;
    @FXML
    private Button buttonHomepage;
    @FXML
    private Button buttonCreate;
    @FXML
    private Button buttonListView;
    @FXML
    private Button buttonReport;
    @FXML
    private Button buttonCategory;
    @FXML
    private Button buttonPayment;
    
    private Button buttonSel;
    
    private AnchorPane anchorPane;
    
    private String iconPathUnmark = "";
    
    private StackPane mainStack;

    public void initi(AnchorPane anchorPane, StackPane stack){
    	this.anchorPane = anchorPane;
        this.buttonSel = this.buttonHomepage;
        this.mainStack = stack;
        loadHomepage();
        fullHeightSize();
    }

    @FXML
    public void loadHomepage(){
    	clearStack();
    	FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/fxml/FXMLHomepage.fxml")));
        try{
            Parent parent = loader.load();
            HomepageController c = loader.getController();
            c.initi(this.mainStack);
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonHomepage, "/view/img/menu/homeIconSelected.png", "/view/img/menu/homeIcon.png");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void loadCreateNewLayout() {
    	clearStack();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLCreateNew.fxml"));
        try {
            Parent parent = loader.load();
            CreateNewController c = loader.getController();
            c.initi(this.mainStack);
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonCreate, "/view/img/menu/addNewIconSelected.png", "/view/img/menu/addNewIcon.png");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadListRegisterLayout() {
    	clearStack();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLList.fxml"));
        try {
            Parent parent = loader.load();
            ListController c = loader.getController();
            c.initi(this.mainStack);
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonListView, "/view/img/menu/listIconSelected.png", "/view/img/menu/listIcon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadCategoryManagementLayout(){
    	clearStack();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManager.fxml"));

        try {
            Parent parent = loader.load();
            ManagerController controller = loader.getController();
            controller.initi("Categorias",new CategoryDAO(),this.mainStack);
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonCategory, "/view/img/menu/categoryIconSelected.png", "/view/img/menu/categoryIcon.png");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadPaymentsManagementLayout(){
    	clearStack();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManager.fxml"));

        try {
            Parent parent = loader.load();
            ManagerController controller = loader.getController();
            controller.initi("Pagamentos",new PaymentDAO(),this.mainStack);
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonPayment, "/view/img/menu/paymentIconSelected.png", "/view/img/menu/paymentIcon.png");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadReportLayout(){
    	clearStack();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLReport.fxml"));
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonReport, "/view/img/menu/reportIconSelected.png", "/view/img/menu/reportIcon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void markButton(Button button, String iconPath, String unmarkPath){
        button.getStyleClass().clear();
        button.getStyleClass().add("main-button-menu-selected");

        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        button.setGraphic(new ImageView(icon));

        this.buttonSel = button;
        this.iconPathUnmark = unmarkPath;
    }

    private void unmarkButton(){    	
        this.buttonSel.getStyleClass().clear();
        this.buttonSel.getStyleClass().add("main-button-menu");
        
        Image icon = new Image(getClass().getResourceAsStream(this.iconPathUnmark));
    	ImageView g = new ImageView(icon);
    	
    	this.buttonSel.setGraphic(g);        	
    }
    
    private void clearStack() {
    	this.mainStack.getChildren().clear();
    	this.mainStack.getChildren().add(this.anchorPane);
    }
    
    private void fullHeightSize() {
    	AnchorPane.setBottomAnchor(this.anchorMenu,0.0);
    	AnchorPane.setTopAnchor(this.anchorMenu,0.0);
    	AnchorPane.setRightAnchor(this.anchorMenu,0.0);
    }
}
