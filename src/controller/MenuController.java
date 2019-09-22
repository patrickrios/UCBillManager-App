package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.dao.CategoryDAO;
import model.dao.PaymentDAO;
import view.util.FadeEffect;
import java.io.IOException;

public class MenuController
{
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

    public void initi(AnchorPane anchorPane)
    {
    	this.anchorPane = anchorPane;
        this.buttonSel = this.buttonHomepage;
        loadHomepage();
    }

    @FXML
    public void loadHomepage()
    {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLHomepage.fxml"));
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonHomepage, "/view/img/pack2/homepageSelected-min.png", "/view/img/pack2/homepage-min.png");
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void loadCreateNewLayout()
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLCreateNew.fxml"));
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonCreate, "/view/img/pack2/createSelected-min.png", "/view/img/pack2/create-new-min.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void loadListRegisterLayout()
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLList.fxml"));
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonListView, "/view/img/pack2/listSelected-min.png", "/view/img/pack2/list-view-min.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadCategoryManagementLayout()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagement.fxml"));

        try {
            Parent parent = loader.load();
            ManagementController controller = loader.getController();
            controller.initi("Categorias", new CategoryDAO());
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonCategory, "/view/img/pack2/categorySelected-min.png", "/view/img/pack2/category-min.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadPaymentsManagementLayout()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLManagement.fxml"));

        try {
            Parent parent = loader.load();
            ManagementController controller = loader.getController();
            controller.initi("Pagamentos", new PaymentDAO());
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonPayment, "/view/img/pack2/paymentSelected-min.png", "/view/img/pack2/payment-min.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadReportLayout()
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLReport.fxml"));
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonReport, "/view/img/pack2/reportSelected-min.png", "/view/img/pack2/report-icon-min.png");
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

    private void unmarkButton()
    {    	
        this.buttonSel.getStyleClass().clear();
        this.buttonSel.getStyleClass().add("main-button-menu");
        
        Image icon = new Image(getClass().getResourceAsStream(this.iconPathUnmark));
    	ImageView g = new ImageView(icon);
    	
    	this.buttonSel.setGraphic(g);
    	
        	
    }
   
}
