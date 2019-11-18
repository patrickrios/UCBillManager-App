package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.dao.ReportDAO;
import model.util.ReportCardItem;
import view.monthpicker.MonthPicker;

public class ReportController implements Initializable{
    @FXML
    private Label labelGenQuant;
    @FXML
    private Label labelExpenseQuant;
    @FXML
    private Label labelRevenueQuant;
    @FXML
    private Label labelGenTotal;
    @FXML
    private Label labelExpenseTotal;
    @FXML
    private Label labelRevenueTotal;
    @FXML
    private Pane paneMonthFilter;
    
    private MonthPicker monthPicker;
    
    private ArrayList<ReportCardItem> genList ;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.monthPicker = new MonthPicker();
		this.paneMonthFilter.getChildren().add(this.monthPicker);
		this.genList = new ReportDAO().getRegistersDatas();
		initiGeneralCard();
	}
	
	private void initiGeneralCard() {
		ReportCardItem item = this.genList.get(0);
		this.labelGenQuant.setText(item.getQuantity()+"");
		this.labelGenTotal.setText(item.getTotalFormatted());
		
		item = this.genList.get(1);
		this.labelExpenseQuant.setText(item.getQuantity()+"");
		this.labelExpenseTotal.setText(item.getTotalFormatted());
		
		item = this.genList.get(2);
		this.labelRevenueQuant.setText(item.getQuantity()+"");
		this.labelRevenueTotal.setText(item.getTotalFormatted());
	}
}