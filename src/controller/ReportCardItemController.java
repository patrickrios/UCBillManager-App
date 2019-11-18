package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.util.ReportCardItem;

public class ReportCardItemController {
    @FXML
    private Pane paneReportCardItem;
    @FXML
    private Label labelName;
    @FXML
    private Label labelQuant;
    @FXML
    private Label labelPerc;
    @FXML
    private Label labelTotalValue;
    
    public void init(ReportCardItem item, int total) {
    	this.labelName.setText(item.getName());
    	this.labelQuant.setText(item.getQuantity()+"");
    	this.labelPerc.setText(item.getPerc(total));
    	this.labelTotalValue.setText(item.getTotalFormatted());
    }

}
