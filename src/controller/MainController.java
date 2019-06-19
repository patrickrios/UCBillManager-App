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
        try
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/FXMLHomepage.fxml"));
            new FadeEffect(parent);
            anchorpaneContent.getChildren().setAll(parent);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}