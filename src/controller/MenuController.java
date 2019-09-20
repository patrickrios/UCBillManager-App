package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    public void initi(AnchorPane anchorPane){
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
            markButton(this.buttonHomepage, "/view/img/homeIconSelected.png");
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
            markButton(this.buttonCreate, "/view/img/addNewIconSelected.png");
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
            markButton(this.buttonListView, "/view/img/listIconSelected.png");
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
            controller.initi("Categorias");
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonCategory, "/view/img/categoryIconSelected.png");
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
            controller.initi("Pagamentos");
            new FadeEffect(parent);
            this.anchorPane.getChildren().setAll(parent);
            unmarkButton();
            markButton(this.buttonPayment, "/view/img/paymentIconSelected.png");
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
            markButton(this.buttonReport, "/view/img/reportIconSelected.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void markButton(Button button, String iconPath){
        button.getStyleClass().clear();
        button.getStyleClass().add("main-button-menu-selected");

        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        button.setGraphic(new ImageView(icon));

        this.buttonSel = button;
    }

    private void unmarkButton()
    {
        this.buttonSel.getStyleClass().clear();
        this.buttonSel.getStyleClass().add("main-button-menu");
    }
}
