package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private AnchorPane anchorpaneContent;
    @FXML
    private AnchorPane menuConteiner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadMenu();
    }

    private void loadMenu()
    {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("/view/fxml/FXMLMenu.fxml"));

        try {
            Parent parent = loader.load();
            MenuController controller = loader.getController();
            controller.initi(this.anchorpaneContent);
            this.menuConteiner.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}