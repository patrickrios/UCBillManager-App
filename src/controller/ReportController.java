package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private VBox vboxCatRanking;
    @FXML
    private VBox vboxPayRanking;
    @FXML
    private Pane paneMonthFilter;
    @FXML
    private Pane paneChart;
    
    private MonthPicker monthPicker;
    
    private ArrayList<ReportCardItem> genList ;
    
    private ArrayList<ReportCardItem> catList ;
    
    private ArrayList<ReportCardItem> payList ;
    
    private int numberRegisters;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.monthPicker = new MonthPicker();
		this.paneMonthFilter.getChildren().add(this.monthPicker);
		loadInitialDatas();
		initiGeneralCard();
		initCategoriesCard();
		initPaymentsCard();
		initPieChart();
	}
	
	private void loadInitialDatas() {
		ReportDAO dao = new ReportDAO();
		this.genList = dao.getRegistersDatas();
		this.catList = dao.getCategoriesDatas();
		this.payList = dao.getPaymentsDatas();
		this.numberRegisters = dao.numberRegister();
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

	private void initCategoriesCard() {
		for(ReportCardItem i : this.catList) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLReportCardItem.fxml"));
			try {
				Parent p = loader.load();
				ReportCardItemController c = loader.getController();
				c.init(i, this.numberRegisters);
				this.vboxCatRanking.getChildren().add(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initPaymentsCard() {
		for(ReportCardItem i : this.payList) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/FXMLReportCardItem.fxml"));
			try {
				Parent p = loader.load();
				ReportCardItemController c = loader.getController();
				c.init(i, this.numberRegisters);
				this.vboxPayRanking.getChildren().add(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initPieChart() {
		ReportCardItem item = this.genList.get(1);
		String exp = item.getName()+" ("+item.getPercIntegerValue(numberRegisters)+"%)";
		int expPer = item.getPercIntegerValue(numberRegisters);
		item = this.genList.get(2);
		String rev = item.getName()+" ("+item.getPercIntegerValue(numberRegisters)+"%)";
		int revPerc = item.getPercIntegerValue(numberRegisters);
		
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data(exp, expPer),
                new PieChart.Data(rev, revPerc));
		
        final PieChart chart = new PieChart(pieChartData);
        chart.setPrefSize(416,248);
        chart.setLegendVisible(false);
        this.paneChart.getChildren().add(chart);
	}
}