package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ManagementController
{
    @FXML
    private Label labelTitle;

    public void initi(String title)
    {
        this.labelTitle.setText(title);
    }
}
