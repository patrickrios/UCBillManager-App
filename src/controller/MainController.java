package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.util.FadeEffect;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private AnchorPane anchorpaneContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadHomepage();
    }

    @FXML
    public void loadHomepage()
    {
        try{
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLHomepage.fxml"));
            new FadeEffect(parent);
            anchorpaneContent.getChildren().setAll(parent);
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
            anchorpaneContent.getChildren().setAll(parent);
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
            anchorpaneContent.getChildren().setAll(parent);
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
            this.anchorpaneContent.getChildren().setAll(parent);
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
            this.anchorpaneContent.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}